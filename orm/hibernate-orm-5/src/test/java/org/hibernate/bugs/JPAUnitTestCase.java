package org.hibernate.bugs;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM, using the Java Persistence API.
 */
public class JPAUnitTestCase {

    private EntityManagerFactory entityManagerFactory;

    @Entity(name = "cart")
    class CartEntity {

        @Id
        private long id;

        @OneToOne(mappedBy = "cart", fetch = FetchType.LAZY, targetEntity = OrderEntity.class, cascade = { CascadeType.REMOVE })
        private OrderEntity purchasedOrder;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }
    }

    @Entity(name = "purchased_order")
    class OrderEntity {

        @Id
        private long id;

        @OneToOne(fetch = FetchType.LAZY, targetEntity = CartEntity.class)
        @JoinColumn(name = "cart_id", nullable = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_purchased_order_cart"))
        private CartEntity cart;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public CartEntity getCart() {
            return cart;
        }

        public void setCart(CartEntity cart) {
            this.cart = cart;
        }
    }

    @Before
    public void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("templatePU");
    }

    @After
    public void destroy() {
        entityManagerFactory.close();
    }

    // Entities are auto-discovered, so just add them anywhere on class-path
    // Add your tests, using standard JUnit.
    @Test
    public void hhh123Test() throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        CartEntity cart = new CartEntity();
        cart.setId(1);
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(1);
        orderEntity.setCart(cart);

        entityManager.persist(cart);
        entityManager.persist(orderEntity);

//        entityManager.getTransaction().commit();
//        entityManager.close();
//
//        entityManager.getTransaction().begin();

        String hql = "UPDATE cart SET purchasedOrder = :attr WHERE id = :id";
        Query updateQuery = entityManager.createQuery(hql);
        updateQuery.setParameter("attr", null);
        updateQuery.setParameter("id", 1L);
        int result = updateQuery.executeUpdate();
        System.out.println("RESULT IS: " + result);

        
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
