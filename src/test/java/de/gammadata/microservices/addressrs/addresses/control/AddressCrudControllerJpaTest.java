package de.gammadata.microservices.addressrs.addresses.control;

import static de.gammadata.microservices.addressrs.addresses.control.AbstractEntityJpaTest.em;
import de.gammadata.microservices.addressrs.addresses.entity.Address;
import javax.persistence.EntityTransaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 *
 * @author gfr
 */
public class AddressCrudControllerJpaTest  extends AbstractEntityJpaTest {

  private BaseEntityListener entityListener = spy(new BaseEntityListener());
  private AddressCrudController testee = spy(new AddressCrudController());

  public AddressCrudControllerJpaTest() {

  }

  @Before
  public void setUp() {
    when(testee.getEm()).thenReturn(em);
    when(entityListener.getEm()).thenReturn(em);
  }

  @Test
  public void testRelations() {
    System.out.println("testRelations()");

    Address adr = TestEntityProvider.createAdressWithAllEntities();
    adr.setCountry(null);//Test auto relations

    EntityTransaction tx = em.getTransaction();
    tx.begin();
    Address per = testee.saveOrUpdateEntity(adr);
    tx.commit();

    Address res = testee.getEntity(per.getId());
    Assert.assertNotNull("unexpected null result for address", res);
    Assert.assertNotNull("unexpected null result for address.city", res.getCity());
    Assert.assertNotNull("unexpected null resul fore address.zipCode", res.getZipCode());
    Assert.assertNotNull("unexpected null result for address.zipCode.country", res.getZipCode().getCountry());
    Assert.assertNotNull("unexpected null result for address.country", res.getCountry());

    Assert.assertNotNull("unexpected null for getCity().getId()", res.getCity().getId());
    Assert.assertNotNull("unexpected null getZipCode().getId()", res.getZipCode().getId());

  }

}
