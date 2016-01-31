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
import org.eclipse.persistence.annotations.Multitenant;
import org.eclipse.persistence.annotations.MultitenantType;
import org.eclipse.persistence.annotations.TenantTableDiscriminator;
import static org.eclipse.persistence.annotations.TenantTableDiscriminatorType.SCHEMA;

/**
 *
 * @author gfr
 */
@MappedSuperclass
@Multitenant(MultitenantType.TABLE_PER_TENANT)
@TenantTableDiscriminator(type = SCHEMA, contextProperty = BaseEntity.TENANT_ID)
//@Multitenant
//@TenantDiscriminatorColumn(name = "TENANT_ID", contextProperty = "tenant.id",
//        discriminatorType = DiscriminatorType.STRING)
public class BaseEntity implements Serializable {

  public final static String TENANT_SCHEMA_NAME = "tenant_schema";
  public final static String TENANT_ID = "tenant.id";

  /**
   *
   */
  public static final String SIMPLE_SEARCH_QUERY_PARAMETER = "parameter";

  /**
   *
   */
  public static final String ID_PARAMETER = "id";

  /**
   *
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  protected Long id;

  /**
   *
   */
  @Version
  protected Integer version;

  /**
   *
   */
  @Column(name = "NAME")
  protected String name;

  /**
   *
   */
  @Column
  @Temporal(TemporalType.TIMESTAMP)
  protected Date modified;

  /**
   *
   */
  public BaseEntity() {
  }

  /**
   *
   * @param id
   * @param version
   * @param name
   * @param modified
   */
  public BaseEntity(Long id, Integer version, String name, Date modified) {
    this.id = id;
    this.version = version;
    this.name = name;
    this.modified = modified;
  }

  /**
   *
   * @return
   */
  public Long getId() {
    return id;
  }

  /**
   *
   * @param id
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   *
   * @return
   */
  public Integer getVersion() {
    return version;
  }

  /**
   *
   * @param version
   */
  public void setVersion(Integer version) {
    this.version = version;
  }

  /**
   *
   * @return
   */
  public String getName() {
    return name;
  }

  /**
   *
   * @param name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   *
   * @return
   */
  public Date getModified() {
    return modified;
  }

  /**
   *
   * @param modified
   */
  public void setModified(Date modified) {
    this.modified = modified;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 59 * hash + Objects.hashCode(this.id);
    hash = 59 * hash + Objects.hashCode(this.version);
    hash = 59 * hash + Objects.hashCode(this.name);
    hash = 59 * hash + Objects.hashCode(this.modified);
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
    return "BaseEntity{" + "id=" + id + ", version=" + version
            + ", name=" + name + ", modified=" + modified + '}';
  }
}
