package com.darkhouse.gdefence.Screens.BottomPanel;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;
import com.darkhouse.gdefence.InventorySystem.inventory.Tooltip.RecipeTooltip;
import com.darkhouse.gdefence.InventorySystem.inventory.TooltipListener;
import com.darkhouse.gdefence.Objects.Recipe;
import com.darkhouse.gdefence.User;

public class RecipeButton extends ImageButton{
    private User.RecipeType type;
    private ItemEnum.Tower tower;
    private Recipe towerRecipe;
    private RecipeTooltip tooltip;

    private TowerMap owner;

    public void setOwner(TowerMap owner) {
        this.owner = owner;
    }
    //    private Array<RecipeButton> updateButtons;//kostil'

//    public void setUpdateButtons(Array<RecipeButton> updateButtons) {
//        this.updateButtons = updateButtons;
//    }

    public RecipeButton(final ItemEnum.Tower tower) {
        super(GDefence.getInstance().assetLoader.generateImageButtonSkin(tower.getTowerTexture()));
        this.tower = tower;
        towerRecipe = new Recipe(tower);
        tooltip = new RecipeTooltip(towerRecipe, GDefence.getInstance().assetLoader.get("skins/uiskin.json", Skin.class));
        tooltip.setLocked(true);
        updateType();

        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(type != User.RecipeType.locked && tower != ItemEnum.Tower.Basic){
                    if(GDefence.getInstance().user.deleteGold(towerRecipe.getGlobalCost())){
                        GDefence.getInstance().user.buyTowerRecipe(tower);
                        owner.updateTypes();//if owner != null
                    }
                }
                return true;
            }
        });
        addListener(new TooltipListener(tooltip, true));
    }


    public void updateType(){
        type = GDefence.getInstance().user.getOpenType(tower);
        if(type == User.RecipeType.locked || tower == ItemEnum.Tower.Basic){//basic havent recipe
            tooltip.setLocked(true);
        }else {
            tooltip.setLocked(false);//
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {//rework
        Sprite s;
        if(type == User.RecipeType.opened) {
            s = new Sprite(GDefence.getInstance().assetLoader.get("openedTower.png", Texture.class));
            s.setBounds(getX(), getY(), getWidth(), getHeight());
            s.draw(batch, 0.2f);
            super.draw(batch, parentAlpha);
        }else if(type == User.RecipeType.canOpen){
             s = new Sprite(GDefence.getInstance().assetLoader.get("canOpenTower.png", Texture.class));
            s.setBounds(getX(), getY(), getWidth(), getHeight());
            s.draw(batch);
            super.draw(batch, parentAlpha);//
        }else {
            s = new Sprite(GDefence.getInstance().assetLoader.get("lockedTower.png", Texture.class));
            //super.draw(batch, parentAlpha);//batch.draw();//black box
            s.setBounds(getX(), getY(), getWidth(), getHeight());
            s.draw(batch);
        }
    }


}
