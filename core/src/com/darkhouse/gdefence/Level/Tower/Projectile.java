package com.darkhouse.gdefence.Level.Tower;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Model.GameActor;
import com.darkhouse.gdefence.Model.Level.Map;

public class Projectile extends GameActor{

//    private Texture texture;
    private Tower tower;
    private Mob target;
    private float speed;

    private Vector2 position = new Vector2();
    private Vector2 velocity = new Vector2();
    private Vector2 movement = new Vector2();
    private Vector2 targetV = new Vector2();
    private Vector2 dir = new Vector2();

    public Projectile(Tower tower, Mob target) {
        super(tower.getTowerPrototype().getPrototype().getProjectileTexture());
        this.tower = tower;
        this.target = target;
        this.speed = 250;//custom
//        texture = tower.getTowerPrototype().getPrototype().getProjectileTexture();//WHAT THE FUCK IT??!!
        // (prototype.getPrototype().getPrototype().getAnotherPrototype.getTowerPrototype.getAnotherFuckingPrototype)

        //position.set(tower.getX(), tower.getY());
        setX(tower.getCenter().x);
        setY(tower.getCenter().y);

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        move(delta);
    }

    private void move(float delta){
        position.set(getX(), getY());
        targetV.set(target.getCenter().x, target.getCenter().y);
        dir.set(targetV).sub(position).nor();
        velocity.set(dir).scl(speed);
        movement.set(velocity).scl(delta);
        if(position.dst2(targetV) > movement.len2()){
            position.add(movement);
        }else {
            position.set(targetV);
//            hitTarget();
            tower.hitTarget(target);
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


    @Override
    public void draw(Batch batch, float parentAlpha) {
        f.setPosition(getX(), getY());
//        f.setSize(12, 8);//default texture size sets without it
        f.setRotation(getRotation());
        f.draw(batch, 1);
    }
}
