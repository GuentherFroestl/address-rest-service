package de.gammadata.microservices.addressrs.addresses.control;

import static de.gammadata.microservices.addressrs.addresses.control.AbstractEntityJpaTest.em;
import de.gammadata.microservices.addressrs.addresses.entity.BaseEntity;
import de.gammadata.microservices.addressrs.addresses.entity.BaseQuerySpecification;
import de.gammadata.microservices.addressrs.application.entity.AddressServiceException;
import java.util.List;
import javax.persistence.EntityTransaction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 *
 * @author gfr
 * @param <T>
 * @param <Q>
 */
public abstract class AbstractCrudControllerTest<T extends BaseEntity, Q extends BaseQuerySpecification>
        extends AbstractEntityJpaTest {

  /**
   *
   * @return
   */
  public abstract T createTestEntity();

  /**
   *
   * @return
   */
  public abstract AbstractCrudController getTestee();

  private BaseEntityListener entityListener = spy(new BaseEntityListener());

  /**
   *
   */
  @Before
  public void setUp() {
    when(entityListener.getEm()).thenReturn(em);
  }

  /**
   *
   */
  @After
  public void tearDown() {
    AbstractEntityJpaTest.deleteEntities(createTestEntity().getClass(), em);
  }

  /**
   *
   */
  @Test
  public void test_CRUD() {
    AbstractEntityJpaTest.deleteEntities(createTestEntity().getClass(), em);
    //Test create
    T entity = createTestEntity();
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    T res1 = (T) getTestee().saveOrUpdateEntity(entity);
    tx.commit();
    Assert.assertNotNull("unexpected null result", res1);
    Assert.assertNotNull("No ID for Entity", res1.getId());
    Assert.assertNotNull("No modfied date for Entity", res1.getModified());
    Assert.assertEquals("Names are not equal", entity.getName(), res1.getName());

    //Test get
    tx.begin();
    T resRead = (T) getTestee().getEntity(res1.getId());
    tx.commit();
    Assert.assertNotNull("unexpected null result", res1);
    TestEntityProvider.setBasePropertiesForEquals(entity, resRead);
    Assert.assertEquals("Entity fetched are not equal to entityCreated", entity, resRead);

    //Test Update
    String newName = "changed name";
    Long id1 = res1.getId();
    Long time1 = res1.getModified().getTime();
    res1.setName(newName);
    tx.begin();
    T resUpdate = (T) getTestee().saveOrUpdateEntity(res1);
    tx.commit();
    Assert.assertNotNull("unexpected null result", resUpdate);
    Assert.assertNotNull("No Id for Entity", res1.getId());
    Assert.assertEquals("Ids don't match", id1, resUpdate.getId());
    Assert.assertNotNull("No modfied date for Entity", res1.getModified());
    Assert.assertTrue("Wrong modified date for result", resUpdate.getModified().getTime() >= time1);
    Assert.assertEquals("Names are not equal", newName, resUpdate.getName());

    //Test list with no query
    List<T> list = getTestee().searchEntities(null);
    Assert.assertNotNull("unexpected null result", list);
    Assert.assertEquals("List size is not correct", 1, list.size());
    Assert.assertEquals("Entities.id are not equal", resUpdate.getId(), list.get(0).getId());
    Assert.assertEquals("Names are not equal", newName, list.get(0).getName());

    long count = getTestee().countEntities(null);
    Assert.assertEquals("List size is not correct", 1l, count);

    //Test with query
    BaseQuerySpecification querySpec = new BaseQuerySpecification();
    querySpec.setQuery("changed");
    List<T> list2 = getTestee().searchEntities(querySpec);
    Assert.assertNotNull("unexpected null result", list2);
    Assert.assertEquals("List size is not correct", 1, list2.size());
    Assert.assertEquals("Entities.id are not equal", resUpdate.getId(), list2.get(0).getId());
    Assert.assertEquals("Names are not equal", newName, list2.get(0).getName());

    long count2 = getTestee().countEntities(querySpec);
    Assert.assertEquals("List size is not correct", 1l, count2);

    querySpec.setStart(0);
    querySpec.setLimit(10);
    List<T> list3 = getTestee().searchEntities(querySpec);
    Assert.assertNotNull("unexpected null result", list3);
    Assert.assertEquals("List size is not correct", 1, list3.size());

    querySpec.setStart(2);
    querySpec.setLimit(10);
    List<T> list4 = getTestee().searchEntities(querySpec);
    Assert.assertNotNull("unexpected null result", list4);
    Assert.assertEquals("List size is not correct", 0, list4.size());

    //test delete
    list = getTestee().searchEntities(null);
    tx.begin();
    for (T en : list) {
      getTestee().deleteEntity(en.getId());
    }
    tx.commit();

    List<T> emptyList = getTestee().searchEntities(null);
    Assert.assertNotNull("unexpected null list", emptyList);
    assertTrue("list is not empty after deleting all entities", emptyList.isEmpty());

    //test deletion with no entity
    try {
      getTestee().deleteEntity(999l);
      fail("expected AddressServiceException was not thrown");
    } catch (Exception e) {
      if (!(e instanceof AddressServiceException)) {
        fail("expected AddressServiceException but got " + e);
      }
    }

  }

}
