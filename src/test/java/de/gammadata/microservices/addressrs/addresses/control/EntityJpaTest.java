package de.gammadata.microservices.addressrs.addresses.control;

import de.gammadata.microservices.addressrs.addresses.entity.Address;
import de.gammadata.microservices.addressrs.addresses.entity.City;
import de.gammadata.microservices.addressrs.addresses.entity.Country;
import de.gammadata.microservices.addressrs.addresses.entity.ZipCode;
import java.util.Date;
import javax.persistence.EntityTransaction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author gfr
 */
public class EntityJpaTest extends AbstractEntityJpaTest {

  public EntityJpaTest() {
  }

  @Before
  public void setUp() {

  }

  @After
  public void tearDown() {

  }

  @Test
  public void testAddress() {
    Address adr = new Address();
    adr.setName("name");
    adr.setAdditionalName("additional name");
    adr.setNumber("number");
    adr.setValidFrom(new Date());
    adr.setValidUntil(new Date());
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    em.persist(adr);
    tx.commit();
    Assert.assertTrue("No ID for Address", adr.getId() != null);
    System.out.println("Got ID = " + adr.getId());
    Address res = em.find(Address.class, adr.getId());
    Assert.assertNotNull("unexpected null result", res);
    Assert.assertEquals("Object are not equal", adr, res);
  }

  @Test
  public void testCity() {
    City city = new City();
    city.setName("city");
    city.setValidFrom(new Date());
    city.setValidUntil(new Date());
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    em.persist(city);
    tx.commit();
    Assert.assertTrue("No ID for Entity", city.getId() != null);
    System.out.println("Got ID = " + city.getId());
    City res = em.find(City.class, city.getId());
    Assert.assertNotNull("unexpected null result", res);
    Assert.assertEquals("Object are not equal", city, res);
  }

  @Test
  public void testZipCode() {
    ZipCode zip = new ZipCode();
    zip.setName("zip");
    zip.setCode("zipcode");
    zip.setValidFrom(new Date());
    zip.setValidUntil(new Date());
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    em.persist(zip);
    tx.commit();
    Assert.assertTrue("No ID for Entity", zip.getId() != null);
    System.out.println("Got ID = " + zip.getId());
    ZipCode res = em.find(ZipCode.class, zip.getId());
    Assert.assertNotNull("unexpected null result", res);
    Assert.assertEquals("Object are not equal", zip, res);
  }

  @Test
  public void testCountry() {
    Country country = new Country();
    country.setName("country");
    country.setIso2CountryCode("DE");
    country.setIso3CountryCode("DEU");
    country.setIsoNumber(123);
    country.setValidFrom(new Date());
    country.setValidUntil(new Date());
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

  @Test(expected = javax.persistence.RollbackException.class)
  public void testCountryValidation() {
    Country country = new Country();
    country.setName("country");
    country.setIso2CountryCode("DE_too_large");
    country.setIso3CountryCode("DEU_too_large");
    country.setIsoNumber(123);
    country.setValidFrom(new Date());
    country.setValidUntil(new Date());
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

  @Test
  public void testRelations() {
    City city = new City();
    city.setName("city");
    city.setValidFrom(new Date());
    city.setValidUntil(new Date());

    ZipCode zip = new ZipCode();
    zip.setName("zip");
    zip.setCode("zipcode");
    zip.setValidFrom(new Date());
    zip.setValidUntil(new Date());

    Country country = new Country();
    country.setName("Austria");
    country.setIso2CountryCode("AT");
    country.setIso3CountryCode("AUT");
    country.setIsoNumber(124);
    country.setValidFrom(new Date());
    country.setValidUntil(new Date());

    Address adr = new Address();
    adr.setName("name");
    adr.setAdditionalName("additional name");
    adr.setNumber("number");
    adr.setValidFrom(new Date());
    adr.setValidUntil(new Date());

    //Relations
    zip.setCountry(country);
    city.setCountry(country);
    adr.setCity(city);
    adr.setZipCode(zip);

    EntityTransaction tx = em.getTransaction();
    tx.begin();
    em.persist(adr);
    tx.commit();

    Address res = em.find(Address.class, adr.getId());
    Assert.assertNotNull("unexpected null result", res);
    Assert.assertNotNull("unexpected null result", res.getCity());
    Assert.assertNotNull("unexpected null result", res.getZipCode());
    Assert.assertNotNull("unexpected null result", res.getZipCode().getCountry());
    System.out.println("de.gammadata.microservices.addressrs.addresses.control.PersistenceUntitTest.testRelations()" + res);
  }
}
