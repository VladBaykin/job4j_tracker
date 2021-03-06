package ru.job4j.tracker;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class SortByNameTest {

    @Test
    public void compare() {
        Item item = new Item("bName");
        Item item2 = new Item("aName");
        MemTracker tracker = new MemTracker();
        tracker.add(item);
        tracker.add(item2);
        List<Item> items = Arrays.asList(item, item2);
        items.sort(new SortByName());
        List<Item> expected = Arrays.asList(item2, item);
        assertEquals(expected, items);
    }
}