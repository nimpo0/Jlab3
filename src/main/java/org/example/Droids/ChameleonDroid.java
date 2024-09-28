package org.example.Droids;

import org.example.file.WorkWithFile;

public class ChameleonDroid extends Droid {

    protected int camouflageDuration;
    private int remainingCamouflage;
    private boolean isAdapted;
    private boolean camouflageUsed;

    public ChameleonDroid() {
        super();
        this.camouflageDuration = 2;
        this.remainingCamouflage = 0;
        this.isAdapted = false;
        this.camouflageUsed = false;
    }

    public ChameleonDroid(String name, int health, int damage, int camouflageDuration) {
        super(name, health, damage);
        this.camouflageDuration = camouflageDuration;
        this.remainingCamouflage = 0;
        this.isAdapted = false;
        this.camouflageUsed = false;
    }

    public int getRemainingCamouflage() {
        return remainingCamouflage;
    }

    public void activateCamouflage(WorkWithFile workWithFile) {
        if (!camouflageUsed) {
            this.remainingCamouflage = this.camouflageDuration;
            camouflageUsed = true;
            logAndPrint(this.name + " активує камуфляж на " + this.camouflageDuration + " раундів.", workWithFile);
        } else {
            logAndPrint(this.name + " не може активувати камуфляж, оскільки він вже використаний.", workWithFile);
        }
    }

    public void adapt(int receivedDamage, WorkWithFile workWithFile) {
        if (!isAdapted) {
            if (receivedDamage > 20) {
                logAndPrint(this.name + " адаптується, збільшуючи свою оборону на 10!", workWithFile);
                this.health += 10;
            } else {
                logAndPrint(this.name + " адаптується, збільшуючи свою атаку на 5!", workWithFile);
                this.damage += 5;
            }
            isAdapted = true;
        }
    }

    public void decrementCamouflage(WorkWithFile workWithFile) {
        if (this.remainingCamouflage >= 0) {
            this.remainingCamouflage--;
            logAndPrint(this.name + " має камуфляж ще на " + this.remainingCamouflage +  " раундів.", workWithFile);
            if (this.remainingCamouflage == 0) {
                logAndPrint(this.name + " втрачає камуфляж.", workWithFile);
            }
        }
    }

    @Override
    public void updateRound(WorkWithFile workWithFile) {
        decrementCamouflage(workWithFile);
        unfreeze(workWithFile);
    }

    @Override
    public void performAction(Droid enemy, WorkWithFile workWithFile) {
        if (getRemainingCamouflage() == 0) {
            activateCamouflage(workWithFile);
        }
        attack(enemy, workWithFile);
        updateRound(workWithFile);
    }

    @Override
    public void takeDamage(int damage, WorkWithFile workWithFile) {
        if (remainingCamouflage >= 0) {
            logAndPrint(this.name + " уникнув атаки завдяки камуфляжу.", workWithFile);
        } else {
            super.takeDamage(damage, workWithFile);
            adapt(damage, workWithFile);
        }
    }

    @Override
    public String toString() {
        return "\tХамелеон (" +
                "ім'я='" + name + '\'' +
                ", здоров'я=" + health +
                ", сила=" + damage +
                ", кількість камуфляжу=" + camouflageDuration +
                ')';
    }
}