package com.darkhouse.gdefence.Level.Ability.Tower;


import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.Level.Ability.Tools.Cooldown;
import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Tower.Projectile;
import com.darkhouse.gdefence.Level.Tower.Tower;

import java.util.concurrent.atomic.AtomicReference;

public class FireArrow extends Ability implements Ability.IAfterHit{

    public static class P extends AbilityPrototype {
        private G grader;
        private AtomicReference<Integer> damage;
        private float delay;
        private AtomicReference<Float> duration;

        public P(int damage, float delay, float duration, G grader) {
            super(4, "fireArrow", grader.gemCap);
            this.damage = new AtomicReference<Integer>(damage);
            this.delay = delay;
            this.duration = new AtomicReference<Float>(duration);
            this.grader = grader;
        }

        @Override
        public String getSaveCode() {
            return null;
        }

        @Override
        public AbilityPrototype copy() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            P p = new P(damage.get(), delay, duration.get(), grader);
            p.gemBoost[0] = new BoostInteger(p.damage, grader.dmgUp, l.getWord("fireArrowGrade1"),
                    true, BoostInteger.IntegerGradeFieldType.DPS, delay);
            p.gemBoost[1] = new BoostFloat(p.duration, grader.durationUp, l.getWord("fireArrowGrade2"),
                    true, BoostFloat.FloatGradeFieldType.TIME);
            return p;
        }

        @Override
        public Ability getAbility() {
            return new FireArrow(this);
        }

        @Override
        public String getTooltip() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            return l.getWord("fireArrowTooltip1") + System.getProperty("line.separator") +
                   l.getWord("fireArrowTooltip2") + " " + FontLoader.colorCode(0) + (int)(damage.get()*(1/delay)) + "[] " +
                   l.getWord("fireArrowTooltip3") + " " + FontLoader.colorCode(1) + duration + "[] " +
                   l.getWord("fireArrowTooltip4");
        }
    }
    public static class G extends AbilityGrader{
        private int dmgUp;
        private float durationUp;

        public G(int dmgUp, float durationUp, int[] gemCap) {
            super(gemCap);
            this.dmgUp = dmgUp;
            this.durationUp = durationUp;
        }
    }

    private class FireEffect extends Effect<Mob>{
        private int damage;
        private Tower source;

        public FireEffect(int damage, float delay, float duration, Tower source) {
            super(false, true, duration, "fireArrow");
            setCooldownable(new Cooldown(delay));
            getCooldownObject().setHidden();
            this.damage = damage;
            this.source = source;
        }

        @Override
        public void apply() {

        }

        @Override
        public void act(float delta) {
            super.act(delta);

            if(getCooldownObject().isReady()){
                owner.hit(damage, source);
                getCooldownObject().resetCooldown();
            }

        }
    }

    private int damage;
    private float delay;
    private float duration;


    public FireArrow(P prototype) {
        this.damage = prototype.damage.get();
        this.delay = prototype.delay;
        this.duration = prototype.duration.get();
    }

    @Override
    protected void init() {

    }

    @Override
    public void hit(Mob target, int dmg, Projectile hittingProjectile) {
        target.addEffect(new FireEffect(damage, delay, duration, owner).setOwner(target));
    }
}
