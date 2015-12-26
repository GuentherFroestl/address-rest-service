package de.gammadata.microservices.addressrs.addresses.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author gfr
 */
@Entity
public class Country extends BaseEntity {

  private static final long serialVersionUID = 1L;
  @Column
  private Integer isoNumber;
  @Column(length = 2)
  private String iso2CountryCode;
  @Column(length = 3)
  private String iso3CountryCode;

  public Country(Integer isoNumber, String iso2CountryCode, String iso3CountryCode,
          String name, Date validFrom, Date validUntil) {
    super(name, validFrom, validUntil);
    this.isoNumber = isoNumber;
    this.iso2CountryCode = iso2CountryCode;
    this.iso3CountryCode = iso3CountryCode;
  }

  public Country() {
  }

  public Integer getIsoNumber() {
    return isoNumber;
  }

  public void setIsoNumber(Integer isoNumber) {
    this.isoNumber = isoNumber;
  }

  public String getIso2CountryCode() {
    return iso2CountryCode;
  }

  public void setIso2CountryCode(String iso2CountryCode) {
    this.iso2CountryCode = iso2CountryCode;
  }

  public String getIso3CountryCode() {
    return iso3CountryCode;
  }

  public void setIso3CountryCode(String iso3CountryCode) {
    this.iso3CountryCode = iso3CountryCode;
  }

  @Override
  public String toString() {
    return "Country{" + super.toString() + ", isoNumber=" + isoNumber + ", iso2CountryCode=" + iso2CountryCode + ", iso3CountryCode=" + iso3CountryCode + '}';
  }

}
