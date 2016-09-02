package com.darkhouse.gdefence.Level;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Mob.Slime;

import java.util.ArrayList;

public class Wave {
    private int numberMobs;
    private int timeSpawn;
    private int mobID;
    public static ArrayList<Mob> mobs = new ArrayList();
    private MapTile spawner;
    //private boolean inWave;

    //public boolean isInWave() {
    //    return inWave;
    //}

    //public void setInWave(boolean inWave) {
    //    this.inWave = inWave;
    //}
    private boolean isFinished = false;

    public boolean isFinished() {
        return isFinished;
    }

    public void setSpawner(MapTile spawner) {
        this.spawner = spawner;
    }

    private float spawnDelay;


    public Wave(int mobID, int numberMobs, int timeSpawn) {
        this.numberMobs = numberMobs;
        this.timeSpawn = timeSpawn;
        this.mobID = mobID;
    }

    public void spawn(MapTile spawner){
        //for(int i = 0; i < numberMobs; i++){
            // if(!mobs.get(i).inGame){
        setSpawner(spawner);
        //inWave = true;



            //Thread.sleep(timeSpawn);


        //}
    }

    public void update(float delta){
        checkToSpawn(delta);
        moveMobs(delta);




    }

    private void moveMobs(float delta){
        for (Mob m : mobs){//fix with iterator
            m.move(delta);
        }
    }





    private void checkToSpawn(float delta){
        spawnDelay += delta;

        if (spawnDelay >= timeSpawn && mobs.size() < numberMobs) {
            mobs.add(Mob.getMobById(mobID));
            mobs.get(mobs.size() - 1).spawn(spawner);

            //System.out.println("spawned");


            spawnDelay -= timeSpawn;

        }

        if(mobs.size() == 0){
            //isFinished = true;
        }
    }

}
