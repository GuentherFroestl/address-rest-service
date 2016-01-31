package de.gammadata.microservices.addressrs.addresses.control;

import static de.gammadata.microservices.addressrs.addresses.control.AbstractEntityJpaTest.em;
import de.gammadata.microservices.addressrs.addresses.entity.BaseQuerySpecification;
import de.gammadata.microservices.addressrs.addresses.entity.Building;
import de.gammadata.microservices.addressrs.addresses.entity.City;
import de.gammadata.microservices.addressrs.addresses.entity.Country;
import de.gammadata.microservices.addressrs.addresses.entity.EntityRelatedQuerySpec;
import de.gammadata.microservices.addressrs.addresses.entity.Street;
import de.gammadata.microservices.addressrs.addresses.entity.StreetBasics;
import de.gammadata.microservices.addressrs.addresses.entity.ZipCode;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityTransaction;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 *
 * @author gfr
 */
public class StreetCrudControllerJpaTest extends AbstractCrudControllerTest<Street, BaseQuerySpecification> {

  private StreetCrudController testee = spy(new StreetCrudController());

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
  public void testNativeQuery() {
    System.out.println("testNativeQuery()");
    Street adrCreated = TestEntityProvider.createAdressWithAllEntities();
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    Street result = testee.saveOrUpdateEntity(adrCreated);
    tx.commit();
    assertNotNull("unexpected null result for address", result);
    assertNotNull("unexpected null result for address.id", result.getId());

    List<StreetBasics> resList = testee.getListByQuery(new BaseQuerySpecification("name"));
    assertNotNull("resultlist unexpected null", resList);
    assertTrue("resultlist has no content", !resList.isEmpty());
    assertNotNull("resultlist unexpected null entity", resList.get(0));
    System.out.println(resList.get(0));
    assertEquals("id does not match", result.getId(),resList.get(0).getId());
    assertEquals("version does not match", result.getVersion(),resList.get(0).getVersion());
    assertEquals("modified does not match", result.getModified(),resList.get(0).getModified());
    assertEquals("name does not match", result.getName(),resList.get(0).getName());
    assertEquals("additionalName does not match", result.getAdditionalName(),resList.get(0).getAdditionalName());
    assertEquals("cityid does not match", result.getCity().getId(),resList.get(0).getCityId());
    assertEquals("cityname does not match", result.getCity().getName(),resList.get(0).getCityName());
    assertEquals("countryid does not match", result.getCountry().getId(),resList.get(0).getCountryId());
    assertEquals("countryname does not match", result.getCountry().getName(),resList.get(0).getCountryName());
    assertEquals("zipcodeid does not match", result.getZipCode().getId(),resList.get(0).getZipCodeId());
    assertEquals("zipcodename does not match", result.getZipCode().getName(),resList.get(0).getZipCodeName());
    //find streets in city
    List<StreetBasics> resList2 = testee.findStreetsInCity(new EntityRelatedQuerySpec(result.getCity().getId()));
    assertNotNull("resultlist unexpected null", resList2);
    assertTrue("resultlist has no content", !resList2.isEmpty());
    assertNotNull("resultlist unexpected null entity", resList2.get(0));
    assertEquals("id does not match",resList2.get(0).getCityId(),result.getCity().getId());
    //find streets in zipcode
    List<StreetBasics> resList3 = testee.findStreetsInZipCode(new EntityRelatedQuerySpec(resList.get(0).getZipCodeId()));
    assertNotNull("resultlist unexpected null", resList3);
    assertTrue("resultlist has no content", !resList3.isEmpty());
    assertNotNull("resultlist unexpected null entity", resList3.get(0));
    assertEquals("id does not match",resList.get(0).getZipCodeId(),resList3.get(0).getZipCodeId());
  }

  @Test
  public void testRelations() {
    System.out.println("testRelations()");

    Street adrCreated = TestEntityProvider.createAdressWithAllEntities();
    int bCount = adrCreated.getBuildings().size();

    EntityTransaction tx = em.getTransaction();
    tx.begin();
    Street result = testee.saveOrUpdateEntity(adrCreated);
    tx.commit();
    assertNotNull("unexpected null result for address", result);
    assertNotNull("unexpected null result for address.id", result.getId());

    Street res = testee.getEntity(result.getId());
    TestEntityProvider.setBasePropertiesForEquals(adrCreated, res);

    assertNotNull("unexpected null result for address", res);
    assertNotNull("unexpected null result for address.city", res.getCity());
    assertNotNull("unexpected null resul fore address.zipCode", res.getZipCode());
    assertNotNull("unexpected null result for address.zipCode.country", res.getZipCode().getCountry());
    assertNotNull("unexpected null result for address.country", res.getCountry());

    assertNotNull("unexpected null for getCity().getId()", res.getCity().getId());
    assertNotNull("unexpected null getZipCode().getId()", res.getZipCode().getId());
    assertNotNull("unexpected null getBuildings()", res.getBuildings());
    assertEquals("getBuildings().size() don't match", bCount, res.getBuildings().size());

    // Test buildings
    List<Building> bList = testee.findBuildings(new EntityRelatedQuerySpec(res.getId()));
    assertEquals("getBuildings().size() don't match", bCount, bList.size());

    EntityRelatedQuerySpec query = new EntityRelatedQuerySpec(res.getId(),res.getBuildings().get(0).getNumber());
    List<Building> bList2 = testee.findBuildings(query);
    assertEquals("getBuildings().size() don't match", 1, bList2.size());

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
    Street adrB = testee.saveOrUpdateEntity(res);
    tx.commit();
    List<Building> bList3 = testee.findBuildings(new EntityRelatedQuerySpec(res.getId(),"name"));
    assertEquals("getBuildings().size() don't match", 2, bList3.size());
  }

  @Override
  public Street createTestEntity() {
    return TestEntityProvider.createAddress();
  }

  @Override
  public AbstractCrudController getTestee() {
    return testee;
  }

}
