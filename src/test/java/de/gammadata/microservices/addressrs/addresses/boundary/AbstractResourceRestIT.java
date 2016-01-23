package de.gammadata.microservices.addressrs.addresses.boundary;

import de.gammadata.microservices.addressrs.application.control.MyObjectMapperProvider;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.BeforeClass;

/**
 *
 * @author gfr
 */
public class AbstractResourceRestIT {

  /**
   *
   */
  protected static Client client;

  /**
   *
   */
  public static final String BASE_URL = "http://localhost:8080/address-rs/api";

  /**
   *
   */
  @BeforeClass
  public static void setUpClass() {
    client = ClientBuilder.newBuilder()
            .register(MyObjectMapperProvider.class) // No need to register this provider if no special configuration is required.
            .register(JacksonFeature.class)
            .build();
  }
}
