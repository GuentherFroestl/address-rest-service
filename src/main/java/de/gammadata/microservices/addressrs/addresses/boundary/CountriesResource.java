package de.gammadata.microservices.addressrs.addresses.boundary;

import de.gammadata.microservices.addressrs.common.boundary.AbstractCrudResource;
import de.gammadata.microservices.addressrs.common.control.AbstractCrudController;
import de.gammadata.microservices.addressrs.addresses.control.CityCrudController;
import de.gammadata.microservices.addressrs.addresses.control.CountryCrudController;
import de.gammadata.microservices.addressrs.addresses.control.ZipCodeCrudController;
import de.gammadata.microservices.addressrs.common.entity.BaseQuerySpecification;
import de.gammadata.microservices.addressrs.addresses.entity.City;
import de.gammadata.microservices.addressrs.addresses.entity.Country;
import de.gammadata.microservices.addressrs.common.entity.EntityRelatedQuerySpec;
import de.gammadata.microservices.addressrs.addresses.entity.ZipCode;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Countries resources, exposed as /Countries.
 */
@ManagedBean
@Path(CountriesResource.PATH)
public class CountriesResource extends AbstractCrudResource<Country, Country, BaseQuerySpecification> {

  /**
   *
   */
  public static final String PATH = "/countries";

  /**
   *
   */
  public static final String CITIES_PATH = "/{id}/streets";

  /**
   *
   */
  public static final String ZIPCODES_PATH = "/{id}/zipcodes";

  @EJB
  CountryCrudController countryController;
  @EJB
  CityCrudController cityController;
  @EJB
  ZipCodeCrudController zipCodeController;

  /**
   *
   * @return
   */
  @Override
  public AbstractCrudController<Country, Country, BaseQuerySpecification> getCrudController() {
    return countryController;
  }

  /**
   * Query cities within a country given by ID.
   *
   * @param id Long CountryId
   * @param start Integer
   * @param limit Integer
   * @param query String
   * @return List of City
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path(CITIES_PATH)
  public List<City> queryCities(
          @PathParam("id") Long id,
          @QueryParam("start") Integer start,
          @QueryParam("limit") Integer limit,
          @QueryParam("query") String query) {
    EntityRelatedQuerySpec querySpec = new EntityRelatedQuerySpec(id, limit, start, query);
    List<City> result = cityController.findCitiesInCountry(querySpec);
    return result;
  }

  /**
   * Query zipCodes within a country given by ID.
   *
   * @param id Long CountryId
   * @param start Integer
   * @param limit Integer
   * @param query String
   * @return List of ZipCode
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path(ZIPCODES_PATH)
  public List<ZipCode> queryZipCodes(
          @PathParam("id") Long id,
          @QueryParam("start") Integer start,
          @QueryParam("limit") Integer limit,
          @QueryParam("query") String query) {
    EntityRelatedQuerySpec querySpec = new EntityRelatedQuerySpec(id, limit, start, query);
    List<ZipCode> result = zipCodeController.findZipCodesInCountry(querySpec);
    return result;
  }

}
