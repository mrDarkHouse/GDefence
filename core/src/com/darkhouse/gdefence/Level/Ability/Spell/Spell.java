package com.darkhouse.gdefence.Level.Ability.Spell;


import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.Level.Ability.Tools.Cooldown;
import com.darkhouse.gdefence.Level.Ability.Tools.DamageType;
import com.darkhouse.gdefence.Level.Ability.Tower.Ability;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Model.Effectable;
import com.darkhouse.gdefence.Model.Panels.SpellPanel;
import com.darkhouse.gdefence.Objects.DamageSource;
import com.darkhouse.gdefence.Objects.SpellObject;
import com.darkhouse.gdefence.Screens.LevelMap;

public abstract class Spell implements DamageSource{

    private int energyCost;
//    private int cooldown;
    private Cooldown cooldown;
    private SpellPanel.SpellButton.SpellTooltip tooltip;


    public void setTooltip(SpellPanel.SpellButton.SpellTooltip tooltip) {
        this.tooltip = tooltip;
    }

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

    public boolean isPierceImmunity() {
        return prototype.isPierceImmunity();
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
//        //stat manager
//
    }
    public float hitMob(Mob m, DamageType type, int dmg){
        float getDmg = m.hit(dmg, type, this);
        addExp(getDmg/10);
        return getDmg;
    }
    public void addExp(float value){
        getPrototype().addExp(value);
        if(tooltip != null) tooltip.hasChanged();
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
