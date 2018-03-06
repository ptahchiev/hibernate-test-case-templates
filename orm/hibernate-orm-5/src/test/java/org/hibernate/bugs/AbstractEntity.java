package org.hibernate.bugs;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Petar Tahchiev
 * @since 1.5
 */
@MappedSuperclass
public class AbstractEntity {

    @Id
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
