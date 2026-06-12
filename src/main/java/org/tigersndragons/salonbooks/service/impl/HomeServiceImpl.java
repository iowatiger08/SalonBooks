package org.tigersndragons.salonbooks.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tigersndragons.salonbooks.model.Appointment;
import org.tigersndragons.salonbooks.model.Employee;
import org.tigersndragons.salonbooks.service.AppointmentService;
import org.tigersndragons.salonbooks.service.HomeService;
import org.tigersndragons.salonbooks.service.OrderService;
import org.tigersndragons.salonbooks.service.PersonService;
import org.tigersndragons.salonbooks.service.ShippingMethodService;

@Service
public class HomeServiceImpl extends BaseServiceImpl implements HomeService {

  private static final long serialVersionUID = 1L;

  @Autowired private AppointmentService appointmentService;
  @Autowired private PersonService personService;
  @Autowired private OrderService orderService;
  @Autowired private ShippingMethodService shipperService;

  public List<Appointment> findOpenAppointments() {
    return appointmentService.getOpenAppointments();
  }

  public List<Appointment> findOpenAppointmentsForEmployee(Employee emp) {
    return appointmentService.getOpenAppointmentsForEmployee(emp);
  }
}
