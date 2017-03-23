package com.darkhouse.gdefence.Level.Ability.Mob;


import com.darkhouse.gdefence.Level.Tower.Tower;

public class BlockDmg extends MobAbility implements MobAbility.IGetDmg{
    private int blockEmount;
    private int blockMinLimit;

    public BlockDmg(int blockEmount, int blockMinLimit) {
        super("Block Dmg", false);
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
    public String getTooltip() {
        return "Block [#64A619ff]" + blockEmount + "[] dmg when attacked" + System.getProperty("line.separator") +
                "cant block less than [#64A619ff]" + blockMinLimit + "[] dmg";
    }

    @Override
    public void init() {

    }
}
