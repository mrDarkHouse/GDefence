package com.darkhouse.gdefence.Screens.BottomPanel;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.InventorySystem.LibgdxUtils;
import com.darkhouse.gdefence.InventorySystem.inventory.Inventory;
import com.darkhouse.gdefence.InventorySystem.inventory.InventoryActor;
import com.darkhouse.gdefence.Model.CompactArsenal;
import com.darkhouse.gdefence.Screens.AbstractCampainScreen;
import com.darkhouse.gdefence.User;

public class Arsenal extends AbstractCampainScreen{
    private CompactArsenal compactArsenal;
    private InventoryActor inventoryActor;



    public Arsenal() {
        super("Arsenal");
    }

    @Override
    public void show() {
        super.show();
        //Skin skin = LibgdxUtils.assets.get("skins/uiskin.json", Skin.class);
        inventoryActor = new InventoryActor(User.getInventory(), new DragAndDrop(), AssetLoader.cellSkin);
        stage.addActor(inventoryActor);

        TextButton towerMap = new TextButton("Tower Map", AssetLoader.getSkin());
        towerMap.setPosition(Gdx.graphics.getWidth() - 200, 500);
        towerMap.setSize(140, 40);
        stage.addActor(towerMap);






    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
