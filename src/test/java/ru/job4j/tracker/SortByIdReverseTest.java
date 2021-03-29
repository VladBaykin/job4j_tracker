package ru.job4j.tracker;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class SortByIdReverseTest {

    @Test
    public void compare() {
        Item item = new Item("Name");
        Item item2 = new Item("Name2");
        Tracker tracker = new Tracker();
        tracker.add(item);
        tracker.add(item2);
        List<Item> items = Arrays.asList(item, item2);
        items.sort(new SortByIdReverse());
        List<Item> expected = Arrays.asList(item2, item);
        assertEquals(expected, items);
    }
}