package de.gammadata.microservices.addressrs.addresses.control;

import de.gammadata.microservices.addressrs.addresses.entity.Country;
import java.util.List;
import javax.ejb.EJB;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

/**
 *
 * @author gfr
 */
@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CountryCrudControllerArquillianIT {

  private Long entityId;
  private Country entityCreated;
  private Country entitySaved;

  @EJB
  StreetCrudController adrController;
  @EJB
  CountryCrudController countryController;
  @EJB
  ZipCodeCrudController zipCodeController;
  @EJB
  CityCrudController cityController;

  @EJB
  CountryCrudController instance;

  /**
   *
   */
  public CountryCrudControllerArquillianIT() {
  }

// Deployment will be doene with the suite deployment plugin
//  @Deployment
//  public static WebArchive createDeployment() {
//    return DeploymentLoaderArquillianIT.createDeployment();
//  }

  /**
   *
   */
  @BeforeClass
  public static void setUpClass() {

  }

  /**
   *
   */
  @AfterClass
  public static void tearDownClass() {
  }

  /**
   *
   */
  @Before
  public void setUp() {
    assertNotNull("CountryCrudController not injected", instance);
    AbstractCrudControllerArquillianTest.deleteAllEntities(adrController, zipCodeController, cityController, countryController);

    entityCreated = TestEntityProvider.createCountry();
    entitySaved = instance.saveOrUpdateEntity(entityCreated);
    assertNotNull("country not saved, null result", entitySaved);
    System.out.println(entitySaved);
    assertNotNull("no id generated", entitySaved.getId());
    TestEntityProvider.setBasePropertiesForEquals(entityCreated, entitySaved);
    entityId = entitySaved.getId();
  }

  /**
   *
   */
  @After
  public void tearDown() {
    AbstractCrudControllerArquillianTest.deleteAllEntities(adrController, zipCodeController, cityController, countryController);

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
    List<Country> result = instance.searchEntities(null);
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
    List<Country> result = instance.searchEntities(null);
    assertNotNull("no result", result);
    assertTrue("result list empty", result.size() > 0);
    for (Country c : result) {
      instance.deleteEntity(c.getId());
    }
    List<Country> delResult = instance.searchEntities(null);
    assertTrue("result list empty", delResult.isEmpty());
  }

}
