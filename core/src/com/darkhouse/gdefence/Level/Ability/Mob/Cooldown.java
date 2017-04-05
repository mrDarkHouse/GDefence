package com.darkhouse.gdefence.Level.Ability.Mob;


import com.darkhouse.gdefence.Level.Ability.Mob.Effects.Effect;
import com.darkhouse.gdefence.Level.Mob.Mob;

public class Cooldown{
    private float cooldown;
    private float cdCap;

    protected float getCdCap() {
        return cdCap;
    }

    protected boolean isReady(){
        return cooldown == 0;
    }
    protected void resetCooldown(){
        cooldown = 0;
    }

    public Cooldown(float cdCap) {
        this.cdCap = cdCap;
    }

    public void act(float delta){
        if(cooldown - delta == 0)cooldown = 0;
        else cooldown -= delta;
    }
}
