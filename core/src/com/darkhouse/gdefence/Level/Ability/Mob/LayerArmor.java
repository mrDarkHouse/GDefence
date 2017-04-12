package com.darkhouse.gdefence.Level.Ability.Mob;


import com.darkhouse.gdefence.Level.Ability.Mob.Effects.Buff.LayerArmorBuff;
import com.darkhouse.gdefence.Level.Tower.Tower;

public class LayerArmor extends MobAbility implements MobAbility.ISpawn{
    private LayerArmorBuff buff;

    public static class P extends AblityPrototype{
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
