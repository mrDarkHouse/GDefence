package com.darkhouse.gdefence.Level.Tower;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;
import com.darkhouse.gdefence.Level.Ability.Tower.Ability;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Model.GameActor;
import com.darkhouse.gdefence.Model.Level.Map;

public class Projectile extends GameActor{

//    private Texture texture;
    protected boolean isMainProjectile;
    protected Array<Ability.IAfterHit> afterHitAbilties;
    public void addAbilities(Ability.IAfterHit a){
        afterHitAbilties.add(a);
    }

    protected Tower tower;
    protected Mob target;
    protected float speed;

    protected float dmgMultiplayer = 1.0f;

    public void setDmgMultiplayer(float dmgMultiplayer) {
        this.dmgMultiplayer = dmgMultiplayer;
    }

    protected Vector2 position = new Vector2();
    protected Vector2 velocity = new Vector2();
    protected Vector2 movement = new Vector2();
    protected Vector2 targetV = new Vector2();
    protected Vector2 dir = new Vector2();

    public Projectile(Tower tower, Vector2 startLocation, Mob target, boolean isMain) {//additional are in MultiShot, Glaive

//
//        super(tower.getTowerPrototype().getPrototype().getProjectileTexture());
//        this.tower = tower;
//        this.target = target;
//        this.speed = tower.getTowerPrototype().getPrototype().getProjectileSpeed();//custom
//        this.isMainProjectile = isMain;
//
//        afterHitAbilties = new Array<Ability.IAfterHit>();
////        texture = tower.getTowerPrototype().getPrototype().getProjectileTexture();//WHAT THE FUCK IT??!!
//        // (prototype.getPrototype().getPrototype().getAnotherPrototype.getTowerPrototype.getAnotherFuckingPrototype)
//
//        //position.set(tower.getX(), tower.getY());
////        setX(tower.getCenter().x);
////        setY(tower.getCenter().y);
//        setX(startLocation.x);
//        setY(startLocation.y);


        this(tower, startLocation, isMain);
        this.target = target;
    }

    public Projectile(Tower tower, Vector2 startLocation, boolean isMain){//for non target projectiles
        super(tower.getTowerPrototype().getPrototype().getProjectileTexture());
        this.tower = tower;
        this.speed = tower.getTowerPrototype().getPrototype().getProjectileSpeed();//custom
        this.isMainProjectile = isMain;

        afterHitAbilties = new Array<Ability.IAfterHit>();

        setX(startLocation.x);
        setY(startLocation.y);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        move(delta);
    }

    protected void move(float delta){
        position.set(getX(), getY());
        targetV.set(target.getCenter().x, target.getCenter().y);
        dir.set(targetV).sub(position).nor();
        velocity.set(dir).scl(speed);
        movement.set(velocity).scl(delta);
        if(position.dst2(targetV) > movement.len2()){
            position.add(movement);
        }else {
            position.set(targetV);
//            tower.hitTarget(target, isMainProjectile);
            tower.hitTarget(target, ((int) (tower.getDmg(target, isMainProjectile) * dmgMultiplayer)));
            afterHit();
            Map.projectiles.remove(this);
        }
        setRotation(dir.angle());

        //rotateBy(degree);

        setX(position.x);
        setY(position.y);
    }
//    private void hitTarget(){
//        target.hit(tower.getTowerPrototype().getDmg(), tower);
//    }

    protected void afterHit(){
        for (Ability.IAfterHit a:afterHitAbilties){
            a.hit(target, ((int) (tower.getDmg(target, isMainProjectile) * dmgMultiplayer)), this);
        }
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        f.setPosition(getX(), getY());
//        f.setSize(12, 8);//default texture size sets without it
        f.setRotation(getRotation());
        f.draw(batch, 1);
    }
}
