package com.darkhouse.gdefence.Level.Ability.Mob;


import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Ability.Tools.Stackable;
import com.darkhouse.gdefence.Level.Ability.Tower.Ability;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Tower.Projectile;
import com.darkhouse.gdefence.Level.Tower.Tower;
import com.darkhouse.gdefence.Model.Effectable;
import com.darkhouse.gdefence.Objects.DamageSource;

public class ScorpionVenom extends MobAbility implements MobAbility.ISpawn{


    private class ScorpionVenomBuff extends Effect<Mob> implements IGetDmg{
        private int asSlow;
        private float duration;

        public ScorpionVenomBuff(float duration, int asSlow) {
            super(true, true, -1, "bonusArmor");
            this.duration = duration;
            this.asSlow = asSlow;
        }

        @Override
        public void apply() {

        }

        @Override
        public float getDmg(DamageSource source, float dmg) {
            if (source instanceof Tower) {//tower
                Tower t = ((Tower) source);
                t.addEffect(new ScorpionVenomEffect(duration, asSlow).setOwner(t));
            }
            return dmg;
        }
    }
    private class ScorpionVenomEffect extends Effect<Tower>{
        private int asSlow;

        public ScorpionVenomEffect(float duration, int asSlow) {
            super(false, true, duration, "slow");
            this.asSlow = asSlow;
        }

        @Override
        public void apply() {
            owner.changeAttackSpeed(-asSlow);
        }

        @Override
        public void dispell() {
            owner.changeAttackSpeed(asSlow);
            super.dispell();
        }
    }

    public static class P extends AbilityPrototype {
        private int asSlow;
        private float duration;

        public P(int asSlow, float duration) {
            super("scorpionVenom", false);
            this.asSlow = asSlow;
            this.duration = duration;
        }

        @Override
        public MobAbility getAbility() {
            return new ScorpionVenom(this);
        }

        @Override
        public String getTooltip() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            return l.getWord("scorpionVenomTooltip1") + " " + FontLoader.colorString(Integer.toString(asSlow), 3) + "" +
                    l.getWord("scorpionVenomTooltip2") + " " + System.getProperty("line.separator") +
                    l.getWord("scorpionVenomTooltip3");
        }
    }

    private ScorpionVenomBuff effect;

    public ScorpionVenom(P prototype) {
        effect = new ScorpionVenomBuff(prototype.duration, prototype.asSlow);
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
