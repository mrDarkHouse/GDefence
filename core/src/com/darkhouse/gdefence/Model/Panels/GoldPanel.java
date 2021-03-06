package com.darkhouse.gdefence.Model.Panels;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;

public class GoldPanel extends AbstractPanel{
    private Label textLabel;

    public GoldPanel(int x, int y, int width, int height) {
        super(x, y, width, height);


        int heightImg = height/2;
        int widthImg = heightImg;
        int heightTxt = height - heightImg;

        add(new Image(GDefence.getInstance().assetLoader.get("coin.png", Texture.class))).width(widthImg).height(heightImg).padTop(20).align(Align.center).row();
        textLabel = new Label(GDefence.getInstance().user.getGold() + "", /*GDefence.getInstance().assetLoader.getSkin()*/FontLoader.generateStyle(0, 26, Color.BLACK, 1, Color.WHITE));//do with FontLoader
        textLabel.setAlignment(Align.center);
        //l.setFontScale(0.5f);
        add(textLabel);





    }

    @Override
    public void act(float delta) {
        super.act(delta);
        textLabel.setText(GDefence.getInstance().user.getGold() + "");
    }

}
