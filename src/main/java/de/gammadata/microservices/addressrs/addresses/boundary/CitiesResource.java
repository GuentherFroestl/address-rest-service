package de.gammadata.microservices.addressrs.addresses.boundary;

import de.gammadata.microservices.addressrs.addresses.control.AbstractCrudController;
import de.gammadata.microservices.addressrs.addresses.control.CityCrudController;
import de.gammadata.microservices.addressrs.addresses.entity.City;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ws.rs.Path;

/**
 * Cities resources, exposed as /cities.
 */
@ManagedBean
@Path("/cities")
public class CitiesResource  extends AbstractCrudResource<City>{

  @EJB
  CityCrudController cityController;

  @Override
  AbstractCrudController<City> getCrudController() {
    return cityController;
  }

}
