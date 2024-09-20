package org.example.Battles;

import org.example.Droids.AttackerDroid;
import org.example.Droids.ChameleonDroid;
import org.example.Droids.Droid;

import java.util.List;

public class Battle {

    public static void oneOnOne(Droid droid1, Droid droid2) {
        System.out.println("Бій 1 на 1: " + droid1.getName() + " проти " + droid2.getName());

        int roundCounter = 0;

        while (droid1.getHealth() > 0 && droid2.getHealth() > 0) {
            roundCounter++;
            System.out.println("Раунд " + roundCounter);

            performAction(droid1, droid2);
            if (droid2.getHealth() > 0) {
                performAction(droid2, droid1);
            }
        }

        if (droid1.getHealth() <= 0) {
            System.out.println(droid1.getName() + " переможений!");
        } else {
            System.out.println(droid2.getName() + " переможений!");
        }
    }

    public static void teamBattle(List<Droid> team1, List<Droid> team2) {
        System.out.println("Командний бій: Команда 1 проти Команди 2");

        int roundCounter = 0;

        while (hasAliveDroids(team1) && hasAliveDroids(team2)) {
            roundCounter++;
            System.out.println("Раунд " + roundCounter);

            for (Droid droid1 : team1) {
                if (droid1.getHealth() > 0) {
                    Droid randomAliveDroid = getRandomAliveDroid(team2);
                    if (randomAliveDroid != null) {
                        performAction(droid1, randomAliveDroid);
                    }
                }
            }

            for (Droid droid2 : team2) {
                if (droid2.getHealth() > 0) {
                    Droid randomAliveDroid = getRandomAliveDroid(team1);
                    if (randomAliveDroid != null) {
                        performAction(droid2, randomAliveDroid);
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

    private static void performAction(Droid droid, Droid enemy) {
        if (droid instanceof AttackerDroid attacker) {
            if (attacker.getCharge() < attacker.getRoundsToCharge()) {
                attacker.attack(enemy);
                attacker.updateRound();
            } else {
                attacker.strongAttack(enemy);
            }
        } else if (droid instanceof ChameleonDroid chameleon) {
            if (chameleon.getRemainingCamouflage() == 0) {
                chameleon.activateCamouflage();
            } else {
                chameleon.attack(enemy);
            }

            chameleon.updateRound();
        } else {
            droid.attack(enemy);
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