package org.tigersndragons.salonbooks;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tigersndragons.salonbooks.model.Contact;
import org.tigersndragons.salonbooks.model.Person;
import org.tigersndragons.salonbooks.model.type.ContactType;
import org.tigersndragons.salonbooks.service.ContactService;

public class ContactTest extends BaseTestCase {

    @Autowired
    ContactService contactService;

    private Contact e1, e2;

    @BeforeEach
    public void setUp() {
        e1 = new Contact();
        e2 = new Contact();
    }

    @Test
    public void testMatchId() {
        e1.setId(0L);
        e2.setId(0L);
        assertTrue(e1.equals(e2));
        e2.setId(1L);
        assertFalse(e1.equals(e2));
    }

    @Test
    public void testMatchingNotes() {
        e1.setId(0L);
        e1.setLabel("auser");
        e2.setId(0L);
        e2.setLabel("auser");
        assertTrue(e1.equals(e2));
        assertTrue(e1.getLabel().equals(e2.getLabel()));
        e2.setId(1L);
        assertFalse(e1.equals(e2));
        assertTrue(e1.getLabel().equals(e2.getLabel()));
    }

    private Contact theDefaultContact() {
        Contact emp = new Contact();
        emp.setId(0L);
        emp.setLabel("3196210000");
        ContactType ct = new ContactType();
        ct.setId(0L);
        ct.setName("MOBILE_PHONE");
        emp.setContactType(ct);
        emp.setPerson(getDefaultPerson());
        return emp;
    }

    @Test
    public void retrieveDefaultContact() {
        Contact emp = contactService.createContactTypeForPerson(getDefaultPerson(), null);
        assertTrue(emp.getLabel().equals(theDefaultContact().getLabel()));
    }

    @Test
    public void testContactFactory() {
        ContactType ct = new ContactType();
        ct.setId(0L);
        Contact emp = contactService.createContactTypeForPerson(getDefaultPerson(), ct, "3196216807");
        assertTrue(emp.getLabel().equals("3196216807"));
        ct.setId(1L);
        Contact emp1 = contactService.createContactTypeForPerson(getDefaultPerson(), ct, "5152810000");
        assertTrue(emp1.getContactType().getName().equals("HOME_PHONE"));
    }

    @Disabled
    @Test
    public void createDefaultContacts() {
        Person person = new Person();
        person.setId(101L);
        person.setPrimaryPhoneNumber("8162223333");
        List<Contact> contacts = contactService.createDefaultContacts(person);
        assertTrue(contacts.size() == 2);
    }

    @Test
    public void retrieveListOfContact() {
        List<Contact> apptList = contactService.getContactsByPerson(getDefaultPerson());
        assertTrue(CollectionUtils.isNotEmpty(apptList) && apptList.size() > 0);
        assertTrue(apptList.get(0).equals(this.theDefaultContact()));
    }

    @Test
    public void retrieveContactById() {
        Contact emp = contactService.getContactbyId(0L);
        Contact e2 = theDefaultContact();
        assertTrue(emp.equals(e2));
        assertTrue(StringUtils.equals(emp.getLabel(), e2.getLabel()));
    }

    @Test
    public void testSaveDefaultContact() {
        Contact emp = theDefaultContact();
        emp.setId(1L);
        contactService.saveContact(emp);
    }
}
