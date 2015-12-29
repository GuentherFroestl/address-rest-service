package de.gammadata.microservices.addressrs.addresses.boundary;

import de.gammadata.microservices.addressrs.addresses.control.AbstractCrudController;
import de.gammadata.microservices.addressrs.addresses.control.ZipCodeCrudController;
import de.gammadata.microservices.addressrs.addresses.entity.ZipCode;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ws.rs.Path;

/**
 * Cities resources, exposed as /cities.
 */
@ManagedBean
@Path("/zipcodes")
public class ZipCodeResource extends AbstractCrudResource<ZipCode> {

  @EJB
  ZipCodeCrudController zipCodeController;

  @Override
  AbstractCrudController<ZipCode> getCrudController() {
    return zipCodeController;
  }

}
