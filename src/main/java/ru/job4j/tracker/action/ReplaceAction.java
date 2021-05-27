package ru.job4j.tracker.action;

import ru.job4j.tracker.Store;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.output.Output;

public class ReplaceAction implements UserAction {
    private final Output out;

    public ReplaceAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Replace item";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Replace item ===");
        int id = input.askInt("Enter id: ");
        String name = input.askStr("Enter new item's name: ");
        Item item = new Item(name);
        if (tracker.replace(id, item)) {
            out.println("Item replaced");
        } else {
            out.println("Id not found");
        }
        return true;
    }
}
