package org.example.Battles;

import org.example.Droids.AttackerDroid;
import org.example.Droids.ChameleonDroid;
import org.example.Droids.BomberDroid;
import org.example.Droids.Droid;
import org.example.file.WorkWithFile;

import java.util.List;

public class Battle {

    public static void oneOnOne(Droid droid1, Droid droid2, WorkWithFile workWithFile) {
        String battleInfo = "Бій 1 на 1: " + droid1.getName() + " проти " + droid2.getName();
        printAndLogResult(battleInfo, workWithFile);

        int roundCounter = 0;

        while (droid1.getHealth() > 0 && droid2.getHealth() > 0) {
            roundCounter++;
            String roundInfo = "Раунд " + roundCounter;
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
        String battleInfo = "Командний бій: Команда 1 проти Команди 2";
        printAndLogResult(battleInfo, workWithFile);

        int roundCounter = 0;

        while (hasAliveDroids(team1) && hasAliveDroids(team2)) {
            roundCounter++;
            String roundInfo = "Раунд " + roundCounter;
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
            printAndLogResult("Команда 1 перемогла!", workWithFile);
        } else {
            printAndLogResult("Команда 2 перемогла!", workWithFile);
        }
    }

    private static void printAndLogResult(String result, WorkWithFile workWithFile) {
        System.out.println(result);
        workWithFile.addAction(result);
    }

    private static void performAction(Droid droid, Droid enemy, WorkWithFile workWithFile) {
        if (droid instanceof AttackerDroid attacker) {
            if (attacker.getCharge() < attacker.getRoundsToCharge()) {
                attacker.attack(enemy, workWithFile);
                attacker.updateRound(workWithFile);
            } else {
                attacker.strongAttack(enemy, workWithFile);
            }
        } else if (droid instanceof ChameleonDroid chameleon) {
            if (chameleon.getRemainingCamouflage() == 0) {
                chameleon.activateCamouflage(workWithFile);
            }
            chameleon.attack(enemy, workWithFile);
            chameleon.updateRound(workWithFile);
        } else if (droid instanceof BomberDroid bomber) {
            if (bomber.shouldUseStrongAttack()) {
                bomber.strongAttack(enemy, workWithFile);
                bomber.resetRoundCounter();
            } else {
                bomber.attack(enemy, workWithFile);
            }
            bomber.updateRound(workWithFile);
        } else {
            droid.attack(enemy, workWithFile);
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
                return droid;
            }
        }
        return null;
    }
}
