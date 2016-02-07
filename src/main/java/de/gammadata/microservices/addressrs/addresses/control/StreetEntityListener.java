package de.gammadata.microservices.addressrs.addresses.control;

import de.gammadata.microservices.addressrs.common.control.BaseEntityListener;
import de.gammadata.microservices.addressrs.addresses.entity.Building;
import de.gammadata.microservices.addressrs.addresses.entity.Street;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * Update Mirror Fields before updateing entity.
 *
 * @author gfr
 */
public class StreetEntityListener extends BaseEntityListener {

  /**
   *
   * @param entity
   */
  @PrePersist
  public void prePersist(Street entity) {
    super.prePersist(entity);
    updateBuildings(entity);
    updateMirrorsFields(entity);
  }

  /**
   *
   * @param entity
   */
  @PreUpdate
  public void preUpdate(Street entity) {
    super.preUpdate(entity);
    updateBuildings(entity);
    updateMirrorsFields(entity);
  }

  private void updateBuildings(Street entity) {
    if (entity != null && entity.getBuildings() != null && !entity.getBuildings().isEmpty()) {
      for (Building b : entity.getBuildings()) {
        b.setStreet(entity);
      }
    }
  }

  private void updateMirrorsFields(Street entity) {
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
