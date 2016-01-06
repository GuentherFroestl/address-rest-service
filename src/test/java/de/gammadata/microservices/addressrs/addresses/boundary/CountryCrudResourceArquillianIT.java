package de.gammadata.microservices.addressrs.addresses.boundary;

import de.gammadata.microservices.addressrs.addresses.control.*;
import de.gammadata.microservices.addressrs.addresses.entity.Country;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;
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
import static org.hamcrest.CoreMatchers.is;

/**
 *
 * @author gfr
 */
@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CountryCrudResourceArquillianIT {

  private Long testDate;
  private Long entityId;
  private Country entityCreated;
  private Country entitySaved;

  @EJB
  AddressCrudController adrController;
  @EJB
  CountryCrudController countryController;
  @EJB
  ZipCodeCrudController zipCodeController;
  @EJB
  CityCrudController cityController;

  @Inject
  CountriesResource instance;

  public CountryCrudResourceArquillianIT() {
  }

// Deployment will be doene with the suite deployment plugin
//  @Deployment
//  public static WebArchive createDeployment() {
//    return DeploymentLoaderArquillianIT.createDeployment();
//  }
  @BeforeClass
  public static void setUpClass() {

  }

  @AfterClass
  public static void tearDownClass() {
  }

  @Before
  public void setUp() {
    assertNotNull("CountryCrudController not injected", instance);
    TestEntityProvider.deleteAllEntities(adrController, zipCodeController, cityController, countryController);
    
    testDate = new Date().getTime();
    entityCreated = TestEntityProvider.createCountry();
    entitySaved = instance.saveOrUpdateEntity(entityCreated);
    assertNotNull("country not saved, null result", entitySaved);
    System.out.println(entitySaved);
    assertNotNull("no id generated", entitySaved.getId());
    assertNotNull("no timestap generated", entitySaved.getModified());
    TestEntityProvider.setIdAndVersion(entityCreated, entitySaved);
    entityId = entitySaved.getId();
  }

  @After
  public void tearDown() {
    TestEntityProvider.deleteAllEntities(adrController, zipCodeController, cityController, countryController);
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
    CountriesResource testee = instance;
    System.out.println("get");
    assertNotNull("no id generated", entityId);
    Country result = testee.getEntity(entityId);
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
}
