package com.darkhouse.gdefence.Level.Path;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Mob.Way;

import java.awt.*;

public class Portal extends WalkableMapTile implements Comparable<Portal>{

    public int id;
    public Portal second;
    private Way startWay;
//    public boolean open;

    public Portal(Way startWay, int id) {
        this.startWay = startWay;
        this.id = id;
    }
    public void init(Portal second){
        this.second = second;
        second.second = this;
//        open = true;
//        second.open = false;
    }

    @Override
    public TargetType getApplyMobs() {
        return null;
    }

    @Override
    public Way manipulatePath(Mob.MoveType enterMobType, Way currentWay) {
        return /*second.*/startWay;
    }

    @Override
    public Point manipulateMob() {
        /*if (open) */return new Point(second.getIndexY(), second.getIndexX());
//        else return null;
//        mob.teleport(second.getX(), second.getY());
//        return true;
    }

    @Override
    public boolean isSwimmable() {
        return false;
    }

    @Override
    public boolean isBuildable() {
        return false;
    }

    @Override
    public void initTexture() {
//        Texture texture = GDefence.getInstance().assetLoader.get("Path/Turn/turnLU.png", Texture.class);
        String w;
        /*if(open) */w = startWay.getShortName();
//        else w = Way.invertWay(startWay).getShortName();
        Texture texture = GDefence.getInstance().assetLoader.get("Path/Portal/portal" + w + id + ".png", Texture.class);
        setRegion(texture);
    }

    @Override
    public String toString() {
        return super.toString() + " id:" + id;
    }

    @Override
    public int compareTo(Portal o) {
        return id - o.id;
    }
}
