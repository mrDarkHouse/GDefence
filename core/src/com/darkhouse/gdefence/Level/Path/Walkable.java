package com.darkhouse.gdefence.Level.Path;


import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Mob.Way;

public interface Walkable {
    Way manipulatePath(Mob enterMob);
}
