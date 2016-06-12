package de.gammadata.microservices.addressrs.application.control;

import de.gammadata.microservices.addressrs.addresses.boundary.StreetResource;
import de.gammadata.microservices.addressrs.contacts.boundary.ComAddressCrudResource;
import de.gammadata.microservices.addressrs.health.boundary.HealthResource;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Register Resources, JacksonFeature and MyObjectMapperProvider.
 *
 * @author gfr
 */
@ApplicationPath("/api")
public class JaxRsApplication extends ResourceConfig {

  /**
   *
   */
  public JaxRsApplication() {
    super(
            JacksonFeature.class,
            MyObjectMapperProvider.class,
            CORSFilter.class);
    packages(
            HealthResource.class.getPackage().getName(),
            StreetResource.class.getPackage().getName(),
            ComAddressCrudResource.class.getPackage().getName());
  }
}
