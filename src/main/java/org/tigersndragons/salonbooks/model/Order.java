package org.tigersndragons.salonbooks.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import org.tigersndragons.salonbooks.model.type.OrderStatusType;

@Entity
@Table(schema = "SALONBOOKS", name = "`ORDER`")
@AttributeOverride(name = "id", column = @Column(name = "ORDER_ID"))
@Getter
@Setter
public class Order extends SalonObject {

    private static final long serialVersionUID = 1L;

    @Column(name = "TOTAL")
    private BigDecimal total = new BigDecimal(0.0);

    @Column(name = "NUM_OF_ITEMS")
    private int numOfItems = 0;

    @Column(name = "TAX")
    private BigDecimal tax = new BigDecimal(0.0);

    @Column(name = "CURRENCY")
    private String currency = "USD";

    @Column(name = "SUBTOTAL")
    private BigDecimal subTotal = new BigDecimal(0.0);

    @ManyToOne
    @JoinColumn(name = "PAYMENT_METHOD_ID", insertable = false, updatable = false)
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "SHIPPER_ID", insertable = false, updatable = false)
    private ShippingMethod shipper;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private OrderStatusType status;

    @ManyToOne
    @JoinColumn(name = "APPOINTMENT_ID", updatable = false)
    private Appointment appointment;

    @Column(name = "SHIPPING")
    private BigDecimal shippingCost;

    @ManyToOne
    @JoinColumn(name = "PERSON_ID", updatable = false)
    private Person person;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.order")
    private Set<OrderItem> orderItems = new HashSet<>(0);

    @Override
    public String toString() {
        return this.id + "," + this.total.toString() + ", " + this.person;
    }
}
