package com.darkhouse.gdefence.Level.Path;


import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Mob.Way;

public interface Walkable {
    TargetType getApplyMobs();
    Way manipulatePath(Mob.MoveType enterMobType, Way currentWay);
}
