package com.darkhouse.gdefence.Level.Mob;


public enum Way {
    LEFT, RIGHT, UP, DOWN;

    public static Way invertWay(Way startWay){
        switch (startWay){
            case LEFT: return RIGHT;
            case RIGHT: return LEFT;
            case UP: return DOWN;
            case DOWN: return UP;
            default: throw new IllegalArgumentException("Way can be " + startWay);
        }
    }

    public String getShortName(){
        return name().substring(0, 1);
    }
//    public static String getShortName(Way way){
//        switch (way){
//            case LEFT: return "L";
//            case RIGHT: return "R";
//            case UP: return "U";
//            case DOWN: return "D";
//            default: throw new IllegalArgumentException("Way can be " + way);
//        }
//    }
}
