package com.darkhouse.gdefence.Level.Ability.Mob;


import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Path.MapTile;
import com.darkhouse.gdefence.Level.Tower.Tower;

public abstract class MobAbility implements Cloneable{
    protected String name;
    protected boolean isHidden = false;

    public boolean isHidden() {
        return isHidden;
    }

    public String getName() {
        return name;
    }

    public MobAbility copy(){
        try {
            return (MobAbility) this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        throw new NullPointerException("Cant clone ability");
    }

    public MobAbility(String name, boolean isHidden) {
        this.name = name;
        this.isHidden = isHidden;
    }
    public abstract String getTooltip();
    public abstract void init();

    public interface IType{
    }

    public interface IGetDmg extends IType {
        float getDmg(Tower source, float dmg);
    }
    public interface IMove extends IType{
        void move(MapTile currentTile);
    }

//    public enum EventTypes implements Event{
//        getDmg, move, attack, autoCast
//    }


    public Mob getOwner() {
        return owner;
    }

    protected Mob owner;

    public void setOwner(Mob owner) {
        this.owner = owner;
        init();
    }
}
