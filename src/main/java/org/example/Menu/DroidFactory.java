package org.example.Menu;

import org.example.Droids.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DroidFactory {
    private List<Droid> droids = new ArrayList<>();

    public Droid createDroid() {
        Scanner scanner = new Scanner(System.in);
        String name;

        System.out.println("Введіть ім'я дроїда:");
        name = scanner.nextLine();

        System.out.println("Оберіть тип дроїда:");
        System.out.println("1. AttackerDroid");
        System.out.println("2. BomberDroid");
        System.out.println("3. ChameleonDroid");
        System.out.println("4. FreezerDroid");
        System.out.println("5. HealerDroid");

        int droidType = scanner.nextInt();
        int health, damage;

        while (true) {
            System.out.print("Введіть здоров'я дроїда (1-100): ");
            health = scanner.nextInt();
            if (health >= 1 && health <= 100) break;
            System.out.println("Значення здоров'я має бути в діапазоні 1-100.");
        }

        while (true) {
            System.out.print("Введіть атаку дроїда (1-50): ");
            damage = scanner.nextInt();
            if (damage >= 1 && damage <= 50) break;
            System.out.println("Значення атаки має бути в діапазоні 1-50.");
        }

        Droid newDroid = null;

        switch (droidType) {
            case 1:
                int roundsToCharge;
                while (true) {
                    System.out.print("Введіть кількість раундів для зарядки (1-5): ");
                    roundsToCharge = scanner.nextInt();
                    if (roundsToCharge >= 1 && roundsToCharge <= 5) break;
                    System.out.println("Значення має бути в діапазоні 1-5.");
                }
                newDroid = new AttackerDroid(name, health, damage, roundsToCharge);
                break;

            case 2:
                int strongDamage;
                while (true) {
                    System.out.print("Введіть силу сильної атаки (1-50): ");
                    strongDamage = scanner.nextInt();
                    if (strongDamage >= 1 && strongDamage <= 50) break;
                    System.out.println("Значення має бути в діапазоні 1-50.");
                }
                double hitChance;
                while (true) {
                    System.out.print("Введіть ймовірність попадання (0.0-1.0): ");
                    hitChance = scanner.nextDouble();
                    if (hitChance >= 0.0 && hitChance <= 1.0) break;
                    System.out.println("Значення має бути в діапазоні 0.0-1.0.");
                }
                newDroid = new BomberDroid(name, health, damage, strongDamage, hitChance);
                break;

            case 3:
                int camouflageDuration, counterAttackDamage;
                while (true) {
                    System.out.print("Введіть тривалість камуфляжу (1-3): ");
                    camouflageDuration = scanner.nextInt();
                    if (camouflageDuration >= 1 && camouflageDuration <= 3) break;
                    System.out.println("Значення має бути в діапазоні 1-3.");
                }
                while (true) {
                    System.out.print("Введіть ушкодження контратаки (1-15): ");
                    counterAttackDamage = scanner.nextInt();
                    if (counterAttackDamage >= 1 && counterAttackDamage <= 15) break;
                    System.out.println("Значення має бути в діапазоні 1-15.");
                }
                newDroid = new ChameleonDroid(name, health, damage, camouflageDuration);
                break;

            case 4:
                double freezeChance;
                while (true) {
                    System.out.print("Введіть ймовірність заморожування (0.0-1.0): ");
                    freezeChance = scanner.nextDouble();
                    if (freezeChance >= 0.0 && freezeChance <= 1.0) break;
                    System.out.println("Значення має бути в діапазоні 0.0-1.0.");
                }
                newDroid = new FreezerDroid(name, health, damage, freezeChance);
                break;

            case 5:
                int healing;
                while (true) {
                    System.out.print("Введіть значення зцілення (1-30): ");
                    healing = scanner.nextInt();
                    if (healing >= 1 && healing <= 30) break;
                    System.out.println("Значення має бути в діапазоні 1-30.");
                }
                newDroid = new HealerDroid(name, health, damage, healing);
                break;

            default:
                System.out.println("Невірний тип дроїда.");
                break;
        }

        if (newDroid != null) {
            droids.add(newDroid);
            System.out.println("Дроїд " + name + " успішно створений!");
        }

        return newDroid;
    }
}
