package com.darkhouse.gdefence.InventorySystem.inventory;


import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.User;

public class OverallInventory {

    private Inventory[] inventories;
    private InventoryActor[] actors;
    private int currentInventory;

    public Inventory getCurrentInventory(){
        return inventories[currentInventory];
    }



    public OverallInventory() {
        inventories = new Inventory[3];
        inventories[0] = new Inventory();
        inventories[1] = new Inventory();
        inventories[2] = new Inventory();
        actors = new InventoryActor[3];
        actors[0] = new InventoryActor(User.getTowerInventory(), new DragAndDrop(),
                GDefence.getInstance().assetLoader.get("skins/uiskin.json", Skin.class));
        actors[1] = new InventoryActor(User.getTowerInventory(), new DragAndDrop(),
                GDefence.getInstance().assetLoader.get("skins/uiskin.json", Skin.class));
        actors[2] = new InventoryActor(User.getTowerInventory(), new DragAndDrop(),
                GDefence.getInstance().assetLoader.get("skins/uiskin.json", Skin.class));

        currentInventory = 0;


    }

    private void update(){
        for (int i = 0; i < inventories.length; i++){
            if(inventories[i] != getCurrentInventory()){
                //inventories[i].hide();
            }
        }


    }







}
