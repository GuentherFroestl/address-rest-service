package de.gammadata.microservices.addressrs.addresses.entity;

import java.util.Date;

/**
 *
 * @author gfr
 */
public class AddressBasics extends BaseEntity {

  public AddressBasics(Long id, Integer version, String name, Date modified, String additionalName,
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

  private String additionalName;
  private Long cityId;
  private String cityName;
  private Long countryId;
  private String countryName;
  private Long zipCodeId;
  private String zipCodeName;

  public String getAdditionalName() {
    return additionalName;
  }

  public void setAdditionalName(String additionalName) {
    this.additionalName = additionalName;
  }

  public Long getCityId() {
    return cityId;
  }

  public void setCityId(Long cityId) {
    this.cityId = cityId;
  }

  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  public Long getCountryId() {
    return countryId;
  }

  public void setCountryId(Long countryId) {
    this.countryId = countryId;
  }

  public String getCountryName() {
    return countryName;
  }

  public void setCountryName(String countryName) {
    this.countryName = countryName;
  }

  public Long getZipCodeId() {
    return zipCodeId;
  }

  public void setZipCodeId(Long zipCodeId) {
    this.zipCodeId = zipCodeId;
  }

  public String getZipCodeName() {
    return zipCodeName;
  }

  public void setZipCodeName(String zipCodeName) {
    this.zipCodeName = zipCodeName;
  }

  @Override
  public String toString() {
    return "AddressBasics{"+ super.toString() + ", additionalName=" + additionalName + ", cityId=" + cityId + ", cityName=" + cityName + ", countryId=" + countryId + ", countryName=" + countryName + ", zipCodeId=" + zipCodeId + ", zipCodeName=" + zipCodeName + '}';
  }
  
  

}
