package com.darkhouse.gdefence.Model.Level;


import com.darkhouse.gdefence.Level.MapLoader;
import com.darkhouse.gdefence.Level.MapTile;

import java.io.File;

public class Map {

    private MapTile[][] tiles;

    public Map(final int number) {
        loadMap(number);
        System.out.println(tiles.length + " "  + tiles[0].length);
        System.out.println(tiles[0][2].getType());
        System.out.println(tiles[2][2].getType());
        System.out.println(tiles[3][2].getType());
        System.out.println(tiles[4][4].getType());
    }

    private void loadMap(final int number){
        MapLoader ml = new MapLoader(number);
        tiles = ml.loadMap();
    }

}
