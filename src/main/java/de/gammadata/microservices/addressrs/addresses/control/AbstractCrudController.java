package de.gammadata.microservices.addressrs.addresses.control;

import de.gammadata.microservices.addressrs.addresses.entity.BaseEntity;
import de.gammadata.microservices.addressrs.application.entity.AddressServiceException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author gfr
 * @param <T> extends BaseEntity
 */
public abstract class AbstractCrudController<T extends BaseEntity> {

  @PersistenceContext(name = "address-pu")
  EntityManager em;

  public abstract Class<T> getEntityClass();

  @SuppressWarnings("unchecked")
  public AbstractCrudController() {

  }

  public List<T> getAllEntities() {
    List<T> res = em.createQuery("Select t from " + this.getEntityClass().getSimpleName() + " t").getResultList();
    return res;
  }

  public T getEntity(Long id) {
    T res = em.find(this.getEntityClass(), id);
    return res;
  }

  public T saveOrUpdateEntity(T pAdr) {
    T res = em.merge(pAdr);
    return res;
  }

  public void deleteEntity(Long id) {
    T entity = getEntity(id);
    if (entity == null) {
      throw new AddressServiceException(AddressServiceException.Error.DATABASE, "No Entity found for deletion with id=" + id);
    }
    em.remove(entity);
  }

}
