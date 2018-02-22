package com.darkhouse.gdefence.Model.Panels;


import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.InventorySystem.inventory.*;
import com.darkhouse.gdefence.InventorySystem.inventory.Source.SlotSource;
import com.darkhouse.gdefence.InventorySystem.inventory.Target.SlotTarget;
import com.darkhouse.gdefence.Level.Ability.Tower.Ability;
import com.darkhouse.gdefence.Objects.GameObject;
import com.darkhouse.gdefence.Objects.Recipe;
import com.darkhouse.gdefence.Objects.SpellObject;
import com.darkhouse.gdefence.Objects.TowerObject;
import com.darkhouse.gdefence.User;

public class TowerCraftPanel extends Window{
    private DragAndDrop dragAndDrop;

    public DragAndDrop getDragAndDrop() {
        return dragAndDrop;
    }

    private Skin skin;
    private boolean hasRecipe = false;
    private SlotActor recipeSlot;//<Recipe>
    private RecipeListener recipeListener;
    private Array<SlotActor> componentSlots;//<TowerObject>
    private Array<ComponentListener> componentListeners;
    private SlotActor resultSlot;
//    private ResultListener resultListener;

    private OverallInventory sourceTargetInventory;

    public void setSourceTargetInventory(OverallInventory sourceTargetInventory) {
        this.sourceTargetInventory = sourceTargetInventory;
    }

    public SlotActor getRecipeSlot() {
        return recipeSlot;
    }

    public Array<SlotActor> getComponentSlots() {
        return componentSlots;
    }

    public SlotActor getResultSlot() {
        return resultSlot;
    }
    private class RecipeListener implements SlotListener{
        @Override
        public void hasChanged(AbstractSlot slot) {
            recipeChanged(slot);
        }
    }
    private class ComponentListener implements SlotListener{
        @Override
        public void hasChanged(AbstractSlot slot) {
            addComponent();
        }
    }
//    private class ResultListener implements SlotListener{
//        @Override
//        public void hasChanged(Slot slot) {
//            if(slot.isEmpty()){//&& dragAndDrop has slotActor
//                removeResult();
//            }
//        }
//    }

    public TowerCraftPanel(DragAndDrop dragAndDrop, OverallInventory overallInventory, Skin skin) {
        super(GDefence.getInstance().assetLoader.getWord("craft_panel"), skin, "description");
        getTitleLabel().setAlignment(Align.center);
        setMovable(false);
        setDefaults();
        setResizable(false);


        this.dragAndDrop = dragAndDrop;
        this.skin = skin;
        setSourceTargetInventory(overallInventory);
        recipeSlot = new SlotActor(skin, new Slot(Recipe.class/*, null, 0*/));
        recipeListener = new RecipeListener();
        recipeSlot.getSlot().addListener(recipeListener);
//        resultListener = new ResultListener();
        resultSlot = new SlotActor(skin, new Slot(TowerObject.class/*, null, 0*/));

        add(recipeSlot).align(Align.center);
        row();
        add();//3 max component size
        add().size(0, 0);
        add().size(0, 0);
        row();
        add();

        pack();//delete this to do resizable
        setSize(180, 250);

//        recipeSlot.setRound(true);
//        recipeSlot.setClip(false);
        sourceTargetInventory.addTarget(recipeSlot);
        dragAndDrop.addSource(new SlotSource(recipeSlot));
        sourceTargetInventory.addSlotAsTarget(dragAndDrop);
        componentSlots = new Array<SlotActor>();
        componentListeners = new Array<ComponentListener>();

    }
    public void init(){
        recipeSlot.addTooltip(getStage());
    }

    public void addRecipe(/*Recipe r*/){//no call with empty recipeSlot
        int components = ((Recipe) recipeSlot.getSlot().getLast()).getComponents().size;
//        recipeSlot.hasChanged();

        for (int i = 0; i < components; i++){
            componentSlots.add(new SlotActor(skin, new Slot(TowerObject.class/*, null, 0*/)));
        }

        for (int i = 0; i < componentSlots.size; i++){
            componentListeners.add(new ComponentListener());
            componentSlots.get(i).getSlot().addListener(componentListeners.get(i));
            componentSlots.get(i).addTooltip(getStage());

            getCells().get(i + 1).size(defaults().getMinWidthValue(), defaults().getMinHeightValue());//60 60
            getCells().get(i + 1).setActor(componentSlots.get(i));
            getCells().get(0).colspan(componentSlots.size);
            getCells().peek().colspan(componentSlots.size);
//            pack();

            dragAndDrop.addSource(new SlotSource(componentSlots.get(i)));
            sourceTargetInventory.addTarget(componentSlots.get(i));
        }
        row();
//        resultSlot.getSlot().addListener(resultListener);
        resultSlot.addTooltip(getStage());
        getCells().peek().setActor(resultSlot);
//        dragAndDrop.addSource(new SlotSource(resultSlot));
        resultSlot.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(!resultSlot.getSlot().isEmpty()) {
                    removeResult();
                }
                return true;
            }
        });

