package com.darkhouse.gdefence.Level.Ability.Tower;


import com.darkhouse.gdefence.Level.Level;
import com.darkhouse.gdefence.Level.Mob.Mob;

public class AbilityManager {

    public static int procLevelDefenceAbility(Mob mob){

        return 0;
    }

    public static void procMobDefenceAbility(){

    }

    public static int procMobAttackAbility(Mob mob){
        return mob.getDmg()*2;
    }

}
