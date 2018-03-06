package org.hibernate.bugs;

import javax.persistence.Entity;

/**
 * @author Petar Tahchiev
 * @since 1.5
 */
@Entity(name = PrincipalEntity.NAME)
public class PrincipalEntity extends AbstractEntity {

    public static final String NAME = "principal";

}
