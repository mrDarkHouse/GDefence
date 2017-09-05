package com.darkhouse.gdefence.Model;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.InventorySystem.inventory.Tooltip.LevelToolip;
import com.darkhouse.gdefence.InventorySystem.inventory.Tooltip.TooltipListener;
import com.darkhouse.gdefence.Screens.LevelPreparationScreen;

public class LevelButton extends TextButton {
    private int number;

    public int getNumber() {
        return number;
    }

//    public boolean isLocked = false;
    public boolean isLocked(){
        return !GDefence.getInstance().user.getLevelAvailable(number);
    }

    public LevelButton(int number) {
        super("" + number, GDefence.getInstance().assetLoader.getCampainLevelStyle());
        this.number = number;
        load();
    }

    private void load(){
        LevelToolip tooltip = new LevelToolip(this, GDefence.getInstance().assetLoader.get("skins/uiskin.json", Skin.class));
        tooltip.setTouchable(Touchable.disabled);
        //CampainMap.getStage().addActor(tooltip);
//        GDefence.getInstance().getCampainMap().getStage().addActor(tooltip);
        addListener(new TooltipListener(tooltip, true));


        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (!isLocked()) {
                    GDefence.getInstance().getLevelPreparationScreen().setLevel(number);
                    GDefence.getInstance().switchScreen(GDefence.getInstance().getLevelPreparationScreen());
                }
                return true;
            }
        });



    }






//    public void lock(){
//        isLocked = true;
        //getStyle().up = new TextureRegionDrawable(new TextureRegion(AssetLoader.levelLock));
        //getStyle().over = new TextureRegionDrawable(new TextureRegion(AssetLoader.levelLock));
        //getStyle().down = new TextureRegionDrawable(new TextureRegion(AssetLoader.levelLock));
        //getStyle().over = new TextureRegionDrawable(AssetLoader.getLevelLockTexture(number));
//    }
//    public void unLock(){
//        isLocked = false;
//    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if(isLocked()){
            Texture t = GDefence.getInstance().assetLoader.get("levelLock.png", Texture.class);
            t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            batch.draw(t, getX(), getY(), getWidth(), getHeight());
        }


    }
}
