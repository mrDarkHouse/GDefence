package com.darkhouse.gdefence.Level.Ability.Tower;


import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.Level.Ability.Tools.Cooldown;
import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Path.MapTile;
import com.darkhouse.gdefence.Level.Tower.Tower;

import java.util.concurrent.atomic.AtomicReference;

public class SpreadAttack extends Ability implements Ability.IOnBuild{

    public static class P extends AbilityPrototype {
        private G g;
        private AtomicReference<Float> cdCap;
        private int shots;

        public P(float cdCap, int bonusAttacks, G grader) {
            super(11, "spreadAttack", grader.gemCap);
            this.cdCap = new AtomicReference<Float>(cdCap);
            this.shots = bonusAttacks;
            this.g = grader;
        }

        @Override
        public String getSaveCode() {
            return super.getSaveCode() + "z" + cdCap.get() + ";" + shots + ";" + g.cdCapDown;
        }

        @Override
        public AbilityPrototype copy() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            P p = new P(cdCap.get(), shots, g);
            p.gemBoost[0] = new BoostFloat(p.cdCap, g.cdCapDown, l.getWord("spreadAttackGrade1"),
                    false, BoostFloat.FloatGradeFieldType.TIME);

            return p;
        }

        @Override
        public Ability getAbility() {
            return new SpreadAttack(this);
        }

        @Override
        public String getTooltip() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            return l.getWord("spreadAttackTooltip1") + " " + shots + " " +
                    l.getWord("spreadAttackTooltip2") + System.getProperty("line.separator") +
                    l.getWord("spreadAttackTooltip3") + " " + FontLoader.colorString(cdCap.get().toString(), 0) + " " +
                    l.getWord("spreadAttackTooltip4");
        }


    }
    public static class G extends AbilityGrader{
        private float cdCapDown;

        public G(float cdCapDown,  int[] gemMax) {
            super(gemMax);
            this.cdCapDown = cdCapDown;
        }
    }

    private class DoubleShotEffect extends Effect<Tower> implements IPostAttack {
//        private float delay = 0.2f;
//        private float currentDelayTime = -1;
        private int shots;

//        private Mob target;

        public DoubleShotEffect(float cdCap, int shots) {
            super(true, false, -1, "bonusArmor");
            setCooldownable(new Cooldown(cdCap));
            this.shots = shots;
        }

        @Override
        public void apply() {

        }

        @Override
        public void act(float delta) {
            super.act(delta);
//            if(currentDelayTime == -1) return;
//            else if(currentDelayTime < delay) currentDelayTime += delta;
//            else if(currentDelayTime > delay) currentDelayTime = delay;
//
//            if(currentDelayTime == delay){
//                Projectile p = new Projectile(owner, owner.getCenter(), target, false);//may be true
//                Map.projectiles.add(p);
//                owner.shotProjectile();
//                lessShots--;
//                if(lessShots == 0) {
//                    currentDelayTime = -1;
//                    getCooldownObject().resetCooldown();
//                }else currentDelayTime = 0;



//            }
        }

//        @Override
//        public void hit(Mob target, int dmg, Projectile hittingProjectile) {
//            System.out.println(currentDelayTime);
//            if (getCooldownObject().isReady()) {
//                this.target = target;
//                currentDelayTime = 0;
////                getCooldownObject().resetCooldown();
//            }
//        }

//        @Override
//        public int getDmg(Mob target, int startDmg) {
//            if (getCooldownObject().isReady()) {
//                this.target = target;
//                currentDelayTime = 0;
//            }
//            return startDmg;
//        }

        @Override
        public void use(Mob target) {//if before attack (must implement IPreAttack)
            if (getCooldownObject().isReady()) {
                owner.addEffect(new TargetShooter(target, shots).setOwner(owner));
                getCooldownObject().resetCooldown();
//                this.target = target;
//                currentDelayTime = 0;
            }
        }
    }
    private class TargetShooter extends Effect<Tower>{//this need to work for each target
        private float delay = 0.1f;
        private float currentDelayTime = 0;

        private Mob target;
        private int lessShots;

        public TargetShooter(Mob target, int shots) {
            super(true, false, -1, "swimSpeed");
            this.target = target;
            lessShots = shots;
        }

        @Override
        public void act(float delta) {
//            super.act(delta);

//            if(currentDelayTime == -1) return;
            if(currentDelayTime < delay) currentDelayTime += delta;
            else if(currentDelayTime > delay) currentDelayTime = delay;

            if(currentDelayTime == delay) {
                owner.shotProjectile(target, false);
                lessShots--;
                if(lessShots == 0)dispell();
                currentDelayTime = 0;
            }
        }

        @Override
        public void apply() {

        }
    }



    private DoubleShotEffect buff;



    public SpreadAttack(P prototype) {
        buff = new DoubleShotEffect(prototype.cdCap.get(), prototype.shots);
    }

    @Override
    public void init() {
        buff.setOwner(owner);
    }

    @Override
    public void builded(MapTile tile) {
        owner.addEffect(buff);
    }



}
