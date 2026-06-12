package org.tigersndragons.salonbooks;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tigersndragons.salonbooks.model.Appointment;
import org.tigersndragons.salonbooks.model.type.AppointmentStatusType;
import org.tigersndragons.salonbooks.service.AppointmentService;
import org.tigersndragons.salonbooks.service.EmployeeService;
import org.tigersndragons.salonbooks.service.PersonService;

public class AppointmentTest extends BaseTestCase {

  @Autowired AppointmentService appointmentService;
  @Autowired EmployeeService employeeService;
  @Autowired PersonService personService;

  private Appointment e1;
  private Appointment e2;

  @BeforeEach
  public void setUp() {
    e1 = new Appointment();
    e2 = new Appointment();
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
    e1.setNotes("auser");
    e2.setId(0L);
    e2.setNotes("auser");
    assertTrue(e1.equals(e2));
    assertTrue(e1.getNotes().equals(e2.getNotes()));
    e2.setId(1L);
    assertFalse(e1.equals(e2));
    assertTrue(e1.getNotes().equals(e2.getNotes()));
  }

  private Appointment theDefaultAppointment() {
    Appointment emp = new Appointment();
    emp.setId(0L);
    emp.setNotes("TEST DEFAULT");
    emp.setAppointmentStatusType(AppointmentStatusType.OPEN);
    emp.setAppointmentDate(LocalDateTime.now());
    emp.setEmployee(employeeService.getDefaultEmployee());
    emp.setPerson(personService.getDefaultPerson());
    return emp;
  }

  @Test
  public void retrieveListOfAppointment2() {
    List<Appointment> apptList = appointmentService.getOpenAppointments();
    assertTrue(CollectionUtils.isNotEmpty(apptList) && apptList.size() > 0);
    assertTrue(
        apptList
            .get(apptList.size() - 1)
            .getNotes()
            .equals(this.theDefaultAppointment().getNotes()));
  }

  @Test
  public void retrieveListOfAppointment() {
    List<Appointment> apptList =
        appointmentService.getOpenAppointmentsForEmployee(employeeService.getDefaultEmployee());
    assertTrue(CollectionUtils.isNotEmpty(apptList) && apptList.size() > 0);
    assertTrue(apptList.get(apptList.size() - 1).equals(this.theDefaultAppointment()));
  }

  @Test
  public void retrieveAppointmentById() {
    Appointment emp = appointmentService.getAppointmentById(0L);
    Appointment e2 = theDefaultAppointment();
    assertTrue(emp.equals(e2));
    assertTrue(StringUtils.equals(emp.getNotes(), e2.getNotes()));
  }

  @Test
  public void testgetAppointmentsByPerson() {
    List<Appointment> alist =
        appointmentService.getAppointmentsByPerson(
            personService.getDefaultPerson(), employeeService.getDefaultEmployee());
    assertTrue(CollectionUtils.isNotEmpty(alist));
  }

  @Test
  public void testSaveDefaultAppointment() {
    appointmentService.save(theDefaultAppointment());
  }
}
