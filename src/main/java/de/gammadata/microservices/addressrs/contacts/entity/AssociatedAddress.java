package de.gammadata.microservices.addressrs.contacts.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import de.gammadata.microservices.addressrs.addresses.entity.Building;
import de.gammadata.microservices.addressrs.common.control.BaseEntityListener;
import de.gammadata.microservices.addressrs.common.entity.EntityWithValidity;
import java.util.Objects;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import static javax.persistence.FetchType.LAZY;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author gfr
 */
@Entity
@EntityListeners({BaseEntityListener.class})
@Table(name = "ASSOCIATED_ADDRESS")
public class AssociatedAddress extends EntityWithValidity {

  private String qualifier;

  @OneToOne(cascade = {PERSIST, MERGE}, fetch = LAZY)
  @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID")
  private Building address;

  @Column(name = "ADDRESS_ID", insertable = false, updatable = false)
  private Long addressId;

  @JsonBackReference
  @ManyToOne(cascade = {PERSIST, MERGE}, fetch = LAZY)
  @JoinColumn(name = "CONTACT_ID", referencedColumnName = "ID")
  private Contact contact;

  @Column(name = "CONTACT_ID", insertable = false, updatable = false)
  private Long contactId;

  public String getQualifier() {
    return qualifier;
  }

  public void setQualifier(String qualifier) {
    this.qualifier = qualifier;
  }

  public Building getAddress() {
    return address;
  }

  public void setAddress(Building address) {
    this.address = address;
  }

  public Long getAddressId() {
    return addressId;
  }

  public void setAddressId(Long addressId) {
    this.addressId = addressId;
  }

  public Contact getContact() {
    return contact;
  }

  public void setContact(Contact contact) {
    this.contact = contact;
  }

  public Long getContactId() {
    return contactId;
  }

  public void setContactId(Long contactId) {
    this.contactId = contactId;
  }

  @Override
  public int hashCode() {
    int hash = super.hashCode();
    hash = 41 * hash + Objects.hashCode(this.qualifier);
    hash = 41 * hash + Objects.hashCode(this.addressId);
    hash = 41 * hash + Objects.hashCode(this.contactId);
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
    final AssociatedAddress other = (AssociatedAddress) obj;
    if (!Objects.equals(this.qualifier, other.qualifier)) {
      return false;
    }
    if (!Objects.equals(this.addressId, other.addressId)) {
      return false;
    }
    return Objects.equals(this.contactId, other.contactId);
  }

  @Override
  public String toString() {
    return "AssociatedAddress{" + super.toString() + ", qualifier=" + qualifier + ", addressId=" + addressId + ", contactId=" + contactId + '}';
  }

}
