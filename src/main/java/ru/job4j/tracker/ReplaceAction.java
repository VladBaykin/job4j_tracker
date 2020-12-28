package ru.job4j.tracker;

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
    public boolean execute(Input input, Tracker tracker) {
        out.println("=== Replace item ===");
        int id = input.askInt("Enter id: ");
        String name = input.askStr("Enter new item's name: ");
        Item item = new Item();
        item.setName(name);
        if (tracker.replace(id, item)) {
            out.println("Item replaced");
        } else {
            out.println("Id not found");
        }
        return true;
    }
}
