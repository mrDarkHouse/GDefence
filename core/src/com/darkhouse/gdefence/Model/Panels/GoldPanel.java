package com.darkhouse.gdefence.Model.Panels;


import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;

public class GoldPanel extends AbstractPanel{

    public GoldPanel(GDefence mainClass) {
        super(mainClass);


        add(new Image(AssetLoader.coin)).row();
        Label l = new Label(this.mainClass.user.getGold() + "", mainClass.getSkin());
        l.setAlignment(Align.center);
        l.setFontScale(0.5f);
        add(l);



    }
}
