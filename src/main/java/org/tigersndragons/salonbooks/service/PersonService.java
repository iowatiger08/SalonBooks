package org.tigersndragons.salonbooks.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.tigersndragons.salonbooks.model.Person;
import org.tigersndragons.salonbooks.model.PersonProfile;
@Service
public interface PersonService {

	public Person lookupByPhoneNumber(String phoneNumber);
	
	public Person lookupByLastName(String lastName);
	
	public List<Person> getListOfActivePersons();
	
	public Person getPersonById(Long id);
	
	public Person createPerson();

	public Person getDefaultPerson();
	
	public Person createPerson(String phoneNumber) ;

	public void save(Person person);
	
	public PersonProfile getPersonProfile(Person person);
	
	public PersonProfile createPersonProfile();

	public PersonProfile createPersonProfile(Person person);
	
	public PersonProfile updatePersonProfile (PersonProfile profile);
	
}
