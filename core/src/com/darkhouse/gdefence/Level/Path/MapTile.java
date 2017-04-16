package com.darkhouse.gdefence.Level.Path;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Mob.Way;
import com.darkhouse.gdefence.Level.Tower.Tower;
import com.darkhouse.gdefence.Model.GDSprite;
import com.darkhouse.gdefence.Objects.TowerObject;
import com.darkhouse.gdefence.Screens.LevelMap;

public abstract class MapTile extends GDSprite{
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
        String[] info = loadCode.split(":");
        int id = Integer.parseInt(info[0]);

        switch (id){
            case 0:
                return new Grass();
            case 18:
                return new Road(Way.values()[Integer.parseInt(info[1])], TargetType.values()[Integer.parseInt(info[2])]);
            case 20:
                return new Turn(Way.values()[Integer.parseInt(info[1])], Way.values()[Integer.parseInt(info[2])],
                        TargetType.values()[Integer.parseInt(info[3])]);
            case 24:
                return new Bridge(Way.values()[Integer.parseInt(info[1])], Way.values()[Integer.parseInt(info[2])],
                        Way.values()[Integer.parseInt(info[3])], TargetType.values()[Integer.parseInt(info[4])],Integer.parseInt(info[5]));
//            case 19:
//                return new WaterRoad(Way.values()[Integer.parseInt(info[1])]);
            case 50:
                return new Decor(Integer.parseInt(info[1]));
            case 80:
                return new Spawn(Way.values()[Integer.parseInt(info[1])], TargetType.values()[Integer.parseInt(info[2])]);
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
        return Way.invertWay(Way.getNearBlockWay(t, prev)).getShortName() + Way.getNearBlockWay(t, next).getShortName();
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
    public abstract boolean isSwimmable();
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
    public Tower getBuildedTower() {
        return buildedTower;
    }

    public MapTile(/*TileType type*/) {
//        setType(type);

//        initTexture();
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
            t.init();
            t.procBuildAbilities(this);
            this.buildedTower = t;
            LevelMap.getLevel().getStatManager().energySpendAdd(tower.getCost());
            return true;
        }else return false;
    }

    public void draw(SpriteBatch batch, float delta){
        draw(batch);
//        Image f;

        if(buildedTower != null) {
            buildedTower.physic(delta);
            buildedTower.draw(batch, delta);
        }
//        if(towerMask != null){//
//
//            return;
//        }

//        switch (logic){
//            case spawnerR:
//                break;
//            case spawnerL:
//
//                break;
//            case spawnerU:
//
//                break;
//            case spawnerD:
//
//                break;
//            case turnR:
//
//                break;
//            case turnL:
//
//                break;
//            case turnU:
//
//                break;
//            case turnD:
//
//                break;
//            case castle:
//                f = new Image(GDefence.getInstance().assetLoader.get("castle.png", Texture.class));
//                f.setPosition(getX(), getY());
//                f.setSize(getWidth(), getHeight());
//                f.draw(batch, 1);
//                break;
//        }




    }
}
