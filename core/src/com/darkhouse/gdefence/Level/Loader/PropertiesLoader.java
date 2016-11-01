package com.darkhouse.gdefence.Level.Loader;


import com.badlogic.gdx.Gdx;
import com.darkhouse.gdefence.Level.Level;
import com.darkhouse.gdefence.Level.Wave;
import com.darkhouse.gdefence.Model.Level.Map;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PropertiesLoader {
    private File loadFile;
    private int expFromLvl;
    private int goldFromLvl;
    private int startEnergy;
    private float startHpPercent;
    private int numberWaves;
    private float[] timeBetweenWaves = new float[20];
    private ArrayList<Wave> waves;

    public int getExpFromLvl() {
        return expFromLvl;
    }
    public int getGoldFromLvl() {
        return goldFromLvl;
    }
    public int getStartEnergy() {
        return startEnergy;
    }
    public float getStartHpPercent() {
        return startHpPercent;
    }
    public int getNumberWaves() {
        return numberWaves;
    }
    public float[] getTimeBetweenWaves() {
        return timeBetweenWaves;
    }
    public ArrayList<Wave> getWaves() {
        return waves;
    }

    public PropertiesLoader(int map) {
        try{
            loadFile = new File("Maps/Map" + map + "SpawnProperty.gdp");
        }catch (Exception e){
            Gdx.app.log("Error", e.toString());
        }

    }

    public void loadProperties(int spawners, boolean forRealMap){
        try {
            if(spawners == 0){//empty maps
                return;
            }

            Scanner loadScanner = new Scanner(loadFile);
            waves = new ArrayList<Wave>();

            expFromLvl = loadScanner.nextInt();
            goldFromLvl = loadScanner.nextInt();
            startEnergy = loadScanner.nextInt();
            startHpPercent = loadScanner.nextFloat();
            while (loadScanner.hasNext()){
                for (int i = 0; i < spawners; i++) {
                    waves.add(new Wave(loadScanner.nextInt(), loadScanner.nextInt(), loadScanner.nextFloat()));
                    timeBetweenWaves[waves.size() - 1] = loadScanner.nextInt();
                    if(forRealMap) {
                        waves.get(waves.size() - 1).setSpawner(Level.getMap().getSpawner().get(i));
                    }
                }
            }
            numberWaves = waves.size();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
