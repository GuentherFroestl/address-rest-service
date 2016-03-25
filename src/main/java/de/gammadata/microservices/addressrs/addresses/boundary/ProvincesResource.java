/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gammadata.microservices.addressrs.addresses.boundary;

import de.gammadata.microservices.addressrs.addresses.control.ProvinceCrudController;
import de.gammadata.microservices.addressrs.addresses.entity.Province;
import de.gammadata.microservices.addressrs.common.boundary.AbstractCrudResource;
import de.gammadata.microservices.addressrs.common.control.AbstractCrudController;
import de.gammadata.microservices.addressrs.common.entity.EntityRelatedQuerySpec;
import de.gammadata.microservices.addressrs.common.entity.SimpleQuerySpecification;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ws.rs.Path;

/**
 * Provinces resource, exposed as /provinces
 *
 * @author gfr
 */
@ManagedBean
@Path(ProvincesResource.PATH)
public class ProvincesResource extends AbstractCrudResource<Province, Province, SimpleQuerySpecification> {

    public static final String PATH = "/provinces";
    public static final String WITHIN_CITY = "/withincountry/{fkid}";

    @EJB
    ProvinceCrudController provinceController;

    @Override
    public AbstractCrudController<Province, Province, SimpleQuerySpecification> getCrudController() {
        return provinceController;
    }

    public List<Province> findProvincesInCountry(EntityRelatedQuerySpec querySpec) {
        return provinceController.findProvincesInCountry(querySpec);
    }
}
