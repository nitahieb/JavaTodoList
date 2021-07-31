package model;

import exceptions.InvalidTitleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoListTest {
    private ToDoList toDolist;
    private Task firstTask;
    private Task secondTask;
    private Task thirdTask;
    private Task fourthTask;
    private ArrayList<Task> list;

    @BeforeEach
    void runBefore() {
        try {
            firstTask = new Task("Math homework", "Integral practice", 20200220);
            secondTask = new Task("English homework", "read Harry Potter", 20200301);
            thirdTask = new Task("Math homework", "Algebra practice", 20300220);
            fourthTask = new Task("Wash dishes", "", 20200203);
        } catch (InvalidTitleException i) {
            System.out.println("setup is wrong");
        }
        toDolist = new ToDoList();
        list = new ArrayList<>();
    }

    @Test
    void testConstructor() {
        assertEquals(toDolist.toDoListLength(), 0);
    }

    @Test
    void testAddTask() {
        toDolist.addTask(firstTask);
        assertTrue(toDolist.containsInToDo(firstTask));
        toDolist.addTask(thirdTask);
        assertEquals(toDolist.toDoListLength(), 1);
        assertTrue(toDolist.containsInToDo(firstTask));
        assertEquals(toDolist.toDoListLength(), 1);
        toDolist.addTask(secondTask);
        assertTrue(toDolist.containsInToDo(secondTask));
    }

    @Test
    void testRemoveTask() {
        toDolist.addTask(firstTask);
        toDolist.removeTask(secondTask.getName());
        assertEquals(1, toDolist.toDoListLength());
        assertTrue(toDolist.containsInToDo(firstTask));
        toDolist.removeTask(firstTask.getName());
        assertEquals(0, toDolist.toDoListLength());
        assertFalse(toDolist.containsInToDo(firstTask));
    }

    @Test
    void testRemoveCompletedTasks() {
        toDolist.addTask(firstTask);
        toDolist.addTask(secondTask);
        toDolist.addTask(fourthTask);
        toDolist.removeCompletedTasks();
        assertEquals(3, toDolist.toDoListLength());
        firstTask.setComplete();
        toDolist.removeCompletedTasks();
        assertEquals(toDolist.toDoListLength(), 2);
        secondTask.setComplete();
        fourthTask.setComplete();
        toDolist.removeCompletedTasks();
        assertEquals(toDolist.toDoListLength(), 0);
    }

    @Test
    void testSelectClass() {
        assertNull(toDolist.selectTask("Homework"));
        toDolist.addTask(firstTask);
        assertEquals(toDolist.selectTask("Math homework"), firstTask);
        toDolist.addTask(secondTask);
        toDolist.addTask(fourthTask);
        assertEquals(toDolist.selectTask("Math homework"), firstTask);
        assertEquals(toDolist.selectTask("English homework"), secondTask);
        assertNull(toDolist.selectTask("Physics Homework"));
        assertEquals(toDolist.selectTask("Wash dishes"), fourthTask);

    }

    @Test
    void testgetQueue() {
        assertEquals(list, toDolist.getQueue());
        toDolist.addTask(firstTask);
        toDolist.addTask(secondTask);
        assertNotEquals(list, toDolist.getQueue());
        list.add(firstTask);
        list.add(secondTask);
        assertEquals(list, toDolist.getQueue());

    }

    @Test
    void testcontainsInToDo() {
        assertFalse(toDolist.containsInToDo(firstTask));
        toDolist.addTask(firstTask);
        toDolist.addTask(secondTask);
        assertTrue(toDolist.containsInToDo(firstTask));
        assertFalse(toDolist.containsInToDo(fourthTask));
        assertTrue(toDolist.containsInToDo(firstTask));
        assertTrue(toDolist.containsInToDo(secondTask));

    }

    @Test
    void testAddTasks() {
        toDolist.addTask(firstTask);
        toDolist.addTask(secondTask);
        toDolist.addTasks(list);
        assertEquals(2, toDolist.toDoListLength());
        list.add(thirdTask);
        list.add(fourthTask);
        toDolist.addTasks(list);
        assertEquals(4, toDolist.toDoListLength());

    }

    @Test
    void testGetTask() {
        toDolist.addTask(firstTask);
        toDolist.addTask(secondTask);
        assertEquals(toDolist.getTask(0), firstTask);
        try {
            toDolist.getTask(5);
            fail();
        } catch (IndexOutOfBoundsException ignored) {
        }
    }

    @Test
    void testRemoveTaskI() {
        toDolist.addTask(firstTask);
        toDolist.addTask(secondTask);
        toDolist.removeTask(0);
        assertEquals(toDolist.toDoListLength(), 1);
        try {
            toDolist.removeTask(5);
            fail();
        } catch (IndexOutOfBoundsException ignored) {
        }
    }

    @Test
    void testSelectTaskError() {
        assertNull(toDolist.selectTask(""));
    }

}
