package com.darkhouse.gdefence.InventorySystem.inventory.Tooltip;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.Level.Ability.Tower.Ability;

public class AbilityDemoTooltip extends AbstractTooltip{
    private Label label;
    private Ability.AbilityPrototype ab;
    private String s;

    public AbilityDemoTooltip(Stage stage, Ability.AbilityPrototype ab, Skin skin) {
        super(ab.getName() + ab.getGemStat(), skin);
        getTitleLabel().setAlignment(Align.center);
//        this.text = ab.getTooltip();
        this.ab = ab;
        init();
        setVisible(false);
        stage.addActor(this);
    }

    private void init(){
        s = System.getProperty("line.separator");
        if(ab.getAbilitiesToSaveOnCraft().size != 0) s += "(" + GDefence.getInstance().assetLoader.getWord("saveOnCraft1") + ")";
        else                                         s += "(" + GDefence.getInstance().assetLoader.getWord("saveOnCraft2") + ")";
        label = new Label(ab.getTooltip() + s, /*FontLoader.generateStyle(15, Color.LIGHT_GRAY*/
                GDefence.getInstance().assetLoader.getSkin(), "description");
        label.getStyle().font.getData().markupEnabled = true;
        label.setAlignment(Align.center);
        add(label);
        pack();
    }
    public void hasChanged() {
        getTitleLabel().setText(ab.getName() + ab.getGemStat());
        label.setText(ab.getTooltip() + s);
    }

}
