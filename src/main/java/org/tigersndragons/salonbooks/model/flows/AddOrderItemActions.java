package org.tigersndragons.salonbooks.model.flows;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tigersndragons.salonbooks.ServiceUtils;
import org.tigersndragons.salonbooks.exception.PersonNotFoundException;
import org.tigersndragons.salonbooks.exception.ValidationException;
import org.tigersndragons.salonbooks.model.Order;
import org.tigersndragons.salonbooks.model.OrderItem;
import org.tigersndragons.salonbooks.model.Person;
import org.tigersndragons.salonbooks.service.OrderService;

@Component
@Getter
@Setter
public class AddOrderItemActions extends SalonFlows {

    private static final long serialVersionUID = 1L;

    private Long orderId;
    private Order order;
    private Person person;
    private Long personId;
    private OrderItem anOrderItem;
    private Long itemSelect;

    @NotNull
    private BigDecimal quantity;

    private String addItemtoOrder;

    @Autowired
    private OrderService orderService;

    public void setPerson(Person person) {
        this.person = person;
        this.personId = person.getId();
    }

    public void setOrder(Order order) {
        this.order = order;
        this.orderId = order.getId();
    }

    public Order startOrder(Long orderId) throws PersonNotFoundException, ValidationException {
        ServiceUtils.assertNotNull("orderId cannot be null", orderId);
        return orderService.startOrder(orderService.getOrderById(orderId));
    }
}
