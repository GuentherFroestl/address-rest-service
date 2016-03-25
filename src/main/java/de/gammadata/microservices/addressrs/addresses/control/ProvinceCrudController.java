/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gammadata.microservices.addressrs.addresses.control;

import de.gammadata.microservices.addressrs.addresses.entity.Country;
import de.gammadata.microservices.addressrs.addresses.entity.Province;
import de.gammadata.microservices.addressrs.application.entity.AddressServiceException;
import de.gammadata.microservices.addressrs.common.control.AbstractCrudController;
import de.gammadata.microservices.addressrs.common.entity.BaseEntity;
import de.gammadata.microservices.addressrs.common.entity.EntityRelatedQuerySpec;
import de.gammadata.microservices.addressrs.common.entity.SimpleQuerySpecification;
import java.util.List;
import java.util.Locale;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 * CRUD Controller for Provinces.
 *
 * @author gfr
 */
@Stateless
public class ProvinceCrudController extends AbstractCrudController<Province, Province, SimpleQuerySpecification> {

    /**
     * QUery Provinces within a country.
     *
     * @param querySpec EntityRelatedQuerySpec
     * @return List of Province
     */
    public List<Province> findProvincesInCountry(EntityRelatedQuerySpec querySpec) {
        if (querySpec == null || querySpec.getRelatedId() == 0) {
            throw new AddressServiceException(AddressServiceException.Error.VALIDATION, "CountryID must not be null to query Provinces within country");
        }
        TypedQuery<Province> query;
        query = getEm().createNamedQuery(Province.QUERY_PROVINCES_WITHIN_COUNTRY, Province.class);
        query.setParameter(BaseEntity.ID_PARAMETER, querySpec.getRelatedId());
        String queryStr = "";
        if (querySpec.getQuery() != null) {
            queryStr = querySpec.getQuery();
        }
        query.setParameter(BaseEntity.SIMPLE_SEARCH_QUERY_PARAMETER, queryStr.toLowerCase(Locale.GERMAN) + "%");

        setQueryLimits(query, querySpec);
        List<Province> results = query.getResultList();
        return results;
    }

    /**
     *
     * @param pProvince Province
     * @return Province
     */
    @Override
    public Province saveOrUpdateEntity(Province pProvince) {
        relateEntities(pProvince);
        return super.saveOrUpdateEntity(pProvince);
    }

    private void relateEntities(Province pProvince) {
        if (pProvince != null && pProvince.getCountry() != null
                && pProvince.getCountry().getId() != null && pProvince.getCountry().getId() > 0
                && getEm() != null) {

            Country c = getEm().find(Country.class, pProvince.getCountry().getId());
            if (c != null) {
                pProvince.setCountry(c);
            } else {
                throw new AddressServiceException(AddressServiceException.Error.VALIDATION,
                        "referenced Country with id=" + pProvince.getCountry().getId() + " not found");
            }
        }
    }

    @Override
    public Class<Province> getEntityClass() {
        return Province.class;
    }

    @Override
    public String getSimpleSearchQueryName() {
        return Province.SIMPLE_SEARCH_QUERY_NAME;
    }

    @Override
    public String getSimpleSearchCountName() {
        return Province.SIMPLE_COUNT_QUERY_NAME;
    }

    @Override
    public String getNativeSearchQuery() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getResultSetMappingName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
