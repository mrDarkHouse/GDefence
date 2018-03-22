package com.darkhouse.gdefence.Model.Panels;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.InventorySystem.inventory.*;
import com.darkhouse.gdefence.InventorySystem.inventory.Source.SlotSource;
import com.darkhouse.gdefence.InventorySystem.inventory.Tooltip.AbstractTooltip;
import com.darkhouse.gdefence.InventorySystem.inventory.Tooltip.GemGradable;
import com.darkhouse.gdefence.InventorySystem.inventory.Tooltip.SlotTooltip;
import com.darkhouse.gdefence.InventorySystem.inventory.Tooltip.TooltipListener;
import com.darkhouse.gdefence.Level.Ability.Tower.Ability;
import com.darkhouse.gdefence.Objects.SpellObject;
import com.darkhouse.gdefence.Objects.TowerObject;
import com.darkhouse.gdefence.User;


public class GemClearPanel extends Window{

//    private class ClearButton extends ImageButton{
//
//        public ClearButton(ImageButtonStyle style) {
//            super(style);
//
//
//
//        }
//    }

    private class ClearTooltip extends AbstractTooltip{
        String info = "";
        String current = "";
        Label infoL;
        Label currL;

        public ClearTooltip(Skin skin) {
            super("", skin);
            AssetLoader l = GDefence.getInstance().assetLoader;
            getTitleLabel().setText(l.getWord("clear"));

            infoL = new Label("", skin, "description");
            currL = new Label("", skin, "description");

            infoL.setAlignment(Align.center);
            currL.setAlignment(Align.center);

            add(infoL).row();
            add(currL);

            setVisible(false);
//            init();
        }
        public void init(Stage stage){
            stage.addActor(this);
        }

        @Override
        public void hasChanged() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            info = "";
            current = "";
            cost = 0;
            gems = new int[6];
            info = l.getWord("clearPanelInfo1") + System.getProperty("line.separator") +
                   l.getWord("clearPanelInfo2") + System.getProperty("line.separator") +
                   l.getWord("clearPanelInfo3");
            if(itemSlot.getSlot().getLast() instanceof TowerObject){
                TowerObject o = ((TowerObject) itemSlot.getSlot().getLast());
                current += System.getProperty("line.separator");
                current += o.getName();
                for (int i = 0; i < o.getSimplyGemStat().length; i++){

//                    current += User.GEM_TYPE.values()[i].getName() + ": " + o.getSimplyGemStat()[i] + " ";
                    cost += o.getSimplyGemStat()[i] * 20;
                    gems[i] += o.getSimplyGemStat()[i];
                }
                for (Ability.AbilityPrototype ap:o.getAbilities()){
                    current += System.getProperty("line.separator");
                    current += ap.getName() + " " + ap.getGemStat();// + " - ";
                    for (int i = 0; i < ap.getGemCur().length; i++){
//                        current += User.GEM_TYPE.values()[i + 3].getName() + ": " + ap.getGemCur()[i] + " ";
                        if(i == 2) cost += ap.getGemCur()[i] * 200;
                        else cost += ap.getGemCur()[i] * 50;
                        gems[i + 3] += ap.getGemCur()[i];
                    }
                }
            }else if(itemSlot.getSlot().getLast() instanceof SpellObject){
                SpellObject o = ((SpellObject) itemSlot.getSlot().getLast());
                current += System.getProperty("line.separator");
                current += o.getName();
                for (int i = 0; i < o.getGemCur().length; i++){
//                    current += User.GEM_TYPE.values()[i + 3].getName() + ": " + o.getGemCur()[i] + " ";
                    if(i == 2) cost += o.getGemCur()[i] * 200;
                    else cost += o.getGemCur()[i] * 50;
                    gems[i + 3] += o.getGemCur()[i];
                }

            }
            current += System.getProperty("line.separator");
            current += l.getWord("total") + ": " + cost + " " + l.getWord("goldL");

            infoL.setText(info);
            currL.setText(current);

