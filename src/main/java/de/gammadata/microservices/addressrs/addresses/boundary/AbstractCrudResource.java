package de.gammadata.microservices.addressrs.addresses.boundary;

import de.gammadata.microservices.addressrs.addresses.control.AbstractCrudController;
import de.gammadata.microservices.addressrs.addresses.entity.BaseEntity;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Cities resources, exposed as /cities.
 *
 * @param <T> extends BaseEntity
 */
public abstract class AbstractCrudResource<T extends BaseEntity> {

  abstract public AbstractCrudController<T> getCrudController();

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public List<T> getAllEntities() {
    List<T> result = getCrudController().getAllEntities();
    return result;
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public T getEntity(@PathParam("id") Long id) {
    T result = getCrudController().getEntity(id);
    return result;
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public T saveOrUpdateEntity(T pIn) {
    T updated = getCrudController().saveOrUpdateEntity(pIn);
    T result = getEntity(updated.getId());
    return result;
  }

  @DELETE
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  public void deleteEntity(@PathParam("id") Long id) {
    getCrudController().deleteEntity(id);
  }
}
