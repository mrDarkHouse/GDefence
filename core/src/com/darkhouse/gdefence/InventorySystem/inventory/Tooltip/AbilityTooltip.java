package com.darkhouse.gdefence.InventorySystem.inventory.Tooltip;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.Level.Ability.Mob.MobAbility;

public class AbilityTooltip extends Window{

    private Skin skin;
    private Label label;
    private String text;
    private Table t;

    public AbilityTooltip(Table t, MobAbility ab, Skin skin) {
        super(ab.getName(), skin);
        getTitleLabel().setAlignment(Align.center);
//        getTitleLabel().clear();
//        getTitleLabel().setVisible(false);
//        getTitleTable().setVisible(false);

        this.skin = skin;
        this.text = ab.getTooltip();
        this.t = t;
        init();
        setVisible(false);
        t.getStage().addActor(this);
    }
    private void init(){
//        skin.getFont().
        label = new Label(text, FontLoader.generateStyle(15, Color.LIGHT_GRAY));
        label.getStyle().font.getData().markupEnabled = true;
        label.setAlignment(Align.center);
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
