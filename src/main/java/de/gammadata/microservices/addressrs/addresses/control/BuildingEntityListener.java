package de.gammadata.microservices.addressrs.addresses.control;

import de.gammadata.microservices.addressrs.addresses.entity.Building;
import de.gammadata.microservices.addressrs.common.control.BaseEntityListener;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * Listener for Building, which will update the fullTextSearchField.
 *
 * @author gfr
 */
public class BuildingEntityListener extends BaseEntityListener {

    /**
     *
     * @param entity
     */
    @PrePersist
    public void prePersist(Building entity) {
        super.prePersist(entity);
        updateFullTextSearchField(entity);
    }

    @PreUpdate
    public void preUpdate(Building entity) {
        super.preUpdate(entity);
        updateFullTextSearchField(entity);
    }

    private void updateFullTextSearchField(Building bd) {
        if (bd == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        if (bd.getNumber() != null) {
            sb.append(bd.getNumber());

        }
        if (bd.getName() != null) {
            sb.append(FULLTEXT_SPACE_CHAR);
            sb.append(bd.getName());

        }
        if (bd.getStreet() != null) {
            if (bd.getStreet().getName() != null) {
                sb.append(FULLTEXT_SPACE_CHAR);
                sb.append(bd.getStreet().getName());
            }

            if (bd.getStreet().getZipCode() != null && bd.getStreet().getZipCode().getName() != null) {
                sb.append(FULLTEXT_SPACE_CHAR);
                sb.append(bd.getStreet().getZipCode().getName());
            }

            if (bd.getStreet().getCity() != null) {
                if (bd.getStreet().getCity().getName() != null) {
                    sb.append(FULLTEXT_SPACE_CHAR);
                    sb.append(bd.getStreet().getCity().getName());
                }
                if (bd.getStreet().getCity().getProvince() != null
                        && bd.getStreet().getCity().getProvince().getName() != null) {
                    sb.append(FULLTEXT_SPACE_CHAR);
                    sb.append(bd.getStreet().getCity().getProvince().getName());

                }
                if (bd.getStreet().getCity().getCountry() != null) {
                    if (bd.getStreet().getCity().getCountry().getName() != null) {
                        sb.append(FULLTEXT_SPACE_CHAR);
                        sb.append(bd.getStreet().getCity().getCountry().getName());
                    }
                    if (bd.getStreet().getCity().getCountry().getIso2CountryCode() != null) {
                        sb.append(FULLTEXT_SPACE_CHAR);
                        sb.append(bd.getStreet().getCity().getCountry().getIso2CountryCode());
                    }
                    if (bd.getStreet().getCity().getCountry().getIso3CountryCode() != null) {
                        sb.append(FULLTEXT_SPACE_CHAR);
                        sb.append(bd.getStreet().getCity().getCountry().getIso3CountryCode());
                    }
                }
            }//end city and upwards relations

        }//end street and upwards relations
        bd.setFullTextSearch(sb.toString());
    }
}
