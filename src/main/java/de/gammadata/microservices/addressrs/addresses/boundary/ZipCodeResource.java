package de.gammadata.microservices.addressrs.addresses.boundary;

import de.gammadata.microservices.addressrs.addresses.control.AbstractCrudController;
import de.gammadata.microservices.addressrs.addresses.control.ZipCodeCrudController;
import de.gammadata.microservices.addressrs.addresses.entity.BaseQuerySpecification;
import de.gammadata.microservices.addressrs.addresses.entity.ZipCode;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ws.rs.Path;

/**
 * Cities resources, exposed as /cities.
 */
@ManagedBean
@Path("/zipcodes")
public class ZipCodeResource extends AbstractCrudResource<ZipCode, BaseQuerySpecification> {

  @EJB
  ZipCodeCrudController zipCodeController;

  @Override
  public AbstractCrudController<ZipCode, BaseQuerySpecification> getCrudController() {
    return zipCodeController;
  }

}
