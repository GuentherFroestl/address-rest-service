package de.gammadata.microservices.addressrs.health.boundary;

import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author gfr
 */
@RunWith(Arquillian.class)
public class HealthResourceArquillianIT {


  /**
   *
   * @return
   */
  @Deployment
  public static JavaArchive createDeployment() {
    JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
            .addClass(HealthResource.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    System.out.println(jar.toString(true));
    return jar;
  }


  /**
   *
   */
  @BeforeClass
  public static void setUpClass() {

  }

  /**
   *
   */
  @AfterClass
  public static void tearDownClass() {
  }
  @Inject
          HealthResource instance;
  /**
   *
   */
  public HealthResourceArquillianIT() {
  }

  /**
   *
   */
  @Before
  public void setUp() {
  }

  /**
   *
   */
  @After
  public void tearDown() {
  }

  /**
   * Test of getPing method, of class HealthResource.
   */
  @Test
  public void testGetPing() {
    System.out.println("getPing");
    String expResult = "ping";
    String result = instance.getPing();
    assertEquals(expResult, result);
  }

}
