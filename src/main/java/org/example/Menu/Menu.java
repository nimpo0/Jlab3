package org.example.Menu;

import org.example.Droids.Droid;
import org.example.Battles.Battle;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private List<Droid> droids = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        while (true) {
            System.out.println("1. Створити дроїда");
            System.out.println("2. Показати список створених дроїдів");
            System.out.println("3. Запустити бій 1 на 1");
            System.out.println("4. Запустити бій команда на команду");
            System.out.println("5. Записати проведений бій у файл");
            System.out.println("6. Відтворити проведений бій зі збереженого файлу");
            System.out.println("7. Вийти з програми");
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
                    System.out.println("поки що без коду");
                    break;
                case 6:
                    System.out.println("поки що без коду2");
                    break;
                case 7:
                    System.out.println("Вихід з програми.");
                    return;
                default:
                    System.out.println("Невірний вибір, спробуйте ще раз.");
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
            System.out.println("Список створених дроїдів:");
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
            Battle.oneOnOne(droids.get(index1), droids.get(index2));
        } else {
            System.out.println("Невірний вибір дроїдів. Перевірте, чи обидва дроїди різні і чи їх номери вірні.");
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
                System.out.println("Невірний вибір дроїда або дроїд вже був вибраний.");
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
                System.out.println("Невірний вибір дроїда або дроїд вже був вибраний.");
                return;
            }
        }

        Battle.teamBattle(team1, team2);
    }
}