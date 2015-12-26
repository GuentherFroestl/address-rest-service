package de.gammadata.microservices.addressrs.addresses.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author gfr
 */
@MappedSuperclass
public class BaseEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  protected Long id;
  @Column
  protected String name;
  @Column
  @Temporal(TemporalType.DATE)
  protected Date validFrom;
  @Column
  @Temporal(TemporalType.DATE)
  protected Date validUntil;

  public BaseEntity(String name, Date validFrom, Date validUntil) {
    this.name = name;
    this.validFrom = validFrom;
    this.validUntil = validUntil;
  }

  public BaseEntity() {
  }
  
  

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getValidFrom() {
    return validFrom;
  }

  public void setValidFrom(Date validFrom) {
    this.validFrom = validFrom;
  }

  public Date getValidUntil() {
    return validUntil;
  }

  public void setValidUntil(Date validUntil) {
    this.validUntil = validUntil;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 89 * hash + Objects.hashCode(this.id);
    hash = 89 * hash + Objects.hashCode(this.name);
    hash = 89 * hash + Objects.hashCode(this.validFrom);
    hash = 89 * hash + Objects.hashCode(this.validUntil);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final BaseEntity other = (BaseEntity) obj;
    if (!Objects.equals(this.name, other.name)) {
      return false;
    }
    if (!Objects.equals(this.id, other.id)) {
      return false;
    }
    if (!Objects.equals(this.validFrom, other.validFrom)) {
      return false;
    }
    if (!Objects.equals(this.validUntil, other.validUntil)) {
      return false;
    }
    return true;
  }

  
  
  @Override
  public String toString() {
    return "BaseEntity{" + "id=" + id + ", name=" + name + ", validFrom=" + validFrom + ", validUntil=" + validUntil + '}';
  }
}
