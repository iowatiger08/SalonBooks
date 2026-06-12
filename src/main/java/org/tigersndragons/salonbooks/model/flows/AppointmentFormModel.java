package org.tigersndragons.salonbooks.model.flows;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tigersndragons.salonbooks.model.type.AppointmentStatusType;

@Transactional
@Component
@Getter
@Setter
public class AppointmentFormModel extends SalonFlows {

  private static final long serialVersionUID = 1L;

  private String notes;
  private Long appointmentId;
  private Long personId;
  private LocalDateTime appointmentDate;
  private boolean addOrdertoAppointment = false;
  private LocalDateTime createDate;
  private AppointmentStatusType appointmentStatusType;
}
