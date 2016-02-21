package de.gammadata.microservices.addressrs.contacts.entity;

import de.gammadata.microservices.addressrs.common.entity.EntityWithValidity;
import java.util.Date;

/**
 * Mapped Class for AssociatedBuildingAddress List view
 *
 * @author gfr
 */
public class AssociatedBasicAddress extends EntityWithValidity {

  private String qualifier;
  private String buildingAddressText;
  private Long contactId;
  private Long buildingAdrId;

  public AssociatedBasicAddress() {
  }

  public AssociatedBasicAddress(Long id, Integer version, String name, Date modified,
          Date validFrom, Date validTo,
          String qualifier, String buildingAddressText, Long contactId, Long buildingAdrId) {
    super(id, version, name, modified, validFrom, validTo);
    this.qualifier = qualifier;
    this.buildingAddressText = buildingAddressText;
    this.contactId = contactId;
    this.buildingAdrId = buildingAdrId;
  }

  public String getQualifier() {
    return qualifier;
  }

  public void setQualifier(String qualifier) {
    this.qualifier = qualifier;
  }

  public String getBuildingAddressText() {
    return buildingAddressText;
  }

  public void setBuildingAddressText(String buildingAddressText) {
    this.buildingAddressText = buildingAddressText;
  }

  public Long getContactId() {
    return contactId;
  }

  public void setContactId(Long contactId) {
    this.contactId = contactId;
  }

  public Long getBuildingAdrId() {
    return buildingAdrId;
  }

  public void setBuildingAdrId(Long buildingAdrId) {
    this.buildingAdrId = buildingAdrId;
  }

}
