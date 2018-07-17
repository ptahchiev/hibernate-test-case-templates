package org.hibernate.bugs;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM, using the Java Persistence API.
 */
public class JPAUnitTestCase {

    private EntityManagerFactory entityManagerFactory;

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
        // persist parent entity in a transaction

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Parent parent = new Parent();
        em.persist(parent);
        //em.flush();
        int id = parent.getId();

        em.getTransaction().commit();
        em.close();

        // relate and persist child entity in a new transaction

        em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        parent = em.find(Parent.class, id);
        // *: parent.getChildren().size();
        assertEquals(0, parent.getChildren().size());
        Child child = new Child();
        child.setParents(Arrays.asList(parent));
        parent.setChildren(Arrays.asList(child));
        em.persist(child);
        //em.persist(parent);
        //em.flush();

        assertEquals(1, parent.getChildren().size()); // -> [Child@36bf7916, Child@36bf7916]

        em.getTransaction().commit();
        em.close();

        em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Parent p = em.find(Parent.class, id);
        // FAILURE: 1 expected, but 2 childs in List
        Assert.assertEquals(1, p.getChildren().size());

        em.getTransaction().commit();
        em.close();
    }
}
