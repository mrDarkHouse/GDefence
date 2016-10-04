package com.darkhouse.gdefence.Model.Level;


import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.darkhouse.gdefence.Level.MapTile;

public class TileTarget extends DragAndDrop.Target{

    public TileTarget(MapTileActor tile) {
        super(tile);
        //System.out.println(((MapTileActor)getActor()).getMapTile());
    }

    @Override
    public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
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
