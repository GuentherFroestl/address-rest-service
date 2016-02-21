package de.gammadata.microservices.addressrs.addresses.control;

import de.gammadata.microservices.addressrs.common.control.AbstractCrudController;
import static de.gammadata.microservices.addressrs.addresses.control.AbstractEntityJpaTest.em;
import de.gammadata.microservices.addressrs.common.entity.SimpleQuerySpecification;
import de.gammadata.microservices.addressrs.addresses.entity.Country;
import java.util.List;
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
public class CountryCrudControllerJpaTest extends AbstractCrudControllerTest<Country, SimpleQuerySpecification> {


  private CountryCrudController testee = spy(new CountryCrudController());


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
  public Country createTestEntity() {
    return TestEntityProvider.createCountry();
  }

  /**
   *
   * @return
   */
  @Override
  public AbstractCrudController getTestee() {
    return testee;
  }

  /**
   *
   */
  @Test
  public void test_Limits() {
    AbstractEntityJpaTest.deleteEntities(createTestEntity().getClass(), em);
    createAndPersistCountry("AA");
    createAndPersistCountry("AB");
    createAndPersistCountry("AC");
    createAndPersistCountry("AD");
    createAndPersistCountry("AE");
    createAndPersistCountry("AF");
    createAndPersistCountry("AG");
    createAndPersistCountry("AH");
    createAndPersistCountry("AI");
    createAndPersistCountry("AJ");

    List<Country> list = testee.getListByQuery(null);
    Assert.assertNotNull("unexpected null result", list);
    Assert.assertEquals("List size does not match", 10, list.size());
    SimpleQuerySpecification qs = new SimpleQuerySpecification(10, 0, "A");
    list = testee.getListByQuery(qs);
    Assert.assertNotNull("unexpected null result", list);
    Assert.assertEquals("List size does not match", 10, list.size());
    
    Long count=testee.countEntitiesByQuery(qs);
    Long expectedCount = 10L;
    Assert.assertEquals("List size does not match", expectedCount, count);
    
    qs = new SimpleQuerySpecification(null, null, "A");
    list = testee.getListByQuery(qs);
    Assert.assertNotNull("unexpected null result", list);
    Assert.assertEquals("List size does not match", 10, list.size());
    
    qs = new SimpleQuerySpecification(5, null, "A");
    list = testee.getListByQuery(qs);
    Assert.assertNotNull("unexpected null result", list);
    Assert.assertEquals("List size does not match", 5, list.size());
    
    qs = new SimpleQuerySpecification(null, 0, "A");
    list = testee.getListByQuery(qs);
    Assert.assertNotNull("unexpected null result", list);
    Assert.assertEquals("List size does not match", 10, list.size());
    
    
    qs = new SimpleQuerySpecification(5, 0, "A");
    list = testee.getListByQuery(qs);
    Assert.assertNotNull("unexpected null result", list);
    Assert.assertEquals("List size does not match", 5, list.size());
    qs = new SimpleQuerySpecification(5, 5, "A");
    list = testee.getListByQuery(qs);
    Assert.assertNotNull("unexpected null result", list);
    Assert.assertEquals("List size does not match", 5, list.size());
    qs = new SimpleQuerySpecification(5, 9, "A");
    list = testee.getListByQuery(qs);
    Assert.assertNotNull("unexpected null result", list);
    Assert.assertEquals("List size does not match", 1, list.size());
    qs = new SimpleQuerySpecification(10, 10, "A");
    list = testee.getListByQuery(qs);
    Assert.assertNotNull("unexpected null result", list);
    Assert.assertEquals("List size does not match", 0, list.size());
    AbstractEntityJpaTest.deleteEntities(createTestEntity().getClass(), em);
  }

  private void createAndPersistCountry(String name) {
    Country c1 = createTestEntity();
    c1.setName(name);
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    Country result = testee.saveOrUpdateEntity(c1);
    tx.commit();
    Assert.assertNotNull("unexpected null result", result);
    Assert.assertNotNull("No ID for Entity", result.getId());
    Assert.assertNotNull("No modfied date for Entity", result.getModified());
    Assert.assertEquals("Names are not equal", result.getName(), result.getName());

  }

}
