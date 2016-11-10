package com.darkhouse.gdefence.Model.Level;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.Screens.LevelMap;

public class HealthBar extends WidgetGroup{
    private ProgressBar healthBar;
    private Label text;

    public HealthBar(int width, int height, int x, int y) {
        healthBar = new ProgressBar(0, LevelMap.getLevel().getMaxHP(), 0.5f, false, GDefence.getInstance().assetLoader.getExpBarSkin());
        healthBar.getStyle().background.setMinHeight(height);
        healthBar.getStyle().knob.setMinHeight(height);
        healthBar.setPosition(x, y);
        //setPosition(Gdx.graphics.getWidth() - expBarSize[0], userlevelButton.getY() - expBarSize[1] - 4);
        healthBar.setSize(width, height);
        //setAnimateDuration(5);
        healthBar.setValue(LevelMap.getLevel().getHealthNumber());
        healthBar.setAnimateDuration(0.5f);
        addActor(healthBar);
        //add(healthBar);
        initLabel();
    }

    private void initLabel(){
        text = new Label(LevelMap.getLevel().getHealthNumber() + "/" + LevelMap.getLevel().getMaxHP(),
                FontLoader.generateStyle(30, Color.BLACK));
        text.setPosition(healthBar.getX() + healthBar.getWidth()/2 - text.getWidth()/2,
                healthBar.getY() + healthBar.getHeight()/2 - text.getHeight()/2);
        addActor(text);
        //add(text);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        healthBar.setValue(LevelMap.getLevel().getHealthNumber());
        text.setText(LevelMap.getLevel().getHealthNumber() + "/" + LevelMap.getLevel().getMaxHP());
    }
}
