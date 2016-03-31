package de.gammadata.microservices.addressrs.addresses.entity;

import de.gammadata.microservices.addressrs.common.entity.BaseEntity;
import de.gammadata.microservices.addressrs.common.control.BaseEntityListener;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author gfr
 */
@Entity
@EntityListeners({BaseEntityListener.class})
@Table(indexes = {
    @Index(name = "CITY_NAME_IDX", columnList = "NAME"),
    @Index(name = "CITY_COUNTRY_NAME_IDX", columnList = "INTERNATIONAL_NAME")})

@NamedQueries({
    @NamedQuery(name = City.SIMPLE_SEARCH_QUERY_NAME,
            query = "select e from City e"
            + City.WHERE_CLAUSE
    ),
    @NamedQuery(name = City.SIMPLE_COUNT_QUERY_NAME,
            query = "select count(e) from City e"
            + City.WHERE_CLAUSE
    ),
    @NamedQuery(name = City.QUERY_CITIES_WITHIN_COUNTRY,
            query = "select e from City e"
            + City.WHERE_CLAUSE_CITIES_IN_COUNTRY
    )})
public class City extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    public static final String SIMPLE_SEARCH_QUERY_NAME = "City_simpleSearchQuery";

    /**
     *
     */
    public static final String SIMPLE_COUNT_QUERY_NAME = "City_simpleSearchCount";

    /**
     *
     */
    public static final String QUERY_CITIES_WITHIN_COUNTRY = "City_queryByCountry";

    /**
     *
     */
    public static final String WHERE_CLAUSE = " where "
            + "LOWER(e.name) like :" + BaseEntity.SIMPLE_SEARCH_QUERY_PARAMETER
            + " OR LOWER(e.internationalName) like :" + BaseEntity.SIMPLE_SEARCH_QUERY_PARAMETER;

    /**
     *
     */
    public static final String WHERE_CLAUSE_CITIES_IN_COUNTRY = " where "
            + " e.country.id= :" + BaseEntity.ID_PARAMETER
            + " AND ("
            + " LOWER(e.name) like :" + BaseEntity.SIMPLE_SEARCH_QUERY_PARAMETER
            + " OR LOWER(e.internationalName) like :" + BaseEntity.SIMPLE_SEARCH_QUERY_PARAMETER
            +")";

    @Column(name = "INTERNATIONAL_NAME")
    private String internationalName;
    
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "COUNTRY_ID")
    private Country country;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "PROVINCE_ID")
    private Province province;

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    /**
     *
     * @return
     */
    public String getInternationalName() {
        return internationalName;
    }

    /**
     *
     * @param internationalName
     */
    public void setInternationalName(String internationalName) {
        this.internationalName = internationalName;
    }

    /**
     *
     * @return
     */
    public Country getCountry() {
        return country;
    }

    /**
     *
     * @param country
     */
    public void setCountry(Country country) {
        this.country = country;
        if (country != null) {
            this.internationalName = country.getName();
        }
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 83 * hash + Objects.hashCode(this.country);
        hash = 83 * hash + Objects.hashCode(this.province);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final City other = (City) obj;
        if (!Objects.equals(this.country, other.country)) {
            return false;
        }
        if (!Objects.equals(this.province, other.province)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "City{" + super.toString() + '}';
    }

}
