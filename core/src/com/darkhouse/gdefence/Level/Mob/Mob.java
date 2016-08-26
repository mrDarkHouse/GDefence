package com.darkhouse.gdefence.Level.Mob;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.darkhouse.gdefence.Level.MapTile;

public abstract class Mob {

    //private Way way = Way.right;
    private Drawable texture;


    //public int healthSpace = 3, healthHeight = 5;
    protected String name;
    protected int health;
    protected int speed;
    protected int dmg;
    protected int ID;
    protected int bounty;
    //private double pxlPerHealth;
    protected boolean inGame = false;
    //protected int xC, yC;
    protected MapTile currentTile;
    protected int x;
    protected int y;
    protected int width;
    protected int height;


    public Drawable getTexture() {
        return texture;
    }
    public void setTexture(Drawable texture) {
        this.texture = texture;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public int getDmg() {
        return dmg;
    }
    public void setDmg(int dmg) {
        this.dmg = dmg;
    }
    public int getBounty() {
        return bounty;
    }
    public void setBounty(int bounty) {
        this.bounty = bounty;
    }
    public boolean isInGame() {
        return inGame;
    }
    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }
//    public int getxC() {
//        return xC;
//    }
//    public void setxC(int xC) {
//        this.xC = xC;
//    }
//    public int getyC() {
//        return yC;
//    }
//    public void setyC(int yC) {
//        this.yC = yC;
//    }
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }


    public static Mob getMobById(int ID){
        switch (ID){
            case 0:
                return new Slime();
            case 1:
                return null;
            case 2:

            case 3:

            case 4:

            case 5:

            default:
                return null;
        }
    }

//    public Mob() {
//    }

    public void spawn(MapTile spawner){

    }


    public void draw(SpriteBatch batch){
        Image i = new Image();
        i.setSize(width, height);
        //i.setPosition(currentTile.getX(), currentTile.getY());
        i.setPosition(x, y);
        i.draw(batch, 1);
    }


}
