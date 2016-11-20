package com.darkhouse.gdefence.Level.Ability;


import com.badlogic.gdx.utils.Timer;
import com.darkhouse.gdefence.Level.Mob.Mob;

public class ArmorReduction extends Debuff{
    private int armor;
    private float duration;


    public ArmorReduction(Mob owner, int armor, float duration) {
        super(owner);
        this.armor = armor;
        this.duration = duration;
    }

    @Override
    public void apply() {
        Timer t = new Timer();
        owner.setArmor(owner.getArmor() - armor);
        t.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                dispell();
            }
        }, duration);
    }

    @Override
    public void dispell() {
        owner.setArmor(owner.getArmor() + armor);
        owner.deleteDebuff(this);
    }
}
