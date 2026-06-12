package org.tigersndragons.salonbooks.service;

import java.util.List;
import org.tigersndragons.salonbooks.model.Contact;
import org.tigersndragons.salonbooks.model.Person;
import org.tigersndragons.salonbooks.model.type.ContactType;

public interface ContactService {

  Contact getContactbyId(Long id);

  List<Contact> getContactsByPerson(Person p);

  void saveContact(Contact c);

  List<Contact> getActiveContacts();

  Contact createContactTypeForPerson(Person person, ContactType type);

  Contact createContactTypeForPerson(Person person, ContactType type, String label);

  List<ContactType> getContactTypeList();

  List<Contact> createDefaultContacts(Person person);
}
