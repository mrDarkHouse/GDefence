package com.darkhouse.gdefence.Level.Ability.Spell;


import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.Level.Ability.Tools.Cooldown;
import com.darkhouse.gdefence.Level.Ability.Tower.Ability;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Model.Effectable;
import com.darkhouse.gdefence.Objects.DamageSource;
import com.darkhouse.gdefence.Objects.SpellObject;
import com.darkhouse.gdefence.Screens.LevelMap;

public abstract class Spell implements DamageSource{

    private int energyCost;
//    private int cooldown;
    private Cooldown cooldown;

    public int getEnergyCost() {
        return energyCost;
    }
    public float getCooldown() {
        return cooldown.getCdCap();
    }

    public Cooldown getCooldownObject() {
        return cooldown;
    }

    private SpellObject prototype;

    public SpellObject getPrototype() {
        return prototype;
    }

    public Spell(SpellObject prototype) {
        this.energyCost = prototype.getEnergyCost();
//        this.cooldown = prototype.getCooldown();
        cooldown = new Cooldown(prototype.getCooldown());
        this.prototype = prototype;
        this.affectedTypes = prototype.getAffectedTypes();
    }

    private Array<Class<? extends Effectable>> affectedTypes;

    public Array<Class<? extends Effectable>> getAffectedTypes() {
        return affectedTypes;
    }

    public boolean isAffectable(Effectable e){
        for (Class<? extends Effectable> c:affectedTypes) {
            if (e.getClass() == c){
                return true;
            }
        }
        return false;
    }

    public void addKill(/*Class<? extends Mob>*/Mob killedMob){
        //add exp from killedMob

    }
    public void hitMob(Mob m, int dmg){
        m.hit(dmg, this);
        getPrototype().addExp(dmg/5);
    }

    abstract public void use(Array<? extends Effectable> targets);

    public interface ITarget{
//        void use(Effectable target);
    }
    public interface INonTarget{
//        void use();
    }
    public interface IAoe{
        int getAoe();
//        void use(Array<Effectable> targets);
    }

    public void act(float delta){
        getCooldownObject().act(delta);
    }

}
