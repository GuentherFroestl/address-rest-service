package de.gammadata.microservices.addressrs.addresses.control;

import de.gammadata.microservices.addressrs.addresses.entity.ZipCode;
import javax.ejb.Stateless;

/**
 * CRUD Controller for ZipCode.
 * @author gfr
 */
@Stateless
public class ZipCodeCrudController extends AbstractCrudController<ZipCode> {

  @Override
  public Class<ZipCode> getEntityClass() {
    return ZipCode.class;
  }
}
