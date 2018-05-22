package org.hibernate.bugs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * @author Petar Tahchiev
 * @since 1.5
 */
@Entity(name = "category")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class CategoryEntity {

    @Id
    private Long id;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
