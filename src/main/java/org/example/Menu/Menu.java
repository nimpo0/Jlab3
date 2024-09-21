package org.example.Menu;

import org.example.Droids.Droid;
import org.example.Battles.Battle;
import org.example.file.WorkWithFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private List<Droid> droids = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private WorkWithFile workWithFile = new WorkWithFile();

    public void showMenu() {
        while (true) {
            System.out.println("\n\t\t\tМеню");
            System.out.println("\t\t1. Створити дроїда");
            System.out.println("\t\t2. Показати список створених дроїдів");
            System.out.println("\t\t3. Запустити бій 1 на 1");
            System.out.println("\t\t4. Запустити бій команда на команду");
            System.out.println("\t\t5. Записати проведений бій у файл");
            System.out.println("\t\t6. Відтворити проведений бій зі збереженого файлу");
            System.out.println("\t\t7. Вийти з програми");
            System.out.print("Виберіть опцію: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    createDroid();
                    break;
                case 2:
                    showDroids();
                    break;
                case 3:
                    oneOnOneBattle();
                    break;
                case 4:
                    teamBattle();
                    break;
                case 5:
                    saveBattleLog();
                    break;
                case 6:
                    loadBattleLog();
                    break;
                case 7:
                    System.out.println("Вихід з програми.");
                    return;
                default:
                    System.out.println("Неправильний вибір, спробуйте ще раз.");
            }
        }
    }

    private void createDroid() {
        DroidFactory factory = new DroidFactory();
        Droid droid = factory.createDroid();
        if (droid != null) {
            droids.add(droid);
        }
    }

    private void showDroids() {
        if (droids.isEmpty()) {
            System.out.println("Список дроїдів порожній.");
        } else {
            System.out.println("\n\t\t~~~~~Список створених дроїдів~~~~~");
            for (Droid droid : droids) {
                System.out.println(droid);
            }
        }
    }

    private void oneOnOneBattle() {
        if (droids.size() < 2) {
            System.out.println("Необхідно створити щонайменше 2 дроїда для бою 1 на 1.");
            return;
        }

        showDroids();

        System.out.print("Введіть номер першого дроїда: ");
        int index1 = scanner.nextInt() - 1;

        System.out.print("Введіть номер другого дроїда: ");
        int index2 = scanner.nextInt() - 1;

        if (index1 >= 0 && index1 < droids.size() && index2 >= 0 && index2 < droids.size() && index1 != index2) {
            workWithFile = new WorkWithFile();
            Battle.oneOnOne(droids.get(index1), droids.get(index2), workWithFile);
        } else {
            System.out.println("Неправильний вибір дроїдів.");
        }
    }

    private void teamBattle() {
        if (droids.size() < 6) {
            System.out.println("Необхідно створити щонайменше 6 дроїдів для командного бою.");
            return;
        }

        showDroids();

        System.out.println("Оберіть дроїдів для команди 1 (3 номери через пробіл): ");
        List<Droid> team1 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            int index = scanner.nextInt() - 1;
            if (index >= 0 && index < droids.size() && !team1.contains(droids.get(index))) {
                team1.add(droids.get(index));
            } else {
                System.out.println("Неправильний вибір дроїда.");
                return;
            }
        }

        System.out.println("Оберіть дроїдів для команди 2 (3 номери через пробіл): ");
        List<Droid> team2 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            int index = scanner.nextInt() - 1;
            if (index >= 0 && index < droids.size() && !team1.contains(droids.get(index)) && !team2.contains(droids.get(index))) {
                team2.add(droids.get(index));
            } else {
                System.out.println("Неправильний вибір дроїда.");
                return;
            }
        }

        workWithFile = new WorkWithFile();
        Battle.teamBattle(team1, team2, workWithFile);
    }

    private void saveBattleLog() {
        try {
            workWithFile.saveToFile();
            System.out.println("Бій збережено у файл.");
        } catch (IOException e) {
            System.out.println("Помилка під час збереження бою.");
        }
    }

    private void loadBattleLog() {
        try {
            List<String> actions = workWithFile.loadFromFile();
            System.out.println("Завантажено бій з файлу:");
            for (String action : actions) {
                System.out.println(action);
            }
        } catch (IOException e) {
            System.out.println("Помилка під час завантаження бою.");
        }
    }
}