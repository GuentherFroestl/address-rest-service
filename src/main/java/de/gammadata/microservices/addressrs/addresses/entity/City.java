package de.gammadata.microservices.addressrs.addresses.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author gfr
 */
@Entity
public class City extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private Country country;

  public Country getCountry() {
    return country;
  }

  public void setCountry(Country country) {
    this.country = country;
  }

  @Override
  public String toString() {
    return "City{" + super.toString() + country + '}';
  }

}
