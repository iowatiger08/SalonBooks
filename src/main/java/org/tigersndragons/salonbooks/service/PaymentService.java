package org.tigersndragons.salonbooks.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.tigersndragons.salonbooks.model.Appointment;
import org.tigersndragons.salonbooks.model.Employee;
import org.tigersndragons.salonbooks.model.Order;
import org.tigersndragons.salonbooks.model.Payment;
import org.tigersndragons.salonbooks.model.PaymentMethod;
import org.tigersndragons.salonbooks.model.Person;

@Service
public interface PaymentService {

	public List<Payment> getPayments();

	public void save(Payment payment);
	
	public Payment takePaymentForOrder(Payment payment , Order order);

}
