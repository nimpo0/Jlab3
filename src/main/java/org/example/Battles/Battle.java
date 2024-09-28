package org.example.Battles;

import org.example.Droids.Droid;
import org.example.file.WorkWithFile;

import java.util.List;

public class Battle {

    public static void oneOnOne(Droid droid1, Droid droid2, WorkWithFile workWithFile) {
        String battleInfo = "\n\t\t~~~~~Бій 1 на 1~~~~~ " + droid1.getName() + " проти " + droid2.getName();
        printAndLogResult(battleInfo, workWithFile);

        int roundCounter = 0;

        while (droid1.getHealth() > 0 && droid2.getHealth() > 0) {
            roundCounter++;
            String roundInfo = "\n\tРаунд " + roundCounter;
            printAndLogResult(roundInfo, workWithFile);

            performAction(droid1, droid2, workWithFile);
            if (droid2.getHealth() > 0) {
                performAction(droid2, droid1, workWithFile);
            }
        }

        if (droid1.getHealth() <= 0) {
            printAndLogResult(droid1.getName() + " переможений!", workWithFile);
        } else {
            printAndLogResult(droid2.getName() + " переможений!", workWithFile);
        }
    }

    public static void teamBattle(List<Droid> team1, List<Droid> team2, WorkWithFile workWithFile) {
        String battleInfo = "\n\t\t~~~~~Командний бій~~~~~";
        printAndLogResult(battleInfo, workWithFile);

        int roundCounter = 0;

        while (hasAliveDroids(team1) && hasAliveDroids(team2)) {
            roundCounter++;
            String roundInfo = "\n\tРаунд " + roundCounter;
            printAndLogResult(roundInfo, workWithFile);

            for (Droid droid1 : team1) {
                if (droid1.getHealth() > 0) {
                    Droid randomAliveDroid = getRandomAliveDroid(team2);
                    if (randomAliveDroid != null) {
                        performAction(droid1, randomAliveDroid, workWithFile);
                    }
                }
            }

            for (Droid droid2 : team2) {
                if (droid2.getHealth() > 0) {
                    Droid randomAliveDroid = getRandomAliveDroid(team1);
                    if (randomAliveDroid != null) {
                        performAction(droid2, randomAliveDroid, workWithFile);
                    }
                }
            }
        }

        if (hasAliveDroids(team1)) {
            printAndLogResult("\n\tКоманда 1 перемогла!", workWithFile);
        } else {
            printAndLogResult("\n\tКоманда 2 перемогла!", workWithFile);
        }
    }

    private static void printAndLogResult(String result, WorkWithFile workWithFile) {
        System.out.println(result);
        workWithFile.addAction(result);
    }

    private static void performAction(Droid droid, Droid enemy, WorkWithFile workWithFile) {
        droid.performAction(enemy, workWithFile);
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
                return droid;
            }
        }
        return null;
    }
}