package de.gammadata.microservices.addressrs.addresses.control;

import de.gammadata.microservices.addressrs.addresses.entity.ZipCode;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 *
 * @author gfr
 */
public class ZipCodeEntityListener extends BaseEntityListener {

  /**
   *
   * @param entity
   */
  @PrePersist
  public void prePersist(ZipCode entity) {
    super.prePersist(entity);
    updateMirrorsFields(entity);
  }

  /**
   *
   * @param entity
   */
  @PreUpdate
  public void preUpdate(ZipCode entity) {
    super.preUpdate(entity);
    updateMirrorsFields(entity);
  }

  private void updateMirrorsFields(ZipCode entity) {
    if (entity != null) {
      if (entity.getCountry() != null) {
        entity.setCountryName(entity.getCountry().getName());
      } else {
        entity.setCountryName(null);
      }
    }

  }

}
