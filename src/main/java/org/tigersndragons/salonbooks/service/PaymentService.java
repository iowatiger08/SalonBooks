package org.tigersndragons.salonbooks.service;

import java.util.List;
import org.tigersndragons.salonbooks.model.Order;
import org.tigersndragons.salonbooks.model.Payment;

public interface PaymentService {

  List<Payment> getPayments();

  void save(Payment payment);

  Payment takePaymentForOrder(Payment payment, Order order);
}
