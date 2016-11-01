package com.darkhouse.gdefence.Level;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.StatManager;
import com.darkhouse.gdefence.Level.Loader.PropertiesLoader;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Model.Level.Map;
import com.darkhouse.gdefence.Screens.LevelEndScreen;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Level {
    private int expFromLvl;
    private int goldFromLvl;
    private int startEnergy;
    private int maxEnergy;
    private int startHP;
    private int maxHP;

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

    public int getHealthNumber() {
        return healthNumber;
    }

    public int getEnergyNumber() {
        return energyNumber;
    }

    public void addEnergy(int energy) {
        if(energy + energyNumber <= GDefence.getInstance().user.maxEnegry.getCurrentValue()) {
            this.energyNumber += energy;
        }else {
            if(this.energyNumber != GDefence.getInstance().user.maxEnegry.getCurrentValue()) {
                this.energyNumber = GDefence.getInstance().user.maxEnegry.getCurrentValue();
            }
        }
    }
    public boolean removeEnergy(int energy) {
        if(energy <= energyNumber) {
            this.energyNumber -= energy;
            return true;
        }else{
            return false;
        }
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


    private StatManager manager;
    public StatManager getStatManager() {
        return manager;
    }

    public static Map getMap(){
        return map;
    }




    public Level(int number) {

        this.number = number;
        manager = new StatManager();
        map = new Map(number, 60, Gdx.graphics.getHeight() - 60, 45);
        //this.map = map;
        loadProperies(map.getSpawner().size());
    }

    private void loadProperies(int spawners){
        PropertiesLoader pl = new PropertiesLoader(number);
        pl.loadProperties(spawners, true);
        expFromLvl = pl.getExpFromLvl();
        goldFromLvl = pl.getGoldFromLvl();
        startEnergy = pl.getStartEnergy();
        startHP = (int)(GDefence.getInstance().user.maxHealth.getCurrentValue() * pl.getStartHpPercent());
        waves = pl.getWaves();
        numberWaves = pl.getNumberWaves();
        timeBetweenWaves = pl.getTimeBetweenWaves();

        maxEnergy = GDefence.getInstance().user.maxEnegry.getCurrentValue();
        energyNumber = startEnergy;
        maxHP = GDefence.getInstance().user.maxHealth.getCurrentValue();
        healthNumber = startHP;


    }

    public void start(){
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
        isWin = true;
        //System.out.println("win");

        //LevelMap.levelMap.hide();
        if(!GDefence.getInstance().user.getLevelCompleted(number)) {
            GDefence.getInstance().user.addGold(goldFromLvl);
            GDefence.getInstance().user.addExp(expFromLvl);
        }else {
            GDefence.getInstance().user.addGold(goldFromLvl/4);//recomplete penalty
            GDefence.getInstance().user.addExp(expFromLvl/4);
        }


        GDefence.getInstance().user.setLevelCompleted(number);
        GDefence.getInstance().user.openLevel(number + 1);


        GDefence.getInstance().setScreen(new LevelEndScreen(true));


        //GDefence.getInstance().setScreen(new MainMenu(GDefence.getInstance()));
    }

    private void looseLevel(){
        //System.out.println("loose");
        GDefence.getInstance().setScreen(new LevelEndScreen(false));


    }



    public void render(float delta, SpriteBatch batch){
        map.draw(delta, batch);
        if(inWave){
            for (int i = 0; i < map.getSpawner().size(); i++) {
                waves.get(currentWave + i).update(delta);
            }
            physicMobs(batch, delta);
            drawTowers();
            drawParticles();



            if(waves.get(currentWave).isFinished()){
                if(currentWave + 1 < waves.size()) {
                    currentWave += map.getSpawner().size();
                    inWave = false;
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
    private void physicMobs(SpriteBatch batch, float delta){
        List<Mob> tmpMobs = new CopyOnWriteArrayList<Mob>(Wave.mobs);
        for (Mob m: tmpMobs){
            m.move(delta);
            m.render(batch);
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
            inWave = true;

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
