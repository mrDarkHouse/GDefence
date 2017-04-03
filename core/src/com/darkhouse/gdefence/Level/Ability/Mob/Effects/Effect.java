package com.darkhouse.gdefence.Level.Ability.Mob.Effects;


import com.badlogic.gdx.graphics.Texture;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Path.MapTile;
import com.darkhouse.gdefence.Level.Tower.Tower;

import java.awt.*;

public abstract class Effect {
    private boolean isBuff;
    public boolean isBuff() {
        return isBuff;
    }
    private boolean isHidden;
    public boolean isHidden() {
        return isHidden;
    }
    //    private Color color;// = Color.RED;
    protected float duration;
    protected float currentTime;
    private Texture icon;
    public Texture getIcon() {
        return icon;
    }
    protected Mob owner;

    public Effect(boolean positive, boolean isHidden, Mob owner, float duration, String effectIconPath/*32x32*/) {
        this.isBuff = positive;
        this.isHidden = isHidden;
        this.owner = owner;
        this.duration = duration;
        currentTime = duration;
        if(effectIconPath.equals(""))icon = GDefence.getInstance().assetLoader.get("", Texture.class);
        else icon = GDefence.getInstance().assetLoader.get("AbilityIcons/Effects/" + effectIconPath + ".png", Texture.class);
    }

    public abstract void apply();

    public abstract void dispell();

    public void updateDuration(){
        if(duration == -1){//infinity time
            return;
        }
        if (owner.haveDebuff(this.getClass())) {
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
