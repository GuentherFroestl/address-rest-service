package de.gammadata.microservices.addressrs.common.control;

import de.gammadata.microservices.addressrs.common.entity.BaseEntity;
import de.gammadata.microservices.addressrs.common.entity.BaseQuerySpecification;
import de.gammadata.microservices.addressrs.application.control.EntityManagerQualifier;
import de.gammadata.microservices.addressrs.application.control.EntityManagerType;
import de.gammadata.microservices.addressrs.application.entity.AddressServiceException;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author gfr
 * @param <T> extends BaseEntity
 * @param <ListDTO> extends BaseEntity for List views
 * @param <Q> extends BaseQuerySpecification
 */
public abstract class AbstractCrudController<T extends BaseEntity, ListDTO extends BaseEntity, Q extends BaseQuerySpecification> {
  //  @PersistenceContext(name = "address-pu")
  @Inject
  @EntityManagerQualifier(EntityManagerType.MULTI_TENANT)
          EntityManager em;

  /**
   *
   * @return
   */
  public abstract Class<T> getEntityClass();

  /**
   *
   * @return
   */
  public abstract String getSimpleSearchQueryName();

  /**
   *
   * @return
   */
  public abstract String getSimpleSearchCountName();

  /**
   *
   * @return
   */
  public abstract String getNativeSearchQuery();

  /**
   *
   * @return
   */
  public abstract String getResultSetMappingName();


  /**
   *
   * @return
   */
  public EntityManager getEm() {
    if (em != null) {
      em.joinTransaction();
    }
    return em;
  }

  /**
   *
   * @param querySpec BaseQuerySpecification
   * @return List of ListDTO
   */
  public List<ListDTO> getListByQuery(BaseQuerySpecification querySpec) {
    Query query = getEm().createNativeQuery(adaptNativeQueryForSchema(getNativeSearchQuery()),
            getResultSetMappingName());
    String searchTxt = "%";
    if (querySpec != null && querySpec.getQuery() != null) {
      searchTxt = querySpec.getQuery().toLowerCase(Locale.GERMAN)+ "%";
    }
    query.setParameter(1, searchTxt);
    setQueryLimits(query, querySpec);

    List<ListDTO> result = query.getResultList();
    return result;
  }

  /**
   * deals with multitenancy in native queries
   *
   * @param nativeQuery String
   * @return adapted QueryString with actual tenant.id set
   */
  public String adaptNativeQueryForSchema(String nativeQuery) {
    String tenantId = (String) getEm().getProperties().get(BaseEntity.TENANT_ID);
    return nativeQuery.replaceAll(BaseEntity.TENANT_SCHEMA_NAME, tenantId);
  }

  /**
   * Set start and limit for a query
   *
   * @param query Query
   * @param querySpec BaseQuerySpecification
   */
  public void setQueryLimits(Query query, BaseQuerySpecification querySpec) {
    if (querySpec == null || query == null) {
      return;
    }
    if (querySpec.getStart() != null) {
      query.setFirstResult(querySpec.getStart());
    }
    if (querySpec.getLimit() != null) {
      query.setMaxResults(querySpec.getLimit());
    }
  }

  /**
   *
   * @param querySpec
   * @return
   */
  public List<T> searchEntities(BaseQuerySpecification querySpec) {
    TypedQuery<T> query;
    if (querySpec == null || querySpec.getQuery() == null || querySpec.getQuery().isEmpty()) {
      query = getEm().createQuery("Select t from "
              + this.getEntityClass().getSimpleName() + " t",
              this.getEntityClass());
    } else {
      query = getEm().createNamedQuery(getSimpleSearchQueryName(), this.getEntityClass());
      query.setParameter(BaseEntity.SIMPLE_SEARCH_QUERY_PARAMETER, querySpec.getQuery().toLowerCase(Locale.GERMAN) + "%");
    }
    setQueryLimits(query, querySpec);
    List<T> results = query.getResultList();
    return results;
  }

  /**
   *
   * @param querySpec
   * @return
   */
  public Long countEntitiesByQuery(BaseQuerySpecification querySpec) {
    if (querySpec == null || querySpec.getQuery() == null || querySpec.getQuery().isEmpty()) {
      return countEntities();
    } else {
      TypedQuery<Long> query;
      query = getEm().createNamedQuery(getSimpleSearchCountName(), Long.class);
      query.setParameter(BaseEntity.SIMPLE_SEARCH_QUERY_PARAMETER, querySpec.getQuery().toLowerCase(Locale.GERMAN) + "%");
      Long result = query.getSingleResult();
      return result;
    }

  }

  private Long countEntities() {
    CriteriaBuilder criteriaBuilder = getEm().getCriteriaBuilder();
    CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
    countQuery.select(criteriaBuilder.count(countQuery.from(this.getEntityClass())));
    Long count = getEm().createQuery(countQuery).getSingleResult();
    return count;
  }

  /**
   *
   * @param id
   * @return
   */
  public T getEntity(Long id) {
    T res = getEm().find(this.getEntityClass(), id);
    return res;
  }

  /**
   *
   * @param pAdr
   * @return
   */
  public T saveOrUpdateEntity(T pAdr) {
    T res = getEm().merge(pAdr);
    return res;
  }

  /**
   *
   * @param id
   */
  public void deleteEntity(Long id) {
    T entity = getEntity(id);
    if (entity == null) {
      throw new AddressServiceException(AddressServiceException.Error.DATABASE, "No Entity found for deletion with id=" + id);
    }
    getEm().remove(entity);
  }

}
