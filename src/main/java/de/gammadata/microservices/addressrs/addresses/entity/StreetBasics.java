package de.gammadata.microservices.addressrs.addresses.entity;

import java.util.Date;

/**
 *
 * @author gfr
 */
public class StreetBasics extends BaseEntity {

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

  private String additionalName;
  private Long cityId;
  private String cityName;
  private Long countryId;
  private String countryName;
  private Long zipCodeId;
  private String zipCodeName;

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
  public String toString() {
    return "StreetBasics{"+ super.toString() + ", additionalName=" + additionalName + ", cityId=" + cityId + ", cityName=" + cityName + ", countryId=" + countryId + ", countryName=" + countryName + ", zipCodeId=" + zipCodeId + ", zipCodeName=" + zipCodeName + '}';
  }
  
  

}
