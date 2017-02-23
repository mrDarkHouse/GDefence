package com.darkhouse.gdefence.Level.Loader;


import com.badlogic.gdx.Gdx;
import com.darkhouse.gdefence.Level.Level;
import com.darkhouse.gdefence.Level.Wave;
import com.darkhouse.gdefence.Model.Level.Map;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class PropertiesLoader {
    private File loadFile;
    private int expFromLvl;
    private int goldFromLvl;
    private float startEnergyPercent;
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
    public float getStartEnergyPercent() {
        return startEnergyPercent;
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
            loadFile = new File("Maps/Map" + map + ".properties");
        }catch (Exception e){
            Gdx.app.log("Error", e.toString());
        }

    }

    public void loadProperties(int spawners, boolean forRealMap){
        try {
            if(spawners == 0){//empty maps
                return;
            }
            Properties prop = new Properties();
            FileInputStream fs = new FileInputStream(loadFile);
            prop.load(fs);


//            Scanner loadScanner = new Scanner(loadFile);
            waves = new ArrayList<Wave>();

//            expFromLvl = loadScanner.nextInt();
//            goldFromLvl = loadScanner.nextInt();
//            startEnergyPercent = loadScanner.nextFloat();
//            startHpPercent = loadScanner.nextFloat();
            expFromLvl = Integer.parseInt(prop.getProperty("expFromLvl"));
            goldFromLvl = Integer.parseInt(prop.getProperty("goldFromLvl"));
            startEnergyPercent = Float.parseFloat(prop.getProperty("startEnegryPercent"));
            startHpPercent = Float.parseFloat(prop.getProperty("startHpPercent"));

            String[] wavesCode = prop.getProperty("waves").split("/");

//            while (loadScanner.hasNext()){
            for (int i = 0; i < wavesCode.length/*/spawners*/; i++) {
                String[] info = wavesCode[i].split(":");
//                for (int j = 0; j < spawners; j++) {
//                    String[] info2 = info[i].split("-");
                    waves.add(new Wave(Integer.parseInt(info[0]), Integer.parseInt(info[1]), Float.parseFloat(info[2])));
                    timeBetweenWaves[waves.size() - 1] = Integer.parseInt(info[3]);//float
                    if (forRealMap) {
                        waves.get(i/* + j*/).setSpawner(Level.getMap().getSpawner().get(/*j*/0));//invertion//i - spawners + j
                    }
//                }
            }
//            }
            numberWaves = waves.size();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
