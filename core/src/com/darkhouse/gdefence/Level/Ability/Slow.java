package com.darkhouse.gdefence.Level.Ability;


import com.badlogic.gdx.utils.Timer;
import com.darkhouse.gdefence.Level.Mob.Mob;

public class Slow extends Debuff {
    private float percent;
    private float duration;
    private float changeSpeed;

    public Slow(Mob owner, float percent, float duration) {
        super(owner);
        this.percent = percent;
        this.duration = duration;
    }

    public void apply(){
        Timer t = new Timer();
        changeSpeed = owner.getSpeed()*percent;
        owner.changeSpeed(-changeSpeed);
        t.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                dispell();
            }
        }, duration);

    }

    @Override
    public void dispell() {
        owner.changeSpeed(changeSpeed);
        owner.deleteDebuff(this);
    }
}
