package de.gammadata.microservices.addressrs.addresses.boundary;

import de.gammadata.microservices.addressrs.addresses.control.AbstractCrudControllerArquillianTest;
import de.gammadata.microservices.addressrs.addresses.control.StreetCrudController;
import de.gammadata.microservices.addressrs.addresses.control.CityCrudController;
import de.gammadata.microservices.addressrs.addresses.control.CountryCrudController;
import de.gammadata.microservices.addressrs.addresses.control.TestEntityProvider;
import de.gammadata.microservices.addressrs.addresses.control.ZipCodeCrudController;
import de.gammadata.microservices.addressrs.addresses.entity.Street;
import java.net.URI;
import java.net.URL;
import javax.ejb.EJB;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertNotNull;

/**
 *
 * @author gfr
 */
@RunWith(Arquillian.class)
public class StreetResourceRestArquillianIT extends StreetResourceRestStIT {

  @EJB
  StreetCrudController adrController;
  @EJB
  CountryCrudController countryController;
  @EJB
  ZipCodeCrudController zipCodeController;
  @EJB
  CityCrudController cityController;

  @ArquillianResource
  private URL base;

// Deployment will be doene with the suite deployment plugin
//  @Deployment
//  public static WebArchive createDeployment() {
//    return DeploymentLoaderArquillianIT.createDeployment();
//  }

  /**
   *
   * @throws Exception
   */
  @Before
  @Override
  public void setUp() throws Exception {
    if (base == null) {
      throw new RuntimeException("no base URL injeted");
    }
    AbstractCrudControllerArquillianTest.deleteAllEntities(adrController, zipCodeController, cityController, countryController);

    System.out.println("Arquillian Tests using URL = " + base.toExternalForm());
    webTarget = client.target(URI.create(new URL(base, "api"+StreetResource.PATH).toExternalForm()));
  }

  /**
   *
   */
  @After
  public void tearDown() {
    AbstractCrudControllerArquillianTest.deleteAllEntities(adrController, zipCodeController, cityController, countryController);
  }

  /**
   *
   */
  @Test
  public void testRelations() {
    System.out.println("testRelations()");
    System.out.println("saveOrUpdateAddress");
    //Create Street
    Street adrReq = TestEntityProvider.createAdressWithAllEntities();
    int bCount = adrReq.getBuildings().size();
    Response response = webTarget
            .request(MediaType.APPLICATION_JSON_TYPE)
            .post(Entity.entity(adrReq, MediaType.APPLICATION_JSON_TYPE));
    checkResponse(response);
    System.out.println(response);
    Street res = response.readEntity(Street.class);

    TestEntityProvider.setBasePropertiesForEquals(adrReq, res);

    assertNotNull("unexpected null result for address", res);
    assertNotNull("unexpected null result for address.id", res.getId());

    WebTarget getTarget = webTarget.path(res.getId().toString());
    response = getTarget
            .request(MediaType.APPLICATION_JSON).get();
    Street resGet = response.readEntity(Street.class);
    
    assertNotNull("unexpected null result for read address", resGet);
    assertNotNull("unexpected null result for read address.id", resGet.getId());

    assertNotNull("unexpected null result for address.city", resGet.getCity());
    assertNotNull("unexpected null resul fore address.zipCode", resGet.getZipCode());
    assertNotNull("unexpected null result for address.zipCode.country", resGet.getZipCode().getCountry());
    assertNotNull("unexpected null result for address.country", resGet.getCountry());

    assertNotNull("unexpected null for getCity().getId()", resGet.getCity().getId());
    assertNotNull("unexpected null getZipCode().getId()", resGet.getZipCode().getId());
    Assert.assertNotNull("unexpected null getBuildings()", resGet.getBuildings());
    Assert.assertEquals("getBuildings().size() don't match", bCount, resGet.getBuildings().size());
    System.out.println(resGet.getBuildings());
  }
}
