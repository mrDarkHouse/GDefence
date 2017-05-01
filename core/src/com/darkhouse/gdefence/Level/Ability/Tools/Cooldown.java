package com.darkhouse.gdefence.Level.Ability.Tools;


public class Cooldown{
    private float cooldown;
    private float cdCap;

    private boolean isHidden;
    public void setHidden() {
        isHidden = true;
    }
    public boolean isHidden() {
        return isHidden;
    }

    public float getCdCap() {
        return cdCap;
    }
    public float getCooldown() {
        return cooldown;
    }

    public boolean isReady(){
        return cooldown == 0;
    }
    public void resetCooldown(){
        cooldown = cdCap;
    }

    public Cooldown(float cdCap) {
        this.cdCap = cdCap;
//        cooldown = cdCap;
        cooldown = 0;
    }

    public void act(float delta){
        if(cooldown - delta <= 0)cooldown = 0;
        else cooldown -= delta;
    }
}
