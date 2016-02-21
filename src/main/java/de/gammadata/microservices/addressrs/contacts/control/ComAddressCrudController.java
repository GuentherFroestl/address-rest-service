package de.gammadata.microservices.addressrs.contacts.control;

import de.gammadata.microservices.addressrs.common.control.AbstractCrudController;
import de.gammadata.microservices.addressrs.common.entity.SimpleQuerySpecification;
import de.gammadata.microservices.addressrs.contacts.entity.CommunicationAddress;
import java.util.List;

/**
 *
 * @author gfr
 */
public class ComAddressCrudController extends AbstractCrudController<CommunicationAddress, CommunicationAddress, SimpleQuerySpecification> {

  @Override
  public List<CommunicationAddress> getListByQuery(SimpleQuerySpecification querySpec) {
    return super.searchEntities(querySpec);
  }

  @Override
  public Class<CommunicationAddress> getEntityClass() {
    return CommunicationAddress.class;
  }

  @Override
  public String getSimpleSearchQueryName() {
    return CommunicationAddress.SIMPLE_SEARCH_QUERY_NAME;
  }

  @Override
  public String getSimpleSearchCountName() {
    return CommunicationAddress.SIMPLE_COUNT_QUERY_NAME;
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
