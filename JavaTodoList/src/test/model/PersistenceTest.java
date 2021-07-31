package model;

import exceptions.InvalidTitleException;
import model.persistence.Persistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PersistenceTest {
    private Task firstTask;
    private Task secondTask;

    private Persistence persistence;
    private Persistence emptyPersistence;
    private Persistence invalidPersistence;
    private ArrayList<Task> list;


    @BeforeEach
    void runBefore() {
        try {
            firstTask = new Task("Math homework", "Integral practice", 20200220);
            secondTask = new Task("English homework", "read Harry Potter", 20200301);
        } catch (InvalidTitleException i) {
            System.out.println("Setup is wrong");
        }
        list = new ArrayList<>();
        String fileLocation = "JavaTodoList/data/ToDoListTest.json";
        persistence = new Persistence(fileLocation);
        String fileLocation2 = "JavaTodoList/data/ToDoListTest2.json";
        emptyPersistence = new Persistence(fileLocation2);
        String invalidFileLocation = "/this/is/notreal/?.json";
        invalidPersistence = new Persistence(invalidFileLocation);

    }

    @Test
    void testSaveLoadAllWithTasks() {
        list.add(firstTask);
        list.add(secondTask);
        persistence.saveAll(list);
        assertEquals(list, persistence.loadAll());
    }


    @Test
    void testLoadAllEmptyList() {
        assertEquals(emptyPersistence.loadAll().size(), 0);
    }

    @Test
    void testSaveAllError() {
        assertEquals(invalidPersistence.loadAll().size(), 0);
        invalidPersistence.saveAll(list);
    }


}
