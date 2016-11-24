package com.darkhouse.gdefence.Screens.BottomPanel;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.InventorySystem.inventory.InventoryActor;
import com.darkhouse.gdefence.Screens.AbstractCampainScreen;
import com.darkhouse.gdefence.User;

public class Arsenal extends AbstractCampainScreen{
    private InventoryActor inventoryActor;
    private TowerMap towerMap;



    public Arsenal() {
        super("Arsenal");
    }

    @Override
    public void show() {
        super.show();

        //Skin skin = LibgdxUtils.assets.get("skins/uiskin.json", Skin.class);
        inventoryActor = new InventoryActor(User.getInventory(), new DragAndDrop(), GDefence.getInstance().assetLoader.get("skins/uiskin.json", Skin.class));
        stage.addActor(inventoryActor);
        towerMap = new TowerMap(GDefence.getInstance().assetLoader.get("skins/uiskin.json", Skin.class));
        stage.addActor(towerMap);
        final TextButton towerMapButton = new TextButton("Tower Map", GDefence.getInstance().assetLoader.getSkin());
        towerMapButton.setPosition(Gdx.graphics.getWidth() - 200, 500);
        towerMapButton.setSize(140, 40);
        towerMapButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                towerMap.setVisible(true);
                return true;
            }
        });
        stage.addActor(towerMapButton);







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
