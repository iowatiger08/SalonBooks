package org.tigersndragons.salonbooks.core.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tigersndragons.salonbooks.model.Appointment;
import org.tigersndragons.salonbooks.model.Employee;
import org.tigersndragons.salonbooks.model.Order;
import org.tigersndragons.salonbooks.model.Person;
import org.tigersndragons.salonbooks.model.flows.AppointmentFormModel;
import org.tigersndragons.salonbooks.model.type.AppointmentStatusType;
import org.tigersndragons.salonbooks.service.AppointmentService;
import org.tigersndragons.salonbooks.service.EmployeeService;
import org.tigersndragons.salonbooks.service.OrderService;
import org.tigersndragons.salonbooks.service.PersonService;

@Controller
public class AppointmentController {
  @Autowired private PersonService personService;
  @Autowired AppointmentFormModel appointmentFlowActions;
  @Autowired private AppointmentService appointmentService;
  @Autowired private OrderService orderService;

  @Autowired private EmployeeService employeeService;

  final List<AppointmentStatusType> statusTypeList =
      new ArrayList<AppointmentStatusType>(Arrays.asList(AppointmentStatusType.values()));

  @RequestMapping(
      value = "/person/{phoneNumberEntered}/appointment/{appointmentId}",
      method = RequestMethod.GET)
  public String showAppointment(
      @PathVariable("phoneNumberEntered") String phoneNumberEntered,
      @PathVariable("appointmentId") Long appointmentId,
      Map<String, Object> model) {
    Appointment appt = appointmentService.getAppointmentById(appointmentId);

    AppointmentFormModel appointmentFlowActions2 = appointmentToFlowActions(appt);
    model.put("appointmentFlowActions", appointmentFlowActions2);
    model.put("appt", appt);
    model.put("statusTypeList", statusTypeList);
    model.put("person", appt.getPerson());
    model.put("MONTHS", appointmentFlowActions2.MONTHS);
    model.put("DATES", appointmentFlowActions2.DATES);
    model.put("YEARS", appointmentFlowActions2.YEARS);
    model.put("MINUTES", appointmentFlowActions2.MINUTES);
    model.put("HOURS", appointmentFlowActions2.HOURS);
    model.put(
        "appointmentList",
        appointmentService.getAppointmentsForPerson(
            appt.getPerson(), employeeService.getDefaultEmployee()));
    return "appointment";
  }

  @RequestMapping(value = "/appointment/{appointmentId}", method = RequestMethod.GET)
  public String showAppointmentWithId(
      @PathVariable("appointmentId") Long appointmentId, Map<String, Object> model) {
    Appointment appt = appointmentService.getAppointmentById(appointmentId);
    AppointmentFormModel appointmentFlowActions2 = appointmentToFlowActions(appt);
    model.put("appointmentFlowActions", appointmentFlowActions2);
    model.put("appt", appt);
    model.put("statusTypeList", statusTypeList);
    model.put("person", appt.getPerson());
    model.put("MONTHS", appointmentFlowActions2.MONTHS);
    model.put("DATES", appointmentFlowActions2.DATES);
    model.put("YEARS", appointmentFlowActions2.YEARS);
    model.put("MINUTES", appointmentFlowActions2.MINUTES);
    model.put("HOURS", appointmentFlowActions2.HOURS);
    model.put(
        "appointmentList",
        appointmentService.getAppointmentsForPerson(
            appt.getPerson(), employeeService.getDefaultEmployee()));
    return "appointment";
  }

  private AppointmentFormModel appointmentToFlowActions(Appointment appt) {
    AppointmentFormModel appointmentFlowActions2 = new AppointmentFormModel();
    appointmentFlowActions2.setPersonId(appt.getPerson().getId());
    appointmentFlowActions2.setAppointmentId(appt.getId());
    appointmentFlowActions2.setAppointmentStatusType(appt.getAppointmentStatusType());
    appointmentFlowActions2.setNotes(appt.getNotes());
    appointmentFlowActions2.setAppointmentDate(appt.getAppointmentDate());
    appointmentFlowActions2.convertEntityDateToModel(appt.getAppointmentDate());
    appointmentFlowActions2.setCreateDate(
        appt.getCreateDate() == null ? appt.getUpdateDate() : appt.getCreateDate());
    return appointmentFlowActions2;
  }

