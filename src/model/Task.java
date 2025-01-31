package model;

import java.io.Serializable;

/**
 * Represents a task with a name a completion status
 */
public class Task  implements Serializable {

    private String name;
    private boolean isCompleted;

    /**
     * Creates a new task with the given name
     * @param name The name of the task
     */
    public Task(String name){
        this.name = name;
        this.isCompleted = false;
    }

    /**
     * Returns the name of the task.
     * @return The task name
     */
    public String getName() {
        return name;
    }

    /**
     * Checks if the task is completed
     * @return true if the task is completed, false otherwise
     */
    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * Marks the task as completed.
     */
    public void markAsCompleted() {
        this.isCompleted = true;
    }

    /**
     * Returns teh status of the task as a string.
     * @return "Terminée" if completed, "En cours" otherwise.
     */
    public String getStatus(){
        return isCompleted ? "Terminée" : "En cours";
    }
}
