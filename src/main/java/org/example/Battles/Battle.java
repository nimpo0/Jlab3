package org.example.Battles;

import org.example.Droids.Droid; // Змініть на правильний пакет

import java.util.List;

public class Battle {

    // Метод для бою 1 на 1
    public static void oneOnOne(Droid droid1, Droid droid2) {
        System.out.println("Бій 1 на 1: " + droid1.getName() + " проти " + droid2.getName());

        while (droid1.getHealth() > 0 && droid2.getHealth() > 0) {
            droid1.attack(droid2);
            if (droid2.getHealth() > 0) {
                droid2.attack(droid1);
            }
        }

        if (droid1.getHealth() <= 0) {
            System.out.println(droid1.getName() + " переможений!");
        } else {
            System.out.println(droid2.getName() + " переможений!");
        }
    }

    // Метод для командного бою
    public static void teamBattle(List<Droid> team1, List<Droid> team2) {
        System.out.println("Командний бій: Команда 1 проти Команди 2");

        while (hasAliveDroids(team1) && hasAliveDroids(team2)) {
            for (Droid droid1 : team1) {
                if (droid1.getHealth() > 0) {
                    Droid target = getRandomAliveDroid(team2);
                    if (target != null) {
                        droid1.attack(target);
                    }
                }
            }

            for (Droid droid2 : team2) {
                if (droid2.getHealth() > 0) {
                    Droid target = getRandomAliveDroid(team1);
                    if (target != null) {
                        droid2.attack(target);
                    }
                }
            }
        }

        if (hasAliveDroids(team1)) {
            System.out.println("Команда 1 перемогла!");
        } else {
            System.out.println("Команда 2 перемогла!");
        }
    }

    private static boolean hasAliveDroids(List<Droid> team) {
        for (Droid droid : team) {
            if (droid.getHealth() > 0) {
                return true;
            }
        }
        return false;
    }

    private static Droid getRandomAliveDroid(List<Droid> team) {
        for (Droid droid : team) {
            if (droid.getHealth() > 0) {
                return droid; // Повертаємо першого живого дроїда
            }
        }
        return null; // Якщо живих дроїдів немає
    }
}

