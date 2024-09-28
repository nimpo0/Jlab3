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

    protected void logAndPrint(String mes, WorkWithFile workWithFile) {
        System.out.println(mes);
        workWithFile.addAction(mes);
    }

    public void freeze(WorkWithFile workWithFile) {
        this.isFrozen = true;
        logAndPrint(this.name + " заморожений і не може діяти в цьому раунді!", workWithFile);
    }

    public void unfreeze(WorkWithFile workWithFile) {
        if (this.isFrozen) {
            this.isFrozen = false;
            logAndPrint(this.name + " розморожений і може діяти в наступному раунді!", workWithFile);
        }
    }

    public abstract void updateRound(WorkWithFile workWithFile);

    public void performAction(Droid enemy, WorkWithFile workWithFile) {
        attack(enemy, workWithFile);
    }

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
        return "\tДроїд{" +
                "ім'я='" + name + '\'' +
                ", здоров'я=" + health +
                ", сила=" + damage +
                ", чи заморожений=" + isFrozen +
                ')';
    }
}