package de.gammadata.microservices.addressrs.addresses.control;

import de.gammadata.microservices.addressrs.addresses.entity.BaseEntity;
import de.gammadata.microservices.addressrs.addresses.entity.Building;
import de.gammadata.microservices.addressrs.addresses.entity.City;
import de.gammadata.microservices.addressrs.addresses.entity.Country;
import de.gammadata.microservices.addressrs.addresses.entity.Street;
import de.gammadata.microservices.addressrs.addresses.entity.ZipCode;
import java.util.ArrayList;

/**
 *
 * @author gfr
 */
public class TestEntityProvider {

  /**
   *
   * @return
   */
  public static Street createAddress() {
    Street adrIn = new Street();
    adrIn.setAdditionalName("additional Name");
    adrIn.setName("name");
    return adrIn;
  }

  /**
   *
   * @param number
   * @return
   */
  public static Building createBuilding(String number) {
    Building b = new Building();
    b.setNumber(number);
    return b;
  }

  /**
   *
   * @return
   */
  public static Street createAdressWithAllEntities() {
    Street adrIn = createAddress();
    adrIn.setBuildings(new ArrayList<Building>());
    adrIn.getBuildings().add(createBuilding("1"));
    adrIn.getBuildings().add(createBuilding("2"));
    adrIn.getBuildings().add(createBuilding("3"));
    adrIn.getBuildings().add(createBuilding("4"));
    adrIn.getBuildings().add(createBuilding("5"));
    adrIn.getBuildings().add(createBuilding("5a"));

    adrIn.setCity(createCityWithCountry());
    adrIn.setCountry(adrIn.getCity().getCountry());
    ZipCode zip = new ZipCode();
    zip.setName("zipCode");
    zip.setCountry(adrIn.getCity().getCountry());
    adrIn.setZipCode(zip);
    return adrIn;
  }

  /**
   *
   * @return
   */
  public static City createCity() {
    City city = new City();
    city.setName("city name");
    return city;
  }

  /**
   *
   * @return
   */
  public static City createCityWithCountry() {
    City city = new City();
    city.setName("city name");
    city.setCountry(createCountry());
    return city;
  }

  /**
   *
   * @return
   */
  public static ZipCode createZipCodeWithCountry() {
    ZipCode zip = new ZipCode();
    zip.setName("name");
    zip.setCountry(createCountry());
    return zip;
  }

  /**
   *
   * @return
   */
  public static Country createCountry() {

    Country country = new Country();
    country.setName("country");
    country.setIso2CountryCode("DE");
    country.setIso3CountryCode("DEU");
    country.setIsoNumber(123);
    return country;
  }

  /**
   *
   * @param pIn
   * @param withId
   */
  public static void setBasePropertiesForEquals(BaseEntity pIn, BaseEntity withId) {
    if (pIn == null || withId == null) {
      return;
    }
    pIn.setId(withId.getId());
    pIn.setVersion(withId.getVersion());
    pIn.setModified(withId.getModified());

    if (pIn instanceof ZipCode && withId instanceof ZipCode) {
      ZipCode z = (ZipCode) pIn;
      ZipCode z2 = (ZipCode) withId;
      setBasePropertiesForEquals(z.getCountry(), z2.getCountry());
    }

    if (pIn instanceof City && withId instanceof City) {
      City z = (City) pIn;
      City z2 = (City) withId;
      setBasePropertiesForEquals(z.getCountry(), z2.getCountry());
    }

    if (pIn instanceof Street && withId instanceof Street) {
      Street z = (Street) pIn;
      Street z2 = (Street) withId;
      setBasePropertiesForEquals(z.getCountry(), z2.getCountry());
      setBasePropertiesForEquals(z.getCity(), z2.getCity());
      setBasePropertiesForEquals(z.getZipCode(), z2.getZipCode());
    }
  }

  private TestEntityProvider() {
  }

}
