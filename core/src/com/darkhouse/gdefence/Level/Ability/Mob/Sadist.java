package com.darkhouse.gdefence.Level.Ability.Mob;


import com.darkhouse.gdefence.Level.Ability.Mob.Tools.Effect;
import com.darkhouse.gdefence.Level.Ability.Mob.Tools.Stackable;
import com.darkhouse.gdefence.Level.Tower.Tower;

public class Sadist extends MobAbility implements MobAbility.ISpawn{
    private class SadistBuff extends Effect implements IGetDmg{
        private int healEmount;

        public SadistBuff(int attacksNeed, int healEmount) {
            super(true, true, -1, "bonusArmor");
            this.healEmount = healEmount;
            setStackable(new Stackable(attacksNeed));
        }

        @Override
        public void apply() {

        }

        @Override
        public float getDmg(Tower source, float dmg) {
            getStackableObject().addStack();
            if(getStackableObject().isMaxStacks()){
                getStackableObject().setCurrentStacks(0);
                owner.heal(healEmount);
                owner.dispellDebuffs();
            }
            return dmg;
        }
    }
    public static class P extends MobAbility.AblityPrototype {
        private int attacksNeed;
        private int healEmount;

        public P(int attackNeed, int healEmount) {
            super("Sadist", false);
            this.attacksNeed = attackNeed;
            this.healEmount = healEmount;
        }
        public MobAbility getAbility(){
            return new Sadist(this);
        }

        @Override
        public String getTooltip() {
            return "After getting [#64A619ff]" + attacksNeed + "[] attacks "  +  System.getProperty("line.separator") +
                    "heal for [#64A619ff]" + healEmount + "[] hp and dispell all debuffs";
        }
    }

    private SadistBuff effect;

    public Sadist(P prototype) {
        effect = new SadistBuff(prototype.attacksNeed, prototype.healEmount);
    }

    @Override
    public void init() {
        effect.setOwner(owner);
    }

    @Override
    public void spawned() {
        owner.addEffect(effect);
    }
}
