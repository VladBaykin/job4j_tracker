package ru.job4j.tracker;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import static org.junit.Assert.*;

public class SqlTrackerTest {

    public Connection init() {
        try (InputStream in = SqlTracker.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void createItem() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("name"));
            assertEquals(1, tracker.findByName("name").size());
        }
    }

    @Test
    public void replace() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item bug = new Item("Bug");
            tracker.add(bug);
            int id = bug.getId();
            Item bugWithDesc = new Item("Bug with description");
            tracker.replace(id, bugWithDesc);
            assertEquals("Bug with description", tracker.findById(id).getName());
        }
    }

    @Test
    public void delete() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item bug = new Item("Bug");
            tracker.add(bug);
            int id = bug.getId();
            tracker.delete(id);
            assertNull(tracker.findById(id));
        }
    }

    @Test
    public void findAll() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item1 = new Item("one");
            Item item2 = new Item("two");
            tracker.add(item1);
            tracker.add(item2);
            assertEquals(2, tracker.findAll().size());
        }
    }

    @Test
    public void findByName() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item1 = new Item("one");
            Item item2 = new Item("two");
            Item item3 = new Item("two");
            tracker.add(item1);
            tracker.add(item2);
            tracker.add(item3);
            assertEquals(2, tracker.findByName("two").size());
        }
    }

    @Test
    public void findById() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item1 = new Item("one");
            Item item2 = new Item("two");
            tracker.add(item1);
            tracker.add(item2);
            assertEquals("one", tracker.findById(item1.getId()).getName());
        }
    }
}