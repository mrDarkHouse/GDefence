package com.darkhouse.gdefence.Model.Level;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.InventorySystem.inventory.Slot;
import com.darkhouse.gdefence.Level.MapTile;

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
