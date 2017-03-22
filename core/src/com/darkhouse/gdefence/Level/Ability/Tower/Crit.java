package com.darkhouse.gdefence.Level.Ability.Tower;


import com.darkhouse.gdefence.Level.Mob.Mob;

public class Crit extends Ability {
    private float chance;
    private float mulitplayer;


    public Crit(float chance, float multiplayer) {
        super(UseType.onHit);
        this.chance = chance;
        this.mulitplayer = multiplayer;
    }

    @Override
    public void use(Mob target) {

    }

    public int getDmg(int dmgStart){
        return (int) (dmgStart * mulitplayer);
        //GraphicManager.applyAnimation("Crit");
    }
}
