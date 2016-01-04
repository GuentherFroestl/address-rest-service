package de.gammadata.microservices.addressrs.addresses.entity;

import de.gammadata.microservices.addressrs.addresses.control.BaseEntityListener;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
  @Index(name = "ZIP_NAME_IDX", columnList = "NAME"),
  @Index(name = "ZIP_CODE_IDX", columnList = "CODE")})
public class ZipCode extends BaseEntity {

  private static final long serialVersionUID = 1L;
  @Column(name = "CODE")
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
