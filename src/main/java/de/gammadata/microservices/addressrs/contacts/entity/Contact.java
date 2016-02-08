package de.gammadata.microservices.addressrs.contacts.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import de.gammadata.microservices.addressrs.addresses.entity.Building;
import de.gammadata.microservices.addressrs.common.control.BaseEntityListener;
import de.gammadata.microservices.addressrs.common.entity.BaseEntity;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import static javax.persistence.FetchType.LAZY;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.eclipse.persistence.annotations.PrivateOwned;

/**
 *
 * @author gfr
 */
@Entity
@EntityListeners({BaseEntityListener.class})
@Table(name = "CONTACTS",
        indexes = {
          @Index(name = "CONTACT_NAME_IDX", columnList = "NAME"),
          @Index(name = "CONTACT_ADDITIONAL_NAME_IDX", columnList = "ADDITIONAL_NAME"),
          @Index(name = "CONTACT_FIRST_NAME_IDX", columnList = "FIRST_NAME"),
          @Index(name = "CONTACT_REGISTRATION_NUMBER_IDX", columnList = "REGISTRATION_NUMBER")
        })
@NamedQueries({
  @NamedQuery(name = Contact.SIMPLE_SEARCH_QUERY_NAME,
          query = "select e from Contact e"
          + Contact.WHERE_CLAUSE
  ),
  @NamedQuery(name = Contact.SIMPLE_COUNT_QUERY_NAME,
          query = "select count(e) from Contact e"
          + Contact.WHERE_CLAUSE
  )})
@SqlResultSetMappings({
  @SqlResultSetMapping(
          name = Contact.RESULT_SET_MAPPING_NAME,
          classes = @ConstructorResult(
                  targetClass = ContactBasics.class,
                  columns = {
                    @ColumnResult(name = "ID", type = Long.class),
                    @ColumnResult(name = "VERSION", type = Integer.class),
                    @ColumnResult(name = "NAME", type = String.class),
                    @ColumnResult(name = "MODIFIED", type = Date.class),
                    @ColumnResult(name = "TYPE", type = String.class),
                    @ColumnResult(name = "GENDER", type = String.class),
                    @ColumnResult(name = "ADDITIONAL_NAME", type = String.class),
                    @ColumnResult(name = "FIRST_NAME", type = String.class),
                    @ColumnResult(name = "REGISTRATION_NUMBER", type = String.class),
                    @ColumnResult(name = "ADDRESS_ID", type = Long.class),
                    @ColumnResult(name = "SALUTAION_ID", type = Long.class),
                    @ColumnResult(name = "COM_ID", type = Long.class)
                  }))
})
public class Contact extends BaseEntity {

  private static final long serialVersionUID = 1L;

  /**
   * Type of the contact.
   */
  public enum TYPE {
    PERSON,
    ORGANISATION
  }

  /**
   * Gender of a Person
   */
  public enum GENDER {
    MALE,
    FEMALE,
    OTHER
  }

  public static final String RESULT_SET_MAPPING_NAME = "ContactBasicsContructor";
  public static final String SIMPLE_SEARCH_QUERY_NAME = "Contact_simpleSearchQuery";
  public static final String SIMPLE_COUNT_QUERY_NAME = "Contact_simpleSearchCount";
  public static final String WHERE_CLAUSE = " where "
          + "LOWER(e.name) like :" + BaseEntity.SIMPLE_SEARCH_QUERY_PARAMETER
          + " OR LOWER(e.additionalName) like :" + BaseEntity.SIMPLE_SEARCH_QUERY_PARAMETER
          + " OR LOWER(e.firstName) like :" + BaseEntity.SIMPLE_SEARCH_QUERY_PARAMETER
          + " OR LOWER(e.registrationNumber) like :" + BaseEntity.SIMPLE_SEARCH_QUERY_PARAMETER;

  public static final String WHERE_CLAUSE_NATIVE = " where"
          + " LOWER(e.NAME) like ?1"
          + " OR LOWER(e.ADDITIONAL_NAME) like ?1"
          + " OR LOWER(e.FIRST_NAME) like ?1"
          + " OR LOWER(e.REGISTRATION_NUMBER) like ?1";
  /**
   * native query string (select part) for multi tenant schema.
   */
  public static final String NATIVE_SEARCH_COULMS = "select e.ID, e.VERSION, e.MODIFIED, e.NAME, e.TYPE,"
          + " e.GENDER, e.ADDITIONAL_NAME, e.FIRST_NAME, e.TITLE, e.REGISTRATION_NUMBER, e.ADDRESS_ID"
          + " SALUTAION_ID, COM_ID"
          + " from " + TENANT_SCHEMA_NAME + ".CONTACTS e";

  public static final String NATIVE_SEARCH_QUERY = NATIVE_SEARCH_COULMS + WHERE_CLAUSE_NATIVE;

  @Column(name = "TYPE")
  @Enumerated(EnumType.STRING)
  private TYPE type;

  @Column(name = "GENDER")
  @Enumerated(EnumType.STRING)
  private GENDER gender;

  @Column(name = "BIRTH_DATE")
  @Temporal(TemporalType.TIMESTAMP)
  private Date creationOrBirthDate;

  @Column(name = "ADDITIONAL_NAME")
  private String additionalName;

  @Column(name = "FIRST_NAME")
  private String firstName;

  @Column(name = "TITLE")
  private String title;

  @Column(name = "REGISTRATION_NUMBER")
  private String registrationNumber;

  @OneToOne(cascade = {PERSIST, MERGE}, fetch = LAZY)
  @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID")
  private Building preferedAddress;

