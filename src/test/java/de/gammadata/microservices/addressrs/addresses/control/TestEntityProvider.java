package de.gammadata.microservices.addressrs.addresses.control;

import de.gammadata.microservices.addressrs.common.entity.BaseEntity;
import de.gammadata.microservices.addressrs.addresses.entity.Building;
import de.gammadata.microservices.addressrs.addresses.entity.City;
import de.gammadata.microservices.addressrs.addresses.entity.Country;
import de.gammadata.microservices.addressrs.addresses.entity.Province;
import de.gammadata.microservices.addressrs.addresses.entity.Street;
import de.gammadata.microservices.addressrs.addresses.entity.ZipCode;
import de.gammadata.microservices.addressrs.contacts.entity.AssociatedBuildingAddress;
import de.gammadata.microservices.addressrs.contacts.entity.CommunicationAddress;
import de.gammadata.microservices.addressrs.contacts.entity.Contact;
import de.gammadata.microservices.addressrs.contacts.entity.Salutation;
import java.util.ArrayList;

/**
 *
 * @author gfr
 */
public class TestEntityProvider {

    private TestEntityProvider() {
    }

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
     * @return Building
     */
    public static Building createBuilding() {
        Building b = new Building();
        b.setNumber("buildingNumber");
        b.setName("buildingName");
        BuildingEntityListener listener = new BuildingEntityListener();
        listener.prePersist(b);
        return b;
    }

    /**
     *
     * @return
     */
    public static Street createAdressWithAllEntities() {
        Street adrIn = createAddress();
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

    public static Province createProvince() {
        Province province = new Province();
        province.setName("Province name");
        return province;
    }

    public static Province createProvinceWithCountry() {
        Province province = new Province();
        province.setName("Province name");
        province.setCountry(createCountry());
        return province;
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

    public static Contact createContact() {

        Contact contact = new Contact();
        contact.setName("Tester");
        contact.setAdditionalName("MiddlerName");
        contact.setFirstName("Tom");
        contact.setGender(Contact.GENDER.MALE);
        contact.setType(Contact.TYPE.PERSON);
        return contact;
    }

    public static Salutation createSalutation() {

        Salutation sal = new Salutation();
        sal.setForAddress("Mr.");
        sal.setForLetter("Dear Mr.");
        sal.setName("Mister");
        return sal;
    }

    public static CommunicationAddress createCommunicationAddress() {
        CommunicationAddress com = new CommunicationAddress();
        com.setName("test@test.com");
        com.setQualifier("office");
        com.setType(CommunicationAddress.TYPE.EMAIL);
        return com;
    }

    public static AssociatedBuildingAddress createAssociatedAddress() {
        AssociatedBuildingAddress adr = new AssociatedBuildingAddress();
        adr.setName("mainaddress");
        adr.setQualifier("private");
        return adr;
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

}
