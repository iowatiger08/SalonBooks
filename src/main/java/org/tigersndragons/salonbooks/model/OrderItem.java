package org.tigersndragons.salonbooks.model;

import jakarta.persistence.AssociationOverride;
import jakarta.persistence.AssociationOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "SALONBOOKS", name = "ORDER_ITEMS")
@AssociationOverrides({
  @AssociationOverride(name = "pk.item", joinColumns = @JoinColumn(name = "ITEM_ID")),
  @AssociationOverride(name = "pk.order", joinColumns = @JoinColumn(name = "ORDER_ID"))
})
@Getter
@Setter
public class OrderItem implements Serializable {

  private static final long serialVersionUID = 1L;

  @EmbeddedId private OrderItemId pk = new OrderItemId();

  @Column(name = "NOTES")
  private String notes;

  @Column(name = "QUANTITY")
  private BigDecimal quantity;

  @Column(name = "CREATE_DATE")
  private LocalDateTime createDate;

  @Column(name = "UPDATE_DATE")
  private LocalDateTime updateDate;

  @Transient
  public Order getOrder() {
    return pk.getOrder();
  }

  public void setOrder(Order order) {
    pk.setOrder(order);
  }

  @Transient
  public Item getItem() {
    return pk.getItem();
  }

  public void setItem(Item item) {
    pk.setItem(item);
  }

  @PrePersist
  protected void prePersist() {
    LocalDateTime now = LocalDateTime.now();
    if (createDate == null) createDate = now;
    updateDate = now;
  }

  @PreUpdate
  protected void preUpdate() {
    updateDate = LocalDateTime.now();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || !(obj instanceof OrderItem)) return false;
    OrderItem other = (OrderItem) obj;
    return pk != null ? pk.equals(other.pk) : other.pk == null;
  }

  @Override
  public int hashCode() {
    return pk != null ? pk.hashCode() : 0;
  }
}
