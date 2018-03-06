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
@Entity(name = UsergroupEntity.NAME)
public class UsergroupEntity extends PrincipalEntity {

    public static final String NAME = "usergroup";

    /**
     * A {@code Set} of {@code UserGroupEntityDefinition} that are parents for this usergroup.
     *
     * @return a set of usergroups that are parents for this usergroup.
     */
    @ManyToMany(fetch = FetchType.LAZY, targetEntity = UsergroupEntity.class)
    @JoinTable(name = UsergroupEntity.NAME + "_"
                    + UsergroupEntity.NAME, joinColumns = @JoinColumn(name = "subgroup_id"), inverseJoinColumns = @JoinColumn(name = UsergroupEntity.NAME
                    + "_id"), indexes = { @Index(columnList = "subgroup_id") })
    private Set<UsergroupEntity> superGroups;

    /**
     * A {@code Set} of {@code UserGroupEntityDefinition} that this usergroup is parent of.
     *
     * @return a set of usergroups that this usergroup is parent of.
     */
    @ManyToMany(fetch = FetchType.LAZY, targetEntity = UsergroupEntity.class)
    @JoinTable(name = UsergroupEntity.NAME + "_" + UsergroupEntity.NAME, joinColumns = @JoinColumn(name = UsergroupEntity.NAME
                    + "_id"), inverseJoinColumns = @JoinColumn(name = "subgroup_id"), indexes = { @Index(columnList = UsergroupEntity.NAME + "_id") })
    private Set<UsergroupEntity> subGroups;

    /**
     * A {@code Set} of {@code UserEntityDefinition} that are assigned to this {@code UserGroupEntityDefinition}.
     *
     * @return a set of users that are assigned to this usergroup.
     */
    @ManyToMany(fetch = FetchType.LAZY, targetEntity = UserEntity.class)
    @JoinTable(name = UsergroupEntity.NAME + "_" + UserEntity.NAME, joinColumns = @JoinColumn(name = UsergroupEntity.NAME
                    + "_id"), inverseJoinColumns = @JoinColumn(name = UserEntity.NAME + "_id"), indexes = { @Index(columnList = UsergroupEntity.NAME + "_id") })
    private Set<UserEntity> users;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = PurchaseLimitEntity.class)
    @JoinTable(name = UsergroupEntity.NAME + "_" + PurchaseLimitEntity.NAME, joinColumns = @JoinColumn(name = UsergroupEntity.NAME
                    + "_id"), inverseJoinColumns = @JoinColumn(name = PurchaseLimitEntity.NAME + "_id"), indexes = {
                    @Index(columnList = UsergroupEntity.NAME + "_id") })
    private Set<PurchaseLimitEntity> purchaseLimits;

    public Set<UsergroupEntity> getSuperGroups() {
        return superGroups;
    }

    public void setSuperGroups(Set<UsergroupEntity> superGroups) {
        this.superGroups = superGroups;
    }

    public Set<UsergroupEntity> getSubGroups() {
        return subGroups;
    }

    public void setSubGroups(Set<UsergroupEntity> subGroups) {
        this.subGroups = subGroups;
    }

    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }

    public Set<PurchaseLimitEntity> getPurchaseLimits() {
        return purchaseLimits;
    }

    public void setPurchaseLimits(Set<PurchaseLimitEntity> purchaseLimits) {
        this.purchaseLimits = purchaseLimits;
    }
}
