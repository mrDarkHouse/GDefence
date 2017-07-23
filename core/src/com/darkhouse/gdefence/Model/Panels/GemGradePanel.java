package com.darkhouse.gdefence.Model.Panels;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.InventorySystem.inventory.*;
import com.darkhouse.gdefence.InventorySystem.inventory.Source.SlotSource;
import com.darkhouse.gdefence.InventorySystem.inventory.Tooltip.AbilityDemoTooltip;
import com.darkhouse.gdefence.InventorySystem.inventory.Tooltip.GemGradeTooltip;
import com.darkhouse.gdefence.InventorySystem.inventory.Tooltip.TooltipListener;
import com.darkhouse.gdefence.Level.Ability.Mob.MobAbility;
import com.darkhouse.gdefence.Level.Ability.Tower.Ability;
import com.darkhouse.gdefence.Objects.GameObject;
import com.darkhouse.gdefence.Objects.Recipe;
import com.darkhouse.gdefence.Objects.SpellObject;
import com.darkhouse.gdefence.Objects.TowerObject;
import com.darkhouse.gdefence.Screens.BottomPanel.GemGradeButton;
import com.darkhouse.gdefence.User;

public class GemGradePanel extends Window{
    private SlotActor gradeTowerSlot;

    public SlotActor getGradeTowerSlot() {
        return gradeTowerSlot;
    }

    public DragAndDrop getDragAndDrop() {
        return dragAndDrop;
    }

    private DragAndDrop dragAndDrop;
    private OverallInventory inventory;
//    private ImageButton[] gemGrades;
    private boolean hasGradedItem;

    private HorizontalGroup[] g;

    private class GradeSlotListener implements SlotListener{
        @Override
        public void hasChanged(AbstractSlot slot) {
            slotChanged(slot);
        }
    }

    public GemGradePanel(DragAndDrop dragAndDrop, OverallInventory inventory, Skin skin) {
        super("Grade Panel", skin);
        getTitleLabel().setAlignment(Align.center);
        setMovable(false);
//        g = new Array<HorizontalGroup>();
        g = new HorizontalGroup[4];
//        g[0] = new HorizontalGroup();
        for (int i = 0; i < g.length; i++){
            g[i] = new HorizontalGroup();
            g[i].space(12);
            g[i].setVisible(false);
            add(g[i]);
            row();
        }
        g[0].setVisible(true);
        setDefaults();


        this.dragAndDrop = dragAndDrop;
        this.inventory = inventory;
        gradeTowerSlot = new SlotActor(skin, new Slot(TowerObject.class));
        gradeTowerSlot.getSlot().addListener(new GradeSlotListener());


        inventory.addTarget(gradeTowerSlot);
        dragAndDrop.addSource(new SlotSource(gradeTowerSlot));
        inventory.addSlotAsTarget(dragAndDrop);

        g[0].addActor(gradeTowerSlot);//.align(Align.topLeft);
//        add(g[0]);
//        debugAll();

        g[0].pack();
        g[1].pack();
        g[2].pack();
        g[3].pack();

        pack();
        setVisible(true);


    }

    public void addTooltips(){//after stage.addActor
//        for (int i = 0; i < gemGrades.length; i++){
//            gemGrades[i].addListener(new TooltipListener(new GemGradeTooltip(getStage(), User.GEM_TYPE.values()[i],
// GDefence.getInstance().assetLoader.get("skins/uiskin.json", Skin.class)), true));
//        }


        gradeTowerSlot.addTooltip(getStage());
    }
    private void notifyListeners(){
        gradeTowerSlot.getSlot().notifyListeners();
        for (int i = 0; i < g.length; i++){
            for (Actor a:g[i].getChildren()){
                for (EventListener t:a.getListeners()){
                    if(t instanceof TooltipListener){
                        ((TooltipListener) t).getTooltip().hasChanged();
                        ((TooltipListener) t).getTooltip().pack();
                    }
                }
                if(a instanceof GemGradeButton){
                    ((GemGradeButton) a).update();
                }
            }
        }



    }
    private void slotChanged(AbstractSlot slot){
        if(!slot.isEmpty()){
            if(!hasGradedItem) {
                hasGradedItem = true;
                if (slot.getPrototype() instanceof ItemEnum.Tower) {
                    addTower(((TowerObject) ((Slot) slot).getLast()));//
                    pack();
                } else if (slot.getPrototype() instanceof ItemEnum.Spell) {

                }
            }
        }else {
            if(hasGradedItem) {
                clearPanel();
                pack();
                hasGradedItem = false;
            }
        }
    }
    private void clearPanel(){
//        getCell(gradeTowerSlot).spaceRight(0);
//        for (Cell c:getCells()){
//            if(c != getCell(gradeTowerSlot)){
////                removeActor(getChildren().peek());
//                c.clearActor().reset();
//            }
//        }

        for (int i = 1; i < g.length; i++){
            g[i].clear();
            g[i].setVisible(false);
//            g[i].remove();
        }
        for (int i = g[0].getChildren().size - 1; i >= 0; i--){
            Actor a = g[0].getChildren().get(i);
            if(a != gradeTowerSlot) g[0].removeActor(a);
        }

    }

