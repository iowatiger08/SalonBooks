package org.tigersndragons.salonbooks.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;

@MappedSuperclass
@Getter
@Setter
public abstract class SalonObject implements Entity, Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;

  @Column(name = "CREATE_DATE")
  protected LocalDateTime createDate;

  @Column(name = "UPDATE_DATE")
  protected LocalDateTime updateDate;

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof SalonObject)) return false;
    return ObjectUtils.equals(this.id, ((SalonObject) obj).getId());
  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
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

  public boolean matches(Entity entity) {
    return false;
  }
}
