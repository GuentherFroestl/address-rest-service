package de.gammadata.microservices.addressrs.addresses.entity;

import de.gammadata.microservices.addressrs.addresses.control.BaseEntityListener;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Index;
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
  @Index(name = "COUNTRY_NAME_IDX", columnList = "NAME")})

@NamedQueries({
  @NamedQuery(name = Country.SIMPLE_SEARCH_QUERY_NAME,
          query = "select e from Country e"
          + Country.WHERE_CLAUSE
  ),
  @NamedQuery(name = Country.SIMPLE_COUNT_QUERY_NAME,
          query = "select count(e) from Country e"
          + Country.WHERE_CLAUSE
  )})
public class Country extends BaseEntity {

  private static final long serialVersionUID = 1L;

  /**
   *
   */
  public static final String SIMPLE_SEARCH_QUERY_NAME = "Country_simpleSearchQuery";

  /**
   *
   */
  public static final String SIMPLE_COUNT_QUERY_NAME = "County_simpleSearchCount";

  /**
   *
   */
  public static final String WHERE_CLAUSE = " where "
          + "LOWER(e.name) like :" + BaseEntity.SIMPLE_SEARCH_QUERY_PARAMETER
          + " OR LOWER(e.iso2CountryCode) like :" + BaseEntity.SIMPLE_SEARCH_QUERY_PARAMETER
          + " OR LOWER(e.iso3CountryCode) like :" + BaseEntity.SIMPLE_SEARCH_QUERY_PARAMETER;

  @Column
  private Integer isoNumber;
  @Column(length = 2)
  private String iso2CountryCode;
  @Column(length = 3)
  private String iso3CountryCode;

  /**
   *
   * @return
   */
  public Integer getIsoNumber() {
    return isoNumber;
  }

  /**
   *
   * @param isoNumber
   */
  public void setIsoNumber(Integer isoNumber) {
    this.isoNumber = isoNumber;
  }

  /**
   *
   * @return
   */
  public String getIso2CountryCode() {
    return iso2CountryCode;
  }

  /**
   *
   * @param iso2CountryCode
   */
  public void setIso2CountryCode(String iso2CountryCode) {
    this.iso2CountryCode = iso2CountryCode;
  }

  /**
   *
   * @return
   */
  public String getIso3CountryCode() {
    return iso3CountryCode;
  }

  /**
   *
   * @param iso3CountryCode
   */
  public void setIso3CountryCode(String iso3CountryCode) {
    this.iso3CountryCode = iso3CountryCode;
  }

  @Override
  public int hashCode() {
    int hash = super.hashCode();
    hash = 61 * hash + Objects.hashCode(this.isoNumber);
    hash = 61 * hash + Objects.hashCode(this.iso2CountryCode);
    hash = 61 * hash + Objects.hashCode(this.iso3CountryCode);
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
    if (!super.equals(obj)) {
      return false;
    }
    final Country other = (Country) obj;
    if (!Objects.equals(this.iso2CountryCode, other.iso2CountryCode)) {
      return false;
    }
    if (!Objects.equals(this.iso3CountryCode, other.iso3CountryCode)) {
      return false;
    }
    if (!Objects.equals(this.isoNumber, other.isoNumber)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "Country{" + super.toString() + ", isoNumber=" + isoNumber + ", iso2CountryCode=" + iso2CountryCode + ", iso3CountryCode=" + iso3CountryCode + '}';
  }

}
