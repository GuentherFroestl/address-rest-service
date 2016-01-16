package de.gammadata.microservices.addressrs.addresses.control;

import de.gammadata.microservices.addressrs.addresses.entity.Address;
import de.gammadata.microservices.addressrs.addresses.entity.Building;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * Update Mirror Fields before updateing entity.
 *
 * @author gfr
 */
public class AddressEntityListener extends BaseEntityListener {

  /**
   *
   * @param entity
   */
  @PrePersist
  public void prePersist(Address entity) {
    super.prePersist(entity);
    updateBuildings(entity);
    updateMirrorsFields(entity);
  }

  /**
   *
   * @param entity
   */
  @PreUpdate
  public void preUpdate(Address entity) {
    super.preUpdate(entity);
    updateBuildings(entity);
    updateMirrorsFields(entity);
  }

  private void updateBuildings(Address entity) {
    if (entity != null && entity.getBuildings() != null && !entity.getBuildings().isEmpty()) {
      for (Building b : entity.getBuildings()) {
        b.setAddress(entity);
      }
    }
  }

  private void updateMirrorsFields(Address entity) {
    if (entity != null) {
      if (entity.getCity() != null) {
        entity.setCityName(entity.getCity().getName());
      }
      if (entity.getCountry() != null) {
        entity.setCountryName(entity.getCountry().getName());
      }
      if (entity.getZipCode() != null) {
        entity.setZipCodeName(entity.getZipCode().getName());
      }
    }
  }
}
