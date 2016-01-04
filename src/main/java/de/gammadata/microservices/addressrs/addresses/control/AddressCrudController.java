package de.gammadata.microservices.addressrs.addresses.control;

import de.gammadata.microservices.addressrs.addresses.entity.Address;
import de.gammadata.microservices.addressrs.addresses.entity.BaseQuerySpecification;
import de.gammadata.microservices.addressrs.addresses.entity.City;
import de.gammadata.microservices.addressrs.addresses.entity.ZipCode;
import de.gammadata.microservices.addressrs.application.entity.AddressServiceException;
import javax.ejb.Stateless;

/**
 * CRUD Controller for Addresses.
 *
 * @author gfr
 */
@Stateless
public class AddressCrudController extends AbstractCrudController<Address, BaseQuerySpecification> {

  @Override
  public Class<Address> getEntityClass() {
    return Address.class;
  }

  @Override
  public Address saveOrUpdateEntity(Address pAdr) {
    relateEntities(pAdr);
    return super.saveOrUpdateEntity(pAdr);
  }

  private void relateEntities(final Address pAdr) {
    if (pAdr != null && pAdr.getCity() != null
            && pAdr.getCity().getId() != null && pAdr.getCity().getId() > 0
            && getEm() != null) {
      City c = getEm().find(City.class, pAdr.getCity().getId());
      if (c != null) {
        pAdr.setCity(c);
      } else {
        throw new AddressServiceException(AddressServiceException.Error.VALIDATION,
                "referenced City with id=" + pAdr.getCity().getId() + " not found");
      }
    }
    if (pAdr != null && pAdr.getZipCode() != null
            && pAdr.getZipCode().getId() != null && pAdr.getZipCode().getId() > 0
            && getEm() != null) {
      ZipCode z = getEm().find(ZipCode.class, pAdr.getZipCode().getId());
      if (z != null) {
        pAdr.setZipCode(z);
      } else {
        throw new AddressServiceException(AddressServiceException.Error.VALIDATION,
                "referenced Zipcode with id=" + pAdr.getZipCode().getId() + " not found");
      }
    }
  }

  @Override
  public String getSimpleSearchQueryName() {
    return Address.SIMPLE_SEARCH_QUERY_NAME;
  }

  @Override
  public String getSimpleSearchCountName() {
    return Address.SIMPLE_COUNT_QUERY_NAME;
  }

}
