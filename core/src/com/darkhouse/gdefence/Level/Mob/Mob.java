package com.darkhouse.gdefence.Level.Mob;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Level.Ability.Debuff;
import com.darkhouse.gdefence.Level.Level;
import com.darkhouse.gdefence.Level.Path.*;
import com.darkhouse.gdefence.Level.Tower.AttackLogic;
import com.darkhouse.gdefence.Level.Tower.Tower;
import com.darkhouse.gdefence.Level.Wave;
import com.darkhouse.gdefence.Model.GDSprite;
import com.darkhouse.gdefence.Model.Level.Map;
import com.darkhouse.gdefence.Screens.LevelMap;

import java.util.ArrayList;

public abstract class Mob extends GDSprite{

    public static Mob getMobOnMap(AttackLogic logic, Tower tower){
        Map map = Level.getMap();
        Mob currentMob;

        switch (logic){
            case First:
                currentMob = null;
                int firstIndex = 99;
                for (Mob m:Wave.mobs) {
                    if(tower.isInRange(m.getCenter())){
                        if(Wave.mobs.indexOf(m) < firstIndex){
                            firstIndex = Wave.mobs.indexOf(m);
                            currentMob = m;
                        }
                    }
                }
                return currentMob;
            case Nearest:
                currentMob = null;
                float currentF = 99999;//infinity
                for (Mob m:Wave.mobs) {
                    if(tower.isInRange(m.getCenter())){
                        //if(new Vector2(tower.getRectangle().getCenter(new Vector2())))
                        if(m.getCenter().dst(tower.getCenter()) < currentF){
                            currentF = m.getCenter().dst(tower.getCenter());
                            currentMob = m;
                        }
                    }
                }
                return currentMob;
            }
        return null;
    }

//    public enum MoveType{
//        ground, water
//
//
//    }
    public enum MoveType{
        ground, water, flying
    }

    //    private Texture texture;

    //public int healthSpace = 3, healthHeight = 5;
    protected String name;
    protected int health;
    protected int armor;
    protected MoveType moveType;
    protected float speed;
    protected int dmg;
    protected int ID;
    protected int bounty;
    //private double pxlPerHealth;
    protected boolean inGame = true;
    //protected int xC, yC;
    protected MapTile currentTile;
    private ProgressBar hpBar;
    private ArrayList <Debuff> effects;//Effect[]

    public void addDebuff(Debuff d){
        if(!haveDebuff(d)) {
            effects.add(d);
            d.apply();//start debuff
        }else {
            getEffect(d).updateDuration();
            //effects.get(effects.indexOf(d)).updateDuration();
        }
    }
    public void deleteDebuff(Debuff d){
        if(haveDebuff(d)) {
            effects.remove(d);
        }
    }
    public boolean haveDebuff(Debuff d){
//        for (Debuff db: effects){
//            if(db.getClass() == d.getClass()){
//                return true;
//            }
//        }
//        return false;
        return (getEffect(d) != null);
    }
    private Debuff getEffect(Debuff d){
        for (Debuff db: effects){
            if(db.getClass() == d.getClass()){
                return db;
            }
        }
        return null;
    }

    //protected int x;
    //protected int y;
    //protected int width;
    //protected int height;

    private Way way;

    public Vector2 getCenter(){
        Vector2 v = new Vector2();
        v.x = getX() + getWidth()/2;
        v.y = getY() + getHeight()/2;
        return v;
    }

    public Mob() {
        effects = new ArrayList<Debuff>();
        setSize(45, 45);
//        setRegion(texture);
    }

    //public Drawable getTexture() {
    //    return texture;
    //}
//    public void setTextureDrawable(Texture texture) {
//        this.texture = texture;
//    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getHealth() {
        return health;
    }
    protected void setHealth(int health) {
        this.health = health;
        hpBar = new ProgressBar(0, getHealth(), 1, false, GDefence.getInstance().assetLoader.getMobHpBarStyle());
    }
    public int getArmor() {
        return armor;
    }
    public void setArmor(int armor) {
        this.armor = armor;
    }
    public MoveType getMoveType() {
        return moveType;
    }
    public void setMoveType(MoveType moveType) {
        this.moveType = moveType;
    }
    public float getSpeed() {
        return speed;
    }
    public void setSpeed(float speed) {
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
        float resistDmg = dmg * (1 - getArmorReduction(getArmor()));

        if(resistDmg < getHealth()){
            health -= resistDmg;
        }else if(isInGame()){
            health = 0;
            setDie(source);
        }
    }

    public void changeSpeed(float value){
        //speed = speed*percent;
        if(speed + value > 5) {//minimum speed const
            speed += value;
        }else {
            speed = 5;
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
                return new Amphibia();
            default:
                return null;
        }
    }

    public static float getArmorReduction(int armor){
        return (float) armor/10;
    }


//    public Mob() {

//    }

    public void actEffects(float delta){
        for (int i = 0; i < effects.size(); i++){
            effects.get(i).act(delta);
        }
//        for (Debuff d:effects){//concurrent;
//            d.act(delta);
//        }
    }


    public void move(float delta){
        for (int i = 0; i < speed; i++){
            step(delta);
        }
    }




    private void step(float delta){
//        currentTile = Level.getMap().getTileContainMob(this);

        MapTile t = Level.getMap().getTileContainMob(this);
        if(t!= null && t != currentTile) {
            //MapTile nextTile = Level.getMap().getNextTile(currentTile, way);
            //if(nextTile!= null && nextTile.getType() != moveType){
            checkTurn(t);
            //}
            currentTile = t;
        }
        update();//

        checkCastle(currentTile);//may be first

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
//        if(currentTile != null){
            //System.out.println(currentTile.getLogic());
            Walkable t = ((Walkable) currentTile);

//            Way w = Map.checkTurnWay(currentTile);
            Way w = t.manipulatePath(this.getMoveType());//must call 1 time per block


            if(w!= null && way!= w){
                way = w;
                //System.out.println(way);
                //System.out.println(currentTile.getLogic());
            }

//        }
    }
    private void checkCastle(MapTile currentTile){
        if(currentTile != null){
            if(currentTile instanceof Castle/* == MapTile.TileLogic.castle*/){
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
//        way = Map.checkSpawnerWay(spawner);
        currentTile = spawner;
        way = ((Spawn) spawner).manipulatePath(this.getMoveType());
        //System.out.println(way);
        update();//some mobs must change texture in spawn block


        setPosition(spawner.getX(), spawner.getY());
        setRotation(0);
        //setDrawable(texture);


    }
    public abstract void update();

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
