package de.gammadata.microservices.addressrs.addresses.boundary;

import de.gammadata.microservices.addressrs.addresses.control.AbstractCrudController;
import de.gammadata.microservices.addressrs.addresses.control.CityCrudController;
import de.gammadata.microservices.addressrs.addresses.entity.BaseQuerySpecification;
import de.gammadata.microservices.addressrs.addresses.entity.City;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ws.rs.Path;

/**
 * Cities resources, exposed as /cities.
 */
@ManagedBean
@Path(CitiesResource.PATH)
public class CitiesResource extends AbstractCrudResource<City, City, BaseQuerySpecification> {

  /**
   *
   */
  public static final String PATH = "/cities";


  @EJB
  CityCrudController cityController;

  /**
   *
   * @return
   */
  @Override
  public AbstractCrudController<City, City, BaseQuerySpecification> getCrudController() {
    return cityController;
  }
}
