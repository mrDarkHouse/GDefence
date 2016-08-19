package com.darkhouse.gdefence.Model.Level;


import com.darkhouse.gdefence.Level.MapLoader;
import com.darkhouse.gdefence.Level.MapTile;

import java.io.File;

public class Map {

    private MapTile[][] tiles;

    public Map(File mapFile) {
        loadMap(mapFile);
    }

    private void loadMap(File mapFile){
        MapLoader ml = new MapLoader(mapFile);
        tiles = ml.loadMap();
    }

}
