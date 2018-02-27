package com.darkhouse.gdefence.Level.Path;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;
import com.darkhouse.gdefence.Level.Level;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Mob.Way;
import com.darkhouse.gdefence.Level.Tower.Tower;
import com.darkhouse.gdefence.Model.GDSprite;
import com.darkhouse.gdefence.Model.Level.Map;
import com.darkhouse.gdefence.Objects.TowerObject;
import com.darkhouse.gdefence.Screens.LevelMap;

import java.util.ArrayList;

public abstract class MapTile extends GDSprite{

    public enum Logic{
        Spawner, Castle, Turn, Road, Portal
    }
    public Logic getLogic(){return Logic.Road;};

    public MapTile getInstance(){return this;};

    //private int x, y, width, height;

    //public int getX() {
    //    return x;
    //}
    //public int getY() {
    //    return y;
    //}

//    public enum TileType{
//        grass, ground/*, tree, rock, water*/
//    }
//    public enum TileLogic {
//        //R - right, L - left, U - up, D - down
//        none, spawnerR, spawnerL, spawnerU, spawnerD, turnR, turnL, turnU, turnD, bridgeL, bridgeR, bridgeU, bridgeD, castle
//    }

    //private TextureRegionDrawable texturePath;
//    private TileType type;
//    private TileLogic logic;

    private int indexX;
    private int indexY;

    public int getIndexX() {
        return indexX;
    }
    public int getIndexY() {
        return indexY;
    }

    public void setIndex(int indexX, int indexY) {
        this.indexX = indexX;
        this.indexY = indexY;
    }


