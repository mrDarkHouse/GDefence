package com.darkhouse.gdefence.Level.Ability;


import com.darkhouse.gdefence.Level.Mob.Mob;

import java.awt.*;

public abstract class Debuff {

    private Color color = Color.RED;
    protected Mob owner;

    public Debuff(Mob owner) {
        this.owner = owner;
    }

    public abstract void apply();

    public abstract void dispell();

}
