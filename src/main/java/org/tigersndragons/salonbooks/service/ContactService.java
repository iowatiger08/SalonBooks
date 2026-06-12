package org.tigersndragons.salonbooks.service;

import java.util.List;

import org.tigersndragons.salonbooks.model.Contact;
import org.tigersndragons.salonbooks.model.Person;
import org.tigersndragons.salonbooks.model.type.ContactType;

public interface ContactService {

	public Contact getContactbyId(Long id);
	
	public List<Contact> getContactsByPerson(Person p);
	
	public void saveContact(Contact c);
	
	public List<Contact> getActiveContacts();
	
	public Contact createContactTypeForPerson(Person person, ContactType type);
	
	public Contact createContactTypeForPerson(Person person, ContactType type, String label);
	

	public List<ContactType> getContactTypeList();
	
	public List<Contact> createDefaultContacts(Person person);

}