    //public TextureRegionDrawable getTexture() {
    //    return texturePath;
    //}
    //public void setTexture(TextureRegionDrawable texturePath) {
    //    this.texturePath = texturePath;
    //}

//    public TileType getType() {
//        return type;
//    }
//    public TileLogic getLogic() {
//        return logic;
//    }
//    public void setType(TileType type) {
//        this.type = type;
//    }
//    public void setLogic(TileLogic logic) {
//        this.logic = logic;
//    }

//    public static TileType getTypeById(int id){
//        return TileType.values()[id];
////        switch (id){
////            case 0:
////                return TileType.grass;
////            case 1:
////                return TileType.ground;
////            case 2:
////                return TileType.rock;
////            case 3:
////                return TileType.water;
////            default:
////                return null;
////        }
//    }
    public static MapTile generateTile(String loadCode){
        String[] tryMulti = loadCode.split("z");
//        System.out.println(tryMulti[0]);

        if(tryMulti.length > 1){
//            String[] additional = loadCode.split("|");
//            System.out.println(additional[0] + " " + additional[1]);
//            additional[0] = additional[0].substring(2);//removing 23:
//                Array<Turn> turns = new Array<Turn>();

            if(Integer.parseInt(tryMulti[0].substring(0, 2)) == 80){
                Array<WalkableMapTile> m = new Array<WalkableMapTile>();
                for (int i = 1; i < tryMulti.length; i++){
                    m.add(((WalkableMapTile) generateTile(tryMulti[i])));//must be walkable
                }
                return new Spawn(TargetType.values()[Integer.parseInt(tryMulti[0].substring(3, 4))], m);
            }

            Turn[] turns = new Turn[tryMulti.length];
            for (int i = 0; i < tryMulti.length; i++){
//                String code = tryMulti[i].substring(0, 2);
//                if(Integer.parseInt(code) == 24)
//                MapTile m = generateTile(tryMulti[i]);
//                if(m instanceof B)
                turns[i] = ((Turn) generateTile(tryMulti[i]));//must be turn (20 21 22)
            }
            return new MultiTurn(turns);
        }

        String[] info = loadCode.split(":");
        int id = Integer.parseInt(info[0]);

        switch (id){
            case 0:case 1:case 2:case 3:
                return new Grass(id);
            case 18:
                switch (info.length){
                    case 1: return new Road();
                    case 2: return new Road(TargetType.values()[Integer.parseInt(info[1])]);
                }
//                return new Road();
//            case 19:
//                return new Road(TargetType.values()[Integer.parseInt(info[1])]);
//                if(Integer.parseInt(info[1]) == 5) return new Road(null, TargetType.values()[Integer.parseInt(info[2])]);
//                else return new Road(Way.values()[Integer.parseInt(info[1])], TargetType.values()[Integer.parseInt(info[2])]);
//            case 19:
//                return new Road(Way.values()[Integer.parseInt(info[1])], Way.values()[Integer.parseInt(info[2])], TargetType.values()[Integer.parseInt(info[3])]);
            case 21:
                switch (info.length){
                    case 3:return new Turn(null, Way.values()[Integer.parseInt(info[1])], false, TargetType.values()[Integer.parseInt(info[2])]);
//                    case 4:return new Turn(Way.values()[Integer.parseInt(info[1])], Way.values()[Integer.parseInt(info[2])], true, TargetType.values()[Integer.parseInt(info[3])]);
                    case 6:return new Turn(Way.values()[Integer.parseInt(info[1])], Way.values()[Integer.parseInt(info[2])],
                            Way.values()[Integer.parseInt(info[3])], Way.values()[Integer.parseInt(info[4])], TargetType.values()[Integer.parseInt(info[5])]);
                }

//                return new Turn(null, Way.values()[Integer.parseInt(info[1])], false, TargetType.values()[Integer.parseInt(info[2])]);

            case 22:
                return new Turn(Way.values()[Integer.parseInt(info[1])], Way.values()[Integer.parseInt(info[2])], true, TargetType.values()[Integer.parseInt(info[3])]);
            case 20://23
                return new Turn(Way.values()[Integer.parseInt(info[1])], Way.values()[Integer.parseInt(info[2])], false, TargetType.values()[Integer.parseInt(info[3])]);
            case 23://20
//                String[] additional = loadCode.split("|");
//                System.out.println(additional[0] + " " + additional[1]);
//                additional[0] = additional[0].substring(2);//removing 23:
////                Array<Turn> turns = new Array<Turn>();
//                Turn[] turns = new Turn[additional.length];
//                for (int i = 0; i < additional.length; i++){
//                    turns[i] = ((Turn) generateTile(additional[i]));//must be turn (20 21 22)
//                }
//                return new MultiTurn(turns);
            case 24:
                switch (info.length){
                    case 5: return new Bridge(null, Way.values()[Integer.parseInt(info[1])],//not ready now
                            Way.values()[Integer.parseInt(info[2])], TargetType.values()[Integer.parseInt(info[3])], Integer.parseInt(info[4]));//all input ways
                    case 6: return new Bridge(Way.values()[Integer.parseInt(info[1])], Way.values()[Integer.parseInt(info[2])],
                            Way.values()[Integer.parseInt(info[3])], TargetType.values()[Integer.parseInt(info[4])], Integer.parseInt(info[5]));
                    case 3: return new Bridge(TargetType.values()[Integer.parseInt(info[1])],Integer.parseInt(info[2]));
                }
//                return new Bridge(Way.values()[Integer.parseInt(info[1])], Way.values()[Integer.parseInt(info[2])],
//                        Way.values()[Integer.parseInt(info[3])], TargetType.values()[Integer.parseInt(info[4])],Integer.parseInt(info[5]));
//            case 19:
//                return new WaterRoad(Way.values()[Integer.parseInt(info[1])]);
            case 50:
                return new Decor(Integer.parseInt(info[1]));
            case 60:
                switch (info.length) {
                    case 3:return new Portal(Way.values()[Integer.parseInt(info[1])], Integer.parseInt(info[2]));
                    case 5:return new Portal(Way.values()[Integer.parseInt(info[1])], Integer.parseInt(info[2]), Integer.parseInt(info[3]), Integer.parseInt(info[4]));
                }
            case 80:
                switch (info.length){
//                    case 2:return new Spawn(TargetType.values()[Integer.parseInt(info[1])]);
                    case 3:return new Spawn(Way.values()[Integer.parseInt(info[1])], TargetType.values()[Integer.parseInt(info[2])]);
                }
            case 99:
                return new Castle();
            default:
                return null;
        }
    }

