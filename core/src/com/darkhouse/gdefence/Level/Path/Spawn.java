package com.darkhouse.gdefence.Level.Path;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Mob.Way;

import java.awt.*;

public class Spawn extends WalkableMapTile /*implements Walkable*/{
    private Way startWay;
    private TargetType applyMobs;
    private Array<WalkableMapTile> logicTiles;

    public Way getStartWay() {
        return startWay;
    }

    @Override
    public TargetType getApplyMobs() {
        return applyMobs;
    }

    public Spawn(Way startWay, TargetType applyMobs) {
        this.startWay = startWay;
        this.applyMobs = applyMobs;
//        initTexture();
    }
    public Spawn(TargetType applyMobs, Array<WalkableMapTile> logicTiles){//empty spawn, move logic added from combining with other blocks
        this.applyMobs = applyMobs;
        this.logicTiles = /*new Array<WalkableMapTile>(logicTiles)*/logicTiles;
    }

    @Override
    public void initTexture() {
        if (logicTiles != null) {
            logicTiles.get(0).initTexture();//0 is main
//            System.out.println();
            return;
        }

        if(applyMobs.isConsist(Mob.MoveType.ground)) {
            if (startWay == Way.LEFT || startWay == Way.RIGHT)
                setRegion(GDefence.getInstance().assetLoader.get("Path/roadHorizontal.png", Texture.class));
            else setRegion(GDefence.getInstance().assetLoader.get("Path/roadVertical.png", Texture.class));
        }else if(applyMobs == TargetType.WATER_ONLY) {
            if (startWay == Way.LEFT || startWay == Way.RIGHT)
                setRegion(GDefence.getInstance().assetLoader.get("Path/waterHorizontal.png", Texture.class));
            else setRegion(GDefence.getInstance().assetLoader.get("Path/waterVertical.png", Texture.class));
        }
    }

    @Override
    public Logic getLogic() {
        if(logicTiles != null) return logicTiles.get(0).getLogic();
        else return Logic.Spawner;
    }

    @Override
    public MapTile getInstance() {
        if(logicTiles != null) return logicTiles.get(0).getInstance();
        return super.getInstance();
    }

    @Override
    public boolean isBuildable() {
        return false;
    }

    @Override
    public boolean isSwimmable() {
        return false;
    }

    @Override
    public Way manipulatePath(Mob.MoveType enterMobType, Way currentWay) {
        if(logicTiles == null) return startWay;
        else {
            Way w;
            for (WalkableMapTile m:logicTiles){
                w = m.manipulatePath(enterMobType, currentWay);
                if(w != null) return w;
            }
            return null;
        }
    }

    @Override
    public Point manipulateMob() {
        return logicTiles.get(0).manipulateMob();
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        if(logicTiles != null) logicTiles.get(0).setBounds(x, y, width, height);
        super.setBounds(x, y, width, height);
    }
    @Override
    public void setIndex(int indexX, int indexY) {
        if(logicTiles != null) logicTiles.get(0).setIndex(indexX, indexY);
        /*else */super.setIndex(indexX, indexY);//

    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(logicTiles != null) logicTiles.get(0).draw(batch, parentAlpha);
        else super.draw(batch, parentAlpha);
    }
}
