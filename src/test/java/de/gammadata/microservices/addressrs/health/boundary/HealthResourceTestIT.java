package de.gammadata.microservices.addressrs.health.boundary;

import de.gammadata.microservices.addressrs.AbstractResourceTestIT;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gfr
 */
public class HealthResourceTestIT  extends AbstractResourceTestIT {
  


  /**
   * Test of ping method, of class AddressResource.
   */
  @Test
  public void testPing() {
    System.out.println("test ping");
    WebTarget userTarget = client.target(BASE_URL+"health");
    Response response = userTarget
            .request(MediaType.TEXT_PLAIN).get();
    String res = response.readEntity(String.class);
    System.out.println(res);
    String expResult = "ping";
    assertEquals(expResult, res);
  }
}
