package com.darkhouse.gdefence.Level.Mob;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Level.Ability.Mob.*;
import com.darkhouse.gdefence.Level.Ability.Mob.Tools.EffectIcon;
import com.darkhouse.gdefence.Level.Ability.Mob.Effects.Effect;
import com.darkhouse.gdefence.Level.Level;
import com.darkhouse.gdefence.Level.Path.*;
import com.darkhouse.gdefence.Level.Tower.AttackLogic;
import com.darkhouse.gdefence.Level.Tower.Tower;
import com.darkhouse.gdefence.Level.Wave;
import com.darkhouse.gdefence.Model.GDSprite;
import com.darkhouse.gdefence.Model.Level.Map;
import com.darkhouse.gdefence.Screens.LevelMap;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Mob extends GDSprite{

    public enum Prototype{
        //           name          texture     moveType       hp  arm spd dmg bounty       //i think it better than Builder
        Slime      ("Slime",      "mob",     MoveType.ground, 80,  0, 50,  1, 3),
        Dog        ("Dog",        "mob2",    MoveType.ground, 50,  1, 100, 2, 4, new GreatEvasion.P(3)),
        Worm       ("Worm",       "mob3",    MoveType.ground, 100, 2, 80,  2, 6),
        JungleBat  ("Jungle Bat", "mob4",    MoveType.ground, 85,  2, 110, 3, 3),
        Boar       ("Boar",       "mob5",    MoveType.ground, 250, 4, 60,  3, 7, new LayerArmor.P(10, 2), new StrongSkin.P(15, 2)),
        Amphibia   ("Amphibia",   "mob6walk",MoveType.water,  150, 2, 50,  2, 5, new Swimmable.P("Mobs/mob6swim.png"), new WaterFeel.P(0.2f), new WaterDefend.P(4));// {
//            @Override
//            public void setAbilities() {
////                abilities.add(new Swimmable(GDefence.getInstance().assetLoader.get(, Texture.class)));
//            }
//        };

        Prototype(String name, String regionPath, MoveType moveType, int health, int armor,  float speed, int dmg, int bounty, MobAbility.AblityPrototype... abilities) {
            this.texture = GDefence.getInstance().assetLoader.get("Mobs/" + regionPath + ".png", Texture.class);
            this.name = name;
            this.health = health;
            this.armor = armor;
            this.moveType = moveType;
            this.speed = speed;
            this.dmg = dmg;
            this.bounty = bounty;
            this.abilities = new Array<MobAbility.AblityPrototype>(abilities);
        }

        protected Texture texture;
        protected String name;
        protected int health;
        protected int armor;
        protected MoveType moveType;
        protected float speed;
        protected int dmg;
        protected int bounty;
        protected Array<MobAbility.AblityPrototype> abilities;

        public String getName() {
            return name;
        }
        public int getHealth() {
            return health;
        }
        public int getArmor() {
            return armor;
        }
        public MoveType getMoveType() {
            return moveType;
        }
        public float getSpeed() {
            return speed;
        }
        public int getDmg() {
            return dmg;
        }
        public int getBounty() {
            return bounty;
        }
        public Array<MobAbility.AblityPrototype> getAbilities() {
            return abilities;
        }
        //        protected Prototype setName(String name) {
//            this.name = name;
//            return this;
//        }
//        protected Prototype setHealth(int health) {
//            this.health = health;
//            return this;
//        }
//        protected Prototype setArmor(int armor) {
//            this.armor = armor;
//            return this;
//        }
//        protected Prototype setMoveType(MoveType moveType) {
//            this.moveType = moveType;
//            return this;
//        }
//        protected Prototype setSpeed(float speed) {
//            this.speed = speed;
//            return this;
//        }
//        protected Prototype setDmg(int dmg) {
//            this.dmg = dmg;
//            return this;
//        }
//        protected Prototype setBounty(int bounty) {
//            this.bounty = bounty;
//            return this;
//        }

//        protected  /*abstract*/ void setAbilities(){};
    }
    public static float getArmorReduction(int armor){
        return (float) (0.1 * armor / (1 + 0.1 * Math.abs(armor)));
    }




    public static Mob getMobOnMap(AttackLogic logic, Tower tower){
        Map map = Level.getMap();
        Mob currentMob;
        switch (logic){
            case First:
                currentMob = null;
                int firstIndex = 99;
                for (Mob m:Wave.mobs) {
                    if(tower.isInRange(m.getCenter())){
                        if(Wave.mobs.indexOf(m, true) < firstIndex){
                            firstIndex = Wave.mobs.indexOf(m, true);
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
    public static Mob.Prototype getMobById(int ID){
        return Prototype.values()[ID];
//        switch (ID){
//            case 0:
//                return Prototype.Slime;
//            case 1:
//                return Prototype.Dog;
//            case 2:
//                return Prototype.Worm;
//            case 3:
//                return Prototype.JungleBat;
//            case 4:
//                return Prototype.Boar;
//            case 5:
//                return Prototype.Amphibia;
//            default:
//                throw new IllegalArgumentException("Mob with id " + ID + " not found");
//        }
    }


    public enum MoveType{
        ground, water, flying
    }

    //public int healthSpace = 3, healthHeight = 5;
    protected String name;
    protected int health;
    protected int armor;
    protected MoveType moveType;
    protected float speed;
    protected int dmg;
    protected int bounty;
    //private double pxlPerHealth;
    protected boolean inGame = true;
    //protected int xC, yC;
    protected MapTile currentTile;
    private ProgressBar hpBar;
    private EffectBar effectBar;
    private ArrayList <Effect> effects;//Effect[]
    private Array<MobAbility> abilities;

    private Way way;

    private class EffectBar extends Table{

//        public void addActor(EffectIcon actor) {
//            super.addActor(actor);
//        }

//        @Override
//        public void addActor(Actor actor) {
//            //delete
//        }

//        @Override
//        public SnapshotArray<Actor> getChildren() {
//            //delete
//            return null;
//        }

        public Array<EffectIcon> getChildrenArray() {
//            return super.getChildren();
            Array<EffectIcon> icons = new Array<EffectIcon>();
            for (Actor a:super.getChildren()){
                icons.add(((EffectIcon) a));
            }
            return icons;
        }

        public void removeIcon(Texture icon){
            for (EffectIcon e:getChildrenArray()){
                if(e.getIcon() == icon)removeActor(e);
            }
        }
    }


    public static Mob createMob(Prototype prototype){
        Mob m = new Mob(prototype);
        m.initAbilities();
        return m;
    }

    public Mob(Prototype prototype) {
        setName(prototype.name);
        setRegion(prototype.texture);
        setMoveType(prototype.moveType);
        setHealth(prototype.health);
        setArmor(prototype.armor);
        setSpeed(prototype.speed);
        setDmg(prototype.dmg);
        setBounty(prototype.bounty);
        setAbilities(prototype.abilities);

        setSize(45, 45);

        effects = new ArrayList<Effect>();
        effectBar = new EffectBar();
        effectBar.setSize(getWidth(), getHeight()/2);
        effectBar.defaults().space(5);
        effectBar.align(Align.center);

//        System.out.println(abilities);

//        setRegion(texture);
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
    public void changeArmor(int value){
        armor += value;
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
    public void setAbilities(Array<MobAbility.AblityPrototype> abilities) {
        this.abilities = new Array<MobAbility>();
        for (MobAbility.AblityPrototype a:abilities){
            this.abilities.add(a.getAbility());
        }
//        this.abilities = new Array<MobAbility>(abilities);
    }
    public Array<MobAbility> getAbilities() {
        return abilities;
    }

    public boolean isInGame() {
        return inGame;
    }

    public void addEffect(Effect d){
        if(!haveEffect(d.getClass())) {
            effects.add(d);
            d.apply();//start debuff
            EffectIcon ei = new EffectIcon(d);
            ei.setSize(effectBar.getHeight(), effectBar.getHeight());
            if(!d.isHidden()) {
                effectBar.add(ei);
            }
        }else {
            getEffect(d.getClass()).updateDuration();
            //effects.get(effects.indexOf(d)).updateDuration();
        }
    }
    public void deleteEffect(Class d){
        Effect searched = getEffect(d);
        if(searched != null) {
            effects.remove(searched);
            effectBar.removeIcon(searched.getIcon());
        }
    }

    public boolean haveEffect(Class d){
//        for (Debuff db: effects){
//            if(db.getClass() == d.getClass()){
//                return true;
//            }
//        }
//        return false;
        return (getEffect(d) != null);
    }
    private Effect getEffect(Class d){
        for (Effect db: effects){
            if(db.getClass() == d){
                return db;
            }
        }
        return null;
    }

    public void initAbilities(){
        for (MobAbility a:abilities){
            a.setOwner(this);
        }
    }
    private void useMoveAbilities(){
        for (MobAbility a:abilities){
            if(a instanceof MobAbility.IMove){
                ((MobAbility.IMove) a).move(currentTile);
            }
        }
    }
    private float useDefenceAbilities(Tower source, float dmg){
        float resistDmg = dmg;
        for (MobAbility a:abilities){
            if(a instanceof MobAbility.IGetDmg){
                System.out.println(a.getName());
                resistDmg = ((MobAbility.IGetDmg) a).getDmg(source, resistDmg);
            }
        }
        CopyOnWriteArrayList<Effect> tmp = new CopyOnWriteArrayList<Effect>(effects);
        for (Effect e:tmp){
            if(e instanceof MobAbility.IGetDmg){
                resistDmg = ((MobAbility.IGetDmg) e).getDmg(source, resistDmg);
            }
        }
        return resistDmg;
    }
    private void useSpawnAbilities(){
        for (MobAbility a:abilities){
            if(a instanceof MobAbility.ISpawn){
                ((MobAbility.ISpawn) a).spawned();
            }
        }
    }


//    private void useAbility(Class<? extends MobAbility.IType> abilityType){
//        for (MobAbility a:abilities){
//            if(a.getClass() == abilityType){
//                System.out.println("a");
//            }
//        }
//
//
//    }

    public Vector2 getCenter(){
        Vector2 v = new Vector2();
        v.x = getX() + getWidth()/2;
        v.y = getY() + getHeight()/2;
        return v;
    }

    public void setDie(Tower source) {
        this.inGame = false;
        Wave.mobs.removeValue(this, true);//add "if contains this" if error
        if(source!= null){
            source.addKill(this);
        }
    }

    public void hit(int dmg, Tower source){//tower ==> spell&tower
//        System.out.println(getArmor() + " " + getArmorReduction(getArmor()));
        float resistDmg = useDefenceAbilities(source, dmg * (1 - getArmorReduction(getArmor())));
        if(resistDmg < getHealth()){
            health -= resistDmg;
        }else if(isInGame()){
            health = 0;
            setDie(source);
        }
    }

    public void changeSpeed(float value){
        //speed = speed*percent;
        if(speed + value > 5) speed += value;//minimum speed const
        else speed = 5;
    }


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
            useMoveAbilities();
//            System.out.println(speed);
        }
//        update();//

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
//        System.out.println("spawned " + this.getName());
//        way = Map.checkSpawnerWay(spawner);
        currentTile = spawner;
        way = ((Spawn) spawner).manipulatePath(this.getMoveType());

//        update();//some mobs must change texture in spawn block
        setPosition(spawner.getX(), spawner.getY());
        setRotation(0);
        useSpawnAbilities();
        //setDrawable(texture);

    }
//    public abstract void update();

    public void render(SpriteBatch batch){
        //Image i = new Image(texture);
        //i.setSize(width, height);
        //i.setPosition(x, y);

        if(isInGame()) {
            draw(batch);
            drawHpBar(batch);
            drawEffects(batch);
        }
    }
    private void drawHpBar(SpriteBatch batch){
        hpBar.setBounds(getX(), getY() + getHeight() /* + 5 //too high */, getWidth(), getHeight()/5);
        hpBar.setValue(getHealth());
        hpBar.draw(batch, 1);
    }
    private void drawEffects(SpriteBatch batch){
        effectBar.setPosition(getX(), getY() + getHeight() + 10);
        effectBar.draw(batch, 1);
    }


}
