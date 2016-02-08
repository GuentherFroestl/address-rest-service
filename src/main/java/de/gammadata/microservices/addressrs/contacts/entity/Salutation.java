package de.gammadata.microservices.addressrs.contacts.entity;

import de.gammadata.microservices.addressrs.common.control.BaseEntityListener;
import de.gammadata.microservices.addressrs.common.entity.BaseEntity;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Index;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author gfr
 */
@Entity
@EntityListeners({BaseEntityListener.class})
@Table(name = "SALUTATIONS",
        indexes = {
          @Index(name = "SALUTATIONS_NAME_IDX", columnList = "NAME"),
          @Index(name = "SALUTATIONS_FOR_LETTER_IDX", columnList = "FOR_LETTER"),
          @Index(name = "SALUTATIONS_FOR_ADDRESS_IDX", columnList = "FOR_ADDRESS")
        })
@NamedQueries({
  @NamedQuery(name = Salutation.SIMPLE_SEARCH_QUERY_NAME,
          query = "select e from Salutation e"
          + Salutation.WHERE_CLAUSE
  ),
  @NamedQuery(name = Salutation.SIMPLE_COUNT_QUERY_NAME,
          query = "select count(e) from Salutation e"
          + Salutation.WHERE_CLAUSE
  )})
public class Salutation extends BaseEntity {

  private static final long serialVersionUID = 1L;

  public static final String SIMPLE_SEARCH_QUERY_NAME = "SalutationsimpleSearchQuery";
  public static final String SIMPLE_COUNT_QUERY_NAME = "Salutation_simpleSearchCount";
  public static final String WHERE_CLAUSE = " where "
          + "LOWER(e.name) like :" + BaseEntity.SIMPLE_SEARCH_QUERY_PARAMETER
          + " OR LOWER(e.forLetter) like :" + BaseEntity.SIMPLE_SEARCH_QUERY_PARAMETER
          + " OR LOWER(e.forAddress) like :" + BaseEntity.SIMPLE_SEARCH_QUERY_PARAMETER;

  @Column(name = "FOR_LETTER")
  private String forLetter;

  @Column(name = "FOR_ADDRESS")
  private String forAddress;

  public String getForLetter() {
    return forLetter;
  }

  public void setForLetter(String forLetter) {
    this.forLetter = forLetter;
  }

  public String getForAddress() {
    return forAddress;
  }

  public void setForAddress(String forAddress) {
    this.forAddress = forAddress;
  }

  @Override
  public int hashCode() {
    int hash = super.hashCode();
    hash = 17 * hash + Objects.hashCode(this.forLetter);
    hash = 17 * hash + Objects.hashCode(this.forAddress);
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
    final Salutation other = (Salutation) obj;
    if (!Objects.equals(this.forLetter, other.forLetter)) {
      return false;
    }
    if (!Objects.equals(this.forAddress, other.forAddress)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "Salutation{" + super.toString() + ", forLetter=" + forLetter + ", forAddress=" + forAddress + '}';
  }

}
