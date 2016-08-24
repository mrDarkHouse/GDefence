package com.darkhouse.gdefence.Level;


import com.badlogic.gdx.graphics.Texture;
import com.darkhouse.gdefence.Level.Tower;

public class MapTile {

    public enum tileType{
        ground, tree, grass, rock, water
    }
    public enum tileLogic{
        //R - right, L - left, U - up, D - down
        none, spawnerR, spawnerL, spawnerU, spawnerD, turnR, turnL, turnU, turnD, castle
    }

    private Texture texture;
    private tileType type;
    private tileLogic logic;

    public tileType getType() {
        return type;
    }
    public tileLogic getLogic() {
        return logic;
    }
    public void setType(tileType type) {
        this.type = type;
    }
    public void setLogic(tileLogic logic) {
        this.logic = logic;
    }

    public static tileType getTypeById(int id){
        switch (id){
            case 0:
                return tileType.grass;
            case 1:
                return tileType.ground;
            case 2:
                return tileType.rock;
            case 3:
                return tileType.grass;
            case 4:
                return tileType.water;
            default:
                return null;
        }
    }

    public static tileLogic getLogicById(int id){
        switch (id){
            case 0:
                return tileLogic.none;
            case 1:
                return tileLogic.spawnerR;
            case 2:
                return tileLogic.spawnerL;
            case 3:
                return tileLogic.spawnerU;
            case 4:
                return tileLogic.spawnerD;
            case 10:
                return tileLogic.turnR;
            case 11:
                return tileLogic.turnL;
            case 12:
                return tileLogic.turnU;
            case 13:
                return tileLogic.turnD;
            case 99:
                return tileLogic.castle;
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



    public MapTile(tileType type) {
        this.type = type;
    }



    public void build(Tower tower){
        this.buildedTower = tower;
    }

    public void render(float delta){
        //draw texture
    }
}
