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
@Entity(name = UserEntity.NAME)
public class UserEntity extends PrincipalEntity {

    public static final String NAME = "user";

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = UsergroupEntity.class)
    @JoinTable(name = UserEntity.NAME + "_" + UsergroupEntity.NAME, joinColumns = @JoinColumn(name = UserEntity.NAME
                    + "_id"), inverseJoinColumns = @JoinColumn(name = UsergroupEntity.NAME + "_id"), indexes = { @Index(columnList = UserEntity.NAME + "_id") })
    private Set<UsergroupEntity> userGroups;

    public Set<UsergroupEntity> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(Set<UsergroupEntity> userGroups) {
        this.userGroups = userGroups;
    }
}
