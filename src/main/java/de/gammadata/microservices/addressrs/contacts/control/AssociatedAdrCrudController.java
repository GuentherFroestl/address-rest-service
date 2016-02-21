package de.gammadata.microservices.addressrs.contacts.control;

import de.gammadata.microservices.addressrs.application.entity.AddressServiceException;
import de.gammadata.microservices.addressrs.common.control.AbstractCrudController;
import de.gammadata.microservices.addressrs.common.entity.BaseQuerySpecification;
import de.gammadata.microservices.addressrs.common.entity.SimpleQuerySpecification;
import de.gammadata.microservices.addressrs.contacts.entity.AssociatedBasicAddress;
import de.gammadata.microservices.addressrs.contacts.entity.AssociatedBuildingAddress;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author gfr
 */
public class AssociatedAdrCrudController extends AbstractCrudController<AssociatedBuildingAddress, AssociatedBasicAddress, SimpleQuerySpecification> {

  @Override
  public Class<AssociatedBuildingAddress> getEntityClass() {
    return AssociatedBuildingAddress.class;
  }

  @Override
  public String getSimpleSearchQueryName() {
    return AssociatedBuildingAddress.SIMPLE_SEARCH_QUERY_NAME;
  }

  @Override
  public String getSimpleSearchCountName() {
    return AssociatedBuildingAddress.SIMPLE_COUNT_QUERY_NAME;
  }

  @Override
  public String getNativeSearchQuery() {
    return AssociatedBuildingAddress.NATIVE_SEARCH_QUERY;
  }

  @Override
  public String getResultSetMappingName() {
    return AssociatedBuildingAddress.RESULT_SET_MAPPING_NAME;
  }

  /**
   * get all associated adresses for a contact.
   *
   * @param querySpec BaseQuerySpecification
   * @param contactId Long
   * @return List of AssociatedBuildingAddress
   */
  public List<AssociatedBasicAddress> getBuildingAdrForContact(BaseQuerySpecification querySpec, Long contactId) {
    if (contactId == null || contactId.equals(0l)) {
      throw new AddressServiceException(AddressServiceException.Error.VALIDATION, "contactId must not be null or 0");
    }
    Query query = getEm().createNativeQuery(adaptNativeQueryForSchema(
            AssociatedBuildingAddress.NATIVE_SEARCH_QUERY_FOR_CONTACTID), getResultSetMappingName());
    query.setParameter(1, contactId);
    setQueryLimits(query, querySpec);
    List<AssociatedBasicAddress> result = query.getResultList();
    return result;
  }
}
