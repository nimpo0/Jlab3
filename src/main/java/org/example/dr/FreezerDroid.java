package org.example.dr;

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

    public double getFreezeChance() {
        return freezeChance;
    }

    public void setFreezeChance(double freezeChance) {
        this.freezeChance = freezeChance;
    }

    @Override
    public void attack(Droid enemy) {
        System.out.println(this.name + " атакує " + enemy.getName() + " і завдає " + this.damage + " пошкоджень.");
        enemy.takeDamage(this.damage);

        if (canFreeze()) {
            System.out.println(this.name + " заморозив " + enemy.getName() + "!");
            enemy.freeze();
        }
    }

    private boolean canFreeze() {
        Random random = new Random();
        return random.nextDouble() < freezeChance;
    }

    @Override
    public String toString() {
        return "FreezerDroid{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", damage=" + damage +
                ", freezeChance=" + (freezeChance * 100) + "%" +
                '}';
    }
}
