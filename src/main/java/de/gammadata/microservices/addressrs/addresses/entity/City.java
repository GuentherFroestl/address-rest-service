package de.gammadata.microservices.addressrs.addresses.entity;

import de.gammadata.microservices.addressrs.addresses.control.CityEntityListener;
import java.util.Objects;
import javax.persistence.CascadeType;
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
@EntityListeners({CityEntityListener.class})
@Table(indexes = {
  @Index(name = "CITY_NAME_IDX", columnList = "NAME"),
  @Index(name = "CITY_COUNTRY_NAME_IDX", columnList = "COUNTRY_NAME")})

@NamedQueries({
  @NamedQuery(name = City.SIMPLE_SEARCH_QUERY_NAME,
          query = "select e from City e"
          + City.WHERE_CLAUSE
  ),
  @NamedQuery(name = City.SIMPLE_COUNT_QUERY_NAME,
          query = "select count(e) from City e"
          + City.WHERE_CLAUSE
  ),
  @NamedQuery(name = City.QUERY_CITIES_BY_COUNTRY_NAME,
          query = "select e from City e"
          + City.WHERE_CLAUSE_COUNTRY_ID
  )})
public class City extends BaseEntity {

  private static final long serialVersionUID = 1L;

  /**
   *
   */
  public static final String SIMPLE_SEARCH_QUERY_NAME = "City_simpleSearchQuery";

  /**
   *
   */
  public static final String SIMPLE_COUNT_QUERY_NAME = "City_simpleSearchCount";

  /**
   *
   */
  public static final String QUERY_CITIES_BY_COUNTRY_NAME = "City_queryByCountry";

  /**
   *
   */
  public static final String WHERE_CLAUSE = " where "
          + "LOWER(e.name) like :" + BaseEntity.SIMPLE_SEARCH_QUERY_PARAMETER
          + " OR LOWER(e.countryName) like :" + BaseEntity.SIMPLE_SEARCH_QUERY_PARAMETER;

  /**
   *
   */
  public static final String WHERE_CLAUSE_COUNTRY_ID = " where "
          + " e.country.id= :" + BaseEntity.ID_PARAMETER
          + " AND"
          + " LOWER(e.name) like :" + BaseEntity.SIMPLE_SEARCH_QUERY_PARAMETER;
  /**
   * Mirror field.
   */
  @Column(name = "COUNTRY_NAME")
  private String countryName;
  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinColumn(name = "COUNTRY_ID")
  private Country country;

  /**
   *
   * @return
   */
  public String getCountryName() {
    return countryName;
  }

  /**
   *
   * @param countryName
   */
  public void setCountryName(String countryName) {
    this.countryName = countryName;
  }


  /**
   *
   * @return
   */
  public Country getCountry() {
    return country;
  }

  /**
   *
   * @param country
   */
  public void setCountry(Country country) {
    this.country = country;
    if (country != null) {
      this.countryName = country.getName();
    }
  }

  @Override
  public int hashCode() {
    int hash = super.hashCode();
    hash = 23 * hash + Objects.hashCode(this.countryName);
    hash = 23 * hash + Objects.hashCode(this.country);
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
    final City other = (City) obj;
    if (!Objects.equals(this.countryName, other.countryName)) {
      return false;
    }
    return Objects.equals(this.country, other.country);
  }

  @Override
  public String toString() {
    return "City{" + super.toString() + ", countryName=" + countryName + ", country=" + country + '}';
  }

}
