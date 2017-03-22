package com.darkhouse.gdefence.Level.Ability.Tower;


import com.darkhouse.gdefence.Level.Mob.Mob;

public class Bash extends Ability implements Chance {

    private float chance = 0.2f;
    private float duration = 1f;
    private float bonusDmg = 20;

    public Bash() {
        super(UseType.onHit);
    }

    @Override
    public void use(Mob target) {

    }


    @Override
    public void proc(Mob target) {

    }

    public void onProc(Mob target){
        target.hit(20, owner);

    }
}
