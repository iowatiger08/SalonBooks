package org.tigersndragons.salonbooks.core.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tigersndragons.salonbooks.model.Appointment;
import org.tigersndragons.salonbooks.model.Item;
import org.tigersndragons.salonbooks.model.Order;
import org.tigersndragons.salonbooks.model.OrderItem;
import org.tigersndragons.salonbooks.model.Person;
import org.tigersndragons.salonbooks.model.flows.AddOrderItemActions;
import org.tigersndragons.salonbooks.model.flows.OrderFormModel;
import org.tigersndragons.salonbooks.model.type.OrderStatusType;
import org.tigersndragons.salonbooks.service.AddressService;
import org.tigersndragons.salonbooks.service.AppointmentService;
import org.tigersndragons.salonbooks.service.EmployeeService;
import org.tigersndragons.salonbooks.service.ItemService;
import org.tigersndragons.salonbooks.service.OrderService;
import org.tigersndragons.salonbooks.service.PersonService;

@Transactional
@Controller
public class OrderController {
  @Autowired private PersonService personService;
  @Autowired private OrderService orderService;
  @Autowired private ItemService itemService;
  @Autowired private AddressService addressService;
  @Autowired private EmployeeService employeeService;
  @Autowired private AppointmentService appointmentService;

  public void setItemService(ItemService itemService) {
    this.itemService = itemService;
  }

  @RequestMapping(value = "/report", method = RequestMethod.GET)
  public String showOrderReport(Map<String, Object> model) {
    List<Order> orderList = orderService.getOrdersForEmployee(employeeService.getDefaultEmployee());
    List<Person> personList = personService.getListOfActivePersons();
    //    List<Appointment> apptList =
    model.put("orderList", orderList);
    model.put("monthlyOrderTotals", orderService.getMonthlyTotals(orderList));
    model.put("personList", personList);

    return "report";
  }

  @RequestMapping(value = "/person/{phoneNumberEntered}/order/new", method = RequestMethod.GET)
  public String startNewOrder(
      @PathVariable("phoneNumberEntered") String phoneNumberEntered, Model model) {
    Person person = personService.lookupByPhoneNumber(phoneNumberEntered);
    Order order =
        orderService.createOrderForPerson(
            person, appointmentService.createAppointmentForPerson(person));
    orderService.saveOrder(order);
    model.addAttribute("appointment", order.getAppointment());
    model.addAttribute("person", order.getPerson());
    List<Appointment> apptList =
        appointmentService.getAppointmentsForPerson(
            order.getPerson(), employeeService.getDefaultEmployee());
    model.addAttribute("appointmentList", apptList);
    return "redirect:/person/" + phoneNumberEntered + "/order/" + order.getId();
  }

  @RequestMapping(
      value = "/person/{phoneNumberEntered}/order/{orderId}",
      method = RequestMethod.GET)
  public String showOrder(
      @PathVariable("phoneNumberEntered") String phoneNumberEntered,
      @PathVariable("orderId") Long orderId,
      Map<String, Object> model) {
    Order order = orderService.getOrderById(orderId);
    OrderFormModel orderFlowActions2 = new OrderFormModel();
    orderFlowActions2.setAppointmentId(order.getAppointment().getId());
    orderFlowActions2.setPerson(order.getPerson());
    orderFlowActions2.convertEntityDateToModel(order.getCreateDate());
    orderFlowActions2.setItemList(itemService.getListOfItems());
    if (CollectionUtils.isNotEmpty(order.getOrderItems())) {
      OrderItem[] arr = order.getOrderItems().toArray(new OrderItem[order.getOrderItems().size()]);
      orderFlowActions2.setOrderItems(arr);
    } else {
      orderFlowActions2.setOrderItems(new OrderItem[0]);
    }
    OrderItem anOrderItem = new OrderItem();
    anOrderItem.setOrder(order);
    orderFlowActions2.setAnOrderItem(anOrderItem);
    orderFlowActions2.setOrder(order);
    orderFlowActions2.setSaveAndClose(order.getStatus().toString());
    AddOrderItemActions addOrderItemActions = new AddOrderItemActions();
    addOrderItemActions.setOrder(order);
    model.put("OrderStatusType", OrderStatusType.values());
    model.put("addOrderItemActions", addOrderItemActions);
    model.put("itemList", itemService.getListOfItems());
    model.put("orderFlowActions", orderFlowActions2);
    model.put("order", order);
    model.put("orderStatusClosed", order.getStatus() == OrderStatusType.CLOSED);
    model.put("person", order.getPerson());
    model.put("address", addressService.getBillingAddressForPerson(order.getPerson()));
    model.put("appointment", order.getAppointment());
    return "order";
  }

  @RequestMapping(
      value = "/person/{phoneNumberEntered}/order/{orderId}",
      method = RequestMethod.POST,
      params = "saveAndClose")
  public String updateOrder(
      @ModelAttribute("orderFlowActions") OrderFormModel orderFlowActions,
      @PathVariable String orderId,
      @PathVariable String phoneNumberEntered,
      Model model) {
    if (orderFlowActions.getSaveAndClose().equals("CLOSED")) {
      Order order = new Order();
      order.setCreateDate(orderFlowActions.getCreateDate());
      order.setId(Long.valueOf(orderId));
      orderService.closeOrder(order);
    } else {
      Order order = orderFlowActions.convertModelToOrder();
      order.setUpdateDate(LocalDateTime.now());
      orderService.saveOrder(order);
    }
    return "redirect:/person/" + phoneNumberEntered + "/order/" + orderId;
  }

  @RequestMapping(
      value = "/person/{phoneNumberEntered}/order/{orderId}",
      method = RequestMethod.POST,
      params = "addItemtoOrder")
  public String addItemToOrder(
      @ModelAttribute("addOrderItemActions") AddOrderItemActions addOrderItemActions,
      @PathVariable String orderId,
      @PathVariable String phoneNumberEntered,
      Model model) {
    OrderItem oi = new OrderItem();
    oi.setOrder(orderService.getOrderById(Long.valueOf(orderId)));
    Item it = itemService.getItemById(addOrderItemActions.getItemSelect()); // new Item();
    oi.setItem(it);
    oi.setQuantity(addOrderItemActions.getQuantity());
    oi.setCreateDate(LocalDateTime.now());
    oi.setUpdateDate(LocalDateTime.now());
    orderService.addItemToOrder(oi, oi.getOrder(), oi.getItem());

    return "redirect:/person/" + phoneNumberEntered + "/order/" + orderId;
  }

  // addItemToOrder(Item, Order)
  @Transactional
  private void save(Order order) {
    //    personService.save(p.getPerson());
    //    orderService.saveAddress(p.getAddress());
  }

  public void setPersonService(PersonService personService) {
    this.personService = personService;
  }

  public void setOrderService(OrderService orderService) {
    this.orderService = orderService;
  }
}
