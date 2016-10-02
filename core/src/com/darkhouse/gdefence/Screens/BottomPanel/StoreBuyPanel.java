package com.darkhouse.gdefence.Screens.BottomPanel;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.InventorySystem.inventory.*;

public class StoreBuyPanel extends InventoryActor{
    private Label[] cost;



    public StoreBuyPanel(Inventory inventory) {
        super(inventory, new DragAndDrop(), AssetLoader.cellSkin);
        getTitleLabel().setText("Store");

    }

    public void store(Item item, int amount){
        getInventory().store(item, amount);
        addCost();
    }

    private void addCost(){
        for (int i = 0; i < getInventory().getSlots().size; i++){
            Item item = getInventory().getSlots().get(i).getItem();
            if(item!= null){
                cost[i].setText("$" + item.getGlobalCost());
            }

        }
    }

    @Override
    protected void setDefaults() {
        setPosition(740, 200);
        defaults().space(8);
        defaults().size(60, 60);
        row().fill().expandX();
        setRowNumber(1);
    }

    @Override
    protected void addSourceTarget(DragAndDrop dragAndDrop, SlotActor slotActor) {
        dragAndDrop.addSource(new ShopSlotSource(slotActor));
        //dragAndDrop.addTarget(new SlotTarget(slotActor));
    }

    @Override
    public void addSlots(Array<SlotActor> slots) {
        for(SlotActor s:slots){
            //dragAndDrop.addSource(new ShopSlotSource(s));
            dragAndDrop.addTarget(new SlotTarget(s));

        }
    }

    @Override
    protected void beforeRow(Slot slot, int i) {
        //Item item = slot.getItem();
        //if(item != null) {
        //    ItemEnum.Tower tower = (ItemEnum.Tower) item;
        //    super.add(new Label("$" + tower.getCost(), FontLoader.generateStyle(16, Color.BLACK)));
        //    System.out.println(item);
        //}else {
        if(cost == null){
            cost = new Label[getInventory().getSlots().size];
        }
        cost[i] = new Label("", FontLoader.generateStyle(26, Color.BLACK));
        super.add(cost[i]);
        //}
    }
}