  @RequestMapping(
      value = "/person/{phoneNumberEntered}/appointment/{appointmentId1}",
      method = RequestMethod.POST)
  public String updateAppointment(
      @ModelAttribute("appointmentFlowActions") AppointmentFormModel appointmentFlowActions2,
      @PathVariable String phoneNumberEntered,
      @PathVariable String appointmentId1,
      Model model) {
    if (appointmentFlowActions2.isAddOrdertoAppointment()) {
      return addOrdertoAppointment(
          appointmentFlowActions2, phoneNumberEntered, appointmentId1, model);
    }
    save(convertFlowActionsToAppointment(appointmentFlowActions2));
    return "redirect:/person/" + phoneNumberEntered + "/appointment/" + appointmentId1;
  }

  @RequestMapping(value = "/appointment/{appointmentId1}", method = RequestMethod.POST)
  public String updateAppointment(
      @ModelAttribute("appointmentFlowActions") AppointmentFormModel appointmentFlowActions2,
      // @PathVariable String phoneNumberEntered,
      @PathVariable String appointmentId1,
      Model model) {

    Appointment appt = convertFlowActionsToAppointment(appointmentFlowActions2);
    String phoneNumberEntered = appt.getPerson().getPrimaryPhoneNumber();
    if (phoneNumberEntered == null) {
      phoneNumberEntered =
          personService.getPersonById(appt.getPerson().getId()).getPrimaryPhoneNumber();
    }
    if (appointmentFlowActions2.isAddOrdertoAppointment()) {
      return addOrdertoAppointment(
          appointmentFlowActions2, phoneNumberEntered, appointmentId1, model);
    }
    save(appt);
    return "redirect:/person/" + phoneNumberEntered + "/appointment/" + appointmentId1;
  }

  public String addOrdertoAppointment(
      AppointmentFormModel appointmentFlowActions2,
      String phoneNumberEntered,
      String appointmentId1,
      Model model) {
    Order order =
        createOrderForAppointment(convertFlowActionsToAppointmentOrder(appointmentFlowActions2));
    return "redirect:/person/" + phoneNumberEntered + "/order/" + order.getId();
  }

  private Order createOrderForAppointment(Appointment appt) {
    return orderService.createOrderForPerson(appt.getPerson(), appt);
  }

  private Appointment convertFlowActionsToAppointmentOrder(
      AppointmentFormModel appointmentFlowActions2) {
    Appointment appt = convertFlowActionsToAppointment(appointmentFlowActions2);

    appt.setAppointmentStatusType(AppointmentStatusType.PENDING_PAYMENT);
    save(appt);
    return appt;
  }

  private Appointment convertFlowActionsToAppointment(
      AppointmentFormModel appointmentFlowActions2) {
    Appointment appt = new Appointment();
    appt.setId(appointmentFlowActions2.getAppointmentId());
    Person person = new Person();
    person.setId(appointmentFlowActions2.getPersonId());
    appt.setPerson(person);
    appt.setNotes(appointmentFlowActions2.getNotes());
    Employee employee = new Employee();
    employee.setId(0L);
    appt.setAppointmentDate(appointmentFlowActions2.getAppointmentDate());
    appt.setAppointmentDate(appointmentFlowActions2.convertModelToDateTime());
    appt.setAppointmentStatusType(appointmentFlowActions2.getAppointmentStatusType());
    appt.setCreateDate(appointmentFlowActions2.getCreateDate());
    appt.setUpdateDate(LocalDateTime.now());
    appt.setEmployee(employee);
    return appt;
  }

  @RequestMapping(
      value = "/person/{phoneNumberEntered}/appointment/new",
      method = RequestMethod.GET)
  public String startAppointment(
      @PathVariable("phoneNumberEntered") String phoneNumberEntered, Model model) {
    Appointment appt =
        appointmentService.createAppointmentForPerson(
            personService.lookupByPhoneNumber(phoneNumberEntered));
    appointmentService.save(appt);
    model.addAttribute("appointment", appt);
    model.addAttribute("person", appt.getPerson());
    List<Appointment> apptList =
        appointmentService.getAppointmentsForPerson(
            appt.getPerson(), employeeService.getDefaultEmployee());
    model.addAttribute("appointmentList", apptList);
    return "redirect:/person/" + phoneNumberEntered + "/appointment/" + appt.getId();
  }

  @Transactional
  private void save(Appointment appt) {
    appointmentService.save(appt);
  }

  public void setPersonService(PersonService personService) {
    this.personService = personService;
  }

  public void setAppointmentService(AppointmentService appointmentService) {
    this.appointmentService = appointmentService;
  }

  public void setAppointmentFlowActions(AppointmentFormModel appointmentFlowActions) {
    this.appointmentFlowActions = appointmentFlowActions;
  }

  public void setOrderService(OrderService orderService) {
    this.orderService = orderService;
  }
}
