package com.darkhouse.gdefence.Model.Level;


import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.Level.Path.MapTile;

public class Path extends Array<MapTile>{
    private int spawnIndex;

    public int getSpawnIndex() {
        return spawnIndex;
    }
    //    private int castleIndex;


    public Path(Array<? extends MapTile> array, int spawnIndex) {
        super(array);
        this.spawnIndex = spawnIndex;
    }
}
