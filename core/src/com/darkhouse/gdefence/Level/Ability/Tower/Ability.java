package com.darkhouse.gdefence.Level.Ability.Tower;


import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.InventorySystem.inventory.Tooltip.GemGradable;
import com.darkhouse.gdefence.Level.Ability.Spell.EchoSmash;
import com.darkhouse.gdefence.Level.Ability.Spell.GlobalSlow;
import com.darkhouse.gdefence.Level.Ability.Spell.SuddenDeath;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Path.MapTile;
import com.darkhouse.gdefence.Level.Tower.Projectile;
import com.darkhouse.gdefence.Level.Tower.Tower;
import com.darkhouse.gdefence.Model.Level.Map;
import com.darkhouse.gdefence.User;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
        private Class<? extends ITowerAbilityType> abilityType;

        public Class<? extends ITowerAbilityType> getAbilityType() {
//            return abilityType.getClass();
            return abilityType;
        }

        public AbilityPrototype gemCur(int[] gems){
            gemsNumber = gems;
            return this;
        }
        public AbilityPrototype addGems(int[] gems){
            for (int i = 0; i < gems.length; i++){
                for (int a = 0; a < gems[i]; a++){
                    addGem(User.GEM_TYPE.values()[i + 3]);
                }
            }
            return this;
        }
        public abstract Array<Class<? extends AbilityPrototype>> getAbilitiesToSaveOnCraft();

        public int[] getGemCur(){
            return gemsNumber;
        }

        public String getSaveCode(){
            return id + "z" + gemsNumber[0] + ";" + gemsNumber[1] + ";" + gemsNumber[2];
        }

        public static AbilityPrototype loadAbilityCode(String code){
            String[] info = code.split("z");
            int id = Integer.parseInt(info[0]);
            String[] tmp = info[1].split(";");
//            System.out.println(Arrays.toString(tmp));
            int[] gemCur = new int[3];
            int[] gemCap = new int[3];
            for (int i = 0; i < gemCap.length; i++){
                gemCur[i] = Integer.parseInt(tmp[i]);
            }
            for (int i = 3; i < gemCap.length*2; i++){
                gemCap[i - 3] = Integer.parseInt(tmp[i]);
            }

            String[] param = info.length >= 3 ? info[2].split(";") : new String[]{""};//need for empty param abilities like steam aura
//            System.out.println(Arrays.toString(param));
//            System.out.println(param[0] + " " + param[1] + " " + param[2] + " " +param[3] + " " +param[4]);
//            System.out.println(Arrays.toString(param));
//            System.out.println(code);
            switch (id){//TODO do all
                case 0:return new Bash.P(Float.parseFloat(param[0]), Float.parseFloat(param[1]), Integer.parseInt(param[2]),
                              new Bash.G(Float.parseFloat(param[3]), Float.parseFloat(param[4]), Integer.parseInt(param[5]), gemCap)).copy().gemCur(gemCur);
                case 1:return new BuckShot.P(Integer.parseInt(param[0]), Float.parseFloat(param[1]),
                              new BuckShot.G(Integer.parseInt(param[2]), Float.parseFloat(param[3]), gemCap)).copy().gemCur(gemCur);
                case 2:return new Crit.P(Float.parseFloat(param[0]), Float.parseFloat(param[1]),
                              new Crit.G(Float.parseFloat(param[2]), Float.parseFloat(param[3]), gemCap)).copy().gemCur(gemCur);
                case 3:return new Desolate.P(Integer.parseInt(param[0]), Float.parseFloat(param[1]),
                              new Desolate.G(Integer.parseInt(param[2]), Float.parseFloat(param[3]), gemCap)).copy().gemCur(gemCur);
                case 4:return new FireArrow.P(Integer.parseInt(param[0]), Float.parseFloat(param[1]), Float.parseFloat(param[2]),
                              new FireArrow.G(Integer.parseInt(param[3]), Float.parseFloat(param[4]), gemCap)).copy().gemCur(gemCur);
                case 5:return new HunterSpeed.P(Integer.parseInt(param[0]), Integer.parseInt(param[1]), Float.parseFloat(param[2]),
                        new HunterSpeed.G(Integer.parseInt(param[3]), Float.parseFloat(param[4]), Integer.parseInt(param[5]), gemCap)).copy().gemCur(gemCur);


                case 9:return new ShotDelay.P(Float.parseFloat(param[0]), Boolean.parseBoolean(param[1]),
                        new ShotDelay.G(Float.parseFloat(param[2]), gemCap)).copy().gemCur(gemCur);
                case 10:return new Splash.P(Integer.parseInt(param[0]), Float.parseFloat(param[1]),
                        new Splash.G(Integer.parseInt(param[2]), Float.parseFloat(param[3]), gemCap)).copy().gemCur(gemCur);
                case 11:return new SpreadAttack.P(Float.parseFloat(param[0]), Integer.parseInt(param[1]), Float.parseFloat(param[2]),
                        new SpreadAttack.G(Float.parseFloat(param[3]), gemCap)).copy().gemCur(gemCur);
                case 13:return new SteamAura.P().copy();



                case 20:
                    return new EchoSmash.P(Integer.parseInt(param[0]), Float.parseFloat(param[1]), Integer.parseInt(param[3]), Float.parseFloat(param[4]), Integer.parseInt(param[2]),
                            new EchoSmash.G(Integer.parseInt(param[5]), Float.parseFloat(param[6]), Integer.parseInt(param[7]), gemCap))/*.addExp()*/.gemCur(gemCur);
                case 21:
                    return new SuddenDeath.P(Integer.parseInt(param[0]), Float.parseFloat(param[1]), Float.parseFloat(param[2]), Float.parseFloat(param[3]), Float.parseFloat(param[4]),
                            new SuddenDeath.G(Float.parseFloat(param[5]), Float.parseFloat(param[6]), Float.parseFloat(param[7]), gemCap)).gemCur(gemCur);
                case 24:
                    return new GlobalSlow.P(Integer.parseInt(param[0]), Float.parseFloat(param[1]), Float.parseFloat(param[2]), Float.parseFloat(param[3]), Float.parseFloat(param[4]),
                            new GlobalSlow.G(Float.parseFloat(param[5]), Float.parseFloat(param[6]), Float.parseFloat(param[7]),gemCap))/*.copy()*/.gemCur(gemCur);
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
                    case PERCENT: return Float.toString(new BigDecimal(f*100).setScale(2, RoundingMode.HALF_UP).floatValue());//(int)(f*100f) + "%";//
                    case TIME: return Float.toString(new BigDecimal(f).setScale(2, RoundingMode.HALF_UP).floatValue()) + "s";//fix 20.0000001 bug
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
//                System.out.println(boostField + " " + boostUp + " " + new BigDecimal(boostField.get() + boostUp).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
                if(positive) boostField.set(new BigDecimal(boostField.get() + boostUp).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());//TODO 0.1+0.1=0.21 (when ROUND_CEILING)
                else         boostField.set(new BigDecimal(boostField.get() - boostUp).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
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
            return GDefence.getInstance().assetLoader.getWord(name);
        }
        public String getTexturePath() {
            return name;
        }

        public AbilityPrototype(int id, String name, /*String texturePath, */int[] gemsMax,
                                Class<? extends ITowerAbilityType> type) {
            this.id = id;
            this.name = name;
//            this.texturePath = texturePath;
//            this.grader = grader;
//            this.gemsMax = grader.gemCap;
            this.gemsMax = gemsMax;
            this.abilityType = type;
        }

        public boolean canGrade(User.GEM_TYPE t){
            if(gemsNumber[t.ordinal() - 3] + 1 <= gemsMax[t.ordinal() - 3]) return true;
            else return false;
        }
        public boolean isGradable(){
            return !(gemsMax[0] == 0 && gemsMax[1] == 0 && gemsMax[2] == 0);
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
            if(isGradable()) return "{" + gemsNumber[0] + ", " + gemsNumber[1] + ", " + gemsNumber[2] + "}";
            else return "";
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

    public interface ITowerAbilityType extends IAbilityType{}

    public interface IAbilityType{}

    public interface INone extends IAbilityType{}

    public interface IPreShot extends ITowerAbilityType{
        boolean use(Mob target);
    }
    public interface IPreAttack extends ITowerAbilityType{
        boolean use(float delta);
    }
    public interface IPostAttack extends ITowerAbilityType{
        void use(Mob target);
    }
    public interface IOnHit extends ITowerAbilityType{
        int getDmg(Mob target, int startDmg);
    }
    public interface IAfterHit extends ITowerAbilityType{
        void hit(Mob target, int dmg, Projectile hittingProjectile);
    }
    public interface IOnBuild extends ITowerAbilityType{
        void builded(MapTile tile);
    }
    public interface IBuildedOnMap extends ITowerAbilityType{
        void buildedOnMap(Tower builded);
    }
    public interface IOnKilled extends ITowerAbilityType{
        void killed(Mob killedMob);
    }
    public interface IOnGetExp extends ITowerAbilityType{
        float addExp(float exp);
    }

    protected abstract void init(Map map);

    protected Tower owner;
//    protected E abilityType;

    private boolean workOnAdditionalProjectiles;

    public boolean isWorkOnAdditionalProjectiles() {
        return workOnAdditionalProjectiles;
    }
    protected void setWorkOnAdditionalProjectiles() {
        this.workOnAdditionalProjectiles = true;
    }

    public void setOwner(Tower owner, Map map) {
        this.owner = owner;
        init(map);
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
