package com.darkhouse.gdefence.Level;


public abstract class Entity {

    private int x;
    private int y;
    private int xCell;
    private int yCell;

    public Entity(int xCell, int yCell) {
        this.xCell = xCell;
        this.yCell = yCell;
    }
}
