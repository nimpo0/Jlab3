package org.example.Droids;

import org.example.file.WorkWithFile;

import java.util.Random;

public class BomberDroid extends Droid {

    private int strongDamage;
    private double hitChance;
    private int roundCounter;

    public BomberDroid() {
        super();
        this.strongDamage = 50;
        this.hitChance = 0.7;
        this.roundCounter = 0;
    }

    public BomberDroid(String name, int health, int damage, int strongDamage, double hitChance) {
        super(name, health, damage);
        this.strongDamage = strongDamage;
        this.hitChance = hitChance;
        this.roundCounter = 0;
    }

    public int getStrongDamage() {
        return strongDamage;
    }

    public double getHitChance() {
        return hitChance;
    }

    public void setHitChance(double hitChance) {
        this.hitChance = hitChance;
    }

    public void bombAttack(Droid target, WorkWithFile workWithFile) {
        if (this.isFrozen) {
            logAndPrint(this.name + " не може кинути сильну бомбу, оскільки заморожений!", workWithFile);
            return;
        }

        Random random = new Random();
        if (random.nextDouble() <= hitChance) {
            logAndPrint(this.name + " кидає сильну бомбу по " + target.getName() + " і завдає " + strongDamage + " пошкоджень!", workWithFile);
            target.takeDamage(strongDamage, workWithFile);
        } else {
            logAndPrint(this.name + " промахнувся!", workWithFile);
        }
    }

    @Override
    public void updateRound(WorkWithFile workWithFile) {
        roundCounter++;
    }

    public boolean shouldUseStrongAttack() {
        return roundCounter % 3 == 0;
    }

    public void resetRoundCounter() {
        roundCounter = 0;
    }

    @Override
    public String toString() {
        return "\tБомбардувальник (" +
                "ім'я='" + name + '\'' +
                ", здоров'я=" + health +
                ", сила=" + damage +
                ", сильна атака=" + strongDamage +
                ", шанс сильної атаки=" + hitChance +
                ')';
    }
}
