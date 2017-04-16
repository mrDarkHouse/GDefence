package com.darkhouse.gdefence.Level.Ability.Tools;


import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
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
    //    private Color color;// = Color.RED;
    protected float duration;
    protected float currentTime;
    private WidgetGroup group;
    private String iconPath;
    public String getIconPath() {
        return iconPath;
    }
    protected T owner;


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
        this.iconPath = effectIconPath;
//        if(effectIconPath.equals("")) iconPath = GDefence.getInstance().assetLoader.get("", Texture.class);
//        else iconPath = GDefence.getInstance().assetLoader.get("AbilityIcons/Effects/" + effectIconPath + ".png", Texture.class);
//        group = new WidgetGroup();
//        group.addActor(iconPath);
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
