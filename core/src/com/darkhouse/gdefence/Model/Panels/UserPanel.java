package com.darkhouse.gdefence.Model.Panels;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;

public class UserPanel extends AbstractPanel {
    public UserPanel(int x, int y, int width, int height, GDefence mainClass) {
        super(x, y, width, height, mainClass);



        ImageTextButton userlevelButton = new ImageTextButton("" + mainClass.user.getLevel(), AssetLoader.getUserLevelSkin());
        //userlevelButton.getLabel().setFontScale(1.5f);
        int userLevelSize[] = {width/4, width/4};
        //userlevelButton.setSize(userLevelSize[0], userLevelSize[1]);
        //userlevelButton.setPosition();
//        userlevelButton.addListener(new InputListener() {
//                                        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                                            return true;
//                                        }
//                                    }
//        );


        int expBarSize[] = {width, height - userLevelSize[1]};
        ExpBar bar = new ExpBar(expBarSize[0], expBarSize[1], mainClass);

        //add(new GoldPanel(mainClass)).width(width/6*2).height(height);
        add(userlevelButton).width(userLevelSize[0]).height(userLevelSize[1]).row();
        add(bar);

        //System.out.println(getY());

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        //debug();
    }
}
