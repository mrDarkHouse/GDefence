package com.darkhouse.gdefence.Level.Ability.Mob.Tools;


import com.darkhouse.gdefence.Level.Ability.Mob.Effects.Effect;
import com.darkhouse.gdefence.Level.Mob.Mob;

import java.io.Serializable;

public class Cooldown{
    private float cooldown;
    private float cdCap;

    public float getCdCap() {
        return cdCap;
    }
    public float getCooldown() {
        return cooldown;
    }

    public boolean isReady(){
        return cooldown == 0;
    }
    public void resetCooldown(){
        cooldown = cdCap;
    }

    public Cooldown(float cdCap) {
        this.cdCap = cdCap;
//        cooldown = cdCap;
        cooldown = 0;
    }

    public void act(float delta){
        if(cooldown - delta <= 0)cooldown = 0;
        else cooldown -= delta;
    }
}
