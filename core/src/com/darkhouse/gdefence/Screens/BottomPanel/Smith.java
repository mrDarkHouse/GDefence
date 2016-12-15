package com.darkhouse.gdefence.Screens.BottomPanel;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.InventorySystem.inventory.*;
import com.darkhouse.gdefence.InventorySystem.inventory.Source.GemGradeSource;
import com.darkhouse.gdefence.InventorySystem.inventory.Target.GemGradeTarget;
import com.darkhouse.gdefence.InventorySystem.inventory.Target.SlotTarget;
import com.darkhouse.gdefence.InventorySystem.inventory.Tooltip.GradableTooltip;
import com.darkhouse.gdefence.Model.Panels.GemGradePanel;
import com.darkhouse.gdefence.Model.Panels.GemPanel;
import com.darkhouse.gdefence.Model.Panels.GoldPanel;
import com.darkhouse.gdefence.Screens.AbstractCampainScreen;
import com.darkhouse.gdefence.User;

public class Smith extends AbstractCampainScreen{
    private ImageButton upHealth;
    private ImageButton upEnergy;
    private InventoryActor inventoryActor;

    //private Array<SlotListener> slotListeners = new Array<SlotListener>();

    public Smith() {
        super("Smith");
//        init();
    }

    @Override
    public void show() {
        super.show();
//        inventoryActor.setTowerInventory(User.getTowerInventory());//update
//        inventoryActor.remove();
////        inventoryActor = null;
//        inventoryActor = new InventoryActor(User.getTowerInventory(), new DragAndDrop(), GDefence.getInstance().assetLoader.get("skins/uiskin.json", Skin.class)){
//            @Override
//            protected void setDefaults() {
//                setPosition(100, 150);
//                defaults().space(8);
//                defaults().size(60, 60);
//                row().fill().expandX();
//                setRowNumber(5);
//                setRows(3);
//            }
//        };
//        stage.addActor(inventoryActor);
//        inventoryActor.init();



//        init();
    }
    public void init(){
        final User user = GDefence.getInstance().user;

        upHealth = new ImageButton(GDefence.getInstance().assetLoader.generateImageButtonSkin(GDefence.getInstance().assetLoader.get("maxHpGrade.png", Texture.class)));
        upEnergy = new ImageButton(GDefence.getInstance().assetLoader.generateImageButtonSkin(GDefence.getInstance().assetLoader.get("maxEnergyGrade.png", Texture.class)));

        upHealth.setSize(120, 80);
        upHealth.setPosition(40, Gdx.graphics.getHeight() - upHealth.getHeight() - super.topPadSize - 30);
        upHealth.addListener(new TooltipListener(new GradableTooltip(getStage(), user.maxHealth, GDefence.getInstance().assetLoader.get("skins/uiskin.json", Skin.class)), true));
        upHealth.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if(user.deleteGold(user.maxHealth.getNextCost())) user.maxHealth.up();
                notifyListeners();
                return true;
            }
        });



        upEnergy.setSize(120, 80);
        upEnergy.setPosition(40, upHealth.getY() - upHealth.getHeight() - 20);
        upEnergy.addListener(new TooltipListener(new GradableTooltip(getStage(), user.maxEnegry, GDefence.getInstance().assetLoader.get("skins/uiskin.json", Skin.class)), true));
        upEnergy.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if(user.deleteGold(user.maxEnegry.getNextCost())) user.maxEnegry.up();
                notifyListeners();
                return true;
            }
        });
        inventoryActor = new InventoryActor(User.getTowerInventory(), new DragAndDrop(), GDefence.getInstance().assetLoader.get("skins/uiskin.json", Skin.class)){
            @Override
            protected void setDefaults() {
                setPosition(100, 150);
                defaults().space(8);
                defaults().size(60, 60);
                row().fill().expandX();
                setRowNumber(5);
                setRows(3);
            }
        };
        GemGradePanel pn = new GemGradePanel(new DragAndDrop(), GDefence.getInstance().assetLoader.get("skins/uiskin.json", Skin.class)){
            @Override
            protected void setDefaults() {
                super.setDefaults();
                setPosition(600, 150);
            }
        };
        inventoryActor.getDragAndDrop().addTarget(new GemGradeTarget(pn.getGradeTowerSlot()));
        pn.getDragAndDrop().addSource(new GemGradeSource(pn.getGradeTowerSlot()));
        for (SlotActor a:inventoryActor.getActorArray()) {
            pn.getDragAndDrop().addTarget(new SlotTarget(a));
        }


        stage.addActor(inventoryActor);
        inventoryActor.init();
        stage.addActor(pn);
        pn.addTooltips();
        stage.addActor(upHealth);
        stage.addActor(upEnergy);
        stage.addActor(new GoldPanel(1000, 500, 160, 100));
        stage.addActor(new GemPanel(1000, 300, 270, 160));
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
