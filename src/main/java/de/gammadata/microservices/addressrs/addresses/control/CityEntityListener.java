package de.gammadata.microservices.addressrs.addresses.control;

import de.gammadata.microservices.addressrs.addresses.entity.City;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * Update Mirror Fields before updateing entity.
 *
 * @author gfr
 */
public class CityEntityListener extends BaseEntityListener {

  @PrePersist
  public void prePersist(City entity) {
    super.prePersist(entity);
    updateMirrorsFields(entity);
  }

  @PreUpdate
  public void preUpdate(City entity) {
    super.preUpdate(entity);
    updateMirrorsFields(entity);
  }

  private void updateMirrorsFields(City entity) {
    if (entity != null) {
      if (entity.getCountry() != null) {
        entity.setCountryName(entity.getCountry().getName());
      } else {
        entity.setCountryName(null);
      }
    }

  }
}
