package org.tigersndragons.salonbooks.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tigersndragons.salonbooks.ServiceUtils;
import org.tigersndragons.salonbooks.model.Appointment;
import org.tigersndragons.salonbooks.model.Employee;
import org.tigersndragons.salonbooks.model.Person;
import org.tigersndragons.salonbooks.model.type.AppointmentStatusType;
import org.tigersndragons.salonbooks.repository.AppointmentRepository;
import org.tigersndragons.salonbooks.service.AppointmentService;
import org.tigersndragons.salonbooks.service.EmployeeService;

@Service
@Transactional
public class AppointmentServiceImpl extends BaseServiceImpl implements AppointmentService {

    private static final long serialVersionUID = 1L;

    @Autowired private AppointmentRepository appointmentRepository;
    @Autowired private EmployeeService employeeService;

    public List<Appointment> getOpenAppointments() {
        return appointmentRepository.findByAppointmentStatusTypeOrderByAppointmentDateDesc(AppointmentStatusType.OPEN);
    }

    public List<Appointment> getOpenAppointmentsForEmployee(Employee emp) {
        return appointmentRepository.findByEmployeeOrderByAppointmentDateDescAppointmentStatusTypeDesc(emp);
    }

    public Appointment createAppointmentForPerson(Person person) {
        Appointment newOne = defaultAppointment();
        newOne.setPerson(person);
        return appointmentRepository.save(newOne);
    }

    public void closeAppointment(Appointment appt) {
        appt = getAppointmentById(appt.getId());
        appt.setAppointmentStatusType(AppointmentStatusType.CLOSED);
        appointmentRepository.save(appt);
    }

    private Appointment defaultAppointment() {
        Appointment newOne = new Appointment();
        newOne.setAppointmentDate(LocalDateTime.now());
        newOne.setEmployee(defaultEmployee());
        newOne.setPerson(new Person());
        newOne.setAppointmentStatusType(AppointmentStatusType.OPEN);
        newOne.setNotes("No other notes set for this appointment");
        return newOne;
    }

    public Appointment assignAppointmenttoPerson(Appointment appt, Person person) {
        appt.setPerson(person);
        return appt;
    }

    private Employee defaultEmployee() {
        return employeeService.getDefaultEmployee();
    }

    public void startAppointment(Appointment appt) {
        appt.setAppointmentStatusType(AppointmentStatusType.WORKING);
    }

    public List<Appointment> getAppointmentsForPerson(Person p, Employee emp) {
        ServiceUtils.assertNotNull("Person cannot be null", p);
        ServiceUtils.assertNotNull("employee cannot be null", emp);
        return appointmentRepository.findByPersonAndEmployeeOrderByAppointmentDateDesc(p, emp);
    }

    public Appointment getAppointmentById(long id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    public List<Appointment> getAppointmentsByPerson(Person p, Employee emp) {
        return appointmentRepository.findByPersonAndEmployeeOrderByAppointmentDateDesc(p, emp);
    }

    public void save(Appointment theAppointment) {
        appointmentRepository.save(theAppointment);
    }
}
