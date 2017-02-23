package com.darkhouse.gdefence.Model.Level;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Level.Loader.MapLoader;
import com.darkhouse.gdefence.Level.Path.Castle;
import com.darkhouse.gdefence.Level.Path.MapTile;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Mob.Way;
import com.darkhouse.gdefence.Level.Path.Spawn;
import com.darkhouse.gdefence.Level.Tower.Projectile;
import com.darkhouse.gdefence.Objects.TowerObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Map {

    private MapTile[][] tiles;

//    private Array<MapTile> path;

    public MapTile[][] getTiles() {
        return tiles;
    }

    private boolean isBuild = false;
    private TowerObject rangeTower;
    private DragAndDrop.Payload payload;

    public void setBuild(boolean build, TowerObject rangeTower, DragAndDrop.Payload payload) {//
        isBuild = build;
        this.rangeTower = rangeTower;
        this.payload = payload;
    }


    public static List<Projectile> projectiles;

    private int x;
    private int y;
    private int cellSize;
    private ArrayList<MapTile> spawner;
    private ArrayList<MapTile> castle;

    public ArrayList<MapTile> getSpawner() {
        return spawner;
    }//need only size
    public ArrayList<MapTile> getCastle() {
        return castle;
    }

//    public static Way checkSpawnerWay(MapTile spawner){
//        switch (spawner.getLogic()){
//            case spawnerR:
//                return Way.RIGHT;
//            case spawnerL:
//                return Way.LEFT;
//            case spawnerU:
//                return Way.UP;
//            case spawnerD:
//                return Way.DOWN;
//            default:
//                return null;
//        }
//    }

//    public static Way checkTurnWay(MapTile tile){
//        switch (tile.getLogic()){
//            case turnR:
//                return Way.RIGHT;
//            case turnL:
//                return Way.LEFT;
//            case turnU:
//                return Way.UP;
//            case turnD:
//                return Way.DOWN;
//            default:
//                return null;
//        }
//    }

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

    public void setIndexTiles(){
        for (int x = 0; x < tiles.length; x++){
            for (int y = 0; y < tiles[0].length; y++){
                tiles[x][y].setIndex(x, y);
            }
        }


    }
    public MapTile getNextTile(MapTile tile, Way way){
        switch (way){
            case RIGHT:
                if(tile.indexX + 1 > tiles.length) {
                    return getTiles()[tile.indexX + 1][tile.indexY];
                }
                break;
            case LEFT:
                if(tile.indexX - 1 < tiles.length) {
                    return getTiles()[tile.indexX - 1][tile.indexY];
                }
                break;
            case UP:
                System.out.println(tile.indexY);
                if(tile.indexY + 1 > tiles[0].length) {
                    return getTiles()[tile.indexX][tile.indexY + 1];
                }
                break;
            case DOWN:
                if(tile.indexY - 1 < tiles[0].length) {
                    return getTiles()[tile.indexX][tile.indexY - 1];
                }
        }
        return null;

    }

    private void initPath(){
        //MapTile castle = getCastle().get(0);


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
//                tiles[i][j].initTexture();/////////
            }
        }
    }

    private void initMap(final int number){
        MapLoader ml = new MapLoader(number);
        tiles = ml.loadMap();
        //setIndexTiles();//
        searchSpawner();
        searchCastle();
    }

    private void searchSpawner(){
        spawner = new ArrayList<MapTile>();
        for (int x = 0; x < tiles.length; x++){
            for (int y = 0; y < tiles[0].length; y++){
                if(tiles[x][y] instanceof Spawn){
                    spawner.add(tiles[x][y]);
                }
            }
        }
    }

    private void searchCastle(){//dont need yet
        castle = new ArrayList<MapTile>();
        for (int x = 0; x < tiles.length; x++){
            for (int y = 0; y < tiles[0].length; y++){
                if(tiles[x][y] instanceof Castle){
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
            drawTowerRange(batch);
        }

    }

    public void drawBuildGrid(SpriteBatch batch, float delta){
        Texture linePixel = GDefence.getInstance().assetLoader.get("buildGridLinePixel.png", Texture.class);
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


    private void drawTowerRange(SpriteBatch batch){//rework with draw in payload
        float x = payload.getValidDragActor().getX();
        float y = payload.getValidDragActor().getY();
        float width = payload.getValidDragActor().getWidth();
        float height = payload.getValidDragActor().getHeight();

        Circle attackRange = new Circle(x + width/2, y + height/2, rangeTower.getRange());
        batch.draw(GDefence.getInstance().assetLoader.get("towerRangeTexture.png", Texture.class), attackRange.x - attackRange.radius, attackRange.y - attackRange.radius,
                attackRange.radius*2, attackRange.radius*2);

    }
}
