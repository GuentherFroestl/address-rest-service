package de.gammadata.microservices.addressrs.contacts.boundary;

import de.gammadata.microservices.addressrs.common.boundary.AbstractCrudResource;
import de.gammadata.microservices.addressrs.common.control.AbstractCrudController;
import de.gammadata.microservices.addressrs.common.entity.SimpleQuerySpecification;
import de.gammadata.microservices.addressrs.contacts.control.ComAddressCrudController;
import de.gammadata.microservices.addressrs.contacts.entity.CommunicationAddress;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ws.rs.Path;

/**
 * jax-ws-rs Resource for CommunicationAddress;
 *
 * @author gfr
 */
@ManagedBean
@Path(ComAddressCrudResource.PATH)
public class ComAddressCrudResource extends AbstractCrudResource<CommunicationAddress, CommunicationAddress, SimpleQuerySpecification> {

  /**
   * jax-ws-rs path.
   */
  public static final String PATH = "/comaddresses";

  @EJB
  private ComAddressCrudController comAdrController;

  @Override
  public AbstractCrudController<CommunicationAddress, CommunicationAddress, SimpleQuerySpecification> getCrudController() {
    return comAdrController;
  }

}
