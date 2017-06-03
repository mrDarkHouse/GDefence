package com.darkhouse.gdefence.Screens.BottomPanel;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;
import com.darkhouse.gdefence.InventorySystem.inventory.Tooltip.RecipeTooltip;
import com.darkhouse.gdefence.InventorySystem.inventory.Tooltip.TooltipListener;
import com.darkhouse.gdefence.Objects.Recipe;
import com.darkhouse.gdefence.User;

public class RecipeButton extends TowerMapObject{
    private User.RecipeType type;
//    private ItemEnum.Tower tower;
    private Recipe towerRecipe;
    private RecipeTooltip tooltip;

//    private TowerMap owner;

    private Sprite s;// = new Sprite(GDefence.getInstance().assetLoader.get("openedTower.png", Texture.class));


//    public void setOwner(TowerMap owner) {
//        this.owner = owner;
//    }
    //    private Array<RecipeButton> updateButtons;//kostil'

//    public void setUpdateButtons(Array<RecipeButton> updateButtons) {
//        this.updateButtons = updateButtons;
//    }

    public RecipeButton(final ItemEnum.Tower tower) {
        super(GDefence.getInstance().assetLoader.generateImageButtonSkin(tower.getTowerTexture()));
//        this.tower = tower;
        towerRecipe = new Recipe(tower);
        tooltip = new RecipeTooltip(towerRecipe, GDefence.getInstance().assetLoader.get("skins/uiskin.json", Skin.class));
        tooltip.setLocked(true);
//        tooltip.setVisible(false);
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

//    @Override ne optimizirovanno
//    public void act(float delta) {
//        super.act(delta);
//        updateType();
//    }

    public void updateType(){
        type = GDefence.getInstance().user.getOpenType(towerRecipe.getTower());
        if(type == User.RecipeType.locked || towerRecipe.getTower() == ItemEnum.Tower.Basic){//basic havent recipe
            tooltip.setLocked(true);
//            tooltip.setVisible(false);
        }else {
//            tooltip.setVisible(true);
            tooltip.setLocked(false);//
        }
//        s = GDefence.getInstance().assetLoader.getLockedTowerSprite(type);
        s = new Sprite(GDefence.getInstance().assetLoader.getLockedTowerTexture(type));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {//rework
//        Sprite s;
        s.setBounds(getX(), getY(), getWidth(), getHeight());
        if(type == User.RecipeType.opened) {
//            s = new Sprite(GDefence.getInstance().assetLoader.get("openedTower.png", Texture.class));
            s.draw(batch, 0.2f);
            super.draw(batch, parentAlpha);
        }else if(type == User.RecipeType.canOpen){
//            s = new Sprite(GDefence.getInstance().assetLoader.get("canOpenTower.png", Texture.class));
            s.draw(batch);
            super.draw(batch, parentAlpha);//
        }else {
//            s = new Sprite(GDefence.getInstance().assetLoader.get("lockedTower.png", Texture.class));
            //super.draw(batch, parentAlpha);//batch.draw();//black box
            s.draw(batch);
        }
    }


}
