package de.gammadata.microservices.addressrs.addresses.boundary;

import de.gammadata.microservices.addressrs.AbstractResourceTestIT;
import de.gammadata.microservices.addressrs.addresses.entity.Address;
import java.util.Date;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author gfr
 */
public class AddressResourceTestIT extends AbstractResourceTestIT {

  @AfterClass
  public static void tearDownClass() {
  }

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  /**
   * Test of getAllAdresses method, of class AddressResource.
   */
  @Test
  public void testGetAllAdresses() {

    WebTarget userTarget = client.target(BASE_URL + "addresses");
    Response response = userTarget
            .request(MediaType.APPLICATION_JSON).get();
    Address[] res = response.readEntity(Address[].class);
    assertNotNull("no result", res);
  }

  /**
   * Test of getAddress method, of class AddressResource.
   */
  @Test
  public void testGetAddress() {
    System.out.println("getAddress");

    //Create Address
    Address adrReq = createAdress();
    Response response = client.target(BASE_URL + "addresses")
            .request(MediaType.APPLICATION_JSON_TYPE)
            .post(Entity.entity(adrReq, MediaType.APPLICATION_JSON_TYPE));
    assertNotNull("no result", response);
    System.out.println(response);
    Address adrCreated = response.readEntity(Address.class);
    assertNotNull("no result", adrCreated);
    adrReq.setId(adrCreated.getId());
    System.out.println(adrCreated);
    assertThat(adrReq, is(equalTo(adrCreated)));

    WebTarget adrGetTarget = client.target(BASE_URL + "addresses").path(adrCreated.getId().toString());
    Address adrFetched = adrGetTarget
            .request(MediaType.APPLICATION_JSON).get().readEntity(Address.class);
    assertNotNull("no result", adrFetched);
    assertThat(adrCreated, is(equalTo(adrFetched)));
  }

  /**
   * Test of saveOrUpdateAddress method, of class AddressResource.
   */
  @Test
  public void testSaveOrUpdateAddress() {
    System.out.println("saveOrUpdateAddress");
    //Create Address
    Address adrReq = createAdress();
    Response response = client.target(BASE_URL + "addresses")
            .request(MediaType.APPLICATION_JSON_TYPE)
            .post(Entity.entity(adrReq, MediaType.APPLICATION_JSON_TYPE));
    assertNotNull("no result", response);
    System.out.println(response);
    Address adrCreated = response.readEntity(Address.class);
    assertNotNull("no result", adrCreated);
    adrReq.setId(adrCreated.getId());
    System.out.println(adrCreated);
    assertThat(adrReq, is(equalTo(adrCreated)));

    //Change address
    adrCreated.setName("name changed");

    response = client.target(BASE_URL + "addresses")
            .request(MediaType.APPLICATION_JSON_TYPE)
            .post(Entity.entity(adrCreated, MediaType.APPLICATION_JSON_TYPE));
    assertNotNull("no result", response);
    System.out.println(response);
    Address resultChanged = response.readEntity(Address.class);
    assertNotNull("no result", adrCreated);
    System.out.println(resultChanged);
    assertThat(adrCreated, is(equalTo(resultChanged)));

  }

  /**
   * Test of deleteAddress method, of class AddressResource.
   */
  @Test
  public void testDeleteAddress() {
    System.out.println("deleteAddress");
    //Create Address
    Address adrReq = createAdress();
    Response response = client.target(BASE_URL + "addresses")
            .request(MediaType.APPLICATION_JSON_TYPE)
            .post(Entity.entity(adrReq, MediaType.APPLICATION_JSON_TYPE));
    assertNotNull("no result", response);
    System.out.println(response);
    Address adrCreated = response.readEntity(Address.class);
    assertNotNull("no result", adrCreated);
    adrReq.setId(adrCreated.getId());
    System.out.println(adrCreated);
    assertThat(adrReq, is(equalTo(adrCreated)));

    WebTarget userTarget = client.target(BASE_URL + "addresses").path(adrCreated.getId().toString());
    Response resp = userTarget.request(MediaType.APPLICATION_JSON).delete();
    assertNotNull("no result", resp);
    System.out.println(resp);
    assertThat(resp.getStatus(), is(equalTo(204)));

    WebTarget adrGetTarget = client.target(BASE_URL + "addresses").path(adrCreated.getId().toString());
    Response response2 = adrGetTarget
            .request(MediaType.APPLICATION_JSON).get();
    System.out.println(response2);
    assertThat(response2.getStatus(), is(equalTo(204))); //No content

  }

  private Address createAdress() {
    Address adrIn = new Address();
    adrIn.setAdditionalName("additional Name");
    adrIn.setName("name");
    adrIn.setNumber("number");
    adrIn.setValidFrom(new Date());
    adrIn.setValidUntil(new Date());
    return adrIn;
  }

}
