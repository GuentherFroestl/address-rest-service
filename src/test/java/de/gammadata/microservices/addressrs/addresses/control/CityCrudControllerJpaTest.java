package de.gammadata.microservices.addressrs.addresses.control;

import de.gammadata.microservices.addressrs.common.control.AbstractCrudController;
import static de.gammadata.microservices.addressrs.addresses.control.AbstractEntityJpaTest.em;
import de.gammadata.microservices.addressrs.common.entity.SimpleQuerySpecification;
import de.gammadata.microservices.addressrs.addresses.entity.City;
import de.gammadata.microservices.addressrs.common.entity.EntityRelatedQuerySpec;
import de.gammadata.microservices.addressrs.application.entity.AddressServiceException;
import java.util.List;
import javax.persistence.EntityTransaction;
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

/**
 *
 * @author gfr
 */
public class CityCrudControllerJpaTest extends AbstractCrudControllerTest<City, SimpleQuerySpecification> {

  private CityCrudController testee = spy(new CityCrudController());

  /**
   *
   */
  @Test
  public void testAdditionaFunctions() {
    System.out.println("testRelations()");

    City entity = TestEntityProvider.createCityWithCountry();
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    City result = testee.saveOrUpdateEntity(entity);
    tx.commit();
    assertNotNull("unexpected null result", result);
    assertNotNull("unexpected null result for result.id", result.getId());
    City result2 = testee.getEntity(result.getId());
    assertNotNull("unexpected null result for result", result2);
    assertNotNull("unexpected null result for getCountry()", result2.getCountry());
    assertNotNull("unexpected null result for getCountry().getId()", result2.getCountry().getId());
    //search for cities within country
    EntityRelatedQuerySpec spec = new EntityRelatedQuerySpec(result2.getCountry().getId());
    List<City> cList = testee.findCitiesInCountry(spec);
    assertNotNull("unexpected null result for city list", cList);
    assertTrue("city list empty ", !cList.isEmpty());
    City result3 = cList.get(0);
    assertEquals("cities do not match", result2, result3);
    //update and relations test
    result3.setName("xxcity changed");
    tx.begin();
    City result4 = testee.saveOrUpdateEntity(result3);
    tx.commit();
    assertEquals("upadted cities do not match", result3, result4);

    City result5 = testee.getEntity(result3.getId());
    assertEquals("upadted cities do not match", result4, result5);
    //search City in Country with qeurystring
    EntityRelatedQuerySpec spec2 = new EntityRelatedQuerySpec(result2.getCountry().getId());
    spec2.setQuery("xxcity");
    List<City> cList2 = testee.findCitiesInCountry(spec2);
    assertNotNull("unexpected null result for searchByCountry city list", cList2);
    assertTrue("unexpected null empty for searchByCountry city list", cList2.size() == 1);

    //search City in Country with qeurystring
    SimpleQuerySpecification spec3 = new SimpleQuerySpecification("xxcity");
    spec2.setQuery("xxcity");
    List<City> cList3 = testee.getListByQuery(spec3);
    assertNotNull("unexpected null result for getListByQuery city list", cList3);
    assertTrue("unexpected null empty for getListByQuery city list", cList3.size() > 0);
    //Test ExceptionHandling no RelatedId
    boolean execptionThrown = false;
    EntityRelatedQuerySpec spec4 = new EntityRelatedQuerySpec(0, "xx");
    try {
      testee.findCitiesInCountry(spec4);
    } catch (AddressServiceException e) {
      execptionThrown = true;
      assertEquals("wrong error in exception", AddressServiceException.Error.VALIDATION, e.getError());
    }
    assertTrue("AddressServiceException not thrown for missing relatedId", execptionThrown);

    //Test ExceptionHandling wrong relations
    result4.getCountry().setId(999l);
    execptionThrown = false;
    try {
      tx.begin();
      testee.saveOrUpdateEntity(result4);
      tx.commit();
    } catch (AddressServiceException e) {
      execptionThrown = true;
      assertEquals("wrong error in exception", AddressServiceException.Error.VALIDATION, e.getError());
    }
    tx.rollback();
    assertTrue("AddressServiceException not thrown for wrong country.id", execptionThrown);
    

  }

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
  public City createTestEntity() {
    return TestEntityProvider.createCity();
  }

  /**
   *
   * @return
   */
  @Override
  public AbstractCrudController getTestee() {
    return testee;
  }
}
