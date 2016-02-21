package de.gammadata.microservices.addressrs.contacts.control;

import de.gammadata.microservices.addressrs.common.control.AbstractCrudController;
import de.gammadata.microservices.addressrs.common.entity.SimpleQuerySpecification;
import de.gammadata.microservices.addressrs.contacts.entity.Salutation;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author gfr
 */
@Stateless
public class SalutationCrudController extends AbstractCrudController<Salutation, Salutation, SimpleQuerySpecification> {

  @Override
  public List<Salutation> getListByQuery(SimpleQuerySpecification querySpec) {
    return super.searchEntities(querySpec);
  }

  @Override
  public Class<Salutation> getEntityClass() {
    return Salutation.class;
  }

  @Override
  public String getSimpleSearchQueryName() {
    return Salutation.SIMPLE_SEARCH_QUERY_NAME;
  }

  @Override
  public String getSimpleSearchCountName() {
    return Salutation.SIMPLE_COUNT_QUERY_NAME;
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
