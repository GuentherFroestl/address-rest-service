package de.gammadata.microservices.addressrs.addresses;

import de.gammadata.microservices.addressrs.addresses.boundary.CountriesResource;
import de.gammadata.microservices.addressrs.addresses.control.CountryCrudController;
import de.gammadata.microservices.addressrs.health.boundary.HealthResource;
import javax.inject.Inject;
import org.eu.ingwar.tools.arquillian.extension.suite.annotations.ArquillianSuiteDeployment;
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
@ArquillianSuiteDeployment
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

}
