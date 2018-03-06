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
@Entity(name = CustomerEntity.NAME)
public class CustomerEntity extends UserEntity {
    public static final String NAME = "customer";

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = PurchaseLimitEntity.class)
    @JoinTable(name = CustomerEntity.NAME + "_" + PurchaseLimitEntity.NAME, joinColumns = @JoinColumn(name = CustomerEntity.NAME
                    + "_id"), inverseJoinColumns = @JoinColumn(name = PurchaseLimitEntity.NAME + "_id"), indexes = {
                    @Index(columnList = CustomerEntity.NAME + "_id") })
    private Set<PurchaseLimitEntity> purchaseLimits;

    public Set<PurchaseLimitEntity> getPurchaseLimits() {
        return purchaseLimits;
    }

    public void setPurchaseLimits(Set<PurchaseLimitEntity> purchaseLimits) {
        this.purchaseLimits = purchaseLimits;
    }
}
