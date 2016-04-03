package de.gammadata.microservices.addressrs.addresses.boundary;

import de.gammadata.microservices.addressrs.addresses.control.AbstractEntityJpaTest;
import de.gammadata.microservices.addressrs.addresses.control.StreetCrudController;
import de.gammadata.microservices.addressrs.addresses.control.TestEntityProvider;
import de.gammadata.microservices.addressrs.addresses.entity.City;
import de.gammadata.microservices.addressrs.addresses.entity.Country;
import de.gammadata.microservices.addressrs.addresses.entity.Province;
import de.gammadata.microservices.addressrs.addresses.entity.Street;
import de.gammadata.microservices.addressrs.addresses.entity.StreetBasics;
import de.gammadata.microservices.addressrs.addresses.entity.ZipCode;
import java.util.List;
import javax.persistence.EntityTransaction;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

/**
 *
 * @author gfr
 */
public class StreetResourceJpaTest extends AbstractEntityJpaTest {
  @AfterClass
  public static void tearDownClass() {
  }

  @Spy
  private StreetCrudController crudController = new StreetCrudController();
  @Spy
  @InjectMocks //will inject StreetCrudController
  private StreetResource testee = new StreetResource();


  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    when(crudController.getEm()).thenReturn(em);
  }

  @After
  public void tearDown() {
    deleteEntities(Street.class, em);
    deleteEntities(City.class, em);
    deleteEntities(Province.class, em);
    deleteEntities(ZipCode.class, em);
    deleteEntities(Country.class, em);
  }

  /**
   * Test of findStreetsInCity method, of class StreetResource.
   */
  @Test
  public void testFindStreetsWithVariousMethods() {
    System.out.println("findStreetsInCity");
    Street adrCreated = TestEntityProvider.createStreetWithAllRelations();
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    Street result = testee.saveOrUpdateEntity(adrCreated);
    tx.commit();
    assertNotNull("unexpected null result for address", result);
    assertNotNull("unexpected null result for address.id", result.getId());
    List<StreetBasics> listResult = testee.findByQuery(null, null, null);
     assertNotNull("unexpected null result for address", listResult);
    assertTrue("no street for citis found", listResult.size() > 0);

    assertNotNull("unexpected null result for address.getCity", result.getCity());
    assertNotNull("unexpected null result for address.getCity.getId", result.getCity().getId());

    List<StreetBasics> cityResult = testee.findStreetsInCity(result.getCity().getId(), null, null, null);
    assertNotNull("unexpected null result for address", cityResult);
    assertTrue("no street for citis found", cityResult.size() > 0);

    //find street by ZipCode
    assertNotNull("unexpected null result for address.getCity", result.getZipCode());
    assertNotNull("unexpected null result for address.getCity.getId", result.getZipCode().getId());

    List<StreetBasics> zipResult = testee.findStreetsInZipCode(result.getZipCode().getId(), null, null, null);
    assertNotNull("unexpected null result for address", zipResult);
    assertTrue("no street for citis found", zipResult.size() > 0);
  }

}
