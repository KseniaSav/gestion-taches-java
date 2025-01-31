import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TaskManager {

    private List<Task> tasks;
    private static final String FILE_NAME = "tasks.dat";
    private Scanner scanner;

    public TaskManager() {
        loadTasks();
    }

    public void start() {
        System.out.println("Bienvenue dans le programme de gestion des taches !");
        scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\t Menue:");
            System.out.println("1. Afficher la liste des taches");
            System.out.println("2. Ajouter une tache");
            System.out.println("3. Supprimer une tache");
            System.out.println("4. Marquer une tache comme terminée");
            System.out.println("5. Quitter le programme");
            System.out.print("Faites votre choix: ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException ex) {
                System.out.println("Veillez entrez un numéro valide");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    displayTasks();
                    break;
                case 2:
                    addTask();
                    break;
                case 3:
                    removeTask();
                    break;
                case 4:
                    markTaskAsCompleted();
                    break;
                case 5:
                    System.out.println("Au revoir !");
                    return;
                default:
                    System.out.println("Option invalide: ");
                    break;
            }
        }
    }

    private void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Votre liste de taches est vide");
        } else {
            System.out.println("Voici la liste des taches:");
            Task task;
            for (int i = 0; i < tasks.size(); i++) {
                task = tasks.get(i);
                System.out.println(i + 1 + ". " + task.getName() + " - " + task.getStatus());
            }
        }
    }

    private void addTask() {
        System.out.print("Ecrivez la nouvelle tache: ");
        String s = scanner.nextLine();
        Task task = new Task(s);
        tasks.add(task);
        saveTasksToFile();
        System.out.println("Une nouvelle tache ajoutée");
    }

    private void removeTask() {
        displayTasks();
        System.out.print("Entrez le numéro de la tache à supprimer: ");
        int num;
        try {
            num = scanner.nextInt();
            scanner.nextLine();
            if (num > 0 && num <= tasks.size()) {
                tasks.remove(num - 1);
                saveTasksToFile();
                System.out.println("La tache est supprimée");
            } else {
                System.out.println("Numéro invalide");
            }
        } catch (InputMismatchException ex) {
            System.out.println("Veillez entrez un numéro valide");
            scanner.nextLine();
        }
    }

    private void saveTasksToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(tasks);
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde des taches: " + e.getMessage());
        }
    }

    private void loadTasks() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            tasks = (List<Task>) in.readObject();
        } catch (ClassNotFoundException | IOException e) {
            tasks = new ArrayList<>();
        }
    }

    private void markTaskAsCompleted() {
        displayTasks();
        System.out.print("Entrez le numéro de la tache terminée: ");
        int choice;
        try {
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice > 0 && choice <= tasks.size()) {
                tasks.get(choice - 1).markAsCompleted();
                saveTasksToFile();
                System.out.println("La tache est marquée comme terminée");
            } else {
                System.out.println("Numéro invalide");
            }
        } catch (InputMismatchException ex) {
            System.out.println("Veillez entrez un numéro valide");
            scanner.nextLine();
        }
    }

    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        manager.start();
    }
}
