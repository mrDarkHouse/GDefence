package com.darkhouse.gdefence.Level;


import com.badlogic.gdx.graphics.Texture;
import com.darkhouse.gdefence.Level.Tower;

public class MapTile {

    public enum tileType{
        ground, tree, grass, rock, water
    }

    private Texture texture;
    private tileType type;

    public tileType getType() {
        return type;
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