    private void addTower(final TowerObject t){
        AssetLoader loader = GDefence.getInstance().assetLoader;

        GemGradeButton[] b = new GemGradeButton[3];

        for (int i = 0; i < b.length; i++) {
            final int finalI = i;
            b[i] = new GemGradeButton(t, User.GEM_TYPE.values()[finalI]);
            b[i].addListener(new TooltipListener(new GemGradeTooltip(getStage(), t, User.GEM_TYPE.values()[i],
                    loader.get("skins/uiskin.json", Skin.class)), true));
            b[i].addListener(new InputListener() {
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    if(t.canGrade() && GDefence.getInstance().user.spendGems(User.GEM_TYPE.values()[finalI], 1)) {
                        t.addGems(User.GEM_TYPE.values()[finalI], 1);
                        notifyListeners();
                    }
                    return true;
                }
            });
            g[0].addActor(b[i]);
        }
        g[0].pack();
        for (Actor a:g[0].getChildren()){
            if(a instanceof GemGradeButton){
                ((GemGradeButton) a).init();
            }
        }

        for (int ab = 1; ab < t.getAbilities().size + 1; ab++){
            final Ability.AbilityPrototype p = t.getAbilities().get(ab - 1);
            GemGradeButton[] a = new GemGradeButton[3];
            g[ab].setVisible(true);
            ImageButton demoAbility = new ImageButton(loader.generateImageButtonSkin(loader.getAbilityIcon(p.getTexturePath()))){
                @Override
                public float getPrefWidth() {
                    return gradeTowerSlot.getWidth();
                }

                @Override
                public float getPrefHeight() {
                    return gradeTowerSlot.getHeight();
                }
            };
//            demoAbility.setSize(gradeTowerSlot.getWidth(), gradeTowerSlot.getHeight());//dont work
            demoAbility.addListener(new TooltipListener(new AbilityDemoTooltip(getStage(), p,
                    loader.get("skins/uiskin.json", Skin.class)), true));
            g[ab].addActor(demoAbility);

            for (int i = 0; i < b.length; i++) {
                final int finalI = i + 3;
                a[i] = new GemGradeButton(p, User.GEM_TYPE.values()[finalI]);

                a[i].addListener(new TooltipListener(new GemGradeTooltip(getStage(), p, User.GEM_TYPE.values()[finalI],
                        loader.get("skins/uiskin.json", Skin.class)), true));

                a[i].addListener(new InputListener() {
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        if(p.canGrade(User.GEM_TYPE.values()[finalI]) && GDefence.getInstance().user.spendGems(User.GEM_TYPE.values()[finalI], 1)) {
                            p.addGem(User.GEM_TYPE.values()[finalI]);
                            notifyListeners();
                        }
                        return true;//
                    }
                });
                g[ab].addActor(a[i]);
            }
            g[ab].pack();
            for (Actor ac:g[ab].getChildren()){
                if(ac instanceof GemGradeButton){
                    ((GemGradeButton) ac).init();
                }
            }
        }
    }

//    private void addGems(User.GEM_TYPE type){
//        GameObject object = gradeTowerSlot.getSlot().getLast();
//        if(object != null){
//            if(object instanceof TowerObject){
//                TowerObject towerObject = (TowerObject) object;
//                if(towerObject.getLevel() > towerObject.getPrimaryGemsNumber()) {
//                    if(GDefence.getInstance().user.spendGems(type, 1)) {
//                        towerObject.addGems(type, 1);
//                    }
//                }else {
//                    tooltipShow();
//                }
//            }
//        }
//    }

//    private void tooltipShow(){
//        //TextTooltip tt = new TextTooltip("Level must be greater then gems number", GDefence.getInstance().getSkin());
//        Dialog d = new Dialog("", GDefence.getInstance().assetLoader.get("uiskin.json", Skin.class)){
//            {
//                text("Level must be greater then gems number").padTop(250);
//                button("Ok", true);
//                //getButtonTable();//defaults().width(200);
//                Array<Cell> cells = getButtonTable().getCells();
//                Cell cell = cells.get(0);
//                cell.width(50).padBottom(360);
//                cell.height(40);
//                //cell.padBottom(Gdx.graphics.getHeight()/2);
//                //cell.center().top();
////
////                cell = cells.get(1);
////                cell.width(50).padBottom(360);
////                cell.height(40);
//                //cell.padBottom(Gdx.graphics.getHeight()/2);
//
//            }
//            @Override
//            protected void result(Object object) {
//                hide();
//            }
//        };
//        d.setBackground(new TextureRegionDrawable(new TextureRegion(GDefence.getInstance().assetLoader.get("MainMenuTranparent.png", Texture.class))));
//        d.getBackground().setMinWidth(Gdx.graphics.getWidth());
//        d.getBackground().setMinHeight(Gdx.graphics.getHeight());
//        d.show(getStage());
//
//    }



    protected void setDefaults(){
//        setPosition(100, 250);
//        setSize(400, 200);
        defaults().space(8);
        defaults().size(60, 60);
//        row().fill().expandX();
    }






}
