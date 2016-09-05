package com.darkhouse.gdefence.Level;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Mob.Slime;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Wave {
    private int numberMobs;
    private int timeSpawn;
    private int mobID;
    public static ArrayList<Mob> mobs = new ArrayList();

    private ArrayList<Mob> mobsToSpawn = new ArrayList<Mob>();
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
        initMobsToSpawn();
        setSpawner(spawner);
        //inWave = true;



            //Thread.sleep(timeSpawn);


        //}
    }

    private void initMobsToSpawn(){
        for (int i = 0; i < numberMobs; i++) {
            mobsToSpawn.add(Mob.getMobById(mobID));
        }
    }

    public void update(float delta){
        checkToSpawn(delta);
        moveMobs(delta);




    }

    private void moveMobs(float delta){
        List<Mob> tmpMobs = new CopyOnWriteArrayList<Mob>(mobs);
        for (Mob m : tmpMobs){
            if(tmpMobs.iterator().hasNext()) {
                m.move(delta);
            }
        }
    }





    private void checkToSpawn(float delta){
        spawnDelay += delta;

        if (spawnDelay >= timeSpawn && mobsToSpawn.size() > 0) {
            mobs.add(mobsToSpawn.get(mobsToSpawn.size() - 1));
            mobsToSpawn.remove(mobsToSpawn.get(mobsToSpawn.size() - 1));
            mobs.get(mobs.size() - 1).spawn(spawner);

            //System.out.println("spawned");


            spawnDelay -= timeSpawn;

        }

        if(mobsToSpawn.size() == 0 && mobs.size() == 0){
            isFinished = true;
        }
    }

}
