import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Bienvenue dans le programme de gestion des taches !");
        List<String> tasks = readTasksFromFile();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\t Menue:");
            System.out.println("1. Ajouter une tache");
            System.out.println("2. Afficher la liste des taches");
            System.out.println("3. Quitter le programme");
            System.out.println("4. Supprimer une tache");
            ;
            System.out.print("Faites votre choix: ");

            int n;
            try {
                n = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException ex) {
                System.out.println("Veillez entrez un numéro valide");
                scanner.nextLine();
                continue;
            }

            switch (n) {
                case 1:
                    System.out.print("Ecrivez la nouvelle tache: ");
                    String s = scanner.nextLine();
                    tasks.add(s);
                    saveTasksToFile(tasks);
                    System.out.println("Une nouvelle tache ajoutée");
                    break;
                case 2:
                    if (tasks.isEmpty()) {
                        System.out.println("Votre liste de taches est vide");
                    } else {
                        System.out.println("Voici la liste des taches:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println(i + 1 + ". " + tasks.get(i));
                        }
                    }
                    break;
                case 3:
                    System.out.println("Au revoir !");
                    return;
                case 4:
                    if (tasks.isEmpty()) {
                        System.out.println("Votre liste de taches est vide");
                    } else {
                        System.out.println("Voici la liste des taches:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println(i + 1 + ". " + tasks.get(i));
                        }
                    }
                    System.out.print("Entrez le numéro de la tache à supprimer: ");
                    int num;
                    try {
                        num = scanner.nextInt();
                        scanner.nextLine();
                        if (num > 0 && num <= tasks.size()) {
                            tasks.remove(num - 1);
                            saveTasksToFile(tasks);
                            System.out.println("La tache est supprimée");
                        } else {
                            System.out.println("Numéro invalide");
                        }
                    } catch (InputMismatchException ex) {
                        System.out.println("Veillez entrez un numéro valide");
                        scanner.nextLine();
                        continue;
                    }

                    break;
                default:
                    System.out.println("Option invalide: ");
                    break;
            }
        }
    }

    private static void saveTasksToFile(List<String> list) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("tasks.txt"))){
            for(String s: list) {
                writer.println(s);
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde des taches: " + e.getMessage());
        }
    }

    private static List<String> readTasksFromFile() {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("tasks.txt"))){
            String line;
            while((line = reader.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture des taches: " + e.getMessage());
        }
        return list;
    }
}
