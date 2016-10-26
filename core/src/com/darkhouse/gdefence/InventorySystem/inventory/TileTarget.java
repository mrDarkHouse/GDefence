package com.darkhouse.gdefence.InventorySystem.inventory;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Level.Level;
import com.darkhouse.gdefence.Level.MapTile;
import com.darkhouse.gdefence.Model.Level.MapTileActor;

public class TileTarget extends DragAndDrop.Target{
    private MapTileActor tile;
    private DragAndDrop dragAndDrop;

    public TileTarget(MapTileActor tile, DragAndDrop dragAndDrop) {
        super(tile);
        this.tile = tile;
        this.dragAndDrop = dragAndDrop;
        //System.out.println(((MapTileActor)getActor()).getMapTile());
    }

    @Override
    public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
       // Image i = ((Image)payload.getDragActor());
        //dragAndDrop.setDragActorPosition(-i.getWidth()/2, i.getHeight()/2);//not optimized

        //TextureRegionDrawable t = new TextureRegionDrawable(new TextureRegion(AssetLoader.rockTower));
        //i.setDrawable(t);
        //Level.getMap().setRangeTower(payload.getDragActor().getX(), payload.getDragActor().getY());
        return true;
    }

    @Override
    public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {

    }

    @Override
    public void reset(DragAndDrop.Source source, DragAndDrop.Payload payload) {

    }

    private static Actor createActor(MapTile tile){
        MapTileActor a = new MapTileActor(tile);
       // a.setMapTile(tile);


        //System.out.println(tile.getX() + " " + tile.getY() + " " + tile.getWidth() + " " + tile.getHeight());
        //System.out.println(a.getX() + " " + a.getY() + " " + a.getWidth() + " " + a.getHeight());
        return a;
    }
}
