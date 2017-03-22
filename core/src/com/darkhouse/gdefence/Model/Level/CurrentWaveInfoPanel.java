package com.darkhouse.gdefence.Model.Level;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Wave;
import com.darkhouse.gdefence.Screens.LevelMap;

public class CurrentWaveInfoPanel extends Table{
    private Label mobsCount = new Label("Mobs left: " + Wave.mobs.size(), GDefence.getInstance().assetLoader.getCurrentInfoPanelSkin());
    private NextWaveTimer timer;


    public CurrentWaveInfoPanel(NextWaveTimer t) {
        this.timer = t;
        init();
    }

    private void init() {
        setBackground(new TextureRegionDrawable(new TextureRegion(GDefence.getInstance().assetLoader.get("infoPanelFone.png", Texture.class))));
        add(mobsCount);

        pack();


    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
//        if(timer.getTime() <= 0) {
            super.draw(batch, parentAlpha);
//        }
    }


    @Override
    public void act(float delta) {
        super.act(delta);
        mobsCount.setText("Mobs left: " + Wave.mobs.size());

    }
}
