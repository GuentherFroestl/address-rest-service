package de.gammadata.microservices.addressrs.addresses.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author gfr
 */
@Entity
public class ZipCode extends BaseEntity {

  private static final long serialVersionUID = 1L;
  @Column
  private String code;

  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private Country country;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Country getCountry() {
    return country;
  }

  public void setCountry(Country country) {
    this.country = country;
  }

  @Override
  public String toString() {
    return "ZipCode{" + super.toString() + ", code=" + code + ", country=" + country + '}';
  }
}
