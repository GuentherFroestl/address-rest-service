package de.gammadata.microservices.addressrs.contacts.control;

import de.gammadata.microservices.addressrs.common.control.AbstractCrudController;
import de.gammadata.microservices.addressrs.common.entity.BaseQuerySpecification;
import de.gammadata.microservices.addressrs.contacts.entity.AssociatedAddress;

/**
 *
 * @author gfr
 */
public class AssociatedAdrCrudController extends AbstractCrudController<AssociatedAddress,AssociatedAddress,BaseQuerySpecification>{

  @Override
  public Class<AssociatedAddress> getEntityClass() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    throw new UnsupportedOperationException("Not supported yet."); 
  }

  @Override
  public String getResultSetMappingName() {
    throw new UnsupportedOperationException("Not supported yet."); 
  }
  
}
