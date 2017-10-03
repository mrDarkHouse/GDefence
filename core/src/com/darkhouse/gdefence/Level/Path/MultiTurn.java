package com.darkhouse.gdefence.Level.Path;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Mob.Way;

public class MultiTurn extends WalkableMapTile{
    private Array<Turn> turns;

    public MultiTurn(Turn... turns) {
        this.turns = new Array<Turn>(turns);
    }

    @Override
    public TargetType getApplyMobs() {
        return null;
    }

    @Override
    public Way manipulatePath(Mob.MoveType enterMobType, Way currentWay) {
        Way w;
        for (Turn t:turns){
            w = t.manipulatePath(enterMobType, currentWay);
            if(w != null) return w;
        }
        return null;
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
        Texture texture = GDefence.getInstance().assetLoader.get("Path/Turn/turnLU.png", Texture.class);
        setRegion(texture);
    }
}
