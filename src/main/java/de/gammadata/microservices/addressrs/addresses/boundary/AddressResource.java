package de.gammadata.microservices.addressrs.addresses.boundary;

import de.gammadata.microservices.addressrs.addresses.control.AddressCrudController;
import de.gammadata.microservices.addressrs.addresses.entity.Address;
import de.gammadata.microservices.addressrs.addresses.entity.BaseQuerySpecification;
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
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * AddressResource (exposed at "addresses" path)
 */
@ManagedBean
@Path("/addresses")
public class AddressResource extends AbstractCrudResource<Address, BaseQuerySpecification> {

  @EJB
  AddressCrudController adrController;

  /**
   *
   * @return
   */
  @Override
  public AddressCrudController getCrudController() {
    return adrController;
  }

  /**
   *
   * @param start
   * @param limit
   * @param query
   * @return
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/jsonarray")
  public JsonArray getListAsJsonArray(
          @QueryParam("start") Integer start,
          @QueryParam("limit") Integer limit,
          @QueryParam("query") String query) {
    BaseQuerySpecification querySpec = new BaseQuerySpecification(limit, start, query);
    List<Address> list = getCrudController().getEntities(querySpec);

    JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
    if (list != null && !list.isEmpty()) {

      for (Address adr : list) {
        JsonObjectBuilder objBuilder = Json.createObjectBuilder();
        objBuilder.add("id", adr.getId())
                .add("version", adr.getVersion())
                .add("modified", JacksonZuluDateSerializer.serialize(adr.getModified()))
                .add("name", adr.getName())
                .add("additionalName", adr.getAdditionalName());
        if (adr.getCity() != null) {
          objBuilder.add("city", Json.createObjectBuilder()
                  .add("id", adr.getCity().getId())
                  .add("name", adr.getCity().getName()));
        }
        if (adr.getCountry() != null) {
          objBuilder.add("country", Json.createObjectBuilder()
                  .add("id", adr.getCity().getCountry().getId())
                  .add("name", adr.getCity().getCountry().getName()));
        }
        if (adr.getZipCode() != null) {
          objBuilder.add("zipCode", Json.createObjectBuilder()
                  .add("id", adr.getZipCode().getId())
                  .add("name", adr.getZipCode().getName()));
        }
        arrayBuilder.add(objBuilder);
      }
    }
    return arrayBuilder.build();
  }
}
