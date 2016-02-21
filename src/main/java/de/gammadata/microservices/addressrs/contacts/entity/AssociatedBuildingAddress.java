package de.gammadata.microservices.addressrs.contacts.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import de.gammadata.microservices.addressrs.addresses.entity.Building;
import de.gammadata.microservices.addressrs.common.control.BaseEntityListener;
import de.gammadata.microservices.addressrs.common.entity.BaseEntity;
import de.gammadata.microservices.addressrs.common.entity.EntityWithValidity;
import java.util.Date;
import java.util.Objects;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import static javax.persistence.FetchType.LAZY;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;

/**
 *
 * @author gfr
 */
@Entity
@EntityListeners({BaseEntityListener.class})
@Table(name = "ASSOCIATED_BUILDING_ADR",
        indexes = {
          @Index(name = "ASS_ADR_QUALIFIER_IDX", columnList = "QUALIFIER"),
          @Index(name = "ASS_ADR_BUILDING_ADR_TXT_IDX", columnList = "BUILDING_ADR_TXT"),
          @Index(name = "ASS_ADR_BUILDING_ID_IDX", columnList = "BUILDING_ID"),
          @Index(name = "ASS_ADR_CONTACT_ID_IDX", columnList = "CONTACT_ID")}
)
//Named Queries for use with CRUD Controller
@NamedQueries({
  @NamedQuery(name = AssociatedBuildingAddress.SIMPLE_SEARCH_QUERY_NAME,
          query = "select e from AssociatedBuildingAddress e"
          + AssociatedBuildingAddress.WHERE_CLAUSE
  ),
  @NamedQuery(name = AssociatedBuildingAddress.SIMPLE_COUNT_QUERY_NAME,
          query = "select count(e) from AssociatedBuildingAddress e"
          + AssociatedBuildingAddress.WHERE_CLAUSE
  ),
  @NamedQuery(name = AssociatedBuildingAddress.ADR_FOR_CONTACT_QUERY_NAME,
          query = "select e from AssociatedBuildingAddress e"
          + " where e.contact.id = :" + AssociatedBuildingAddress.FK_SEARCH_QUERY_PARAMETER
  )})
//Resultset Mapping for List view, w/o deep relation graph.
@SqlResultSetMappings({
  @SqlResultSetMapping(
          name = AssociatedBuildingAddress.RESULT_SET_MAPPING_NAME,
          classes = @ConstructorResult(
                  targetClass = AssociatedBasicAddress.class,
                  columns = {
                    @ColumnResult(name = "ID", type = Long.class),
                    @ColumnResult(name = "VERSION", type = Integer.class),
                    @ColumnResult(name = "NAME", type = String.class),
                    @ColumnResult(name = "MODIFIED", type = Date.class),
                    @ColumnResult(name = "VALID_FROM", type = Date.class),
                    @ColumnResult(name = "VALID_TO", type = Date.class),
                    @ColumnResult(name = "QUALIFIER", type = String.class),
                    @ColumnResult(name = "BUILDING_ADR_TXT", type = String.class),
                    @ColumnResult(name = "CONTACT_ID", type = Long.class),
                    @ColumnResult(name = "BUILDING_ID", type = Long.class)
                  }))
})
public class AssociatedBuildingAddress extends EntityWithValidity {

  private static final long serialVersionUID = 1L;

  /**
   * Some static text for queries.
   */
  public static final String SIMPLE_SEARCH_QUERY_NAME = "ASS_ADR_simpleSearchQuery";
  public static final String SIMPLE_COUNT_QUERY_NAME = "ASS_ADR_simpleSearchCount";
  public static final String WHERE_CLAUSE = " where"
          + " LOWER(e.name) like :" + BaseEntity.SIMPLE_SEARCH_QUERY_PARAMETER
          + " OR LOWER(e.qualifier) like :" + BaseEntity.SIMPLE_SEARCH_QUERY_PARAMETER
          + " OR LOWER(e.buildingAddressText) like :" + BaseEntity.SIMPLE_SEARCH_QUERY_PARAMETER;

  public static final String ADR_FOR_CONTACT_QUERY_NAME = "ASS_ADR_FOR_CONTACT";
  public static final String RESULT_SET_MAPPING_NAME = "AssAdrBasicsContructor";

  //Native stuff
  public static final String NATIVE_SEARCH_COULMS = "select e.ID, e.VERSION, e.MODIFIED, e.NAME, e.QUALIFIER,"
          + " e.BUILDING_ADR_TXT, e.CONTACT_ID, e.BUILDING_ID from "
          + TENANT_SCHEMA_NAME
          + ".ASSOCIATED_BUILDING_ADR e";

  public static final String WHERE_CLAUSE_NATIVE = " where"
          + " LOWER(e.name) like ?1"
          + " OR LOWER(e.QUALIFIER) like ?1"
          + " OR LOWER(e.BUILDING_ADR_TXT) like ?1";

  public static final String WHERE_CLAUSE_NATIVE_FOR_CONTACTID = " where"
          + " CONTACT_ID = ?1";

  public static final String NATIVE_SEARCH_QUERY = NATIVE_SEARCH_COULMS + WHERE_CLAUSE_NATIVE;
  public static final String NATIVE_SEARCH_QUERY_FOR_CONTACTID = NATIVE_SEARCH_COULMS + WHERE_CLAUSE_NATIVE_FOR_CONTACTID;

  @Column(name = "QUALIFIER")
  private String qualifier;

  @Column(name = "BUILDING_ADR_TXT", length = 255)
  private String buildingAddressText;

  @OneToOne(cascade = {PERSIST, MERGE}, fetch = LAZY)
  @JoinColumn(name = "BUILDING_ID", referencedColumnName = "ID")
  private Building buildingAddress;

  @JsonBackReference
  @ManyToOne(cascade = {PERSIST, MERGE}, fetch = LAZY)
  @JoinColumn(name = "CONTACT_ID", referencedColumnName = "ID")
  private Contact contact;

  public String getQualifier() {
    return qualifier;
  }

  public void setQualifier(String qualifier) {
    this.qualifier = qualifier;
  }

  public String getBuildingAddressText() {
    return buildingAddressText;
  }

  public void setBuildingAddressText(String buildingAddressText) {
    this.buildingAddressText = buildingAddressText;
  }

  public Building getBuildingAddress() {
    return buildingAddress;
  }

  public void setBuildingAddress(Building buildingAddress) {
    this.buildingAddress = buildingAddress;
  }

  public Contact getContact() {
    return contact;
  }

  public void setContact(Contact contact) {
    this.contact = contact;
  }

  @Override
  public int hashCode() {
    int hash = super.hashCode();
    hash = 59 * hash + Objects.hashCode(this.qualifier);
    hash = 59 * hash + Objects.hashCode(this.buildingAddressText);
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
    final AssociatedBuildingAddress other = (AssociatedBuildingAddress) obj;
    if (!Objects.equals(this.qualifier, other.qualifier)) {
      return false;
    }
    return Objects.equals(this.buildingAddressText, other.buildingAddressText);
  }

  @Override
  public String toString() {
    return "AssociatedBuildingAddress{" + super.toString() + ", qualifier=" + qualifier + ", buildingAddressText="
            + buildingAddressText + '}';
  }

}
