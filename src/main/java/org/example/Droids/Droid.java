package org.example.Droids;

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

    public void freeze() {
        this.isFrozen = true;
        System.out.println(this.name + " заморожений і не може діяти в цьому раунді!");
    }

    public void unfreeze() {
        this.isFrozen = false;
        System.out.println(this.name + " розморозився і може знову діяти.");
    }

    public void updateRound(){

    }

    public void takeDamage(int damage) {
        this.setHealth(this.health - damage);
        System.out.println(this.name + " отримав " + damage + " пошкоджень. Залишилось здоров'я: " + this.health);
    }

    public void attack(Droid enemy) {
        if (this.isFrozen) {
            System.out.println(this.name + " не може атакувати, оскільки заморожений!");
            return;
        }

        System.out.println(this.name + " атакує " + enemy.getName() + " і завдає " + this.damage + " пошкоджень.");
        enemy.takeDamage(this.damage);
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

