package org.tigersndragons.salonbooks.model.flows;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.tigersndragons.salonbooks.model.Appointment;
import org.tigersndragons.salonbooks.model.Item;
import org.tigersndragons.salonbooks.model.Order;
import org.tigersndragons.salonbooks.model.OrderItem;
import org.tigersndragons.salonbooks.model.PaymentMethod;
import org.tigersndragons.salonbooks.model.Person;
import org.tigersndragons.salonbooks.model.ShippingMethod;
import org.tigersndragons.salonbooks.model.type.OrderStatusType;

@Component
@Getter
@Setter
public class OrderFormModel extends SalonFlows {

  private static final long serialVersionUID = 1L;

  private Long orderId;
  private Order order;
  private Person person;
  private Long personId;
  private BigDecimal shipping = new BigDecimal(0.00);
  private BigDecimal subTotal = new BigDecimal(0.00);
  private BigDecimal tax = new BigDecimal(0.00);
  private BigDecimal total = new BigDecimal(0.00);
  private OrderItem[] orderItems;
  private OrderItem anOrderItem;
  private Item itemSelect;
  private BigDecimal quantity;
  private Long appointmentId;
  private List<Item> itemList;
  private String saveAndClose;
  private Long paymentMethodId;
  private Long shipperId;
  private LocalDateTime createDate;
  private int numOfItems;

  public void setPerson(Person person) {
    this.person = person;
    this.personId = person.getId();
  }

  public void setOrder(Order order) {
    this.order = order;
    convertOrdertoModel();
  }

  public Order convertModelToOrder() {
    Order o = new Order();
    o.setId(orderId);
    Appointment appointment = new Appointment();
    appointment.setId(appointmentId);
    o.setAppointment(appointment);
    Person person1 = new Person();
    person1.setId(personId);
    o.setPerson(person1);
    if (saveAndClose != null) o.setStatus(OrderStatusType.valueOf(saveAndClose));
    o.setSubTotal(subTotal);
    o.setTax(tax);
    o.setTotal(total);
    o.setNumOfItems(numOfItems);
    o.setShippingCost(shipping);
    o.setCreateDate(createDate);
    PaymentMethod payment = new PaymentMethod();
    payment.setId(paymentMethodId);
    o.setPaymentMethod(payment);
    ShippingMethod shipMethod = new ShippingMethod();
    shipMethod.setId(shipperId);
    o.setShipper(shipMethod);
    return o;
  }

  public void convertOrdertoModel() {
    Order o = this.order;
    setOrderId(o.getId());
    if (o.getAppointment() != null) setAppointmentId(o.getAppointment().getId());
    setCreateDate(o.getCreateDate());
    if (o.getPerson() != null) setPersonId(o.getPerson().getId());
    setSubTotal(o.getSubTotal());
    setTax(o.getTax());
    setTotal(o.getTotal());
    setNumOfItems(o.getNumOfItems());
    setShipping(o.getShippingCost());
    if (o.getPaymentMethod() != null) setPaymentMethodId(o.getPaymentMethod().getId());
    if (o.getShipper() != null) setShipperId(o.getShipper().getId());
  }
}
