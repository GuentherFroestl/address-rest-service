package de.gammadata.microservices.addressrs.contacts.control;

import de.gammadata.microservices.addressrs.addresses.control.AbstractCrudControllerTest;
import de.gammadata.microservices.addressrs.addresses.control.AbstractEntityJpaTest;
import de.gammadata.microservices.addressrs.addresses.control.TestEntityProvider;
import de.gammadata.microservices.addressrs.common.control.AbstractCrudController;
import de.gammadata.microservices.addressrs.common.entity.SimpleQuerySpecification;
import de.gammadata.microservices.addressrs.contacts.entity.Contact;
import de.gammadata.microservices.addressrs.contacts.entity.ContactBasics;
import java.util.List;
import javax.persistence.EntityTransaction;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author gfr
 */
public class ContactCrudControllerJpaTest extends AbstractCrudControllerTest<Contact, SimpleQuerySpecification> {

  private ContactCrudController testee = spy(new ContactCrudController());

  @Test
  public void testNativeQuery() {
    System.out.println("testNativeQuery()");
    Contact entityCreated = createTestEntity();
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    Contact result = testee.saveOrUpdateEntity(entityCreated);
    tx.commit();
    assertNotNull("unexpected null result for address", result);
    assertNotNull("unexpected null result for address.id", result.getId());
    
    List<ContactBasics> resList = testee.getListByQuery(new SimpleQuerySpecification(entityCreated.getName()));
    assertNotNull("resultlist unexpected null", resList);
    assertTrue("resultlist has no content", !resList.isEmpty());
    assertNotNull("resultlist unexpected null entity", resList.get(0));
    System.out.println(resList.get(0));
    assertEquals("id does not match", result.getId(),resList.get(0).getId());
    assertEquals("version does not match", result.getVersion(),resList.get(0).getVersion());
    assertEquals("modified does not match", result.getModified(),resList.get(0).getModified());
    assertEquals("name does not match", result.getName(),resList.get(0).getName());
    assertEquals("additionalName does not match", result.getAdditionalName(),resList.get(0).getAdditionalName());
    assertEquals("firstName does not match", result.getFirstName(),resList.get(0).getFirstName());
    assertEquals("additionalName does not match", result.getRegistrationNumber(),resList.get(0).getRegistrationNumber());
    assertEquals("type does not match", result.getType(),Contact.TYPE.valueOf(resList.get(0).getType()));
    assertEquals("gender does not match", result.getGender(),Contact.GENDER.valueOf(resList.get(0).getGender()));
  }

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
    AbstractEntityJpaTest.deleteEntities(Contact.class, em);
  }

  @Override
  public Contact createTestEntity() {
    return TestEntityProvider.createContact();
  }

  @Override
  public AbstractCrudController getTestee() {
    return testee;
  }

}
