package com.darkhouse.gdefence.Screens.BottomPanel;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.InventorySystem.inventory.Tooltip.TooltipListener;
import com.darkhouse.gdefence.User;

public class ResearchButton extends TowerMapObject{

    private class ResearchTooltip extends Window{
        private Skin skin;
        private User.Research research;

        private boolean isLocked;

        public void setLocked(boolean locked) {
            isLocked = locked;
            if(isLocked)setVisible(false);
            else setVisible(true);
        }

        public ResearchTooltip(User.Research research, Skin skin) {
            super("Recipe//", skin);
            this.research = research;
            this.skin = skin;
            init();
            GDefence.getInstance().getArsenal().getStage().addActor(this);


            setVisible(false);
        }

        private void init(){
//            getStage().addActor(this);
            getTitleLabel().setText(research.name());//
            getTitleLabel().setAlignment(Align.center);

            add(new Label(research.getTooltip(), skin)).row();
            pack();
        }



    }


//    private boolean open;
    private User.Research research;
    private Sprite s;
//    private ResearchTooltip tooltip;
    private Window tooltip;

    public ResearchButton(User.Research research) {
        super(GDefence.getInstance().assetLoader.generateResearchButtonSkin(research.getTexturePath()));
        this.research = research;

        tooltip = new ResearchTooltip(research, GDefence.getInstance().assetLoader.get("skins/uiskin.json", Skin.class));

//        tooltip = new ;
//        tooltip.setLocked(true);

        updateType();

        addListener(new TooltipListener(tooltip, true));

    }

    public void updateType(){
//        open = GDefence.getInstance().user.isResearchOpened(research);
//        tooltip.setLocked(!open);

        if(GDefence.getInstance().user.isResearchOpened(research)) s = new Sprite(GDefence.getInstance().assetLoader.getLockedTowerTexture(User.RecipeType.opened));
        else s = new Sprite(GDefence.getInstance().assetLoader.getLockedTowerTexture(User.RecipeType.canOpen));


    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        s.setBounds(getX(), getY(), getWidth(), getHeight());
        if(GDefence.getInstance().user.isResearchOpened(research)) s.draw(batch, 0.2f);
        else s.draw(batch, parentAlpha);
        super.draw(batch, parentAlpha);
    }
}
