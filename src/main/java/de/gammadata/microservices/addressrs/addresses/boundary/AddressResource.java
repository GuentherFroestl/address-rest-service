package de.gammadata.microservices.addressrs.addresses.boundary;

import de.gammadata.microservices.addressrs.addresses.control.AbstractCrudController;
import de.gammadata.microservices.addressrs.addresses.control.AddressCrudController;
import de.gammadata.microservices.addressrs.addresses.entity.Address;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ws.rs.Path;

/**
 * AddressResource (exposed at "addresses" path)
 */
@ManagedBean
@Path("/addresses")
public class AddressResource extends AbstractCrudResource<Address>{

  @EJB
  AddressCrudController adrController;

  @Override
  public AbstractCrudController<Address> getCrudController() {
    return adrController;
  }
}
