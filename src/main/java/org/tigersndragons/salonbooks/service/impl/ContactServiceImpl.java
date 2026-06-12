package org.tigersndragons.salonbooks.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tigersndragons.salonbooks.ServiceUtils;
import org.tigersndragons.salonbooks.model.Contact;
import org.tigersndragons.salonbooks.model.Employee;
import org.tigersndragons.salonbooks.model.Person;
import org.tigersndragons.salonbooks.model.factory.ContactFactory;
import org.tigersndragons.salonbooks.model.type.ContactType;
import org.tigersndragons.salonbooks.repository.ContactRepository;
import org.tigersndragons.salonbooks.service.ContactService;
import org.tigersndragons.salonbooks.service.EmployeeService;

@Service
public class ContactServiceImpl extends BaseServiceImpl implements ContactService {

  private static final long serialVersionUID = 1L;

  @Autowired private ContactRepository contactRepository;
  @Autowired private EmployeeService employeeService;

  public List<Contact> getActiveContacts() {
    return contactRepository.findAll();
  }

  public Contact createContact() {
    return createContactForPerson(new Person());
  }

  public Contact createContactForPerson(Person person) {
    Contact newOne = ContactFactory.createContact("", null);
    newOne.setPerson(person);
    return newOne;
  }

  public Contact createContactTypeForPerson(Person person, ContactType type) {
    Contact newOne =
        type == null ? ContactFactory.defaultContact() : ContactFactory.createContact("", type);
    newOne.setPerson(person);
    return newOne;
  }

  public Contact createContactTypeForPerson(Person person, ContactType type, String label) {
    Contact newOne = ContactFactory.createContact(label, type);
    newOne.setPerson(person);
    return newOne;
  }

  public void closeContact(Contact appt) {
    appt.setIsActive("N");
    contactRepository.save(appt);
  }

  public Contact assignContacttoPerson(Contact appt, Person person) {
    appt.setPerson(person);
    return appt;
  }

  public List<Contact> getContactsForPerson(Person p, Employee emp) {
    ServiceUtils.assertNotNull("Person cannot be null", p);
    return contactRepository.findByPerson(p);
  }

  public List<ContactType> getContactTypeList() {
    return new ArrayList<>();
  }

  public Contact getContactById(long id) {
    return contactRepository.findById(id).orElse(null);
  }

  public List<Contact> getContactsByPerson(Person p) {
    return contactRepository.findByPerson(p);
  }

  public void save(Contact theContact) {
    contactRepository.save(theContact);
  }

  public Contact getContactbyId(Long id) {
    return getContactById(id);
  }

  public void saveContact(Contact c) {
    save(c);
  }

  public List<Contact> createDefaultContacts(Person person) {
    List<Contact> contacts = new ArrayList<>();
    ContactType ct = new ContactType();
    ct.setId(0L);
    contacts.add(createContactTypeForPerson(person, ct, person.getPrimaryPhoneNumber()));
    ct = new ContactType();
    ct.setId(1L);
    contacts.add(createContactTypeForPerson(person, ct, "5152810000"));
    return contacts;
  }
}
