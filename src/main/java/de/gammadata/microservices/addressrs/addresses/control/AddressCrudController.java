package de.gammadata.microservices.addressrs.addresses.control;

import de.gammadata.microservices.addressrs.addresses.entity.Address;
import de.gammadata.microservices.addressrs.addresses.entity.AddressBasics;
import de.gammadata.microservices.addressrs.addresses.entity.BaseEntity;
import de.gammadata.microservices.addressrs.addresses.entity.BaseQuerySpecification;
import de.gammadata.microservices.addressrs.addresses.entity.Building;
import de.gammadata.microservices.addressrs.addresses.entity.City;
import de.gammadata.microservices.addressrs.addresses.entity.Country;
import de.gammadata.microservices.addressrs.addresses.entity.ZipCode;
import de.gammadata.microservices.addressrs.application.entity.AddressServiceException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * CRUD Controller for Addresses.
 *
 * @author gfr
 */
@Stateless
public class AddressCrudController extends AbstractCrudController<Address, BaseQuerySpecification> {

  /**
   *
   * @param querySpec
   * @return
   */
  public List<AddressBasics> findNative(BaseQuerySpecification querySpec) {
    Query query = getEm().createNativeQuery(Address.NATIVE_SEARCH_QUERY, "AddressBasicsContructor");
    String searchTxt = "%";
    if (querySpec != null && querySpec.getQuery() != null) {
      searchTxt = querySpec.getQuery().toLowerCase() + "%";
    }
    query.setParameter(1, searchTxt);
    if (querySpec != null && querySpec.getStart() != null) {
      query.setFirstResult(querySpec.getStart());
    }
    if (querySpec != null && querySpec.getLimit() != null) {
      query.setMaxResults(querySpec.getLimit());
    }

    List<AddressBasics> result = query.getResultList();
    return result;
  }

  /**
   *
   * @param querySpec
   * @param adrId
   * @return
   */
  public List<Building> findBuildings(BaseQuerySpecification querySpec, Long adrId) {

    if (adrId == null || adrId == 0) {
      throw new RuntimeException("AddressID must not be null to query buildings");
    }

    TypedQuery<Building> query;
    if (querySpec == null || querySpec.getQuery() == null || querySpec.getQuery().isEmpty()) {
      Address adr = getEntity(adrId);
      if (adr != null) {
        return adr.getBuildings();
      } else {
        return null;
      }
    } else {
      query = getEm().createNamedQuery(Building.BUILDING_FOR_ADR_SEARCH_QUERY_NAME, Building.class);
      query.setParameter(BaseEntity.SIMPLE_SEARCH_QUERY_PARAMETER, querySpec.getQuery().toLowerCase() + "%");
      query.setParameter(Building.ADR_ID_QUERY_PARAMETER, adrId);
    }

    if (querySpec.getStart() != null) {
      query.setFirstResult(querySpec.getStart());
    }
    if (querySpec.getLimit() != null) {
      query.setMaxResults(querySpec.getLimit());
    }
    List<Building> results = query.getResultList();
    return results;

  }

  /**
   *
   * @return
   */
  @Override
  public Class<Address> getEntityClass() {
    return Address.class;
  }

  /**
   *
   * @param pAdr
   * @return
   */
  @Override
  public Address saveOrUpdateEntity(Address pAdr) {
    relateEntities(pAdr);
    return super.saveOrUpdateEntity(pAdr);
  }

  private void relateEntities(final Address pAdr) {
    if (pAdr == null) {
      return;
    }
    if (pAdr.getCity() != null
            && pAdr.getCity().getId() != null && pAdr.getCity().getId() > 0
            && getEm() != null) {
      City c = getEm().find(City.class, pAdr.getCity().getId());
      if (c != null) {
        pAdr.setCity(c);
      } else {
        throw new AddressServiceException(AddressServiceException.Error.VALIDATION,
                "referenced City with id=" + pAdr.getCity().getId() + " not found");
      }
    }
    if (pAdr.getZipCode() != null
            && pAdr.getZipCode().getId() != null && pAdr.getZipCode().getId() > 0
            && getEm() != null) {
      ZipCode z = getEm().find(ZipCode.class, pAdr.getZipCode().getId());
      if (z != null) {
        pAdr.setZipCode(z);
      } else {
        throw new AddressServiceException(AddressServiceException.Error.VALIDATION,
                "referenced Zipcode with id=" + pAdr.getZipCode().getId() + " not found");
      }
    }

    if (pAdr.getCountry() == null && pAdr.getCity() != null && pAdr.getCity().getCountry() != null) {
      pAdr.setCountry(pAdr.getCity().getCountry());
    } else if (pAdr.getCountry() == null && pAdr.getZipCode() != null && pAdr.getZipCode().getCountry() != null) {
      pAdr.setCountry(pAdr.getZipCode().getCountry());
    }

    if (pAdr.getCountry() != null
            && pAdr.getCountry().getId() != null && pAdr.getCountry().getId() > 0
            && getEm() != null) {
      Country c = getEm().find(Country.class, pAdr.getCountry().getId());
      if (c != null) {
        pAdr.setCountry(c);
      } else {
        throw new AddressServiceException(AddressServiceException.Error.VALIDATION,
                "referenced Country with id=" + pAdr.getCountry().getId() + " not found");
      }
    }
  }

  /**
   *
   * @return
   */
  @Override
  public String getSimpleSearchQueryName() {
    return Address.SIMPLE_SEARCH_QUERY_NAME;
  }

  /**
   *
   * @return
   */
  @Override
  public String getSimpleSearchCountName() {
    return Address.SIMPLE_COUNT_QUERY_NAME;
  }

}
