package de.gammadata.microservices.addressrs.contacts.control;

import de.gammadata.microservices.addressrs.common.control.AbstractCrudController;
import de.gammadata.microservices.addressrs.common.entity.BaseQuerySpecification;
import de.gammadata.microservices.addressrs.contacts.entity.Contact;
import de.gammadata.microservices.addressrs.contacts.entity.ContactBasics;
import javax.ejb.Stateless;

/**
 *
 * @author gfr
 */
@Stateless
public class ContactCrudController extends AbstractCrudController<Contact, ContactBasics, BaseQuerySpecification> {

  @Override
  public Class<Contact> getEntityClass() {
    return Contact.class;
  }

  @Override
  public String getSimpleSearchQueryName() {
    return Contact.SIMPLE_SEARCH_QUERY_NAME;
  }

  @Override
  public String getSimpleSearchCountName() {
    return Contact.SIMPLE_COUNT_QUERY_NAME;
  }

  @Override
  public String getNativeSearchQuery() {
    return Contact.NATIVE_SEARCH_QUERY;
  }

  @Override
  public String getResultSetMappingName() {
    return Contact.RESULT_SET_MAPPING_NAME;
  }

}
