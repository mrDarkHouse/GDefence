package com.darkhouse.gdefence.Level.Tower;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Model.GameActor;
import com.darkhouse.gdefence.Model.Level.Map;
import com.darkhouse.gdefence.Screens.LevelMap;

public class Tower extends GameActor{

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



    protected boolean canAttack = true;
    protected ItemEnum.Tower towerPrototype;
    public ItemEnum.Tower getTowerPrototype() {
        return towerPrototype;
    }
    private float preShotTime;
    private Mob target;
    //private Texture texture;
    private Circle attackRange;
    private Texture attackRangeTexture;

    private ShapeRenderer shape;

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
    public Tower(ItemEnum.Tower towerPrototype, float x, float y, float width, float height) {
        setBounds(x, y, width, height);
        this.towerPrototype = towerPrototype;
        setTexture(AssetLoader.getTowerTexture(towerPrototype));
        initRange();
    }

    private void initRange(){
        attackRange = new Circle(getCenter(), towerPrototype.getRange());
        //shape = new ShapeRenderer();

        //Pixmap pixmap = new Pixmap((int)(attackRange.radius*2), (int)(attackRange.radius*2), Pixmap.Format.RGBA8888);
        //pixmap.setColor(Color.BLACK);
        //Pixmap.setBlending(Pixmap.Blending.None);
        //Pixmap.setFilter(Pixmap.Filter.BiLinear);
        //pixmap.drawCircle(attackRange.x, attackRange.y, attackRange.radius);
        //pixmap.drawCircle((int)attackRange.x, (int)attackRange.y, (int)attackRange.radius);
        //System.out.println((int)attackRange.x + " " + (int)attackRange.y);
        //attackRangeTexture = new Texture(pixmap);//not work
        //pixmap.dispose();

        attackRangeTexture = new Texture("towerRangeTexture.png");
    }

    public void addKill(/*Class<? extends Mob>*/Mob killedMob){
        //add exp from killedMob
        LevelMap.getLevel().addEnergy(killedMob.getBounty());
        LevelMap.getLevel().getStatManager().MobsKilledAdd(1);
    }


    public boolean isInRange(Vector2 p){
        //Circle attackRange = new Circle(getCenter(), towerPrototype.getRange());
        return attackRange.contains(p);
    }

    public void physic(float delta){
        if(canAttack){
            attack(delta);


        }
    }

    private void attack(float delta){
        if(target == null || !isInRange(target.getCenter()) || !target.isInGame()) {
            target = Mob.getMobOnMap(AttackLogic.Nearest, this);
        }else {
            preShotTime += delta;
            if(preShotTime >= towerPrototype.getSpeedDelay()){
                preShotTime = 0;
                shot(target);

            }

        }


    }
    private void shot(Mob target){
        //target.hit(towerPrototype.getDmg());
        Map.projectiles.add(new Projectile(this, target));


    }

    public void draw(SpriteBatch batch, float delta){//draw from levelMap instead MapTile
        super.draw(batch, delta);
//        Image f = new Image(getTexture());
//        f.setPosition(getX(), getY());
//        f.setSize(getWidth(), getHeight());
//        f.draw(batch, 1);
        //batch.draw(attackRangeTexture, attackRange.x, attackRange.y);




    }
    public void drawRange(SpriteBatch batch, float delta){
        //Image im = new Image(attackRangeTexture);
        //im.setScaling(Scaling.none);
        //im.setDebug(true);
        //im.setAlign(0);
        //im.setPosition(attackRange.x - attackRange.radius, attackRange.y - attackRange.radius);
        //im.setSize(attackRange.radius*2, attackRange.radius*2);
        //im.draw(batch, 1);

        batch.draw(attackRangeTexture, attackRange.x - attackRange.radius, attackRange.y - attackRange.radius,
                attackRange.radius*2, attackRange.radius*2);
        //batch.end();
        //shape.setAutoShapeType(true);
        //shape.setColor(Color.BLACK);
        //shape.begin();
        //shape.circle(attackRange.x, attackRange.y, attackRange.radius);//work
        //shape.end();
        //shape.setAutoShapeType(false);
        //batch.begin();

    }













}
