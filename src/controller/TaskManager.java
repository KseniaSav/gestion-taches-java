package controller;

import model.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Manages a list of tasks, allowing displaying, adding, removing, marking as completed tasks.
 */
public class TaskManager {

    private List<Task> tasks;
    private final String FILE_NAME;

    /**
     * Initializes a task manager by loading tasks from a file if it exists,
     * otherwise create a new list.
     */
    public TaskManager(String file) {
        this.FILE_NAME = file;
        loadTasks(file);
    }

    /**
     * Adds a new task to the list and saves the changes in the file
     */
    public void addTask(String name) {
        Task task = new Task(name);
        tasks.add(task);
        saveTasksToFile();
    }

    /**
     * Removes a task, chosen by the user, and save changes in the file
     */
    public void removeTask(int index) {
        tasks.remove(index);
        saveTasksToFile();
    }

    /**
     * Marks a chosen task as completed
     */
    public void markTaskAsCompleted(int index) {
        tasks.get(index).setCompleted();
        saveTasksToFile();
    }

    /**
     * Saves the list of tasks to the file
     */
    private void saveTasksToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(tasks);
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde des taches: " + e.getMessage());
        }
    }

    /**
     * Loads a list of tasks from a file or create an empty list
     */
    private void loadTasks(String file) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            tasks = (List<Task>) in.readObject();
        } catch (ClassNotFoundException | IOException e) {
            tasks = new ArrayList<>();
        }
    }

    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }
}
