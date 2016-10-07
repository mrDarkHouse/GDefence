package com.darkhouse.gdefence.Model.Level;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.darkhouse.gdefence.Level.Loader.MapLoader;
import com.darkhouse.gdefence.Level.MapTile;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Mob.Way;
import com.darkhouse.gdefence.Level.Tower.Projectile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Map {

    private MapTile[][] tiles;

    public MapTile[][] getTiles() {
        return tiles;
    }


    public static List<Projectile> projectiles;

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

    public static Way checkSpawnerWay(MapTile spawner){
        switch (spawner.getLogic()){
            case spawnerR:
                return Way.RIGHT;
            case spawnerL:
                return Way.LEFT;
            case spawnerU:
                return Way.UP;
            case spawnerD:
                return Way.DOWN;
            default:
                return null;
        }
    }

    public static Way checkTurnWay(MapTile tile){
        switch (tile.getLogic()){
            case turnR:
                return Way.RIGHT;
            case turnL:
                return Way.LEFT;
            case turnU:
                return Way.UP;
            case turnD:
                return Way.DOWN;
            default:
                return null;
        }
    }

    public MapTile getTileContainMob(Mob mob){
        for (int x = 0; x < tiles.length; x++){
            for (int y = 0; y < tiles[0].length; y++){
                if (tiles[x][y].contains(mob.getBoundingRectangle())){//
                    //if(tiles[x][y].getLogic() == MapTile.TileLogic.turnR) {
                        //System.out.println(tiles[x][y].getX() + " " + tiles[x][y].getY());
                        //System.out.println(mob.getX() + " " + mob.getY());
                    //}
                    return tiles[x][y];
                }
            }
        }
        return null;
    }


    public Map(final int number, int x, int y, int cellSize) {
        projectiles = new ArrayList<Projectile>();//dirty code
        initMap(number);
        this.x = x;
        this.y = y;
        this.cellSize = cellSize;
        initCells();

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
       // Iterator<Projectile> it = projectiles.iterator();
        List<Projectile> tmp = new CopyOnWriteArrayList<Projectile>(projectiles);

//        while(it.hasNext()){
//            Projectile p = it.next();
//            p.act(delta);
//            p.draw(batch, 1);
//        }
        for (Projectile p:tmp){//ConcurrentModificationException
            p.act(delta);
            p.draw(batch, 1);
        }
    }

}
