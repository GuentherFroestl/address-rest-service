package de.gammadata.microservices.addressrs.addresses.entity;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author gfr
 */
public class StreetBasics extends BaseEntity {

  static final long serialVersionUID = 1l;

  private String additionalName;
  private Long cityId;
  private String cityName;
  private Long countryId;
  private String countryName;
  private Long zipCodeId;
  private String zipCodeName;

  public StreetBasics() {
  }

  /**
   *
   * @param id
   * @param version
   * @param name
   * @param modified
   * @param additionalName
   * @param cityId
   * @param cityName
   * @param countryId
   * @param countryName
   * @param zipCodeId
   * @param zipCodeName
   */
  public StreetBasics(Long id, Integer version, String name, Date modified, String additionalName,
          Long cityId, String cityName, Long countryId, String countryName, Long zipCodeId, String zipCodeName) {
    super(id, version, name, modified);
    this.additionalName = additionalName;
    this.cityId = cityId;
    this.cityName = cityName;
    this.countryId = countryId;
    this.countryName = countryName;
    this.zipCodeId = zipCodeId;
    this.zipCodeName = zipCodeName;

  }

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
  public Long getCityId() {
    return cityId;
  }

  /**
   *
   * @param cityId
   */
  public void setCityId(Long cityId) {
    this.cityId = cityId;
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
  public Long getCountryId() {
    return countryId;
  }

  /**
   *
   * @param countryId
   */
  public void setCountryId(Long countryId) {
    this.countryId = countryId;
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
  public Long getZipCodeId() {
    return zipCodeId;
  }

  /**
   *
   * @param zipCodeId
   */
  public void setZipCodeId(Long zipCodeId) {
    this.zipCodeId = zipCodeId;
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
    hash = 41 * hash + Objects.hashCode(this.additionalName);
    hash = 41 * hash + Objects.hashCode(this.cityId);
    hash = 41 * hash + Objects.hashCode(this.cityName);
    hash = 41 * hash + Objects.hashCode(this.countryId);
    hash = 41 * hash + Objects.hashCode(this.countryName);
    hash = 41 * hash + Objects.hashCode(this.zipCodeId);
    hash = 41 * hash + Objects.hashCode(this.zipCodeName);
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
    final StreetBasics other = (StreetBasics) obj;
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
    if (!Objects.equals(this.cityId, other.cityId)) {
      return false;
    }
    if (!Objects.equals(this.countryId, other.countryId)) {
      return false;
    }
    return Objects.equals(this.zipCodeId, other.zipCodeId);
  }

  @Override
  public String toString() {
    return "StreetBasics{" + super.toString() + ", additionalName=" + additionalName + ", cityId=" + cityId + ", cityName=" + cityName + ", countryId=" + countryId + ", countryName=" + countryName + ", zipCodeId=" + zipCodeId + ", zipCodeName=" + zipCodeName + '}';
  }

}
