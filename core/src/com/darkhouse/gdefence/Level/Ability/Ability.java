package com.darkhouse.gdefence.Level.Ability;


import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Tower.Tower;

public abstract class Ability {

    public enum UseType{
        preattack, onHit, onBuild, onKilled;


//        public
    }
    protected UseType useType;

    public UseType getUseType() {
        return useType;
    }
    //private int level;

    protected Tower owner;

    public void setOwner(Tower owner) {
        this.owner = owner;
    }

    public Ability(UseType useType) {
        //this.owner = owner;
        this.useType = useType;
    }

    public abstract void use(Mob target);//targetly

    public int getDmg(int dmgStart){
        return dmgStart;
    }//only dmg


}
