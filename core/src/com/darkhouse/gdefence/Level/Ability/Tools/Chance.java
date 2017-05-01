package com.darkhouse.gdefence.Level.Ability.Tools;


import java.util.Random;

public class Chance {

    public static boolean proc(float chance){
        Random r = new Random();
        System.out.println(r.nextFloat());
        return (r.nextFloat() > 1 - chance);
    }

}
