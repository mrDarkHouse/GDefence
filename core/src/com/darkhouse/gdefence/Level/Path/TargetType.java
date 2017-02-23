package com.darkhouse.gdefence.Level.Path;


import com.darkhouse.gdefence.Level.Mob.Mob;

public enum TargetType {
    GROUND_ONLY {
        @Override
        public boolean isConsist(Mob.MoveType moveType) {
            return moveType == Mob.MoveType.ground;
        }
    }, WATER_ONLY {
        @Override
        public boolean isConsist(Mob.MoveType moveType) {
            return moveType == Mob.MoveType.water;
        }
    }, GROUND_WATER {
        @Override
        public boolean isConsist(Mob.MoveType moveType) {
            return moveType == Mob.MoveType.ground || moveType == Mob.MoveType.water;
        }
    }, AIR_ONLY {
        @Override
        public boolean isConsist(Mob.MoveType moveType) {
            return moveType == Mob.MoveType.flying;
        }
    }, ALL {
        @Override
        public boolean isConsist(Mob.MoveType moveType) {
            return moveType == Mob.MoveType.ground || moveType == Mob.MoveType.water || moveType == Mob.MoveType.flying;
        }
    };

    public abstract boolean isConsist(Mob.MoveType moveType);
}
