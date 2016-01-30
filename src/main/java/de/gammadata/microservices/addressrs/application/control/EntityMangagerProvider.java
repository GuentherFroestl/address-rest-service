package de.gammadata.microservices.addressrs.application.control;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author gfr
 */
@SessionScoped
public class EntityMangagerProvider implements Serializable {

  @PersistenceUnit(name = "address-pu")
  private EntityManagerFactory emf;

  @Produces
  @EntityManagerQualifier(EntityManagerType.PROVIDER)
  public EntityManager entityManager() {
    Map emProps = new HashMap();
    EntityManager em = emf.createEntityManager(emProps);
    return em;
  }
}
