package de.gammadata.microservices.addressrs.addresses.boundary;

import de.gammadata.microservices.addressrs.addresses.control.AbstractCrudController;
import de.gammadata.microservices.addressrs.addresses.control.StreetCrudController;
import de.gammadata.microservices.addressrs.addresses.control.ZipCodeCrudController;
import de.gammadata.microservices.addressrs.addresses.entity.BaseQuerySpecification;
import de.gammadata.microservices.addressrs.addresses.entity.EntityRelatedQuerySpec;
import de.gammadata.microservices.addressrs.addresses.entity.StreetBasics;
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
 * Cities resources, exposed as /cities.
 */
@ManagedBean
@Path(ZipCodeResource.PATH)
public class ZipCodeResource extends AbstractCrudResource<ZipCode, ZipCode, BaseQuerySpecification> {

  /**
   *
   */
  public static final String PATH = "/zipcodes";

  /**
   *
   */
  public static final String STREETS_PATH = "/{id}/streets";

  @EJB
  ZipCodeCrudController zipCodeController;
  @EJB
  StreetCrudController streetController;

  /**
   *
   * @return
   */
  @Override
  public AbstractCrudController<ZipCode, ZipCode, BaseQuerySpecification> getCrudController() {
    return zipCodeController;
  }

  /**
   * Query street within a city given by ID.
   *
   * @param id Long
   * @param start Integer
   * @param limit Integer
   * @param query String
   * @return List of StreetBasics
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path(STREETS_PATH)
  public List<StreetBasics> queryStreets(
          @PathParam("id") Long id,
          @QueryParam("start") Integer start,
          @QueryParam("limit") Integer limit,
          @QueryParam("query") String query) {
    EntityRelatedQuerySpec querySpec = new EntityRelatedQuerySpec(id, limit, start, query);
    List<StreetBasics> result = streetController.findStreetsInZipCode(querySpec);
    return result;
  }

}
