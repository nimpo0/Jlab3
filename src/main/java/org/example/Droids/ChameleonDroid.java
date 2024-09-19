package org.example.Droids;

public class ChameleonDroid extends Droid {

    private int camouflageDuration;
    private int counterAttackDamage;
    private int remainingCamouflage;

    public ChameleonDroid() {
        super();
        this.camouflageDuration = 2;
        this.counterAttackDamage = 15;
        this.remainingCamouflage = 0;
    }

    public ChameleonDroid(String name, int health, int damage, int camouflageDuration, int counterAttackDamage) {
        super(name, health, damage);
        this.camouflageDuration = camouflageDuration;
        this.counterAttackDamage = counterAttackDamage;
        this.remainingCamouflage = 0;
    }

    public int getCamouflageDuration() {
        return camouflageDuration;
    }

    public void setCamouflageDuration(int camouflageDuration) {
        this.camouflageDuration = camouflageDuration;
    }

    public int getCounterAttackDamage() {
        return counterAttackDamage;
    }

    public void setCounterAttackDamage(int counterAttackDamage) {
        this.counterAttackDamage = counterAttackDamage;
    }

    public void activateCamouflage() {
        if (this.remainingCamouflage == 0) {
            this.remainingCamouflage = this.camouflageDuration;
            System.out.println(this.name + " активує камуфляж на " + this.camouflageDuration + " раундів.");
        } else {
            System.out.println(this.name + " вже використовує камуфляж на " + this.remainingCamouflage + " раундів.");
        }
    }

    public void decrementCamouflage() {
        if (this.remainingCamouflage > 0) {
            this.remainingCamouflage--;
            if (this.remainingCamouflage == 0) {
                System.out.println(this.name + " втрачає камуфляж.");
            }
        }
    }

    @Override
    public void takeDamage(int damage) {
        if (this.remainingCamouflage > 0) {
            System.out.println(this.name + " уникнув частини атаки завдяки камуфляжу.");
        } else {
            super.takeDamage(damage);
            System.out.println(this.name + " завдає " + this.counterAttackDamage + " ушкоджень у відповідь.");
            this.attack(this);
        }
    }

    @Override
    public String toString() {
        return "ChameleonDroid{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", damage=" + damage +
                ", camouflageDuration=" + camouflageDuration +
                ", counterAttackDamage=" + counterAttackDamage +
                '}';
    }
}
