package com.darkhouse.gdefence.Level;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.StatManager;
import com.darkhouse.gdefence.InventorySystem.inventory.DropSlot;
import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;
import com.darkhouse.gdefence.Level.Loader.MapLoader;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Model.Level.Map;
import com.darkhouse.gdefence.Screens.LevelEndScreen;
import com.darkhouse.gdefence.Screens.LevelMap;

import java.util.*;

public class Level {
    private boolean isPaused = true;

    private int expFromLvl;
    private int goldFromLvl;
    private int startEnergy;
    private int maxEnergy;
    private int startHP;
    private int maxHP;
    private String dropList;
    private String penaltyDropList;

    private Map.MapType type;

    public Map.MapType getType() {
        return type;
    }


    private int mobLimit;//for non classic types
    private float timeLimit;
    private float roundTime = 0f;

    public float getRoundTime() {
        return roundTime;
    }

    public int getMobLimit() {
        return mobLimit;
    }
    public float getTimeLimit() {
        return timeLimit;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }
    public int getMaxHP() {
        return maxHP;
    }

    public int getStartEnergy() {
        return startEnergy;
    }
    public int getStartHP() {
        return startHP;
    }

    private boolean inWave = false;

    public boolean isInWave() {
        return inWave;
    }

    private void setInWave(boolean inWave) {
        this.inWave = inWave;
        if(inWave)waveStartEvent();
        else waveEndEvent();
    }

    public int getHealthNumber() {
        return healthNumber;
    }

    public int getEnergyNumber() {
        return energyNumber;
    }

    public boolean haveEnergy(int energy){
        return energy <= energyNumber;
    }

    public void addEnergy(int energy) {
        if(energy + energyNumber <= GDefence.getInstance().user.maxEnergy.getCurrentValue()) {
            this.energyNumber += energy;
        }else {
            if(this.energyNumber != GDefence.getInstance().user.maxEnergy.getCurrentValue()) {
                this.energyNumber = GDefence.getInstance().user.maxEnergy.getCurrentValue();
            }
        }
        ownerScreen.energyChangeEvent();
    }
    public boolean removeEnergy(int energy) {
        if(haveEnergy(energy)) {
            this.energyNumber -= energy;
            getStatManager().energySpendAdd(energy);
            ownerScreen.energyChangeEvent();
            return true;
        }else return false;
    }
    public float getTimerTime(){
        return timeBetweenWaves[currentWave];
    }

    public void damage(int dmg) {
        if(dmg < getHealthNumber()) {
            this.healthNumber -= dmg;
            getStatManager().HpLooseAdd(dmg);
        }else {
            getStatManager().HpLooseAdd(getHealthNumber());
            this.healthNumber = 0;
            looseLevel();
        }
    }

    public void heal(int heal) {
        if(heal <= maxHP) {
            this.healthNumber += heal;
        }else this.healthNumber = maxHP;
    }


    private int energyNumber;
    private int healthNumber;

    private boolean isWin = false;

    private int number;
    //public Wave[] waves;
    private ArrayList<Wave> waves;

    public Wave getCurrentWave(){
        if(currentWave < numberWaves) {
            return waves.get(currentWave);
        }else {
            return null;
        }
    }

    public Wave getWave(int wave){
        if(wave > 0 && wave <= waves.size()) {
            return waves.get(wave - 1);
        }else return null;
    }

    //private ArrayList<Tower> towers;
    //private MapTile[][] map;
    private static Map map;
    public int currentWave;
    public int numberWaves;
    public float[] timeBetweenWaves;

    private float timeBeforeSwitchScreen = 2;

    private LevelMap ownerScreen;


    private StatManager manager;
    public StatManager getStatManager() {
        return manager;
    }

    public static Map getMap(){
        return map;
    }




    public Level(int number, LevelMap ownerScreen) {
        this.ownerScreen = ownerScreen;
        this.number = number;
        manager = new StatManager();
        map = new Map(number, 60, Gdx.graphics.getHeight() - 60, 45);
        //this.map = map;
        loadProperies(map.getSpawner().size());
        init();
    }

