package com.darkhouse.gdefence.Level.Mob;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Level.Ability.Mob.*;
import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Level;
import com.darkhouse.gdefence.Level.Path.*;
import com.darkhouse.gdefence.Level.Tower.AttackLogic;
import com.darkhouse.gdefence.Level.Tower.Tower;
import com.darkhouse.gdefence.Level.Wave;
import com.darkhouse.gdefence.Model.Effectable;
import com.darkhouse.gdefence.Model.Level.Map;
import com.darkhouse.gdefence.Objects.DamageSource;
import com.darkhouse.gdefence.Screens.LevelMap;

import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Mob extends Effectable{

    public enum Prototype{
        //           texturePath  texturePath  moveType       hp  arm spd dmg bounty       //i think it better than Builder
//        Slime      ("Slime",      "mob",     MoveType.ground, 50,  0, 50,  1, 2),
        Dog        ("wolf",       "wolf",     MoveType.ground, 30,   0, 60,  1, 1/*, new HealingAura.P(250, 1, 5)*/, new ScorpionVenom.P(10, 5)),
        Eagle      ("eagle",      "eagle",    MoveType.ground, 45,   1, 50,  1, 1),
        Hedgehog   ("hedgehog",   "hedgehog", MoveType.ground, 100,  2, 30,  2, 2, new Curdle.P(2, 0.5f, "Mobs/hedgehogBlock")),
        Lynx       ("lynx",       "lynx",     MoveType.ground, 80,   3, 40,  2, 2, new Sprint.P(4, 2, 50)),
        Boar       ("boar",       "boar",     MoveType.ground, 750,  5, 30,  100, 7, new LayerArmor.P(10, 2), new StrongSkin.P(5, 2)),

        Ant        ("ant",        "ant",      MoveType.ground, 50,   1, 70,  1,  1),
        Snake      ("snake",      "snake",    MoveType.ground, 75,   2, 80,  2,  2),
        Jerboa     ("jerboa",     "jerboa",   MoveType.ground, 90,   0, 60,  1,  1, new GreatEvasion.P(2f)),
        Scorpion   ("scorpion",   "scorpion", MoveType.ground, 150,  3, 50,  1,  3, new ScorpionVenom.P(10, 2)),

        Crab       ("crab",       "crab",     MoveType.water,  200,  4, 50,  3,  2, new Swimmable.P("Mobs/crab2.png")),
        Frog       ("frog",       "frog",     MoveType.water,  150,  3, 40,  2,  1, new Swimmable.P("Mobs/frog2.png"), new WaterShield.P(0.2f, 0.5f, 3)),
        Turtle     ("turtle",     "turtle",   MoveType.water,  350,  5, 20,  4,  4, new Swimmable.P("Mobs/turtle2.png"), new WaterDefend.P(10), new WaterFeel.P(40)),
        Axolotl    ("axolotl",    "axolotl",  MoveType.water,  750,  10,50,  100,0, new Swimmable.P("Mobs/axolotl2.png")),

        Pinguin    ("pinguin",    "pinguin",  MoveType.ground, 250,  2, 50,  1,  1),
        PolarBear  ("polarbear",  "polarbear",MoveType.ground, 500,  6, 50,  2,  2),
        Seal       ("seal",       "seal",     MoveType.ground, 300,  4, 60,  1,  1),
        PolarDeer  ("polardeer",  "polardeer",MoveType.ground, 400,  3, 80,  3,  3),

        Soldier    ("soldier",    "soldier",  MoveType.ground, 350,  2, 50,  1,  1),
        Crusader   ("crusader",   "crusader", MoveType.ground, 450,  5, 75,  2,  2),
        Medic      ("medic",      "medic",    MoveType.ground, 100,  2, 60,  2,  1, new HealingAura.P(200, 1, 5)),
        Tank       ("tank",       "tank",     MoveType.ground, 800,  8, 50,  4,  5),

        UFO        ("ufo",        "ufo",      MoveType.ground, 400,  2, 80,  2,  2),
        Rocket     ("ufo",        "ufo",      MoveType.ground, 400,  2, 80,  2,  2);

//        JungleBat  ("Jungle Bat", "mob4",    MoveType.ground, 85,   2, 110, 3, 4/*, new Sadist.P(3, 35)*/),
//
//        Slime      ("Slime",      "mob",     MoveType.ground, 50,   0, 50,  1, 2),
//        Amphibia   ("Amphibia",   "mob6walk",MoveType.water,  150,  2, 50,  2, 5, new Swimmable.P("Mobs/mob6swim.png"), new WaterFeel.P(20)/*, new WaterDefend.P(4)*/);
        // {
//            @Override
//            publ ic void setAbilities() {
////                abilities.add(new Swimmable(GDefence.getInstance().assetLoader.get(, Texture.class)));
//            }
//        };

        Prototype(String name, String regionPath, MoveType moveType, int health, int armor,  float speed, int dmg, int bounty, MobAbility.AbilityPrototype... abilities) {
            this.texturePath = regionPath;
            this.name = name;
            this.health = health;
            this.armor = armor;
            this.moveType = moveType;
            this.speed = speed;
            this.dmg = dmg;
            this.bounty = bounty;
            this.abilities = new Array<MobAbility.AbilityPrototype>(abilities);
        }

        protected String texturePath;
        protected String name;
        protected int health;
        protected int armor;
        protected MoveType moveType;
        protected float speed;
        protected int dmg;
        protected int bounty;
        protected Array<MobAbility.AbilityPrototype> abilities;

        public String getName() {
            return GDefence.getInstance().assetLoader.getWord(name);
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
        public Array<MobAbility.AbilityPrototype> getAbilities() {
            return abilities;
        }
        //        protected Prototype setName(String texturePath) {
//            this.texturePath = texturePath;
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

//        protected  /*abstreffe*/ void setAbilities(){};
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
    }


    public enum MoveType{
        ground, water, flying
    }

    //public int healthSpace = 3, healthHeight = 5;
    protected String name;
    protected int health;
    private int maxHealth;
    protected int armor;
    protected MoveType moveType;
    protected float speed;
    protected int dmg;
    protected int bounty;
    //private double pxlPerHealth;
    protected boolean inGame = true;
    //protected int xC, yC;
    protected WalkableMapTile currentTile;
    protected Texture[] textures;

    private String texturePath;

    public String getTexturePath() {
        return texturePath;
    }

    private ProgressBar hpBar;
//    private EffectBar effectBar;
//    private ArrayList <Effect> effects;//Effect[]
    private Array<MobAbility> abilities;

    private Way way;

    public Way getWay() {
        return way;
    }

    public void setWay(Way way) {
        this.way = way;
        setRegion(textures[way.ordinal()]);
    }

    public enum State{
        normal, stunned
    }

    private State state;
    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
    }

//    private class EffectBar extends Table{
//
////        public void addActor(EffectIcon actor) {
////            super.addActor(actor);
////        }
//
////        @Override
////        public void addActor(Actor actor) {
////            //delete
////        }
//
////        @Override
////        public SnapshotArray<Actor> getChildren() {
////            //delete
////            return null;
////        }
//
//        public Array<EffectIcon> getChildrenArray() {
////            return super.getChildren();
//            Array<EffectIcon> icons = new Array<EffectIcon>();
//            for (Actor a:super.getChildren()){
//                icons.add(((EffectIcon) a));
//            }
//            return icons;
//        }
//
//        public void removeIcon(Texture icon){
//            for (EffectIcon e:getChildrenArray()){
//                if(e.getIcon() == icon)removeActor(e);
//            }
//        }
//    }


    public static Mob createMob(Prototype prototype){
        Mob m = new Mob(prototype);
        m.initAbilities();
        return m;
    }

    public Mob(Prototype prototype) {
        super();
        setName(prototype.name);
//        setRegion(prototype.texturePath);
        texturePath = prototype.texturePath;
        setMoveType(prototype.moveType);
        setHealth(prototype.health);
        setArmor(prototype.armor);
        setSpeed(prototype.speed);
        setDmg(prototype.dmg);
        setBounty(prototype.bounty);
        setAbilities(prototype.abilities);
//        initAbilities();

        setSize(40, 40);
        initEffectable();

        setState(State.normal);

        textures = new Texture[4];
        for (int i = 0; i < 4; i++){
            textures[i] = GDefence.getInstance().assetLoader.getMobTexture(texturePath + i);
//            textures[i].setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
//            System.out.println(textures[i].getMinFilter());
        }

//        setRegion(textures[0]);
//        setRegion(GDefence.getInstance().assetLoader.getMobTexture(texturePath));//

//        effects = new ArrayList<Effect>();
//        effectBar = new EffectBar();
//        effectBar.setSize(getWidth(), getHeight()/2);
//        effectBar.defaults().spaceLeft(5).spaceRight(5);
//        effectBar.align(Align.center);

//        System.out.println(abilities);

//        setRegion(texturePath);
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

    public int getMaxHealth() {
        return maxHealth;
    }
    public float getHealthPercent(){
        return (float)health/maxHealth;
    }

    protected void setHealth(int health) {
        this.maxHealth = health;
        this.health = health;
        hpBar = new ProgressBar(0, getHealth(), 1, false, GDefence.getInstance().assetLoader.getMobHpBarStyle()/*GDefence.getInstance().assetLoader.getSkin(), "health-bar2"*/);
//        hpBar.getStyle().knob = hpBar.getStyle().knobBefore;

        hpBar.getStyle().background.setMinWidth(getWidth());
        hpBar.getStyle().knobBefore.setMinWidth(getWidth() - 2);
//        hpBar.getStyle().background.setMinHeight(getHeight()/5);//.setSize(getWidth(), getHeight()/5);
//        hpBar.getStyle().knobBefore.setMinHeight(getHeight()/5);

//        hpBar.getStyle().background.setMinHeight(10);
//        hpBar.getStyle().knobBefore.setMinHeight(10 - 2);
////        hpBar.setPosition(getX(), getY());
//        //setPosition(Gdx.graphics.getWidth() - expBarSize[0], userlevelButton.getY() - expBarSize[1] - 4);
////        hpBar.setSize(width, height);
//        hpBar.setValue(getHealth());
//        hpBar.setAnimateDuration(0.5f);
//        hpBar.pack();
//        hpBar.
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
    public void setAbilities(Array<MobAbility.AbilityPrototype> abilities) {
        this.abilities = new Array<MobAbility>();
        for (MobAbility.AbilityPrototype a:abilities){
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

//    public void addEffect(Effect d){
//        if(!haveEffect(d.getClass())) {
//            effects.add(d);
//            d.apply();//start effect
//            if(!d.isHidden()) {
//                EffectIcon ei = new EffectIcon(d);
//                ei.setSize(effectBar.getHeight(), effectBar.getHeight());
//                effectBar.add(ei);
//            }
//        }else {
//            getEffect(d.getClass()).updateDuration();
//            //effects.get(effects.indexOf(d)).updateDuration();
//        }
//    }
//    public void deleteEffect(Class d){
//        Effect searched = getEffect(d);
//        if(searched != null) {
//            effects.remove(searched);
//            effectBar.removeIcon(GDefence.getInstance().assetLoader.getEffectIcon(searched.getIconPath()));
//        }
//    }
//
//    public boolean haveEffect(Class d){
////        for (Debuff db: effects){
////            if(db.getClass() == d.getClass()){
////                return true;
////            }
////        }
////        return false;
//        return (getEffect(d) != null);
//    }
//    private Effect getEffect(Class d){
//        for (Effect db: effects){
//            if(db.getClass() == d){
//                return db;
//            }
//        }
//        return null;
//    }

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
    private float useDefenceAbilities(DamageSource source, float dmg){
        float resistDmg = dmg;
        for (MobAbility a:abilities){
            if(a instanceof MobAbility.IGetDmg){
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
    private boolean useDieAbilities(/*Tower source*/){
        boolean isAlive = false;
        for (MobAbility a:abilities){
            if(a instanceof MobAbility.IDie){
                if(((MobAbility.IDie) a).die(/*source*/)) isAlive = true;
            }
        }
        return isAlive;
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

    public void setDie(DamageSource source) {
        this.inGame = false;
        LevelMap.getLevel().mobDieEvent(source);
        Wave.mobs.removeValue(this, true);//add "if contains this" if error
        if(source!= null){
            LevelMap.getLevel().addEnergy(getBounty());
            LevelMap.getLevel().getStatManager().MobsKilledAdd(1);
            source.addKill(this);
        }
    }

    public float hit(float dmg, DamageSource source){//return really get damage
//        System.out.println(getArmor() + " " + getArmorReduction(getArmor()));
        float resistDmg = useDefenceAbilities(source, dmg * (1 - getArmorReduction(getArmor())));
        if(resistDmg < getHealth()){
            health -= resistDmg;
            return resistDmg;
        }else if(isInGame()){
            if(!useDieAbilities(/*source*/)) {
                float lesshp = health;
                health = 0;
                setDie(source);
                return lesshp;
            }else return 0;
        }else return 0;
    }

    public void changeSpeed(float value){
        //speed = speed*percent;
        if(speed + value > 5) speed += value;//minimum speed const
        else speed = 5;
    }
    public void changeArmor(int value){
        armor += value;
    }
    public void heal(int value){
        if(health + value < maxHealth) health+=value;
        else health = maxHealth;
    }
    public void dispellDebuffs(){
        CopyOnWriteArrayList<Effect> tmpEffects = new CopyOnWriteArrayList<Effect>(effects);
        for (Effect e:tmpEffects){
            if(!e.isBuff() && e.isDispellable()) e.dispell();
        }
    }
    public void dispellBuffs(){
        CopyOnWriteArrayList<Effect> tmpEffects = new CopyOnWriteArrayList<Effect>(effects);
        for (Effect e:tmpEffects){
            if(e.isBuff() && e.isDispellable()) e.dispell();
        }
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
        if(t!= null && t != currentTile && t instanceof WalkableMapTile) {
//            System.out.println(t);
            //MapTile nextTile = Level.getMap().getNextTile(currentTile, way);
            //if(nextTile!= null && nextTile.getType() != moveType){
            WalkableMapTile tt = ((WalkableMapTile) t);
            checkTurn(tt);
            //}
//            currentTile = tt;
            useMoveAbilities();
//            System.out.println(speed);
        }
//        update();//

        checkCastle(currentTile);//may be first

        if(getState() == State.normal) {

            switch (way) {
                case RIGHT:
                    setX(getX() + delta);//bug when delta > time when check turn//fixed maybe
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
    }
    private void checkTurn(WalkableMapTile t){
//        if(currentTile != null){
            //System.out.println(currentTile.getLogic());
//        WalkableMapTile t = (/*(WalkableMapTile)*/ currentTile);

//        System.out.println(t);
//            Way w = Map.checkTurnWay(currentTile);
        Point v = t.manipulateMob();
        if(v != null) {
            teleport(Level.getMap().getTiles()[v.y][v.x].getX(), Level.getMap().getTiles()[v.y][v.x].getY());
//            System.out.println(Level.getMap().getTileContainMob(this));
            currentTile = ((WalkableMapTile) Level.getMap().getTileContainMob(this));
        }else currentTile = t;//vrode tak norm no esli che to mozet v etom problema


        Way w = currentTile.manipulatePath(this.getMoveType(), way);


        if(w!= null && way!= w){
            setWay(w);
            //System.out.println(way);
            //System.out.println(currentTile.getLogic());
        }

//    }
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


    public void spawn(WalkableMapTile spawner){
//        System.out.println("spawned " + this.getName());
//        way = Map.checkSpawnerWay(spawner);
//        setRegion(GDefence.getInstance().assetLoader.getMobTexture(texturePath));//
//        setUserObject(GDefence.getInstance().assetLoader.getMobTexture(texturePath));


        currentTile = spawner;
        Spawn spawn = ((Spawn) spawner);
        setWay(spawn.manipulatePath(this.getMoveType(), null));

//        update();//some mobs must change texturePath in spawn block
        setPosition(spawner.getX(), spawner.getY());
        setRotation(0);
        useSpawnAbilities();
        //setDrawable(texturePath);

    }
    public void teleport(float x, float y){
        setX(x);//if in bounds
        setY(y);
    }
//    public abstract void update();

    public void render(SpriteBatch batch){
        //Image i = new Image(texturePath);
        //i.setSize(width, height);
        //i.setPosition(x, y);

        if(isInGame()) {
            draw(batch, 1f);
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
