package de.gammadata.microservices.addressrs.addresses.control;

import de.gammadata.microservices.addressrs.addresses.entity.Country;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import static org.hamcrest.CoreMatchers.equalTo;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
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

  public CountryCrudControllerArquillianIT() {
  }

  @EJB
  CountryCrudController instance;

  @Deployment
  public static JavaArchive createDeployment() {
    JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
            .addPackage(CountryCrudController.class.getPackage())
            .addAsResource("persistence-arquillian.xml", "META-INF/persistence.xml")
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
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
    assertNotNull("CountryCrudController not injected", instance);
    Country pCountry = createCountry();
    Country result = instance.saveOrUpdate(pCountry);
    assertNotNull("country not saved, null result", result);
    System.out.println(result);
    assertNotNull("no id generated", result.getId());
    setIdAndVersion(pCountry, result);
    assertThat(result, is(equalTo(pCountry)));
  }

  /**
   * Test of findAll method, of class CountryCrudController.
   *
   */
  @Test
  public void test2_FindAll() {

    assertNotNull("CountryCrudController not injected", instance);
    System.out.println("findAll");
    List<Country> result = instance.findAll();
    assertNotNull("no result", result);
    System.out.println(result);
    assertThat(result.size(), is(equalTo(1)));
    Country rc = result.get(0);
    Country expCountry = createCountry();
    System.out.println(expCountry);
    setIdAndVersion(expCountry, rc);
    assertThat(rc, is(equalTo(expCountry)));

  }

  /**
   * Test of get method, of class CountryCrudController.
   */
  public void testGet() throws Exception {
    System.out.println("get");
    Long id = null;
    EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
    CountryCrudController instance = (CountryCrudController) container.getContext().lookup("java:global/classes/CountryCrudController");
    Country expResult = null;
    Country result = instance.get(id);
    assertEquals(expResult, result);
    container.close();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of delete method, of class CountryCrudController.
   */
  public void testDelete() throws Exception {
    System.out.println("delete");
    Long id = null;
    EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
    CountryCrudController instance = (CountryCrudController) container.getContext().lookup("java:global/classes/CountryCrudController");
    instance.delete(id);
    container.close();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getEntityClass method, of class CountryCrudController.
   */
  public void testGetEntityClass() throws Exception {
    System.out.println("getEntityClass");
    EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
    CountryCrudController instance = (CountryCrudController) container.getContext().lookup("java:global/classes/CountryCrudController");
    Class<Country> expResult = null;
    Class<Country> result = instance.getEntityClass();
    assertEquals(expResult, result);
    container.close();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
  private final Long testDate = new Date().getTime();

  protected Country createCountry() {
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
