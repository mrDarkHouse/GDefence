package com.darkhouse.gdefence.Level.Tower;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.InventorySystem.inventory.Tooltip.SlotTooltip;
import com.darkhouse.gdefence.InventorySystem.inventory.Tooltip.TooltipListener;
import com.darkhouse.gdefence.InventorySystem.inventory.Tooltip.TowerTooltip;
import com.darkhouse.gdefence.Level.Ability.Tools.DamageType;
import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Ability.Tower.Ability;
import com.darkhouse.gdefence.Level.Level;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Path.MapTile;
import com.darkhouse.gdefence.Model.Effectable;
import com.darkhouse.gdefence.Model.Level.Map;
import com.darkhouse.gdefence.Objects.DamageSource;
import com.darkhouse.gdefence.Objects.TowerObject;
import com.darkhouse.gdefence.Screens.LevelMap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Tower extends Effectable implements DamageSource{

    public static float getAttackSpeedDelay(int asValue){
        return new BigDecimal(4/(1 + (float)asValue/10)).setScale(2, BigDecimal.ROUND_FLOOR).floatValue();
    }

    protected boolean canAttack = true;

    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }
    public boolean isCanAttack() {
        return canAttack;
    }

    protected TowerObject towerPrototype;
    public TowerObject getTowerPrototype() {
        return towerPrototype;
    }
    private float preShotTime;
    private Mob target;
    //private Texture texturePath;
    private Circle attackRange;
//    private Texture attackRangeTexture;

    private TowerTooltip tooltip;
    private Map map;

    private int dmg;
    private int bonusDmg;

    public int getDmg() {
        return dmg;
    }
    public int getBonusDmg() {
        return bonusDmg;
    }

    private int speed;
    private int bonusSpeed;

    public int getSpeed() {
        return speed;
    }
    public int getBonusSpeed() {
        return bonusSpeed;
    }
    //    private float speedDelay;

//    private Array<Ability> abilities;
    private HashMap<Class<? extends Ability.ITowerAbilityType> , Array<Ability>> abilities;

    private Array<Ability> getAbilities(){
        Array<Ability> a = new Array<Ability>();
        for (java.util.Map.Entry<Class<? extends Ability.ITowerAbilityType> , Array<Ability>> e:abilities.entrySet()){
            for (Ability ab:e.getValue()){
                a.add(ab);
            }
        }
        return a;
    }
