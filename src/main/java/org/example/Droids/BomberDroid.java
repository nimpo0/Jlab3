package org.example.Droids;

public class BomberDroid extends Droid {

    private int areaDamage;

    public BomberDroid() {
        super();
        this.areaDamage = 30;
    }

    public BomberDroid(String name, int health, int damage, int areaDamage) {
        super(name, health, damage);
        this.areaDamage = areaDamage;
    }

    public int getAreaDamage() {
        return areaDamage;
    }

    public void setAreaDamage(int areaDamage) {
        this.areaDamage = areaDamage;
    }

    public void bombard(Droid[] enemies) {
        if (this.isFrozen) {
            System.out.println(this.name + " не може здійснити масований удар, оскільки заморожений!");
            return;
        }

        System.out.println(this.name + " здійснює масований удар!");

        for (Droid enemy : enemies) {
            if (enemy != null) {
                enemy.takeDamage(this.areaDamage);
            }
        }
    }

    @Override
    public String toString() {
        return "BomberDroid{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", damage=" + damage +
                ", areaDamage=" + areaDamage +
                '}';
    }
}
