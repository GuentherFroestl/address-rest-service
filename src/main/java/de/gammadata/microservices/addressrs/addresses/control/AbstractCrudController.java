package de.gammadata.microservices.addressrs.addresses.control;

import de.gammadata.microservices.addressrs.addresses.entity.BaseEntity;
import de.gammadata.microservices.addressrs.addresses.entity.BaseQuerySpecification;
import de.gammadata.microservices.addressrs.application.entity.AddressServiceException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author gfr
 * @param <T> extends BaseEntity
 * @param <Q> extends BaseQuerySpecification
 */
public abstract class AbstractCrudController<T extends BaseEntity, Q extends BaseQuerySpecification> {

  public abstract Class<T> getEntityClass();

  public abstract String getSimpleSearchQueryName();

  public abstract String getSimpleSearchCountName();

  @SuppressWarnings("unchecked")
  public AbstractCrudController() {

  }

  @PersistenceContext(name = "address-pu")
  EntityManager em;

  protected EntityManager getEm() {
    return em;
  }

  public List<T> getEntities(BaseQuerySpecification querySpec) {
    TypedQuery<T> query;
    if (querySpec == null || querySpec.getQuery() == null || querySpec.getQuery().isEmpty()) {
      query = getEm().createQuery("Select t from "
              + this.getEntityClass().getSimpleName() + " t",
              this.getEntityClass());
    } else {
      query = getEm().createNamedQuery(getSimpleSearchQueryName(), this.getEntityClass());
      query.setParameter(BaseEntity.SIMPLE_SEARCH_QUERY_PARAMETER, querySpec.getQuery().toLowerCase() + "%");
    }

    if (querySpec != null && querySpec.getStart() != null) {
      query.setFirstResult(querySpec.getStart());
    }
    if (querySpec != null && querySpec.getLimit() != null) {
      query.setMaxResults(querySpec.getLimit());
    }
    List<T> results = query.getResultList();
    return results;
  }

  public Long countEntities(BaseQuerySpecification querySpec) {
    if (querySpec == null || querySpec.getQuery() == null || querySpec.getQuery().isEmpty()) {
      return countEntities();
    } else {
      TypedQuery<Long> query;
      query = getEm().createNamedQuery(getSimpleSearchCountName(), Long.class);
      query.setParameter(BaseEntity.SIMPLE_SEARCH_QUERY_PARAMETER, querySpec.getQuery().toLowerCase() + "%");
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

  public T getEntity(Long id) {
    T res = getEm().find(this.getEntityClass(), id);
    return res;
  }

  public T saveOrUpdateEntity(T pAdr) {
    T res = getEm().merge(pAdr);
    return res;
  }

  public void deleteEntity(Long id) {
    T entity = getEntity(id);
    if (entity == null) {
      throw new AddressServiceException(AddressServiceException.Error.DATABASE, "No Entity found for deletion with id=" + id);
    }
    getEm().remove(entity);
  }

}
