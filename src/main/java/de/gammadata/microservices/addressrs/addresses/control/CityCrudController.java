package de.gammadata.microservices.addressrs.addresses.control;

import de.gammadata.microservices.addressrs.addresses.entity.BaseQuerySpecification;
import de.gammadata.microservices.addressrs.addresses.entity.City;
import de.gammadata.microservices.addressrs.addresses.entity.Country;
import de.gammadata.microservices.addressrs.application.entity.AddressServiceException;
import javax.ejb.Stateless;

/**
 * CRUD Controller for Cities.
 *
 * @author gfr
 */
@Stateless
public class CityCrudController extends AbstractCrudController<City, BaseQuerySpecification> {

  /**
   *
   * @return
   */
  @Override
  public Class<City> getEntityClass() {
    return City.class;
  }

  /**
   *
   * @param pCity
   * @return
   */
  @Override
  public City saveOrUpdateEntity(City pCity) {
    relateEntities(pCity);
    return super.saveOrUpdateEntity(pCity); //To change body of generated methods, choose Tools | Templates.
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

}
