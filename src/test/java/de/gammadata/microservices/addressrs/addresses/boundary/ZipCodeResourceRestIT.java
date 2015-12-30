package de.gammadata.microservices.addressrs.addresses.boundary;

import de.gammadata.microservices.addressrs.addresses.entity.ZipCode;
import java.util.Date;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.is;

/**
 *
 * @author gfr
 */
public class ZipCodeResourceRestIT extends AbstractResourceRestIT {

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
   * Test of getAllAdresses method, of class ZipCodeResource.
   */
  @Test
  public void testGetAll() {

    WebTarget userTarget = client.target(BASE_URL + "zipcodes");
    Response response = userTarget
            .request(MediaType.APPLICATION_JSON).get();
    checkResponse(response);
    ZipCode[] res = response.readEntity(ZipCode[].class);
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
   * Test of getZipCode method, of class ZipCodeResource.
   */
  @Test
  public void testGetZipCode() {
    System.out.println("getZipCode");

    //Create ZipCode
    ZipCode adrReq = createZipCode();
    Response response = client.target(BASE_URL + "zipcodes")
            .request(MediaType.APPLICATION_JSON_TYPE)
            .post(Entity.entity(adrReq, MediaType.APPLICATION_JSON_TYPE));
    checkResponse(response);
    System.out.println(response);
    ZipCode adrCreated = response.readEntity(ZipCode.class);
    assertNotNull("no result", adrCreated);
    adrReq.setId(adrCreated.getId());
    adrReq.setVersion(adrCreated.getVersion());
    System.out.println(adrCreated);
    assertThat(adrReq, is(equalTo(adrCreated)));

    WebTarget adrGetTarget = client.target(BASE_URL + "zipcodes").path(adrCreated.getId().toString());
    response = adrGetTarget
            .request(MediaType.APPLICATION_JSON).get();
    ZipCode adrFetched = response.readEntity(ZipCode.class);
    assertNotNull("no result", adrFetched);
    assertThat(adrCreated, is(equalTo(adrFetched)));
  }

  /**
   * Test of saveOrUpdateZipCode method, of class ZipCodeResource.
   */
  @Test
  public void testSaveOrUpdateZipCode() {
    System.out.println("saveOrUpdateZipCode");
    //Create ZipCode
    ZipCode adrReq = createZipCode();
    Response response = client.target(BASE_URL + "zipcodes")
            .request(MediaType.APPLICATION_JSON_TYPE)
            .post(Entity.entity(adrReq, MediaType.APPLICATION_JSON_TYPE));
    checkResponse(response);
    System.out.println(response);
    ZipCode adrCreated = response.readEntity(ZipCode.class);
    assertNotNull("no result", adrCreated);
    adrReq.setId(adrCreated.getId());
    adrReq.setVersion(adrCreated.getVersion());
    System.out.println(adrCreated);
    assertThat(adrReq, is(equalTo(adrCreated)));

    //Change address
    adrCreated.setName("name changed");

    response = client.target(BASE_URL + "zipcodes")
            .request(MediaType.APPLICATION_JSON_TYPE)
            .post(Entity.entity(adrCreated, MediaType.APPLICATION_JSON_TYPE));
    checkResponse(response);
    System.out.println(response);
    ZipCode resultChanged = response.readEntity(ZipCode.class);
    assertNotNull("no result", adrCreated);
    System.out.println(resultChanged);
    assertThat(adrCreated.getName(), is(equalTo(resultChanged.getName())));

  }

  /**
   * Test of deleteZipCode method, of class ZipCodeResource.
   */
  @Test
  public void testDeleteZipCode() {
    System.out.println("deleteZipCode");
    //Create ZipCode
    ZipCode adrReq = createZipCode();
    Response response = client.target(BASE_URL + "zipcodes")
            .request(MediaType.APPLICATION_JSON_TYPE)
            .post(Entity.entity(adrReq, MediaType.APPLICATION_JSON_TYPE));
    checkResponse(response);
    System.out.println(response);
    ZipCode adrCreated = response.readEntity(ZipCode.class);
    assertNotNull("no result", adrCreated);
    adrReq.setId(adrCreated.getId());
    adrReq.setVersion(adrCreated.getVersion());
    System.out.println(adrCreated);
    assertThat(adrReq, is(equalTo(adrCreated)));

    WebTarget userTarget = client.target(BASE_URL + "zipcodes").path(adrCreated.getId().toString());
    Response resp = userTarget.request(MediaType.APPLICATION_JSON).delete();
    checkResponse(response);
    System.out.println(resp);
    assertThat(resp.getStatus(), is(equalTo(204)));

    WebTarget adrGetTarget = client.target(BASE_URL + "zipcodes").path(adrCreated.getId().toString());
    Response response2 = adrGetTarget
            .request(MediaType.APPLICATION_JSON).get();
    checkResponse(response2);
    System.out.println(response2);
    assertThat(response2.getStatus(), is(equalTo(204))); //No content
  }

  private ZipCode createZipCode() {
    ZipCode result = new ZipCode();
    result.setName("zipcode");
    result.setValidFrom(new Date());
    result.setValidUntil(new Date());
    return result;
  }
}
