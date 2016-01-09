package de.gammadata.microservices.addressrs.addresses.control;

import de.gammadata.microservices.addressrs.addresses.entity.BaseQuerySpecification;
import de.gammadata.microservices.addressrs.addresses.entity.Country;
import de.gammadata.microservices.addressrs.addresses.entity.ZipCode;
import de.gammadata.microservices.addressrs.application.entity.AddressServiceException;
import javax.ejb.Stateless;

/**
 * CRUD Controller for ZipCode.
 *
 * @author gfr
 */
@Stateless
public class ZipCodeCrudController extends AbstractCrudController<ZipCode, BaseQuerySpecification> {

  @Override
  public Class<ZipCode> getEntityClass() {
    return ZipCode.class;
  }

  @Override
  public ZipCode saveOrUpdateEntity(ZipCode pZipCode) {
    relateEntities(pZipCode);
    return super.saveOrUpdateEntity(pZipCode);
  }

  private void relateEntities(ZipCode pZipCode) {
    if (pZipCode != null && pZipCode.getCountry() != null
            && pZipCode.getCountry().getId() != null && pZipCode.getCountry().getId() > 0
            && getEm() != null) {

      Country c = getEm().find(Country.class, pZipCode.getCountry().getId());
      if (c != null) {
        pZipCode.setCountry(c);
      } else {
        throw new AddressServiceException(AddressServiceException.Error.VALIDATION,
                "referenced Country with id=" + pZipCode.getCountry().getId() + " not found");
      }
    }
  }

  @Override
  public String getSimpleSearchQueryName() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String getSimpleSearchCountName() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

}
