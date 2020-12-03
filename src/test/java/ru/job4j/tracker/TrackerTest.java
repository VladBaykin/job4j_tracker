package ru.job4j.tracker;

import org.junit.Test;

import static org.junit.Assert.*;

public class TrackerTest {

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item();
        item.setName("test");
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertEquals(item.getName(), result.getName());
    }
}