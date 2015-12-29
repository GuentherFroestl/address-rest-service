package de.gammadata.microservices.addressrs.addresses.control;

import de.gammadata.microservices.addressrs.addresses.entity.Country;
import javax.ejb.Stateless;

/**
 * CRUD Controller for Country.
 *
 * @author gfr
 */
@Stateless
public class CountryCrudController extends AbstractCrudController<Country> {

  @Override
  public Class<Country> getEntityClass() {
    return Country.class;
  }
}
