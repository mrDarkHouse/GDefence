package com.darkhouse.gdefence.Model.Level;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.Screens.LevelMap;

public class EnegryBar extends WidgetGroup{
    private ProgressBar energyBar;
    private Label text;

    public EnegryBar(int width, int height, int x, int y) {
        energyBar = new ProgressBar(0, LevelMap.getLevel().getMaxEnergy(), 0.5f, true, /*GDefence.getInstance().assetLoader.getEnergyBarSkin()*/ GDefence.getInstance().assetLoader.getSkin(), "energy-bar");
        energyBar.getStyle().background.setMinWidth(width);
        energyBar.getStyle().knobBefore.setMinWidth(width - 2);
        energyBar.setPosition(x, y);
        //setPosition(Gdx.graphics.getWidth() - expBarSize[0], userlevelButton.getY() - expBarSize[1] - 4);
        energyBar.setSize(width, height);
        energyBar.setValue(LevelMap.getLevel().getEnergyNumber());
        energyBar.setAnimateDuration(0.5f);
        addActor(energyBar);
        initLabel();
    }

    private void initLabel(){
        text = new Label(LevelMap.getLevel().getEnergyNumber() + "/" + LevelMap.getLevel().getMaxEnergy(),
                FontLoader.generateStyle(26, Color.BLACK));
        Container l = new Container(text);
        l.setPosition(energyBar.getX() + energyBar.getWidth()/2,
                energyBar.getY() + energyBar.getHeight()/2);

        l.setTransform(true);
        l.addAction(Actions.rotateTo(90));

        addActor(l);
    }
    public void update(){
        energyBar.setValue(LevelMap.getLevel().getEnergyNumber());
        text.setText(LevelMap.getLevel().getEnergyNumber() + "/" + LevelMap.getLevel().getMaxEnergy());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
//        energyBar.setValue(LevelMap.getLevel().getEnergyNumber());
//        text.setText(LevelMap.getLevel().getEnergyNumber() + "/" + LevelMap.getLevel().getMaxEnergy());
    }


}
