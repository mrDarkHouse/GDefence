package com.darkhouse.gdefence.Screens.BottomPanel;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.InventorySystem.inventory.InventoryActor;
import com.darkhouse.gdefence.InventorySystem.inventory.OverallInventory;
import com.darkhouse.gdefence.InventorySystem.inventory.SlotActor;
import com.darkhouse.gdefence.InventorySystem.inventory.Target.SlotTarget;
import com.darkhouse.gdefence.Model.Panels.TowerCraftPanel;
import com.darkhouse.gdefence.Screens.AbstractCampainScreen;
import com.darkhouse.gdefence.User;

public class Arsenal extends AbstractCampainScreen{
    private OverallInventory inventoryActor;
    private TowerMap towerMap;



    public Arsenal() {
        super("Arsenal");
        //init();
    }

    @Override
    public void show() {
        super.show();
//        inventoryActor.remove();
//        inventoryActor = null;
//        inventoryActor = new InventoryActor(User.getTowerInventory(), new DragAndDrop(),
//                GDefence.getInstance().assetLoader.get("skins/uiskin.json", Skin.class));
//        stage.addActor(inventoryActor);
//        inventoryActor.init();

//        inventoryActor.setTowerInventory(User.getTowerInventory());
        //init();
    }

    public void init(){
        //Skin skin = LibgdxUtils.assets.get("skins/uiskin.json", Skin.class);
//        inventoryActor = new InventoryActor(User.getTowerInventory(), new DragAndDrop(),
//                GDefence.getInstance().assetLoader.get("skins/uiskin.json", Skin.class));
        inventoryActor = new OverallInventory();
        stage.addActor(inventoryActor);
        inventoryActor.init();
        towerMap = new TowerMap(GDefence.getInstance().assetLoader.get("skins/uiskin.json", Skin.class));
        stage.addActor(towerMap);
        final TextButton towerMapButton = new TextButton("Tower Map", GDefence.getInstance().assetLoader.get("skins/uiskin.json", Skin.class));
        towerMapButton.setPosition(Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 100);
        towerMapButton.setSize(140, 40);
        towerMapButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                towerMap.setVisible(true);
                towerMap.toFront();
                return true;
            }
        });
        stage.addActor(towerMapButton);
        TowerCraftPanel towerCraftPanel = new TowerCraftPanel(new DragAndDrop(), inventoryActor, GDefence.getInstance().assetLoader.get("skins/uiskin.json", Skin.class));
        stage.addActor(towerCraftPanel);
        towerCraftPanel.init();

//        inventoryActor.addTarget(towerCraftPanel.getRecipeSlot());
//        inventoryActor.addSlotAsSourceTarget(towerCraftPanel.getDragAndDrop());


    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}
