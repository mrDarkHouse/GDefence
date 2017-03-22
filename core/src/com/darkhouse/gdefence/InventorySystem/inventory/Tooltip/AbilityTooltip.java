package com.darkhouse.gdefence.InventorySystem.inventory.Tooltip;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.darkhouse.gdefence.Helpers.FontLoader;

public class AbilityTooltip extends Window{

    private Skin skin;
    private Label label;
    private String text;
    private Table t;

    public AbilityTooltip(Table t, String text, Skin skin) {
        super("", skin);
//        getTitleLabel().clear();
//        getTitleLabel().setVisible(false);
//        getTitleTable().setVisible(false);

        this.skin = skin;
        this.text = text;
        this.t = t;
        init();
        setVisible(false);
        t.getStage().addActor(this);
    }
    private void init(){
//        skin.getFont().
        label = new Label(text, FontLoader.generateStyle(15, Color.WHITE));
        label.getStyle().font.getData().markupEnabled = true;
        add(label);
        pack();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(t.isVisible()){
            super.draw(batch, parentAlpha);
        }
    }
}
