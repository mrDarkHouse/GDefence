package com.darkhouse.gdefence.Model.Level;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Screens.LevelMap;

public class NextWaveInfoPanel extends Table{
    private NextWaveTimer nextWaveTimer;

    public NextWaveInfoPanel() {
        init();
    }

    private void init(){
        nextWaveTimer = new NextWaveTimer();

        add(new Label("Prepare Wave: " + (LevelMap.getLevel().currentWave + 1) , AssetLoader.getSkin())).row();
        add(new Label("Name: " + Mob.getMobById(LevelMap.getLevel().getCurrentWave().getMobID()).getName(), AssetLoader.getInfoPanelSkin())).row();
        add(new Label("Health: " + Mob.getMobById(LevelMap.getLevel().getCurrentWave().getMobID()).getHealth(), AssetLoader.getInfoPanelSkin())).row();

        add(nextWaveTimer).row();
    }

    public void draw(Batch batch, float parentAlpha) {
        if(nextWaveTimer.getTime() > 0) {
            super.draw(batch, parentAlpha);
        }
    }

    public void update(){

    }
}
