package de.gammadata.microservices.addressrs.addresses.boundary;

import de.gammadata.microservices.addressrs.addresses.control.AddressCrudController;
import de.gammadata.microservices.addressrs.addresses.entity.Address;
import de.gammadata.microservices.addressrs.application.control.JaxRsApplication;
import de.gammadata.microservices.addressrs.application.entity.AddressServiceException;
import de.gammadata.microservices.addressrs.health.boundary.HealthResource;
import java.net.URI;
import java.net.URL;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.runner.RunWith;

/**
 *
 * @author gfr
 */
@RunWith(Arquillian.class)
public class AddressResourceArquillianIT extends AddressResourceRestIT {

  @ArquillianResource
  private URL base;

  @Deployment
  public static WebArchive createDeployment() {
    WebArchive jar = ShrinkWrap.create(WebArchive.class)
            .addPackage(AddressCrudController.class.getPackage())
            .addPackage(AddressResource.class.getPackage())
            .addPackage(Address.class.getPackage())
            .addPackage(HealthResource.class.getPackage())
            .addPackage(JaxRsApplication.class.getPackage())//JaxRsApplication
            .addPackage(AddressServiceException.class.getPackage())
            .addAsResource("persistence-arquillian.xml", "META-INF/persistence.xml")
            .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    System.out.println(jar.toString(true));
    return jar;
  }

  @Before
  @Override
  public void setUp() throws Exception {
    if (base == null) {
      throw new RuntimeException("no base URL inhejted");
    }
    System.out.println("Arquillian Tests using URL = " + base.toExternalForm());
    webTarget = client.target(URI.create(new URL(base, "api/addresses").toExternalForm()));
  }

}
