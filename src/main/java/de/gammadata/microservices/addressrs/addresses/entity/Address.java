package de.gammadata.microservices.addressrs.addresses.entity;

import de.gammadata.microservices.addressrs.addresses.control.AddressEntityListener;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author gfr
 */
@Entity
@Table(indexes = {
  @Index(name = "ADR_NAME_IDX", columnList = "NAME"),
  @Index(name = "ADR_ADD_NAME_IDX", columnList = "ADDITIONAL_NAME"),
  @Index(name = "ADR_CITY_NAME_IDX", columnList = "CITY_NAME"),
  @Index(name = "ADR_COUNTRY_NAME_IDX", columnList = "COUNTRY_NAME"),
  @Index(name = "ADR_ZIPCODE_NAME_IDX", columnList = "ZIPCODE_NAME")})
@EntityListeners({AddressEntityListener.class})
@NamedQueries({
  @NamedQuery(name = Address.SIMPLE_SEARCH_QUERY_NAME,
          query = "select e from Address e"
          + Address.WHERE_CLAUSE
  ),
  @NamedQuery(name = Address.SIMPLE_COUNT_QUERY_NAME,
          query = "select count(e) from Address e"
          + Address.WHERE_CLAUSE
  )})
public class Address extends BaseEntity {

  public static final String SIMPLE_SEARCH_QUERY_NAME = "Address_simpleSearchQuery";
  public static final String SIMPLE_COUNT_QUERY_NAME = "Address_simpleSearchCount";

  public static final String WHERE_CLAUSE = " where "
          + "LOWER(e.name) like :" + Address.SIMPLE_SEARCH_QUERY_PARAMETER
          + " OR LOWER(e.additionalName) like :" + Address.SIMPLE_SEARCH_QUERY_PARAMETER
          + " OR LOWER(e.cityName) like :" + Address.SIMPLE_SEARCH_QUERY_PARAMETER;

  private static final long serialVersionUID = 1L;

  @Column(name = "ADDITIONAL_NAME")
  private String additionalName;

  @Column
  private String number;
  /**
   * Mirror field.
   */
  @Column(name = "CITY_NAME")
  private String cityName;
  /**
   * Mirror field.
   */
  @Column(name = "COUNTRY_NAME")
  private String countryName;
  /**
   * Mirror field.
   */
  @Column(name = "ZIPCODE_NAME")
  private String zipCodeName;

  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private ZipCode zipCode;

  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private City city;

  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private Country country;

  public String getAdditionalName() {
    return additionalName;
  }

  public void setAdditionalName(String additionalName) {
    this.additionalName = additionalName;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public ZipCode getZipCode() {
    return zipCode;
  }

  public void setZipCode(ZipCode zipCode) {
    this.zipCode = zipCode;
  }

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }

  public Country getCountry() {
    return country;
  }

  public void setCountry(Country country) {
    this.country = country;
  }

  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  public String getCountryName() {
    return countryName;
  }

  public void setCountryName(String countryName) {
    this.countryName = countryName;
  }

  public String getZipCodeName() {
    return zipCodeName;
  }

  public void setZipCodeName(String zipCodeName) {
    this.zipCodeName = zipCodeName;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 67 * hash + Objects.hashCode(this.additionalName);
    hash = 67 * hash + Objects.hashCode(this.number);
    hash = 67 * hash + Objects.hashCode(this.zipCode);
    hash = 67 * hash + Objects.hashCode(this.city);
    hash = 67 * hash + Objects.hashCode(this.country);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!super.equals(obj)) {
      return false;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Address other = (Address) obj;
    if (!Objects.equals(this.additionalName, other.additionalName)) {
      return false;
    }
    if (!Objects.equals(this.number, other.number)) {
      return false;
    }
    if (!Objects.equals(this.zipCode, other.zipCode)) {
      return false;
    }
    if (!Objects.equals(this.city, other.city)) {
      return false;
    }
    return Objects.equals(this.country, other.country);
  }

  @Override
  public String toString() {
    return "Address{" + super.toString() + ", additionalName=" + additionalName + ", number=" + number + ", zipCode="
            + zipCode + ", city=" + city + ", country=" + country + '}';
  }

}
