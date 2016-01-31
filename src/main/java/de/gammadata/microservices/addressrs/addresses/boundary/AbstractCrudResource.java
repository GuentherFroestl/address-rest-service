package de.gammadata.microservices.addressrs.addresses.boundary;

import de.gammadata.microservices.addressrs.addresses.control.AbstractCrudController;
import de.gammadata.microservices.addressrs.addresses.entity.BaseEntity;
import de.gammadata.microservices.addressrs.addresses.entity.BaseQuerySpecification;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Cities resources, exposed as /cities.
 *
 * @param <T> T extends BaseEntity
 * @param <L> l extends BaseEntity, lightweight Representation
 * @param <Q> Q extends BaseQuerySpecification
 */
public abstract class AbstractCrudResource<T extends BaseEntity, L extends BaseEntity,
        Q extends BaseQuerySpecification> {
  
    /**
   *
   */
  public static final String QUERY_PATH = "/query";
  public static final String COUNT_PATH = "/count";
  public static final String BY_ID_PATH ="/{id}";

  /**
   *
   * @return AbstractCrudController
   */
  abstract public AbstractCrudController<T, L, Q> getCrudController();

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
  @Path(QUERY_PATH)
  public List<L> findByQuery(
          @QueryParam("start") Integer start,
          @QueryParam("limit") Integer limit,
          @QueryParam("query") String query) {
    BaseQuerySpecification querySpec = new BaseQuerySpecification(limit, start,query);
    List<L> result = getCrudController().getListByQuery(querySpec);
    return result;
  }

  /**
   *
   * @param query
   * @return
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path(COUNT_PATH)
  public Long countEntitiesByQuery(@QueryParam("query") String query) {
    BaseQuerySpecification querySpec = new BaseQuerySpecification();
    Long result = getCrudController().countEntitiesByQuery(querySpec);
    return result;
  }

  /**
   *
   * @param id
   * @return
   */
  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public T getEntity(@PathParam("id") Long id) {
    T result = getCrudController().getEntity(id);
    return result;
  }

  /**
   *
   * @param pIn
   * @return
   */
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public T saveOrUpdateEntity(T pIn) {
    T updated = getCrudController().saveOrUpdateEntity(pIn);
    T result = getEntity(updated.getId());
    return result;
  }

  /**
   *
   * @param id
   */
  @DELETE
  @Path(BY_ID_PATH)
  @Consumes(MediaType.APPLICATION_JSON)
  public void deleteEntity(@PathParam("id") Long id) {
    getCrudController().deleteEntity(id);
  }
}
