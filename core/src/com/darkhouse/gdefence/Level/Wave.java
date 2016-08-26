package com.darkhouse.gdefence.Level;


import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Mob.Slime;

import java.util.ArrayList;

public class Wave {
    private int numberMobs;
    private int timeSpawn;
    private int mobID;
    public static ArrayList<Mob> mobs = new ArrayList();


    public Wave(int mobID, int numberMobs, int timeSpawn) {
        this.numberMobs = numberMobs;
        this.timeSpawn = timeSpawn;
        this.mobID = mobID;
    }

    public void spawn(MapTile spawner){
        for(int i = 0; i < numberMobs; i++){
            // if(!mobs.get(i).inGame){
            mobs.add(Mob.getMobById(mobID));
            mobs.get(mobs.size() - 1).spawn(spawner);

            try {
                Thread.sleep(timeSpawn);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(this);
            }
        }
    }
}
