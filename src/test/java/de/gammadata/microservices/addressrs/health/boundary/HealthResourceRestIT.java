package de.gammadata.microservices.addressrs.health.boundary;

import de.gammadata.microservices.addressrs.addresses.boundary.AbstractResourceRestIT;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author gfr
 */
public class HealthResourceRestIT  extends AbstractResourceRestIT {
  


  /**
   * Test of ping method, of class HealthResource.
   */
  @Test
  public void testPing() {
    System.out.println("test ping");
    WebTarget userTarget = client.target(HealthResource.PATH);
    Response response = userTarget
            .request(MediaType.TEXT_PLAIN).get();
    String res = response.readEntity(String.class);
    System.out.println(res);
    String expResult = "ping";
    assertEquals(expResult, res);
  }
}
