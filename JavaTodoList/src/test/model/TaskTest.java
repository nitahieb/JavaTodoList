package model;

import exceptions.InvalidTitleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    private Task firstTask;
    private Task secondTask;
    private Task thirdTask;
    private Task fourthTask;
    private Task fifthTask;

    @BeforeEach
    void runBefore() {
        try {
            firstTask = new Task("Math homework", "Integral practice", 20200220);
            secondTask = new Task("Math homework", "Derivative practice", 20200220);
            thirdTask = new Task("Science homework", "Derivative practice", 20200220);
            fourthTask = new Task("m", null, 0);
            fifthTask = new Task("m", "this is not a task", 110);
        } catch (InvalidTitleException i) {
            System.out.println("setup is wrong");
        }
    }

    @Test
    void testConstructor() {
        assertEquals(firstTask.getName(), "Math homework");
        assertEquals(firstTask.getDescription(), "Integral practice");
        assertEquals(firstTask.getDueDate(), 20200220);
    }

    @SuppressWarnings("unused")
    @Test
    void testExceptionThrownConstructor() {
        try {Task invalidTask = new Task("", "", 0);
            fail("Exception should have been thrown");
        } catch (InvalidTitleException ignored) {

        }
    }

    @Test
    void testChangeName() {
        try {
            firstTask.changeName("Calculus homework");
            assertEquals(firstTask.getName(), "Calculus homework");
            firstTask.changeName("Calculus 101 Homework");
            assertEquals(firstTask.getName(), "Calculus 101 Homework");
        } catch (InvalidTitleException i) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    void testExceptionThrownChangeName() {
        try {
            firstTask.changeName("");
            fail("Exception should have been thrown");
        } catch (InvalidTitleException ignored) {

        }

    }

    @Test
    void testChangeDescription() {
        firstTask.changeDescription("");
        assertEquals(firstTask.getDescription(), "");
        firstTask.changeDescription("Integral practice pg. 100 - 110");
        assertEquals(firstTask.getDescription(), "Integral practice pg. 100 - 110");
    }

    @Test
    void testChangeDueDate() {
        firstTask.changeDueDate(99991231);
        assertEquals(firstTask.getDueDate(), 99991231);
    }

    @Test
    void testSetComplete() {
        assertFalse(firstTask.getComplete());
        firstTask.setComplete();
        assertTrue(firstTask.getComplete());
    }

    @Test
    void testToString() {
        assertEquals(firstTask.toString(), "Math homework Integral practice that is due on 20200220. And is it complete? false");
    }

    @Test
    void testEquals() {
        assertEquals(secondTask, firstTask);
        assertEquals(firstTask, secondTask);
        assertNotEquals(firstTask, thirdTask);
        assertNotEquals(thirdTask, firstTask);
        assertNotEquals(thirdTask, fourthTask);
        assertNotEquals(null, thirdTask);
        assertEquals(fourthTask, fifthTask);


    }
}
