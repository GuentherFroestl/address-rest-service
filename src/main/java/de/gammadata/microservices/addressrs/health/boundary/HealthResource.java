package de.gammadata.microservices.addressrs.health.boundary;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author gfr
 */
@Path(HealthResource.PATH)
public class HealthResource {
  
  public static final String PATH = "/healthcheck";

  /**
   * Retrieves representation of an instance of de.gammadata.microservices.addressrs.health.HealthResource
   *
   * @return an instance of java.lang.String
   */
  @GET
  @Produces({MediaType.TEXT_PLAIN})
  public String getPing() {
    return "ping";
  }
}
