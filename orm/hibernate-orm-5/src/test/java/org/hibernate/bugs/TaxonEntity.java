package org.hibernate.bugs;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author Petar Tahchiev
 * @since 1.5
 */
@Entity(name = "taxon")
@Table(name = "taxon", uniqueConstraints = @UniqueConstraint(columnNames = { "catalog_version_id", "code" }))
@AttributeOverride(name = "code", column = @Column(name = "code", nullable = false, unique = false))
public class TaxonEntity extends CategoryEntity {

    @Column(name = "catalog_version_id")
    private String catalogVersion;

    public String getCatalogVersion() {
        return catalogVersion;
    }

    public void setCatalogVersion(String catalogVersion) {
        this.catalogVersion = catalogVersion;
    }
}
