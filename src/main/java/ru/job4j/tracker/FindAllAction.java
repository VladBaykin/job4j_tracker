package ru.job4j.tracker;

public class FindAllAction implements UserAction {
    private final Output out;

    public FindAllAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Show all items";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        out.println("=== Show all items ===");
        for (Item items : tracker.findAll()) {
            out.println("Name: " + items.getName()
                                + ", id: " + items.getId());
        }
        return true;
    }
}
