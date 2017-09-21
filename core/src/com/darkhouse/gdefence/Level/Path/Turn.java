package com.darkhouse.gdefence.Level.Path;


import com.badlogic.gdx.graphics.Texture;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Mob.Way;

import java.util.IllegalFormatException;

public class Turn extends MapTile implements Walkable{
    private Way startWay;
    private Way resultWay;
    public Way getStartWay() {
        return startWay;
    }
    public Way getResultWay() {
        return resultWay;
    }

    private TargetType applyMobs;
    public TargetType getApplyMobs() {
        return applyMobs;
    }

    public Turn(Way startWay, Way resultWay, TargetType applyMobs) {
//        super();
        this.startWay = startWay;
        this.resultWay = resultWay;
        this.applyMobs = applyMobs;
//        initTexture();
    }
    @Override
    public void initTexture() {
        Texture texture;
//        if(startWay == Way.LEFT && resultWay == Way.UP || startWay == Way.DOWN && resultWay == Way.RIGHT)
//            texture = GDefence.getInstance().assetLoader.get("Path/Turn/turnLU.png", Texture.class);
//        else if(startWay == Way.LEFT && resultWay == Way.DOWN || startWay == Way.UP && resultWay == Way.RIGHT)
//            texture = GDefence.getInstance().assetLoader.get("Path/Turn/turnLD.png", Texture.class);
//        else if(startWay == Way.RIGHT && resultWay == Way.UP || startWay == Way.DOWN && resultWay == Way.LEFT)
//            texture = GDefence.getInstance().assetLoader.get("Path/Turn/turnRU.png", Texture.class);
//        else if(startWay == Way.RIGHT && resultWay == Way.DOWN || startWay == Way.UP && resultWay == Way.LEFT)
//            texture = GDefence.getInstance().assetLoader.get("Path/Turn/turnRD.png", Texture.class);
//        else throw new IllegalArgumentException("startWay and resultWay cant be " + startWay + " and " + resultWay);
        if(applyMobs.isConsist(Mob.MoveType.ground))texture = GDefence.getInstance().assetLoader.get("Path/Turn/turn" +
                getTurnCode(startWay, resultWay) + ".png", Texture.class);
        else if(applyMobs == TargetType.WATER_ONLY)texture = GDefence.getInstance().assetLoader.get("Path/Turn/turnWater" +
                getTurnCode(startWay, resultWay) + ".png", Texture.class);
        else throw new IllegalArgumentException(applyMobs + "targets not support");

        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        setRegion(texture);
    }

    public static String getTurnCode(Way startWay, Way resultWay){
        if(startWay == Way.LEFT && resultWay == Way.UP || startWay == Way.DOWN && resultWay == Way.RIGHT)
            return "LU";
        else if(startWay == Way.LEFT && resultWay == Way.DOWN || startWay == Way.UP && resultWay == Way.RIGHT)
            return "LD";
        else if(startWay == Way.RIGHT && resultWay == Way.UP || startWay == Way.DOWN && resultWay == Way.LEFT)
            return "RU";
        else if(startWay == Way.RIGHT && resultWay == Way.DOWN || startWay == Way.UP && resultWay == Way.LEFT)
            return "RD";
        else throw new IllegalArgumentException("startWay and resultWay cant be " + startWay + " and " + resultWay);
    }
    public static String getCenterTurnCode(Way startWay, Way resultWay){
        if(startWay == Way.LEFT && resultWay == Way.UP || startWay == Way.UP && resultWay == Way.LEFT)
            return "LU";
        else if(startWay == Way.LEFT && resultWay == Way.DOWN || startWay == Way.DOWN && resultWay == Way.LEFT)
            return "LD";
        else if(startWay == Way.RIGHT && resultWay == Way.UP || startWay == Way.UP && resultWay == Way.RIGHT)
            return "RU";
        else if(startWay == Way.RIGHT && resultWay == Way.DOWN || startWay == Way.DOWN && resultWay == Way.RIGHT)
            return "RD";
        else throw new IllegalArgumentException("startWay and resultWay cant be " + startWay + " and " + resultWay);
    }

    public static String getTripleTurnCode(Way startWay, Way resultWay1, Way resultWay2){
        if(startWay == Way.LEFT && resultWay1 == Way.DOWN && resultWay2 == Way.UP) return "L";
        if(startWay == Way.LEFT && resultWay1 == Way.UP && resultWay2 == Way.DOWN) return "L";//if forget resultWay following
        if(startWay == Way.UP && resultWay1 == Way.UP && resultWay2 == Way.RIGHT) return "L";
        if(startWay == Way.UP && resultWay1 == Way.RIGHT && resultWay2 == Way.UP) return "L";//if forget resultWay following
        if(startWay == Way.DOWN && resultWay1 == Way.DOWN && resultWay2 == Way.RIGHT) return "L";
        if(startWay == Way.DOWN && resultWay1 == Way.RIGHT && resultWay2 == Way.DOWN) return "L";//

        if(startWay == Way.RIGHT && resultWay1 == Way.DOWN && resultWay2 == Way.UP) return "R";
        if(startWay == Way.RIGHT && resultWay1 == Way.UP && resultWay2 == Way.DOWN) return "R";//if forget resultWay following
        if(startWay == Way.UP && resultWay1 == Way.UP && resultWay2 == Way.LEFT) return "R";
        if(startWay == Way.UP && resultWay1 == Way.LEFT && resultWay2 == Way.UP) return "R";//if forget resultWay following
        if(startWay == Way.DOWN && resultWay1 == Way.DOWN && resultWay2 == Way.LEFT) return "R";
        if(startWay == Way.DOWN && resultWay1 == Way.LEFT && resultWay2 == Way.DOWN) return "R";//

        if(startWay == Way.UP && resultWay1 == Way.LEFT && resultWay2 == Way.RIGHT) return "U";
        if(startWay == Way.UP && resultWay1 == Way.RIGHT && resultWay2 == Way.LEFT) return "U";//if forget resultWay following
        if(startWay == Way.RIGHT && resultWay1 == Way.RIGHT && resultWay2 == Way.DOWN) return "U";
        if(startWay == Way.RIGHT && resultWay1 == Way.DOWN && resultWay2 == Way.RIGHT) return "U";//if forget resultWay following
        if(startWay == Way.LEFT && resultWay1 == Way.LEFT && resultWay2 == Way.DOWN) return "U";
        if(startWay == Way.LEFT && resultWay1 == Way.DOWN && resultWay2 == Way.LEFT) return "U";//

        if(startWay == Way.DOWN && resultWay1 == Way.LEFT && resultWay2 == Way.RIGHT) return "D";
        if(startWay == Way.DOWN && resultWay1 == Way.RIGHT && resultWay2 == Way.LEFT) return "D";//if forget resultWay following
        if(startWay == Way.RIGHT && resultWay1 == Way.RIGHT && resultWay2 == Way.UP) return "D";
        if(startWay == Way.RIGHT && resultWay1 == Way.UP && resultWay2 == Way.RIGHT) return "D";//if forget resultWay following
        if(startWay == Way.LEFT && resultWay1 == Way.LEFT && resultWay2 == Way.UP) return "D";
        if(startWay == Way.LEFT && resultWay1 == Way.UP && resultWay2 == Way.LEFT) return "D";//

        return null;
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
    public Way manipulatePath(Mob.MoveType enterMobType, Way currentWay){
        if(applyMobs.isConsist(enterMobType)) return resultWay;
        else return null;//
    }


}
