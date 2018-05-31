package org.hibernate.bugs;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * @author Petar Tahchiev
 * @since 1.5
 */
@Entity(name = "category")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class CategoryEntity extends AbstractEntity {

}
