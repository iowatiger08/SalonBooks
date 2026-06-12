package org.tigersndragons.salonbooks.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "SALONBOOKS", name = "ITEM")
@AttributeOverride(name = "id", column = @Column(name = "ITEM_ID"))
@Getter
@Setter
public class Item extends SalonObject {

    private static final long serialVersionUID = 1L;

    @Column(name = "SKU")
    private String sku;

    @Column(name = "IS_SERVICE")
    private int isService;

    @Column(name = "LABEL")
    private String label;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "UNIT_COST")
    private BigDecimal unitCost;

    @Column(name = "DELETED_FLAG")
    private String deletedFlag = "N";

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.item", cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems = new HashSet<>(0);

    @Override
    public String toString() {
        return this.getId() + " | " + this.label + " | " + this.sku + " | " + this.price;
    }
}
