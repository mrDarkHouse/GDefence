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

        public P(float cdCap) {
            super("Double Attack");
            this.cdCap = cdCap;
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
    private class DoubleAttackEffect extends Effect<Tower> implements IPreAttack{
        private float delay = 0.2f;
        private float currentDelayTime = -1;

        private Mob target;

        public DoubleAttackEffect(float cdCap) {
            super(true, false, -1, "bonusArmor");
            setCooldownable(new Cooldown(cdCap));
        }

        @Override
        public void apply() {

        }

        @Override
        public void act(float delta) {
            super.act(delta);
            if(currentDelayTime == -1) return;
            else if(currentDelayTime < delay) currentDelayTime += delta;
            else if(currentDelayTime > delay) currentDelayTime = delay;

            if(currentDelayTime == delay){
                Projectile p = new Projectile(owner, owner.getCenter(), target, false);//may be true
                Map.projectiles.add(p);
                currentDelayTime = -1;
                getCooldownObject().resetCooldown();
            }
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
                this.target = target;
                currentDelayTime = 0;
            }
        }
    }


    private DoubleAttackEffect buff;

    public DoubleAttack(P prototype) {
        buff = new DoubleAttackEffect(prototype.cdCap);
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
