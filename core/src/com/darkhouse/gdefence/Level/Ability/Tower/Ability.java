package com.darkhouse.gdefence.Level.Ability.Tower;


import com.darkhouse.gdefence.InventorySystem.inventory.Tooltip.GemGradable;
import com.darkhouse.gdefence.Level.Ability.Spell.GlobalSlow;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Path.MapTile;
import com.darkhouse.gdefence.Level.Tower.Projectile;
import com.darkhouse.gdefence.Level.Tower.Tower;
import com.darkhouse.gdefence.User;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicReference;

public abstract class Ability {

    public abstract static class AbilityPrototype implements GemGradable{
//        protected AbilityGrader grader;
        protected int id;
        protected String name;
        protected String texturePath;
        protected int[] gemsNumber = new int[3];
        protected int[] gemsMax = new int[3];
        public Boost[] gemBoost = new Boost[3];

        public AbilityPrototype gemCur(int[] gems){
            gemsNumber = gems;
            return this;
        }

        public String getSaveCode(){
            return id + "z" + gemsNumber[0] + ";" + gemsNumber[1] + ";" + gemsNumber[2] + ";" + gemsMax[0] + ";" + gemsMax[1] + ";" + gemsMax[2];
        }
        public static AbilityPrototype loadAbilityCode(String code){
            String[] info = code.split("z");
            int id = Integer.parseInt(info[0]);
            String[] tmp = info[1].split(";");
            int[] gemCur = new int[3];
            int[] gemCap = new int[3];
            for (int i = 0; i < gemCap.length; i++){
                gemCur[i] = Integer.parseInt(tmp[i]);
            }
            for (int i = 3; i < gemCap.length*2; i++){
                gemCap[i - 3] = Integer.parseInt(tmp[i]);
            }
            String[] param = info[2].split(";");
//            System.out.println(param[0] + " " + param[1] + " " + param[2] + " " +param[3] + " " +param[4]);

            switch (id){//TODO do all
                case 0:
                return new Bash.P(Float.parseFloat(param[0]), Float.parseFloat(param[1]), Integer.parseInt(param[2]),
                        new Bash.G(Float.parseFloat(param[3]), Float.parseFloat(param[4]), Integer.parseInt(param[5]), gemCap)).copy().gemCur(gemCur);
                case 20:
                    return new GlobalSlow.P(Integer.parseInt(param[0]), Integer.parseInt(param[1]), Float.parseFloat(param[2]), Integer.parseInt(param[3]),
                            new GlobalSlow.G(Float.parseFloat(param[4]), Integer.parseInt(param[5]), gemCap))/*.copy()*/.gemCur(gemCur);
//                case 21:
//                  return
                default:return null;
            }

        }

        public int getGemsNumber(){
            return gemsNumber[0] + gemsNumber[1] + gemsNumber[2];
        }



        public static abstract class Boost{
//            public static Boost createInstance(AtomicReference boostField, Number boostUp, String name, boolean positive){
//                if(boostField.get() instanceof Integer){//boostUp also
//                    return new BoostInteger(((AtomicReference<Integer>) boostField), boostUp.intValue(), name, positive);
//                }else if(boostField.get() instanceof Float){
//                    return new BoostFloat(boostField.floatValue(), boostUp.floatValue(), name, positive);
//                }
//                return null;
//            }



            protected String name;
            protected boolean positive;

            abstract public String boostField();
            abstract public String boostUp();
            abstract public String concate();
            abstract public void grade();


            public Boost(String name, boolean positive) {
                this.name = name;
                this.positive = positive;
            }
        }
        public static class BoostInteger extends Boost{
            private AtomicReference<Integer> boostField;
            private int boostUp;
            private IntegerGradeFieldType type;
            private float[] params;

            public enum IntegerGradeFieldType {
                NONE, DPS
            }

            private String configurate(int i){
                switch (type){
                    case NONE: return i + "";
                    case DPS: return i*(1/params[0]) + "";
                    default: return "error";
                }
            }

            public String boostField() {
                return configurate(boostField.get());
            }
            public String boostUp() {
                return configurate(boostUp);
            }
            public String concate() {
                if(positive) return configurate(boostField.get() + boostUp);
                else         return configurate(boostField.get() - boostUp);
            }
            public void grade() {
                if(positive) boostField.set(boostField.get() + boostUp);
                else         boostField.set(boostField.get() - boostUp);
            }

