package com.darkhouse.gdefence.Level;


import com.badlogic.gdx.Gdx;
import com.darkhouse.gdefence.Level.Loader.PropertiesLoader;
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



    public int coinNumber;
    public int healthNumber;

    private int number;
    //public Wave[] waves;
    private ArrayList<Wave> waves;
    //private MapTile[][] map;
    private Map map;
    public int currentWave;
    public int numberWaves;
    public int[] timeBetweenWaves;




    public Level(int number, Map map) {
        this.number = number;
        this.map = map;
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
        for(int i = 0; i < numberWaves; i++){
            currentWave = i;
            try {
                inWave = true;

                waves.get(currentWave).spawn(map.getSpawner().get(0));//

                while (Wave.mobs.size() > 0) {
                    Thread.sleep(100);
                    //System.out.println(Wave.mobs.size());
                }
                inWave = false;

                if(i == numberWaves && healthNumber > 0){
                    winLevel();
                    break;
                }


            }catch (Exception e){
                Gdx.app.log("Error", e.getMessage());
            }


        }
    }

    private void winLevel(){
        System.out.println("win");
    }

    private void looseLevel(){

    }



}
