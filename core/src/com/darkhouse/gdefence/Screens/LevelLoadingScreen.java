package com.darkhouse.gdefence.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.InventorySystem.inventory.Inventory;

public class LevelLoadingScreen extends AbstractLoadingScreen{

    private int level;
    private Inventory inventory;
    private LevelMap map;
    private Label tipsLabel;
    private Table tipsTable;

    @Override
    public void show() {
        super.show();
        stage.addActor(tipsTable);

        GDefence.getInstance().assetLoader.loadLevelMap();
    }

    public LevelLoadingScreen(int level, Inventory inventory) {
        super();
        this.level = level;
        this.inventory = inventory;
        tipsLabel = new Label(GDefence.getTip(), FontLoader.generateStyle(24, Color.BLACK));
        tipsLabel.setAlignment(Align.center);
        tipsTable = new Table();
        tipsTable.align(Align.center|Align.bottom);
        tipsTable.setWidth(Gdx.graphics.getWidth());
        tipsTable.setPosition(0, 160);
        tipsTable.add(tipsLabel)/*.align(Align.center)*/;
//        tipsTable.debugAll();
    }

    @Override
    protected void onLoad() {
        map = new LevelMap(level, inventory);
//        if (Gdx.input.isTouched()) {
            GDefence.getInstance().switchScreen(map);
//            LevelMap.getLevel().start();//WHAT THE SHIT THIS
//        }
    }
}