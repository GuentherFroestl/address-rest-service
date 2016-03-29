/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gammadata.microservices.addressrs.addresses.control;

import de.gammadata.microservices.addressrs.addresses.entity.Building;
import de.gammadata.microservices.addressrs.addresses.entity.City;
import de.gammadata.microservices.addressrs.addresses.entity.Country;
import de.gammadata.microservices.addressrs.addresses.entity.Province;
import de.gammadata.microservices.addressrs.addresses.entity.Street;
import de.gammadata.microservices.addressrs.addresses.entity.ZipCode;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author gfr
 */
public class BuildingEntityListenerTest {

    BuildingEntityListener instance = new BuildingEntityListener();

    /**
     * Test of prePersist method, of class BuildingEntityListener.
     */
    @Test
    public void testPrePersist() {
        System.out.println("prePersist");
        Building bd = new Building();
        bd.setName("buildingName");
        bd.setNumber("buildingNumber");
        instance.prePersist(bd);
        Assert.assertNotNull("modified date is null", bd.getModified());
        Assert.assertNotNull("fullTextField is null", bd.getFullTextSearch());
        Assert.assertEquals("fullTextField is not correct", "buildingNumber buildingName", bd.getFullTextSearch());
        //with street
        Street street = new Street();
        street.setName("streetName");
        bd.setStreet(street);
        instance.prePersist(bd);
        Assert.assertEquals("fullTextField is not correct", "buildingNumber buildingName streetName", bd.getFullTextSearch());

        //with ZipCode
        ZipCode zip = new ZipCode();
        zip.setName("zipCodeName");
        street.setZipCode(zip);
        instance.prePersist(bd);
        Assert.assertEquals("fullTextField is not correct", "buildingNumber buildingName streetName zipCodeName", bd.getFullTextSearch());

        //with City
        City city = new City();
        city.setName("cityName");
        street.setCity(city);
        instance.prePersist(bd);
        Assert.assertEquals("fullTextField is not correct", "buildingNumber buildingName streetName zipCodeName cityName", bd.getFullTextSearch());

        Province prov = new Province();
        prov.setName("provinceName");
        city.setProvince(prov);
        instance.prePersist(bd);
        Assert.assertEquals("fullTextField is not correct", "buildingNumber buildingName streetName zipCodeName cityName provinceName", bd.getFullTextSearch());

        Country country = new Country();
        country.setName("countryName");
        country.setIso3CountryCode("iso3Code");
        city.setCountry(country);
        instance.prePersist(bd);
        Assert.assertEquals("fullTextField is not correct", "buildingNumber buildingName streetName zipCodeName cityName"
                + " provinceName countryName iso3Code", bd.getFullTextSearch());

    }

    /**
     * Test of preUpdate method, of class BuildingEntityListener.
     */
    @Test
    public void testPreUpdate() {
        System.out.println("preUpdate");
        testPrePersist();

    }

}
