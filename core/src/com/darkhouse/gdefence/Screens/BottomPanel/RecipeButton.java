package com.darkhouse.gdefence.Screens.BottomPanel;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;
import com.darkhouse.gdefence.InventorySystem.inventory.Tooltip.RecipeTooltip;
import com.darkhouse.gdefence.InventorySystem.inventory.TooltipListener;
import com.darkhouse.gdefence.User;

public class RecipeButton extends ImageButton{
    private User.RecipeType type;
    private ItemEnum.Tower tower;
    private Recipe towerRecipe;
    private RecipeTooltip tooltip;



    public RecipeButton(final ItemEnum.Tower tower) {
        super(GDefence.getInstance().assetLoader.getTowerCellSkin(tower));
        this.tower = tower;
        towerRecipe = new Recipe(tower);
        tooltip = new RecipeTooltip(towerRecipe, GDefence.getInstance().assetLoader.get("skins/uiskin.json", Skin.class));
        tooltip.setLocked(true);
        updateType();

        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(type == User.RecipeType.canOpen){
                    if(GDefence.getInstance().user.deleteGold(500)){
                        GDefence.getInstance().user.buyTowerRecipe(tower);
                    }
                }
                return true;
            }
        });
        addListener(new TooltipListener(tooltip, true));
    }

    private void updateType(){
        type = GDefence.getInstance().user.isOpenedTower(tower);
        if(type == User.RecipeType.canOpen){
            tooltip.setLocked(false);
        }else {
            tooltip.setLocked(true);//
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(type == User.RecipeType.opened) {
            super.draw(batch, parentAlpha);
        }else if(type == User.RecipeType.canOpen){
            super.draw(batch, parentAlpha);//
        }else {
            super.draw(batch, parentAlpha);//batch.draw();//black box
        }
    }


}
