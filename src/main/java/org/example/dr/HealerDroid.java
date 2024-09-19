package org.example.dr;

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

    public void heal() {
        setHealth(this.health + healing);
        System.out.println(this.name + " зцілив себе на " + healing + " одиниць. Здоров'я тепер: " + this.health);
    }

    public void heal(Droid droid) {
        droid.setHealth(droid.getHealth() + healing);
        System.out.println(this.name + " зцілив " + droid.getName() + " на " + healing + " одиниць. Здоров'я " + droid.getName() + ": " + droid.getHealth());
    }

    @Override
    public void attack(Droid enemy) {
        System.out.println(this.name + " атакує " + enemy.getName() + " і завдає " + this.damage + " пошкоджень.");
        enemy.takeDamage(this.damage);
        this.heal();
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
