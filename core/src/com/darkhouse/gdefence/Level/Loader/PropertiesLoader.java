package com.darkhouse.gdefence.Level.Loader;


import com.badlogic.gdx.Gdx;
import com.darkhouse.gdefence.Level.Wave;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PropertiesLoader {
    private File loadFile;
    private int expFromLvl;
    private int goldFromLvl;
    private int startCoins;
    private int startHp;
    private int numberWaves;
    private int[] timeBetweenWaves = new int[20];
    private ArrayList<Wave> waves;

    public int getExpFromLvl() {
        return expFromLvl;
    }
    public int getGoldFromLvl() {
        return goldFromLvl;
    }
    public int getStartCoins() {
        return startCoins;
    }
    public int getStartHp() {
        return startHp;
    }
    public int getNumberWaves() {
        return numberWaves;
    }
    public int[] getTimeBetweenWaves() {
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

    public void loadProperties(){
        try {
            Scanner loadScanner = new Scanner(loadFile);
            waves = new ArrayList<Wave>();

            expFromLvl = loadScanner.nextInt();
            goldFromLvl = loadScanner.nextInt();
            startCoins = loadScanner.nextInt();
            startHp = loadScanner.nextInt();
            while (loadScanner.hasNext()){
                waves.add(new Wave(loadScanner.nextInt(), loadScanner.nextInt(), loadScanner.nextInt()));
                timeBetweenWaves[waves.size() - 1] = loadScanner.nextInt();
            }
            numberWaves = waves.size();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
