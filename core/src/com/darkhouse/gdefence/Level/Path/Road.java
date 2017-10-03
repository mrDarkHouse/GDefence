package com.darkhouse.gdefence.Level.Path;


import com.badlogic.gdx.graphics.Texture;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Mob.Way;

public class Road extends WalkableMapTile{
    private Way startWay;
    private Way innerWay;
    private TargetType applyMobs;
//    public Way getStartWay() {
//        return startWay;
//    }
    public TargetType getApplyMobs() {
        return applyMobs;
    }

    public Road(TargetType applyMobs) {
        this.applyMobs = applyMobs;
    }

    public Road() {
    }
    //    public Road(TargetType applyMobs) {
//        this.applyMobs = applyMobs;
//    }

    //    public Road(Way startWay, TargetType applyMobs) {
//        this.startWay = startWay;
//        this.applyMobs = applyMobs;
////        initTexture();
//    }
//
//    public Road(Way startWay, Way innerWay, TargetType applyMobs) {
//        this.startWay = startWay;
//        this.innerWay = innerWay;
//        this.applyMobs = applyMobs;
//    }

    @Override
    public void initTexture() {
//        if (applyMobs.isConsist(Mob.MoveType.ground)) {
//            if (startWay == Way.LEFT || startWay == Way.RIGHT)
                setRegion(GDefence.getInstance().assetLoader.get("Path/roadHorizontal.png", Texture.class));
//            else setRegion(GDefence.getInstance().assetLoader.get("Path/roadVertical.png", Texture.class));
//        }else if(applyMobs == TargetType.WATER_ONLY){
//            if (startWay == Way.LEFT || startWay == Way.RIGHT)
//                setRegion(GDefence.getInstance().assetLoader.get("Path/waterHorizontal.png", Texture.class));
//            else setRegion(GDefence.getInstance().assetLoader.get("Path/waterVertical.png", Texture.class));
//        }
    }

    @Override
    public boolean isBuildable() {
        return false;
    }

    @Override
    public boolean isSwimmable() {
        return applyMobs == TargetType.WATER_ONLY;
    }

    @Override
    public Way manipulatePath(Mob.MoveType enterMobType, Way currentWay) {
//        System.out.println(Way.getNearBlockWay(this, prevTile));
        return null;
//        if(applyMobs.isConsist(enterMobType) && currentWay == innerWay) return startWay;
//        else return null;//dont manipulate
//        if(applyMobs.isConsist(enterMobType) && Way.getNearBlockWay(this, prevTile) == innerWay)return startWay;
//
    }
}
