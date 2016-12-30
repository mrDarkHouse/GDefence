package com.darkhouse.gdefence.Model.Panels;


import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;
import com.darkhouse.gdefence.InventorySystem.inventory.Slot;
import com.darkhouse.gdefence.InventorySystem.inventory.SlotActor;
import com.darkhouse.gdefence.InventorySystem.inventory.SlotListener;
import com.darkhouse.gdefence.InventorySystem.inventory.Source.SlotSource;
import com.darkhouse.gdefence.Objects.Recipe;
import com.darkhouse.gdefence.Objects.SpellObject;
import com.darkhouse.gdefence.Objects.TowerObject;

public class TowerCraftPanel extends Window{
    private DragAndDrop dragAndDrop;

    public DragAndDrop getDragAndDrop() {
        return dragAndDrop;
    }

    private Skin skin;
    private boolean hasRecipe = false;
    private SlotActor recipeSlot;//<Recipe>
    private Array<SlotActor> componentSlots;//<TowerObject>
    private SlotActor resultSlot;

    public SlotActor getRecipeSlot() {
        return recipeSlot;
    }

    public Array<SlotActor> getComponentSlots() {
        return componentSlots;
    }

    public SlotActor getResultSlot() {
        return resultSlot;
    }
    private class setListener implements SlotListener{
        @Override
        public void hasChanged(Slot slot) {
            if(!slot.isEmpty()) {
                if (!hasRecipe && slot.getPrototype() == ItemEnum.Detail.Recipe/*remove when complete <Recipe> slot*/) {//
                    hasRecipe = true;
                    addRecipe();
                }
            }else {
                if(hasRecipe) {
                    hasRecipe = false;
                    removeRecipe();
                }
            }
        }
    }

    public TowerCraftPanel(DragAndDrop dragAndDrop, Skin skin) {
        super("Grade Panel", skin);
        getTitleLabel().setAlignment(Align.center);
        setMovable(false);
        setDefaults();
        setSize(400, 400);

        this.dragAndDrop = dragAndDrop;
        this.skin = skin;
        recipeSlot = new SlotActor(skin, new Slot(null, 0));
        recipeSlot.getSlot().addListener(new setListener());
        resultSlot = new SlotActor(skin, new Slot(null, 0));
        add(recipeSlot).row();
        recipeSlot.setRound(true);
        recipeSlot.setClip(false);
        dragAndDrop.addSource(new SlotSource(recipeSlot));
        componentSlots = new Array<SlotActor>();
    }

    public void addRecipe(/*Recipe r*/){//no call with empty recipeSlot
        int components = ((Recipe) recipeSlot.getSlot().getLast()).getComponents().size;
        for (int i = 0; i < components; i++){
            componentSlots.add(new SlotActor(skin, new Slot(null, 0)));
        }

        for (SlotActor a:componentSlots){
            add(a);
        }
        row();
        add(resultSlot);
    }
    public void removeRecipe(){
        for (SlotActor a:componentSlots){
            removeActor(a);
        }
        componentSlots.clear();
        removeActor(resultSlot);
    }
    public void addComponent(){//updating
        checkResult();
    }
    private void checkResult(){
        Recipe r = ((Recipe) recipeSlot.getSlot().getLast());
        Array<TowerObject> needComponents = r.getComponents();
        Array<TowerObject> currentComponents = new Array<TowerObject>();

        for (SlotActor a:componentSlots){
            if(!a.getSlot().isEmpty()){
                currentComponents.add(((TowerObject) a.getSlot().getLast()));
            }
        }
        int contains = 0;//
        for (TowerObject t:needComponents){
            for (TowerObject at:currentComponents){//
                if(t.equals(at)){
                    contains++;
                }
            }
        }
        if(contains == needComponents.size){
            resultSlot.getSlot().add(Slot.genereateStartObjects(r.getTower(), 1));//create new tower
//            System.out.println("aa");
        }

//
//        if(currentComponents
    }






    protected void setDefaults(){
        setPosition(800, 250);
        defaults().space(8);
        defaults().size(60, 60);
        row().fill().expandX();
    }
}
