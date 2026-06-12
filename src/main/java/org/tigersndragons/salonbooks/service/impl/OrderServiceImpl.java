package org.tigersndragons.salonbooks.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tigersndragons.salonbooks.model.Appointment;
import org.tigersndragons.salonbooks.model.Employee;
import org.tigersndragons.salonbooks.model.Item;
import org.tigersndragons.salonbooks.model.MonthlyTotals;
import org.tigersndragons.salonbooks.model.Order;
import org.tigersndragons.salonbooks.model.OrderItem;
import org.tigersndragons.salonbooks.model.PaymentMethod;
import org.tigersndragons.salonbooks.model.Person;
import org.tigersndragons.salonbooks.model.type.OrderStatusType;
import org.tigersndragons.salonbooks.repository.OrderItemRepository;
import org.tigersndragons.salonbooks.repository.OrderRepository;
import org.tigersndragons.salonbooks.repository.PaymentMethodRepository;
import org.tigersndragons.salonbooks.service.AppointmentService;
import org.tigersndragons.salonbooks.service.EmployeeService;
import org.tigersndragons.salonbooks.service.ItemService;
import org.tigersndragons.salonbooks.service.OrderService;
import org.tigersndragons.salonbooks.service.PaymentService;
import org.tigersndragons.salonbooks.service.ShippingMethodService;

@Service
@Transactional
public class OrderServiceImpl extends BaseServiceImpl implements OrderService {

  private static final long serialVersionUID = 1L;
  private static final BigDecimal TAX_RATE = new BigDecimal("0.06");

  @Autowired private OrderRepository orderRepository;
  @Autowired private OrderItemRepository orderItemRepository;
  @Autowired private PaymentMethodRepository paymentMethodRepository;
  @Autowired private ItemService itemService;
  @Autowired private PaymentService paymentService;
  @Autowired private ShippingMethodService shipperService;
  @Autowired private EmployeeService employeeService;
  @Autowired private AppointmentService appointmentService;

  public List<Order> getListOfActiveOrders() {
    return orderRepository.findAllByOrderByIdDesc();
  }

  public Order getOrderById(Long id) {
    return orderRepository.findById(id).orElse(null);
  }

  public Order createOrderForPerson(Person person, Appointment appointment) {
    Order existing = orderRepository.findFirstByAppointmentOrderByIdDesc(appointment).orElse(null);
    if (existing != null) return existing;

    Order order = new Order();
    order.setPerson(person);
    order.setCurrency("USD");
    order.setNumOfItems(0);
    order.setSubTotal(new BigDecimal("0.00"));
    order.setTax(new BigDecimal("0.00"));
    order.setShipper(shipperService.getDefaultShipper());
    if (appointment == null) {
      order.setAppointment(appointmentService.createAppointmentForPerson(person));
    } else {
      Appointment appt = new Appointment();
      appt.setId(appointment.getId());
      order.setAppointment(appt);
    }
    order.setStatus(OrderStatusType.OPEN);
    return orderRepository.save(order);
  }

  public Order startOrder(Order order) {
    order.setStatus(OrderStatusType.PENDING);
    return orderRepository.save(order);
  }

  public Order saveOrder(Order order) {
    return orderRepository.save(order);
  }

  public Order payForOrder(Order order) {
    order.setStatus(OrderStatusType.PAID);
    return orderRepository.save(order);
  }

  public Order closeOrder(Order order) {
    order = getOrderById(order.getId());
    appointmentService.closeAppointment(order.getAppointment());
    order.setStatus(OrderStatusType.CLOSED);
    return orderRepository.save(order);
  }

  public List<Order> getOrdersForPerson(Person person) {
    return orderRepository.findByPersonOrderByIdDesc(person);
  }

  public List<Order> getOrdersForEmployee(Employee emp) {
    return orderRepository.findAllByOrderByIdDesc();
  }

