package de.gammadata.microservices.addressrs.addresses.entity;

import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author gfr
 */
@Entity
public class Address extends BaseEntity {

  private static final long serialVersionUID = 1L;
  @Column
  private String additionalName;
  @Column
  private String number;

  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private ZipCode zipCode;

  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private City city;

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

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 67 * hash + Objects.hashCode(this.additionalName);
    hash = 67 * hash + Objects.hashCode(this.number);
    hash = 67 * hash + Objects.hashCode(this.zipCode);
    hash = 67 * hash + Objects.hashCode(this.city);
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
    return true;
  }

  @Override
  public String toString() {
    return "Address{" + super.toString() + ", additionalName=" + additionalName + ", number=" + number + ", zipCode=" + zipCode + ", city=" + city + '}';
  }

}