//        pack();
    }
    public void removeRecipe(){
//        for (SlotActor a:componentSlots){//#iterator cannot be used nested
//            User.getTowerInventory().store(a.getSlot().getAll());//saving items
//            getCells().get(2).size(0);
//            a.remove();
//        }
        for (int i = 0; i < componentSlots.size; i++){
            User.getTowerInventory().store(componentSlots.get(i).getSlot().getAll());//saving items
            getCells().get(i + 1).size(0, 60);
            componentSlots.get(i).remove();
        }
        componentListeners.clear();
        if(!resultSlot.getSlot().isEmpty()) {
//            resultSlot.getSlot().removeListener(resultListener);//kostil'
            resultSlot.getSlot().takeAll();
//            resultSlot.getSlot().addListener(resultListener);//
        }

        componentSlots.clear();
//        resultSlot.getSlot().removeListener(resultListener);
        resultSlot.remove();
    }
    private void recipeChanged(AbstractSlot slot){
        if(!slot.isEmpty()) {
            if (!hasRecipe /*&& slot.getPrototype() == ItemEnum.Detail.Recipe*//*remove when complete <Recipe> slot*/) {//
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

    public void addComponent(){//updating
        checkResult();
    }
    private void checkResult(){
//        System.out.println(recipeSlot.getSlot().getLast());
        Recipe r = ((Recipe) recipeSlot.getSlot().getLast());
        if(r == null) {
//            System.out.println("Null");
            return;
        }
        Array<TowerObject> needComponents = r.getComponents();//nullPointer
        Array<TowerObject> currentComponents = new Array<TowerObject>();


        for (SlotActor a:componentSlots){
            if(!a.getSlot().isEmpty()){
                currentComponents.add(((TowerObject) a.getSlot().getLast()));
            }
        }
        int contains = 0;//
        for (TowerObject t:needComponents){
            for (TowerObject at:currentComponents){//
                if(t.equalsOrHigher(at)){
                    contains++;
                }
            }
        }
        if(contains == needComponents.size){
            Array<Ability.AbilityPrototype> spellMatch = new Array<Ability.AbilityPrototype>();
            for (Ability.AbilityPrototype ap:r.getTower().getAbilities()) {
                for (TowerObject at : currentComponents) {
                    for (Ability.AbilityPrototype so : at.getAbilities()) {
                        for (Class<? extends Ability.AbilityPrototype> a:ap.getAbilitiesToSaveOnCraft()){
                            if(a == so.getClass()){
                                spellMatch.add(so);
                            }
                        }
//                        if (ap.getClass() == so.getClass()){
//                            spellMatch.add(so);
//                        }
                    }

                }
            }
            Array<? extends GameObject> t = TowerObject.generateStartObjects(r.getTower(), 1);
            Array<Ability.AbilityPrototype> att = ((TowerObject) t.get(0)).getAbilities();
            for (Ability.AbilityPrototype at:att){
                for (Ability.AbilityPrototype ap:spellMatch){
                    if(at.getClass() == ap.getClass()) {
                        at.gemCur(ap.getGemCur());
                    }
                }
            }


            resultSlot.getSlot().add(t);//create new tower

        }else{
            if(!resultSlot.getSlot().isEmpty()){
//                resultSlot.getSlot().removeListener(resultListener);//kostil'
                resultSlot.getSlot().takeAll();
//                resultSlot.getSlot().addListener(resultListener);//
            }
        }

//
//        if(currentComponents
    }
    private void removeResult(){//cant do item in seconds time
//        componentSlots.clear();
//        recipeSlot.getSlot().takeAll();
//        System.out.println(recipeSlot.getSlot().getAll());
//        System.out.println(recipeSlot.getStage());

        for (int i = 0; i < componentSlots.size; i++){
            componentSlots.get(i).getSlot().removeListener(componentListeners.get(i));
            /*if(!a.getSlot().isEmpty())*/ componentSlots.get(i).getSlot().take(1);
            //take(1) allows to contains and create a lot components
            componentSlots.get(i).getSlot().addListener(componentListeners.get(i));
        }
//        recipeSlot.getSlot().removeListener(recipeListener);//recipe always not null
//        recipeSlot.getSlot().take(1);
//        recipeSlot.getSlot().addListener(recipeListener);



        GDefence.getInstance().user.craftTower(/*(ItemEnum.Tower)*/ (TowerObject) resultSlot.getSlot().getLast()/*.getPrototype()*/);//
//        TowerObject.generateStartObjects(r.getTower(), 1);
//        User.getTowerInventory().store(resultSlot.getSlot().take(1));


        recipeSlot.getSlot().take(1);
        checkResult();
//        if(!recipeSlot.getSlot().isEmpty()) {
//            for (int i = 0; i < componentSlots.size; i++) {
//                if (!componentSlots.get(i).getSlot().isEmpty()) return;
//            }
//        }




//        if(/*recipeSlot.getSlot().isEmpty() && */resultSlot.getSlot().isEmpty()){//
//            componentListeners.clear();
//            for (int i = 0; i < componentSlots.size; i++){
//                componentSlots.get(i).remove();
//            }
//            componentSlots.clear();
////            resultSlot.getSlot().removeListener(resultListener);
////            getCells().peek().setActor(null);
//            resultSlot.remove();
//        }



    }






    protected void setDefaults(){
        setPosition(1000, 330);
        defaults().space(8);
        defaults().size(60, 60);
//        row().fill().expandX();
    }
}
