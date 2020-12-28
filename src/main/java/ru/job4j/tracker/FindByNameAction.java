package ru.job4j.tracker;

public class FindByNameAction implements UserAction {
    @Override
    public String name() {
        return "Find items by name";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        Item[] items = tracker.findByName(input.askStr("Enter name: "));
        if (items.length > 0) {
            for (Item item : items) {
                System.out.println("Name: " + item.getName()
                                    + ", id: " + item.getId());
            }
        } else {
            System.out.println("Items not found");
        }
        return true;
    }
}
