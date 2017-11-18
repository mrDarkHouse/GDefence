package com.darkhouse.gdefence.Level.Path;


import com.badlogic.gdx.math.Vector2;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Mob.Way;

import java.awt.*;

public abstract class WalkableMapTile extends MapTile{



    public abstract TargetType getApplyMobs();
    public abstract Way manipulatePath(Mob.MoveType enterMobType, Way currentWay);
    public Point manipulateMob(){return null;}
    public abstract boolean isSwimmable();//for textures
}