  public Order addItemToOrder(OrderItem orderItem, Order order, Item item) {
    if (order == null || order.getStatus().equals(OrderStatusType.CLOSED)) {
      throw new IllegalArgumentException("cannot update null or closed orders");
    }
    item = verifyItem(item);
    if (orderItem == null) {
      orderItem = new OrderItem();
      orderItem.setOrder(order);
      orderItem.setItem(item);
      orderItem.setQuantity(new BigDecimal("1"));
    }
    orderItem.setNotes(orderItem.getNotes() == null ? "" : orderItem.getNotes());
    orderItemRepository.save(orderItem);

    if (orderHasMatchingOrderItem(order, orderItem)) {
      order.getOrderItems().remove(orderItem);
    }
    order.getOrderItems().add(orderItem);
    order.setNumOfItems(getQuantityOfAllOrderItems(order));
    order.setSubTotal(updateSubTotal(order.getOrderItems()));
    order.setTax(getTaxAmount(order.getSubTotal()));
    order.setTotal(order.getSubTotal().add(order.getTax()).setScale(2, BigDecimal.ROUND_DOWN));
    return orderRepository.save(order);
  }

  public Order removeOrderItemFromOrder(Order order, OrderItem orderItem) {
    BigDecimal previousQuantity = new BigDecimal("1.00");
    for (OrderItem oi : order.getOrderItems()) {
      if (oi.equals(orderItem) && oi.getQuantity().intValue() > 0) {
        previousQuantity = oi.getQuantity();
      }
    }
    order.getOrderItems().remove(orderItem);
    order.setNumOfItems(order.getNumOfItems() - 1);
    BigDecimal unitPrice = orderItem.getItem().getPrice().multiply(previousQuantity);
    order.setSubTotal(order.getSubTotal().subtract(unitPrice).setScale(2, BigDecimal.ROUND_DOWN));
    order.setTax(getTaxAmount(order.getSubTotal()));
    order.setTotal(order.getSubTotal().add(order.getTax()).setScale(2, BigDecimal.ROUND_DOWN));
    orderItemRepository.delete(orderItem);
    return orderRepository.save(order);
  }

  public Order updateOrderItem(Order order, OrderItem orderItem) {
    for (OrderItem oi : order.getOrderItems()) {
      if (oi.equals(orderItem)) {
        removeOrderItemFromOrder(order, oi);
      }
    }
    order.getOrderItems().add(orderItem);
    order.setNumOfItems(order.getNumOfItems() + orderItem.getQuantity().intValue());
    order.setSubTotal(
        updateSubTotal(
            order.getSubTotal(), orderItem.getItem().getPrice(), orderItem.getQuantity()));
    order.setTax(getTaxAmount(order.getSubTotal()));
    order.setTotal(order.getSubTotal().add(order.getTax()).setScale(2, BigDecimal.ROUND_DOWN));
    return orderRepository.save(order);
  }

  public List<PaymentMethod> getPaymentMethods() {
    return paymentMethodRepository.findAll();
  }

  public List<MonthlyTotals> getMonthlyTotals(List<Order> orderList) {
    return null;
  }

  private int getQuantityOfAllOrderItems(Order order) {
    if (CollectionUtils.isEmpty(order.getOrderItems())) return 0;
    int count = 0;
    for (OrderItem oi : order.getOrderItems()) count += oi.getQuantity().intValue();
    return count;
  }

  private boolean orderHasMatchingOrderItem(Order order, OrderItem orderItem) {
    if (CollectionUtils.isNotEmpty(order.getOrderItems())) {
      for (OrderItem oi : order.getOrderItems()) {
        if (oi.getItem().equals(orderItem.getItem()) && oi.getOrder().equals(orderItem.getOrder()))
          return true;
      }
    }
    return false;
  }

  private Item verifyItem(Item item) {
    if (item == null || item.getDeletedFlag().equals("Y")) {
      throw new IllegalArgumentException("cannot update or add null/deleted items");
    }
    if (item.getId() == null && item.getSku() != null) {
      item = itemService.getItemBySku(item.getSku());
    }
    return item;
  }

  private BigDecimal updateSubTotal(
      BigDecimal current, BigDecimal itemPrice, BigDecimal itemQuantity) {
    return current.add(itemPrice.multiply(itemQuantity)).setScale(2, BigDecimal.ROUND_DOWN);
  }

  private BigDecimal updateSubTotal(Set<OrderItem> orderItems) {
    BigDecimal total = new BigDecimal("0.00");
    if (CollectionUtils.isEmpty(orderItems)) return total;
    for (OrderItem oi : orderItems) {
      total =
          total
              .add(oi.getItem().getPrice().multiply(oi.getQuantity()))
              .setScale(2, BigDecimal.ROUND_DOWN);
    }
    return total;
  }

  private BigDecimal getTaxAmount(BigDecimal taxable) {
    return taxable.multiply(TAX_RATE).setScale(2, BigDecimal.ROUND_DOWN);
  }
}
