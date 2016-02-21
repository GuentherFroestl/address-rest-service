package de.gammadata.microservices.addressrs.addresses.boundary;

import de.gammadata.microservices.addressrs.common.boundary.AbstractCrudResource;
import de.gammadata.microservices.addressrs.addresses.control.StreetCrudController;
import de.gammadata.microservices.addressrs.common.entity.SimpleQuerySpecification;
import de.gammadata.microservices.addressrs.addresses.entity.Building;
import de.gammadata.microservices.addressrs.common.entity.EntityRelatedQuerySpec;
import de.gammadata.microservices.addressrs.addresses.entity.Street;
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
 * StreetResource (exposed at "streets" path)
 */
@ManagedBean
@Path(StreetResource.PATH)
public class StreetResource extends AbstractCrudResource<Street, StreetBasics, SimpleQuerySpecification> {

  /**
   * REST Pathes.
   */
  public static final String PATH = "/streets";
  public static final String WITHIN_CITY = "/withincity/{fkid}";
  public static final String WITHIN_ZIPCODE = "/withinzipcode/{fkid}";
  public static final String BUILDINGS_PATH = "/{streetid}/buildings";

  @EJB
  StreetCrudController streetController;

  /**
   *
   * @return
   */
  @Override
  public StreetCrudController getCrudController() {
    return streetController;
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path(WITHIN_CITY)
  public List<StreetBasics> findStreetsInCity(
          @PathParam("fkid") Long fkid,
          @QueryParam("start") Integer start,
          @QueryParam("limit") Integer limit,
          @QueryParam("query") String query) {
    EntityRelatedQuerySpec querySpec = new EntityRelatedQuerySpec(fkid, limit, start, query);
    return getCrudController().findStreetsInCity(querySpec);
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path(WITHIN_ZIPCODE)
  public List<StreetBasics> findStreetsInZipCode(
          @PathParam("fkid") Long fkid,
          @QueryParam("start") Integer start,
          @QueryParam("limit") Integer limit,
          @QueryParam("query") String query) {
    EntityRelatedQuerySpec querySpec = new EntityRelatedQuerySpec(fkid, limit, start, query);
    return getCrudController().findStreetsInZipCode(querySpec);
  }

  /**
   * Query street within a city given by ID.
   *
   * @param streetid Long
   * @param start Integer
   * @param limit Integer
   * @param query String
   * @return List of StreetBasics
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path(BUILDINGS_PATH)
  public List<Building> queryStreets(
          @PathParam("streetid") Long streetid,
          @QueryParam("start") Integer start,
          @QueryParam("limit") Integer limit,
          @QueryParam("query") String query) {
    EntityRelatedQuerySpec querySpec = new EntityRelatedQuerySpec(streetid, limit, start, query);
    List<Building> result = getCrudController().findBuildings(querySpec);
    return result;
  }
}
