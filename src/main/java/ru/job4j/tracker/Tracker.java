package ru.job4j.tracker;

import java.util.Arrays;

public class Tracker {
    private final Item[] items = new Item[100];
    private int ids = 1;
    private int size = 0;

    public Item add(Item item) {
        item.setId(ids++);
        items[size++] = item;
        return item;
    }

    public Item[] findAll() {
        return Arrays.copyOf(items, size);
    }

    public Item[] findByName(String key) {
        Item[] result = new Item[size];
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (items[i] != null && items[i].getName().equals(key)) {
                result[index] = items[i];
                index++;
            }
        }
        return Arrays.copyOf(result, index);
    }

    public Item findById(int id) {
        Item result = null;
        for (int i = 0; i < size; i++) {
            if (items[i].getId() == id) {
                result = items[i];
                break;
            }
        }
        return result;
    }
}