package de.gammadata.microservices.addressrs.addresses.control;

import de.gammadata.microservices.addressrs.addresses.boundary.CountriesResource;
import de.gammadata.microservices.addressrs.addresses.entity.Country;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import static org.hamcrest.CoreMatchers.equalTo;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import static org.hamcrest.CoreMatchers.is;

/**
 *
 * @author gfr
 */
@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CountryCrudControllerArquillianIT {

  private Long testDate;
  private Long entityId;
  private Country entityCreated;
  private Country entitySaved;

  public CountryCrudControllerArquillianIT() {
  }

  @EJB
  CountryCrudController instance;

  @Deployment
  public static JavaArchive createDeployment() {
    JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
            .addPackage(CountryCrudController.class.getPackage())
            .addPackage(CountriesResource.class.getPackage())
            .addAsResource("persistence-arquillian.xml", "META-INF/persistence.xml")
            .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    System.out.println(jar.toString(true));
    return jar;
  }

  @BeforeClass
  public static void setUpClass() {

  }

  @AfterClass
  public static void tearDownClass() {
  }

  @Before
  public void setUp() {
    assertNotNull("CountryCrudController not injected", instance);
    testDate = new Date().getTime();
    entityCreated = createCountry(testDate);
    entitySaved = instance.saveOrUpdateEntity(entityCreated);
    assertNotNull("country not saved, null result", entitySaved);
    System.out.println(entitySaved);
    assertNotNull("no id generated", entitySaved.getId());
    setIdAndVersion(entityCreated, entitySaved);
    entityId = entitySaved.getId();
  }

  @After
  public void tearDown() {
  }

  /**
   * Test of saveOrUpdate method, of class CountryCrudController.
   */
  @Test
  public void test1_SaveOrUpdate() {
    System.out.println("saveOrUpdate");
    assertThat(entitySaved, is(equalTo(entityCreated)));

  }

  /**
   * Test of get method, of class CountryCrudController.
   */
  @Test
  public void test2_Get() {
    System.out.println("get");
    assertNotNull("no id generated", entityId);
    Country result = instance.getEntity(entityId);
    assertThat(result, is(equalTo(entitySaved)));
  }

  /**
   * Test of findAll method, of class CountryCrudController.
   *
   */
  @Test
  public void test3_FindAll() {
    System.out.println("findAll");
    List<Country> result = instance.getAllEntities();
    assertNotNull("no result", result);
    assertTrue("result list empty", result.size() > 0);
    Country testEntity = null;
    for (Country c : result) {
      if (c.getId().equals(entitySaved.getId())) {
        testEntity = c;
      }
    }
    assertNotNull("test entity not found in list", testEntity);
    assertThat(testEntity, is(equalTo(entitySaved)));
  }

  /**
   * Test of delete method, of class CountryCrudController.
   */
  @Test
  public void test4_Delete() {
    System.out.println("delete");
    List<Country> result = instance.getAllEntities();
    assertNotNull("no result", result);
    assertTrue("result list empty", result.size() > 0);
    for (Country c : result) {
      instance.deleteEntity(c.getId());
    }
    List<Country> delResult = instance.getAllEntities();
    assertTrue("result list empty", delResult.isEmpty());
  }

  protected Country createCountry(long testDate) {
    Country pCountry = new Country();
    pCountry.setIso2CountryCode("DE");
    pCountry.setIso3CountryCode("DEU");
    pCountry.setIsoNumber(1234);
    pCountry.setName("Deutschland");
    pCountry.setValidFrom(new Date(testDate));
    pCountry.setValidUntil(new Date(testDate));
    return pCountry;
  }

  protected void setIdAndVersion(Country pIn, Country withId) {
    pIn.setId(withId.getId());
    pIn.setVersion(withId.getVersion());
  }

}
