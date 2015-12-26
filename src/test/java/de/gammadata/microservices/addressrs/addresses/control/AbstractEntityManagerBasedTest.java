package de.gammadata.microservices.addressrs.addresses.control;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 *
 * @author gfr
 */
public class AbstractEntityManagerBasedTest {

  protected static EntityManagerFactory emFactory;
  protected static EntityManager em;

  @BeforeClass
  public static synchronized void setUpClass() {
    //Creating Entity Manager
    if (em == null) {
      emFactory = Persistence.createEntityManagerFactory("addresstest");
      em = emFactory.createEntityManager();
    }
  }

  @AfterClass
  public static void tearDownClass() {
//    if (em != null) {
//      em.close();
//      emFactory.close();
//    }
  }

  public AbstractEntityManagerBasedTest() {
  }

}
