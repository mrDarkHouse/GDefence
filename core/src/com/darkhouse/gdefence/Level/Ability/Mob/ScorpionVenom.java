package com.darkhouse.gdefence.Level.Ability.Mob;


import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.Level.Ability.Tools.DamageType;
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
            super(true, true, -1, IGetDmg.class);
            this.duration = duration;
            this.asSlow = asSlow;
        }

        @Override
        public void apply() {

        }

        @Override
        public float getDmg(DamageSource source, DamageType type, float dmg) {
            if (source instanceof Tower) {//tower
                Tower t = ((Tower) source);
                t.addEffect(new ScorpionVenomEffect(duration, asSlow).setOwner(t));
            }
            return dmg;
        }
    }
    private class ScorpionVenomEffect extends Effect<Tower>{
        private int asSlow;
        private int currentSlow;

        public ScorpionVenomEffect(float duration, int asSlow) {
            super(false, true, duration, "scorpionVenom");
            this.asSlow = asSlow;
        }

        @Override
        public void apply() {
            currentSlow = owner.changeAttackSpeed(-asSlow);
//            System.out.println(currentSlow);//TODO double sout
        }

        @Override
        public void dispell() {
            owner.changeAttackSpeed(-currentSlow);
            super.dispell();
        }
    }

    public static class P extends AbilityPrototype {
        private int asSlow;
        private float duration;

        public P(int asSlow, float duration) {
            super("scorpionVenom", false, ISpawn.class);
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
            return l.getWord("scorpionVenomTooltip1") + " " + FontLoader.colorString(Integer.toString(asSlow), 3) + " " + System.getProperty("line.separator") +
                    l.getWord("scorpionVenomTooltip2") + " " + FontLoader.colorString(Float.toString(duration), 3) + " " +
                    l.getWord("scorpionVenomTooltip3");
        }
    }

    private ScorpionVenomBuff effect;

    public ScorpionVenom(P prototype) {
        super(prototype);
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
