package model;

import exceptions.InvalidTitleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ToDoListPersistenceTest {
    private ToDoList toDolist;
    private Task firstTask;
    private Task secondTask;
    private Task thirdTask;
    private Task fourthTask;
    private ToDoList emptyTodolist;
    private ToDoList emptyTodolist2;


    @BeforeEach
    void runBefore() {
        try {
            firstTask = new Task("Math homework", "Integral practice", 20200220);
            secondTask = new Task("English homework", "read Harry Potter", 20200301);
            thirdTask = new Task("physics homework", "kinematics practice", 20300220);
            fourthTask = new Task("Wash dishes", "", 20200203);
        } catch (InvalidTitleException i) {
            System.out.println("Setup is wrong");
        }
        toDolist = new ToDoList();
        emptyTodolist = new ToDoList();
        emptyTodolist2 = new ToDoList();
        toDolist.addTask(firstTask);
        toDolist.addTask(secondTask);
        toDolist.addTask(thirdTask);

    }

    @Test
    void testSaveLoadempty(){
        String fileLocation2 = "JavaTodoList/data/ToDoListTest2.json";
        emptyTodolist2.loadAll(fileLocation2);
        assertEquals(0, emptyTodolist2.toDoListLength());

    }

    @Test
    void testSaveLoadAllWithTasks(){
        String fileLocation3 = "JavaTodoList/data/ToDoListTest3.json";
        toDolist.saveAll(fileLocation3);
        emptyTodolist.loadAll(fileLocation3);
        assertEquals(emptyTodolist.toDoListLength(), 3);
        assertTrue(emptyTodolist.containsInToDo(firstTask));
        assertTrue(emptyTodolist.containsInToDo(secondTask));
        assertTrue(emptyTodolist.containsInToDo(thirdTask));
        assertFalse(emptyTodolist.containsInToDo(fourthTask));
        emptyTodolist.removeTask(firstTask.getName());
        emptyTodolist.removeTask(secondTask.getName());
        emptyTodolist.removeTask(thirdTask.getName());
    }

    @Test
    void testLoadError(){
        String invalidFileLocation = "/this/is/notreal/?.json";
        emptyTodolist.loadAll(invalidFileLocation);
        assertEquals(0, emptyTodolist.toDoListLength());
    }





}
