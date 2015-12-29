package de.gammadata.microservices.addressrs.addresses.entity;

import java.util.Date;
import java.util.Objects;
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
