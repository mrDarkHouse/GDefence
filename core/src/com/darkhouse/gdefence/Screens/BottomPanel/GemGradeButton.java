package com.darkhouse.gdefence.Screens.BottomPanel;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.InventorySystem.inventory.Tooltip.GemGradable;
import com.darkhouse.gdefence.Level.Ability.Tower.Ability;
import com.darkhouse.gdefence.Objects.TowerObject;
import com.darkhouse.gdefence.User;

public class GemGradeButton extends ImageButton{
//    private Ability.AbilityPrototype p;
    private User.GEM_TYPE t;
    private GemGradable owner;
    private Sprite transparent;
    private boolean locked;
//    private

    public GemGradeButton(GemGradable p, User.GEM_TYPE t) {
        super(GDefence.getInstance().assetLoader.generateImageButtonSkin(GDefence.getInstance().assetLoader.getGemTexure(t)));
        this.owner = p;
        this.t = t;
        update();
        transparent = new Sprite(GDefence.getInstance().assetLoader.get("Gems/transparentGem.png", Texture.class));
    }

    public void init(){
        transparent.setBounds(getX(), getY(), getWidth(), getHeight());
    }

    public void update(){
        if(owner instanceof Ability.AbilityPrototype){
            locked = !((Ability.AbilityPrototype) owner).canGrade(t);
        }else if(owner instanceof TowerObject){
            locked = !((TowerObject) owner).canGrade();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        init();
        if(locked) transparent.draw(batch);
    }
}
