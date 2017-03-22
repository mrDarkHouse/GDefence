package com.darkhouse.gdefence.Level.Path;


import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.Level.Mob.Mob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum TargetType {
    GROUND_ONLY {
        @Override
        public Mob.MoveType[] getMoveTypes() {
            return new Mob.MoveType[]{Mob.MoveType.ground};
        }
    }, WATER_ONLY {
        @Override
        public Mob.MoveType[] getMoveTypes() {
            return new Mob.MoveType[]{Mob.MoveType.water};
        }
    }, GROUND_WATER {
        @Override
        public Mob.MoveType[] getMoveTypes() {
            return new Mob.MoveType[]{Mob.MoveType.ground, Mob.MoveType.water};
        }
    }, AIR_ONLY {
        @Override
        public Mob.MoveType[] getMoveTypes() {
            return new Mob.MoveType[]{Mob.MoveType.flying};
        }
    }, ALL {
        @Override
        public Mob.MoveType[] getMoveTypes() {
            return new Mob.MoveType[]{Mob.MoveType.ground, Mob.MoveType.water, Mob.MoveType.flying};
        }
    };

    public boolean isConsist(Mob.MoveType search){
        for (Mob.MoveType type:getMoveTypes()) if(type == search)return true;
        return false;
    }
    public boolean isConsist(TargetType searchType){
        List<Mob.MoveType> types = Arrays.asList(getMoveTypes());
        List<Mob.MoveType> searchTypes = Arrays.asList(searchType.getMoveTypes());
        return types.containsAll(searchTypes);
    }
    public boolean haveSame(TargetType searchType){
        for (Mob.MoveType type:getMoveTypes()){
            for (Mob.MoveType search:searchType.getMoveTypes())if(type == search) return true;
        }
        return false;
    }
    public Mob.MoveType[] getSame(TargetType searchType){
        ArrayList<Mob.MoveType> sameTypes = new ArrayList<Mob.MoveType>();
        for (Mob.MoveType type:getMoveTypes()){
            for (Mob.MoveType search:searchType.getMoveTypes())if(type == search) sameTypes.add(type);
        }
        return sameTypes.toArray(new Mob.MoveType[]{});//can i getDmg empty array?(in theory yes)
    }

    public abstract Mob.MoveType[] getMoveTypes();
}
