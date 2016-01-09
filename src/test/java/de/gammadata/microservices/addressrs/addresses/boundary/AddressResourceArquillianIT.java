package de.gammadata.microservices.addressrs.addresses.boundary;

import de.gammadata.microservices.addressrs.addresses.control.AddressCrudController;
import de.gammadata.microservices.addressrs.addresses.control.CityCrudController;
import de.gammadata.microservices.addressrs.addresses.control.CountryCrudController;
import de.gammadata.microservices.addressrs.addresses.control.TestEntityProvider;
import de.gammadata.microservices.addressrs.addresses.control.ZipCodeCrudController;
import java.net.URI;
import java.net.URL;
import javax.ejb.EJB;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

/**
 *
 * @author gfr
 */
@RunWith(Arquillian.class)
public class AddressResourceArquillianIT extends AddressResourceRestIT {

  @EJB
  AddressCrudController adrController;
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
  @Before
  @Override
  public void setUp() throws Exception {
    if (base == null) {
      throw new RuntimeException("no base URL injeted");
    }
    TestEntityProvider.deleteAllEntities(adrController, zipCodeController, cityController, countryController);

    System.out.println("Arquillian Tests using URL = " + base.toExternalForm());
    webTarget = client.target(URI.create(new URL(base, "api/addresses").toExternalForm()));
  }

  @After
  public void tearDown() {
    TestEntityProvider.deleteAllEntities(adrController, zipCodeController, cityController, countryController);
  }

}
