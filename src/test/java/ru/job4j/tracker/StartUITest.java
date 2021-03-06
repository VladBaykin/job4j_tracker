package ru.job4j.tracker;

import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.tracker.action.*;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.input.StubInput;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.output.StubOutput;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StartUITest {

    @Ignore
    @Test
    public void whenCreateItem() {
        Output output = new StubOutput();
        Input input = new StubInput(new String[] {"0", "Item name", "1"});
        Store tracker = new SqlTracker();
        tracker.init();
        List<UserAction> actions = new ArrayList<>();
        actions.add(new CreateAction(output));
        actions.add(new ExitAction(output));
        new StartUI(output).init(input, tracker, actions);
        assertEquals("Item name", tracker.findAll().get(0).getName());
    }

    @Ignore
    @Test
    public void whenReplaceItem() {
        Output output = new StubOutput();
        Store tracker = new SqlTracker();
        tracker.init();
        Item item = new Item("Replaced item");
        tracker.add(item);
        String replacedName = "New item name";
        Input input = new StubInput(
                new String[] {"0", String.valueOf(item.getId()), replacedName, "1"});
        List<UserAction> actions = new ArrayList<>();
        actions.add(new ReplaceAction(output));
        actions.add(new ExitAction(output));
        new StartUI(output).init(input, tracker, actions);
        assertEquals(replacedName, tracker.findById(item.getId()).getName());
    }

    @Ignore
    @Test
    public void whenDeleteItem() {
        Output output = new StubOutput();
        Store tracker = new SqlTracker();
        tracker.init();
        Item item = new Item("Deleted item");
        tracker.add(item);
        Input input = new StubInput(
                new String[] {"0", String.valueOf(item.getId()), "1"});
        List<UserAction> actions = new ArrayList<>();
        actions.add(new DeleteAction(output));
        actions.add(new ExitAction(output));
        new StartUI(output).init(input, tracker, actions);
        assertNull(tracker.findById(item.getId()));
    }

    @Ignore
    @Test
    public void whenExit() {
        Output output = new StubOutput();
        Input input = new StubInput(new String[] {"0"});
        Store tracker = new SqlTracker();
        tracker.init();
        List<UserAction> actions = new ArrayList<>();
        actions.add(new ExitAction(output));
        new StartUI(output).init(input, tracker, actions);
        String ln = System.lineSeparator();
        assertEquals("Menu." + ln
                + "0. Exit program" + ln
                + "=== Exit program ===" + ln, output.toString());
    }

    @Ignore
    @Test
    public void whenFindAll() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0", "1"});
        Store tracker = new SqlTracker();
        tracker.init();
        Item item = new Item("test");
        tracker.add(item);
        List<UserAction> actions = new ArrayList<>();
        actions.add(new FindAllAction(out));
        actions.add(new ExitAction(out));
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

    @Ignore
    @Test
    public void whenFindByName() {
        Store tracker = new SqlTracker();
        tracker.init();
        Item item = new Item("test");
        tracker.add(item);
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0", item.getName(), "1"});
        List<UserAction> actions = new ArrayList<>();
        actions.add(new FindByNameAction(out));
        actions.add(new ExitAction(out));
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

    @Ignore
    @Test
    public void whenFindById() {
        Store tracker = new SqlTracker();
        tracker.init();
        Item item = new Item("test");
        tracker.add(item);
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0", String.valueOf(item.getId()), "1"});
        List<UserAction> actions = new ArrayList<>();
        actions.add(new FindByIdAction(out));
        actions.add(new ExitAction(out));
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