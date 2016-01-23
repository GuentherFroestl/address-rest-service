package de.gammadata.microservices.addressrs.addresses.control;

import static de.gammadata.microservices.addressrs.addresses.control.AbstractEntityJpaTest.em;
import de.gammadata.microservices.addressrs.addresses.entity.BaseQuerySpecification;
import de.gammadata.microservices.addressrs.addresses.entity.City;
import de.gammadata.microservices.addressrs.addresses.entity.EntityRelatedQuerySpec;
import java.util.List;
import javax.persistence.EntityTransaction;
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
public class CityCrudControllerJpaTest extends AbstractCrudControllerTest<City, BaseQuerySpecification> {

  private CityCrudController testee = spy(new CityCrudController());

  /**
   *
   */
  @Test
  public void testRelations() {
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
