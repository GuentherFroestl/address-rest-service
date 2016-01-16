package de.gammadata.microservices.addressrs.addresses.boundary;

import de.gammadata.microservices.addressrs.addresses.control.AbstractCrudController;
import de.gammadata.microservices.addressrs.addresses.control.CountryCrudController;
import de.gammadata.microservices.addressrs.addresses.entity.BaseQuerySpecification;
import de.gammadata.microservices.addressrs.addresses.entity.Country;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ws.rs.Path;

/**
 * Countries resources, exposed as /Countries.
 */
@ManagedBean
@Path("/countries")
public class CountriesResource extends AbstractCrudResource<Country, BaseQuerySpecification> {

  @EJB
  CountryCrudController countryController;

  /**
   *
   * @return
   */
  @Override
  public AbstractCrudController<Country, BaseQuerySpecification> getCrudController() {
    return countryController;
  }

}
