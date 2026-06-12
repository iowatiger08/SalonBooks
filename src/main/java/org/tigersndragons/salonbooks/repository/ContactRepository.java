package org.tigersndragons.salonbooks.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.tigersndragons.salonbooks.model.Contact;
import org.tigersndragons.salonbooks.model.Person;

public interface ContactRepository extends JpaRepository<Contact, Long> {
  List<Contact> findByPerson(Person person);
}
