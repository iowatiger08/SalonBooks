package org.tigersndragons.salonbooks.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.tigersndragons.salonbooks.model.Appointment;
import org.tigersndragons.salonbooks.model.Employee;
@Service
public interface HomeService {
	public List<Appointment> findOpenAppointments();
	public List<Appointment> findOpenAppointmentsForEmployee(Employee emmp);
}
