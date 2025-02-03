package test;

import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    private Task task;

    @BeforeEach
    void setUp() {
        task = new Task("Test Task");
    }

    @Test
    void getName() {
        assertEquals("Test Task", task.getName());
    }

    @Test
    void isCompleted() {
        assertFalse(task.isCompleted());
        task.setCompleted();
        assertTrue(task.isCompleted());
    }

    @Test
    void setCompleted() {
        task.setCompleted();
        assertTrue(task.isCompleted());
    }

    @Test
    void getStatus() {
        assertEquals("En cours", task.getStatus());
        task.setCompleted();
        assertEquals("Terminée", task.getStatus());
    }
}
