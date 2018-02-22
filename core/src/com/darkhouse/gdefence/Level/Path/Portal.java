package com.darkhouse.gdefence.Level.Path;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Mob.Way;

import java.awt.*;
import java.util.ArrayList;

public class Portal extends WalkableMapTile implements Comparable<Portal>{

    public int id;
    public Array<Portal> seconds;
    private Way startWay;
//    private Portal ;
    private Vector2 exeption;
//    public boolean open;

    public Portal(Way startWay, int id) {
        this.startWay = startWay;
        this.id = id;
        seconds = new Array<Portal>();
    }
    public Portal(Way startWay, int id, int xEception, int yExeption){
        this(startWay, id);
        exeption = new Vector2(xEception, yExeption);
    }

    private boolean checkExeption(Portal p){
        if(exeption == null) return false;
        return p.getIndexX() == exeption.x && p.getIndexY() == exeption.y;
    }
    public void init(ArrayList<Portal> portals){
        for (Portal p:portals){
            if(p != this && !checkExeption(p)) seconds.add(p);
        }
//        System.out.println("P " + seconds);
    }

//    public void init(/*Array<Portal>*/Portal second){
////        this.second = second;
////        for (Portal p:second){
//
////        for (Portal p:second.seconds){
//            for (Portal a:seconds) {
//                a.seconds.add(second);
////                p.seconds.add(this);
////                p.seconds.add(a);
////                a.seconds.add(p);
//            }
////        }
//        seconds.add(second);
//            /*p.*/second.seconds.add(this);
//
////        }
////        second.second = this;
////        open = true;
////        second.open = false;
//    }

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
        int i = (int) (Math.random()*seconds.size);
//        System.out.println(seconds);
        /*if (open) */return new Point(seconds.get(i).getIndexY(), seconds.get(i).getIndexX());
//        else return null;
//        mob.teleport(second.getX(), second.getY());
//        return true;
    }

    @Override
    public boolean isSwimmable() {
        return false;
    }

    @Override
    public Logic getLogic() {
        return Logic.Portal;
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
