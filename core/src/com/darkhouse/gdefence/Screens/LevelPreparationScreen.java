package com.darkhouse.gdefence.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.InventorySystem.inventory.Inventory;
import com.darkhouse.gdefence.InventorySystem.inventory.InventoryActor;
import com.darkhouse.gdefence.InventorySystem.inventory.OverallInventory;
import com.darkhouse.gdefence.Model.PreparationSpellInventoryActor;
import com.darkhouse.gdefence.Model.PreparationTowerInventoryActor;
import com.darkhouse.gdefence.User;

public class LevelPreparationScreen extends AbstractCampainScreen{
    private int level;
//    private InventoryActor inventoryActor;
    private OverallInventory inventoryActor;
    private PreparationTowerInventoryActor preparationTowerInventoryActor;
    private PreparationSpellInventoryActor preparationSpellInventoryActor;
    private Inventory saveInventory[];

    public LevelPreparationScreen() {
        super("prepare_to_level");
    }
    public void setLevel(int level){
        setName("prepare_to_level", String.valueOf(level));
//        this.level = level;
        load(level);
    }



    @Override
    public void show() {
        super.show();
        //saveInventory = User.getTowerInventory().copy();
        //saveInventory.copy(User.getTowerInventory());
//        saveInventory = new Inventory[3];
//        saveInventory[0] = new Inventory(User.getTowerInventory());
//        saveInventory[1] = new Inventory(User.getSpellInventory());
//        saveInventory[2] = new Inventory(User.getDetailInventory());


//        load(level);
        //System.out.println(saveInventory.getSlots());
    }

    public void load(final int level){
        saveInventory = new Inventory[3];
        saveInventory[0] = new Inventory(User.getTowerInventory());
        saveInventory[1] = new Inventory(User.getSpellInventory());
        saveInventory[2] = new Inventory(User.getDetailInventory());

//        inventoryActor = new InventoryActor(saveInventory, new DragAndDrop(),
//                GDefence.getInstance().assetLoader.get("skins/uiskin.json", Skin.class));
        inventoryActor = new OverallInventory(saveInventory);
        stage.addActor(inventoryActor);
        inventoryActor.init();
        preparationTowerInventoryActor = new PreparationTowerInventoryActor(new DragAndDrop(),
                GDefence.getInstance().assetLoader.getSkin());
        preparationTowerInventoryActor.setPosition(700, 250);
        inventoryActor.addTarget(preparationTowerInventoryActor);
        inventoryActor.addSlotAsTarget(preparationTowerInventoryActor.getDragAndDrop());
//        inventoryActor.addSlots(preparationTowerInventoryActor);
//        preparationTowerInventoryActor.addSlots(inventoryActor);
        stage.addActor(preparationTowerInventoryActor);
        preparationTowerInventoryActor.init();


        inventoryActor.setPosition(100, 50);

        preparationSpellInventoryActor = new PreparationSpellInventoryActor(new DragAndDrop(),
                GDefence.getInstance().assetLoader.getSkin());
        preparationSpellInventoryActor.setPosition(700, 100);
        inventoryActor.addTarget(preparationSpellInventoryActor);
        inventoryActor.addSlotAsTarget(preparationSpellInventoryActor.getDragAndDrop());

//        preparationSpellInventoryActor.addSlots(inventoryActor);
        stage.addActor(preparationSpellInventoryActor);
        preparationSpellInventoryActor.init();


        TextButton startButton = new TextButton("Start", GDefence.getInstance().assetLoader.getSkin());
        startButton.setSize(150, 70);
        startButton.setPosition(/*Gdx.graphics.getWidth() - 200*/1080, 30);
        startButton.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                GDefence.getInstance().setScreen(new LevelLoadingScreen(level, preparationTowerInventoryActor.getInventory(),
                        preparationSpellInventoryActor.getInventory()));
                return true;
            }
        });
        stage.addActor(startButton);


    }

//    @Override
//    public void hide() {
//        //User.setTowerInventory(saveInventory/*.copy()*/);
//        super.hide();
//    }

//    @Override
//    public void resize(int width, int height) {
//
//    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}
