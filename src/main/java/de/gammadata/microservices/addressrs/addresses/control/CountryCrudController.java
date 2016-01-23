package de.gammadata.microservices.addressrs.addresses.control;

import de.gammadata.microservices.addressrs.addresses.entity.BaseQuerySpecification;
import de.gammadata.microservices.addressrs.addresses.entity.Country;
import java.util.List;
import javax.ejb.Stateless;

/**
 * CRUD Controller for Country.
 *
 * @author gfr
 */
@Stateless
public class CountryCrudController extends AbstractCrudController<Country, Country, BaseQuerySpecification> {

  @Override
  public List<Country> getList(BaseQuerySpecification querySpec) {
    return super.searchEntities(querySpec); //To change body of generated methods, choose Tools | Templates.
  }
  
  /**
   *
   * @return
   */
  @Override
  public Class<Country> getEntityClass() {
    return Country.class;
  }

  /**
   *
   * @return
   */
  @Override
  public String getSimpleSearchQueryName() {
    return Country.SIMPLE_SEARCH_QUERY_NAME;
  }

  /**
   *
   * @return
   */
  @Override
  public String getSimpleSearchCountName() {
    return Country.SIMPLE_COUNT_QUERY_NAME;
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
