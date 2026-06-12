# SalonBooks — CLAUDE.md

Project context and conventions for Claude Code sessions.

## What this project is

Salon appointment/order/inventory management app. Currently on branch
`modernize/spring-boot-3-docker` which migrated the codebase from Spring 4 / Hibernate 4
to Spring Boot 3.3.5, Spring Data JPA, Docker, and a full quality toolchain.

**GitHub:** https://github.com/iowatiger08/SalonBooks.git

---

## Environment

- **Runtime:** Amazon Corretto 25 (JDK 25)
- **Maven:** IntelliJ's bundled Maven at
  `/Applications/IntelliJ IDEA.app/Contents/plugins/maven/lib/maven3/bin/mvn`
  (the `mvn` command is NOT on PATH — use the full path or `! <full path> <goal>`)
- **Active build profile:** `dev` uses H2 in-memory; `prod` uses PostgreSQL via Docker

**The `.mvn/jvm.config` file** adds `--add-exports` for JDK 25 internals required by
Lombok 1.18.46 and Error Prone 2.50. Without it, the build fails.

---

## Key decisions & constraints

| Decision | Reason |
|---|---|
| Lombok 1.18.46 (pinned) | Spring Boot default too old for JDK 25 |
| SpotBugs 4.10.2.0 (pinned) | 4.8.x can't read JDK 25 class files (major version 69) |
| Spotless 3.6.0 + GJF 1.35.0 | 2.43.x broke on JDK 25 javac internals |
| Error Prone 2.50.0 | Latest; needs `.mvn/jvm.config` to open jdk.compiler |
| `CASE_INSENSITIVE_IDENTIFIERS=TRUE` in H2 URL | `ORDER` is a SQL reserved word |
| WAR packaging (`java -jar app.war`) | JSP views can't be served from embedded JAR |
| Seed data at ID=0 | Legacy tests look up entities by `id=0L`; autoincrement starts at 1 |

---

## Build commands

```bash
MVN="/Applications/IntelliJ IDEA.app/Contents/plugins/maven/lib/maven3/bin/mvn"
"$MVN" clean verify              # full build: compile + test + Spotless + Checkstyle + SpotBugs
"$MVN" test                      # tests only (H2)
"$MVN" spotless:apply            # auto-format all Java with Google Java Format
"$MVN" spotbugs:gui              # interactive SpotBugs GUI
```

---

## Quality toolchain

| Tool | Purpose | Config file |
|---|---|---|
| Spotless + GJF 1.35 | Formatting (2-space, 100-char) | `pom.xml` |
| Checkstyle 10.18 | Naming, banned imports, code patterns | `config/checkstyle.xml`, `config/checkstyle-suppressions.xml` |
| SpotBugs 4.10 + FindSecBugs 1.13 | Bug patterns + security | `config/spotbugs-exclude.xml` |
| Error Prone 2.50 | Compile-time bug detection | `pom.xml` compiler args |
| Lombok 1.18.46 | Boilerplate reduction | `lombok.config` |

**Suppressions philosophy:**
- `checkstyle-suppressions.xml` suppresses visibility rules on Spring controllers,
  flow models, and `SalonObject` (protected fields by design)
- `spotbugs-exclude.xml` suppresses EI_EXPOSE_REP on controllers/DTOs/flow models,
  MS_MUTABLE_ARRAY on view-helper constants, and all findings on
  `EncryptionServiceImpl` (scheduled for removal after BCrypt migration)

---

## Package layout

```
src/main/java/.../salonbooks/
├── SalonBooksApplication.java
├── core/controller/          # Spring MVC controllers
├── model/                    # JPA entities + DTOs
│   ├── flows/                # Legacy Web Flow form models
│   ├── factory/              # ContactFactory
│   └── type/                 # Enums + BaseLookup
├── repository/               # Spring Data JPA (one interface per entity)
├── service/{impl}/           # Service layer
├── security/                 # SecurityConfig, SalonUserDetailsService
└── exception/
```

---

## Testing

All tests extend `BaseTestCase` (`@SpringBootTest @ActiveProfiles("dev") @Transactional`).
Tests run against H2 with Liquibase seed data. 53 tests pass, 2 disabled.

**Seed data** is in `db/changelog/db.changelog-002-seed.xml` and includes records at
`ID=0` for Employee, Person, Address, Appointment, Order, Item, and Contact
to satisfy legacy test expectations.

---

## Upcoming: React UI migration

When converting to a React frontend:
1. Add `@RestController` to controllers (or create `api/` package alongside JSP controllers)
2. Configure CORS in `SecurityConfig`
3. Add JWT or session cookie handling
4. Create `frontend/` module with Vite + React
5. Add `frontend` service to `docker-compose.yml`
6. Remove JSP dependencies (`tomcat-embed-jasper`, JSTL) from pom.xml
