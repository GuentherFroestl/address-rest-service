package de.gammadata.microservices.addressrs.addresses.boundary;

import de.gammadata.microservices.addressrs.addresses.control.TestEntityProvider;
import de.gammadata.microservices.addressrs.addresses.entity.Street;
import de.gammadata.microservices.addressrs.addresses.entity.StreetBasics;
import java.util.List;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;

/**
 *
 * @author gfr
 */
public class StreetResourceRestStIT extends AbstractResourceRestIT {

  /**
   *
   */
  protected WebTarget webTarget;

  /**
   *
   * @throws Exception
   */
  @Before
  public void setUp() throws Exception {
    webTarget = client.target(BASE_URL + StreetResource.PATH);
  }

  /**
   * Test of getAllAdresses method, of class AddressResource.
   */
  @Test
  public void testGetAllAdresses() {

    Response response = webTarget.path("/query")
            .request(MediaType.APPLICATION_JSON).get();
    checkResponse(response);
    StreetBasics[] res = response.readEntity(StreetBasics[].class);
    assertNotNull("no result", res);
  }

  /**
   *
   * @param resp
   */
  protected void checkResponse(Response resp) {
    assertNotNull("no result", resp);
    assertTrue("wrong status for response=" + resp.getStatus(), resp.getStatus() >= 200 && resp.getStatus() < 300);
  }

  /**
   * Test of getAddress method, of class AddressResource.
   */
  @Test
  public void testGetAddress() {
    System.out.println("getAddress");

    //Create Street
    Street adrReq = TestEntityProvider.createAddress();
    Response response = webTarget
            .request(MediaType.APPLICATION_JSON_TYPE)
            .post(Entity.entity(adrReq, MediaType.APPLICATION_JSON_TYPE));
    checkResponse(response);
    System.out.println(response);
    Street adrCreated = response.readEntity(Street.class);
    assertNotNull("no result", adrCreated);
    assertNotNull("no id", adrCreated.getId());
    assertNotNull("no id", adrCreated.getModified());
    adrReq.setId(adrCreated.getId());
    adrReq.setVersion(adrCreated.getVersion());
    TestEntityProvider.setBasePropertiesForEquals(adrReq, adrCreated);
    System.out.println(adrCreated);
    assertThat(adrCreated, is(equalTo(adrReq)));

    response = webTarget.path("/query")
            .request(MediaType.APPLICATION_JSON).get();
    checkResponse(response);
    List<StreetBasics> adrList = response.readEntity(List.class);
    assertNotNull("no result", adrList);
    assertTrue("list empty", !adrList.isEmpty());
  }

  /**
   * Test of saveOrUpdateAddress method, of class AddressResource.
   */
  @Test
  public void testSaveOrUpdateAddress() {
    System.out.println("saveOrUpdateAddress");
    //Create Street
    Street adrReq = TestEntityProvider.createAdressWithAllEntities();
    Response response = webTarget
            .request(MediaType.APPLICATION_JSON_TYPE)
            .post(Entity.entity(adrReq, MediaType.APPLICATION_JSON_TYPE));
    checkResponse(response);
    System.out.println(response);
    Street adrCreated = response.readEntity(Street.class);
    assertNotNull("no result", adrCreated);
    assertNotNull("no id", adrCreated.getId());
    assertNotNull("no id", adrCreated.getModified());
    adrReq.setId(adrCreated.getId());
    adrReq.setVersion(adrCreated.getVersion());
    System.out.println(adrCreated);
    TestEntityProvider.setBasePropertiesForEquals(adrReq, adrCreated);
    assertThat(adrCreated.getName(), is(equalTo(adrReq.getName())));

    //Change address
    adrCreated.setName("name changed");

    response = webTarget
            .request(MediaType.APPLICATION_JSON_TYPE)
            .post(Entity.entity(adrCreated, MediaType.APPLICATION_JSON_TYPE));
    checkResponse(response);
    System.out.println(response);
    Street resultChanged = response.readEntity(Street.class);
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
    //Create Street
    Street adrReq = TestEntityProvider.createAdressWithAllEntities();
    Response response = webTarget
            .request(MediaType.APPLICATION_JSON_TYPE)
            .post(Entity.entity(adrReq, MediaType.APPLICATION_JSON_TYPE));
    checkResponse(response);
    System.out.println(response);
    Street adrCreated = response.readEntity(Street.class);
    assertNotNull("no result", adrCreated);
    adrReq.setId(adrCreated.getId());
    adrReq.setVersion(adrCreated.getVersion());
    System.out.println(adrCreated);
    TestEntityProvider.setBasePropertiesForEquals(adrReq, adrCreated);
    assertThat(adrCreated.getName(), is(equalTo(adrReq.getName())));

    WebTarget userTarget = webTarget.path(adrCreated.getId().toString());
    Response resp = userTarget.request(MediaType.APPLICATION_JSON).delete();
    checkResponse(response);
    System.out.println(resp);
    assertThat(204, is(equalTo(resp.getStatus())));

    WebTarget adrGetTarget = webTarget.path(adrCreated.getId().toString());
    Response response2 = adrGetTarget
            .request(MediaType.APPLICATION_JSON).get();
    checkResponse(response2);
    System.out.println(response2);
    assertThat(204, is(equalTo(response2.getStatus()))); //No content
  }

}
