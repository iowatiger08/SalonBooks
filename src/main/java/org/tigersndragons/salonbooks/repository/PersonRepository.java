package org.tigersndragons.salonbooks.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.tigersndragons.salonbooks.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
  Optional<Person> findByPrimaryPhoneNumber(String phoneNumber);

  Optional<Person> findFirstByLastName(String lastName);
}
