package de.gammadata.microservices.addressrs.addresses.boundary;

import de.gammadata.microservices.addressrs.addresses.control.AbstractCrudController;
import de.gammadata.microservices.addressrs.addresses.control.CityCrudController;
import de.gammadata.microservices.addressrs.addresses.control.StreetCrudController;
import de.gammadata.microservices.addressrs.addresses.entity.BaseQuerySpecification;
import de.gammadata.microservices.addressrs.addresses.entity.City;
import de.gammadata.microservices.addressrs.addresses.entity.EntityRelatedQuerySpec;
import de.gammadata.microservices.addressrs.addresses.entity.StreetBasics;
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
 * Cities resources, exposed as /cities.
 */
@ManagedBean
@Path(CitiesResource.PATH)
public class CitiesResource extends AbstractCrudResource<City, City, BaseQuerySpecification> {

  /**
   *
   */
  public static final String PATH = "/cities";

  /**
   *
   */
  public static final String STREET_PATH = "/{id}/streets";

  @EJB
  CityCrudController cityController;
  @EJB
  StreetCrudController streetController;

  /**
   *
   * @return
   */
  @Override
  public AbstractCrudController<City, City, BaseQuerySpecification> getCrudController() {
    return cityController;
  }

  /**
   * Query street within a city given by ID.
   * @param id Long
   * @param start Integer
   * @param limit Integer
   * @param query String
   * @return List of StreetBasics
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path(STREET_PATH)
  public List<StreetBasics> queryStreets(
          @PathParam("id") Long id,
          @QueryParam("start") Integer start,
          @QueryParam("limit") Integer limit,
          @QueryParam("query") String query) {
    EntityRelatedQuerySpec querySpec = new EntityRelatedQuerySpec(id,limit, start, query);
    List<StreetBasics> result = streetController.findStreetsInCity(querySpec);
    return result;
  }
}
