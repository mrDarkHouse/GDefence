package com.darkhouse.gdefence.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.InventorySystem.inventory.Inventory;
import com.darkhouse.gdefence.InventorySystem.inventory.InventoryActor;
import com.darkhouse.gdefence.Model.PreparationTowerInventoryActor;
import com.darkhouse.gdefence.User;

public class LevelPreparationScreen extends AbstractCampainScreen{
    private int level;
    private InventoryActor inventoryActor;
    private PreparationTowerInventoryActor preparationTowerInventoryActor;
    private Inventory saveInventory;

    public LevelPreparationScreen(int level) {
        super("" + level);
        this.level = level;

    }

    @Override
    public void show() {
        super.show();
        //saveInventory = User.getInventory().copy();
        //saveInventory.copy(User.getInventory());
        saveInventory = new Inventory(User.getInventory());


        load(level);
    }

    private void load(final int level){
        inventoryActor = new InventoryActor(User.getInventory(), new DragAndDrop(), GDefence.getInstance().assetLoader.get("skins/uiskin.json", Skin.class));
        stage.addActor(inventoryActor);
        inventoryActor.init();
        preparationTowerInventoryActor = new PreparationTowerInventoryActor(new DragAndDrop(), GDefence.getInstance().assetLoader.get("skins/uiskin.json", Skin.class));
        inventoryActor.addSlots(preparationTowerInventoryActor);
        preparationTowerInventoryActor.addSlots(inventoryActor);
        stage.addActor(preparationTowerInventoryActor);
        preparationTowerInventoryActor.init();

        inventoryActor.setPosition(100, 50);

        TextButton startButton = new TextButton("Start", GDefence.getInstance().assetLoader.getSkin());
        startButton.setSize(150, 70);
        startButton.setPosition(Gdx.graphics.getWidth() - 200, 30);
        startButton.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                GDefence.getInstance().setScreen(new LevelMap(level, preparationTowerInventoryActor.getInventory()));
                return true;
            }
        });
        stage.addActor(startButton);


    }

    @Override
    public void hide() {
        System.out.println(User.getInventory().getSlots());
        User.setInventory(saveInventory/*.copy()*/);
        System.out.println(User.getInventory().getSlots());
        super.hide();
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
}
