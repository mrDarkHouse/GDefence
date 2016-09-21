package com.darkhouse.gdefence.Model.Panels;


import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;

public class GoldPanel extends AbstractPanel{

    public GoldPanel(int x, int y, int width, int height) {
        super(x, y, width, height);


        int heightImg = height/2;
        int widthImg = heightImg;
        int heightTxt = height - heightImg;

        add(new Image(AssetLoader.coin)).width(widthImg).height(heightImg).padTop(20).align(Align.center).row();
        Label l = new Label(GDefence.getInstance().user.getGold() + "", AssetLoader.getSkin());
        l.setAlignment(Align.center);
        //l.setFontScale(0.5f);
        add(l);



    }
}
