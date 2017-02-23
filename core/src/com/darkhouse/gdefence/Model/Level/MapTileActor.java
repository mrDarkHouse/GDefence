package com.darkhouse.gdefence.Model.Level;


import com.badlogic.gdx.scenes.scene2d.Actor;
import com.darkhouse.gdefence.Level.Path.MapTile;

public class MapTileActor extends Actor{
    private MapTile mapTile;

    public MapTileActor(MapTile tile) {
        super();
        this.mapTile = tile;
        setBounds(tile.getX(), tile.getY(), tile.getWidth(), tile.getHeight());
    }



    public MapTile getMapTile() {
        return mapTile;
    }

    //public void setMapTile(MapTile mapTile) {
    //    this.mapTile = mapTile;
    //}



}