    public static String getSignerCode(MapTile prev, MapTile t, MapTile next){
//        AssetLoader a = GDefence.getInstance().assetLoader;
//        Way d = ((Walkable) t).manipulatePath(Mob.MoveType.ground);

//      if(t.getTexture() == a.get("Path/Turn/turnLU.png", Texture.class))return d == Way.RIGHT?"DR":"LU";
//        if(Way.getNearBlockWay(t, prev) == Way.LEFT && Way.getNearBlockWay(t, next) == Way.RIGHT) return "R";
//        if(Way.getNearBlockWay(t, prev) == Way.RIGHT && Way.getNearBlockWay(t, next) == Way.LEFT) return "L";
//        if(Way.getNearBlockWay(t, prev) == Way.UP && Way.getNearBlockWay(t, next) == Way.DOWN) return "D";
//        if(Way.getNearBlockWay(t, prev) == Way.DOWN && Way.getNearBlockWay(t, next) == Way.UP) return "U";

        if(prev == null) return Way.getNearBlockWay(t, next).getShortName() + Way.getNearBlockWay(t, next).getShortName();
        if(next == null) return Way.invertWay(Way.getNearBlockWay(t, prev)).getShortName() + Way.invertWay(Way.getNearBlockWay(t, prev)).getShortName();
        if (Way.getNearBlockWay(t, prev) == null || Way.getNearBlockWay(t, next) == null) return null;//tiles dont connect (portals)
        else return Way.invertWay(Way.getNearBlockWay(t, prev)).getShortName() + Way.getNearBlockWay(t, next).getShortName();
    }

//    public static TileLogic getLogicById(int id){
//        return TileLogic.values()[id];//99
////        switch (id){
////            case 0:
////                return TileLogic.none;
////            case 1:
////                return TileLogic.spawnerR;
////            case 2:
////                return TileLogic.spawnerL;
////            case 3:
////                return TileLogic.spawnerU;
////            case 4:
////                return TileLogic.spawnerD;
////            case 10:
////                return TileLogic.turnR;
////            case 11:
////                return TileLogic.turnL;
////            case 12:
////                return TileLogic.turnU;
////            case 13:
////                return TileLogic.turnD;
////            case 99:
////                return TileLogic.castle;
////            default:
////                return null;
////        }
//    }

    public abstract boolean isBuildable();
//        switch (type){
//            case grass:
//                return true;
//            case ground:
//                return false;//if level type standart
//            case tree:
//                return false;
//            case rock:
//                return false;
//            case water:
//                return false;
//        }
//        throw new RuntimeException("wrong type");

//    public abstract boolean isWalkable();
//        switch (type){
//            case grass:
//                return false;
//            case ground:
//                return true;
////            case tree:
////                return false;
////            case rock:
////                return false;
////            case water:
////                return false;
//        }
//        throw new RuntimeException("wrong type");
//    }

//        switch (type){
//            case grass:
//                return false;
//            case ground:
//                return false;
////            case tree:
////                return false;
////            case rock:
////                return false;
////            case water:
////                return true;
//        }
//        throw new RuntimeException("wrong type");
//    }

    private Tower buildedTower;
    private Map owner;
    public Tower getBuildedTower() {
        return buildedTower;
    }

    public MapTile(/*TileType type*/) {
//        this.owner
//        setType(type);

//        initTexture();
    }

    public void setOwner(Map owner) {
        this.owner = owner;
    }

    public abstract void initTexture();
//        switch (type){
//            case ground:
//                setRegion(GDefence.getInstance().assetLoader.get("ground.png", Texture.class));
//                break;
//            case grass:
//                setRegion(GDefence.getInstance().assetLoader.get("grass.png", Texture.class));
//                break;
//        }
//    }

    public void setBounds(int x, int y, int width, int height){
        setPosition(x, y);
        setSize(width, height);
    }



    public boolean build(TowerObject tower){
        if(isBuildable() && buildedTower == null && LevelMap.getLevel().removeEnergy(tower.getCost())) {//tooltip "no enought enegry", "cannot build there"
            Tower t = new Tower(tower, getX(), getY(), getWidth(), getHeight());
            LevelMap.levelMap.getStage().addActor(t);//TODO
            this.buildedTower = t;
            t.init(owner);
            t.procBuildAbilities(this);
            for (Tower tw: Level.getMap().getTowersOnMap()){
                tw.procBuildOnMapAbilities(t);
            }


//            LevelMap.getLevel().getStatManager().energySpendAdd(tower.getCost());
            return true;
        }else return false;
    }

    public void draw(SpriteBatch batch/*, float delta*/){
//        draw(batch, 1f);
        super.draw(batch, 1f);

//        if(getBuildedTower() != null) {
//            getBuildedTower().drawRange(batch, delta);
//        }


    }

    @Override
    public String toString() {
        return super.toString() + " x:" + getIndexX() + " y:" + getIndexY();
    }
}