    private void loadProperies(int spawners){
        MapLoader ml = new MapLoader(number);
        ml.loadProperties(spawners, true);
        expFromLvl = ml.getExpFromLvl();
        goldFromLvl = ml.getGoldFromLvl();
        startEnergy = (int)(GDefence.getInstance().user.maxEnergy.getCurrentValue() * ml.getStartEnergyPercent());
        startHP = (int)(GDefence.getInstance().user.maxHealth.getCurrentValue() * ml.getStartHpPercent());
        waves = ml.getWaves();
        numberWaves = ml.getNumberWaves();
        timeBetweenWaves = ml.getTimeBetweenWaves();
        dropList = ml.getDropList();
        penaltyDropList = ml.getPenaltyDropList();

        maxEnergy = GDefence.getInstance().user.maxEnergy.getCurrentValue();
        energyNumber = startEnergy;
        maxHP = GDefence.getInstance().user.maxHealth.getCurrentValue();
        healthNumber = startHP;

        type = ml.getMapType();
        mobLimit = ml.getMobLimit();
        timeLimit = ml.getTimeLimit();

    }
    public void init(){
        for (Wave w:waves){
            w.init();
        }
    }

    public void mobDieEvent(){
        ownerScreen.mobDieEvent();
    }


    public void start(){
        isPaused = false;

//        for(int i = 0; i < numberWaves; i++){
//            currentWave = i;
//            //try {
//                //inWave = true;
//
//                waves.get(currentWave).spawn(map.getSpawner().get(0));//
//
//                //while (Wave.mobs.size() > 0) {
//                    //Thread.sleep(100);
//                    //System.out.println(Wave.mobs.size());
//                //}
//                //inWave = false;
//
//                if(i == numberWaves && healthNumber > 0){
//                    winLevel();
//                    break;
//                }
//
//
////            }catch (Exception e){
////                Gdx.app.log("Error", e.getMessage());
////            }
//
//
//        }
        //waves.get(currentWave).spawn(map.getSpawner().get(0));
        //inWave = true;                //insta spawn
    }



    private void winLevel(){
        Wave.mobs.clear();//bug when mobs from prev level appear in current
        isWin = true;
        //System.out.println("win");

        //LevelMap.levelMap.hide();
        if(!GDefence.getInstance().user.getLevelCompleted(number)) {
            GDefence.getInstance().user.addGold(goldFromLvl);
            GDefence.getInstance().user.addExp(expFromLvl);
            addDrop(dropList);
        }else {
            GDefence.getInstance().user.addGold(goldFromLvl/4);//recomplete penalty
            GDefence.getInstance().user.addExp(expFromLvl/4);
            addDrop(penaltyDropList);
        }


        GDefence.getInstance().user.setLevelCompleted(number);
        GDefence.getInstance().user.openLevel(number + 1);


        GDefence.getInstance().setScreen(new LevelEndScreen(true, getStatManager()));


        //GDefence.getInstance().setScreen(new MainMenu(GDefence.getInstance()));
    }

    public void looseLevel(){
        Wave.mobs.clear();
        //System.out.println("loose");
        GDefence.getInstance().setScreen(new LevelEndScreen(false, getStatManager()));


    }
//    private class Drop{
////        private IDroppable drop;
//        private int count;
//
//        public Drop(/*IDroppable drop,*/ int count) {
////            this.drop = drop;
//            this.count = count;
//        }
//    }

