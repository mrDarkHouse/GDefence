package com.darkhouse.gdefence.Screens.BottomPanel;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.InventorySystem.LibgdxUtils;
import com.darkhouse.gdefence.InventorySystem.inventory.Inventory;
import com.darkhouse.gdefence.InventorySystem.inventory.InventoryActor;
import com.darkhouse.gdefence.Model.CompactArsenal;
import com.darkhouse.gdefence.Screens.AbstractCampainScreen;

public class Arsenal extends AbstractCampainScreen{
    private CompactArsenal compactArsenal;


    public Arsenal() {
        super("Arsenal");
    }

    @Override
    public void show() {
        super.show();
        //Skin skin = LibgdxUtils.assets.get("skins/uiskin.json", Skin.class);
        stage.addActor(new InventoryActor(new Inventory(), new DragAndDrop(), AssetLoader.cellSkin));


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}
