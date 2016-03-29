package de.gammadata.microservices.addressrs.addresses.control;

import de.gammadata.microservices.addressrs.addresses.entity.Building;
import de.gammadata.microservices.addressrs.application.entity.AddressServiceException;
import de.gammadata.microservices.addressrs.common.control.AbstractCrudController;
import de.gammadata.microservices.addressrs.common.entity.BaseEntity;
import de.gammadata.microservices.addressrs.common.entity.EntityRelatedQuerySpec;
import de.gammadata.microservices.addressrs.common.entity.SimpleQuerySpecification;
import java.util.List;
import java.util.Locale;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 * CRUD operations for Buildings.
 *
 * @author gfr
 */
@Stateless
public class BuildingCrudController extends AbstractCrudController<Building, Building, SimpleQuerySpecification> {

    /**
   *
   * @param querySpec
   * @return
   */
  public List<Building> findBuildingsForStreet(EntityRelatedQuerySpec querySpec) {

    if (querySpec == null || querySpec.getRelatedId() == 0) {
      throw new AddressServiceException(AddressServiceException.Error.VALIDATION, "StreetID must not be null to query buildings");
    }

    TypedQuery<Building> query;

      query = getEm().createNamedQuery(Building.BUILDING_FOR_STREET_SEARCH_QUERY_NAME, Building.class);
      query.setParameter(BaseEntity.SIMPLE_SEARCH_QUERY_PARAMETER, querySpec.getQuery().toLowerCase(Locale.GERMAN) + "%");
      query.setParameter(Building.ADR_ID_QUERY_PARAMETER, querySpec.getRelatedId());
    
    setQueryLimits(query, querySpec);
    List<Building> results = query.getResultList();
    return results;

  }
    
    @Override
    public Class<Building> getEntityClass() {
        return Building.class;
    }

    @Override
    public String getSimpleSearchQueryName() {
        return Building.BUILDING_SIMPLE_SEARCH_QUERY_NAME;
    }

    @Override
    public String getSimpleSearchCountName() {
        return Building.BUILDING_SIMPLE_COUNT_QUERY_NAME;
    }

    @Override
    public String getNativeSearchQuery() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getResultSetMappingName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
