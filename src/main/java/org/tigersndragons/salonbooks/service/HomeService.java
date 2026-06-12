package org.tigersndragons.salonbooks.service;

import java.util.List;
import org.tigersndragons.salonbooks.model.Appointment;
import org.tigersndragons.salonbooks.model.Employee;

public interface HomeService {
  List<Appointment> findOpenAppointments();

  List<Appointment> findOpenAppointmentsForEmployee(Employee emmp);
}
