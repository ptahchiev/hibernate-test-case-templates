package org.hibernate.bugs;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;
import java.util.Objects;

import static org.hibernate.testing.transaction.TransactionUtil.doInJPA;
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
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		// Do stuff...
		entityManager.getTransaction().commit();
		entityManager.close();
	}


	@Test
	public void testInitializedProxyCanBeUnproxied() {
		Parent u = new Parent();
		SubChild c = new SubChild();
		u.setChild(c);
		doInJPA(() -> entityManagerFactory, (entityManager -> {
			entityManager.persist(u);
		}));
		doInJPA(() -> entityManagerFactory, (entityManager -> {
			Parent parent = entityManager.find(Parent.class, u.getId());
			SubChild unproxiedChild = initializeAndUnproxy(getUser(parent));
			assertEquals(SubChild.class, unproxiedChild.getClass());
		}));
	}

	public <U extends Child> U getUser(Parent p) {
		return (U) p.getChild();
	}

	public <T> T initializeAndUnproxy(T entity) {
		if (entity == null) {
			throw new NullPointerException("Entity passed for initialization is null!");
		}

		if (!Hibernate.isInitialized(entity)) {
			Hibernate.initialize(entity);
			if (entity instanceof HibernateProxy) {
				entity = (T) ((HibernateProxy) entity).getHibernateLazyInitializer().getImplementation();
			}
		}
		return entity;
	}


	@Entity(name = "Parent")
	public static class Parent {
		@Id
		@GeneratedValue
		private Integer id;

		private String name;

		@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
		private Child child;

		public Integer getId() {
			return id;
		}

		public void setChild(Child child) {
			this.child = child;
			child.setParent(this);
		}

		public Child getChild() {
			return child;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Parent parent = (Parent) o;
			return Objects.equals(name, parent.name);
		}

		@Override
		public int hashCode() {
			return Objects.hash(name);
		}
	}

	@Entity(name = "Child")
	public static class Child {
		@Id
		@GeneratedValue
		private Integer id;

		@OneToOne(fetch = FetchType.LAZY)
		private Parent parent;

		public Integer getId() {
			return id;
		}

		public void setParent(Parent parent) {
			this.parent = parent;
		}

		public Parent getParent() {
			return parent;
		}
	}

	@Entity(name = "SubChild")
	public static class SubChild extends Child {

	}
}
