package de.gammadata.microservices.addressrs.contacts.control;

import de.gammadata.microservices.addressrs.addresses.control.AbstractCrudControllerTest;
import de.gammadata.microservices.addressrs.addresses.control.TestEntityProvider;
import de.gammadata.microservices.addressrs.addresses.entity.Building;
import de.gammadata.microservices.addressrs.addresses.entity.Street;
import de.gammadata.microservices.addressrs.common.control.AbstractCrudController;
import de.gammadata.microservices.addressrs.common.entity.BaseQuerySpecification;
import de.gammadata.microservices.addressrs.common.entity.SimpleQuerySpecification;
import de.gammadata.microservices.addressrs.contacts.entity.AssociatedBasicAddress;
import de.gammadata.microservices.addressrs.contacts.entity.AssociatedBuildingAddress;
import de.gammadata.microservices.addressrs.contacts.entity.Contact;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 *
 * @author gfr
 */
public class AssociatedAdrCrudControllerTest extends AbstractCrudControllerTest<AssociatedBuildingAddress, SimpleQuerySpecification> {

  private AssociatedAdrCrudController testee = spy(new AssociatedAdrCrudController());

  /**
   * Test of getBuildingAdrForContact method, of class AssociatedAdrCrudController.
   */
  @Before
  @Override
  public void setUp() {
    super.setUp();
    when(testee.getEm()).thenReturn(em);
  }

  @After
  @Override
  public void tearDown() {
    if (em.getTransaction().isActive()) {
      em.getTransaction().rollback();
    }
    super.tearDown(); 
  }

  @Test
  public void testGetBuildingAdrForContact() {
    System.out.println("getBuildingAdrForContact");
    em.getTransaction().begin();
    //Create Contact
    Contact contact = TestEntityProvider.createContact();
    contact = em.merge(contact);
    em.flush();
    Assert.assertNotNull("contactId is null", contact.getId());
    //Create address
    Street street = TestEntityProvider.createAdressWithAllEntities();
    street = em.merge(street);
    em.flush();
    Assert.assertNotNull("streetId is null", street.getId());
    Assert.assertNotNull("no buildings in list", street.getBuildings());
    Assert.assertFalse("no buildings in list", street.getBuildings().isEmpty());
    Building building = street.getBuildings().get(0);

    AssociatedBuildingAddress assAdr = new AssociatedBuildingAddress();
    assAdr.setContact(contact);
    assAdr.setBuildingAddress(building);
    assAdr = em.merge(assAdr);
    em.flush();
    Assert.assertNotNull("AssociatedBuildingAddress is null", assAdr.getId());
    assAdr = em.find(AssociatedBuildingAddress.class, assAdr.getId());
    assertEquals("Building Id does not match", building, assAdr.getBuildingAddress());
    assertEquals("Contact Id does not match", contact, assAdr.getContact());

    BaseQuerySpecification spec = new BaseQuerySpecification();
    Long contactId = contact.getId();
    List<AssociatedBasicAddress> result = testee.getBuildingAdrForContact(spec, contactId);
    Assert.assertNotNull("AssociatedBuildingAddress list is null", result);
    Assert.assertFalse("no AssociatedBuildingAddress in list", result.isEmpty());
    assertEquals("AssociatedBuildingAddress ID don't match", assAdr.getId(), result.get(0).getId());
    assertEquals("AssociatedBuildingAddress Contact-ID don't match", assAdr.getContact().getId(), result.get(0).getContactId());
    assertEquals("AssociatedBuildingAddress Contact-ID don't match", assAdr.getBuildingAddress().getId(), result.get(0).getBuildingAdrId());
    assertEquals("AssociatedBuildingAddress Contact-ID don't match", assAdr.getBuildingAddressText(), result.get(0).getBuildingAddressText());
  }

  @Override
  public AssociatedBuildingAddress createTestEntity() {
    AssociatedBuildingAddress entity = new AssociatedBuildingAddress();
    entity.setBuildingAddressText("this is a test address");
    entity.setName("test entity");
    return entity;
  }

  @Override
  public AbstractCrudController getTestee() {
    return testee;
  }

}
