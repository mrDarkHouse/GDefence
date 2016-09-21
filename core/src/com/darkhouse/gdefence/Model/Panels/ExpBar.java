package com.darkhouse.gdefence.Model.Panels;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;

public class ExpBar extends ProgressBar{
    public ExpBar(int width, int height, int x, int y) {
        super(0, 100, 0.5f, false, AssetLoader.getExpBarSkin());

        //ProgressBar bar = new ProgressBar(0, 10, 0.5f, false, AssetLoader.getExpBarSkin());
        int expBarSize[] = {width, height};
        setPosition(x, y);
        //setPosition(Gdx.graphics.getWidth() - expBarSize[0], userlevelButton.getY() - expBarSize[1] - 4);
        setSize(expBarSize[0], expBarSize[1]);
        // bar.setAnimateDuration(5);

        //setValue(7.8f);
        setValue(GDefence.getInstance().user.getCurrentExp());
    }

    public ExpBar(int width, int height) {
        super(0, 100, 0.5f, false, AssetLoader.getExpBarSkin());

        //ProgressBar bar = new ProgressBar(0, 10, 0.5f, false, AssetLoader.getExpBarSkin());
        int expBarSize[] = {width, height};
        //setPosition(x, y);
        //setPosition(Gdx.graphics.getWidth() - expBarSize[0], userlevelButton.getY() - expBarSize[1] - 4);
        setSize(expBarSize[0], expBarSize[1]);
        // bar.setAnimateDuration(5);

        //setValue(7.8f);
        setValue(GDefence.getInstance().user.getCurrentExp());
    }



}
