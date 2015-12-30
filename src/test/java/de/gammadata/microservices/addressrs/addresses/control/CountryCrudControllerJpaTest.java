package de.gammadata.microservices.addressrs.addresses.control;

import de.gammadata.microservices.addressrs.addresses.boundary.CountriesResource;
import de.gammadata.microservices.addressrs.addresses.entity.Country;
import java.util.Date;
import javax.persistence.EntityTransaction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 *
 * @author gfr
 */
public class CountryCrudControllerJpaTest extends AbstractEntityJpaTest {

  public CountryCrudControllerJpaTest() {
  }

  private CountryCrudController testee = spy(new CountryCrudController());
  private CountriesResource  resourceTestee = spy (new CountriesResource());

  @Before
  public void setUp() {
    when(testee.getEm()).thenReturn(em);
    when(resourceTestee.getCrudController()).thenReturn(testee);

  }

  @After
  public void tearDown() {
  }

  /**
   * Test of saveOrUpdateEntity method, of class CountryCrudController.
   */
  @Test
  public void testSaveOrUpdateEntity() {
    System.out.println("saveOrUpdateEntity");
    Country country = new Country();
    country.setName("country");
    country.setIso2CountryCode("DE");
    country.setIso3CountryCode("DEU");
    country.setIsoNumber(123);
    country.setValidFrom(new Date());
    country.setValidUntil(new Date());
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    Country result = testee.saveOrUpdateEntity(country);
    tx.commit();
    Assert.assertTrue("No ID for Entity", result.getId() != null);
    System.out.println("Got ID = " + result.getId());
    Country res2 = em.find(Country.class, result.getId());
    Assert.assertNotNull("unexpected null result", res2);
    Assert.assertEquals("Object are not equal", result, res2);
    Country res3 = resourceTestee.getEntity(result.getId());
    Assert.assertEquals("Object are not equal", result, res3);

  }

  @Test
  public void testEntityClass() {
    System.out.println("getEntityClass");
    Class result = testee.getEntityClass();
    Assert.assertEquals("Class are not as expected", result, Country.class);

  }

  /**
   * Test of getAllEntities method, of class CountryCrudController.
   */
  public void testGetAllEntities() throws Exception {
    System.out.println("getAllEntities");

  }

  /**
   * Test of getEntity method, of class CountryCrudController.
   */
  public void testGetEntity() throws Exception {
    System.out.println("getEntity");

  }

  /**
   * Test of deleteEntity method, of class CountryCrudController.
   */
  public void testDeleteEntity() throws Exception {
    System.out.println("deleteEntity");

  }

  /**
   * Test of getEntityClass method, of class CountryCrudController.
   */
  public void testGetEntityClass() throws Exception {
    System.out.println("getEntityClass");

  }

}
