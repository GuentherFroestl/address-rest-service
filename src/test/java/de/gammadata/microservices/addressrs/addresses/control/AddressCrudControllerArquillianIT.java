package de.gammadata.microservices.addressrs.addresses.control;

import de.gammadata.microservices.addressrs.addresses.entity.Address;
import de.gammadata.microservices.addressrs.addresses.entity.BaseQuerySpecification;
import de.gammadata.microservices.addressrs.addresses.entity.City;
import de.gammadata.microservices.addressrs.addresses.entity.Country;
import de.gammadata.microservices.addressrs.addresses.entity.ZipCode;
import java.util.List;
import javax.ejb.EJB;
import static org.hamcrest.CoreMatchers.equalTo;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.CoreMatchers.is;

/**
 *
 * @author gfr
 */
@RunWith(Arquillian.class)
public class AddressCrudControllerArquillianIT {


  @EJB
  AddressCrudController adrController;
  @EJB
  CountryCrudController countryController;
  @EJB
  ZipCodeCrudController zipCodeController;
  @EJB
  CityCrudController cityController;

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
    AbstractCrudControllerArquillianTest.deleteAllEntities(adrController, zipCodeController, cityController, countryController);

  }

  @After
  public void tearDown() {
    AbstractCrudControllerArquillianTest.deleteAllEntities(adrController, zipCodeController, cityController, countryController);
  }

  @Test
  public void testRelations() {
    System.out.println("testRelations()");
    assertNotNull("AddressCrudController not injected", adrController);
    City city = new City();
    city.setName("city");

    ZipCode zip = new ZipCode();
    zip.setName("zipcode");

    Country country = TestEntityProvider.createCountry();

    Address adr = new Address();
    adr.setName("name");
    adr.setAdditionalName("additional name");
    adr.setNumber("number");

    //Relations with persist cascade
    zip.setCountry(country);
    city.setCountry(country);
    adr.setCity(city);
    adr.setZipCode(zip);

    adr = adrController.saveOrUpdateEntity(adr);

    Address res = adrController.getEntity(adr.getId());
    assertNotNull("unexpected null result", res);
    assertNotNull("unexpected null result", res.getCity());
    assertNotNull("unexpected null result", res.getZipCode());
    assertNotNull("unexpected null result", res.getZipCode().getCountry());

    assertNotNull("unexpected null for getCity().getId()", res.getCity().getId());
    assertNotNull("unexpected null getZipCode().getId()", res.getZipCode().getId());

    //Test relations on a new address with existing related entities
    Address adr2 = new Address();
    adr2.setName("name 2");
    adr2.setAdditionalName("additional name 2");
    adr2.setNumber("number 2");

    City c = new City();
    c.setId(res.getCity().getId());
    adr2.setCity(c);
    ZipCode z = new ZipCode();
    z.setId(res.getZipCode().getId());
    adr2.setZipCode(z);
    adr2 = adrController.saveOrUpdateEntity(adr2);
    assertThat("City not correctly related", res.getCity(), is(equalTo(adr2.getCity())));
    assertEquals("ZipCode not correctly related", res.getZipCode(), adr2.getZipCode());

    // Test changing a relation with a new entity.
    City c2 = new City();
    c2.setName("City 2");

    adr2.setCity(c2);

    adr2 = adrController.saveOrUpdateEntity(adr2);
    System.out.println(adr2.getCity());
    assertTrue("new City not correctly created, no ID", adr2.getCity().getId() != null);
    assertTrue("new City not correctly created, no ID", adr2.getCity().getId() > 0);
    assertThat("new City not correctly created name is not city 2", adr2.getCity().getName(), is(equalTo("City 2")));

    //Search w/o argument
    List<Address> adrList = adrController.getEntities(new BaseQuerySpecification());
    assertNotNull("unexpected null result list for simple search query", adrList);
    assertThat("simple search count does not match", adrList.size(), is(equalTo(2)));
    Long count = adrController.countEntities(new BaseQuerySpecification());
    assertThat("simple search count does not match", count, is(equalTo(2l)));

    //Search with argument
    adrList = adrController.getEntities(new BaseQuerySpecification(null, null, "name"));
    assertNotNull("unexpected null result list for simple search query", adrList);
    assertThat("simple search count does not match", adrList.size(), is(equalTo(2)));
    count = adrController.countEntities(new BaseQuerySpecification(null, null, "name"));
    assertThat("simple search count does not match", 2l, is(equalTo(count)));

    count = adrController.countEntities(new BaseQuerySpecification(null, null, "name 2"));
    assertThat("simple search count does not match", 1l, is(equalTo(count)));

    count = adrController.countEntities(new BaseQuerySpecification(null, null, "city"));
    assertThat("simple search count for city does not match", count, is(equalTo(2l)));

    count = adrController.countEntities(new BaseQuerySpecification(null, null, "City"));
    assertThat("simple search count for city does not match", count, is(equalTo(2l)));

    adrList = adrController.getEntities(new BaseQuerySpecification(null, null, "City"));
    System.out.println(adrList);

    count = adrController.countEntities(new BaseQuerySpecification(null, null, "City 2"));
    assertThat("simple search count for city does not match", count, is(equalTo(1l)));

  }

}
