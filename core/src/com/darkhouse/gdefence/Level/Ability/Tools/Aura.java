package com.darkhouse.gdefence.Level.Ability.Tools;


import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.darkhouse.gdefence.Level.Level;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Model.Effectable;

public class Aura {
    private Effectable owner;
    private Circle rangeCircle;

    private int range;

    public Circle getRangeCircle() {
        return rangeCircle;
    }

    public Aura(int range) {
        this.range = range;
    }

    public void init(Effectable owner){
        this.owner = owner;
        rangeCircle = new Circle(owner.getX(), owner.getY(), range);
    }

    public void update(){
        rangeCircle.setPosition(owner.getX(), owner.getY());
    }

    public boolean isInRange(Effectable other){
        return rangeCircle.contains(other.getX(), other.getY());
    }
}
