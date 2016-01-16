package de.gammadata.microservices.addressrs.addresses.control;

import de.gammadata.microservices.addressrs.addresses.entity.BaseEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.junit.Assert;
import org.junit.BeforeClass;

/**
 *
 * @author gfr
 */
public class AbstractEntityJpaTest {

  /**
   *
   */
  protected static EntityManagerFactory emFactory;

  /**
   *
   */
  protected static EntityManager em;

  /**
   *
   */
  @BeforeClass
  public static synchronized void setUpClass() {
    //Creating Entity Manager
    if (em == null) {
      emFactory = Persistence.createEntityManagerFactory("addresstest");
      em = emFactory.createEntityManager();
    }
  }

  /**
   *
   * @param cl
   * @param em
   */
  public static void deleteEntities(Class cl,EntityManager em) {
    TypedQuery<BaseEntity> query = em.createQuery("Select t from "
            + cl.getSimpleName() + " t",
            cl);
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    List<BaseEntity> li = query.getResultList();
    if (li != null && !li.isEmpty()) {
      for (BaseEntity en : li) {
        em.remove(en);
      }
    }
    tx.commit();

    List<BaseEntity> liDel = query.getResultList();
    Assert.assertTrue("ENtities not deleted for " + cl.getSimpleName(), liDel.isEmpty());
  }

}
