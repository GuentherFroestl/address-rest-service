package de.gammadata.microservices.addressrs.contacts.boundary;

import de.gammadata.microservices.addressrs.common.boundary.AbstractCrudResource;
import de.gammadata.microservices.addressrs.common.control.AbstractCrudController;
import de.gammadata.microservices.addressrs.common.entity.SimpleQuerySpecification;
import de.gammadata.microservices.addressrs.contacts.control.ContactCrudController;
import de.gammadata.microservices.addressrs.contacts.entity.Contact;
import de.gammadata.microservices.addressrs.contacts.entity.ContactBasics;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ws.rs.Path;

/**
 *
 * @author gfr
 */
@ManagedBean
@Path(ContactCrudResource.PATH)
public class ContactCrudResource extends AbstractCrudResource<Contact, ContactBasics, SimpleQuerySpecification> {

  /**
   * jax-ws-rs path.
   */
  public static final String PATH = "/contacts";
  @EJB
  ContactCrudController contactCrudController;

  @Override
  public AbstractCrudController<Contact, ContactBasics, SimpleQuerySpecification> getCrudController() {
    return contactCrudController;
  }

}
