/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gammadata.microservices.addressrs.addresses.control;

import static de.gammadata.microservices.addressrs.addresses.control.AbstractEntityJpaPostgresTest.em;
import de.gammadata.microservices.addressrs.addresses.entity.Building;
import de.gammadata.microservices.addressrs.addresses.entity.City;
import de.gammadata.microservices.addressrs.addresses.entity.Country;
import de.gammadata.microservices.addressrs.addresses.entity.Street;
import de.gammadata.microservices.addressrs.addresses.entity.ZipCode;
import de.gammadata.microservices.addressrs.common.control.AbstractCrudController;
import de.gammadata.microservices.addressrs.common.entity.SimpleQuerySpecification;
import java.util.List;
import javax.persistence.Query;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 *
 * @author gfr
 */
public class BuildingCrudControllerPostgresIT extends AbstractCrudControllerPostrgresTest<Building, SimpleQuerySpecification> {

    private BuildingCrudController testee = spy(new BuildingCrudController());

    @Before
    @Override
    public void setUp() {
        super.setUp();
        when(testee.getEm()).thenReturn(em);

    }

    @After
    @Override
    public void tearDown() {
        super.tearDown();
        deleteEntities(Street.class, em);
        deleteEntities(City.class, em);
        deleteEntities(ZipCode.class, em);
        deleteEntities(Country.class, em);
    }

    @Override
    public Building createTestEntity() {
        return TestEntityProvider.createBuilding();
    }

    @Override
    public AbstractCrudController getTestee() {
        return testee;
    }

    @Test
    public void testFullTextSearch() {
        System.out.println("testFullTextSearch()");
        Street street = TestEntityProvider.createStreetWithAllRelations();
        Building bd = new Building();
        bd.setStreet(street);
        bd.setNumber("1a");
        bd.setName("testBuilding");
        em.getTransaction().begin();
        testee.saveOrUpdateEntity(bd);
        em.getTransaction().commit();
        List<Building> list = testee.searchEntities(null);
        Assert.assertNotNull("building was not saved", list);
        Assert.assertEquals("result list size not 1", 1, list.size());
        System.out.println("building stored " + list.get(0));

        String queryTxt = "select * from building where tsv @@ to_tsquery('english',?);";
        Query query = em.createNativeQuery(queryTxt, Building.class);
        query.setParameter(1, "testBuilding");
        List<Building> result = query.getResultList();
        Assert.assertNotNull("no result for fulltext serach", result);
        Assert.assertEquals("result list size not 1", 1, result.size());
        System.out.println("building found with fulltext " + list.get(0));

        query.setParameter(1, "deu");
        result = query.getResultList();
        Assert.assertNotNull("no result for fulltext serach", result);
        Assert.assertEquals("result list size not 1", 1, result.size());
        System.out.println("building found with 'deu' " + list.get(0));

        query.setParameter(1, "name & deu");
        result = query.getResultList();
        Assert.assertNotNull("no result for fulltext serach", result);
        Assert.assertEquals("result list size not 1", 1, result.size());
        System.out.println("building found with 'name & deu' " + list.get(0));

        query.setParameter(1, "zip:*");
        result = query.getResultList();
        Assert.assertNotNull("no result for fulltext serach", result);
        Assert.assertEquals("result list size not 1", 1, result.size());
        System.out.println("building found with 'zip:*' " + list.get(0));

        query.setParameter(1, "zip:* & na:*");
        result = query.getResultList();
        Assert.assertNotNull("no result for fulltext serach", result);
        Assert.assertEquals("result list size not 1", 1, result.size());
        System.out.println("building found with 'zip:* & na:*' " + list.get(0));

    }

}
