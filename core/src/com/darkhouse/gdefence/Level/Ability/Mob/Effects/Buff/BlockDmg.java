package com.darkhouse.gdefence.Level.Ability.Mob.Effects.Buff;


import com.darkhouse.gdefence.Level.Ability.Mob.Effects.Effect;
import com.darkhouse.gdefence.Level.Ability.Mob.MobAbility;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Tower.Tower;

public class BlockDmg extends Effect implements MobAbility.IGetDmg{
    private int blockEmount;
    private int blockMinLimit;

    public BlockDmg(float duration, int blockEmount, int blockMinLimit) {
        super(true, true, true, duration, "blockDmg");
        this.blockEmount = blockEmount;
        this.blockMinLimit = blockMinLimit;
    }

    @Override
    public float getDmg(Tower source, float dmg) {
        if(dmg < blockMinLimit)return dmg;
        float newDmg = dmg - blockEmount;
        return newDmg > blockMinLimit ? newDmg:blockMinLimit;
    }

    @Override
    public void apply() {

    }

    @Override
    public void dispell() {

    }


}
