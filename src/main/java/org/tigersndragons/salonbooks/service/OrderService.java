package org.tigersndragons.salonbooks.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.tigersndragons.salonbooks.model.Appointment;
import org.tigersndragons.salonbooks.model.Employee;
import org.tigersndragons.salonbooks.model.Item;
import org.tigersndragons.salonbooks.model.MonthlyTotals;
import org.tigersndragons.salonbooks.model.Order;
import org.tigersndragons.salonbooks.model.OrderItem;
import org.tigersndragons.salonbooks.model.PaymentMethod;
import org.tigersndragons.salonbooks.model.Person;
@Service
public interface OrderService {
	public List<Order> getListOfActiveOrders();
	
	public Order getOrderById(Long id);

	public Order saveOrder(Order order);
	
	public Order startOrder(Order order);
	
	public Order closeOrder(Order order);
	public Order createOrderForPerson(Person person, Appointment appointment);
	
	public List<Order> getOrdersForPerson(Person person);
	
	public List<Order> getOrdersForEmployee(Employee emp);
	
//	public void addItemToOrder (OrderItem orderItem);
	
	public Order removeOrderItemFromOrder(Order order, OrderItem orderItem);
	public Order addItemToOrder(OrderItem orderItem, Order order ,Item item) ;
	public Order updateOrderItem(Order order , OrderItem orderItem);
	
	public List<PaymentMethod> getPaymentMethods();

	public List<MonthlyTotals> getMonthlyTotals(List<Order> orderList);
}
