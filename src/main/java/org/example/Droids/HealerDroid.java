package org.example.Droids;

import org.example.file.WorkWithFile;

public class HealerDroid extends Droid {

    private int healing;
    private int healCount;
    private int roundsSinceLastHeal;

    public HealerDroid() {
        super();
        this.healing = 20;
        this.healCount = 3;
        this.roundsSinceLastHeal = 0;
    }

    public HealerDroid(String name, int health, int damage, int healing) {
        super(name, health, damage);
        this.healing = healing;
        this.healCount = 3;
        this.roundsSinceLastHeal = 0;
    }

    @Override
    public void updateRound(WorkWithFile workWithFile) {
        roundsSinceLastHeal++;
    }

    public boolean canHeal() {
        return roundsSinceLastHeal >= healCount;
    }

    public void heal(WorkWithFile workWithFile) {
        setHealth(this.health + healing);
        String healMessage = this.name + " зцілив себе на " + healing + " одиниць. Здоров'я тепер: " + this.health;
        logAndPrint(healMessage, workWithFile);
        roundsSinceLastHeal = 0;
    }

    @Override
    public void attack(Droid enemy, WorkWithFile workWithFile) {
        String attackMessage = this.name + " атакує " + enemy.getName() + " і завдає " + this.damage + " пошкоджень.";
        logAndPrint(attackMessage, workWithFile);
        enemy.takeDamage(this.damage, workWithFile);
        if (canHeal()) {
            heal(workWithFile);
        }
    }

    @Override
    public String toString() {
        return "\tДроїд відновлювач (" +
                "ім'я='" + name + '\'' +
                ", здоров'я=" + health +
                ", сила=" + damage +
                ", відновлення=" + healing +
                ')';
    }
}
