package org.example.dr;

public class Droid {

    protected String name;
    protected int health;
    protected int damage;

    public Droid() {
    }

    public Droid(String name, int health, int damage) {
        this.name = name;
        this.health = health;
        this.damage = damage;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public String toString() {
        return "Droid{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", damage=" + damage +
                '}';
    }
}
