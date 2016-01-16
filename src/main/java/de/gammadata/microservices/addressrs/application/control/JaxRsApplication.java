package de.gammadata.microservices.addressrs.application.control;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Register JacksonFeature and MyObjectMapperProvider.
 * @author gfr
 */
@ApplicationPath("/api")
public class JaxRsApplication extends ResourceConfig {

  /**
   *
   */
  public JaxRsApplication() {
    super(JacksonFeature.class,
            MyObjectMapperProvider.class);
    packages("de.gammadata.microservices.addressrs.addresses.boundary",
            "de.gammadata.microservices.addressrs.health.boundary");

  }
}
