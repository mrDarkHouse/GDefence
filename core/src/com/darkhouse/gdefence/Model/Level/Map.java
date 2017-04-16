package com.darkhouse.gdefence.Model.Level;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Level.Loader.MapLoader;
import com.darkhouse.gdefence.Level.Path.*;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Mob.Way;
import com.darkhouse.gdefence.Level.Tower.Projectile;
import com.darkhouse.gdefence.Objects.TowerObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Map {
    private boolean isBuild = false;
    private TowerObject rangeTower;
    private DragAndDrop.Payload payload;

    public void setBuild(boolean build, TowerObject rangeTower, DragAndDrop.Payload payload) {//
        isBuild = build;
        this.rangeTower = rangeTower;
        this.payload = payload;
    }

    private MapTile[][] tiles;
    public MapTile[][] getTiles() {
        return tiles;
    }
//    private HashMap<Mob.MoveType, Array<Array<Array<MapTile>>>> paths;//moveType, 1.Each spawn 2.Each possible path 3.Each maptile in path
    private HashMap<Mob.MoveType, Array<Path>> paths; //array of possible paths

    public HashMap<Mob.MoveType, Array<Path>> getPaths() {
        return paths;
    }

    //    public HashMap<Mob.MoveType, Array<Array<Array<MapTile>>>> getPaths() {
//        return paths;
//    }

    public static List<Projectile> projectiles;

    private int x;
    private int y;
    private int cellSize;
    private ArrayList<Spawn> spawner;
    private ArrayList<MapTile> castle;

    public ArrayList<Spawn> getSpawner() {
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

    public MapTile getFirstTile(MapTile mapTile1, MapTile mapTile2, Mob.MoveType prefType){//meaning that all tiles are Walkable
//        Walkable w1 = ((Walkable) mapTile1);
//        Walkable w2 = ((Walkable) mapTile2);

//        if(!w1.getApplyMobs().haveSame(w2.getApplyMobs()))throw new IllegalArgumentException(mapTile1 + " and " + mapTile2 +
//                " cant be in one path(applyMobs fields haven't same moveType)");
//
//        if(!Arrays.asList(w1.getApplyMobs().getSame(w2.getApplyMobs())).contains(prefType)) throw  new
//                IllegalArgumentException("prefType cannot exist in this types " + prefType);

//        for (Mob.MoveType mt:w1.getApplyMobs().getSame(w2.getApplyMobs())){
        int[] index = new int[2];
//            for (Array<Array<MapTile>> spawn:paths.get(prefType)){//can be shit//worked when was 2 dimension in paths hashmap
//                for (Array<MapTile> path:spawn) {
        for (Path path:paths.get(prefType)) {
            if (path.contains(mapTile1, true)) index[0] = path.indexOf(mapTile1, true);
            else index[0] = 99;//very very big
            if (path.contains(mapTile2, true)) index[1] = path.indexOf(mapTile2, true);
            else index[1] = 99;
//                    if(path.indexOf(mapTile1, true) < path.indexOf(mapTile2, true)) return mapTile1;
//                    else return mapTile2;//meaning that they different
            if (index[0] == index[1]) throw new IllegalArgumentException("Tiles identy or dont exist on way");
            return index[0] < index[1] ? mapTile1 : mapTile2;
//                }
//            }
//        }
        }
        throw new IllegalArgumentException("tiles dont cross");
    }

    private Array<MapTile> generatePath(Spawn spawner, Mob.MoveType type){
        GDefence.getInstance().log("Start generate path for " + type.name());
        Array<MapTile> path = new Array<MapTile>();
        path.add(spawner);

        while (!(path.peek() instanceof Castle)) {
            MapTile currentTile = path.peek();
            Walkable thisTile = ((Walkable) currentTile);
            Way manipulatedWay = thisTile.manipulatePath(type);
            int i = 1;//
            while(manipulatedWay == null){
                manipulatedWay = ((Walkable) path.get(path.size - 1 - i)).manipulatePath(type);//last - i
                i++;
            }
            int[] coord = Way.getCoordOffset(manipulatedWay, currentTile.getIndexX(), currentTile.getIndexY());
            MapTile checkTile = tiles[coord[0]][coord[1]];
            if (checkTile instanceof Walkable) path.add(checkTile);
            else throw new IllegalArgumentException("Path logic is wrong, bad tile is " + "x " + coord[0] + "y " + coord[1]);
        }
        GDefence.getInstance().log("End generate path for " + type.name());
        return path;
    }
    private void initPaths(Array<Mob.MoveType> types){
        GDefence.getInstance().log("Start init paths");
//        paths = new HashMap<Mob.MoveType, Array<Array<Array<MapTile>>>>();
        paths = new HashMap<Mob.MoveType, Array<Path>>();
        for (Mob.MoveType moveType:types){
//            Array<Array<Array<MapTile>>> forSpawner = new Array<Array<Array<MapTile>>>();
//            Array<Array<MapTile>> possiblePaths = new Array<Array<MapTile>>();
//            Array<MapTile> currentPath; //= new Array<MapTile>();
            Array<Path> possiblePaths = new Array<Path>();
            Path currentPath;
            for (int i = 0; i < spawner.size(); i++){
                while (true) {//rework with deleting break and infinity loop
                    currentPath = new Path(generatePath(spawner.get(i), moveType), i);
                    if (!possiblePaths.contains(currentPath, false)) {
                        possiblePaths.add(currentPath);//override contains
                    }
                    else break;
                }
            }
            paths.put(moveType, possiblePaths);
        }
        GDefence.getInstance().log("End init paths");
    }

    public void setIndexTiles(){
        for (int y = 0; y < tiles[0].length; y++){
            for (int x = 0; x < tiles.length; x++){
                tiles[x][y].setIndex(x, y);
            }
        }
    }
//    public MapTile getNextTile(MapTile tile, Way way){
//        switch (way){
//            case RIGHT:
//                if(tile.indexX + 1 > tiles.length) {
//                    return getTiles()[tile.indexX + 1][tile.indexY];
//                }
//                break;
//            case LEFT:
//                if(tile.indexX - 1 < tiles.length) {
//                    return getTiles()[tile.indexX - 1][tile.indexY];
//                }
//                break;
//            case UP:
//                System.out.println(tile.indexY);
//                if(tile.indexY + 1 > tiles[0].length) {
//                    return getTiles()[tile.indexX][tile.indexY + 1];
//                }
//                break;
//            case DOWN:
//                if(tile.indexY - 1 < tiles[0].length) {
//                    return getTiles()[tile.indexX][tile.indexY - 1];
//                }
//        }
//        return null;
//
//    }


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
        ml.loadProperties(ml.getSpawnersNumber(), false);//may do outside (in Level class)
        setIndexTiles();//
        searchSpawner();
        searchCastle();

        initPaths(ml.getMoveTypesInLevel());
//        normalizeTextures();
//        normalizeBlocks();



    }

    public void initBaseTextures(){
        for (MapTile[] ma:tiles){//can map.getTiles()
            for (MapTile m: ma) m.initTexture();
        }
    }

//    private void normalizeTextures(){
//        for (int y = 0; y < tiles[0].length; y++){
//            for (int x = 0; x < tiles.length; x++){
//                if(tiles[x][y] instanceof Turn){
//                    Texture texturePath = null;
//                    Turn turn = ((Turn) tiles[x][y]);
//                    Way startWay = turn.getStartWay();
//                    Way resultWay = turn.getResultWay();
//                    Way[] leastWays = Way.getLeastWays(new Way[]{Way.invertWay(startWay), resultWay});
//                    for (Way leastWay : leastWays) {
//                        int[] coord = Way.getCoordOffset(leastWay, x, y);
//                        if (coord[0] >= 0 && coord[0] < tiles.length && coord[1] >= 0 && coord[1] < tiles[0].length) {
//                            MapTile checkTile = tiles[coord[0]][coord[1]];
//                            if (checkTile != null && checkTile instanceof Walkable) {
//                                if (checkTile instanceof Road) {
//                                    if(turn.getApplyMobs() == TargetType.WATER_ONLY) {
//                                        texturePath = GDefence.getInstance().assetLoader.get("Path/Turn/turnWaterGround" +
//                                                Turn.getTurnCode(startWay, resultWay) + leastWay.getShortName() + ".png", Texture.class);
////                                        texturePath.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
//                                        break;
//                                    }else if(turn.getApplyMobs() == TargetType.GROUND_ONLY || turn.getApplyMobs() == TargetType.GROUND_WATER){
//                                        texturePath = GDefence.getInstance().assetLoader.get("Path/Turn/bridge" +
//                                                Turn.getTripleTurnCode(startWay, resultWay, leastWay) + "noArrows.png", Texture.class);
//                                        break;
//                                    }
//                                } else /*if (checkTile instanceof WaterRoad)*/ {
//                                    if(turn.getApplyMobs() == TargetType.WATER_ONLY) {
//                                        texturePath = GDefence.getInstance().assetLoader.get("Path/Turn/waterBridge" +
//                                                Turn.getTripleTurnCode(startWay, resultWay, leastWay) + "noArrows.png", Texture.class);
//                                        break;
//                                    }else if(turn.getApplyMobs() == TargetType.GROUND_ONLY || turn.getApplyMobs() == TargetType.GROUND_WATER){
//                                        texturePath = GDefence.getInstance().assetLoader.get("Path/Turn/turnGroundWater" +
//                                                Turn.getTurnCode(startWay, resultWay) + leastWay.getShortName() + ".png", Texture.class);
////                                        texturePath.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
//                                        break;
//                                    }
//                                }
//
//                            }
//                        }
//                    }
//                    if(texturePath!= null) turn.setRegion(texturePath);
//
//                }
//            }
//        }
//    }

    public void normalizeBlocks(){
        for (int y = 0; y < tiles[0].length; y++) {
            for (int x = 0; x < tiles.length; x++) {
                if(tiles[x][y] instanceof Road) {
                    Texture texture = null;
                    Road road = ((Road) tiles[x][y]);
                    Way[] leastWays = Way.getLeastWays(new Way[]{});
                    Array<MapTile> rightTiles = new Array<MapTile>();
                    for (Way leastWay : leastWays) {
                        int[] coord = Way.getCoordOffset(leastWay, x, y);
                        if (coord[0] >= 0 && coord[0] < tiles.length && coord[1] >= 0 && coord[1] < tiles[0].length) {
                            MapTile checkTile = tiles[coord[0]][coord[1]];
                            if (checkTile != null && checkTile instanceof Walkable) {
                                rightTiles.add(checkTile);//TODO must check and dont connect roads like ||||
                            }
                        }
                    }
                    switch (rightTiles.size){
                        case 2://turn
                            if(rightTiles.get(0).getIndexX() == rightTiles.get(1).getIndexX() ||
                                    rightTiles.get(0).getIndexY() == rightTiles.get(1).getIndexY()) {//check for line road (--- or -/n-/n- )
                                break;
                            }
//                            if(rightTiles.get(0).isSwimmable() && rightTiles.get(1).isSwimmable() && road.isSwimmable()){
//                                String turnCode = Turn.getTurnCode(Way.invertWay(Way.getNearBlockWay(road, rightTiles.get(0))),
//                                        Way.getNearBlockWay(road, rightTiles.get(1)));//different names
//                                texturePath = GDefence.getInstance().assetLoader.get("Path/Turn/turnWater" +
//                                        turnCode + ".png", Texture.class);
//                                break;
//                            }
//                            if(!rightTiles.get(0).isSwimmable() && !rightTiles.get(1).isSwimmable() && !road.isSwimmable()){
//                                String turnCode = Turn.getTurnCode(Way.invertWay(Way.getNearBlockWay(road, rightTiles.get(0))),
//                                        Way.getNearBlockWay(road, rightTiles.get(1)));//different names
//                                texturePath = GDefence.getInstance().assetLoader.get("Path/Turn/turn" +
//                                        turnCode + ".png", Texture.class);
//                                break;
//                            }
                            if(road.isSwimmable()){
                                String turnCode = Turn.getTurnCode(Way.invertWay(Way.getNearBlockWay(road, rightTiles.get(0))),
                                        Way.getNearBlockWay(road, rightTiles.get(1)));//different names
                                texture = GDefence.getInstance().assetLoader.getTurn("Path/Turn/turnWater" +
                                        turnCode + ".png");
                            }
                            if(!road.isSwimmable()){
                                String turnCode = Turn.getTurnCode(Way.invertWay(Way.getNearBlockWay(road, rightTiles.get(0))),
                                        Way.getNearBlockWay(road, rightTiles.get(1)));//different names
                                texture = GDefence.getInstance().assetLoader.getTurn("Path/Turn/turn" +
                                        turnCode + ".png");
                            }
                            break;
                        case 3://tripleTurn
                            MapTile tile[] = {rightTiles.get(0), rightTiles.get(1), rightTiles.get(2)};
                            if(tile[0].isSwimmable() && tile[1].isSwimmable() && tile[2].isSwimmable() && road.isSwimmable()){
                                String turnCode = Turn.getTripleTurnCode(Way.invertWay(Way.getNearBlockWay(road, tile[0])),//invert need to do same names
                                        Way.getNearBlockWay(road, tile[1]), Way.getNearBlockWay(road, tile[2]));
                                texture = GDefence.getInstance().assetLoader.getTurn("Path/Turn/waterBridge" +
                                        turnCode + "noArrows.png");
                                break;
                            }
                            if(!tile[0].isSwimmable() && !tile[1].isSwimmable() && !tile[2].isSwimmable() && !road.isSwimmable()){
                                String turnCode = Turn.getTripleTurnCode(Way.invertWay(Way.getNearBlockWay(road, tile[0])),//invert need to do same names
                                        Way.getNearBlockWay(road, tile[1]), Way.getNearBlockWay(road, tile[2]));
                                texture = GDefence.getInstance().assetLoader.getTurn("Path/Turn/bridge" +
                                        turnCode + "noArrows.png");
                                break;
                            }
                            if(road.isSwimmable()){
                                String turnCode = null;

                                Array<MapTile> swimmable = new Array<MapTile>();
                                Array<MapTile> noSwimmable = new Array<MapTile>();
                                for (MapTile mt:tile){
                                    if(mt.isSwimmable())swimmable.add(mt);
                                    else noSwimmable.add(mt);
                                }
                                switch (swimmable.size){
                                    case 2:
                                        turnCode = Turn.getTurnCode(Way.invertWay(Way.getNearBlockWay(road, swimmable.get(0))),
                                                Way.getNearBlockWay(road, swimmable.get(1))) +
                                                Way.getNearBlockWay(road, noSwimmable.get(0)).getShortName();
                                        break;
                                    case 1:
                                        MapTile firstNoSwimmable = getFirstTile(noSwimmable.get(0), noSwimmable.get(1), Mob.MoveType.water);
                                        noSwimmable.removeValue(firstNoSwimmable, true);//or false
                                        MapTile secondNoSwimmable = noSwimmable.get(0);
                                        turnCode = Turn.getTurnCode(Way.invertWay(Way.getNearBlockWay(road, firstNoSwimmable)),
                                                Way.getNearBlockWay(road, swimmable.get(0))) + Way.getNearBlockWay(road, secondNoSwimmable).getShortName();
//                                        for (int i = 0; i < noSwimmable.size; i++){
//                                            if(Way.getNearBlockWay(road, noSwimmable.get(i)) != Way.invertWay(startWay)){
//                                                turnCode += Way.getNearBlockWay(road, noSwimmable.get(i)).getShortName();
//                                            }
//                                        }
                                        break;
                                }
                                if(turnCode!= null) {
                                    texture = GDefence.getInstance().assetLoader.getTurn("Path/Turn/turnWaterGround" + turnCode + ".png");
                                    break;
                                }
                            }
                            if(!road.isSwimmable()) {
                                String turnCode = null;

                                Array<MapTile> noSwimmable = new Array<MapTile>();
                                Array<MapTile> swimmable = new Array<MapTile>();
                                for (MapTile mt:tile){
                                    if(mt.isSwimmable())swimmable.add(mt);
                                    else noSwimmable.add(mt);
                                }
                                switch (noSwimmable.size){
                                    case 2:
                                        turnCode = Turn.getTurnCode(Way.invertWay(Way.getNearBlockWay(road, noSwimmable.get(0))),
                                                Way.getNearBlockWay(road, noSwimmable.get(1))) +
                                                Way.getNearBlockWay(road, swimmable.get(0)).getShortName();
                                        break;
                                    case 1:
                                        MapTile firstSwimmable = getFirstTile(swimmable.get(0), swimmable.get(1), Mob.MoveType.ground);
                                        noSwimmable.removeValue(firstSwimmable, true);//or false
                                        MapTile secondSwimmable = swimmable.get(0);
                                        turnCode = Turn.getTurnCode(Way.invertWay(Way.getNearBlockWay(road, firstSwimmable)),
                                                Way.getNearBlockWay(road, noSwimmable.get(0))) + Way.getNearBlockWay(road, secondSwimmable).getShortName();
//                                        for (int i = 0; i < noSwimmable.size; i++){
//                                            if(Way.getNearBlockWay(road, noSwimmable.get(i)) != Way.invertWay(startWay)){
//                                                turnCode += Way.getNearBlockWay(road, noSwimmable.get(i)).getShortName();
//                                            }
//                                        }
                                        break;
                                }
                                if(turnCode!= null) {
                                    texture = GDefence.getInstance().assetLoader.getTurn("Path/Turn/turnGroundWater" + turnCode + ".png");
                                    break;
                                }
                            }



                            break;
                        case 4://cross

                            break;
                    }
                    if(texture != null)road.setRegion(texture);
                }
            }
        }

    }

    private void searchSpawner(){
        spawner = new ArrayList<Spawn>();
        for (int y = 0; y < tiles[0].length; y++){
            for (int x = 0; x < tiles.length; x++){
                if(tiles[x][y] instanceof Spawn){
                    spawner.add(((Spawn) tiles[x][y]));
                }
            }
        }
    }

    private void searchCastle(){//dont need yet
        castle = new ArrayList<MapTile>();
        for (int y = 0; y < tiles[0].length; y++){
            for (int x = 0; x < tiles.length; x++){
                if(tiles[x][y] instanceof Castle){
                    castle.add(tiles[x][y]);
                }
            }
        }
    }

    public void draw(float delta, SpriteBatch batch){
        for (int y = 0; y < tiles[0].length; y++){
            for (int x = 0; x < tiles.length; x++){
                tiles[x][y].draw(batch, delta);
            }
        }
        for (int y = 0; y < tiles[0].length; y++){
            for (int x = 0; x < tiles.length; x++){
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
        for (int y = 0; y < tiles[0].length; y++){
            for (int x = 0; x < tiles.length; x++){
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
