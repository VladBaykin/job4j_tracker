package ru.job4j.tracker;

public class FindByIdAction implements UserAction {
    @Override
    public String name() {
        return "Find item by id";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        int id = input.askInt("Enter id: ");
        Item item = tracker.findById(id);
        if (item != null) {
            System.out.println("Name: " + item.getName()
                                + ", id: " + item.getId());
        } else {
            System.out.println("Id not found");
        }
        return true;
    }
}
