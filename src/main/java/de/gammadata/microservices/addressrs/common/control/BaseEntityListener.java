package de.gammadata.microservices.addressrs.common.control;

import de.gammadata.microservices.addressrs.common.entity.BaseEntity;
import java.util.Date;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 *
 * @author gfr
 */
public class BaseEntityListener {

    public static final String FULLTEXT_SPACE_CHAR = " ";

    @Inject
    @EntityManagerQualifier(EntityManagerType.MULTI_TENANT)
    EntityManager em;

    /**
     *
     * @param entity
     */
    @PreUpdate
    public void preUpdate(BaseEntity entity) {
        stampModification(entity);
    }

    /**
     *
     * @param entity
     */
    @PrePersist
    public void prePersist(BaseEntity entity) {
        stampModification(entity);
    }

    /**
     *
     * @param entity
     */
    public void stampModification(BaseEntity entity) {
        if (entity != null) {
            entity.setModified(new Date());
        }
    }

}
