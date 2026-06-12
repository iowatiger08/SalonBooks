package org.tigersndragons.salonbooks.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(schema = "SALONBOOKS", name = "EMPLOYEE")
@AttributeOverride(name = "id", column = @Column(name = "EMPLOYEE_ID"))
@Getter
@Setter
public class Employee extends SalonObject {

  private static final long serialVersionUID = 1L;

  @Column(name = "USERNAME", unique = true)
  @NaturalId
  private String username;

  @Column(name = "PASSWORD")
  private String password;

  @Column(name = "EMPLOYEE_NAME")
  private String name;
}
