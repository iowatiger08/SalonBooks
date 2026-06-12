package org.tigersndragons.salonbooks.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.tigersndragons.salonbooks.model.type.ContactType;

@Entity
@Table(schema = "SALONBOOKS", name = "CONTACT")
@AttributeOverride(name = "id", column = @Column(name = "CONTACT_ID"))
@Getter
@Setter
public class Contact extends SalonObject {

  private static final long serialVersionUID = 1L;

  @ManyToOne
  @JoinColumn(name = "TYPE_ID")
  private ContactType contactType;

  @Column(name = "LABEL")
  private String label;

  @ManyToOne
  @JoinColumn(name = "PERSON_ID")
  private Person person;

  @Column(name = "ISACTIVE")
  private String isActive;

  @Column(name = "ISURL")
  private String isURL;

  @Column(name = "FORMAT")
  private String format;
}
