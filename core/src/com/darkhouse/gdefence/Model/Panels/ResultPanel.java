package com.darkhouse.gdefence.Model.Panels;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.StatManager;
import com.darkhouse.gdefence.Level.Level;
import com.darkhouse.gdefence.Screens.LevelMap;

public class ResultPanel extends AbstractPanel{
    private StatManager manager;


    public ResultPanel(boolean isWin) {
        manager = LevelMap.getLevel().getManager();

        init();
    }

    private void init(){
        String mobsKilled = "Killed mobs: " + manager.getMobsKilled();
        String hpLoose = "Hp loose: " + manager.getHpLoose();
        String moneySpend = "Money spend: " + manager.getMoneySpend();

        setBackground(new TextureRegionDrawable(new TextureRegion(AssetLoader.infoPanelFone)));

        Label mobsKilledLabel = new Label(mobsKilled, AssetLoader.getTimerSkin());
        Label hpLooseLabel = new Label(hpLoose, AssetLoader.getTimerSkin());
        Label mobeySpendLabel = new Label(moneySpend, AssetLoader.getTimerSkin());

        add(mobsKilledLabel).align(Align.left).row();
        add(hpLooseLabel).align(Align.left).row();
        add(mobeySpendLabel).align(Align.left).row();



    }


}
