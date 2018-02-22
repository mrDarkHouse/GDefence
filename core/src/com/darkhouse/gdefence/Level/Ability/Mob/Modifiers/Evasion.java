package com.darkhouse.gdefence.Level.Ability.Mob.Modifiers;


import com.darkhouse.gdefence.Level.Ability.Mob.MobAbility;
import com.darkhouse.gdefence.Level.Ability.Tools.DamageType;
import com.darkhouse.gdefence.Level.Tower.Tower;
import com.darkhouse.gdefence.Objects.DamageSource;

import java.util.Random;

public class Evasion extends Modifier implements MobAbility.IGetDmg{
    private float chance;
    private Random r;

    public Evasion(float chance) {
        this.chance = chance;
        r = new Random();
    }

    @Override
    public float getDmg(DamageSource source, DamageType type, float dmg) {
        if(chance > r.nextFloat()) {
            return 0;
        }
        else return dmg;
    }
}
