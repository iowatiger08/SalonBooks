package org.tigersndragons.salonbooks.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.tigersndragons.salonbooks.model.type.AppointmentStatusType;

@Entity
@Table(schema = "SALONBOOKS", name = "APPOINTMENT")
@AttributeOverride(name = "id", column = @Column(name = "APPOINTMENT_ID"))
@Getter
@Setter
public class Appointment extends SalonObject {

  private static final long serialVersionUID = 1L;

  @Column(name = "APPOINTMENT_DATE")
  private LocalDateTime appointmentDate;

  @Column(name = "NOTES")
  private String notes;

  @ManyToOne
  @JoinColumn(name = "PERSON_ID")
  private Person person;

  @ManyToOne
  @JoinColumn(name = "EMPLOYEE_ID")
  private Employee employee;

  @Enumerated(EnumType.STRING)
  @Column(name = "APPOINTMENT_STATUS")
  private AppointmentStatusType appointmentStatusType;
}
