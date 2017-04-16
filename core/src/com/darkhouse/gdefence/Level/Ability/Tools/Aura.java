package com.darkhouse.gdefence.Level.Ability.Tools;


import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.darkhouse.gdefence.Level.Level;
import com.darkhouse.gdefence.Level.Mob.Mob;

public class Aura {
    private Mob owner;
    private Circle rangeCircle;

    public Circle getRangeCircle() {
        return rangeCircle;
    }

    public Aura() {}

    public void init(Mob owner, int range){
        this.owner = owner;
        rangeCircle = new Circle(owner.getX(), owner.getY(), range);
    }

    public void update(){
        rangeCircle.setPosition(owner.getX(), owner.getY());
    }

    public boolean isInRange(Mob other){
        return rangeCircle.contains(other.getX(), other.getY());
    }
}
