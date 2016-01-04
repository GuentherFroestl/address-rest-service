package de.gammadata.microservices.addressrs.addresses.boundary;

import de.gammadata.microservices.addressrs.addresses.control.AbstractCrudController;
import de.gammadata.microservices.addressrs.addresses.control.AddressCrudController;
import de.gammadata.microservices.addressrs.addresses.entity.Address;
import de.gammadata.microservices.addressrs.addresses.entity.BaseQuerySpecification;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ws.rs.Path;

/**
 * AddressResource (exposed at "addresses" path)
 */
@ManagedBean
@Path("/addresses")
public class AddressResource extends AbstractCrudResource<Address, BaseQuerySpecification>{

  @EJB
  AddressCrudController adrController;

  @Override
  public AbstractCrudController<Address,BaseQuerySpecification> getCrudController() {
    return adrController;
  }
}
