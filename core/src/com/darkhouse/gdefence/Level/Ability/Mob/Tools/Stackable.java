package com.darkhouse.gdefence.Level.Ability.Mob.Tools;

import com.darkhouse.gdefence.Level.Ability.Mob.Effects.Effect;
import com.darkhouse.gdefence.Level.Ability.Mob.Modifiers.Modifier;


public abstract class Stackable extends Effect{
    private int stacks;
    private int maxStacks;

    public Stackable(boolean positive, boolean isHidden, boolean isDispellable, float duration, String effectIconPath, int maxStacks, Modifier startEffect) {
        super(positive, isHidden, isDispellable, duration, effectIconPath);
        this.maxStacks = maxStacks;
    }

//    protected abstract void updateStack();

    public int getStacks() {
        return stacks;
    }
    public void addStack(){
        stacks++;
    }
    public void setStacks(int value){
        stacks = value;
    }

    public int getMaxStacks() {
        return maxStacks;
    }

    public void deleteStack(){
        stacks--;
        if(stacks == 0){
            dispell();
        }
    }


//    @Override
//    public void apply() {
//
//    }
//
//    @Override
//    public void dispell() {
//
//    }
}
