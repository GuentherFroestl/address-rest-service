package de.gammadata.microservices.addressrs.addresses.boundary;

import de.gammadata.microservices.addressrs.common.boundary.AbstractCrudResource;
import de.gammadata.microservices.addressrs.common.control.AbstractCrudController;
import de.gammadata.microservices.addressrs.addresses.control.ZipCodeCrudController;
import de.gammadata.microservices.addressrs.common.entity.BaseQuerySpecification;
import de.gammadata.microservices.addressrs.addresses.entity.ZipCode;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ws.rs.Path;

/**
 * Cities resources, exposed as /cities.
 */
@ManagedBean
@Path(ZipCodeResource.PATH)
public class ZipCodeResource extends AbstractCrudResource<ZipCode, ZipCode, BaseQuerySpecification> {

  /**
   *
   */
  public static final String PATH = "/zipcodes";


  @EJB
  ZipCodeCrudController zipCodeController;

  /**
   *
   * @return
   */
  @Override
  public AbstractCrudController<ZipCode, ZipCode, BaseQuerySpecification> getCrudController() {
    return zipCodeController;
  }


}
