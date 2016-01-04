package de.gammadata.microservices.addressrs.addresses.control;

import de.gammadata.microservices.addressrs.addresses.entity.BaseEntity;
import de.gammadata.microservices.addressrs.addresses.entity.Country;

/**
 *
 * @author gfr
 */
public class TestEntityProvider {

  private TestEntityProvider() {
  }

  public static Country createCountry() {

    Country country = new Country();
    country.setName("country");
    country.setIso2CountryCode("DE");
    country.setIso3CountryCode("DEU");
    country.setIsoNumber(123);
    return country;
  }

  public static Country createCountry(long testDate) {
    Country pCountry = new Country();
    pCountry.setIso2CountryCode("DE");
    pCountry.setIso3CountryCode("DEU");
    pCountry.setIsoNumber(1234);
    pCountry.setName("Deutschland");
    return pCountry;
  }

  public static void setIdAndVersion(BaseEntity pIn, BaseEntity withId) {
    pIn.setId(withId.getId());
    pIn.setVersion(withId.getVersion());
    pIn.setModified(withId.getModified());
  }

}
