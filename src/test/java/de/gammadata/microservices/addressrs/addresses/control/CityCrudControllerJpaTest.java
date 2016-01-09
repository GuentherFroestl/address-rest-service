package de.gammadata.microservices.addressrs.addresses.control;

import static de.gammadata.microservices.addressrs.addresses.control.AbstractEntityJpaTest.em;
import de.gammadata.microservices.addressrs.addresses.entity.BaseQuerySpecification;
import de.gammadata.microservices.addressrs.addresses.entity.City;
import org.junit.Before;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 *
 * @author gfr
 */
public class CityCrudControllerJpaTest extends AbstractCrudControllerTest<City, BaseQuerySpecification> {

  private CityCrudController testee = spy(new CityCrudController());

  @Before
  @Override
  public void setUp() {
    super.setUp();
    when(testee.getEm()).thenReturn(em);

  }

  @Override
  public City createTestEntity() {
    return TestEntityProvider.createCity();
  }

  @Override
  public AbstractCrudController getTestee() {
    return testee;
  }
}
