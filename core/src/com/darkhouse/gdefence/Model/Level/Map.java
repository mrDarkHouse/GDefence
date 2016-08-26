package com.darkhouse.gdefence.Model.Level;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.darkhouse.gdefence.Level.Loader.MapLoader;
import com.darkhouse.gdefence.Level.MapTile;

import java.util.ArrayList;

public class Map {

    private MapTile[][] tiles;
    private int x;
    private int y;
    private int cellSize;
    private ArrayList<MapTile> spawner;
    private ArrayList<MapTile> castle;

    public ArrayList<MapTile> getSpawner() {
        return spawner;
    }
    public ArrayList<MapTile> getCastle() {
        return castle;
    }

    public Map(final int number, int x, int y, int cellSize) {
        initMap(number);
        this.x = x;
        this.y = y;
        this.cellSize = cellSize;
        initCells();
        //System.out.println(tiles.length + " "  + tiles[0].length);
        //System.out.println(tiles[1][0].getType());
        //System.out.println(tiles[2][0].getType());
        //System.out.println(tiles[3][0].getType());
        //System.out.println(tiles[4][0].getType());

    }
    private void initCells(){
        for (int i = 0; i < tiles.length; i++){
            for (int j = 0; j < tiles[0].length; j++){
                tiles[i][j].setBounds(x + cellSize*i, y - cellSize - cellSize*j , cellSize, cellSize);
            }
        }
    }

    private void initMap(final int number){
        MapLoader ml = new MapLoader(number);
        tiles = ml.loadMap();
        searchSpawner();
        searchCastle();
    }

    private void searchSpawner(){
        spawner = new ArrayList<MapTile>();
        for (int x = 0; x < tiles.length; x++){
            for (int y = 0; y < tiles[0].length; y++){
                if(tiles[x][y].getLogic() == MapTile.TileLogic.spawnerR ||
                   tiles[x][y].getLogic() == MapTile.TileLogic.spawnerL ||
                   tiles[x][y].getLogic() == MapTile.TileLogic.spawnerU ||
                   tiles[x][y].getLogic() == MapTile.TileLogic.spawnerD){
                        spawner.add(tiles[x][y]);
                }
            }
        }
    }

    private void searchCastle(){
        castle = new ArrayList<MapTile>();
        for (int x = 0; x < tiles.length; x++){
            for (int y = 0; y < tiles[0].length; y++){
                if(tiles[x][y].getLogic() == MapTile.TileLogic.castle){
                    castle.add(tiles[x][y]);
                }
            }
        }
    }

    public void draw(float delta, SpriteBatch batch){
        for (int x = 0; x < tiles.length; x++){
            for (int y = 0; y < tiles[0].length; y++){
                tiles[x][y].draw(delta, batch);
            }
        }
    }

}
