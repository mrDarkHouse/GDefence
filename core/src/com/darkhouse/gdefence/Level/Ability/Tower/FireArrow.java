package com.darkhouse.gdefence.Level.Ability.Tower;


import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.Level.Ability.Tools.Cooldown;
import com.darkhouse.gdefence.Level.Ability.Tools.DamageType;
import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Tower.Projectile;
import com.darkhouse.gdefence.Level.Tower.Tower;
import com.darkhouse.gdefence.Model.Level.Map;
import com.darkhouse.gdefence.User;

import java.util.concurrent.atomic.AtomicReference;

public class FireArrow extends Ability implements Ability.IAfterHit{

    public static class P extends AbilityPrototype {
        private AtomicReference<Integer> damage;
        private float delay;
        private AtomicReference<Float> duration;
        private G g;
        private S s;

        public P(int damage, float delay, float duration, G grader) {
            super(4, "fireArrow", grader.gemCap, IAfterHit.class);
            this.damage = new AtomicReference<Integer>(damage);
            this.delay = delay;
            this.duration = new AtomicReference<Float>(duration);
            this.g = grader;
            this.s = new S(damage, duration);
        }

        @Override
        public Array<Class<? extends AbilityPrototype>> getAbilitiesToSaveOnCraft() {
            return new Array<Class<? extends AbilityPrototype>>();
        }

        @Override
        public AbilityPrototype copy() {
            P p = new P(damage.get(), delay, duration.get(), g);
            p.initBoosts(GDefence.getInstance().assetLoader);
            p.s = s;
            return p;
        }

        @Override
        public void flush() {
            damage = new AtomicReference<Integer>(s.damage);
            duration = new AtomicReference<Float>(s.duration);
            initBoosts(GDefence.getInstance().assetLoader);
        }

        @Override
        protected void initBoosts(AssetLoader l) {
            gemBoost[0] = new BoostInteger(damage, g.dmgUp, l.getWord("fireArrowGrade1"),
                    true, BoostInteger.IntegerGradeFieldType.DPS, delay);
            gemBoost[1] = new BoostFloat(duration, g.durationUp, l.getWord("fireArrowGrade2"),
                    true, BoostFloat.FloatGradeFieldType.TIME);
        }

        @Override
        public Ability getAbility() {
            return new FireArrow(this);
        }

        @Override
        public String getTooltip() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            return l.getWord("fireArrowTooltip1") + System.getProperty("line.separator") +
                   l.getWord("fireArrowTooltip2") + " " + FontLoader.colorString((int)(damage.get()*(1/delay)) + "", User.GEM_TYPE.BLACK) + " " +
                   l.getWord("fireArrowTooltip3") + " " + FontLoader.colorString(duration.get().toString(), User.GEM_TYPE.GREEN) + " " +
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
    private static class S extends AbilitySaverStat{
        private int damage;
        private float duration;

        public S(int damage, float duration) {
            this.damage = damage;
            this.duration = duration;
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
                owner.hit(damage, DamageType.Magic, source);
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
    protected void init(Map map) {

    }

    @Override
    public void hit(Mob target, int dmg, Projectile hittingProjectile) {
        target.addEffect(new FireEffect(damage, delay, duration, owner).setOwner(target));
    }
}
