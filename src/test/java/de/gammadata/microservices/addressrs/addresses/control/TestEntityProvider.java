package de.gammadata.microservices.addressrs.addresses.control;

import de.gammadata.microservices.addressrs.addresses.entity.Address;
import de.gammadata.microservices.addressrs.addresses.entity.BaseEntity;
import de.gammadata.microservices.addressrs.addresses.entity.City;
import de.gammadata.microservices.addressrs.addresses.entity.Country;
import de.gammadata.microservices.addressrs.addresses.entity.ZipCode;

/**
 *
 * @author gfr
 */
public class TestEntityProvider {

  private TestEntityProvider() {
  }

  public static Address createAdress() {
    Address adrIn = new Address();
    adrIn.setAdditionalName("additional Name");
    adrIn.setName("name");
    adrIn.setNumber("number");
    return adrIn;
  }

  public static Address createAdressWithAllEntities() {
    Address adrIn = new Address();
    adrIn.setAdditionalName("additional Name");
    adrIn.setName("name");
    adrIn.setNumber("number");
    adrIn.setCity(createCityWithCountry());
    adrIn.setCountry(adrIn.getCity().getCountry());
    ZipCode zip = new ZipCode();
    zip.setName("zipCode");
    zip.setCountry(adrIn.getCity().getCountry());
    adrIn.setZipCode(zip);
    return adrIn;
  }
  
    public static City createCity() {
    City city = new City();
    city.setName("city name");
    return city;
  }

  public static City createCityWithCountry() {
    City city = new City();
    city.setName("city name");
    city.setCountry(createCountry());
    return city;
  }
  
    public static ZipCode createZipCodeWithCountry() {
    ZipCode zip = new ZipCode();
    zip.setName("name");
    zip.setCountry(createCountry());
    return zip;
  }

  public static Country createCountry() {

    Country country = new Country();
    country.setName("country");
    country.setIso2CountryCode("DE");
    country.setIso3CountryCode("DEU");
    country.setIsoNumber(123);
    return country;
  }


  public static void setBasePropertiesForEquals(BaseEntity pIn, BaseEntity withId) {
    pIn.setId(withId.getId());
    pIn.setVersion(withId.getVersion());
    pIn.setModified(withId.getModified());
  }


}
