package de.gammadata.microservices.addressrs.addresses.entity;

import de.gammadata.microservices.addressrs.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import de.gammadata.microservices.addressrs.addresses.control.StreetEntityListener;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import org.eclipse.persistence.annotations.PrivateOwned;

/**
 *
 * @author gfr
 */
@Entity
@EntityListeners({StreetEntityListener.class})

@Table(indexes = {
  @Index(name = "ADR_NAME_IDX", columnList = "NAME"),
  @Index(name = "ADR_ADD_NAME_IDX", columnList = "ADDITIONAL_NAME"),
  @Index(name = "ADR_CITY_NAME_IDX", columnList = "CITY_NAME"),
  @Index(name = "ADR_COUNTRY_NAME_IDX", columnList = "COUNTRY_NAME"),
  @Index(name = "ADR_ZIPCODE_NAME_IDX", columnList = "ZIPCODE_NAME")})

@NamedQueries({
  @NamedQuery(name = Street.SIMPLE_SEARCH_QUERY_NAME,
          query = "select e from Street e"
          + Street.WHERE_CLAUSE
  ),
  @NamedQuery(name = Street.SIMPLE_COUNT_QUERY_NAME,
          query = "select count(e) from Street e"
          + Street.WHERE_CLAUSE
  )})

@SqlResultSetMappings({
  @SqlResultSetMapping(
          name = Street.RESULT_SET_MAPPING_NAME,
          classes = @ConstructorResult(
                  targetClass = StreetBasics.class,
                  columns = {
                    @ColumnResult(name = "ID", type = Long.class),
                    @ColumnResult(name = "VERSION", type = Integer.class),
                    @ColumnResult(name = "NAME", type = String.class),
                    @ColumnResult(name = "MODIFIED", type = Date.class),
                    @ColumnResult(name = "ADDITIONAL_NAME", type = String.class),
                    @ColumnResult(name = "CITY_ID", type = Long.class),
                    @ColumnResult(name = "CITY_NAME", type = String.class),
                    @ColumnResult(name = "COUNTRY_ID", type = Long.class),
                    @ColumnResult(name = "COUNTRY_NAME", type = String.class),
                    @ColumnResult(name = "ZIPCODE_ID", type = Long.class),
                    @ColumnResult(name = "ZIPCODE_NAME", type = String.class)
                  }))
})
public class Street extends BaseEntity {

  /**
   *
   */
  public static final String SIMPLE_SEARCH_QUERY_NAME = "Street_simpleSearchQuery";

  /**
   *
   */
  public static final String SIMPLE_COUNT_QUERY_NAME = "Street_simpleSearchCount";

  /**
   *
   */
  public static final String RESULT_SET_MAPPING_NAME = "StreetsBasicsContructor";

  /**
   *
   */
  public static final String WHERE_CLAUSE = " where"
          + " LOWER(e.name) like :" + BaseEntity.SIMPLE_SEARCH_QUERY_PARAMETER
          + " OR LOWER(e.additionalName) like :" + BaseEntity.SIMPLE_SEARCH_QUERY_PARAMETER
          + " OR LOWER(e.cityName) like :" + BaseEntity.SIMPLE_SEARCH_QUERY_PARAMETER
          + " OR LOWER(e.countryName) like :" + BaseEntity.SIMPLE_SEARCH_QUERY_PARAMETER
          + " OR LOWER(e.zipCodeName) like :" + BaseEntity.SIMPLE_SEARCH_QUERY_PARAMETER;

  /**
   *
   */
  public static final String WHERE_CLAUSE_NATIVE = " where"
          + " LOWER(e.name) like ?1"
          + " OR LOWER(e.ADDITIONAL_NAME) like ?1"
          + " OR LOWER(e.CITY_NAME) like ?1"
          + " OR LOWER(e.COUNTRY_NAME) like ?1"
          + " OR LOWER(e.ZIPCODE_NAME) like ?1";

  /**
   *
   */
  public static final String CITY_WHERE_CLAUSE_NATIVE = " where"
          + " e.CITY_ID = ?1"
          + " AND ("
          + " LOWER(e.name) like ?2"
          + " OR LOWER(e.ADDITIONAL_NAME) like ?2"
          + " )";
  
  /**
   *
   */
  public static final String ZIPCODE_WHERE_CLAUSE_NATIVE = " where"
          + " e.ZIPCODE_ID = ?1"
          + " AND ("
          + " LOWER(e.name) like ?2"
          + " OR LOWER(e.ADDITIONAL_NAME) like ?2"
          + " )";

  /**
   *
   */
  public static final String NATIVE_SEARCH_COULMS = "select e.ID, e.VERSION, e.MODIFIED, e.NAME, e.ADDITIONAL_NAME,"
          + " e.CITY_ID, e.CITY_NAME, e.COUNTRY_NAME, e.COUNTRY_ID, e.ZIPCODE_NAME, e.ZIPCODE_ID from "
          + TENANT_SCHEMA_PLACEHOLDER
          + ".Street e";

  /**
   *
   */
  public static final String NATIVE_SEARCH_QUERY = NATIVE_SEARCH_COULMS + WHERE_CLAUSE_NATIVE;

  /**
   *
   */
  public static final String NATIVE_SEARCH_QUERY_CITY = NATIVE_SEARCH_COULMS + CITY_WHERE_CLAUSE_NATIVE;

  /**
   *
   */
  public static final String NATIVE_SEARCH_QUERY_ZIPCODE = NATIVE_SEARCH_COULMS + ZIPCODE_WHERE_CLAUSE_NATIVE;

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

  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinColumn(name = "ZIPCODE_ID")
  private ZipCode zipCode;

  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinColumn(name = "CITY_ID")
  private City city;

  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinColumn(name = "COUNTRY_ID")
  private Country country;

  /**
   *
   * @return
   */
  public String getAdditionalName() {
    return additionalName;
  }

  /**
   *
   * @param additionalName
   */
  public void setAdditionalName(String additionalName) {
    this.additionalName = additionalName;
  }

  /**
   *
   * @return
   */
  public ZipCode getZipCode() {
    return zipCode;
  }

  /**
   *
   * @param zipCode
   */
  public void setZipCode(ZipCode zipCode) {
    this.zipCode = zipCode;
    if (zipCode != null) {
      this.zipCodeName = zipCode.getName();
    } else {
      this.zipCodeName = null;
    }
  }

  /**
   *
   * @return
   */
  public City getCity() {
    return city;
  }

  /**
   *
   * @param city
   */
  public void setCity(City city) {
    this.city = city;
    if (city != null) {
      this.cityName = city.getName();
    } else {
      this.cityName = null;
    }
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
    } else {
      this.countryName = null;
    }
  }

  /**
   *
   * @return
   */
  public String getCityName() {
    return cityName;
  }

  /**
   *
   * @param cityName
   */
  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

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
  public String getZipCodeName() {
    return zipCodeName;
  }

  /**
   *
   * @param zipCodeName
   */
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
    final Street other = (Street) obj;
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
    return Objects.equals(this.country, other.country);
  }

  @Override
  public String toString() {
    return "Street{" + super.toString() + ", additionalName=" + additionalName + ", cityName=" + cityName + ", countryName=" + countryName + ", zipCodeName=" + zipCodeName + ", zipCode=" + zipCode + ", city=" + city + ", country=" + country + '}';
  }

}
