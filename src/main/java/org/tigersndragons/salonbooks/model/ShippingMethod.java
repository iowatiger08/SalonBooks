package org.tigersndragons.salonbooks.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.tigersndragons.salonbooks.model.type.ShipperType;

@Entity
@Table(schema = "SALONBOOKS", name = "SHIPPING_METHOD")
@AttributeOverride(name = "id", column = @Column(name = "METHOD_ID"))
@Getter
@Setter
public class ShippingMethod extends SalonObject {

  private static final long serialVersionUID = 1L;

  @Column(name = "METHOD_NAME")
  @Enumerated(EnumType.STRING)
  private ShipperType name;
}
