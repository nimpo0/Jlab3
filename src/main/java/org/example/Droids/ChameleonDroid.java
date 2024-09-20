package org.example.Droids;

public class ChameleonDroid extends Droid {

    protected int camouflageDuration;
    private int remainingCamouflage;
    private boolean isAdapted;

    public ChameleonDroid() {
        super();
        this.camouflageDuration = 2;
        this.remainingCamouflage = 0;
        this.isAdapted = false;
    }

    public ChameleonDroid(String name, int health, int damage, int camouflageDuration) {
        super(name, health, damage);
        this.camouflageDuration = camouflageDuration;
        this.remainingCamouflage = 0;
        this.isAdapted = false;
    }

    public int getCamouflageDuration() {
        return camouflageDuration;
    }

    public int getRemainingCamouflage() {
        return remainingCamouflage;
    }

    public void setCamouflageDuration(int camouflageDuration) {
        this.camouflageDuration = camouflageDuration;
    }

    @Override
    public void updateRound() {
        decrementCamouflage();
    }


    public void activateCamouflage() {
        if (this.remainingCamouflage == 0) {
            this.remainingCamouflage = this.camouflageDuration;
            System.out.println(this.name + " активує камуфляж на " + this.camouflageDuration + " раундів.");
        }
    }

    public void decrementCamouflage() {
        if (this.remainingCamouflage > 0) {
            this.remainingCamouflage--;
            System.out.println(this.name + " має камуфляж ще на " + this.remainingCamouflage + " раундів.");
            if (this.remainingCamouflage == 0) {
                System.out.println(this.name + " втрачає камуфляж.");
            }
        }
    }

    public void adapt(int receivedDamage) {
        if (!isAdapted) {
            if (receivedDamage > 20) {
                System.out.println(this.name + " адаптується, збільшуючи свою оборону на 10!");
                this.health += 10;
            } else {
                System.out.println(this.name + " адаптується, збільшуючи свою атаку на 5!");
                this.damage += 5;
            }
            isAdapted = true;
        }
    }

    @Override
    public void takeDamage(int damage) {
        if (this.remainingCamouflage > 0) {
            System.out.println(this.name + " уникнув частини атаки завдяки камуфляжу.");
        } else {
            super.takeDamage(damage);
            this.adapt(damage);
        }
    }

    @Override
    public String toString() {
        return "ChameleonDroid{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", damage=" + damage +
                ", camouflageDuration=" + camouflageDuration +
                '}';
    }
}

