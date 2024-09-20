package org.example.Droids;

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
    public void updateRound() {
        charge();
    }

    public void charge() {
        if (charge < roundsToCharge) {
            charge++;
            System.out.println(this.name + " заряджається. Прогрес заряду: " + charge + "/" + roundsToCharge);
        } else {
            System.out.println(this.name + " готовий до сильної атаки!");
        }
    }

    public void strongAttack(Droid target) {
        if (charge >= roundsToCharge) {
            int strongDamage = this.damage * 2;
            System.out.println(this.name + " виконує сильну атаку на " + target.getName() + " і завдає " + strongDamage + " пошкоджень.");
            target.takeDamage(strongDamage);
            charge = 0;
        } else {
            System.out.println(this.name + " не готовий до сильної атаки. Потрібно зарядитися ще " + (roundsToCharge - charge) + " раундів.");
        }
    }

    @Override
    public String toString() {
        return "AttackerDroid{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", damage=" + damage +
                ", charge=" + charge +
                ", roundsToCharge=" + roundsToCharge +
                '}';
    }

}

