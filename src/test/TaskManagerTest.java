package test;

import controller.TaskManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;

class TaskManagerTest {

    private TaskManager taskManager;
    private final String TEST_FILE = "test_tasks.dat";

    @BeforeEach
    void setUp() {
        taskManager = new TaskManager(TEST_FILE);
    }

    @AfterEach
    void tearDown() {
        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    /*
    @Test
    void testAddTask() {
        taskManager.addTask("Test Task");
        List<Task> tasks = taskManager.getTasks();
        assertEquals(1, tasks.size());
        assertEquals("Test Task", tasks.get(0).getName());
    }

    @Test
    void testRemoveTask() {
        taskManager.addTask("Task to remove");
        taskManager.removeTask(1);
        assertTrue(taskManager.getTasks().isEmpty());
    }

    @Test
    void testMarkTaskAsCompleted() {
        taskManager.addTask("Incomplete Task");
        taskManager.markTaskAsCompleted(1);
        assertTrue(taskManager.getTasks().get(0).isCompleted());
    }

    @Test
    void testSaveAndLoadTasks() {
        taskManager.addTask("Persistent Task");
        taskManager.saveTasksToFile();
        TaskManager newTaskManager = new TaskManager(testFile);
        assertEquals(1, newTaskManager.getTasks().size());
        assertEquals("Persistent Task", newTaskManager.getTasks().get(0).getName());
    }
    */
}
