package de.gammadata.microservices.addressrs.addresses.boundary;

import de.gammadata.microservices.addressrs.addresses.entity.City;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;

/**
 *
 * @author gfr
 */
public class CitiesResourceRestStIT extends AbstractResourceRestIT {

  @AfterClass
  public static void tearDownClass() {
  }

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }
  
  protected static String CITY_URL = BASE_URL + CitiesResource.PATH;

  /**
   * Test of getAllAdresses method, of class CityResource.
   */
  @Test
  public void testGetAll() {

    WebTarget userTarget = client.target(CITY_URL);
    Response response = userTarget
            .request(MediaType.APPLICATION_JSON).get();
    checkResponse(response);
    City[] res = response.readEntity(City[].class);
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
   * Test of getCity method, of class CityResource.
   */
  @Test
  public void testGetCity() {
    System.out.println("getCity");

    //Create City
    City adrReq = createCity();
    Response response = client.target(CITY_URL)
            .request(MediaType.APPLICATION_JSON_TYPE)
            .post(Entity.entity(adrReq, MediaType.APPLICATION_JSON_TYPE));
    checkResponse(response);
    System.out.println(response);
    City adrCreated = response.readEntity(City.class);
    assertNotNull("no result", adrCreated);
    adrReq.setId(adrCreated.getId());
    adrReq.setVersion(adrCreated.getVersion());
    System.out.println(adrCreated);
    assertThat(adrReq, is(equalTo(adrCreated)));

    WebTarget adrGetTarget = client.target(CITY_URL).path(adrCreated.getId().toString());
    response = adrGetTarget
            .request(MediaType.APPLICATION_JSON).get();
    City adrFetched = response.readEntity(City.class);
    assertNotNull("no result", adrFetched);
    assertThat(adrCreated, is(equalTo(adrFetched)));
  }

  /**
   * Test of saveOrUpdateCity method, of class CityResource.
   */
  @Test
  public void testSaveOrUpdateCity() {
    System.out.println("saveOrUpdateCity");
    //Create City
    City adrReq = createCity();
    Response response = client.target(CITY_URL)
            .request(MediaType.APPLICATION_JSON_TYPE)
            .post(Entity.entity(adrReq, MediaType.APPLICATION_JSON_TYPE));
    checkResponse(response);
    System.out.println(response);
    City adrCreated = response.readEntity(City.class);
    assertNotNull("no result", adrCreated);
    adrReq.setId(adrCreated.getId());
    adrReq.setVersion(adrCreated.getVersion());
    System.out.println(adrCreated);
    assertThat(adrReq, is(equalTo(adrCreated)));

    //Change address
    adrCreated.setName("name changed");

    response = client.target(CITY_URL)
            .request(MediaType.APPLICATION_JSON_TYPE)
            .post(Entity.entity(adrCreated, MediaType.APPLICATION_JSON_TYPE));
    checkResponse(response);
    System.out.println(response);
    City resultChanged = response.readEntity(City.class);
    assertNotNull("no result", adrCreated);
    System.out.println(resultChanged);
    assertThat(adrCreated.getName(), is(equalTo(resultChanged.getName())));

  }

  /**
   * Test of deleteCity method, of class CityResource.
   */
  @Test
  public void testDeleteCity() {
    System.out.println("deleteCity");
    //Create City
    City adrReq = createCity();
    Response response = client.target(CITY_URL)
            .request(MediaType.APPLICATION_JSON_TYPE)
            .post(Entity.entity(adrReq, MediaType.APPLICATION_JSON_TYPE));
    checkResponse(response);
    System.out.println(response);
    City adrCreated = response.readEntity(City.class);
    assertNotNull("no result", adrCreated);
    adrReq.setId(adrCreated.getId());
    adrReq.setVersion(adrCreated.getVersion());
    System.out.println(adrCreated);
    assertThat(adrReq, is(equalTo(adrCreated)));

    WebTarget userTarget = client.target(CITY_URL).path(adrCreated.getId().toString());
    Response resp = userTarget.request(MediaType.APPLICATION_JSON).delete();
    checkResponse(response);
    System.out.println(resp);
    assertThat(resp.getStatus(), is(equalTo(204)));

    WebTarget adrGetTarget = client.target(CITY_URL).path(adrCreated.getId().toString());
    Response response2 = adrGetTarget
            .request(MediaType.APPLICATION_JSON).get();
    checkResponse(response2);
    System.out.println(response2);
    assertThat(response2.getStatus(), is(equalTo(204))); //No content
  }

  private City createCity() {
    City result = new City();
    result.setName("city name");
    return result;
  }
}