//    private Array<Effect> effects;//

    public void addExp(float value){
        getTowerPrototype().addExp(value);
        tooltip.hasChanged();
    }

    public int changeAttackSpeed(int value){
        if(speed + bonusSpeed + value > 10) {
            bonusSpeed += value;
            tooltip.hasChanged();
            return value;
        } else {
            bonusSpeed = -speed + 10;
            tooltip.hasChanged();
            return -10;
        }
//        tooltip.hasChanged();

//        if(speed + value > 10) speed += value;
//        else speed = 10;
    }
    public void changeDmg(int value){
        if(dmg + bonusDmg + value > 0) bonusDmg += value;
        else bonusDmg = -dmg;
        tooltip.hasChanged();
//        if (dmg + value > 0) dmg += value;
//        else dmg = 0;
    }

    public Tower(TowerObject towerPrototype, float x, float y, float width, float height) {
        super();
//        super(towerPrototype.getPrototype().getTowerTexture());
        setBounds(x, y, width, height);
        initEffectable();
        this.towerPrototype = towerPrototype;
        initRange();

        Texture t = towerPrototype.getPrototype().getTowerTexture();
//        t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);//TODO
        setRegion(t);


        abilities = new HashMap<Class<? extends Ability.ITowerAbilityType>, Array<Ability>>();
//        abilities = new Array<Ability>();
        for (Ability.AbilityPrototype p:towerPrototype.getAbilities()){
            if(abilities.containsKey(p.getAbilityType())){
                abilities.get(p.getAbilityType()).add(p.getAbility());
            }else {
                Array<Ability> arr = new Array<Ability>(){};
                arr.add(p.getAbility());
                abilities.put(p.getAbilityType(), arr);
            }
//            abilities.put(p.getAbilityType(), p.getAbility());
//            abilities.add(p.getAbility());
        }

//        for (Ability a:towerPrototype.getAbilities()) {
//            a.setOwner(this);
//        }

        this.dmg = towerPrototype.getDmg();
        this.speed = towerPrototype.getSpeed();

        preShotTime = getAttackSpeedDelay(speed + bonusSpeed);//for momental shot
//        this.speedDelay = getAttackSpeedDelay(speed);
    }
    public void init(Map map){
        //
        this.map = map;
        initAbilities();
        tooltip = new TowerTooltip(this, GDefence.getInstance().assetLoader.getSkin());
        addListener(new TooltipListener(tooltip, true));
    }

    private void initAbilities(){
        for (Ability a:getAbilities()){
            a.setOwner(this, map);
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

//        attackRangeTexture = GDefence.getInstance().assetLoader.get("towerRangeTexture.png", Texture.class);
//        attackRangeTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    public void addKill(/*Class<? extends Mob>*/Mob killedMob){
        //add exp from killedMob
        procKillAbilities(killedMob);
    }





    public boolean isInRange(Vector2 p){
        //Circle attackRange = new Circle(getCenter(), towerPrototype.getRange());
        return attackRange.contains(p);
    }

    @Override
    public void act(float delta) {
        if (!LevelMap.levelMap.isPaused()) physic(delta);
    }

    public void physic(float delta){
        actEffects(delta);
//        if(canAttack){//if stunned
            attack(delta);


//        }
    }

    public void actEffects(float delta){
        Array<Effect> ar = getEffects();
        for (int i = 0; i < ar.size; i++){
            ar.get(i).act(delta);
        }
    }

    private void attack(float delta){
        if(getTowerPrototype().getPrototype().getAttackType() == AttackType.none) return;
//        target = Mob.getMobOnMap(AttackLogic.First, this);//if !shot one target

        if(target == null || !isInRange(target.getCenter()) || !target.isInGame()) {
            target = Mob.getMobOnMap(AttackLogic.First, this);
            preShotTime += delta*Level.getTimeMultiplayer();//fix when next attack after kill has delay
        }else {
//            preShotTime += delta;
            if(preShotTime >= getAttackSpeedDelay(speed + bonusSpeed)/*/Level.getTimeMultiplayer()*/){//
                if(procPreAttackAbilities(delta)) {
                    shotProjectile(target, true);
                    procPostAttackAbilities();
                }
            }else preShotTime += delta*Level.getTimeMultiplayer();
        }


    }


    public void procBuildAbilities(MapTile t){
        if(abilities.containsKey(Ability.IOnBuild.class)){
            for (Ability a : abilities.get(Ability.IOnBuild.class)) {
////            if(a instanceof Ability.IOnBuild){
                ((Ability.IOnBuild) a).builded(t);
////            }
            }
        }
    }
    public void procBuildOnMapAbilities(Tower other){
        if(abilities.containsKey(Ability.IBuildedOnMap.class)) {
            for (Ability a : abilities.get(Ability.IBuildedOnMap.class)) {
////            if(a instanceof Ability.IBuildedOnMap){
                ((Ability.IBuildedOnMap) a).buildedOnMap(other);
////            }
            }
        }
        if(effects.containsKey(Ability.IBuildedOnMap.class)) {
            for (Effect e : effects.get(Ability.IBuildedOnMap.class)) {
                ((Ability.IBuildedOnMap) e).buildedOnMap(other);
            }
        }
//        for (Effect e:effects){
//            if(e instanceof Ability.IBuildedOnMap){
//                ((Ability.IBuildedOnMap) e).buildedOnMap(other);
//            }
//        }
    }
    private void procKillAbilities(Mob killedMob){
        if(effects.containsKey(Ability.IOnKilled.class)) {
            for (Effect e : effects.get(Ability.IOnKilled.class)) {
                ((Ability.IOnKilled) e).killed(killedMob);
            }
        }
//        CopyOnWriteArrayList<Effect> tmp = new CopyOnWriteArrayList<Effect>(effects);
//        for (Effect e:tmp){
//            if(e instanceof Ability.IOnKilled){
//                ((Ability.IOnKilled) e).killed(killedMob);
//            }
//        }
    }
    private boolean usePreshotAbilities(){
        boolean shotMainProjectile = true;
        if(abilities.containsKey(Ability.IPreShot.class)){
            for (Ability a : abilities.get(Ability.IPreShot.class)) {
//            if (a instanceof Ability.IPreShot) {
                if (!((Ability.IPreShot) a).use(target)) shotMainProjectile = false;
//            }
            }
        }
        if(effects.containsKey(Ability.IPreShot.class)) {
            for (Effect e : effects.get(Ability.IPreShot.class)) {
                if(!((Ability.IPreShot) e).use(target)) shotMainProjectile = false;
            }
        }
//        for (Effect e : effects) {
//            if (e instanceof Ability.IPreShot) {
//                if (!((Ability.IPreShot) e).use(target)) shotMainProjectile = false;
//            }
//        }
        return shotMainProjectile;
    }
    private boolean procPreAttackAbilities(float delta){
        boolean canShotNow = true;
        if(abilities.containsKey(Ability.IPreAttack.class)){
            for (Ability a : abilities.get(Ability.IPreAttack.class)) {
//            if (a instanceof Ability.IPreAttack) {
                if (!((Ability.IPreAttack) a).use(delta)) canShotNow = false;
//            }
            }
        }
        if(effects.containsKey(Ability.IPreAttack.class)) {
            for (Effect e : effects.get(Ability.IPreAttack.class)) {
                if(!((Ability.IPreAttack) e).use(delta)) canShotNow = false;
            }
        }
//        for (Effect e : effects) {
//            if (e instanceof Ability.IPreAttack) {
//                if(!((Ability.IPreAttack) e).use(delta)) canShotNow = false;
//            }
//        }
        return canShotNow;
    }
    private void procPostAttackAbilities(){
        if(abilities.containsKey(Ability.IPostAttack.class)){
            for (Ability a : abilities.get(Ability.IPostAttack.class)) {
//            if (a instanceof Ability.IPostAttack) {
                ((Ability.IPostAttack) a).use(target);
//            }
            }
        }
        if(effects.containsKey(Ability.IPostAttack.class)) {
            Array<Effect> arr = effects.get(Ability.IPostAttack.class);
//            CopyOnWriteArrayList<Effect> tmp = new CopyOnWriteArrayList<Effect>(arr);
            for (/*Effect e : arr*/int i = 0; i < arr.size; i++) {//TODO rework or do it on each effect parsing
                Effect e = arr.get(i);
                ((Ability.IPostAttack) e).use(target);
            }
        }
//        CopyOnWriteArrayList<Effect> tmp = new CopyOnWriteArrayList<Effect>(effects);
//        for (Effect e : tmp) {//concurrent
//            if (e instanceof Ability.IPostAttack) {
//                ((Ability.IPostAttack) e).use(target);
//            }
//        }
    }
    private int procOnHitAbilities(int dmg, Mob target, boolean isMain){
        if(abilities.containsKey(Ability.IOnHit.class)){
            for (Ability a : abilities.get(Ability.IOnHit.class)) {
//            if(a instanceof Ability.IOnHit && (isMain || a.isWorkOnAdditionalProjectiles())){
                dmg = ((Ability.IOnHit) a).getDmg(target, dmg);
//            }
            }
        }
        if(effects.containsKey(Ability.IOnHit.class)) {
            for (Effect e : effects.get(Ability.IOnHit.class)) {
                if(isMain || e.isWorkOnAdditionalProjectiles()){
                    dmg = ((Ability.IOnHit) e).getDmg(target, dmg);
                }
            }
        }
//        for (Effect e:effects){
//            if(e instanceof Ability.IOnHit && (isMain || e.isWorkOnAdditionalProjectiles())){
//                dmg = ((Ability.IOnHit) e).getDmg(target, dmg);
//            }
//        }
        return dmg;
    }
    private Projectile procAfteHitAbilities(Projectile p){
        if(abilities.containsKey(Ability.IAfterHit.class)){
            for (Ability a : abilities.get(Ability.IAfterHit.class)) {
//            if(a instanceof Ability.IAfterHit){
                p.addAbilities(((Ability.IAfterHit) a));
//            }
            }
        }
        return p;
    }
    private float procOnGetExp(float realDmg){
        float exp = getExpFromDmg(realDmg);
        if(effects.containsKey(Ability.IOnGetExp.class)){
            for (Effect e:effects.get(Ability.IOnGetExp.class)){
                exp = ((Ability.IOnGetExp) e).addExp(exp);
            }
        }
//        CopyOnWriteArrayList<Effect> tmp = new CopyOnWriteArrayList<Effect>(getEffects());
//        for (Effect e:tmp){
//            if(e instanceof Ability.IOnGetExp){
//                exp = ((Ability.IOnGetExp) e).addExp(exp);
//            }
//        }
        return exp;
    }

    public void shotProjectile(Mob target, boolean followMainShots){
//        boolean shotMainProjectile;
        if(canAttack) {
//            shotMainProjectile = usePreshotAbilities();
            if (usePreshotAbilities()) {
                shot(target);
            }
            if(followMainShots) preShotTime = 0;//if true, timer to next attack dont start before shot fired
        }

    }


    private void shot(Mob target){
        //target.hit(towerPrototype.getDmg());
        Projectile p = new Projectile(this, this.getCenter(), target, true);
        Map.projectiles.add(procAfteHitAbilities(p));
    }

//    public void hitTarget(Mob target, boolean isMain){
//        int dmg = this.dmg;
//        for (Ability a:abilities){
////            if(a.getUseType() == Ability.UseType.onHit){
////                a.use(target);//if main target//now work on mulitshot
////                dmg = a.getDmg(dmg);
////            }
//            if(a instanceof Ability.IOnHit && (isMain || a.isWorkOnAdditionalProjectiles())){
//                dmg = ((Ability.IOnHit) a).getDmg(target, dmg);
//            }
//        }
//        if(target != null) {//hotfix
//            target.hit(dmg, this);
//            getTowerPrototype().addExp(dmg/10);
//
//            procAfterHitAbilities(target, dmg);
//        }
//    }

    public int getDmg(Mob target, boolean isMain){
        int dmg = this.dmg + bonusDmg;
        return procOnHitAbilities(dmg, target, isMain);
    }
    public boolean hitTarget(Mob target, float dmg){
        if(target != null) {//hotfix
            float realDmg = target.hit(dmg, DamageType.Physic, this);
            addExp(procOnGetExp(realDmg));
            return realDmg != 0;
//            procAfterHitAbilities(target, dmg);
        }else return false;
    }

    public float getExpFromDmg(float dmg){
        if(dmg == 0) return 0;  //not earn exp while hit invulnerable units
        else return (float) (Math.sqrt(dmg + 5) / 4f);
    }

    @Override
    public void addEffect(Effect d) {
        super.addEffect(d);
        tooltip.hasChanged();
    }

    @Override
    public void deleteEffect(Class d) {
        super.deleteEffect(d);
        tooltip.hasChanged();
    }
    //    private void procAfterHitAbilities(Mob target, int dmg){
//        for (Ability a:abilities){
//            if(a instanceof Ability.IAfterHit){
//                ((Ability.IAfterHit) a).hit(target, dmg);//this.dmg if need not buff by abilities
//            }
//        }
//    }

//    public void draw(SpriteBatch batch, float delta){//draw from levelMap instead MapTile
//        super.draw(batch, delta);
//        Image f = new Image(getTexture());
//        f.setPosition(getX(), getY());
//        f.setSize(getWidth(), getHeight());
//        f.draw(batch, 1);
//        batch.draw(attackRangeTexture, attackRange.x, attackRange.y);
//    }


    public void draw(SpriteBatch batch, float delta){
        super.draw(batch, 1f);
        drawEffects(batch);
    }

    public void drawRange(/*SpriteBatch batch*//*, float delta*/ShapeRenderer shape){
        //Image im = new Image(attackRangeTexture);
        //im.setScaling(Scaling.none);
        //im.setDebug(true);
        //im.setAlign(0);
        //im.setPosition(attackRange.x - attackRange.radius, attackRange.y - attackRange.radius);
        //im.setSize(attackRange.radius*2, attackRange.radius*2);
        //im.draw(batch, 1);

        if(tooltip.isVisible()) {
//            batch.end();
//            shape.setAutoShapeType(true);
//            shape.begin(/*ShapeRenderer.ShapeType.Line*/);
            shape.setProjectionMatrix(getStage().getCamera().combined);
            shape.circle(attackRange.x, attackRange.y, attackRange.radius, 256);
//            shape.end();
//            batch.draw(attackRangeTexture, attackRange.x - attackRange.radius, attackRange.y - attackRange.radius,
//                    attackRange.radius * 2, attackRange.radius * 2);
//            batch.begin();
        }

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
