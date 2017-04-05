package com.darkhouse.gdefence.Level.Ability.Mob.Modifiers;


import com.darkhouse.gdefence.Level.Ability.Mob.MobAbility;
import com.darkhouse.gdefence.Level.Tower.Tower;

import java.util.Random;

public class Evasion extends Modifier implements MobAbility.IGetDmg{
    private float chance;
    private Random r;

    public Evasion(float chance) {
        this.chance = chance;
        r = new Random();
    }

    @Override
    public float getDmg(Tower source, float dmg) {
        if(r.nextFloat() > chance) return 0;
        else return dmg;
    }
}
