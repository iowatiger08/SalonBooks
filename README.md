# SalonBooks

Salon appointment, order, and inventory management application.

**GitHub:** https://github.com/iowatiger08/SalonBooks.git  
**Branch:** `modernize/spring-boot-3-docker`

---

## Stack

| Concern | Technology |
|---|---|
| Framework | Spring Boot 3.3.5 (Spring 6 / Hibernate 6) |
| Language | Java 21 (runs on JDK 25) |
| Persistence | Spring Data JPA + Liquibase |
| DB (dev/test) | H2 in-memory |
| DB (prod) | PostgreSQL 16 |
| Security | Spring Security + BCrypt |
| Models | Lombok `@Getter @Setter` |
| Tests | JUnit 5 + Mockito 5 + `@SpringBootTest` |
| Deployment | Docker (multi-stage WAR) |

---

## Quick start (local dev)

```bash
# Requires JDK 21+ and Maven 3.9+
mvn spring-boot:run -Dspring-boot.run.profiles=dev
# App at http://localhost:8080  (H2 console at /h2-console)
```

## Docker

```bash
cp .env.example .env           # set DB_PASSWORD
docker compose up --build      # PostgreSQL + app
```

Liquibase runs migrations automatically on startup. Seed data is included.

---

## Build & quality gates

```bash
mvn verify                  # compile + test + all quality checks
mvn spotless:apply          # auto-format (Google Java Format 1.35)
mvn spotbugs:gui            # browse SpotBugs findings interactively
```

| Gate | Tool | Config |
|---|---|---|
| Formatting | Spotless + Google Java Format 1.35 | `pom.xml` |
| Linting | Checkstyle 10.18 | `config/checkstyle.xml` |
| Bug patterns | SpotBugs 4.10 + FindSecBugs | `config/spotbugs-exclude.xml` |
| Compile-time | Error Prone 2.50 | `pom.xml` |

> **JDK 25 note:** `.mvn/jvm.config` adds the `--add-exports` flags required by
> Lombok 1.18.46 and Error Prone 2.50 against JDK 25 internals.

---

## Architecture

```
src/main/java/.../salonbooks/
├── SalonBooksApplication.java       # Spring Boot entry point
├── core/controller/                 # Spring MVC controllers (JSP views)
├── model/                           # JPA entities + form models
├── repository/                      # Spring Data JPA repositories
├── service/{impl}/                  # Service layer
├── security/                        # Spring Security config + UserDetailsService
└── model/flows/                     # Legacy Web Flow form models (kept for JSP binding)

src/main/resources/
├── application.properties           # common config
├── application-dev.properties       # H2 datasource
├── application-prod.properties      # PostgreSQL datasource (env vars)
└── db/changelog/                    # Liquibase: schema + seed data
```

## Next phase: React UI

The JSP views are temporary. The planned migration:
1. Convert `@Controller` → `@RestController` (JSON responses)
2. Add Vite + React frontend in `frontend/`
3. Add CORS + JWT config to `SecurityConfig`
4. Add `frontend` service to `docker-compose.yml`

---

## Known issues (inherited from original)

1. Validation not fully implemented
2. Appointment-first workflow can produce bad URLs
3. Save confirmations not shown in UI

---

**Copyright:** Tony E Hansen  
**License:** MIT — see [LICENSE](LICENSE)
