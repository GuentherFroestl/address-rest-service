package de.gammadata.microservices.addressrs.contacts.boundary;

import de.gammadata.microservices.addressrs.common.boundary.AbstractCrudResource;
import de.gammadata.microservices.addressrs.common.control.AbstractCrudController;
import de.gammadata.microservices.addressrs.common.entity.SimpleQuerySpecification;
import de.gammadata.microservices.addressrs.contacts.control.SalutationCrudController;
import de.gammadata.microservices.addressrs.contacts.entity.Salutation;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ws.rs.Path;

/**
 * jax-ws-rs Resource for Salutation;
 *
 * @author gfr
 */
@ManagedBean
@Path(SalutationResource.PATH)
public class SalutationResource extends AbstractCrudResource<Salutation, Salutation, SimpleQuerySpecification> {

  /**
   * jax-ws-rs path.
   */
  public static final String PATH = "/salutations";
  @EJB
  private SalutationCrudController salutationCrudController;

  @Override
  public AbstractCrudController<Salutation, Salutation, SimpleQuerySpecification> getCrudController() {
    return salutationCrudController;
  }

}
