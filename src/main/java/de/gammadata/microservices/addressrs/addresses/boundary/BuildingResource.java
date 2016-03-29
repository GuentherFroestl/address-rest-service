package de.gammadata.microservices.addressrs.addresses.boundary;

import de.gammadata.microservices.addressrs.addresses.control.BuildingCrudController;
import de.gammadata.microservices.addressrs.addresses.entity.Building;
import de.gammadata.microservices.addressrs.common.boundary.AbstractCrudResource;
import de.gammadata.microservices.addressrs.common.control.AbstractCrudController;
import de.gammadata.microservices.addressrs.common.entity.EntityRelatedQuerySpec;
import de.gammadata.microservices.addressrs.common.entity.SimpleQuerySpecification;
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
 *
 * @author gfr
 */
@ManagedBean
@Path(BuildingResource.PATH)
public class BuildingResource extends AbstractCrudResource<Building, Building, SimpleQuerySpecification> {

    public static final String PATH = "/buildings";
    public static final String BUILDINGS_PATH = "/{streetid}/buildings";

    @EJB
    public BuildingCrudController buildingController;

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
    public List<Building> queryForStreet(
            @PathParam("streetid") Long streetid,
            @QueryParam("start") Integer start,
            @QueryParam("limit") Integer limit,
            @QueryParam("query") String query) {
        EntityRelatedQuerySpec querySpec = new EntityRelatedQuerySpec(streetid, limit, start, query);
        List<Building> result = buildingController.findBuildingsForStreet(querySpec);
        return result;
    }

    @Override
    public AbstractCrudController<Building, Building, SimpleQuerySpecification> getCrudController() {
        return buildingController;
    }
}
