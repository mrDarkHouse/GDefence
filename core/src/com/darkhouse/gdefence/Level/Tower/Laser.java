package com.darkhouse.gdefence.Level.Tower;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Level.Ability.Mob.FrozenAura;
import com.darkhouse.gdefence.Level.Ability.Tools.DamageType;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Wave;
import com.darkhouse.gdefence.Model.GameActor;
import com.darkhouse.gdefence.Model.Level.Map;

import java.awt.geom.Line2D;
//import com.sun.javafx.geom.Line2D;


public class Laser extends GameActor{

    private Tower owner;
    private Mob target;
    private Line2D line;
    private Rectangle rect;
    private Vector2 v;
    private float attackSpeed;
    private float timer = 0f;
    private float x1, x2, y1, y2;

    float px = 0f;
    float py = 0f;

    public Laser(Tower tower/*, Mob target*/) {
        super(tower.getTowerPrototype().getPrototype().getProjectileTexture());
        attackSpeed = Tower.getAttackSpeedDelay(tower.getSpeed());
        this.owner = tower;
        x1 = tower.getCenter().x;
        y1 = tower.getCenter().y;

        v = new Vector2(x1, y1);
//        rect = new Rectangle(x1, y1, x2-x1, y2-y1)

        line = new Line2D.Float(x1, y1, x1 + px, y1 + py);
//        Vector2 v1 = new Vector2(x1, y1);
//        float angle1;

//        rect = new Rectangle();




//        (x1, y1, x1 + px, y1 + py);
//        line.prefWidth(20);


//        f.setBounds();
    }
    public void setTarget(Mob target){
        if(target!= this.target) {
            this.target = target;
            x2 = target.getCenter().x;
            y2 = target.getCenter().y;
//            setVisible(true);
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(target == null) return;
        if(!owner.isInRange(target.getCenter())){
//            setVisible(false);
//            Map.lasers.remove(this);
        }else {
            move(delta);
            timer += delta;
            if (timer >= attackSpeed) {
                attack();
                timer = timer % attackSpeed;
            }
        }
    }

    private void move(float delta){
        x2 = target.getCenter().x;
        y2 = target.getCenter().y;

        float xTop, yTop;
        if(x2 > x1) xTop = GDefence.WIDTH;
        else xTop = 0;
        if(y2 > y1) yTop = GDefence.HEIGHT;
        else yTop = 0;


        float angle1 = (float)Math.toDegrees(Math.atan2(Math.abs(yTop - y1), Math.abs(xTop - x1)));
//                v1.angle(new Vector2(xTop, yTop));
        float angle2 = (float)Math.toDegrees(Math.atan2(Math.abs(y2 - y1), Math.abs(x2 - x1)));
//                v1.angle(new Vector2(x2, y2));
        if (angle1 < angle2) {
            py = GDefence.HEIGHT - y1;
            px = py/ ((float) Math.tan(Math.toRadians(angle2)));
        } else {
            px = GDefence.WIDTH - x1;
            py = ((float) Math.tan(Math.toRadians(angle2)) * px);
        }


//        System.out.println("tan " + Math.tan(Math.toRadians(angle2)));
//        System.out.println("angle " + angle1 + " " + angle2);
//        System.out.println("pxpy " + px + " " + py);

//        v.add(px, py);
        System.out.println(v.angle(new Vector2(px, py)));

        line.setLine(x1, y1, x1 + px, y1 + py);
//        line.setEndY(y1 + py);
    }

    private void attack(){
        for (int i = 0; i < Wave.mobs.size; i++){
            Mob m = Wave.mobs.get(i);
            if(line.contains(m.getCenter().x, m.getCenter().y)){
                m.hit(owner.getDmg(m, true), DamageType.Physic, owner);
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
//        super.draw(batch, parentAlpha);
//        float dx = x2-x1;
//        float dy = y2-y1;
//        float dist = (float)Math.sqrt(dx*dx + dy*dy);
//        float rad = (float)Math.atan2(dy, dx);
        if(target != null && owner.isInRange(target.getCenter())) {
//            batch.draw(getTexture(), x1, y1, px, py, 0, 0, 10, 10, false, false);
            batch.draw(new TextureRegion(getTexture()), x1, y1, 100, 1, 0, 0, 0, 0, (float)Math.toRadians(45));
        }
    }
}
