package de.gammadata.microservices.addressrs.contacts.control;

import de.gammadata.microservices.addressrs.addresses.control.*;
import de.gammadata.microservices.addressrs.addresses.entity.City;
import de.gammadata.microservices.addressrs.addresses.entity.Country;
import de.gammadata.microservices.addressrs.addresses.entity.Street;
import de.gammadata.microservices.addressrs.addresses.entity.ZipCode;
import de.gammadata.microservices.addressrs.contacts.entity.AssociatedAddress;
import de.gammadata.microservices.addressrs.contacts.entity.CommunicationAddress;
import de.gammadata.microservices.addressrs.contacts.entity.Contact;
import de.gammadata.microservices.addressrs.contacts.entity.Salutation;
import javax.persistence.EntityTransaction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author gfr
 */
public class ContactsEntityJpaTest extends AbstractEntityJpaTest {

  /**
   *
   */
  public ContactsEntityJpaTest() {
  }

  /**
   *
   */
  @Before
  public void setUp() {
  }

  /**
   *
   */
  @After
  public void tearDown() {
    deleteEntities(Contact.class, em);
    deleteEntities(Salutation.class, em);
    deleteEntities(CommunicationAddress.class, em);
    deleteEntities(AssociatedAddress.class, em);
  }

 

  @Test
  public void testContact() {
    System.out.println("testContact()");
    Contact expected = TestEntityProvider.createContact();
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    em.persist(expected);
    tx.commit();
    Assert.assertTrue("No ID for Entity", expected.getId() != null);
    System.out.println("Got ID = " + expected.getId());
    Contact res = em.find(Contact.class, expected.getId());
    Assert.assertNotNull("unexpected null result", res);
    Assert.assertEquals("Object are not equal", expected, res);
    Assert.assertNotNull("unexpected null for timestap", res.getModified());
  }

  @Test
  public void testSalutation() {
    System.out.println("testContact()");
    Salutation expected = TestEntityProvider.createSalutation();
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    em.persist(expected);
    tx.commit();
    Assert.assertTrue("No ID for Entity", expected.getId() != null);
    System.out.println("Got ID = " + expected.getId());
    Salutation res = em.find(Salutation.class, expected.getId());
    Assert.assertNotNull("unexpected null result", res);
    Assert.assertEquals("Object are not equal", expected, res);
    Assert.assertNotNull("unexpected null for timestap", res.getModified());
  }

  @Test
  public void testCommunicationAddress() {
    System.out.println("testContact()");
    CommunicationAddress expected = TestEntityProvider.createCommunicationAddress();
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    em.persist(expected);
    tx.commit();
    Assert.assertTrue("No ID for Entity", expected.getId() != null);
    System.out.println("Got ID = " + expected.getId());
    CommunicationAddress res = em.find(CommunicationAddress.class, expected.getId());
    Assert.assertNotNull("unexpected null result", res);
    Assert.assertEquals("Object are not equal", expected, res);
    Assert.assertNotNull("unexpected null for timestap", res.getModified());
  }

  @Test
  public void testAssociatedAddress() {
    System.out.println("testContact()");
    AssociatedAddress expected = TestEntityProvider.createAssociatedAddress();
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    em.persist(expected);
    tx.commit();
    Assert.assertTrue("No ID for Entity", expected.getId() != null);
    System.out.println("Got ID = " + expected.getId());
    AssociatedAddress res = em.find(AssociatedAddress.class, expected.getId());
    Assert.assertNotNull("unexpected null result", res);
    Assert.assertEquals("Object are not equal", expected, res);
    Assert.assertNotNull("unexpected null for timestap", res.getModified());
  }

}
