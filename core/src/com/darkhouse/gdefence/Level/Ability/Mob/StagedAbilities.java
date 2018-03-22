package com.darkhouse.gdefence.Level.Ability.Mob;


import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.Level.Ability.Tools.DamageType;
import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Ability.Tower.Ability;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Objects.DamageSource;
import com.darkhouse.gdefence.Screens.LevelMap;

public class StagedAbilities extends MobAbility implements MobAbility.ISpawn{

    public static class P extends MobAbility.AbilityPrototype{

        private MobAbility.AbilityPrototype[][] abilities;
        private int stages;
        private String[] mobModelPath;
        private float[] healthTreshold;
        private int energyBonus;

        public P(int stages, int energyBonus, String[] mobModelPath, float[] healthTreshold, MobAbility.AbilityPrototype[][] abilities) {
            super("stagedAbilities", false, ISpawn.class);
            this.stages = stages;
            this.energyBonus = energyBonus;
            this.mobModelPath = mobModelPath;
            this.healthTreshold = healthTreshold;
            this.abilities = abilities;

        }


        @Override
        public MobAbility getAbility() {
            return new StagedAbilities(this);
        }

        @Override
        public String getTooltip() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            return l.getWord("stagedAbilitiesTooltip1") + System.getProperty("line.separator") +
                   l.getWord("stagedAbilitiesTooltip2") + " " + FontLoader.colorString(Integer.toString(energyBonus), 9) + " " +
                   l.getWord("stagedAbilitiesTooltip3");
        }
    }

    private int stages;
    private MobAbility.AbilityPrototype[][] abilities;
    private String[] mobModelPath;
    private float[] healthTreshold;
    private int curentStage;
    private int energyBonus;

    public int getCurentStage() {
        return curentStage;
    }

    private StageChecker stageChecker;

    public class StageChecker extends Effect<Mob> implements IAfterGetDmg/*, IStrong*/{

        public StageChecker() {
            super(true, false, -1, IAfterGetDmg.class);
//            setStrong();
        }

        public int getStage(){
            return curentStage + 1;
        }

        @Override
        public void apply() {

        }

        @Override
        public void afterGetDmg() {
            if(curentStage < healthTreshold.length && checkStage(curentStage)) moveStage();
            LevelMap.levelMap.getBossStatusPanel().hasChanged();
            LevelMap.levelMap.updateHealth();
//            System.out.println(getStage() + " " + LevelMap.levelMap.getBossStatusPanel());
        }

//        @Override
//        public float getDmg(DamageSource source, DamageType type, float dmg) {
//            LevelMap.levelMap.getBossStatusPanel().hasChanged();
//            if(curentStage < healthTreshold.length && checkStage(curentStage, dmg)) moveStage();
//            return dmg;
//        }
    }

    public StagedAbilities(P prototype) {
        super(prototype);
        this.stages = prototype.stages;
        this.abilities = prototype.abilities;
        this.mobModelPath = prototype.mobModelPath;
        this.healthTreshold = prototype.healthTreshold;
        this.energyBonus = prototype.energyBonus;
        stageChecker = new StageChecker();

//        System.out.println(owner.getAbilities());

    }



    private boolean checkStage(int stage/*, float dmg*/){
//        System.out.println(owner.getHealth() + " " + owner.getMaxHealth() * healthTreshold[stage]);
        return owner.getHealth()/* - dmg */<= owner.getMaxHealth() * healthTreshold[stage];
    }
    private void moveStage(){
        curentStage++;
        owner.removeBuffsListeners();
//        System.out.println("moved " + curentStage);
        owner.setAbilities(abilities[curentStage]);
        for (MobAbility a:owner.getAbilities()){
            a.setOwner(getOwner());
            if(a instanceof ISpawn){
                ((ISpawn) a).spawned();
            }
        }


        owner.initTextures(mobModelPath[curentStage]);
        owner.setWay(owner.getWay());

        LevelMap.getLevel().addEnergy(energyBonus);
//        System.out.println("abi " + owner.getAbilities());
//        owner.setRegion(GDefence.getInstance().assetLoader.get("Mobs/" + ));
    }


    @Override
    public void init() {
        stageChecker.setOwner(owner);
        owner.addEffect(stageChecker);

        owner.setAbilities(abilities[0]);
        for (MobAbility a:owner.getAbilities()){
            a.setOwner(getOwner());
        }
//        System.out.println(LevelMap.getLevel().getCurrentWave().getBoss().getBonusArmor());
//        System.out.println(owner.getAbilities());
//        for ()
    }
    @Override
    public void spawned() {

    }




}
