package de.gammadata.microservices.addressrs.addresses.control;

import de.gammadata.microservices.addressrs.addresses.entity.BaseQuerySpecification;
import de.gammadata.microservices.addressrs.addresses.entity.Country;
import javax.ejb.Stateless;

/**
 * CRUD Controller for Country.
 *
 * @author gfr
 */
@Stateless
public class CountryCrudController extends AbstractCrudController<Country, BaseQuerySpecification> {

  @Override
  public Class<Country> getEntityClass() {
    return Country.class;
  }

  @Override
  public String getSimpleSearchQueryName() {
    return Country.SIMPLE_SEARCH_QUERY_NAME;
  }

  @Override
  public String getSimpleSearchCountName() {
    return Country.SIMPLE_COUNT_QUERY_NAME;
  }
}
