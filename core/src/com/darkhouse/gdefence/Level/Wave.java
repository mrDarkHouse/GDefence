package com.darkhouse.gdefence.Level;


import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.Level.Ability.Mob.BossResist;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Path.MapTile;
import com.darkhouse.gdefence.Level.Path.WalkableMapTile;
import com.darkhouse.gdefence.Model.Level.Map;
import com.darkhouse.gdefence.Screens.LevelMap;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Wave {
    private int numberMobs;

    public int getNumberMobs() {
        return numberMobs;
    }

    private float timeSpawn;
    private int mobID;

    private Mob boss;

    public Mob getBoss() {
        return boss;
    }

    public int getMobID() {
        return mobID;
    }

    public static Array<Mob> mobs = new Array();

    //public ArrayList<Mob> mobs = new ArrayList();

    private Array<Mob> mobsToSpawn = new Array<Mob>();

    public Array<Mob> getMobsToSpawn() {
        return mobsToSpawn;
    }
    //    public ArrayList<Mob> getMobsToSpawn() {
//        return mobsToSpawn;
//    }

    private WalkableMapTile spawner;
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

    public void setSpawner(WalkableMapTile spawner) {
        this.spawner = spawner;
    }

    private float spawnDelay;


    private boolean isPaused = true;

    public Wave(int mobID, int numberMobs, float timeSpawn) {
        this.numberMobs = numberMobs;
        this.timeSpawn = timeSpawn;
        this.mobID = mobID;
    }
    public void init(){
        initMobsToSpawn();
    }

    public void spawn(/*MapTile spawner*/){
        isPaused = false;
//        initMobsToSpawn();
        //setSpawner(spawner);

    }

    private void initMobsToSpawn(){
        for (int i = 0; i < numberMobs; i++) {
            Mob m = Mob.createMob(Mob.getMobById(mobID));
            if(m.haveAbility(BossResist.class)) {
                boss = m;
//                System.out.println("boss " + m);
            }
            mobsToSpawn.add(m);
        }
    }

    public void update(float delta){
        if(!isPaused) checkToSpawn(delta);
        //moveMobs(delta);
    }

//    private void moveMobs(float delta){
//        List<Mob> tmpMobs = new CopyOnWriteArrayList<Mob>(mobs);
//        for (Mob m : tmpMobs){
//            if(tmpMobs.iterator().hasNext()) {
//                m.move(delta);
//            }
//        }
//    }






    private void checkToSpawn(float delta){
        spawnDelay += delta*Level.getTimeMultiplayer();


        if (spawnDelay >= timeSpawn && mobsToSpawn.size > 0) {
            mobs.add(mobsToSpawn.peek());
//            System.out.println(mobs.peek().getAbilities().get(0).getOwner());
            mobsToSpawn.removeValue(mobsToSpawn.peek(), true);
//            System.out.println(mobs.get(mobs.size - 1).getAbilities().get(0).getOwner());
            mobs.peek().spawn(spawner);

            LevelMap.levelMap.mobSpawnEvent(mobs.peek());

            //System.out.println("spawned " + timeSpawn);

            //System.out.println("spawned");


            spawnDelay -= timeSpawn;

        }

        if(mobsToSpawn.size == 0 && mobs.size == 0){
            isFinished = true;
        }
    }

}
