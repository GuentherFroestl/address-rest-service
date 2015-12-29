package de.gammadata.microservices.addressrs.addresses.control;

import de.gammadata.microservices.addressrs.addresses.entity.City;
import javax.ejb.Stateless;

/**
 * CRUD Controller for Cities.
 * @author gfr
 */
@Stateless
public class CityCrudController extends AbstractCrudController<City>{

  @Override
  public Class<City> getEntityClass() {
    return City.class;
  }
  
}
