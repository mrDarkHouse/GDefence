package com.darkhouse.gdefence.Level.Ability.Mob.Modifiers;


import java.util.Random;

public class PseudoRandom {
    private float chance;
    private Random r;

    public PseudoRandom(float chance) {
        this.chance = chance;
        r = new Random();
    }
    public boolean proc(){
        float value = chance/10;
        if(chance > r.nextFloat()) {
            chance-=value;
            return true;
        } else{
            chance+=value;
            return false;
        }
//        return ;
    }
}
