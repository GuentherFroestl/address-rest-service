package de.gammadata.microservices.addressrs.addresses;

import de.gammadata.microservices.addressrs.addresses.boundary.StreetResource;
import de.gammadata.microservices.addressrs.addresses.control.StreetCrudController;
import de.gammadata.microservices.addressrs.addresses.entity.Street;
import de.gammadata.microservices.addressrs.application.control.JaxRsApplication;
import de.gammadata.microservices.addressrs.application.entity.AddressServiceException;
import de.gammadata.microservices.addressrs.common.boundary.AbstractCrudResource;
import de.gammadata.microservices.addressrs.common.control.AbstractCrudController;
import de.gammadata.microservices.addressrs.health.boundary.HealthResource;
import javax.inject.Inject;
import org.eu.ingwar.tools.arquillian.extension.suite.annotations.ArquillianSuiteDeployment;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author gfr
 */
@RunWith(Arquillian.class)
@ArquillianSuiteDeployment
public class DeploymentLoaderArquillianIT {

  /**
   *
   * @return
   */
  @Deployment
  public static WebArchive createDeployment() {
    WebArchive war = ShrinkWrap.create(WebArchive.class)
            .addPackage(AbstractCrudController.class.getPackage())
            .addPackage(AbstractCrudResource.class.getPackage())
            .addPackage(StreetCrudController.class.getPackage())
            .addPackage(StreetResource.class.getPackage())
            .addPackage(Street.class.getPackage())
            .addPackage(HealthResource.class.getPackage())
            .addPackage(JaxRsApplication.class.getPackage())//JaxRsApplication
            .addPackage(AddressServiceException.class.getPackage())
            .addAsResource("persistence-arquillian.xml", "META-INF/persistence.xml")
            .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    System.out.println(war.toString(true));
    return war;
  }

  @Inject
  HealthResource resource;

  /**
   *
   */
  @Test
  public void test1_Injected() {
    System.out.println("test1_Injected");
    assertNotNull("resource not injected, Arquillian deployment probably failed", resource);
  }
}
