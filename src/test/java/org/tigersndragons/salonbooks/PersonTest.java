package org.tigersndragons.salonbooks;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tigersndragons.salonbooks.exception.PersonNotFoundException;
import org.tigersndragons.salonbooks.exception.ValidationException;
import org.tigersndragons.salonbooks.model.Person;
import org.tigersndragons.salonbooks.model.flows.PersonFormModel;
import org.tigersndragons.salonbooks.model.type.GenderType;
import org.tigersndragons.salonbooks.service.PersonService;

public class PersonTest extends BaseTestCase {

    @Autowired
    PersonService personService;

    @Autowired
    PersonFormModel personFlowActions;

    private Person e1, e2;

    @BeforeEach
    public void setUp() {
        e1 = new Person();
        e2 = new Person();
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
    public void testMatchingFirstName() {
        e1.setId(0L);
        e1.setFirstName("auser");
        e2.setId(0L);
        e2.setFirstName("auser");
        assertTrue(e1.equals(e2));
        assertTrue(e1.getFirstName().equals(e2.getFirstName()));
        e2.setId(1L);
        assertFalse(e1.equals(e2));
        assertTrue(e1.getFirstName().equals(e2.getFirstName()));
    }

    private Person theDefaultPerson() {
        Person emp = new Person();
        emp.setId(0L);
        emp.setFirstName("TEST");
        emp.setLastName("TESTER");
        emp.setBirthDate(LocalDate.parse("1980-01-01"));
        emp.setGender(GenderType.U);
        emp.setPrimaryPhoneNumber("5152201111");
        return emp;
    }

    @Test
    public void retrieveDefaultPerson() {
        Person emp = personService.getDefaultPerson();
        assertTrue(emp.equals(theDefaultPerson()));
    }

    @Test
    public void retrieveListOfPerson() {
        List<Person> personList = personService.getListOfActivePersons();
        assertTrue(CollectionUtils.isNotEmpty(personList) && personList.size() > 0);
        assertTrue(personList.get(0).equals(this.theDefaultPerson()));
    }

    @Test
    public void retrieveDefaultPersonById() {
        Person emp = personService.getPersonById(0L);
        Person e2 = theDefaultPerson();
        assertTrue(emp.equals(e2));
        assertTrue(StringUtils.equals(emp.getFirstName(), e2.getFirstName()));
        assertTrue(StringUtils.equals(emp.getPrimaryPhoneNumber(), e2.getPrimaryPhoneNumber()));
    }

    @Test
    public void testlookupByLastName() {
        Person tester = personService.lookupByLastName("TESTER");
        Person dTested = theDefaultPerson();
        assertTrue(tester.equals(dTested));
    }

    @Test
    public void testlookupByPhone() {
        Person tester = personService.lookupByPhoneNumber("5152201111");
        Person dTested = theDefaultPerson();
        assertTrue(tester.equals(dTested));
    }

    @Test
    public void testSaveDefaultPerson() {
        Person p = theDefaultPerson();
        p.setId(null);
        p.setPrimaryPhoneNumber("5152202222");
        personService.save(p);
        assertNotNull(p.getId());
    }

    @Test
    public void testCreatePersonService() {
        assertNotNull(personService.createPerson());
        assertNotNull(personService.createPerson(""));
        assertNotNull(personService.createPerson("478"));
        assertNotNull(personService.createPerson("1234567890"));
        assertNotNull(personService.createPerson("123-456-7890"));
        assertNotNull(personService.createPerson("!234567890"));
        assertNotNull(personService.createPerson("I234567890"));
    }

    @Test
    public void testCreatePersonFlow() throws PersonNotFoundException, ValidationException {
        try {
            personFlowActions.lookupCustomer("1234567890");
        } catch (PersonNotFoundException e) { /* expected */ }

        Person p32 = personFlowActions.lookupCustomer("515-220-1111");
        assertNotNull(p32);

        Person p4 = personFlowActions.lookupCustomer("!5152201111");
        assertNotNull(p4);

        Person defaultOne = personFlowActions.lookupCustomer("5152201111");
        assertNotNull(defaultOne);

        assertThrows(ValidationException.class, () ->
            personFlowActions.lookupCustomer("I515220110")
        );
    }
}
