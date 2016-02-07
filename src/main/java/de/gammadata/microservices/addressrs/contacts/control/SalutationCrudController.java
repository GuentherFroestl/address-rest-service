package de.gammadata.microservices.addressrs.contacts.control;

import de.gammadata.microservices.addressrs.common.control.AbstractCrudController;
import de.gammadata.microservices.addressrs.common.entity.BaseQuerySpecification;
import de.gammadata.microservices.addressrs.contacts.entity.Salutation;
import javax.ejb.Stateless;

/**
 *
 * @author gfr
 */
@Stateless
public class SalutationCrudController extends AbstractCrudController<Salutation, Salutation, BaseQuerySpecification> {

  @Override
  public Class<Salutation> getEntityClass() {
    return Salutation.class;
  }

  @Override
  public String getSimpleSearchQueryName() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String getSimpleSearchCountName() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String getNativeSearchQuery() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String getResultSetMappingName() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

}
