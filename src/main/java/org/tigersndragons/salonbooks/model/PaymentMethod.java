package org.tigersndragons.salonbooks.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "SALONBOOKS", name = "PAYMENT_METHOD")
@AttributeOverride(name = "id", column = @Column(name = "PAYMENT_METHOD_ID"))
@Getter
@Setter
public class PaymentMethod extends SalonObject {

  private static final long serialVersionUID = 1L;

  @Column(name = "name")
  private String name;
}
