package com.darkhouse.gdefence.Level.Ability.Tower;


import com.darkhouse.gdefence.Level.Mob.Mob;

import java.awt.*;

public abstract class Debuff {
    protected float duration;
    protected float currentTime;
    private Color color = Color.RED;
    protected Mob owner;

    public Debuff(Mob owner, float duration) {
        this.owner = owner;
        this.duration = duration;
        currentTime = duration;
    }

    public abstract void apply();

    public abstract void dispell();

    public void updateDuration(){
        if(duration == -1){//infinity time
            return;
        }
        if (owner.haveDebuff(this)) {
            currentTime = duration;
        }
    }

    public void act(float delta){
        if(duration == -1){//infinity time
            return;
        }
        currentTime -= delta;
        if(currentTime < 0){
            dispell();
        }
    }
}
