package com.darkhouse.gdefence.Level.Tower;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;
import com.darkhouse.gdefence.Level.Mob.Mob;

public class Tower extends RectangleMapObject{

//    public static Tower createTower(ItemEnum.Tower tower){
//        switch (tower){
//            case Basic:
//                return new BasicTower();
//            case Rock:
//                return new RockTower();
//            case Arrow:
//                return new ArrowTower();
//            case Range:
//                return new RangeTower();
//            default:
//                return null;
//        }
//    }

    public Tower(ItemEnum.Tower towerPrototype, float x, float y, float width, float height) {
        super(x, y, width, height);
        this.towerPrototype = towerPrototype;
        this.texture = AssetLoader.getTowerTexture(towerPrototype);
    }

    protected boolean canAttack = true;
    protected ItemEnum.Tower towerPrototype;
    private float preShotTime;
    private Mob target;
    private Texture texture;

    /*
    //protected TowerType ID;
    protected String name;
    protected AttackType attackType;
    protected int dmg;
    //protected int speed;
    protected float speedDelay;
    protected int cost;
    protected ArrayList<Ability> abilities;

   // public TowerType getID() {
    //    return ID;
   // }
   // public void setID(TowerType ID) {
    //    this.ID = ID;
    //}
    public ArrayList<Ability> getAbilities() {
        return abilities;
    }
    public void setAbilities(ArrayList<Ability> abilities) {
        this.abilities = abilities;
    }
    public AttackType getAttackType() {
        return attackType;
    }
    public void setAttackType(AttackType attackType) {
        this.attackType = attackType;
    }
    public int getCost() {
        return cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }
    public int getDmg() {
        return dmg;
    }
    public void setDmg(int dmg) {
        this.dmg = dmg;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public float getSpeedDelay() {
        return speedDelay;
    }
    public void setSpeedDelay(float speedDelay) {
        this.speedDelay = speedDelay;
    }

    */

//    public static Tower getTowerByID(int ID){
//        switch (ID){
//            case 0:
//                return new BasicTower();
//            case 1:
//                return new RockTower();
//            case 2:
//                return new ArrowTower();
//            case 3:
//                return new RangeTower();
//            default:
//                return null;
//        }
//    }
    public boolean isInRange(Vector2 p){
        Circle attackRange = new Circle(getRectangle().getCenter(new Vector2()), towerPrototype.getRange());
        return attackRange.contains(p);
    }

    public void physic(float delta){
        if(canAttack){
            attack(delta);


        }
    }

    private void attack(float delta){
        preShotTime += delta;
        if(preShotTime >= towerPrototype.getSpeedDelay()){
            preShotTime = 0;
            if(target == null) {
                target = Mob.getMobOnMap(AttackLogic.Nearest, this);
            }//else {
            //    shot(target);
            //}
            if(target != null ) {
                shot(target);
            }
        }

    }
    private void shot(Mob target){
        target.hit(towerPrototype.getDmg());

        //Bullet b = new Bullet();//create bullet
    }

    public void draw(SpriteBatch batch, float delta){
        //TextureAtlas icons = new TextureAtlas(Gdx.files.internal("icons/icons.atlas"));
        //towerMask = icons.findRegion(tower.getTextureRegion());//
        Image f = new Image(texture);
        f.setPosition(getRectangle().getX(), getRectangle().getY());
        f.setSize(getRectangle().getWidth(), getRectangle().getHeight());
        f.draw(batch, 1);
    }













}