            public BoostInteger(AtomicReference<Integer> boostField, int boostUp, String name, boolean positive, IntegerGradeFieldType type, float... params) {
                super(name, positive);
                this.boostField = boostField;
                this.boostUp = boostUp;
                this.type = type;
                this.params = params;
            }
        }
        public static class BoostFloat extends Boost{
            private AtomicReference<Float> boostField;
            private float boostUp;
            protected FloatGradeFieldType type;

            public enum FloatGradeFieldType {
                NONE, PERCENT, TIME, MULTIPLAYER, ANGLE
            }

            private String configurate(float f){
                switch (type){
                    case PERCENT: return f*100 + "%";
                    case TIME: return f + "s";
                    case MULTIPLAYER: return f + "x";
                    case ANGLE: return ((int) f) + "*";
                    case NONE: return f + "";
                    default: return "error";
                }
            }

            public String boostField() {
                return configurate(boostField.get());
            }
            public String boostUp() {
                return configurate(boostUp);
            }
            public String concate() {
                if(positive) return configurate(boostField.get() + boostUp);
                else return configurate(boostField.get() - boostUp);
            }
            public void grade() {
                if(positive) boostField.set(new BigDecimal(boostField.get() + boostUp).setScale(2, BigDecimal.ROUND_FLOOR).floatValue());
                else         boostField.set(new BigDecimal(boostField.get() - boostUp).setScale(2, BigDecimal.ROUND_FLOOR).floatValue());
            }

            public BoostFloat(AtomicReference<Float> boostField, float boostUp, String name, boolean positive, FloatGradeFieldType type) {
                super(name, positive);
                this.boostField = boostField;
                this.boostUp = boostUp;
                this.type = type;
            }
        }


        abstract public AbilityPrototype copy();

        public String getName() {
            return name;
        }
        public String getTexturePath() {
            return texturePath;
        }

        public AbilityPrototype(int id, String name, String texturePath, int[] gemsMax) {
            this.id = id;
            this.name = name;
            this.texturePath = texturePath;
//            this.grader = grader;
//            this.gemsMax = grader.gemCap;
            this.gemsMax = gemsMax;
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
        @Override
        public String getGemGradeTooltip(User.GEM_TYPE gemType) {
            String s = "";
            Boost b = gemBoost[gemType.ordinal() - 3];
            if(b == null || gemsMax[gemType.ordinal() - 3] == 0) return "Cant grade this gem";
            if(/*canGrade(gemType)*/gemsNumber[gemType.ordinal() - 3] + 1 <= gemsMax[gemType.ordinal() - 3]) {//it take override version so it's better
                if (b.positive) s += "+ ";
                else s += "- ";
                s += b.boostUp() + " " + b.name + System.getProperty("line.separator") +
                        "(" + b.boostField() + "=>" + (b.concate()) + ")";
            }else {
                s += "MAX" + System.getProperty("line.separator") +
                        "(" + b.boostField() + ")";
            }
            return s;

//            if(canGrade(gemType)) {
//                return "+ " + getBoostValue(gemType) + " " + getBoostName(gemType) + System.getProperty("line.separator") +
//                        "(" + getCurrentValue(gemType) + "=>" + getBoostedValue(gemType) + ")";
//            }else {
//                return "MAX" + System.getProperty("line.separator") +
//                        "(" + getCurrentValue(gemType) + ")";
//            }
        }
        protected boolean grade(User.GEM_TYPE t){
            if(gemBoost[t.ordinal() - 3] != null) {
                gemBoost[t.ordinal() - 3].grade();
                return true;
            }else return false;
        }
        public String getGemStat(){
            return "{" + gemsNumber[0] + ", " + gemsNumber[1] + ", " + gemsNumber[2] + "}";
        }

        abstract public Ability getAbility();
        abstract public String getTooltip();
    }

    public abstract static class AbilityGrader{
        public int[] gemCap = new int[3];
        public AbilityGrader(int[] gemCap) {
            this.gemCap = gemCap;
        }
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
