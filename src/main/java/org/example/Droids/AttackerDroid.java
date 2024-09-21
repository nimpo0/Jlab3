package org.example.Droids;

import org.example.file.WorkWithFile;

public class AttackerDroid extends Droid {

    private int charge;
    private int roundsToCharge;

    public AttackerDroid() {
        super();
        this.charge = 0;
        this.roundsToCharge = 2;
    }

    public AttackerDroid(String name, int health, int damage, int roundsToCharge) {
        super(name, health, damage);
        this.charge = 0;
        this.roundsToCharge = roundsToCharge;
    }

    public int getCharge() {
        return charge;
    }

    public int getRoundsToCharge() {
        return roundsToCharge;
    }

    @Override
    public void updateRound(WorkWithFile workWithFile) {
        charge(workWithFile);
    }

    public void charge(WorkWithFile workWithFile) {
        if (charge < roundsToCharge) {
            charge++;
            logAndPrint(this.name + " заряджається. Прогрес заряду: " + charge + "/" + roundsToCharge, workWithFile);
        } else {
            logAndPrint(this.name + " готовий до сильної атаки!", workWithFile);
        }
    }


    public void strongAttack(Droid target, WorkWithFile workWithFile) {
        if (charge >= roundsToCharge) {
            int strongDamage = this.damage * 2;
            logAndPrint(this.name + " виконує сильну атаку на " + target.getName() + " і завдає " + strongDamage + " пошкоджень.", workWithFile);
            target.takeDamage(strongDamage, workWithFile);
            charge = 0;
        } else {
            logAndPrint(this.name + " не готовий до сильної атаки. Потрібно зарядитися ще " + (roundsToCharge - charge) + " раундів.", workWithFile);
        }
    }

    @Override
    public String toString() {
        return "\tАтакуючий дроїд (" +
                "ім'я='" + name + '\'' +
                ", здоров'я=" + health +
                ", сила=" + damage +
                ", заряд=" + charge +
                ", кількість раундів до повного заряду=" + roundsToCharge +
                ')';
    }
}
