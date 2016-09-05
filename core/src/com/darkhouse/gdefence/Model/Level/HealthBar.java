package com.darkhouse.gdefence.Model.Level;


import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Screens.LevelMap;

public class HealthBar extends ProgressBar{
    public HealthBar(int width, int height, int x, int y) {
        super(0, LevelMap.getLevel().getStartHP(), 0.5f, false, AssetLoader.getExpBarSkin());

        int expBarSize[] = {width, height};
        setPosition(x, y);
        //setPosition(Gdx.graphics.getWidth() - expBarSize[0], userlevelButton.getY() - expBarSize[1] - 4);
        setSize(expBarSize[0], expBarSize[1]);
        //setAnimateDuration(5);

        //setValue(7.8f);
        setValue(LevelMap.getLevel().getHealthNumber());

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setValue(LevelMap.getLevel().getHealthNumber());
        //System.out.println(LevelMap.getLevel().getHealthNumber());
    }
}
