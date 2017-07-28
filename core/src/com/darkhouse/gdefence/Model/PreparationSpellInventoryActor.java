package com.darkhouse.gdefence.Model;


import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.darkhouse.gdefence.InventorySystem.inventory.Inventory;
import com.darkhouse.gdefence.InventorySystem.inventory.InventoryActor;
import com.darkhouse.gdefence.InventorySystem.inventory.SlotActor;
import com.darkhouse.gdefence.InventorySystem.inventory.Source.SlotSource;
import com.darkhouse.gdefence.InventorySystem.inventory.Target.SlotTarget;
import com.darkhouse.gdefence.Objects.SpellObject;
import com.darkhouse.gdefence.Objects.TowerObject;

public class PreparationSpellInventoryActor extends InventoryActor{

    public static class PreparationSpellInventory extends Inventory {

        public PreparationSpellInventory() {
            super(SpellObject.class, 4, 1);
        }

    }

    public PreparationSpellInventoryActor(DragAndDrop dragAndDrop, Skin skin) {
        super(new PreparationSpellInventory(), dragAndDrop, skin);
        getTitleLabel().setText("Level spells");
    }

    @Override
    protected void setDefaults() {
        defaults().space(8);
        defaults().size(60, 60);
        row().fill().expandX();
        setRowNumber(4);
        setRows(4);
    }

    @Override
    protected void addSourceTarget(SlotActor slotActor) {
        dragAndDrop.addSource(new SlotSource(slotActor));
        dragAndDrop.addTarget(new SlotTarget(slotActor));
    }
}
