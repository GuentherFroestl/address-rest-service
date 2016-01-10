package de.gammadata.microservices.addressrs.addresses.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import de.gammadata.microservices.addressrs.addresses.control.AddressEntityListener;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.eclipse.persistence.annotations.PrivateOwned;

/**
 *
 * @author gfr
 */
@Entity
@EntityListeners({AddressEntityListener.class})

@Table(indexes = {
  @Index(name = "ADR_NAME_IDX", columnList = "NAME"),
  @Index(name = "ADR_ADD_NAME_IDX", columnList = "ADDITIONAL_NAME"),
  @Index(name = "ADR_CITY_NAME_IDX", columnList = "CITY_NAME"),
  @Index(name = "ADR_COUNTRY_NAME_IDX", columnList = "COUNTRY_NAME"),
  @Index(name = "ADR_ZIPCODE_NAME_IDX", columnList = "ZIPCODE_NAME")})

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
          + "LOWER(e.name) like :" + BaseEntity.SIMPLE_SEARCH_QUERY_PARAMETER
          + " OR LOWER(e.additionalName) like :" + BaseEntity.SIMPLE_SEARCH_QUERY_PARAMETER
          + " OR LOWER(e.cityName) like :" + BaseEntity.SIMPLE_SEARCH_QUERY_PARAMETER
          + " OR LOWER(e.countryName) like :" + BaseEntity.SIMPLE_SEARCH_QUERY_PARAMETER
          + " OR LOWER(e.zipCodeName) like :" + BaseEntity.SIMPLE_SEARCH_QUERY_PARAMETER;

  private static final long serialVersionUID = 1L;

  @Column(name = "ADDITIONAL_NAME")
  private String additionalName;
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

  @JsonManagedReference
  @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
  @PrivateOwned
  private List<Building> buildings;

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

  public List<Building> getBuildings() {
    return buildings;
  }

  public void setBuildings(List<Building> buildings) {
    this.buildings = buildings;
  }

  public ZipCode getZipCode() {
    return zipCode;
  }

  public void setZipCode(ZipCode zipCode) {
    this.zipCode = zipCode;
    if (zipCode != null) {
      this.zipCodeName = zipCode.getName();
    } else {
      this.zipCodeName = null;
    }
  }

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
    if (city != null) {
      this.cityName = city.getName();
    } else {
      this.cityName = null;
    }
  }

  public Country getCountry() {
    return country;
  }

  public void setCountry(Country country) {
    this.country = country;
    if (country != null) {
      this.countryName = country.getName();
    } else {
      this.countryName = null;
    }
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
    int hash = super.hashCode();
    hash = 29 * hash + Objects.hashCode(this.additionalName);
    hash = 29 * hash + Objects.hashCode(this.cityName);
    hash = 29 * hash + Objects.hashCode(this.countryName);
    hash = 29 * hash + Objects.hashCode(this.zipCodeName);
    hash = 29 * hash + Objects.hashCode(this.zipCode);
    hash = 29 * hash + Objects.hashCode(this.city);
    hash = 29 * hash + Objects.hashCode(this.country);
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
    final Address other = (Address) obj;
    if (!Objects.equals(this.additionalName, other.additionalName)) {
      return false;
    }
    if (!Objects.equals(this.cityName, other.cityName)) {
      return false;
    }
    if (!Objects.equals(this.countryName, other.countryName)) {
      return false;
    }
    if (!Objects.equals(this.zipCodeName, other.zipCodeName)) {
      return false;
    }
    if (!Objects.equals(this.zipCode, other.zipCode)) {
      return false;
    }
    if (!Objects.equals(this.city, other.city)) {
      return false;
    }
    if (!Objects.equals(this.country, other.country)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "Address{" + super.toString() + ", additionalName=" + additionalName + ", cityName=" + cityName + ", countryName=" + countryName + ", zipCodeName=" + zipCodeName + ", zipCode=" + zipCode + ", city=" + city + ", country=" + country + '}';
  }

}
