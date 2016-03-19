package de.gammadata.microservices.addressrs.common.entity;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Defines Validity for an related Entity.
 *
 * @author gfr
 */
@MappedSuperclass
public class EntityWithValidity extends BaseEntity {
    
    static final long serialVersionUID = 1l;

  public EntityWithValidity() {
  }

  public EntityWithValidity(Long id, Integer version, String name, Date modified, Date validFrom, Date validTo) {
    super(id, version, name, modified);
    this.validFrom = getNullSaveDate(validFrom);
    this.validTo = getNullSaveDate(validTo);
  }

  @Column(name = "VALID_FROM")
  @Temporal(TemporalType.DATE)
  private Date validFrom;

  @Column(name = "VALID_TO")
  @Temporal(TemporalType.DATE)
  private Date validTo;

  public Date getValidFrom() {
    return getNullSaveDate(validFrom);
  }

  public void setValidFrom(Date validFrom) {
    this.validFrom = getNullSaveDate(validFrom);
  }

  public Date getValidTo() {
    return getNullSaveDate(validTo);
  }

  public void setValidTo(Date validTo) {
    this.validTo = getNullSaveDate(validTo);
  }

  @Override
  public int hashCode() {
    int hash = super.hashCode();
    hash = 53 * hash + Objects.hashCode(this.validFrom);
    hash = 53 * hash + Objects.hashCode(this.validTo);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {

    if (!super.equals(obj)) {
      return false;
    }
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final EntityWithValidity other = (EntityWithValidity) obj;
    if (!compareDates(this.validFrom, other.validFrom)) {
      return false;
    }
    return compareDates(this.validTo, other.validTo);
  }

  @Override
  public String toString() {
    return "EntityWithValidity{" + super.toString() + ", validFrom=" + validFrom + ", validTo=" + validTo + '}';
  }

}
