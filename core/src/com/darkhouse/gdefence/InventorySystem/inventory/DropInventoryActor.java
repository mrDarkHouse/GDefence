package com.darkhouse.gdefence.InventorySystem.inventory;


import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.InventorySystem.inventory.Tooltip.SlotTooltip;
import com.darkhouse.gdefence.InventorySystem.inventory.Tooltip.TooltipListener;

public class DropInventoryActor extends Window{
    protected Array<DropSlotActor> actorArray;
    private Skin skin;

    public DropInventoryActor(Array<DropSlot> slots, Skin skin) {
        super("Drop", skin, "description");
        this.skin = skin;

        getTitleLabel().setAlignment(Align.center);
        setMovable(false);

        Table table = new Table();
        setPosition(100, 250);
        table.padLeft(20);
        table.padRight(20);
        table.defaults().space(8);
        table.defaults().size(60, 60);
        table.row().fill().expandX();



        actorArray = new Array<DropSlotActor>();
        for (DropSlot s:slots){
            DropSlotActor a = new DropSlotActor(s, skin);
            actorArray.add(a);
        }

        for (DropSlotActor s:actorArray){
            table.add(s);

            //row
        }
        add(table);
    }
    public void init(){//init must be after stage.add(InventoryActor)
        for(DropSlotActor s: actorArray) {
            SlotTooltip tooltip = new SlotTooltip(s.getStage(), s.getSlot(), skin);
            tooltip.setTouchable(Touchable.disabled);
            s.addListener(new TooltipListener(tooltip, true));
        }

    }

}
