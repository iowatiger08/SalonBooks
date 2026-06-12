package org.tigersndragons.salonbooks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.tigersndragons.salonbooks.model.Address;
import org.tigersndragons.salonbooks.service.AddressService;
import org.tigersndragons.salonbooks.service.PersonService;

public class AddressTest extends BaseTestCase {

  @Autowired AddressService addressService;

  private PersonService personService;
  private Address e1;
  private Address e2;

  @BeforeEach
  public void setUp() {
    personService = Mockito.mock(PersonService.class);
    e1 = new Address();
    e2 = new Address();
    Mockito.when(personService.getPersonById(0L)).thenReturn(getDefaultPerson());
    Mockito.when(personService.getDefaultPerson()).thenReturn(getDefaultPerson());
  }

  @Test
  public void testMatchId() {
    e1.setId(0L);
    e2.setId(0L);
    assertEquals(e1, e2);
    e2.setId(1L);
    assertFalse(e1.equals(e2));
  }

  @Test
  public void testMatchingNotes() {
    e1.setId(0L);
    e1.setCity("auser");
    e2.setId(0L);
    e2.setCity("auser");
    assertTrue(e1.equals(e2));
    assertTrue(e1.getCity().equals(e2.getCity()));
    e2.setId(1L);
    assertFalse(e1.equals(e2));
    assertTrue(e1.getCity().equals(e2.getCity()));
  }

  private Address theDefaultAddress() {
    Address emp = new Address();
    emp.setId(0L);
    emp.setLine1("default");
    emp.setCity("Des Moines");
    emp.setState("IA");
    emp.setZip("50315");
    emp.setPerson(getDefaultPerson());
    return emp;
  }

  @Test
  public void retrieveDefaultAddress() {
    Address emp = addressService.createDefaultAddress();
    emp.setId(0L);
    assertTrue(emp.equals(theDefaultAddress()));
  }

  @Test
  public void retrieveListOfAddresses() {
    List<Address> apptList = addressService.getAddressByPerson(personService.getDefaultPerson());
    assertTrue(CollectionUtils.isNotEmpty(apptList) && apptList.size() > 0);
    assertTrue(apptList.get(0).getLine1().equals(this.theDefaultAddress().getLine1()));
  }

  @Test
  public void retrieveAddressById() {
    Address emp = addressService.getAddressbyId(0L);
    Address e2 = theDefaultAddress();
    assertTrue(emp.equals(e2));
    assertTrue(StringUtils.equals(emp.getCity(), e2.getCity()));
  }

  @Test
  public void testSaveDefaultAddress() {
    Address emp = theDefaultAddress();
    emp.setId(0L);
    addressService.saveAddress(emp);
    assertNotNull(emp);
  }
}
