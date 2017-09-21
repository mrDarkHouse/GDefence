package com.darkhouse.gdefence.Model.Panels;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.StatManager;
import com.darkhouse.gdefence.Screens.LevelMap;

public class ResultPanel extends AbstractPanel{
    private StatManager manager;


    public ResultPanel(boolean isWin, StatManager statManager) {
        this.manager = statManager;// = LevelMap.getLevel().getStatManager();

        init();
    }

    private void init(){
        pad(20);
        AssetLoader l = GDefence.getInstance().assetLoader;

        String mobsKilled = l.getWord("killedMobs") + ": " + manager.getMobsKilled();
        String hpLoose = l.getWord("hpLoose") + ": " + manager.getHpLoose();
        String moneySpend = l.getWord("energySpend") + ": " + manager.getEnergySpend();


        setBackground(l.getSkin().getDrawable("info-panel"));

        Label mobsKilledLabel = new Label(mobsKilled, l.getTimerSkin());
        Label hpLooseLabel = new Label(hpLoose, l.getTimerSkin());
        Label mobeySpendLabel = new Label(moneySpend, l.getTimerSkin());

        add(mobsKilledLabel).align(Align.left).row();
        add(hpLooseLabel).align(Align.left).row();
        add(mobeySpendLabel).align(Align.left).row();



    }


}