  @Column(name = "ADDRESS_ID", insertable = false, updatable = false)
  private Long preferedAddressId;

  @ManyToOne(cascade = {PERSIST, MERGE}, fetch = LAZY)
  @JoinColumn(name = "SALUTAION_ID", referencedColumnName = "ID")
  private Salutation salutation;

  @Column(name = "SALUTAION_ID", insertable = false, updatable = false)
  private Long salutationId;

  @OneToOne(cascade = {PERSIST, MERGE}, fetch = LAZY)
  @JoinColumn(name = "COM_ID", referencedColumnName = "ID")
  private CommunicationAddress preferedCommunication;

  @Column(name = "COM_ID", insertable = false, updatable = false)
  private Long preferedCommunicationId;

  @JsonManagedReference
  @OneToMany(mappedBy = "contact", cascade = ALL, fetch = LAZY)
  @PrivateOwned
  private List<AssociatedAddress> associatedAddresses;

  @JsonManagedReference
  @OneToMany(mappedBy = "contact", cascade = ALL, fetch = LAZY)
  @PrivateOwned
  private List<CommunicationAddress> associatedCommAddresses;

  public TYPE getType() {
    return type;
  }

  public void setType(TYPE type) {
    this.type = type;
  }

  public GENDER getGender() {
    return gender;
  }

  public void setGender(GENDER gender) {
    this.gender = gender;
  }

  public Date getCreationOrBirthDate() {
    return creationOrBirthDate;
  }

  public void setCreationOrBirthDate(Date creationOrBirthDate) {
    this.creationOrBirthDate = creationOrBirthDate;
  }

  public String getAdditionalName() {
    return additionalName;
  }

  public void setAdditionalName(String additionalName) {
    this.additionalName = additionalName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getRegistrationNumber() {
    return registrationNumber;
  }

  public void setRegistrationNumber(String registrationNumber) {
    this.registrationNumber = registrationNumber;
  }

  public Building getPreferedAddress() {
    return preferedAddress;
  }

  public void setPreferedAddress(Building preferedAddress) {
    this.preferedAddress = preferedAddress;
  }

  public Long getPreferedAddressId() {
    return preferedAddressId;
  }

  public void setPreferedAddressId(Long preferedAddressId) {
    this.preferedAddressId = preferedAddressId;
  }

  public Salutation getSalutation() {
    return salutation;
  }

  public void setSalutation(Salutation salutation) {
    this.salutation = salutation;
  }

  public Long getSalutationId() {
    return salutationId;
  }

  public void setSalutationId(Long salutationId) {
    this.salutationId = salutationId;
  }

  public CommunicationAddress getPreferedCommunication() {
    return preferedCommunication;
  }

  public void setPreferedCommunication(CommunicationAddress preferedCommunication) {
    this.preferedCommunication = preferedCommunication;
  }

  public Long getPreferedCommunicationId() {
    return preferedCommunicationId;
  }

  public void setPreferedCommunicationId(Long preferedCommunicationId) {
    this.preferedCommunicationId = preferedCommunicationId;
  }

  public List<AssociatedAddress> getAssociatedAddresses() {
    return associatedAddresses;
  }

  public void setAssociatedAddresses(List<AssociatedAddress> associatedAddresses) {
    this.associatedAddresses = associatedAddresses;
  }

  public List<CommunicationAddress> getAssociatedCommAddresses() {
    return associatedCommAddresses;
  }

  public void setAssociatedCommAddresses(List<CommunicationAddress> associatedCommAddresses) {
    this.associatedCommAddresses = associatedCommAddresses;
  }

  @Override
  public int hashCode() {
    int hash = super.hashCode();
    hash = 29 * hash + Objects.hashCode(this.type);
    hash = 29 * hash + Objects.hashCode(this.creationOrBirthDate);
    hash = 29 * hash + Objects.hashCode(this.additionalName);
    hash = 29 * hash + Objects.hashCode(this.firstName);
    hash = 29 * hash + Objects.hashCode(this.title);
    hash = 29 * hash + Objects.hashCode(this.registrationNumber);
    hash = 29 * hash + Objects.hashCode(this.preferedAddressId);
    hash = 29 * hash + Objects.hashCode(this.salutationId);
    hash = 29 * hash + Objects.hashCode(this.preferedCommunicationId);
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
    final Contact other = (Contact) obj;
    if (!Objects.equals(this.additionalName, other.additionalName)) {
      return false;
    }
    if (!Objects.equals(this.firstName, other.firstName)) {
      return false;
    }
    if (!Objects.equals(this.title, other.title)) {
      return false;
    }
    if (!Objects.equals(this.registrationNumber, other.registrationNumber)) {
      return false;
    }
    if (this.type != other.type) {
      return false;
    }
    if (!Objects.equals(this.creationOrBirthDate, other.creationOrBirthDate)) {
      return false;
    }
    if (!Objects.equals(this.preferedAddressId, other.preferedAddressId)) {
      return false;
    }
    if (!Objects.equals(this.salutationId, other.salutationId)) {
      return false;
    }
    return Objects.equals(this.preferedCommunicationId, other.preferedCommunicationId);
  }

  @Override
  public String toString() {
    return "Contact{" + super.toString() + ", type=" + type + ", creationOrBirthDate=" + creationOrBirthDate
            + ", additionalName=" + additionalName + ", firstName=" + firstName
            + ", title=" + title + ", registrationNumber=" + registrationNumber
            + ", preferedAddressId=" + preferedAddressId + ", salutationId=" + salutationId
            + ", preferedCommunicationId=" + preferedCommunicationId + '}';
  }

}
