package de.gammadata.microservices.addressrs.addresses.control;

import static de.gammadata.microservices.addressrs.addresses.control.AbstractEntityJpaTest.em;
import de.gammadata.microservices.addressrs.addresses.entity.BaseQuerySpecification;
import de.gammadata.microservices.addressrs.addresses.entity.ZipCode;
import org.junit.Before;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 *
 * @author gfr
 */
public class ZipCodeCrudControllerJpaTest extends AbstractCrudControllerTest<ZipCode, BaseQuerySpecification> {

  private ZipCodeCrudController testee = spy(new ZipCodeCrudController());

  /**
   *
   */
  @Before
  @Override
  public void setUp() {
    super.setUp();
    when(testee.getEm()).thenReturn(em);

  }

  /**
   *
   * @return
   */
  @Override
  public ZipCode createTestEntity() {
    return TestEntityProvider.createZipCodeWithCountry();
  }

  /**
   *
   * @return
   */
  @Override
  public ZipCodeCrudController getTestee() {
    return testee;
  }

}
