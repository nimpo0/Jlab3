package org.example.Droids;

import org.example.file.WorkWithFile;

public abstract class Droid {

    protected String name;
    protected int health;
    protected int damage;
    protected boolean isFrozen;
    protected int maxHealth;

    public Droid() {
        this.isFrozen = false;
        this.maxHealth = 100;
    }

    public Droid(String name, int health, int damage) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.isFrozen = false;
        this.maxHealth = health;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public void setHealth(int health) {
        if (health > maxHealth) {
            this.health = maxHealth;
        } else if (health < 0) {
            this.health = 0;
        } else {
            this.health = health;
        }
    }

    public boolean isFrozen() {
        return isFrozen;
    }

    protected void logAndPrint(String message, WorkWithFile workWithFile) {
        System.out.println(message);
        workWithFile.addAction(message);
    }

    public void freeze(WorkWithFile workWithFile) {
        this.isFrozen = true;
        logAndPrint(this.name + " заморожений і не може діяти в цьому раунді!", workWithFile);
    }

    public void unfreeze(WorkWithFile workWithFile) {
        this.isFrozen = false;
        logAndPrint(this.name + " розморозився і може знову діяти.", workWithFile);
    }

    public abstract void updateRound(WorkWithFile workWithFile);

    public void takeDamage(int damage, WorkWithFile workWithFile) {
        this.setHealth(this.health - damage);
        logAndPrint(this.name + " отримав " + damage + " пошкоджень. Залишилось здоров'я: " + this.health, workWithFile);
    }

    public void attack(Droid enemy, WorkWithFile workWithFile) {
        if (this.isFrozen) {
            logAndPrint(this.name + " не може атакувати, оскільки заморожений!", workWithFile);
            return;
        }

        logAndPrint(this.name + " атакує " + enemy.getName() + " і завдає " + this.damage + " пошкоджень.", workWithFile);
        enemy.takeDamage(this.damage, workWithFile);
    }

    @Override
    public String toString() {
        return "Droid{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", damage=" + damage +
                ", isFrozen=" + isFrozen +
                '}';
    }
}
