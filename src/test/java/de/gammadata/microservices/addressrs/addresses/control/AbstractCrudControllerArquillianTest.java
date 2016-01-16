package de.gammadata.microservices.addressrs.addresses.control;

import de.gammadata.microservices.addressrs.addresses.entity.Address;
import de.gammadata.microservices.addressrs.addresses.entity.City;
import de.gammadata.microservices.addressrs.addresses.entity.Country;
import de.gammadata.microservices.addressrs.addresses.entity.ZipCode;
import java.util.List;

/**
 *
 * @author gfr
 */
public class AbstractCrudControllerArquillianTest {
  
  /**
   * Cleanup Utillity for Tests. If one or more controllers are null not cleanup for that entities will be performed.
   *
   * @param adrController AddressCrudController
   * @param zipCodeController ZipCodeCrudController
   * @param cityController CityCrudController
   * @param countryController CountryCrudController
   */
  public static void deleteAllEntities(AddressCrudController adrController, ZipCodeCrudController zipCodeController, CityCrudController cityController, CountryCrudController countryController) {
    if (adrController != null) {
      List<Address> lAdr = adrController.getEntities(null);
      if (lAdr != null && !lAdr.isEmpty()) {
        for (Address a : lAdr) {
          adrController.deleteEntity(a.getId());
        }
      }
    }
    if (cityController != null) {
      List<City> lCity = cityController.getEntities(null);
      if (lCity != null && !lCity.isEmpty()) {
        for (City c : lCity) {
          cityController.deleteEntity(c.getId());
        }
      }
    }
    if (zipCodeController != null) {
      List<ZipCode> lZip = zipCodeController.getEntities(null);
      if (lZip != null && !lZip.isEmpty()) {
        for (ZipCode z : lZip) {
          zipCodeController.deleteEntity(z.getId());
        }
      }
    }
    if (countryController != null) {
      List<Country> lCountry = countryController.getEntities(null);
      if (lCountry != null && !lCountry.isEmpty()) {
        for (Country c : lCountry) {
          countryController.deleteEntity(c.getId());
        }
      }
    }
  }

  /**
   *
   */
  public AbstractCrudControllerArquillianTest() {
  }
  
}
