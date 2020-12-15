package ru.job4j.tracker;


public class StartUI {
    public void init(Input input, Tracker tracker) {
        boolean run = true;
        while (run) {
            this.showMenu();
            int select = input.askInt("Select: ");
            if (select == 0) {
                System.out.println("=== Create a new Item ===");
                String name = input.askStr("Enter name: ");
                Item item = new Item();
                item.setName(name);
                tracker.add(item);
            } else if (select == 1) {
                System.out.println("=== All items ===");
                for (Item items : tracker.findAll()) {
                    System.out.println("Name: " + items.getName() + ", id: " + items.getId());
                }
            } else if (select == 2) {
                System.out.println("=== Replace item ===");
                int id = input.askInt("Enter id: ");
                String name = input.askStr("Enter new item's name: ");
                Item item = new Item();
                item.setName(name);
                if (tracker.replace(id, item)) {
                    System.out.println("Item replaced");
                } else {
                    System.out.println("Id not found");
                }
            } else if (select == 3) {
                System.out.println("=== Delete item ===");
                int id = input.askInt("Enter id: ");
                if (tracker.delete(id)) {
                    System.out.println("Item deleted");
                } else {
                    System.out.println("Id not found");
                }
            } else if (select == 4) {
                System.out.println("=== Find item by id ===");
                int id = input.askInt("Enter id: ");
                Item item = tracker.findById(id);
                if (item != null) {
                    System.out.println("Name: " + item.getName() + ", id: " + item.getId());
                } else {
                    System.out.println("Id not found");
                }
            } else if (select == 5) {
                System.out.println("=== Find items by name ===");
                Item[] items = tracker.findByName(input.askStr("Enter name: "));
                if (items.length > 0) {
                    for (Item item : items) {
                        System.out.println("Name: " + item.getName() + ", id: " + item.getId());
                    }
                } else {
                    System.out.println("Items not found");
                }
            } else if (select == 6) {
                System.out.println("=== Exit program ===");
                run = false;
            }
        }
    }

    private void showMenu() {
        System.out.println("Menu.");
        System.out.println("0. Add new item");
        System.out.println("1. Show all items");
        System.out.println("2. Replace item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by id");
        System.out.println("5. Find items by name");
        System.out.println("6. Exit program");
    }

    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Tracker tracker = new Tracker();
        new StartUI().init(input, tracker);
    }
}