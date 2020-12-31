package ru.job4j.tracker;

import org.junit.Test;

import static org.junit.Assert.*;

public class StartUITest {

    @Test
    public void whenCreateItem() {
        Output output = new StubOutput();
        Input input = new StubInput(new String[] {"0", "Item name", "1"});
        Tracker tracker = new Tracker();
        UserAction[] actions = {
                new CreateAction(output),
                new ExitAction(output)};
        new StartUI(output).init(input, tracker, actions);
        assertEquals("Item name", tracker.findAll()[0].getName());
    }

    @Test
    public void whenReplaceItem() {
        Output output = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = new Item();
        item.setName("Replaced item");
        tracker.add(item);
        String replacedName = "New item name";
        Input input = new StubInput(
                new String[] {"0", String.valueOf(item.getId()), replacedName, "1"});
        UserAction[] actions = {new ReplaceAction(output), new ExitAction(output)};
        new StartUI(output).init(input, tracker, actions);
        assertEquals(replacedName, tracker.findById(item.getId()).getName());
    }

    @Test
    public void whenDeleteItem() {
        Output output = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = new Item();
        item.setName("Deleted item");
        tracker.add(item);
        Input input = new StubInput(
                new String[] {"0", String.valueOf(item.getId()), "1"});
        UserAction[] actions = {
                new DeleteAction(output),
                new ExitAction(output)};
        new StartUI(output).init(input, tracker, actions);
        assertNull(tracker.findById(item.getId()));
    }

    @Test
    public void whenExit() {
        Output output = new StubOutput();
        Input input = new StubInput(new String[] {"0"});
        Tracker tracker = new Tracker();
        UserAction[] actions = {new ExitAction(output)};
        new StartUI(output).init(input, tracker, actions);
        String ln = System.lineSeparator();
        assertEquals("Menu." + ln
                + "0. Exit program" + ln
                + "=== Exit program ===" + ln, output.toString());
    }

    @Test
    public void whenFindAll() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0", "1"});
        Tracker tracker = new Tracker();
        Item item = new Item();
        item.setName("test");
        tracker.add(item);
        UserAction[] actions = {
                new FindAllAction(out),
                new ExitAction(out),
        };
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertEquals("Menu." + ln
                        + "0. Show all items" + ln
                        + "1. Exit program" + ln
                        + "=== Show all items ===" + ln
                        + "Name: test, id: 1" + ln
                        + "Menu." + ln
                        + "0. Show all items" + ln
                        + "1. Exit program" + ln
                        + "=== Exit program ===" + ln
                , out.toString());
    }

    @Test
    public void whenFindByName() {
        Tracker tracker = new Tracker();
        Item item = new Item();
        item.setName("test");
        tracker.add(item);
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0", item.getName(), "1"});
        UserAction[] actions = {
                new FindByNameAction(out),
                new ExitAction(out),
        };
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertEquals("Menu." + ln
                        + "0. Find items by name" + ln
                        + "1. Exit program" + ln
                        + "=== Find items by name ===" + ln
                        + "Name: test, id: 1" + ln
                        + "Menu." + ln
                        + "0. Find items by name" + ln
                        + "1. Exit program" + ln
                        + "=== Exit program ===" + ln
                , out.toString());
    }

    @Test
    public void whenFindById() {
        Tracker tracker = new Tracker();
        Item item = new Item();
        item.setName("test");
        tracker.add(item);
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0", String.valueOf(item.getId()), "1"});
        UserAction[] actions = {
                new FindByIdAction(out),
                new ExitAction(out),
        };
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertEquals("Menu." + ln
                        + "0. Find item by id" + ln
                        + "1. Exit program" + ln
                        + "=== Find item by id ===" + ln
                        + "Name: test, id: 1" + ln
                        + "Menu." + ln
                        + "0. Find item by id" + ln
                        + "1. Exit program" + ln
                        + "=== Exit program ===" + ln
                , out.toString());
    }
}