package com.darkhouse.gdefence.Level.Ability.Tools;


public class Stackable{
    private int stacks;
    private int maxStacks;

//    public Stackable(boolean positive, boolean isDispellable, float duration, String effectIconPath, int maxStacks) {
//        super(positive, isDispellable, duration, effectIconPath);
//        this.maxStacks = maxStacks;
//    }
//    public Stackable(boolean positive, boolean isDispellable, float duration, int maxStacks) {
//        super(positive, isDispellable, duration);
//        this.maxStacks = maxStacks;
//    }

    public Stackable(int maxStacks) {
        this.maxStacks = maxStacks;
    }


//    protected abstract void updateStack();

    public int getStacks() {
        return stacks;
    }
    public void addStack(){
        stacks++;
    }
    public void setCurrentStacks(int value){
        stacks = value;
    }

    public boolean isZeroStacks(){
        return stacks == 0;
    }
    public boolean isMaxStacks(){
        return stacks == maxStacks;
    }

    public int getMaxStacks() {
        return maxStacks;
    }

    public void deleteStack(){
        stacks--;
//        if(stacks == 0) dispell();
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
