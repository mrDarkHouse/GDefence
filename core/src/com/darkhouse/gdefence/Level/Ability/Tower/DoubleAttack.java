package com.darkhouse.gdefence.Level.Ability.Tower;


import com.darkhouse.gdefence.Level.Ability.Tools.Cooldown;
import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Path.MapTile;
import com.darkhouse.gdefence.Level.Tower.Projectile;
import com.darkhouse.gdefence.Level.Tower.Tower;
import com.darkhouse.gdefence.Model.Level.Map;

public class DoubleAttack extends Ability implements Ability.IOnBuild{

    public static class P extends Ability.AblityPrototype{
        private float cdCap;
        private int shots;

        public P(float cdCap, int bonusAttacks) {
            super("Spread Attack");
            this.cdCap = cdCap;
            this.shots = bonusAttacks;
        }

        @Override
        public Ability getAbility() {
            return new DoubleAttack(this);
        }

        @Override
        public String getTooltip() {
            return "Shot second projectile every " + cdCap + " seconds";
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
//                this.target = target;
//                currentDelayTime = 0;
            }
        }
    }
    private class TargetShooter extends Effect<Tower>{//this need to work for each target
        private float delay = 0.2f;
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

    public DoubleAttack(P prototype) {
        buff = new DoubleShotEffect(prototype.cdCap, prototype.shots);
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
