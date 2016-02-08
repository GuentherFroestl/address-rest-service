package de.gammadata.microservices.addressrs.addresses.control;

import static de.gammadata.microservices.addressrs.addresses.control.AbstractEntityJpaTest.em;
import de.gammadata.microservices.addressrs.addresses.entity.City;
import de.gammadata.microservices.addressrs.addresses.entity.Country;
import de.gammadata.microservices.addressrs.addresses.entity.Street;
import de.gammadata.microservices.addressrs.addresses.entity.ZipCode;
import javax.persistence.EntityTransaction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author gfr
 */
public class AdressesEntityJpaTest extends AbstractEntityJpaTest {

  /**
   *
   */
  public AdressesEntityJpaTest() {
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
    deleteEntities(Street.class, em);
    deleteEntities(City.class, em);
    deleteEntities(ZipCode.class, em);
    deleteEntities(Country.class, em);
  }

  /**
   *
   */
  @Test
  public void testAddress() {
    Street adr = new Street();
    adr.setName("name");
    adr.setAdditionalName("additional name");
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    em.persist(adr);
    tx.commit();
    Assert.assertTrue("No ID for Address", adr.getId() != null);
    System.out.println("Got ID = " + adr.getId());
    Street res = em.find(Street.class, adr.getId());
    Assert.assertNotNull("unexpected null result", res);
    Assert.assertEquals("Object are not equal", adr, res);
    Assert.assertNotNull("unexpected null for timestap", res.getModified());
  }

  /**
   *
   */
  @Test
  public void testAddressMultitenancy() {
    Street adr = new Street();
    adr.setName("name2");
    adr.setAdditionalName("additional name2");
    EntityTransaction tx = em.getTransaction();
    tx.begin();
//    em.setProperty("tenant.id", 1);
    em.persist(adr);
    tx.commit();
    Assert.assertTrue("No ID for Address", adr.getId() != null);
    System.out.println("Got ID = " + adr.getId());

    Street res = em.find(Street.class, adr.getId());
    Assert.assertNotNull("unexpected null result", res);
    Assert.assertEquals("Object are not equal", adr, res);
    Assert.assertNotNull("unexpected null for timestap", res.getModified());
  }

  /**
   *
   */
  @Test
  public void testCity() {
    City city = new City();
    city.setName("city");
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    em.persist(city);
    tx.commit();
    Assert.assertTrue("No ID for Entity", city.getId() != null);
    System.out.println("Got ID = " + city.getId());
    City res = em.find(City.class, city.getId());
    Assert.assertNotNull("unexpected null result", res);
    Assert.assertEquals("Object are not equal", city, res);
    Assert.assertNotNull("unexpected null for timestap", res.getModified());
  }

  /**
   *
   */
  @Test
  public void testZipCode() {
    ZipCode zip = new ZipCode();
    zip.setName("zipcode");
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    em.persist(zip);
    tx.commit();
    Assert.assertTrue("No ID for Entity", zip.getId() != null);
    System.out.println("Got ID = " + zip.getId());
    ZipCode res = em.find(ZipCode.class, zip.getId());
    Assert.assertNotNull("unexpected null result", res);
    Assert.assertEquals("Object are not equal", zip, res);
    Assert.assertNotNull("unexpected null for timestap", res.getModified());
  }

  /**
   *
   */
  @Test
  public void testCountry() {
    Country country = new Country();
    country.setName("country");
    country.setIso2CountryCode("DE");
    country.setIso3CountryCode("DEU");
    country.setIsoNumber(123);
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    em.persist(country);
    tx.commit();
    Assert.assertTrue("No ID for Entity", country.getId() != null);
    System.out.println("Got ID = " + country.getId());
    Country res = em.find(Country.class, country.getId());
    Assert.assertNotNull("unexpected null result", res);
    Assert.assertEquals("Object are not equal", country, res);
    Assert.assertNotNull("unexpected null for timestap", res.getModified());
  }
  /**
   *
   */
  @Test(expected = javax.persistence.RollbackException.class)
  public void testCountryValidation() {
    Country country = new Country();
    country.setName("country");
    country.setIso2CountryCode("DE_too_large");
    country.setIso3CountryCode("DEU_too_large");
    country.setIsoNumber(123);
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    em.persist(country);
    tx.commit();
    Assert.assertTrue("No ID for Entity", country.getId() != null);
    System.out.println("Got ID = " + country.getId());
    Country res = em.find(Country.class, country.getId());
    Assert.assertNotNull("unexpected null result", res);
    Assert.assertEquals("Object are not equal", country, res);
  }

  /**
   *
   */
  @Test
  public void testStreetRelations() {
    System.out.println("testRelations()");

    Street adr = TestEntityProvider.createAdressWithAllEntities();
    int bCount = adr.getBuildings().size();

    EntityTransaction tx = em.getTransaction();
    tx.begin();
    em.persist(adr);
    tx.commit();

    Street res = em.find(Street.class, adr.getId());
    Assert.assertNotNull("unexpected null result for address", res);
    Assert.assertNotNull("unexpected null result for address.city", res.getCity());
    Assert.assertNotNull("unexpected null resul fore address.zipCode", res.getZipCode());
    Assert.assertNotNull("unexpected null result for address.zipCode.country", res.getZipCode().getCountry());
    Assert.assertNotNull("unexpected null result for address.country", res.getCountry());
    Assert.assertNotNull("unexpected null for getCity().getId()", res.getCity().getId());
    Assert.assertNotNull("unexpected null getZipCode().getId()", res.getZipCode().getId());
    Assert.assertNotNull("unexpected null getBuildings()", res.getBuildings());
    Assert.assertEquals("getBuildings().size() don't match", bCount, res.getBuildings().size());

  }

}
