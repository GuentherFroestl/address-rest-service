package de.gammadata.microservices.addressrs.contacts.control;

import de.gammadata.microservices.addressrs.addresses.control.AbstractCrudControllerTest;
import de.gammadata.microservices.addressrs.addresses.control.TestEntityProvider;
import de.gammadata.microservices.addressrs.common.control.AbstractCrudController;
import de.gammadata.microservices.addressrs.common.entity.BaseQuerySpecification;
import de.gammadata.microservices.addressrs.contacts.entity.CommunicationAddress;
import org.junit.Before;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 *
 * @author gfr
 */
public class ComAddressCrudControllerJpaTest extends AbstractCrudControllerTest<CommunicationAddress, BaseQuerySpecification>{
  
  private ComAddressCrudController testee = spy(new ComAddressCrudController());
  
  @Before
  @Override
  public void setUp() {
    super.setUp();
    when(testee.getEm()).thenReturn(em);
  }

  @Override
  public CommunicationAddress createTestEntity() {
    return TestEntityProvider.createCommunicationAddress();
  }

  @Override
  public AbstractCrudController getTestee() {
    return testee;
  }
}
