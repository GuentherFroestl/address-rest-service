package de.gammadata.microservices.addressrs.addresses.boundary;

import de.gammadata.microservices.addressrs.common.boundary.AbstractCrudResource;
import de.gammadata.microservices.addressrs.common.control.AbstractCrudController;
import de.gammadata.microservices.addressrs.addresses.control.CityCrudController;
import de.gammadata.microservices.addressrs.common.entity.SimpleQuerySpecification;
import de.gammadata.microservices.addressrs.addresses.entity.City;
import de.gammadata.microservices.addressrs.common.entity.EntityRelatedQuerySpec;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ws.rs.Path;

/**
 * Cities resources, exposed as /cities.
 */
@ManagedBean
@Path(CitiesResource.PATH)
public class CitiesResource extends AbstractCrudResource<City, City, SimpleQuerySpecification> {

  /**
   *
   */
  public static final String PATH = "/cities";
  public static final String WITHIN_CITY = "/withincountry/{fkid}";

  @EJB
  CityCrudController cityController;

  /**
   *
   * @return
   */
  @Override
  public AbstractCrudController<City, City, SimpleQuerySpecification> getCrudController() {
    return cityController;
  }

  /**
   * find cities within a given country by id.
   *
   * @param querySpec EntityRelatedQuerySpec
   * @return List of cities
   */
  public List<City> findCitiesInCountry(EntityRelatedQuerySpec querySpec) {
    return cityController.findCitiesInCountry(querySpec);
  }

}
