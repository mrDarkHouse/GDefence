package com.darkhouse.gdefence.Model.Level;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.darkhouse.gdefence.Level.MapLoader;
import com.darkhouse.gdefence.Level.MapTile;

import java.io.File;

public class Map {

    private MapTile[][] tiles;
    private int x;
    private int y;
    private int cellSize;

    public Map(final int number, int x, int y, int cellSize) {
        loadMap(number);
        this.x = x;
        this.y = y;
        this.cellSize = cellSize;
        initCells(x, y, cellSize);
        System.out.println(tiles.length + " "  + tiles[0].length);
        System.out.println(tiles[0][2].getType());
        System.out.println(tiles[2][2].getType());
        System.out.println(tiles[3][2].getType());
        System.out.println(tiles[4][4].getType());

    }
    private void initCells(int x, int y, int cellSize){
        for (int i = 0; i < tiles.length; i++){
            for (int j = 0; j < tiles[0].length; j++){
                tiles[i][j].setBounds(x + cellSize*i, y - cellSize - cellSize*j , cellSize, cellSize);
            }
        }
    }

    private void loadMap(final int number){
        MapLoader ml = new MapLoader(number);
        tiles = ml.loadMap();
    }

    public void draw(float delta, SpriteBatch batch){
        for (int x = 0; x < tiles.length; x++){
            for (int y = 0; y < tiles[0].length; y++){
                tiles[x][y].draw(delta, batch);
            }
        }
    }

}
