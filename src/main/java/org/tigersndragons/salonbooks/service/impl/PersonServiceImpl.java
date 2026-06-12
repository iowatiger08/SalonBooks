package org.tigersndragons.salonbooks.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tigersndragons.salonbooks.ServiceUtils;
import org.tigersndragons.salonbooks.model.Address;
import org.tigersndragons.salonbooks.model.Appointment;
import org.tigersndragons.salonbooks.model.Order;
import org.tigersndragons.salonbooks.model.Person;
import org.tigersndragons.salonbooks.model.PersonProfile;
import org.tigersndragons.salonbooks.model.type.GenderType;
import org.tigersndragons.salonbooks.repository.PersonRepository;
import org.tigersndragons.salonbooks.service.AddressService;
import org.tigersndragons.salonbooks.service.AppointmentService;
import org.tigersndragons.salonbooks.service.EmployeeService;
import org.tigersndragons.salonbooks.service.OrderService;
import org.tigersndragons.salonbooks.service.PersonService;

@Service
@Transactional
public class PersonServiceImpl extends BaseServiceImpl implements PersonService {

  private static final long serialVersionUID = 1L;

  @Autowired private PersonRepository personRepository;
  @Autowired private AddressService addressService;
  @Autowired private AppointmentService appointmentService;
  @Autowired private EmployeeService employeeService;
  @Autowired private OrderService orderService;

  public Person lookupByPhoneNumber(String phoneNumber) {
    ServiceUtils.assertNotNull("PHONE CANNOT BE NULL", phoneNumber);
    return personRepository.findByPrimaryPhoneNumber(phoneNumber).orElse(null);
  }

  public Person lookupByLastName(String lastName) {
    ServiceUtils.assertNotNull("Name cannot be null", lastName);
    return personRepository.findFirstByLastName(lastName).orElse(null);
  }

  public List<Person> getListOfActivePersons() {
    return personRepository.findAll();
  }

  public Person getPersonById(Long id) {
    return personRepository.findById(id).orElse(null);
  }

  public Person createPerson(String phoneNumber) {
    Person p = new Person();
    p.setBirthDate(LocalDate.now());
    p.setLastName("CHANGE");
    p.setFirstName("CHANGE");
    p.setPrimaryPhoneNumber(phoneNumber);
    p.setGender(GenderType.U);
    return p;
  }

  public Person getDefaultPerson() {
    Person person = new Person();
    person.setId(0L);
    return person;
  }

  public Person createPerson() {
    return createPerson(null);
  }

  public void save(Person person) {
    ServiceUtils.assertNotNull("Person cannot be null", person);
    ServiceUtils.assertNotNull("PrimaryPhoneNumber cannot be null", person.getPrimaryPhoneNumber());
    personRepository.save(person);
  }

  public PersonProfile getPersonProfile(Person person) {
    PersonProfile profile = new PersonProfile();
    profile.setPerson(person);
    profile.setAddresses(addressService.getAddressByPerson(person));
    profile.setAppointments(
        appointmentService.getAppointmentsForPerson(person, employeeService.getDefaultEmployee()));
    profile.setOrders(orderService.getOrdersForPerson(person));
    return profile;
  }

  public PersonProfile createPersonProfile() {
    return createPersonProfile(new Person());
  }

  public PersonProfile createPersonProfile(Person person) {
    PersonProfile profile = new PersonProfile();
    profile.setPerson(person);
    List<Address> addresses = new ArrayList<>();
    addresses.add(addressService.createDefaultAddress());
    profile.setAddresses(addresses);
    return profile;
  }

  public PersonProfile updatePersonProfile(PersonProfile profile) {
    save(profile.getPerson());
    for (Address addy : profile.getAddresses()) {
      addressService.saveAddress(addy);
    }
    for (Appointment appt : profile.getAppointments()) {
      appointmentService.save(appt);
    }
    for (Order order : profile.getOrders()) {
      orderService.saveOrder(order);
    }
    return profile;
  }
}
