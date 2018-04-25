package com.darkhouse.gdefence.InventorySystem.inventory;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;

public class Amount extends Label{
    private AbstractSlot slot;

    public Amount(Skin skin, AbstractSlot slot) {
        super("" + slot.getAmount(), skin);
        this.slot = slot;
        init();
    }

    private void init(){
        Label.LabelStyle style = new Label.LabelStyle();
        style.background = new TextureRegionDrawable(new TextureRegion(GDefence.getInstance().assetLoader.get("cell.png", Texture.class)));
        style.font = FontLoader.generateFont(0, 20, Color.BLACK);
//        style.fontColor = Color.BLACK;
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
