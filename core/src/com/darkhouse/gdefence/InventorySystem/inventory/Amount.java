package com.darkhouse.gdefence.InventorySystem.inventory;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;

public class Amount extends Label{
    private Slot slot;


    public Amount(Skin skin, Slot slot) {
        super("" + slot.getAmount(), skin);
        this.slot = slot;
        init();

    }


    private void init(){
        Label.LabelStyle style = new Label.LabelStyle();
        style.background = new TextureRegionDrawable(new TextureRegion(AssetLoader.cell));
        style.font = FontLoader.impact20;
        style.fontColor = Color.BLACK;
        setStyle(style);
        setSize(22, 22);

        change();

    }

    public void change(){
        if(slot.getAmount() > 1){
            setText("" + slot.getAmount());
            setVisible(true);
        }else {
            setVisible(false);
        }
    }


}
