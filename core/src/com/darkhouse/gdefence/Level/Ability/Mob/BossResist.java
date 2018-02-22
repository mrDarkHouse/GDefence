package com.darkhouse.gdefence.Level.Ability.Mob;


import com.darkhouse.gdefence.Level.Ability.Tower.Ability;

public class BossResist extends MobAbility{

    public static class P extends MobAbility.AbilityPrototype{

        public P() {
            super("bossResist", true);
        }

        @Override
        public MobAbility getAbility() {
            return new BossResist(this);
        }

        @Override
        public String getTooltip() {
            return "";
        }
    }

    public BossResist(AbilityPrototype prototype) {
        super(prototype);
    }

    @Override
    public void init() {

    }
}
