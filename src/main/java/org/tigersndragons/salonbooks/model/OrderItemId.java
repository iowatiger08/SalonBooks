package org.tigersndragons.salonbooks.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class OrderItemId implements Serializable {

  private static final long serialVersionUID = 1L;

  @ManyToOne private Order order;

  @ManyToOne private Item item;

  @Override
  public int hashCode() {
    int hashCode = (order != null && order.getId() != null ? order.getId().hashCode() : 0);
    hashCode = 31 * hashCode + (item != null && item.getId() != null ? item.getId().hashCode() : 0);
    return hashCode;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || !(obj instanceof OrderItemId)) return false;
    OrderItemId other = (OrderItemId) obj;
    if (order != null ? !order.getId().equals(other.order.getId()) : other.order.getId() != null)
      return false;
    if (item != null ? !item.getId().equals(other.item.getId()) : other.item.getId() != null)
      return false;
    return true;
  }
}
