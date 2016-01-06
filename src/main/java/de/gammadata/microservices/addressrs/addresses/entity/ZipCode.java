package de.gammadata.microservices.addressrs.addresses.entity;

import de.gammadata.microservices.addressrs.addresses.control.BaseEntityListener;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author gfr
 */
@Entity
@EntityListeners({BaseEntityListener.class})
@Table(indexes = {
  @Index(name = "ZIP_NAME_IDX", columnList = "NAME")})
public class ZipCode extends BaseEntity {

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
    return "ZipCode{" + super.toString() + ", country=" + country + '}';
  }
}
