package com.darkhouse.gdefence.Level;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;
import com.darkhouse.gdefence.Level.Tower.Tower;
import com.darkhouse.gdefence.Model.GDSprite;
import com.darkhouse.gdefence.Screens.LevelMap;

public class MapTile extends GDSprite{
    //private int x, y, width, height;

    //public int getX() {
    //    return x;
    //}
    //public int getY() {
    //    return y;
    //}

    public enum TileType{
        ground, tree, grass, rock, water
    }
    public enum TileLogic {
        //R - right, L - left, U - up, D - down
        none, spawnerR, spawnerL, spawnerU, spawnerD, turnR, turnL, turnU, turnD, castle
    }

    //private TextureRegionDrawable texture;
    private TileType type;
    private TileLogic logic;

    public int indexX;
    public int indexY;



    public void setIndex(int indexX, int indexY) {
        this.indexX = indexX;
        this.indexY = indexY;
    }


    //public TextureRegionDrawable getTexture() {
    //    return texture;
    //}
    //public void setTexture(TextureRegionDrawable texture) {
    //    this.texture = texture;
    //}

    public TileType getType() {
        return type;
    }
    public TileLogic getLogic() {
        return logic;
    }
    public void setType(TileType type) {
        this.type = type;
    }
    public void setLogic(TileLogic logic) {
        this.logic = logic;
    }

    public static TileType getTypeById(int id){
        switch (id){
            case 0:
                return TileType.grass;
            case 1:
                return TileType.ground;
            case 2:
                return TileType.rock;
            case 3:
                return TileType.grass;
            case 4:
                return TileType.water;
            default:
                return null;
        }
    }

    public static TileLogic getLogicById(int id){
        switch (id){
            case 0:
                return TileLogic.none;
            case 1:
                return TileLogic.spawnerR;
            case 2:
                return TileLogic.spawnerL;
            case 3:
                return TileLogic.spawnerU;
            case 4:
                return TileLogic.spawnerD;
            case 10:
                return TileLogic.turnR;
            case 11:
                return TileLogic.turnL;
            case 12:
                return TileLogic.turnU;
            case 13:
                return TileLogic.turnD;
            case 99:
                return TileLogic.castle;
            default:
                return null;
        }
    }

    public boolean isBuildable(){
        switch (type){
            case ground:
                return false;//if level type standart
            case tree:
                return false;
            case grass:
                return true;
            case rock:
                return false;
            case water:
                return false;
        }
        throw new RuntimeException("wrong type");
    }

    public boolean isWalkable(){
        switch (type){
            case ground:
                return true;
            case tree:
                return false;
            case grass:
                return false;
            case rock:
                return false;
            case water:
                return false;
        }
        throw new RuntimeException("wrong type");
    }

    public boolean isSwimmable(){
        switch (type){
            case ground:
                return false;
            case tree:
                return false;
            case grass:
                return false;
            case rock:
                return false;
            case water:
                return true;
        }
        throw new RuntimeException("wrong type");
    }

    private Tower buildedTower;

    public Tower getBuildedTower() {
        return buildedTower;
    }

    public MapTile(TileType type) {
        setType(type);

        initTexture();
    }

    private void initTexture(){
        switch (type){
            case ground:
                setRegion(AssetLoader.ground);
                break;
            case grass:
                setRegion(AssetLoader.grass);
                break;
        }
    }

    public void setBounds(int x, int y, int width, int height){
        setPosition(x, y);
        setSize(width, height);
    }



    public boolean build(ItemEnum.Tower tower){
        if(isBuildable() && buildedTower == null && LevelMap.getLevel().removeEnergy(tower.getCost())) {//tooltip "no enought enegry", "cannot build there"
            this.buildedTower = new Tower(tower, getX(), getY(), getWidth(), getHeight());
            LevelMap.getLevel().getStatManager().energySpendAdd(tower.getCost());
            return true;
        }else return false;
    }

    public void draw(SpriteBatch batch, float delta){
        draw(batch);
        Image f;

        if(buildedTower != null) {
            buildedTower.physic(delta);
            buildedTower.draw(batch, delta);
        }
//        if(towerMask != null){//
//
//            return;
//        }

        switch (logic){
            case spawnerR:
                break;
            case spawnerL:

                break;
            case spawnerU:

                break;
            case spawnerD:

                break;
            case turnR:

                break;
            case turnL:

                break;
            case turnU:

                break;
            case turnD:

                break;
            case castle:
                f = new Image(AssetLoader.castle);
                f.setPosition(getX(), getY());
                f.setSize(getWidth(), getHeight());
                f.draw(batch, 1);
                break;
        }




    }
}
