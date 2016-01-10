package de.gammadata.microservices.addressrs.addresses.control;

import static de.gammadata.microservices.addressrs.addresses.control.AbstractEntityJpaTest.em;
import de.gammadata.microservices.addressrs.addresses.entity.Address;
import de.gammadata.microservices.addressrs.addresses.entity.BaseQuerySpecification;
import de.gammadata.microservices.addressrs.addresses.entity.Building;
import de.gammadata.microservices.addressrs.addresses.entity.City;
import de.gammadata.microservices.addressrs.addresses.entity.Country;
import de.gammadata.microservices.addressrs.addresses.entity.ZipCode;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityTransaction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertNotNull;

/**
 *
 * @author gfr
 */
public class AddressCrudControllerJpaTest extends AbstractCrudControllerTest<Address, BaseQuerySpecification> {

  private AddressCrudController testee = spy(new AddressCrudController());

  @Before
  @Override
  public void setUp() {
    super.setUp();
    when(testee.getEm()).thenReturn(em);

  }

  @Test
  public void testPrecondition() {
    Assert.assertNotNull("unexpected null result for testee", testee);
  }

  @After
  @Override
  public void tearDown() {
    super.tearDown();
    AbstractEntityJpaTest.deleteEntities(ZipCode.class, em);
    AbstractEntityJpaTest.deleteEntities(City.class, em);
    AbstractEntityJpaTest.deleteEntities(Country.class, em);
  }

  @Test
  public void testRelations() {
    System.out.println("testRelations()");

    Address adrCreated = TestEntityProvider.createAdressWithAllEntities();
    int bCount = adrCreated.getBuildings().size();

    EntityTransaction tx = em.getTransaction();
    tx.begin();
    Address result = testee.saveOrUpdateEntity(adrCreated);
    tx.commit();
    assertNotNull("unexpected null result for address", result);
    assertNotNull("unexpected null result for address.id", result.getId());

    Address res = testee.getEntity(result.getId());
    TestEntityProvider.setBasePropertiesForEquals(adrCreated, res);

    assertNotNull("unexpected null result for address", res);
    assertNotNull("unexpected null result for address.city", res.getCity());
    assertNotNull("unexpected null resul fore address.zipCode", res.getZipCode());
    assertNotNull("unexpected null result for address.zipCode.country", res.getZipCode().getCountry());
    assertNotNull("unexpected null result for address.country", res.getCountry());

    assertNotNull("unexpected null for getCity().getId()", res.getCity().getId());
    assertNotNull("unexpected null getZipCode().getId()", res.getZipCode().getId());
    Assert.assertNotNull("unexpected null getBuildings()", res.getBuildings());
    Assert.assertEquals("getBuildings().size() don't match", bCount, res.getBuildings().size());
    
    // Test buildings
    List<Building> bList = testee.findBuildings(null, res.getId());
    Assert.assertEquals("getBuildings().size() don't match", bCount, bList.size());
    
    BaseQuerySpecification query = new BaseQuerySpecification(res.getBuildings().get(0).getNumber());
    List<Building> bList2 = testee.findBuildings(query, res.getId());
    Assert.assertEquals("getBuildings().size() don't match", 1, bList2.size());
    
    //Update Buildings
    List<Building> bListNew = new ArrayList<>();
    res.setBuildings(bListNew);
    Building b1 = new Building();
    b1.setName("name 1");
    bListNew.add(b1);
    Building b2 = new Building();
    b2.setName("name 2");
    bListNew.add(b2);
    tx.begin();
    Address adrB = testee.saveOrUpdateEntity(res);
    tx.commit();
    query.setQuery("name"); // 2 Times in buildings
    List<Building> bList3 = testee.findBuildings(query, res.getId());
    Assert.assertEquals("getBuildings().size() don't match", 2, bList3.size());
  }

  @Override
  public Address createTestEntity() {
    return TestEntityProvider.createAddress();
  }

  @Override
  public AbstractCrudController getTestee() {
    return testee;
  }

}