            pack();
        }
    }


    private SlotActor itemSlot;
    private DragAndDrop dragAndDrop;
    private OverallInventory inventory;

    public SlotActor getItemSlot() {
        return itemSlot;
    }

    private boolean hasItem;
    private ImageButton clearButton;
    private TooltipListener tooltipListener;
    private ClearButtonListener clearButtonListener;
    private int cost;
    private int[] gems;

    public void addSource(SlotSource s){
        dragAndDrop.addSource(s);
    }
    public void addTarget(DragAndDrop.Target t){
        dragAndDrop.addTarget(t);
    }


    public GemClearPanel(DragAndDrop dragAndDrop, OverallInventory inventory, Skin skin) {
        super(GDefence.getInstance().assetLoader.getWord("clear_panel"), skin, "description");
        getTitleLabel().setAlignment(Align.center);
        setMovable(false);
        this.dragAndDrop = dragAndDrop;
        this.inventory = inventory;

        defaults().space(5);
        defaults().size(60);

        itemSlot = new SlotActor(skin, new Slot(1, TowerObject.class, SpellObject.class));
        itemSlot.getSlot().addListener(new ClearSlotListener());

        inventory.addTarget(itemSlot);
        dragAndDrop.addSource(new SlotSource(itemSlot));
        inventory.addSlotAsTarget(dragAndDrop);

        ImageButton.ImageButtonStyle st = new ImageButton.ImageButtonStyle();
        st.up = new TextureRegionDrawable(new TextureRegion(GDefence.getInstance().assetLoader.get("gemClear.png", Texture.class)));
        clearButton = new ImageButton(st);

        clearButton.setSize(60, 60);
        clearButton.setVisible(false);


        tooltipListener = new TooltipListener(new ClearTooltip(getSkin()), true);
        clearButton.addListener(tooltipListener);
        clearButtonListener = new ClearButtonListener();

        add(itemSlot);
        add(clearButton);

        pack();
    }

    private class ClearSlotListener implements SlotListener {
        @Override
        public void hasChanged(AbstractSlot slot) {
            slotChanged(slot);
        }
    }
    private class ClearButtonListener extends ClickListener {
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            clearGems();
            return true;
        }
    }

    private void slotChanged(AbstractSlot slot){
        if(!slot.isEmpty()){
            if(!hasItem) {
                hasItem = true;
                addItem();
//                if (slot.getPrototype() instanceof ItemEnum.Tower) {
//                    addTower(((TowerObject) ((Slot) slot).getLast()));
//                    pack();
//                } else if (slot.getPrototype() instanceof ItemEnum.Spell) {
//                    addSpell(((SpellObject) ((Slot) slot).getLast()));
//                    pack();
//                }
            }
        }else {
            if(hasItem) {
                clearPanel();
                pack();
                hasItem = false;
            }
        }
    }

    private void clearGems(){
//        if(itemSlot.getSlot().getLast() instanceof  GemGradable) {
            GemGradable o = itemSlot.getSlot().getLast();
            if (GDefence.getInstance().user.deleteGold(cost)) {
                o.flushGems();
                for (int i = 0; i < gems.length; i++) {
                    GDefence.getInstance().user.addGems(User.GEM_TYPE.values()[i], gems[i]);
                }
                itemSlot.getSlot().notifyListeners();
//            for (EventListener e:clearButton.getListeners()){
//                if(e.equals(tooltipListener)){
                tooltipListener.getTooltip().hasChanged();
//                }
//            }
            }
//        }/*else if(itemSlot.getSlot().getLast() instanceof SpellObject){
//            SpellObject o = ((SpellObject) itemSlot.getSlot().getLast());
//            if (GDefence.getInstance().user.deleteGold(cost)) {
//                o.flushGems();
//                for (int i = 0; i < gems.length; i++) {
//                    GDefence.getInstance().user.addGems(User.GEM_TYPE.values()[i], gems[i]);
//                }
//                itemSlot.getSlot().notifyListeners();
//                tooltipListener.getTooltip().hasChanged();
//            }
//        }*/

    }

    private void addItem(){
        itemSlot.addListener(new TooltipListener(new SlotTooltip(getStage(), itemSlot.getSlot(), getSkin()), true));

        for (EventListener e:clearButton.getListeners()){
            if(e.equals(tooltipListener)){
                tooltipListener.getTooltip().hasChanged();
            }
        }

//        clearButton.addListener(tooltipListener);
        clearButton.addListener(clearButtonListener);
        clearButton.setVisible(true);
    }
    private void clearPanel(){
//        clearButton.removeListener(tooltipListener);
        clearButton.removeListener(clearButtonListener);
        clearButton.setVisible(false);
        cost = 0;//
    }
    public void initListeners(){
        tooltipListener.getTooltip().init(getStage());
//        for (EventListener t:clearButton.getListeners()){
//            if(t instanceof TooltipListener){
//                ((TooltipListener) t).getTooltip().hasChanged();
//            }
//        }
    }

}

