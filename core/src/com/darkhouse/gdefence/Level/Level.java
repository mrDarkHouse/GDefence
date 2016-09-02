package com.darkhouse.gdefence.Level;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.darkhouse.gdefence.Level.Loader.PropertiesLoader;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Tower.Tower;
import com.darkhouse.gdefence.Model.Level.Map;

import java.io.File;
import java.util.ArrayList;

public class Level {
    private int expFromLvl;
    private int goldFromLvl;
    private int startCoins;
    private int startHP;

    private boolean inWave = false;

    public int getHealthNumber() {
        return healthNumber;
    }

    public int getCoinNumber() {
        return coinNumber;
    }

    public void addCoin(int coin) {
        this.coinNumber += coin;
    }
    public boolean removeCoin(int coin) {
        if(coin <= coinNumber) {
            this.coinNumber -= coinNumber;
            return true;
        }else{
            return false;
        }
    }

    public void damage(int dmg) {
        this.healthNumber -= dmg;
    }

    public void heal(int heal) {
        if(heal <= startHP) {//startHp to maxHp
            this.healthNumber += heal;
        }
    }


    private int coinNumber;
    private int healthNumber;

    private int number;
    //public Wave[] waves;
    private ArrayList<Wave> waves;
    private ArrayList<Tower> towers;
    //private MapTile[][] map;
    private static Map map;
    public int currentWave;
    public int numberWaves;
    public float[] timeBetweenWaves;

    private float roundTimer;

    public static Map getMap(){
        return map;
    }




    public Level(int number) {

        this.number = number;
        map = new Map(number, 60, Gdx.graphics.getHeight() - 60, 45);
        //this.map = map;
        loadProperies();
    }

    private void loadProperies(){
        PropertiesLoader pl = new PropertiesLoader(number);
        pl.loadProperties();
        expFromLvl = pl.getExpFromLvl();
        goldFromLvl = pl.getGoldFromLvl();
        startCoins = pl.getStartCoins();
        startHP = pl.getStartHp();
        waves = pl.getWaves();
        numberWaves = pl.getNumberWaves();
        timeBetweenWaves = pl.getTimeBetweenWaves();

        coinNumber = startCoins;
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
        waves.get(currentWave).spawn(map.getSpawner().get(0));
        inWave = true;



    }



    private void winLevel(){
        System.out.println("win");
    }

    private void looseLevel(){

    }



    public void render(float delta, SpriteBatch batch){
        map.draw(delta, batch);
        if(inWave){
            waves.get(currentWave).update(delta);
            drawMobs(delta, batch);
            drawTowers();
            drawParticles();



            if(waves.get(currentWave).isFinished()){
                if(currentWave < waves.size()) {
                    currentWave++;
                    inWave = false;
                    System.out.println("new wave");
                }else {
                    winLevel();
                }
            }
        }else {
            updateRoundTimer(delta);
        }




    }
    private void drawMobs(float delta, SpriteBatch batch){
        for (Mob m: Wave.mobs){
            m.render(batch);
        }
    }
    private void drawTowers(){

    }
    private void drawParticles(){

    }

    private void updateRoundTimer(float delta) {
        timeBetweenWaves[currentWave] -= delta;
        System.out.println(timeBetweenWaves[currentWave]);

        if(timeBetweenWaves[currentWave] <= 0){
            waves.get(currentWave).spawn(map.getSpawner().get(0));
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
