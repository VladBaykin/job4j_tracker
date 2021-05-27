package ru.job4j.tracker;

import org.junit.Test;

import static org.junit.Assert.*;

public class MemTrackerTest {

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        MemTracker tracker = new MemTracker();
        Item item = new Item("test");
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertEquals(item.getName(), result.getName());
    }

    @Test
    public void whenReplace() {
        MemTracker tracker = new MemTracker();
        Item bug = new Item("Bug");
        tracker.add(bug);
        int id = bug.getId();
        Item bugWithDesc = new Item("Bug with description");
        tracker.replace(id, bugWithDesc);
        assertEquals("Bug with description", tracker.findById(id).getName());
    }

    @ Test
    public void whenDelete() {
        MemTracker tracker = new MemTracker();
        Item bug = new Item("Bug");
        tracker.add(bug);
        int id = bug.getId();
        tracker.delete(id);
        assertNull(tracker.findById(id));
    }
}