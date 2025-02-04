package view;

import controller.TaskManager;
import model.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TaskManagerGUI {
    private TaskManager taskManager;
    private JFrame frame;
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JButton addButton, removeButton, completeButton;
    private static final String FILE_NAME = "tasks.dat";

    public TaskManagerGUI(TaskManager taskManager) {
        this.taskManager = taskManager;
        createUI();
    }

    private void createUI() {
        frame = new JFrame("ToDo List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Task list
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        frame.add(new JScrollPane(taskList), BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Add Task");
        removeButton = new JButton("Remove Task");
        completeButton = new JButton("Mark as Completed");

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(completeButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Load existing tasks
        loadTasks();

        // Button actions
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String taskName = JOptionPane.showInputDialog("Enter task name:");
                if (taskName != null && !taskName.trim().isEmpty()) {
                    taskManager.addTask(taskName);
                    loadTasks();
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = taskList.getSelectedIndex();
                if (index != -1) {
                    taskManager.removeTask(index);
                    loadTasks();
                }
            }
        });

        completeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = taskList.getSelectedIndex();
                if (index != -1) {
                    taskManager.markTaskAsCompleted(index);
                    loadTasks();
                }
            }
        });

        frame.setVisible(true);
    }

    private void loadTasks() {
        taskListModel.clear();
        List<Task> tasks = taskManager.getTasks();
        for (Task task : tasks) {
            taskListModel.addElement(task.getName() + "  " + (task.getStatus()));
        }
    }

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager(FILE_NAME);
        new TaskManagerGUI(taskManager);
    }
}

