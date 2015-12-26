package de.gammadata.microservices.addressrs;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;
import org.junit.BeforeClass;

/**
 *
 * @author gfr
 */
public class AbstractResourceTestIT {

  protected static Client client;
  public static String BASE_URL = "http://localhost:8080/address-rs/api/";

  @BeforeClass
  public static void setUpClass() {
    client = ClientBuilder.newBuilder().register(MOXyJsonProvider.class)
            .build();
  }

}
