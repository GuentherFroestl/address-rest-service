/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gammadata.microservices.addressrs.addresses.control;

import static de.gammadata.microservices.addressrs.addresses.control.AbstractEntityJpaTest.em;
import de.gammadata.microservices.addressrs.addresses.entity.Building;
import de.gammadata.microservices.addressrs.common.control.AbstractCrudController;
import de.gammadata.microservices.addressrs.common.entity.SimpleQuerySpecification;
import org.junit.Before;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 *
 * @author gfr
 */
public class BuildingCrudControllerJpaTest extends AbstractCrudControllerTest<Building, SimpleQuerySpecification> {

    private BuildingCrudController testee = spy(new BuildingCrudController());

    @Before
    @Override
    public void setUp() {
        super.setUp();
        when(testee.getEm()).thenReturn(em);

    }

    @Override
    public Building createTestEntity() {
        return TestEntityProvider.createBuilding();
    }

    @Override
    public AbstractCrudController getTestee() {
        return testee;
    }

}
