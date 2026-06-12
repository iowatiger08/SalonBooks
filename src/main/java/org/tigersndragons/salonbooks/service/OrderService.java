package org.tigersndragons.salonbooks.service;

import java.util.List;
import org.tigersndragons.salonbooks.model.Appointment;
import org.tigersndragons.salonbooks.model.Employee;
import org.tigersndragons.salonbooks.model.Item;
import org.tigersndragons.salonbooks.model.MonthlyTotals;
import org.tigersndragons.salonbooks.model.Order;
import org.tigersndragons.salonbooks.model.OrderItem;
import org.tigersndragons.salonbooks.model.PaymentMethod;
import org.tigersndragons.salonbooks.model.Person;

public interface OrderService {
  List<Order> getListOfActiveOrders();

  Order getOrderById(Long id);

  Order saveOrder(Order order);

  Order startOrder(Order order);

  Order closeOrder(Order order);

  Order createOrderForPerson(Person person, Appointment appointment);

  List<Order> getOrdersForPerson(Person person);

  List<Order> getOrdersForEmployee(Employee emp);

  //  public void addItemToOrder (OrderItem orderItem);

  Order removeOrderItemFromOrder(Order order, OrderItem orderItem);

  Order addItemToOrder(OrderItem orderItem, Order order, Item item);

  Order updateOrderItem(Order order, OrderItem orderItem);

  List<PaymentMethod> getPaymentMethods();

  List<MonthlyTotals> getMonthlyTotals(List<Order> orderList);
}
