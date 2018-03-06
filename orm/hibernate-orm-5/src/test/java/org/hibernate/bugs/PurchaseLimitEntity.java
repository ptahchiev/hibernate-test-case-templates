package org.hibernate.bugs;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Set;

/**
 * @author Petar Tahchiev
 * @since 1.5
 */
@Entity(name = PurchaseLimitEntity.NAME)
public class PurchaseLimitEntity extends AbstractEntity {

    public static final String NAME = "purchase_limit";

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = UsergroupEntity.class)
    @JoinTable(name = UsergroupEntity.NAME + "_" + PurchaseLimitEntity.NAME, joinColumns = @JoinColumn(name = PurchaseLimitEntity.NAME
                    + "_id"), inverseJoinColumns = @JoinColumn(name = UsergroupEntity.NAME + "_id"), indexes = {
                    @Index(columnList = PurchaseLimitEntity.NAME + "_id") })
    private Set<UsergroupEntity> userGroups;

    public Set<UsergroupEntity> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(Set<UsergroupEntity> userGroups) {
        this.userGroups = userGroups;
    }
}
