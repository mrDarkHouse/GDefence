package com.darkhouse.gdefence.Level.Mob;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Level.Level;
import com.darkhouse.gdefence.Level.MapTile;
import com.darkhouse.gdefence.Level.Tower.AttackLogic;
import com.darkhouse.gdefence.Level.Tower.Tower;
import com.darkhouse.gdefence.Level.Wave;
import com.darkhouse.gdefence.Model.GDSprite;
import com.darkhouse.gdefence.Model.Level.Map;
import com.darkhouse.gdefence.Screens.LevelMap;

import java.awt.*;

public abstract class Mob extends GDSprite{

    public static Mob getMobOnMap(AttackLogic logic, Tower tower){
        Map map = Level.getMap();

        switch (logic){
            case First:
                for (Mob m:Wave.mobs) {
                    if(tower.isInRange(m.getCenter())){
                        Vector2 castle = map.getCastle().get(0).getPosition();

                        //bla bla bla
                    }
                }




                break;
            case Nearest:
                Mob currentMob = null;
                float currentF = 99999;//infinity
                for (Mob m:Wave.mobs) {
                    if(tower.isInRange(m.getCenter())){
                        //if(new Vector2(tower.getRectangle().getCenter(new Vector2())))
                        if(m.getCenter().dst(tower.getCenter()) < currentF){
                            currentMob = m;
                        }
                    }
                }
                return currentMob;
            }
        return null;
    }





    //private Way way = Way.right;
    private Texture texture;


    //public int healthSpace = 3, healthHeight = 5;
    protected String name;
    protected int health;
    protected int armor;
    protected float speed;
    protected int dmg;
    protected int ID;
    protected int bounty;
    //private double pxlPerHealth;
    protected boolean inGame = true;
    //protected int xC, yC;
    protected MapTile currentTile;
    private ProgressBar hpBar;
    //protected int x;
    //protected int y;
    //protected int width;
    //protected int height;

    private Way way;// = Way.RIGHT;

    public Vector2 getCenter(){
        Vector2 v = new Vector2();
        v.x = getX() + getWidth()/2;
        v.y = getY() + getHeight()/2;
        return v;
    }

    public Mob() {

    }

    //public Drawable getTexture() {
    //    return texture;
    //}
    public void setTextureDrawable(Texture texture) {
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
        hpBar = new ProgressBar(0, getHealth(), 1, false, AssetLoader.getMobHpBarStyle());
    }
    public int getArmor() {
        return armor;
    }
    public void setArmor(int armor) {
        this.armor = armor;
    }
    public float getSpeed() {
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
    public void setDie(Tower source) {
        this.inGame = false;
        Wave.mobs.remove(this);//add "if contains this" if error
        if(source!= null){
            source.addKill(this);
        }
    }

    public void hit(int dmg, Tower source){//tower ==> spell&tower
        float resistDmg = dmg * getArmorReduction(getArmor());

        if(resistDmg < getHealth()){
            health -= resistDmg;
        }else if(isInGame()){
            health = 0;
            setDie(source);
        }
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
//    public int getWidth() {
//        return width;
//    }
//    public void setWidth(int width) {
//        this.width = width;
//    }
//    public int getHeight() {
//        return height;
//    }
//    public void setHeight(int height) {
//        this.height = height;
//    }


    public static Mob getMobById(int ID){
        switch (ID){
            case 0:
                return new Slime();
            case 1:
                return new Dog();
            case 2:
                return new Worm();
            case 3:
                return new JungleBat();
            case 4:
                return new Boar();

            case 5:

            default:
                return null;
        }
    }

    public static float getArmorReduction(int armor){
        return armor/10;
    }


//    public Mob() {

//    }




    public void move(float delta){
        for (int i = 0; i < speed; i++){
            step(delta);
        }
    }




    private void step(float delta){
        currentTile = Level.getMap().getTileContainMob(this);
        checkTurn(currentTile);
        checkCastle(currentTile);

        switch (way){
            case RIGHT:
                setX(getX() + delta);//bug when delta > time when check turn
                break;
            case LEFT:
                setX(getX() - delta);
                break;
            case UP:
                setY(getY() + delta);
                break;
            case DOWN:
                setY(getY() - delta);
                break;
        }
    }
    private void checkTurn(MapTile currentTile){
        if(currentTile != null){
            //System.out.println(currentTile.getLogic());

            Way w = Map.checkTurnWay(currentTile);
            if(w!= null && way!= w){
                way = w;
                //System.out.println(way);
                //System.out.println(currentTile.getLogic());
            }

        }
    }
    private void checkCastle(MapTile currentTile){
        if(currentTile != null){
            if(currentTile.getLogic() == MapTile.TileLogic.castle){
                if(isInGame()) {
                    damage();
                }
            }
        }
    }
    private void damage(){
        //dmg = AbilityManager.procMobAttackAbility(this);
        //dmg = AbilityManager.procLevelDefenceAbility(this);

        LevelMap.getLevel().damage(dmg);
        //System.out.println("died");
        setDie(null);
    }


    public void spawn(MapTile spawner){
        way = Map.checkSpawnerWay(spawner);
        //System.out.println(way);

        setSize(45, 45);
        setPosition(spawner.getX(), spawner.getY());
        setRotation(0);
        //setDrawable(texture);
        setRegion(texture);

    }


    public void render(SpriteBatch batch){
        //Image i = new Image(texture);
        //i.setSize(width, height);
        //i.setPosition(x, y);

        if(isInGame()) {
            draw(batch);
            drawHpBar(batch);
        }
    }
    private void drawHpBar(SpriteBatch batch){
        hpBar.setBounds(getX(), getY() + getHeight() /* + 5 //too high */, getWidth(), getHeight()/5);
        hpBar.setValue(getHealth());
        hpBar.draw(batch, 1);
    }


}
