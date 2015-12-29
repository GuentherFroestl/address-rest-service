package de.gammadata.microservices.addressrs.addresses.control;

import de.gammadata.microservices.addressrs.addresses.entity.Address;
import javax.ejb.Stateless;

/**
 * CRUD Controller for Addresses.
 *
 * @author gfr
 */
@Stateless
public class AddressCrudController extends AbstractCrudController<Address> {

  @Override
  public Class<Address> getEntityClass() {
    return Address.class;
  }

}
