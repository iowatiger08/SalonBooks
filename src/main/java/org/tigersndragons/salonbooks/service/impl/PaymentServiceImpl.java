package org.tigersndragons.salonbooks.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tigersndragons.salonbooks.model.Order;
import org.tigersndragons.salonbooks.model.Payment;
import org.tigersndragons.salonbooks.service.PaymentService;

@Service
@Transactional
public class PaymentServiceImpl extends BaseServiceImpl implements PaymentService {

    private static final long serialVersionUID = 1L;

    public List<Payment> getPayments() {
        return null;
    }

    public void save(Payment payment) {
    }

    public Payment takePaymentForOrder(Payment payment, Order order) {
        return payment;
    }
}
