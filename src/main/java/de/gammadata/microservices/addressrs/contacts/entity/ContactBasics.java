package de.gammadata.microservices.addressrs.contacts.entity;

import de.gammadata.microservices.addressrs.common.entity.BaseEntity;
import java.util.Date;

/**
 * Basic properties for Contact, suited for list views.
 *
 * @author gfr
 */
public class ContactBasics extends BaseEntity {

  private String type;
  private String gender;
  private String additionalName;
  private String firstName;
  private String registrationNumber;
  private Long preferedAddressId;
  private Long salutationId;
  private Long preferedCommunicationId;

  public ContactBasics() {
  }

  public ContactBasics(Long id, Integer version, String name, Date modified,
          String type, String gender, String additionalName,
          String firstName, String registrationNumber, Long preferedAddressId,
          Long salutationId, Long preferedCommunicationId) {

    super(id, version, name, modified);
    this.type = type;
    this.gender = gender;
    this.additionalName = additionalName;
    this.firstName = firstName;
    this.registrationNumber = registrationNumber;
    this.preferedAddressId = preferedAddressId;
    this.salutationId = salutationId;
    this.preferedCommunicationId = preferedCommunicationId;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
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

  public String getRegistrationNumber() {
    return registrationNumber;
  }

  public void setRegistrationNumber(String registrationNumber) {
    this.registrationNumber = registrationNumber;
  }

  public Long getPreferedAddressId() {
    return preferedAddressId;
  }

  public void setPreferedAddressId(Long preferedAddressId) {
    this.preferedAddressId = preferedAddressId;
  }

  public Long getSalutationId() {
    return salutationId;
  }

  public void setSalutationId(Long salutationId) {
    this.salutationId = salutationId;
  }

  public Long getPreferedCommunicationId() {
    return preferedCommunicationId;
  }

  public void setPreferedCommunicationId(Long preferedCommunicationId) {
    this.preferedCommunicationId = preferedCommunicationId;
  }

}