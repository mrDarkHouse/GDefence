package com.darkhouse.gdefence.Level.Ability.Tower;


import com.darkhouse.gdefence.Level.Ability.Mob.MobAbility;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Path.MapTile;
import com.darkhouse.gdefence.Level.Tower.Projectile;
import com.darkhouse.gdefence.Level.Tower.Tower;

public abstract class Ability {

    public abstract static class AblityPrototype{
        protected String name;
//        protected boolean isHidden;

        public String getName() {
            return name;
        }
//        public boolean isHidden() {
//            return isHidden;
//        }

        public AblityPrototype(String name) {
            this.name = name;
//            this.isHidden = isHidden;
        }

        abstract public Ability getAbility();
        abstract public String getTooltip();
    }

    public interface IPreAttack{
        void use(Mob target);
    }
    public interface IOnHit {
        int getDmg(Mob target, int startDmg);
    }
    public interface IAfterHit {
        void hit(Mob target, int dmg, Projectile hittingProjectile);
    }
    public interface IOnBuild {
        void builded(MapTile tile);
    }


    public interface IOnKilled {
        void killed(Mob killedMob);
    }


    protected abstract void init();

//    public enum UseType{
//        preattack, onHit, onBuild, onKilled
//
//
////        public
//    }
//    protected UseType useType;
//
//    public UseType getUseType() {
//        return useType;
//    }
    //private int level;




    protected Tower owner;
    private boolean workOnAdditionalProjectiles;

    public boolean isWorkOnAdditionalProjectiles() {
        return workOnAdditionalProjectiles;
    }
    protected void setWorkOnAdditionalProjectiles() {
        this.workOnAdditionalProjectiles = true;
    }

    public void setOwner(Tower owner) {
        this.owner = owner;
        init();
    }

//    public Ability(UseType useType) {
//        //this.owner = owner;
//        this.useType = useType;
//    }

//    public abstract void use(Mob target);//targetly

//    public int getDmg(int dmgStart){
//        return dmgStart;
//    }//only dmg


}
