package de.gammadata.microservices.addressrs.addresses.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import de.gammadata.microservices.addressrs.addresses.control.BaseEntityListener;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author gfr
 */
@Entity
@EntityListeners({BaseEntityListener.class})
@Table(indexes = {
  @Index(name = "BUILDING_NAME_IDX", columnList = "NAME"),
  @Index(name = "BUILDING_NUMBER_IDX", columnList = "NUMBER"),
  @Index(name = "BUILDING_STREET_ID_IDX", columnList = "STREET_ID")})

@NamedQueries({
  @NamedQuery(name = Building.BUILDING_FOR_ADR_SEARCH_QUERY_NAME,
          query = "select e from Building e"
          + Building.WHERE_CLAUSE
  ),
  @NamedQuery(name = Building.BUILDING_FOR_ADR_COUNT_QUERY_NAME,
          query = "select count(e) from Building e"
          + Building.WHERE_CLAUSE
  )})
public class Building extends BaseEntity {

  private static final long serialVersionUID = 1L;

  /**
   *
   */
  public static final String BUILDING_FOR_ADR_SEARCH_QUERY_NAME = "Building_simpleSearchQuery";

  /**
   *
   */
  public static final String BUILDING_FOR_ADR_COUNT_QUERY_NAME = "Building_simpleSearchCount";

  /**
   *
   */
  public static final String ADR_ID_QUERY_PARAMETER = "Adr_id";

  /**
   *
   */
  public static final String WHERE_CLAUSE = " where ( "
          + "LOWER(e.name) like :" + BaseEntity.SIMPLE_SEARCH_QUERY_PARAMETER
          + " OR LOWER(e.number) like :" + BaseEntity.SIMPLE_SEARCH_QUERY_PARAMETER
          + " ) AND e.streetId = :" + ADR_ID_QUERY_PARAMETER;

  @Column(name = "NUMBER")
  private String number;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "STREET_ID")
  private Street street;

  @Column(name = "STREET_ID", insertable = false, updatable = false)
  private Long streetId;

  /**
   *
   * @return
   */
  public String getNumber() {
    return number;
  }

  /**
   *
   * @param number
   */
  public void setNumber(String number) {
    this.number = number;
  }

  /**
   *
   * @return
   */
  public Street getStreet() {
    return street;
  }

  /**
   *
   * @param street
   */
  public void setStreet(Street street) {
    this.street = street;
  }

  /**
   *
   * @return
   */
  public Long getStreetId() {
    return streetId;
  }

  @Override
  public int hashCode() {
    int hash = super.hashCode();
    hash = 97 * hash + Objects.hashCode(this.number);
    hash = 97 * hash + Objects.hashCode(this.street);
    hash = 97 * hash + Objects.hashCode(this.streetId);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (!super.equals(obj)){
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
    final Building other = (Building) obj;
    if (!Objects.equals(this.number, other.number)) {
      return false;
    }
    if (!Objects.equals(this.street, other.street)) {
      return false;
    }
    return Objects.equals(this.streetId, other.streetId);
  }
  
  

}
