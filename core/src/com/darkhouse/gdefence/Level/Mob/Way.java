package com.darkhouse.gdefence.Level.Mob;


import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.Level.Path.MapTile;
import com.darkhouse.gdefence.Level.Path.Portal;

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

    public static Way[] getLeastWays(Way[] ways){//
        Array allWays = new Array();//compilator said that it's bad
        allWays.addAll(Way.values());
        for (Way way : ways) {
            allWays.removeValue(way, true);
        }
        return ((Way[]) allWays.toArray(Way.class));
    }
    public static int[] getCoordOffset(Way way, int x, int y){
        switch (way){
            case LEFT: return new int[]{x - 1, y};
            case RIGHT: return new int[]{x + 1, y};
            case UP: return new int[]{x, y - 1};
            case DOWN: return new int[]{x, y + 1};
            default: throw new IllegalArgumentException("Way can be " + way);
        }
    }
    public static Way getNearBlockWay(MapTile tile1, MapTile tile2){//meaning that tiles init on map
        if(Math.abs(tile2.getIndexX() - tile1.getIndexX()) > 1 || Math.abs(tile2.getIndexY() - tile1.getIndexY()) > 1){
            return null;//blocks cant connect but can place near on one of axis (happen in portals)
        }
        switch (tile2.getIndexX() - tile1.getIndexX()){
            case 1:return RIGHT;
            case -1:return LEFT;
            case 0: switch (tile2.getIndexY() - tile1.getIndexY()){
                case 1:return DOWN;
                case -1:return UP;
                case 0:throw new IllegalArgumentException("block identy");
                    default:return null;
//                default:throw new IllegalArgumentException("blocks too far");
            }
            default:return null;//throw new IllegalArgumentException("blocks too far");
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
