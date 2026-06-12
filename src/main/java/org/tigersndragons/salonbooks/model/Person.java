package org.tigersndragons.salonbooks.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import org.tigersndragons.salonbooks.model.type.GenderType;

@Entity
@Table(schema = "SALONBOOKS", name = "PERSON")
@AttributeOverride(name = "id", column = @Column(name = "PERSON_ID"))
@Getter
@Setter
public class Person extends SalonObject {

  private static final long serialVersionUID = 1L;

  @Column(name = "FIRST_NAME")
  private String firstName = "Unknown";

  @Column(name = "MIDDLE_NAME")
  private String middleName = "Unknown";

  @Column(name = "LAST_NAME")
  private String lastName = "Unknown";

  @Column(name = "BIRTH_DATE")
  private LocalDate birthDate;

  @Column(name = "GENDER")
  @Enumerated(EnumType.STRING)
  private GenderType gender = GenderType.U;

  @Column(name = "PREFIX")
  private String prefix = "Ms";

  @Column(name = "SUFFIX")
  private String suffix = "";

  @Column(name = "EMAIL")
  private String email = "default@email.ca";

  @Column(name = "HOME_PHONE")
  private String homePhoneNumber;

  @Column(name = "TWITTER")
  private String twitter = "@default";

  @Column(name = "PRIMARY_PHONENUMBER")
  private String primaryPhoneNumber;

  @Override
  public String toString() {
    return this.id
        + ","
        + this.firstName
        + " "
        + this.lastName
        + ","
        + this.primaryPhoneNumber
        + " | ";
  }
}
