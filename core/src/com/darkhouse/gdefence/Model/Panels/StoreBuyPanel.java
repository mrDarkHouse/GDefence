package com.darkhouse.gdefence.Model.Panels;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.InventorySystem.inventory.*;
import com.darkhouse.gdefence.InventorySystem.inventory.Source.BuySlotSource;
import com.darkhouse.gdefence.InventorySystem.inventory.Target.SellTarget;
import com.darkhouse.gdefence.InventorySystem.inventory.Target.SlotTarget;
import com.darkhouse.gdefence.Screens.BottomPanel.SellButton;

public class StoreBuyPanel extends InventoryActor{
    private Label[] cost;
    private SellButton sellButton;



    public StoreBuyPanel(Inventory inventory) {
        super(inventory, new DragAndDrop(), GDefence.getInstance().assetLoader.get("skins/uiskin.json", Skin.class));
        getTitleLabel().setText("Store");




    }

    @Override
    protected void beforeInitCells() {
        sellButton = new SellButton();
    }

    @Override
    protected void afterInitCells() {
        super.add(sellButton).expand().fill().row();//do width on 2 columns
    }

    public void store(Item item, int amount){
        getInventory().store(item, amount);
        addCost();
    }

    private void addCost(){
        for (int i = 0; i < getInventory().getSlots().size; i++){
            Item item = getInventory().getSlots().get(i).getPrototype();
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
        setRows(4);
    }

    @Override
    protected void addSourceTarget(SlotActor slotActor) {
        dragAndDrop.addSource(new BuySlotSource(slotActor));

        ////dragAndDrop.addTarget(new SlotTarget(slotActor));
    }

    @Override
    public void addSlots(InventoryActor inventory) {
        for(SlotActor s:inventory.getActorArray()){
            ////dragAndDrop.addSource(new BuySlotSource(s));
            ////inventory.getDragAndDrop().addSource(new CombinedSlotSource(s));
            dragAndDrop.addTarget(new SlotTarget(s));

            inventory.getDragAndDrop().addTarget(new SellTarget(sellButton));//need 1 time


        }
    }

    @Override
    protected void beforeRow(Slot slot, int i) {
        //Item item = slot.getPrototype();
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
