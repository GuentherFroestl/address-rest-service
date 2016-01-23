package de.gammadata.microservices.addressrs.addresses.boundary;

import de.gammadata.microservices.addressrs.addresses.entity.Country;
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
public class CountriesResourceRestStIT extends AbstractResourceRestIT {
  
  /**
   *
   */
  protected static final String COUNTRY_URL = BASE_URL + CountriesResource.PATH;

  /**
   *
   */
  @AfterClass
  public static void tearDownClass() {
  }

  /**
   *
   */
  @Before
  public void setUp() {
  }

  /**
   *
   */
  @After
  public void tearDown() {
  }

  /**
   * Test of getAllAdresses method, of class CountryResource.
   */
  @Test
  public void testGetAll() {

    WebTarget userTarget = client.target(COUNTRY_URL);
    Response response = userTarget
            .request(MediaType.APPLICATION_JSON).get();
    checkResponse(response);
    Country[] res = response.readEntity(Country[].class);
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
   * Test of getCountry method, of class CountryResource.
   */
  @Test
  public void testGetCountry() {
    System.out.println("getCountry");

    //Create Country
    Country adrReq = createCountry();
    Response response = client.target(COUNTRY_URL)
            .request(MediaType.APPLICATION_JSON_TYPE)
            .post(Entity.entity(adrReq, MediaType.APPLICATION_JSON_TYPE));
    checkResponse(response);
    System.out.println(response);
    Country adrCreated = response.readEntity(Country.class);
    assertNotNull("no result", adrCreated);
    adrReq.setId(adrCreated.getId());
    adrReq.setVersion(adrCreated.getVersion());
    System.out.println(adrCreated);
    assertThat(adrReq, is(equalTo(adrCreated)));

    WebTarget adrGetTarget = client.target(COUNTRY_URL).path(adrCreated.getId().toString());
    response = adrGetTarget
            .request(MediaType.APPLICATION_JSON).get();
    Country adrFetched = response.readEntity(Country.class);
    assertNotNull("no result", adrFetched);
    assertThat(adrCreated, is(equalTo(adrFetched)));
  }

  /**
   * Test of saveOrUpdateCountry method, of class CountryResource.
   */
  @Test
  public void testSaveOrUpdateCountry() {
    System.out.println("saveOrUpdateCountry");
    //Create Country
    Country adrReq = createCountry();
    Response response = client.target(COUNTRY_URL)
            .request(MediaType.APPLICATION_JSON_TYPE)
            .post(Entity.entity(adrReq, MediaType.APPLICATION_JSON_TYPE));
    checkResponse(response);
    System.out.println(response);
    Country adrCreated = response.readEntity(Country.class);
    assertNotNull("no result", adrCreated);
    adrReq.setId(adrCreated.getId());
    adrReq.setVersion(adrCreated.getVersion());
    System.out.println(adrCreated);
    assertThat(adrReq, is(equalTo(adrCreated)));

    //Change address
    adrCreated.setName("name changed");

    response = client.target(COUNTRY_URL)
            .request(MediaType.APPLICATION_JSON_TYPE)
            .post(Entity.entity(adrCreated, MediaType.APPLICATION_JSON_TYPE));
    checkResponse(response);
    System.out.println(response);
    Country resultChanged = response.readEntity(Country.class);
    assertNotNull("no result", adrCreated);
    System.out.println(resultChanged);
    assertThat(adrCreated.getName(), is(equalTo(resultChanged.getName())));

  }

  /**
   * Test of deleteCountry method, of class CountryResource.
   */
  @Test
  public void testDeleteCountry() {
    System.out.println("deleteCountry");
    //Create Country
    Country adrReq = createCountry();
    Response response = client.target(COUNTRY_URL)
            .request(MediaType.APPLICATION_JSON_TYPE)
            .post(Entity.entity(adrReq, MediaType.APPLICATION_JSON_TYPE));
    checkResponse(response);
    System.out.println(response);
    Country adrCreated = response.readEntity(Country.class);
    assertNotNull("no result", adrCreated);
    adrReq.setId(adrCreated.getId());
    adrReq.setVersion(adrCreated.getVersion());
    System.out.println(adrCreated);
    assertThat(adrReq, is(equalTo(adrCreated)));

    WebTarget userTarget = client.target(COUNTRY_URL).path(adrCreated.getId().toString());
    Response resp = userTarget.request(MediaType.APPLICATION_JSON).delete();
    checkResponse(response);
    System.out.println(resp);
    assertThat(resp.getStatus(), is(equalTo(204)));

    WebTarget adrGetTarget = client.target(COUNTRY_URL).path(adrCreated.getId().toString());
    Response response2 = adrGetTarget
            .request(MediaType.APPLICATION_JSON).get();
    checkResponse(response2);
    System.out.println(response2);
    assertThat(response2.getStatus(), is(equalTo(204))); //No content
  }

  private Country createCountry() {
    Country result = new Country();
    result.setName("country name");
    result.setIso2CountryCode("DE");
    result.setIso3CountryCode("DEU");
    result.setIsoNumber(123);
    return result;
  }
}
