package org.tigersndragons.salonbooks.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.tigersndragons.salonbooks.model.Appointment;
import org.tigersndragons.salonbooks.model.Employee;
import org.tigersndragons.salonbooks.model.Person;
import org.tigersndragons.salonbooks.model.type.AppointmentStatusType;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
  List<Appointment> findByAppointmentStatusTypeOrderByAppointmentDateDesc(
      AppointmentStatusType status);

  List<Appointment> findByEmployeeOrderByAppointmentDateDescAppointmentStatusTypeDesc(
      Employee employee);

  List<Appointment> findByPersonAndEmployeeOrderByAppointmentDateDesc(
      Person person, Employee employee);
}
