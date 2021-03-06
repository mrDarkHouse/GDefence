package com.darkhouse.gdefence.Level.Ability.Mob;


import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.Level.Ability.Tools.DamageType;
import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Ability.Tools.Stackable;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Tower.Tower;
import com.darkhouse.gdefence.Objects.DamageSource;

public class Sadist extends MobAbility implements MobAbility.ISpawn{
    private class SadistBuff extends Effect<Mob> implements IGetDmg/*, IStrong, IListener*/{
        private int healEmount;

        public SadistBuff(int attacksNeed, int healEmount) {
            super(true, true, -1, "recovery", IGetDmg.class);
            this.healEmount = healEmount;
            setStackable(new Stackable(attacksNeed));
            setStrong();
            setListener();
        }

        @Override
        public void apply() {

        }

        @Override
        public float getDmg(DamageSource source, DamageType type, float dmg) {
            getStackableObject().addStack();
            if(getStackableObject().isMaxStacks()){
                getStackableObject().setCurrentStacks(0);
                owner.heal(healEmount);
                owner.dispellDebuffs();
            }
            return dmg;
        }
    }
    public static class P extends AbilityPrototype {
        private int attacksNeed;
        private int healEmount;

        public P(int attackNeed, int healEmount) {
            super("sadist", false, ISpawn.class);
            this.attacksNeed = attackNeed;
            this.healEmount = healEmount;
        }
        public MobAbility getAbility(){
            return new Sadist(this);
        }

        @Override
        public String getTooltip() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            return l.getWord("sadistTooltip1") + " " + FontLoader.colorString(Integer.toString(attacksNeed), 10) + " " +
                    l.getWord("sadistTooltip2")  +  System.getProperty("line.separator") +
                    l.getWord("sadistTooltip3") + " " + FontLoader.colorString(Integer.toString(healEmount), 10) + " " +
                    l.getWord("sadistTooltip4");
        }
    }

    private SadistBuff effect;

    public Sadist(P prototype) {
        super(prototype);
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
