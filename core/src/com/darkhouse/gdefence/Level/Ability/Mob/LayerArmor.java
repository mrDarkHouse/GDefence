package com.darkhouse.gdefence.Level.Ability.Mob;


import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Ability.Mob.Modifiers.BonusArmor;
import com.darkhouse.gdefence.Level.Ability.Tools.Stackable;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Tower.Tower;

public class LayerArmor extends MobAbility implements MobAbility.ISpawn{

    private class LayerArmorBuff extends Effect<Mob> implements IGetDmg{
        private int armorPerStack;
        private BonusArmor modifier;

        public LayerArmorBuff(int stacks, int armor) {
            super(true, false, -1, "bonusArmor");
            this.armorPerStack = armor;
            setStackable(new Stackable(stacks));
            getStackableObject().setCurrentStacks(stacks);
            modifier = new BonusArmor(armor*stacks);
        }
        //    protected void updateStack(){
        //        modifier.setArmor(armorPerStack*getStacks());
        //    }

        @Override
        public float getDmg(/*Tower source, */float dmg) {
            owner.changeArmor(-armorPerStack);
            getStackableObject().deleteStack();
            if(getStackableObject().isZeroStacks()) dispell();
            //        updateStack();
            return dmg;
        }

        @Override
        public void apply() {
            owner.changeArmor(modifier.getArmor());
        }

        //    @Override
        //    public void dispell() {
        ////        owner.changeArmor(-modifier.getArmor());
        //        owner.deleteEffect(this.getClass());
        //    }
    }
    public static class P extends AbilityPrototype {
        private int stacks;
        private int armor;

        public P(int stacks, int armor) {
            super("Layer Armor", false);
            this.stacks = stacks;
            this.armor = armor;
        }
        public MobAbility getAbility(){
            return new LayerArmor(this);
        }

        @Override
        public String getTooltip() {
            return "Have [#64A619ff]" + stacks + "[] stacks, each add [#64A619ff]" + armor + "[] armor" + System.getProperty("line.separator") +
                    "after getting attack [#CD6600ff]1 []stack disappear";
        }
    }

    private LayerArmorBuff buff;

    public LayerArmor(P prototype) {
        buff = new LayerArmorBuff(prototype.stacks, prototype.armor);
    }

    @Override
    public void init() {
        buff.setOwner(owner);
    }

    @Override
    public void spawned() {
        owner.addEffect(buff);
    }


}
