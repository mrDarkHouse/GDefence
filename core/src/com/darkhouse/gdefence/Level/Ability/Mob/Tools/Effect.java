package com.darkhouse.gdefence.Level.Ability.Mob.Tools;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Level.Mob.Mob;

import java.io.Serializable;

public abstract class Effect implements Serializable{
    private boolean isBuff;
    public boolean isBuff() {
        return isBuff;
    }
    private boolean isHidden;
    public boolean isHidden() {
        return isHidden;
    }
    private boolean isDispellable;
    public boolean isDispellable() {
        return isDispellable;
    }
    //    private Color color;// = Color.RED;
    protected float duration;
    protected float currentTime;
    private WidgetGroup group;
    private Texture icon;
    public Texture getIcon() {
        return icon;
    }
    protected Mob owner;


    public Effect setOwner(Mob owner) {
        this.owner = owner;
        return this;
    }

    private Cooldown cooldown;
    public Cooldown getCooldownObject() {//return null if !isCooldownable
        return cooldown;
    }
    public boolean isCooldownable(){
        return cooldown != null;
    }
    public void setCooldownable(Cooldown cooldown) {
        this.cooldown = cooldown;
    }

    private Stackable stackable;
    public Stackable getStackableObject() {
        return stackable;
    }
    public boolean isStackable(){
                                      return stackable != null;
    }
    public void setStackable(Stackable stackable) {
        this.stackable = stackable;
    }

    private Aura aura;
    public Aura getAuraObject() {
        return aura;
    }
    public boolean isAura(){
        return aura != null;
    }
    public void setAura(Aura aura) {
        this.aura = aura;
    }





    //    public interface IGetDmg extends MobAbility.IType {
//        float getDmg(Tower source, float dmg);
//    }
//    public interface IMove extends MobAbility.IType {
//        void move(MapTile currentTile);
//    }

    public Effect(boolean positive, boolean isDispellable,/* Mob owner,*/ float duration, String effectIconPath/*32x32*/) {
        this.isHidden = false;
        this.isBuff = positive;
        this.isDispellable = isDispellable;
//        this.owner = owner;
        this.duration = duration;
        currentTime = duration;
        if(effectIconPath.equals(""))icon = GDefence.getInstance().assetLoader.get("", Texture.class);
        else icon = GDefence.getInstance().assetLoader.get("AbilityIcons/Effects/" + effectIconPath + ".png", Texture.class);
//        group = new WidgetGroup();
//        group.addActor(icon);
    }
    public Effect(boolean positive, boolean isDispellable, float duration){
        this.isHidden = true;
        this.isBuff = positive;
        this.isDispellable = isDispellable;
//        this.owner = owner;
        this.duration = duration;
        currentTime = duration;
    }

    public abstract void apply();

    public void dispell(){
        owner.deleteEffect(this.getClass());
    }

    public void updateDuration(){
        if(duration == -1){//infinity time
            return;
        }
        if (owner.haveEffect(this.getClass())) {
            currentTime = duration;
        }
    }

    public void act(float delta){
        if(duration == -1){//infinity time
            return;
        }
        currentTime -= delta;
        if(currentTime < 0){
            dispell();
        }
    }
}
