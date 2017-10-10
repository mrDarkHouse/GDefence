package com.darkhouse.gdefence.Level.Ability.Mob;


import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
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
            super("layerArmor", false);
            this.stacks = stacks;
            this.armor = armor;
        }
        public MobAbility getAbility(){
            return new LayerArmor(this);
        }

        @Override
        public String getTooltip() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            return l.getWord("layerArmorTooltip1") + " " + FontLoader.colorString(Integer.toString(stacks), 3) +
                    l.getWord("layerArmorTooltip2") + " " + FontLoader.colorString(Integer.toString(armor), 3) + " " +
                    l.getWord("layerArmorTooltip3") + System.getProperty("line.separator") +
                    l.getWord("layerArmorTooltip4") + " " + FontLoader.colorString(Integer.toString(1), 4) + " " +
                    l.getWord("layerArmorTooltip5");
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
