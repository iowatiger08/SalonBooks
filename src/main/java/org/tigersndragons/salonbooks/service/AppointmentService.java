package org.tigersndragons.salonbooks.service;

import java.util.List;

import org.tigersndragons.salonbooks.model.Appointment;
import org.tigersndragons.salonbooks.model.Employee;
import org.tigersndragons.salonbooks.model.Person;

public interface AppointmentService {

	public List<Appointment> getOpenAppointments();

	public List<Appointment> getOpenAppointmentsForEmployee(Employee emp);
	
	public Appointment createAppointmentForPerson(Person person);
	
	public List<Appointment> getAppointmentsForPerson(Person p, Employee emp);
	
//	public Appointment createAppointment();
	
	public void startAppointment(Appointment anAppointment);
	
	public Appointment assignAppointmenttoPerson(Appointment appt, Person person);
	
	public void closeAppointment(Appointment appt);

	public Appointment getAppointmentById(long l);

	public List<Appointment>  getAppointmentsByPerson(Person defaultPerson, Employee emp);

	public void save(Appointment theAppointment);

}
