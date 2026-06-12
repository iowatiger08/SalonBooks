package org.tigersndragons.salonbooks.model.type;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(schema = "SALONBOOKS", name = "CONTACT_TYPE")
@AttributeOverrides({
  @AttributeOverride(name = "id", column = @Column(name = "ID")),
  @AttributeOverride(name = "name", column = @Column(name = "NAME"))
})
public class ContactType extends BaseLookup {}
