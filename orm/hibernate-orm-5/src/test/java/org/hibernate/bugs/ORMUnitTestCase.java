/*
 * Copyright 2014 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hibernate.bugs;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.testing.junit4.BaseCoreFunctionalTestCase;
import org.junit.Test;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import java.util.Set;
import java.util.SortedSet;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM, using its built-in unit test framework.
 * Although ORMStandaloneTestCase is perfectly acceptable as a reproducer, usage of this class is much preferred.
 * Since we nearly always include a regression test with bug fixes, providing your reproducer using this method
 * simplifies the process.
 * <p>
 * What's even better?  Fork hibernate-orm itself, add your test case directly to a module's unit tests, then
 * submit it as a PR!
 */
public class ORMUnitTestCase extends BaseCoreFunctionalTestCase {

    // Add your entities here.
    @Override
    protected Class[] getAnnotatedClasses() {
        return new Class[] { Foo.class, Bar.class, X.class, Y.class };
    }

    // If you use *.hbm.xml mappings, instead of annotations, add the mappings here.
    @Override
    protected String[] getMappings() {
        return new String[] {
                        //				"Foo.hbm.xml",
                        //				"Bar.hbm.xml"
        };
    }

    // If those mappings reside somewhere other than resources/org/hibernate/test, change this.
    @Override
    protected String getBaseForMappings() {
        return "org/hibernate/test/";
    }

    // Add in any settings that are specific to your test.  See resources/hibernate.properties for the defaults.
    @Override
    protected void configure(Configuration configuration) {
        super.configure(configuration);

        configuration.setProperty(AvailableSettings.SHOW_SQL, Boolean.TRUE.toString());
        configuration.setProperty(AvailableSettings.FORMAT_SQL, Boolean.TRUE.toString());
        //configuration.setProperty( AvailableSettings.GENERATE_STATISTICS, "true" );
    }

    // Add your tests, using standard JUnit.
    @Test
    public void hhh123Test() throws Exception {
        // BaseCoreFunctionalTestCase automatically creates the SessionFactory and provides the Session.
        Session s = openSession();
        Transaction tx = s.beginTransaction();
        // Do stuff...
        tx.commit();
        s.close();
    }

    @Entity
    @Table(name = "foo")
    class Foo {

        @Id
        private long id;

        @ManyToMany(mappedBy = "foos")
        private Set<Bar> bars;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public Set<Bar> getBars() {
            return bars;
        }

        public void setBars(Set<Bar> bars) {
            this.bars = bars;
        }
    }

    @Entity
    @Table(name = "bar")
    class Bar {
        @Id
        private long id;

        @OrderColumn(name = "foo_order")
        @ManyToMany
        @JoinTable(name = "foo_bar")
        private SortedSet<Foo> foos;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public SortedSet<Foo> getFoos() {
            return foos;
        }

        public void setFoos(SortedSet<Foo> foos) {
            this.foos = foos;
        }
    }

    @Entity
    @Table(name = "x")
    class X {
        @Id
        private long id;

        @ManyToOne
        private Y y;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public Y getY() {
            return y;
        }

        public void setY(Y y) {
            this.y = y;
        }
    }

    @Entity
    @Table(name = "y")
    class Y {
        @Id
        private long id;

        @OrderColumn(name = "x_order")
        @OneToMany
        @JoinTable(name = "x_y")
        private SortedSet<X> x;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public SortedSet<X> getX() {
            return x;
        }

        public void setX(SortedSet<X> x) {
            this.x = x;
        }
    }
}
