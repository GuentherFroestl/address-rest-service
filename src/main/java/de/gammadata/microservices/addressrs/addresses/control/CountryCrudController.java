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
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String getSimpleSearchCountName() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
}
