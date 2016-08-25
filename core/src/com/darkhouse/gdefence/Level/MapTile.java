package com.darkhouse.gdefence.Level;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.darkhouse.gdefence.Helpers.AssetLoader;

public class MapTile {
    private int x, y, width, height;

    public enum TileType{
        ground, tree, grass, rock, water
    }
    public enum TileLogic {
        //R - right, L - left, U - up, D - down
        none, spawnerR, spawnerL, spawnerU, spawnerD, turnR, turnL, turnU, turnD, castle
    }

    private TextureRegionDrawable texture;
    private TileType type;
    private TileLogic logic;

    public TextureRegionDrawable getTexture() {
        return texture;
    }
    public void setTexture(TextureRegionDrawable texture) {
        this.texture = texture;
    }

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



    public MapTile(TileType type) {
        this.type = type;

        initTexture();
    }

    private void initTexture(){
        switch (type){
            case ground:
                setTexture(new TextureRegionDrawable(AssetLoader.ground));
                break;
            case grass:
                setTexture(new TextureRegionDrawable(AssetLoader.grass));
                break;
        }
    }

    public void setBounds(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }



    public void build(Tower tower){
        this.buildedTower = tower;
    }

    public void draw(float delta, SpriteBatch batch){
        //RectangleMapObject r = new RectangleMapObject(x, y, width, height);

        Image i = new Image(texture);
        i.setPosition(x, y);
        i.setSize(width, height);
        //System.out.println(getTexture().getName());

        i.draw(batch, 1);




    }
}
