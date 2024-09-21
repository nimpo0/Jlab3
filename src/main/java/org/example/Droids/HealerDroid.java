package org.example.Droids;

import org.example.file.WorkWithFile;

public class HealerDroid extends Droid {

    private int healing;

    public HealerDroid() {
        super();
        this.healing = 20;
    }

    public HealerDroid(String name, int health, int damage, int healing) {
        super(name, health, damage);
        this.healing = healing;
    }

    public int getHealing() {
        return healing;
    }

    public void setHealing(int healing) {
        this.healing = healing;
    }

    @Override
    public void updateRound(WorkWithFile workWithFile) {

    }

    public void heal(WorkWithFile workWithFile) {
        setHealth(this.health + healing);
        String healMessage = this.name + " зцілив себе на " + healing + " одиниць. Здоров'я тепер: " + this.health;
        logAndPrint(healMessage, workWithFile);
    }

    public void heal(Droid droid, WorkWithFile workWithFile) {
        droid.setHealth(droid.getHealth() + healing);
        String healMessage = this.name + " зцілив " + droid.getName() + " на " + healing + " одиниць. Здоров'я " + droid.getName() + ": " + droid.getHealth();
        logAndPrint(healMessage, workWithFile);
    }

    @Override
    public void attack(Droid enemy, WorkWithFile workWithFile) {
        String attackMessage = this.name + " атакує " + enemy.getName() + " і завдає " + this.damage + " пошкоджень.";
        logAndPrint(attackMessage, workWithFile);
        enemy.takeDamage(this.damage, workWithFile);
        heal(workWithFile);
    }

    @Override
    public String toString() {
        return "HealerDroid{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", damage=" + damage +
                ", healing=" + healing +
                '}';
    }
}
