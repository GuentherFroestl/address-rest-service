package de.gammadata.microservices.addressrs.addresses.control;

import de.gammadata.microservices.addressrs.common.control.AbstractCrudController;
import de.gammadata.microservices.addressrs.common.entity.BaseEntity;
import de.gammadata.microservices.addressrs.common.entity.SimpleQuerySpecification;
import de.gammadata.microservices.addressrs.addresses.entity.Country;
import de.gammadata.microservices.addressrs.common.entity.EntityRelatedQuerySpec;
import de.gammadata.microservices.addressrs.addresses.entity.ZipCode;
import de.gammadata.microservices.addressrs.application.entity.AddressServiceException;
import java.util.List;
import java.util.Locale;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 * CRUD Controller for ZipCode.
 *
 * @author gfr
 */
@Stateless
public class ZipCodeCrudController extends AbstractCrudController<ZipCode, ZipCode, SimpleQuerySpecification> {

  /**
   *
   * @param querySpec
   * @return
   */
  public List<ZipCode> findZipCodesInCountry(EntityRelatedQuerySpec querySpec) {
    if (querySpec == null || querySpec.getRelatedId() == 0) {
      throw new AddressServiceException(AddressServiceException.Error.VALIDATION,
              "CountryID must not be null to query ZipCodes within country");
    }
    TypedQuery<ZipCode> query;
    query = getEm().createNamedQuery(ZipCode.QUERY_ZIPCODES_BY_COUNTRY_NAME, ZipCode.class);
    query.setParameter(BaseEntity.ID_PARAMETER, querySpec.getRelatedId());
    if (querySpec.getQuery() != null) {
      query.setParameter(BaseEntity.SIMPLE_SEARCH_QUERY_PARAMETER, querySpec.getQuery().toLowerCase(Locale.GERMAN) + "%");
    } else {
      query.setParameter(BaseEntity.SIMPLE_SEARCH_QUERY_PARAMETER, "%");
    }
    setQueryLimits(query, querySpec);
    List<ZipCode> results = query.getResultList();
    return results;
  }

  @Override
  public List<ZipCode> getListByQuery(SimpleQuerySpecification querySpec) {
    return super.searchEntities(querySpec);
  }

  /**
   *
   * @return
   */
  @Override
  public Class<ZipCode> getEntityClass() {
    return ZipCode.class;
  }

  /**
   *
   * @param pZipCode
   * @return
   */
  @Override
  public ZipCode saveOrUpdateEntity(ZipCode pZipCode) {
    relateEntities(pZipCode);
    return super.saveOrUpdateEntity(pZipCode);
  }

  private void relateEntities(ZipCode pZipCode) {
    if (pZipCode != null && pZipCode.getCountry() != null
            && pZipCode.getCountry().getId() != null && pZipCode.getCountry().getId() > 0
            && getEm() != null) {

      Country c = getEm().find(Country.class, pZipCode.getCountry().getId());
      if (c != null) {
        pZipCode.setCountry(c);
      } else {
        throw new AddressServiceException(AddressServiceException.Error.VALIDATION,
                "referenced Country with id=" + pZipCode.getCountry().getId() + " not found");
      }
    }
  }

  /**
   *
   * @return
   */
  @Override
  public String getSimpleSearchQueryName() {
    return ZipCode.SIMPLE_SEARCH_QUERY_NAME;
  }

  /**
   *
   * @return
   */
  @Override
  public String getSimpleSearchCountName() {
    return ZipCode.SIMPLE_COUNT_QUERY_NAME;
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
