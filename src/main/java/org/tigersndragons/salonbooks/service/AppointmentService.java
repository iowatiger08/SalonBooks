package org.tigersndragons.salonbooks.service;

import java.util.List;
import org.tigersndragons.salonbooks.model.Appointment;
import org.tigersndragons.salonbooks.model.Employee;
import org.tigersndragons.salonbooks.model.Person;

public interface AppointmentService {

  List<Appointment> getOpenAppointments();

  List<Appointment> getOpenAppointmentsForEmployee(Employee emp);

  Appointment createAppointmentForPerson(Person person);

  List<Appointment> getAppointmentsForPerson(Person p, Employee emp);

  //  public Appointment createAppointment();

  void startAppointment(Appointment anAppointment);

  Appointment assignAppointmenttoPerson(Appointment appt, Person person);

  void closeAppointment(Appointment appt);

  Appointment getAppointmentById(long l);

  List<Appointment> getAppointmentsByPerson(Person defaultPerson, Employee emp);

  void save(Appointment theAppointment);
}
