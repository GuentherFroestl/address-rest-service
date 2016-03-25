package de.gammadata.microservices.addressrs.addresses.entity;

import de.gammadata.microservices.addressrs.common.control.BaseEntityListener;
import de.gammadata.microservices.addressrs.common.entity.BaseEntity;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Entity for a province, district (China), state (US), etc.
 *
 * @author gfr
 */
@Entity
@EntityListeners({BaseEntityListener.class})
@Table(name = "PROVINCE",
        indexes = {
            @Index(name = "PROVINCE_NAME_IDX", columnList = "NAME")})
@NamedQueries({
    @NamedQuery(name = Province.SIMPLE_SEARCH_QUERY_NAME,
            query = "select e from Province e"
            + Province.WHERE_CLAUSE
    ),
    @NamedQuery(name = Province.SIMPLE_COUNT_QUERY_NAME,
            query = "select count(e) from Province e"
            + Province.WHERE_CLAUSE
    ),
    @NamedQuery(name = Province.QUERY_PROVINCES_WITHIN_COUNTRY,
            query = "select e from Province e"
            + Province.WHERE_CLAUSE_PROVINCES_IN_COUNTRY
    )})
public class Province extends BaseEntity {

    private static final long serialVersionUID = 1L;

    public static final String SIMPLE_SEARCH_QUERY_NAME = "Province_simpleSearchQuery";
    public static final String SIMPLE_COUNT_QUERY_NAME = "Province_simpleSearchCount";
    public static final String QUERY_PROVINCES_WITHIN_COUNTRY = "Province_queryByCountry";

    public static final String WHERE_CLAUSE = " where "
            + "LOWER(e.name) like :" + BaseEntity.SIMPLE_SEARCH_QUERY_PARAMETER;

    public static final String WHERE_CLAUSE_PROVINCES_IN_COUNTRY = " where "
            + " e.country.id= :" + BaseEntity.ID_PARAMETER
            + " AND"
            + " LOWER(e.name) like :" + BaseEntity.SIMPLE_SEARCH_QUERY_PARAMETER;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "COUNTRY_ID")
    private Country country;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 89 * hash + Objects.hashCode(this.country);
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
        final Province other = (Province) obj;
        if (!Objects.equals(this.country, other.country)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Province{" + super.toString() + '}';
    }

}
