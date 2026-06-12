package org.tigersndragons.salonbooks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tigersndragons.salonbooks.model.Appointment;
import org.tigersndragons.salonbooks.model.Item;
import org.tigersndragons.salonbooks.model.Order;
import org.tigersndragons.salonbooks.model.OrderItem;
import org.tigersndragons.salonbooks.model.type.OrderStatusType;
import org.tigersndragons.salonbooks.service.ItemService;
import org.tigersndragons.salonbooks.service.OrderService;
import org.tigersndragons.salonbooks.service.PersonService;

public class OrderTest extends BaseTestCase {

  @Autowired OrderService orderService;
  @Autowired ItemService itemService;

  private PersonService personService;

  private Order e1;
  private Order e2;

  @BeforeEach
  public void setUp() {
    personService = mock(PersonService.class);
    e1 = new Order();
    e2 = new Order();
    when(personService.getPersonById(0L)).thenReturn(getDefaultPerson());
    when(personService.getDefaultPerson()).thenReturn(getDefaultPerson());
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
  public void testMatchingStatus() {
    e1.setId(0L);
    e1.setStatus(OrderStatusType.OPEN);
    e2.setId(0L);
    e2.setStatus(OrderStatusType.OPEN);
    assertTrue(e1.equals(e2));
    assertEquals(e1.getStatus(), e2.getStatus());
    e2.setId(1L);
    assertFalse(e1.equals(e2));
    assertEquals(e1.getStatus(), e2.getStatus());
  }

  private Order testEmptyOrder() {
    Order emp = new Order();
    emp.setId(0L);
    emp.setStatus(OrderStatusType.OPEN);
    emp.setPerson(personService.getDefaultPerson());
    emp.setNumOfItems(0);
    emp.setCurrency("USD");
    emp.setSubTotal(new BigDecimal("0.00"));
    emp.setTax(new BigDecimal("0.00"));
    emp.setTotal(new BigDecimal("0.00"));
    emp.setShippingCost(new BigDecimal("0.00"));
    return emp;
  }

  @Test
  public void getOrderWithItem() {
    Order order = testOrderwithItem();
    assertNotNull(order);
    Item item = itemService.createItem();
    item.setId(0L);
    OrderItem orderItem = new OrderItem();
    orderItem.setOrder(order);
    orderItem.setItem(item);
    orderItem.setQuantity(new BigDecimal("2"));
    orderItem.setNotes("notes2");

    order = orderService.addItemToOrder(orderItem, order, item);
    assertNotNull(order);
    assertEquals(2, order.getNumOfItems());
    assertEquals(new BigDecimal("0.02"), order.getSubTotal());
    assertEquals(1, order.getOrderItems().size());
  }

  public Order testOrderwithItem() {
    Order order = new Order();
    order.setId(0L);
    order.setStatus(OrderStatusType.OPEN);
    order.setPerson(personService.getDefaultPerson());
    order.setNumOfItems(0);
    order.setCurrency("USD");
    order.setSubTotal(new BigDecimal("0.00"));
    order.setTax(new BigDecimal("0.00"));
    order.setTotal(new BigDecimal("0.00"));
    Item item = itemService.createItem();
    item.setId(0L);
    OrderItem orderItem = new OrderItem();
    orderItem.setOrder(order);
    orderItem.setItem(item);
    orderItem.setQuantity(new BigDecimal(1));
    orderItem.setNotes("notes");
    return orderService.addItemToOrder(orderItem, order, item);
  }

  @Test
  public void retrieveListOfOrders() {
    List<Order> orderList = orderService.getListOfActiveOrders();
    assertTrue(CollectionUtils.isNotEmpty(orderList) && orderList.size() > 0);
    assertTrue(orderList.get(orderList.size() - 1).equals(this.testEmptyOrder()));
  }

  @Test
  public void retrieveOrderById() {
    Order emp = orderService.getOrderById(0L);
    Order e2 = testEmptyOrder();
    assertTrue(emp.equals(e2));
  }

  private Appointment testAppointment() {
    Appointment appointment = new Appointment();
    appointment.setId(0L);
    appointment.setPerson(getDefaultPerson());
    return appointment;
  }

  @Test
  public void testStartEmptyOrder() {
    Order emp =
        orderService.createOrderForPerson(personService.getDefaultPerson(), testAppointment());
    assertNotNull(emp);
    assertNotNull(emp.getId());
    emp = orderService.startOrder(emp);
    assertNotNull(emp);
    assertNotNull(emp.getId());
    assertEquals(OrderStatusType.PENDING, emp.getStatus());
  }

  @Test
  public void testCloseEmptyOrder() {
    Order emp =
        orderService.createOrderForPerson(personService.getDefaultPerson(), testAppointment());
    assertNotNull(emp);
    assertNotNull(emp.getId());
    emp = orderService.closeOrder(emp);
    assertNotNull(emp);
    assertNotNull(emp.getId());
    assertEquals(OrderStatusType.CLOSED, emp.getStatus());
  }
}
