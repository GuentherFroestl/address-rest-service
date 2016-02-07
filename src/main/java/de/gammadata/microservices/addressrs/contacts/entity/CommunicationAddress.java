package de.gammadata.microservices.addressrs.contacts.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import de.gammadata.microservices.addressrs.common.control.BaseEntityListener;
import de.gammadata.microservices.addressrs.common.entity.EntityWithValidity;
import java.util.Objects;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import static javax.persistence.FetchType.LAZY;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author gfr
 */
@Entity
@EntityListeners({BaseEntityListener.class})
@Table(name = "COM_ADDRESS")
public class CommunicationAddress extends EntityWithValidity {

  public enum TYPE {
    TELEPHONE,
    FAX,
    EMAIL,
    MESSAGING,
    SOCIALMEDIA,
    CHAT,
    OTHER
  }
  @Column(name = "TYPE")
  @Enumerated(EnumType.STRING)
  private TYPE type;

  /**
   * qualifies the com, like business, private, etc.
   */
  @Column(name = "QUALIFIER")
  private String qualifier;

  @JsonBackReference
  @ManyToOne(cascade = {PERSIST, MERGE}, fetch = LAZY)
  @JoinColumn(name = "CONTACT_ID", referencedColumnName = "ID")
  private Contact contact;

  @Column(name = "CONTACT_ID", insertable = false, updatable = false)
  private Long contactId;

  public TYPE getType() {
    return type;
  }

  public void setType(TYPE type) {
    this.type = type;
  }

  public String getQualifier() {
    return qualifier;
  }

  public void setQualifier(String qualifier) {
    this.qualifier = qualifier;
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
    hash = 37 * hash + Objects.hashCode(this.type);
    hash = 37 * hash + Objects.hashCode(this.qualifier);
    hash = 37 * hash + Objects.hashCode(this.contactId);
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
    final CommunicationAddress other = (CommunicationAddress) obj;
    if (!Objects.equals(this.qualifier, other.qualifier)) {
      return false;
    }
    if (this.type != other.type) {
      return false;
    }
    return Objects.equals(this.contactId, other.contactId);
  }

  @Override
  public String toString() {
    return "CommunicationAddress{" + super.toString() + ", type=" + type + ", qualifier=" + qualifier + ", contactId=" + contactId + '}';
  }

}
