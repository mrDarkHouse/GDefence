package com.darkhouse.gdefence.Level.Ability.Tools;


import java.util.Random;

public class Chance {

    public static boolean proc(float chance){
        Random r = new Random();
        float f = r.nextFloat();
//        System.out.println(f);
        return (f > 1f - chance);
    }

}