    private void addDrop(String dropCode){
//        Array<GameObject> currentDrop = new Array<GameObject>();
//        Array<Drop> currentDrop = new Array<Drop>();
//        HashMap<String, Integer> currentDrop = new HashMap<String, Integer>();//String = texturePath;
        String[] objects = dropCode.split("/");

        Array<DropSlot> drop = new Array<DropSlot>();


        for (String s:objects){
            String[] curr = s.split(":");
            switch (curr.length) {
            case 2:
                Random rand = new Random();
                String[] range = curr[1].split("-");
                int count;
                if(range.length > 1) count = rand.nextInt((Integer.parseInt(range[1]) - Integer.parseInt(range[0])) + 1) + Integer.parseInt(range[0]);
                else count = Integer.parseInt(range[0]);//One item
                ItemEnum.addItemById(Integer.parseInt(curr[0]), count, GDefence.getInstance().user);
//                DropSlot slot = ;
                drop.add(new DropSlot(ItemEnum.getItemById(Integer.parseInt(curr[0])), count));
//                currentDrop.add(ItemEnum.getItemTextureById(Integer.parseInt(curr[0])));
                break;
            case 3:
                Random chance = new Random();
                if (chance.nextFloat() > 1 - Float.parseFloat(curr[2])) {
//                currentDrop.add();
                    ItemEnum.addItemById(Integer.parseInt(curr[0]), Integer.parseInt(curr[1]), GDefence.getInstance().user);
                    drop.add(new DropSlot(ItemEnum.getItemById(Integer.parseInt(curr[0])), 1));
                }
                break;
            }
        }
        getStatManager().setDrop(drop);
    }



    public void render(SpriteBatch batch){
        map.draw(batch);
        drawMobs(batch);
    }
    public void physic(float delta){
        if(delta > 30) return;
        map.physic(delta);
        if(isPaused) return;
        if(inWave){
            roundTime += delta;
            if(type == Map.MapType.TIME){
                if(roundTime >= getTimeLimit()) looseLevel();
            }
            for (int i = 0; i < map.getSpawner().size(); i++) {
                if(currentWave + i < waves.size()) {//hotfix
                    waves.get(currentWave + i).update(delta);
                }
            }
            physicMobs(delta);
//            drawTowers();
//            drawParticles();



            if(waves.get(currentWave).isFinished()){
                if(currentWave + map.getSpawner().size() < waves.size()) {
                    currentWave += map.getSpawner().size();
//                    inWave = false;
                    setInWave(false);
//                    waveEndEvent();
                    //System.out.println("new wave");
                }else {
                    if(!isWin) {
                        timeBeforeSwitchScreen -= delta;
                        if(timeBeforeSwitchScreen <= 0) {
                            winLevel();
                        }
                    }
                }
            }
        }else {
            updateRoundTimer(delta);
        }
    }
//    private void drawWave(float delta, SpriteBatch batch){
//        for (int i = 0; i < map.getSpawner().size(); i++){
//            waves.get(currentWave + i).render(batch);
//        }
//    }
    private void waveEndEvent(){
        ownerScreen.updateEnd();
    }
    private void waveStartEvent(){
        ownerScreen.updateStart();
    }

    private void drawMobs(SpriteBatch batch){
        for (Mob m: Wave.mobs){
            m.render(batch);
        }
    }

    private void physicMobs(float delta){
//        List<Mob> tmpMobs = new CopyOnWriteArrayList<Mob>(Wave.mobs);
        for (Mob m: Wave.mobs){
            m.actEffects(delta);
            m.move(delta);
        }
    }


    private void drawTowers(){

    }
    private void drawParticles(){

    }

    private void updateRoundTimer(float delta) {
        timeBetweenWaves[currentWave] -= delta;
        //System.out.println(timeBetweenWaves[currentWave]);

        if(timeBetweenWaves[currentWave] <= 0){
            for (int i = 0; i < map.getSpawner().size(); i++) {
                if(currentWave + i < waves.size()) {//hotfix
                    waves.get(currentWave + i).spawn(/*map.getSpawner().get(0)*/);
                }
            }
            setInWave(true);
//            inWave = true;
//            waveStartEvent();
        }
        //if (roundTimer > 0) {
        //    roundTimer -= delta;
        //}
        //else {
            //waves.get(currentWave).setInWave(true);
            //roundTimer = timeBetweenWaves[currentWave];

            //prepareLevel(level++);
            //spawnedEnemies = 0;
       // }
    }



}
