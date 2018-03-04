package com.darkhouse.gdefence.Level.Ability.Tools;


import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.darkhouse.gdefence.Level.Ability.Tower.Ability;
import com.darkhouse.gdefence.Model.Effectable;

public abstract class Effect<T extends Effectable>{
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
    private boolean strong;
    public boolean isStrong() {
        return strong;
    }
    protected void setStrong() {
        this.strong = true;
    }
    private boolean listener;
    public boolean isListener() {
        return listener;
    }
    protected void setListener() {
        this.listener = true;
    }

    //    private Color color;// = Color.RED;
    protected float duration;
    protected float currentTime;
    private String iconPath;
    public String getIconPath() {
        return iconPath;
    }
    protected T owner;
    private Class<? extends Ability.IAbilityType> type;


    public Class<? extends Ability.IAbilityType> getType() {
        return type;
    }

    public Effect setOwner(T owner) {
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


    private boolean workOnAdditionalProjectiles;

    public boolean isWorkOnAdditionalProjectiles() {
        return workOnAdditionalProjectiles;
    }
    protected void setWorkOnAdditionalProjectiles() {
        this.workOnAdditionalProjectiles = true;
    }




    //    public interface IGetDmg extends MobAbility.IType {
//        float getDmg(Tower source, float dmg);
//    }
//    public interface IMove extends MobAbility.IType {
//        void move(MapTile currentTile);
//    }

    public Effect(boolean positive,
                  boolean isDispellable, float duration, Class<? extends Ability.IAbilityType> type){
        this.isBuff = positive;
        this.isDispellable = isDispellable;
        this.duration = duration;
        currentTime = duration;
        this.type = type;
        this.isHidden = true;
    }

    public Effect(boolean positive, boolean isDispellable,/* Mob owner,*/ float duration, String effectIconPath/*32x32*/,
                  Class<? extends Ability.IAbilityType> type) {
        this(positive, isDispellable, duration, type);
//        this.type = type;
//        this.isHidden = false;
//        this.isBuff = positive;
//        this.isDispellable = isDispellable;
//        this.duration = duration;
//        currentTime = duration;
        this.iconPath = effectIconPath;
        this.isHidden = false;
    }

    public Effect(boolean positive, boolean isDispellable,
                  /* Mob owner,*/ float duration, String effectIconPath/*32x32*/){
        this(positive, isDispellable, duration, effectIconPath, Ability.INone.class);
    }
    public Effect(boolean positive, boolean isDispellable,
                  /* Mob owner,*/ float duration){
        this(positive, isDispellable, duration, Ability.INone.class);
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
        if(isCooldownable()){
            getCooldownObject().act(delta);
        }

        if(duration == -1){//infinity time
            return;
        }
        currentTime -= delta;
        if(currentTime < 0){
            dispell();
        }
    }
}
