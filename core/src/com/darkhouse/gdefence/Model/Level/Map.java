package com.darkhouse.gdefence.Model.Level;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.darkhouse.gdefence.Helpers.AssetLoader;
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

    private boolean isBuild = false;

    public void setBuild(boolean build) {
        isBuild = build;
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
                tiles[x][y].draw(batch, delta);
            }
        }
        for (int x = 0; x < tiles.length; x++){
            for (int y = 0; y < tiles[0].length; y++){
                if(tiles[x][y].getBuildedTower() != null) {
                    tiles[x][y].getBuildedTower().drawRange(batch, delta);
                }
            }
        }
       // Iterator<Projectile> it = projectiles.iterator();
        List<Projectile> tmp = new CopyOnWriteArrayList<Projectile>(projectiles);

//        while(it.hasNext()){
//            Projectile p = it.next();
//            p.act(delta);
//            p.draw(batch, 1);
//        }
        for (Projectile p:tmp){
            p.act(delta);
            p.draw(batch, 1);
        }

        if(isBuild){
            drawBuildGrid(batch, delta);
        }

    }

    public void drawBuildGrid(SpriteBatch batch, float delta){
        Texture linePixel = AssetLoader.buildGridLinePixel;
        for (int x = 0; x < tiles.length; x++){
            for (int y = 0; y < tiles[0].length; y++){
                for (int i = 0; i < tiles[x][y].getWidth(); i++){
                    batch.draw(linePixel, tiles[x][y].getX() + i, tiles[x][y].getY() + tiles[x][y].getHeight());
                }
                for (int i = 0; i < tiles[x][y].getHeight(); i++){
                    batch.draw(linePixel, tiles[x][y].getX(), tiles[x][y].getY() + i);
                }
            }
        }
        for (int x = 0; x < tiles.length; x++) {//draw last x lane
            for (int i = 0; i < tiles[0][0].getWidth(); i++){
                batch.draw(linePixel, tiles[x][tiles[0].length - 1].getX() + i, tiles[x][tiles[0].length - 1].getY());
            }
        }
        for (int y = 0; y < tiles[0].length; y++) {//draw last y lane
            for (int i = 0; i < tiles[0][0].getHeight(); i++){
                batch.draw(linePixel, tiles[tiles.length - 1][y].getX() + tiles[0][0].getWidth(),
                        tiles[tiles.length - 1][y].getY() + i);
            }
        }
    }
}
