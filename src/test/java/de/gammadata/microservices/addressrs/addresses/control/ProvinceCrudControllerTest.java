/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gammadata.microservices.addressrs.addresses.control;

import static de.gammadata.microservices.addressrs.addresses.control.AbstractEntityJpaTest.em;
import de.gammadata.microservices.addressrs.addresses.entity.Country;
import de.gammadata.microservices.addressrs.addresses.entity.Province;
import de.gammadata.microservices.addressrs.common.control.AbstractCrudController;
import de.gammadata.microservices.addressrs.common.entity.EntityRelatedQuerySpec;
import de.gammadata.microservices.addressrs.common.entity.SimpleQuerySpecification;
import java.util.List;
import javax.persistence.EntityTransaction;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author gfr
 */
public class ProvinceCrudControllerTest extends AbstractCrudControllerTest<Province, SimpleQuerySpecification> {

    private ProvinceCrudController testee = spy(new ProvinceCrudController());

    @Test
    public void testAdditionaFunctions() {
        System.out.println("testRelations()");
        Province entity = TestEntityProvider.createProvinceWithCountry();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Province result = testee.saveOrUpdateEntity(entity);
        tx.commit();
        assertNotNull("unexpected null result", result);
        assertNotNull("unexpected null result for result.id", result.getId());

        Province result2 = testee.getEntity(result.getId());
        assertNotNull("unexpected null result for result", result2);
        assertNotNull("unexpected null result for getCountry()", result2.getCountry());
        assertNotNull("unexpected null result for getCountry().getId()", result2.getCountry().getId());

        //search for cities within country
        EntityRelatedQuerySpec spec = new EntityRelatedQuerySpec(result2.getCountry().getId());

        List<Province> pList = testee.findProvincesInCountry(spec);
        assertNotNull("unexpected null result for provinces list", pList);
        assertTrue("provinces list empty ", !pList.isEmpty());

        //negative test
        spec.setRelatedId(12345);
        pList = testee.findProvincesInCountry(spec);
        assertTrue("provinces list not empty ", pList.isEmpty());

    }

    @Override
    public void tearDown() {
        super.tearDown();
        deleteEntities(Country.class, em);

    }

    @Override
    public Province createTestEntity() {
        return TestEntityProvider.createProvince();
    }

    @Before
    @Override
    public void setUp() {
        super.setUp();
        when(testee.getEm()).thenReturn(em);

    }

    @Override
    public AbstractCrudController getTestee() {
        return testee;
    }

}
