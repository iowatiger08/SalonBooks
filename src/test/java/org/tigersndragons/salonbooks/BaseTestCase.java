package org.tigersndragons.salonbooks;

import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.tigersndragons.salonbooks.model.Person;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("dev")
@Transactional
public abstract class BaseTestCase {

  protected Logger logger = LoggerFactory.getLogger(getClass());

  protected Person getDefaultPerson() {
    Person person = new Person();
    person.setId(0L);
    return person;
  }
}
