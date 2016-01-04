package de.gammadata.microservices.addressrs.addresses;

import de.gammadata.microservices.addressrs.addresses.boundary.CountriesResource;
import de.gammadata.microservices.addressrs.addresses.control.AddressCrudController;
import de.gammadata.microservices.addressrs.addresses.control.CityCrudController;
import de.gammadata.microservices.addressrs.addresses.control.CountryCrudController;
import de.gammadata.microservices.addressrs.addresses.control.ZipCodeCrudController;
import de.gammadata.microservices.addressrs.addresses.entity.Address;
import de.gammadata.microservices.addressrs.addresses.entity.City;
import de.gammadata.microservices.addressrs.addresses.entity.Country;
import de.gammadata.microservices.addressrs.addresses.entity.ZipCode;
import de.gammadata.microservices.addressrs.health.boundary.HealthResource;
import java.util.List;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author gfr
 */
@RunWith(Arquillian.class)
//@ArquillianSuiteDeployment
public class DeploymentLoaderArquillianIT {

  @Deployment
  public static JavaArchive createDeployment() {
    JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
            .addPackage(CountryCrudController.class.getPackage())
            .addPackage(CountriesResource.class.getPackage())
            .addPackage(HealthResource.class.getPackage())
            .addAsResource("persistence-arquillian.xml", "META-INF/persistence.xml")
            .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    System.out.println(jar.toString(true));
    return jar;
  }

  @Inject
  HealthResource resource;

  @Test
  public void test1_Injected() {
    System.out.println("test1_Injected");
    assertNotNull("resource not injected, Arquillian deployment probably failed", resource);
  }

  /**
   * Cleanup Utillity for Tests. If one or more controllers are null not cleanup for that entities will be performed.
   *
   * @param adrController AddressCrudController
   * @param zipCodeController ZipCodeCrudController
   * @param cityController CityCrudController
   * @param countryController CountryCrudController
   */
  public static void deleteAllEntities(
          AddressCrudController adrController,
          ZipCodeCrudController zipCodeController,
          CityCrudController cityController,
          CountryCrudController countryController) {

    if (adrController != null) {
      List<Address> lAdr = adrController.getEntities(null);
      if (lAdr != null && !lAdr.isEmpty()) {
        for (Address a : lAdr) {
          adrController.deleteEntity(a.getId());
        }
      }
    }
    if (cityController != null) {
      List<City> lCity = cityController.getEntities(null);
      if (lCity != null && !lCity.isEmpty()) {
        for (City c : lCity) {
          cityController.deleteEntity(c.getId());
        }
      }
    }
    if (zipCodeController != null) {
      List<ZipCode> lZip = zipCodeController.getEntities(null);
      if (lZip != null && !lZip.isEmpty()) {
        for (ZipCode z : lZip) {
          zipCodeController.deleteEntity(z.getId());
        }
      }
    }
    if (countryController != null) {
      List<Country> lCountry = countryController.getEntities(null);
      if (lCountry != null && !lCountry.isEmpty()) {
        for (Country c : lCountry) {
          countryController.deleteEntity(c.getId());
        }
      }
    }
  }

}
