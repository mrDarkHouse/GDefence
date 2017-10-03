package com.darkhouse.gdefence.Level.Ability.Mob;


import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Path.MapTile;
import com.darkhouse.gdefence.Level.Path.WalkableMapTile;
import com.darkhouse.gdefence.Level.Tower.Tower;

import java.io.*;

public abstract class MobAbility{
    protected String name;
    protected boolean isHidden = false;

    public abstract static class AbilityPrototype {
        protected String name;
        protected boolean isHidden;

        public String getName() {
            return GDefence.getInstance().assetLoader.getWord(name);
        }
        public boolean isHidden() {
            return isHidden;
        }

        public AbilityPrototype(String name, boolean isHidden) {
            this.name = name;
            this.isHidden = isHidden;
        }

        abstract public MobAbility getAbility();
        abstract public String getTooltip();
    }

    public boolean isHidden() {
        return isHidden;
    }

//    public String getName() {
//        return name;
//    }

//    public MobAbility copy(){
//        try {
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            ObjectOutputStream ous = new ObjectOutputStream(baos);
//            ous.writeObject(this);
//            ous.close();
//            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
//            ObjectInputStream ois = new ObjectInputStream(bais);
//            return ((MobAbility) ois.readObject());
//
////            return (MobAbility) this.clone();
////        } catch (CloneNotSupportedException e) {
////            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        throw new NullPointerException("Cant clone ability");
//    }

//    public MobAbility(String texturePath, boolean isHidden) {
//        this.texturePath = texturePath;
//        this.isHidden = isHidden;
//    }
//    public abstract String getTooltip();
    public abstract void init();

    public interface IType{
    }

//    public enum procType{
//        onSpawn, getDmg, move, die
//    }

    public interface IGetDmg extends IType {
        float getDmg(/*Tower source, */float dmg);
    }
    public interface IMove extends IType{
        void move(WalkableMapTile currentTile);
    }
    public interface ISpawn extends IType{
        void spawned();
    }
    public interface IDie extends IType{
        boolean die(/*Tower source*/);
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
