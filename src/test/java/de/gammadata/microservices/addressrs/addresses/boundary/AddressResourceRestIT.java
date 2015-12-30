package de.gammadata.microservices.addressrs.addresses.boundary;

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
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.is;

/**
 *
 * @author gfr
 */
public class AddressResourceRestIT extends AbstractResourceRestIT {

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
    checkResponse(response);
    Address[] res = response.readEntity(Address[].class);
    assertNotNull("no result", res);
  }

  private void checkResponse(Response resp) {
    if (resp == null) {
      throw new RuntimeException("Response is null");
    }
    if (resp.getStatus() > 299) {
      throw new RuntimeException("Response has Status " + resp.getStatus());
    }
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
    checkResponse(response);
    System.out.println(response);
    Address adrCreated = response.readEntity(Address.class);
    assertNotNull("no result", adrCreated);
    adrReq.setId(adrCreated.getId());
    adrReq.setVersion(adrCreated.getVersion());
    System.out.println(adrCreated);
    assertThat(adrCreated, is(equalTo(adrReq)));

    WebTarget adrGetTarget = client.target(BASE_URL + "addresses").path(adrCreated.getId().toString());
    response = adrGetTarget
            .request(MediaType.APPLICATION_JSON).get();
    Address adrFetched = response.readEntity(Address.class);
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
    checkResponse(response);
    System.out.println(response);
    Address adrCreated = response.readEntity(Address.class);
    assertNotNull("no result", adrCreated);
    adrReq.setId(adrCreated.getId());
    adrReq.setVersion(adrCreated.getVersion());
    System.out.println(adrCreated);
    assertThat(adrCreated, is(equalTo(adrReq)));

    //Change address
    adrCreated.setName("name changed");

    response = client.target(BASE_URL + "addresses")
            .request(MediaType.APPLICATION_JSON_TYPE)
            .post(Entity.entity(adrCreated, MediaType.APPLICATION_JSON_TYPE));
    checkResponse(response);
    System.out.println(response);
    Address resultChanged = response.readEntity(Address.class);
    assertNotNull("no result", adrCreated);
    System.out.println(resultChanged);
    assertThat(resultChanged.getName(), is(equalTo(adrCreated.getName())));

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
    checkResponse(response);
    System.out.println(response);
    Address adrCreated = response.readEntity(Address.class);
    assertNotNull("no result", adrCreated);
    adrReq.setId(adrCreated.getId());
    adrReq.setVersion(adrCreated.getVersion());
    System.out.println(adrCreated);
    assertThat(adrCreated, is(equalTo(adrReq)));

    WebTarget userTarget = client.target(BASE_URL + "addresses").path(adrCreated.getId().toString());
    Response resp = userTarget.request(MediaType.APPLICATION_JSON).delete();
    checkResponse(response);
    System.out.println(resp);
    assertThat(204, is(equalTo(resp.getStatus())));

    WebTarget adrGetTarget = client.target(BASE_URL + "addresses").path(adrCreated.getId().toString());
    Response response2 = adrGetTarget
            .request(MediaType.APPLICATION_JSON).get();
    checkResponse(response2);
    System.out.println(response2);
    assertThat(204, is(equalTo(response2.getStatus()))); //No content
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
