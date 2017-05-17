package com.darkhouse.gdefence.Screens.BottomPanel;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.User;

public class ResearchButton extends ImageButton{
    private boolean open;
    private User.Research research;

    private TowerMap owner;

    public ResearchButton(User.Research research) {
        super(GDefence.getInstance().assetLoader.generateResearchButtonSkin(research.getTexturePath()));
        this.research = research;
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(GDefence.getInstance().user.isResearchOpened(research)) {
            super.draw(batch, parentAlpha);
        }
    }
}
