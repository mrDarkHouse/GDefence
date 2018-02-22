package com.darkhouse.gdefence.Model.Level;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Screens.LevelMap;

public class HealthBar extends WidgetGroup{
    protected ProgressBar healthBar;
    protected Label text;

    private int width;
    private int height;
    private int x;
    private int y;

    public HealthBar(int width, int height, int x, int y) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    public void init(){
        initBar();
        healthBar.getStyle().background.setMinHeight(height);
        healthBar.getStyle().knobBefore.setMinHeight(height - 2);
        healthBar.setPosition(x, y);
        //setPosition(Gdx.graphics.getWidth() - expBarSize[0], userlevelButton.getY() - expBarSize[1] - 4);
        healthBar.setSize(width, height);
        healthBar.setValue(LevelMap.getLevel().getHealthNumber());
        healthBar.setAnimateDuration(0.5f);
        addActor(healthBar);
        //add(healthBar);
        initLabel();
    }
    protected void initBar(){
        healthBar = new ProgressBar(0, LevelMap.getLevel().getMaxHP(), 0.5f, false,
                /*GDefence.getInstance().assetLoader.getExpBarSkin()*/GDefence.getInstance().assetLoader.getSkin(), "health-bar");
    }

    protected void initLabel(){
        text = new Label(LevelMap.getLevel().getHealthNumber() + "/" + LevelMap.getLevel().getMaxHP(),
                FontLoader.generateStyle(0, 30, Color.BLACK));
        text.setPosition(healthBar.getX() + healthBar.getWidth()/2 - text.getWidth()/2,
                healthBar.getY() + healthBar.getHeight()/2 - text.getHeight()/2);
        addActor(text);
        //add(text);
    }
    public void update(){
        healthBar.setValue(LevelMap.getLevel().getHealthNumber());
        text.setText(LevelMap.getLevel().getHealthNumber() + "/" + LevelMap.getLevel().getMaxHP());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
//        healthBar.setValue(LevelMap.getLevel().getHealthNumber());
//        text.setText(LevelMap.getLevel().getHealthNumber() + "/" + LevelMap.getLevel().getMaxHP());
    }
}
