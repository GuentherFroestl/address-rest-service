package de.gammadata.microservices.addressrs.addresses.control;

import de.gammadata.microservices.addressrs.addresses.entity.BaseQuerySpecification;
import de.gammadata.microservices.addressrs.addresses.entity.Country;
import org.junit.Before;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 *
 * @author gfr
 */
public class CountryCrudControllerJpaTest extends AbstractCrudControllerTest<Country, BaseQuerySpecification> {

  public CountryCrudControllerJpaTest() {
  }

  private CountryCrudController testee = spy(new CountryCrudController());

  @Before
  @Override
  public void setUp() {
    super.setUp();
    when(testee.getEm()).thenReturn(em);
  }

  @Override
  public Country createTestEntity() {
    return TestEntityProvider.createCountry();
  }

  @Override
  public AbstractCrudController getTestee() {
    return testee;
  }

}
