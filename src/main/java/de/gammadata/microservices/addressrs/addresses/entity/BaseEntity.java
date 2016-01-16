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
import javax.persistence.Version;

/**
 *
 * @author gfr
 */
@MappedSuperclass
public class BaseEntity implements Serializable {

  public static final String SIMPLE_SEARCH_QUERY_PARAMETER = "parameter";

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  protected Long id;
  @Version
  protected Integer version;
  @Column(name = "NAME")
  protected String name;
  @Column
  @Temporal(TemporalType.TIMESTAMP)
  protected Date modified;

  public BaseEntity() {
  }

  public BaseEntity(String name) {
    this.name = name;
  }

  public BaseEntity(Long id, Integer version, String name, Date modified) {
    this.id = id;
    this.version = version;
    this.name = name;
    this.modified = modified;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getModified() {
    return modified;
  }

  public void setModified(Date modified) {
    this.modified = modified;
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 41 * hash + Objects.hashCode(this.id);
    hash = 41 * hash + Objects.hashCode(this.version);
    hash = 41 * hash + Objects.hashCode(this.name);
    hash = 41 * hash + Objects.hashCode(this.modified);
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
    if (!Objects.equals(this.version, other.version)) {
      return false;
    }
    return compareDates(this.modified, other.modified);
  }

  /**
   * due to a bug in jpa date are just precise as of second level. ms are random. So compare it with the date strings.
   *
   * @param obj Date
   * @param other Date
   * @return true if equal
   */
  private boolean compareDates(Date obj, Date other) {
    if (obj == null && other == null) {
      return true;
    }
    if (obj == null || other == null) {
      return false;
    }

    return obj.toString().compareTo(other.toString()) == 0;
  }

  @Override
  public String toString() {
    return "BaseEntity{" + "id=" + id + ", version=" + version + ", name=" + name + ", modified=" + modified + '}';
  }
}
