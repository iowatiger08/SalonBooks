package org.tigersndragons.salonbooks.service;

import java.util.List;
import org.tigersndragons.salonbooks.model.Person;
import org.tigersndragons.salonbooks.model.PersonProfile;

public interface PersonService {

  Person lookupByPhoneNumber(String phoneNumber);

  Person lookupByLastName(String lastName);

  List<Person> getListOfActivePersons();

  Person getPersonById(Long id);

  Person createPerson();

  Person getDefaultPerson();

  Person createPerson(String phoneNumber);

  void save(Person person);

  PersonProfile getPersonProfile(Person person);

  PersonProfile createPersonProfile();

  PersonProfile createPersonProfile(Person person);

  PersonProfile updatePersonProfile(PersonProfile profile);
}
