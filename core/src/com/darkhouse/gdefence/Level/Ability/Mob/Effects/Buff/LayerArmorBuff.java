package com.darkhouse.gdefence.Level.Ability.Mob.Effects.Buff;


import com.darkhouse.gdefence.Level.Ability.Mob.MobAbility;
import com.darkhouse.gdefence.Level.Ability.Mob.Modifiers.BonusArmor;
import com.darkhouse.gdefence.Level.Ability.Mob.Tools.Stackable;
import com.darkhouse.gdefence.Level.Tower.Tower;

public class LayerArmorBuff extends Stackable implements MobAbility.IGetDmg{
    private int armorPerStack;
    private BonusArmor modifier;

    public LayerArmorBuff(int stacks, int armor) {
        super(true, false, false, -1, "swimSpeed", stacks, new BonusArmor(2));
        this.armorPerStack = armor;
        setStacks(stacks);
        modifier = new BonusArmor(armor*stacks);
    }
//    protected void updateStack(){
//        modifier.setArmor(armorPerStack*getStacks());
//    }

    @Override
    public float getDmg(Tower source, float dmg) {
        System.out.println(owner.getArmor());
        deleteStack();
//        updateStack();
        return dmg;
    }

    @Override
    public void deleteStack() {
        owner.changeArmor(-armorPerStack);
        super.deleteStack();
    }

    @Override
    public void apply() {
        owner.changeArmor(modifier.getArmor());
    }

    @Override
    public void dispell() {
//        owner.changeArmor(-modifier.getArmor());
        owner.deleteEffect(this.getClass());
    }
}
