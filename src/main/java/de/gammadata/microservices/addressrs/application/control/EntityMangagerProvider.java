package de.gammadata.microservices.addressrs.application.control;

import java.util.HashMap;
import java.util.Map;
import javax.ejb.Singleton;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author gfr
 */
@Singleton
public class EntityMangagerProvider {

  @PersistenceUnit(name = "address-pu")
  private EntityManagerFactory emf;

  @Produces
  @EntityManagerQualifier(EntityManagerType.MULTI_TENANT)
  public EntityManager entityManager() {
    Map emProps = new HashMap();
    EntityManager em = emf.createEntityManager(emProps);
    return em;
  }
}
