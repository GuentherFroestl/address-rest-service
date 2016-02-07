package de.gammadata.microservices.addressrs.addresses.control;

import de.gammadata.microservices.addressrs.common.control.AbstractCrudController;
import de.gammadata.microservices.addressrs.common.entity.BaseEntity;
import de.gammadata.microservices.addressrs.common.entity.BaseQuerySpecification;
import de.gammadata.microservices.addressrs.addresses.entity.City;
import de.gammadata.microservices.addressrs.addresses.entity.Country;
import de.gammadata.microservices.addressrs.common.entity.EntityRelatedQuerySpec;
import de.gammadata.microservices.addressrs.application.entity.AddressServiceException;
import java.util.List;
import java.util.Locale;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 * CRUD Controller for Cities.
 *
 * @author gfr
 */
@Stateless
public class CityCrudController extends AbstractCrudController<City, City, BaseQuerySpecification> {

  /**
   *
   * @param querySpec
   * @return
   */
  public List<City> findCitiesInCountry(EntityRelatedQuerySpec querySpec) {
    if (querySpec == null || querySpec.getRelatedId() == 0) {
      throw new AddressServiceException(AddressServiceException.Error.VALIDATION, "CountryID must not be null to query cities within country");
    }
    TypedQuery<City> query;
    query = getEm().createNamedQuery(City.QUERY_CITIES_BY_COUNTRY_NAME, City.class);
    query.setParameter(BaseEntity.ID_PARAMETER, querySpec.getRelatedId());
    String queryStr = "";
    if (querySpec.getQuery() != null) {
      queryStr = querySpec.getQuery();
    }
    query.setParameter(BaseEntity.SIMPLE_SEARCH_QUERY_PARAMETER, queryStr.toLowerCase(Locale.GERMAN) + "%");

    setQueryLimits(query, querySpec);
    List<City> results = query.getResultList();
    return results;
  }

  /**
   *
   * @return
   */
  @Override
  public Class<City> getEntityClass() {
    return City.class;
  }

  @Override
  public List<City> getListByQuery(BaseQuerySpecification querySpec) {
    return super.searchEntities(querySpec);
  }

  /**
   *
   * @param pCity
   * @return
   */
  @Override
  public City saveOrUpdateEntity(City pCity) {
    relateEntities(pCity);
    return super.saveOrUpdateEntity(pCity);
  }

  private void relateEntities(City pCity) {
    if (pCity != null && pCity.getCountry() != null
            && pCity.getCountry().getId() != null && pCity.getCountry().getId() > 0
            && getEm() != null) {

      Country c = getEm().find(Country.class, pCity.getCountry().getId());
      if (c != null) {
        pCity.setCountry(c);
      } else {
        throw new AddressServiceException(AddressServiceException.Error.VALIDATION,
                "referenced Country with id=" + pCity.getCountry().getId() + " not found");
      }

    }
  }

  /**
   *
   * @return
   */
  @Override
  public String getSimpleSearchQueryName() {
    return City.SIMPLE_SEARCH_QUERY_NAME;
  }

  /**
   *
   * @return
   */
  @Override
  public String getSimpleSearchCountName() {
    return City.SIMPLE_COUNT_QUERY_NAME;
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
