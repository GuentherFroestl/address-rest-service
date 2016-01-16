package de.gammadata.microservices.addressrs.addresses.control;

import de.gammadata.microservices.addressrs.addresses.entity.BaseEntity;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 *
 * @author gfr
 */
public class BaseEntityListener {

  @PersistenceContext(name = "address-pu")
  EntityManager em;

  /**
   *
   * @return
   */
  public EntityManager getEm() {
    return em;
  }

  /**
   *
   * @param entity
   */
  @PreUpdate
  public void preUpdate(BaseEntity entity) {
    stampModification(entity);
  }

  /**
   *
   * @param entity
   */
  @PrePersist
  public void prePersist(BaseEntity entity) {
    stampModification(entity);
  }

  /**
   *
   * @param entity
   */
  public void stampModification(BaseEntity entity) {
    if (entity != null) {
      entity.setModified(new Date());
    }
  }

}
