package ru.job4j.tracker.action;

import ru.job4j.tracker.Store;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.output.Output;

import java.util.List;

public class FindByNameAction implements UserAction {
    private final Output out;

    public FindByNameAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Find items by name";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Find items by name ===");
        List<Item> items = tracker.findByName(input.askStr("Enter name: "));
        if (items.size() > 0) {
            for (Item item : items) {
                out.println("Name: " + item.getName()
                            + ", id: " + item.getId());
            }
        } else {
            out.println("Items not found");
        }
        return true;
    }
}
