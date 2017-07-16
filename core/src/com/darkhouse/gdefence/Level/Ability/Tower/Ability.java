package com.darkhouse.gdefence.Level.Ability.Tower;


import com.darkhouse.gdefence.Level.Ability.Mob.MobAbility;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Path.MapTile;
import com.darkhouse.gdefence.Level.Tower.Projectile;
import com.darkhouse.gdefence.Level.Tower.Tower;
import com.darkhouse.gdefence.User;

public abstract class Ability {

    public abstract static class AblityPrototype{
        protected AbilityGrader grader;
        protected String name;
        protected int[] gemsNumber = new int[3];
        protected int[] gemsMax = new int[3];

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

        public boolean canGrade(User.GEM_TYPE t){
            if(gemsNumber[t.ordinal() - 3] + 1 <= gemsMax[t.ordinal() - 3]) return true;
            else return false;
        }

        public void addGem(User.GEM_TYPE t){
            if(canGrade(t)){
                gemsNumber[t.ordinal() - 3]++;
                grade(t);
            }
        }

        protected boolean grade(User.GEM_TYPE t){return true;}

        public String getGemStat(){
            return "{" + gemsNumber[0] + ", " + gemsNumber[1] + ", " + gemsNumber[2] + "}";
        }

        abstract public Ability getAbility();
        abstract public String getTooltip();
    }

    public abstract static class AbilityGrader{



    }

    public interface IPreShot {
        boolean use(Mob target);
    }
    public interface IPreAttack{
        boolean use(float delta);
    }
    public interface IPostAttack{
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
