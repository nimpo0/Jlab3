package org.example.Droids;

import org.example.file.WorkWithFile;

import java.util.Random;

public class FreezerDroid extends Droid {

    private double freezeChance;

    public FreezerDroid() {
        super();
        this.freezeChance = 0.25;
    }

    public FreezerDroid(String name, int health, int damage, double freezeChance) {
        super(name, health, damage);
        this.freezeChance = freezeChance;
    }

    @Override
    public void updateRound(WorkWithFile workWithFile) {
    }

    @Override
    public void attack(Droid enemy, WorkWithFile workWithFile) {
        String attackMessage = this.name + " атакує " + enemy.getName() + " і завдає " + this.damage + " пошкоджень.";
        logAndPrint(attackMessage, workWithFile);
        enemy.takeDamage(this.damage, workWithFile);

        if (canFreeze()) {
            String freezeMessage = this.name + " заморозив " + enemy.getName() + "!";
            logAndPrint(freezeMessage, workWithFile);
            enemy.freeze(workWithFile);
        }
    }

    private boolean canFreeze() {
        Random random = new Random();
        return random.nextDouble() < freezeChance;
    }

    @Override
    public String toString() {
        return "\tЗаморожувавач (" +
                "ім'я='" + name + '\'' +
                ", здоров'я=" + health +
                ", сила=" + damage +
                ", шанс заморожування=" + (freezeChance * 100) + "%" +
                ')';
    }
}
