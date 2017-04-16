package com.darkhouse.gdefence.Level.Tower;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Level.Ability.Mob.MobAbility;
import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Ability.Tower.Ability;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Path.MapTile;
import com.darkhouse.gdefence.Model.Effectable;
import com.darkhouse.gdefence.Model.Level.Map;
import com.darkhouse.gdefence.Objects.TowerObject;
import com.darkhouse.gdefence.Screens.LevelMap;

import java.math.BigDecimal;
import java.util.concurrent.CopyOnWriteArrayList;

public class Tower extends Effectable{

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

    public static float getAttackSpeedDelay(int asValue){
        return new BigDecimal(4/(1 + (float)asValue/10)).setScale(2, BigDecimal.ROUND_FLOOR).floatValue();
    }



    protected boolean canAttack = true;
    protected TowerObject towerPrototype;
    public TowerObject getTowerPrototype() {
        return towerPrototype;
    }
    private float preShotTime;
    private Mob target;
    //private Texture texturePath;
    private Circle attackRange;
    private Texture attackRangeTexture;

    private int dmg;
    private int speed;
//    private float speedDelay;

    private Array<Ability> abilities;
//    private Array<Effect> effects;//


    private ShapeRenderer shape;


    public void changeAttackSpeed(int value){
        if(speed + value > 10) speed += value;
        else speed = 10;
    }
    public void procBuildAbilities(MapTile t){
        for (Ability a:abilities){
            if(a instanceof Ability.IOnBuild){
                ((Ability.IOnBuild) a).builded(t);
            }
        }
    }



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

    public Tower(TowerObject towerPrototype, float x, float y, float width, float height) {
        super();
//        super(towerPrototype.getPrototype().getTowerTexture());
        setBounds(x, y, width, height);
        initEffectable();
        this.towerPrototype = towerPrototype;
        initRange();

        setRegion(towerPrototype.getPrototype().getTowerTexture());

        abilities = new Array<Ability>();
        for (Ability.AblityPrototype p:towerPrototype.getAbilities()){
            abilities.add(p.getAbility());
        }

//        for (Ability a:towerPrototype.getAbilities()) {
//            a.setOwner(this);
//        }

        this.dmg = towerPrototype.getDmg();
        this.speed = towerPrototype.getSpeed();
//        this.speedDelay = getAttackSpeedDelay(speed);
    }
    public void init(){
        //
        initAbilities();
    }

    private void initAbilities(){
        for (Ability a:abilities){
            a.setOwner(this);
        }
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

        attackRangeTexture = GDefence.getInstance().assetLoader.get("towerRangeTexture.png", Texture.class);
        attackRangeTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    public void addKill(/*Class<? extends Mob>*/Mob killedMob){
        //add exp from killedMob
        procKillAbilities(killedMob);

        LevelMap.getLevel().addEnergy(killedMob.getBounty());
        LevelMap.getLevel().getStatManager().MobsKilledAdd(1);
    }

    private void procKillAbilities(Mob killedMob){
        CopyOnWriteArrayList<Effect> tmp = new CopyOnWriteArrayList<Effect>(effects);
        for (Effect e:tmp){
            if(e instanceof Ability.IOnKilled){
                ((Ability.IOnKilled) e).killed(killedMob);
            }
        }
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
        target = Mob.getMobOnMap(AttackLogic.First, this);//if !shot one target
        if(target == null || !isInRange(target.getCenter()) || !target.isInGame()) {
            target = Mob.getMobOnMap(AttackLogic.First, this);
        }else {
            preShotTime += delta;
            if(preShotTime >= getAttackSpeedDelay(speed)){//
                preShotTime = 0;
                for (Ability a:abilities){
//                    if(a.getUseType() == Ability.UseType.preattack){
//                        a.use(target);
//                    }
                    if(a instanceof Ability.IPreAttack){
                        ((Ability.IPreAttack) a).use(target);
                    }
                }
                shot(target);
            }

        }


    }
//    private void procAbility(Ability.UseType type){
//        switch (type){
//            case preattack:
//
//                break;
//            case onHit:
//
//
//                break;
//
//        }
//    }
    private void shot(Mob target){
        //target.hit(towerPrototype.getDmg());
        Map.projectiles.add(new Projectile(this, target));


    }
    public void hitTarget(Mob target){
        int dmg = this.dmg;
        for (Ability a:abilities){
//            if(a.getUseType() == Ability.UseType.onHit){
//                a.use(target);//if main target//now work on mulitshot
//                dmg = a.getDmg(dmg);
//            }
            if(a instanceof Ability.IOnHit){
                dmg = ((Ability.IOnHit) a).getDmg(target, dmg);
            }
        }
        if(target != null) {//hotfix
            target.hit(dmg, this);
            getTowerPrototype().addExp(dmg/10);
        }
    }

//    public void draw(SpriteBatch batch, float delta){//draw from levelMap instead MapTile
//        super.draw(batch, delta);
//        Image f = new Image(getTexture());
//        f.setPosition(getX(), getY());
//        f.setSize(getWidth(), getHeight());
//        f.draw(batch, 1);
//        batch.draw(attackRangeTexture, attackRange.x, attackRange.y);
//    }
    public void draw(SpriteBatch batch, float delta){
        super.draw(batch);
        drawEffects(batch);
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



    private void drawEffects(SpriteBatch batch){
        effectBar.setPosition(getX(), getY() + getHeight());
        effectBar.draw(batch, 1);
    }


}
