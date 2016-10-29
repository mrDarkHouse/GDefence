package com.darkhouse.gdefence.Screens.BottomPanel;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.InventorySystem.inventory.GradableTooltip;
import com.darkhouse.gdefence.InventorySystem.inventory.SlotListener;
import com.darkhouse.gdefence.InventorySystem.inventory.TooltipListener;
import com.darkhouse.gdefence.Model.Panels.GoldPanel;
import com.darkhouse.gdefence.Screens.AbstractCampainScreen;
import com.darkhouse.gdefence.User;

public class Smith extends AbstractCampainScreen{
    private ImageButton upHealth;
    private ImageButton upEnergy;

    //private Array<SlotListener> slotListeners = new Array<SlotListener>();

    public Smith() {
        super("Smith");
    }

    @Override
    public void show() {
        super.show();

        final User user = GDefence.getInstance().user;

        upHealth = new ImageButton(AssetLoader.generateImageButtonSkin(AssetLoader.maxHpGrade));
        upEnergy = new ImageButton(AssetLoader.generateImageButtonSkin(AssetLoader.maxEnergyGrade));

        upHealth.setSize(120, 80);
        upHealth.setPosition(40, Gdx.graphics.getHeight() - upHealth.getHeight() - super.topPadSize - 30);
        upHealth.addListener(new TooltipListener(new GradableTooltip(user.maxHealth, AssetLoader.cellSkin), true));
        upHealth.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if(user.deleteGold(user.maxHealth.getNextCost())) user.maxHealth.up();
                notifyListeners();
                return true;
            }
        });



        upEnergy.setSize(120, 80);
        upEnergy.setPosition(40, upHealth.getY() - upHealth.getHeight() - 20);
        upEnergy.addListener(new TooltipListener(new GradableTooltip(user.maxEnegry, AssetLoader.cellSkin), true));
        upEnergy.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if(user.deleteGold(user.maxEnegry.getNextCost())) user.maxEnegry.up();
                notifyListeners();
                return true;
            }
        });




        stage.addActor(upHealth);
        stage.addActor(upEnergy);
        stage.addActor(new GoldPanel(1000, 500, 160, 100));
    }

    private void notifyListeners(){
        for (EventListener l:upHealth.getListeners()){
            if(l.getClass() == TooltipListener.class){
                ((GradableTooltip)((TooltipListener)l).getTooltip()).hasChanged();
            }
        }
        for (EventListener l:upEnergy.getListeners()){
            if(l.getClass() == TooltipListener.class){
                ((GradableTooltip)((TooltipListener)l).getTooltip()).hasChanged();
            }
        }
    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}
