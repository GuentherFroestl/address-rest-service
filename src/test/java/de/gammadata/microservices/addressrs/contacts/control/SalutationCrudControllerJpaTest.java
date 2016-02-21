package de.gammadata.microservices.addressrs.contacts.control;

import de.gammadata.microservices.addressrs.addresses.control.AbstractCrudControllerTest;
import de.gammadata.microservices.addressrs.addresses.control.TestEntityProvider;
import de.gammadata.microservices.addressrs.common.control.AbstractCrudController;
import de.gammadata.microservices.addressrs.common.entity.SimpleQuerySpecification;
import de.gammadata.microservices.addressrs.contacts.entity.Salutation;
import org.junit.Before;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 *
 * @author gfr
 */
public class SalutationCrudControllerJpaTest extends AbstractCrudControllerTest<Salutation, SimpleQuerySpecification> {

  private SalutationCrudController testee = spy(new SalutationCrudController());

  @Before
  @Override
  public void setUp() {
    super.setUp();
    when(testee.getEm()).thenReturn(em);
  }

  @Override
  public Salutation createTestEntity() {
    return TestEntityProvider.createSalutation();
  }

  @Override
  public AbstractCrudController getTestee() {
    return testee;
  }
}
