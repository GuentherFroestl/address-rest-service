package de.gammadata.microservices.addressrs.addresses.boundary;

import de.gammadata.microservices.addressrs.addresses.control.StreetCrudController;
import de.gammadata.microservices.addressrs.addresses.entity.Street;
import de.gammadata.microservices.addressrs.addresses.entity.StreetBasics;
import de.gammadata.microservices.addressrs.addresses.entity.BaseQuerySpecification;
import de.gammadata.microservices.addressrs.addresses.entity.Building;
import de.gammadata.microservices.addressrs.addresses.entity.EntityRelatedQuerySpec;
import de.gammadata.microservices.addressrs.application.control.JacksonZuluDateSerializer;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
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
public class StreetResource extends AbstractCrudResource<Street, StreetBasics, BaseQuerySpecification> {

  /**
   *
   */
  public static final String PATH = "/streets";

  /**
   *
   */
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
    List<Building> result = streetController.findBuildings(querySpec);
    return result;
  }
}
