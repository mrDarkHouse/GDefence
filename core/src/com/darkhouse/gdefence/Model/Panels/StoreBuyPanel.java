package com.darkhouse.gdefence.Model.Panels;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Align;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.InventorySystem.inventory.*;
import com.darkhouse.gdefence.InventorySystem.inventory.Source.BuySlotSource;
import com.darkhouse.gdefence.InventorySystem.inventory.Target.SellTarget;
import com.darkhouse.gdefence.InventorySystem.inventory.Target.SlotTarget;
import com.darkhouse.gdefence.InventorySystem.inventory.Tooltip.SlotTooltip;
import com.darkhouse.gdefence.InventorySystem.inventory.Tooltip.TooltipListener;
import com.darkhouse.gdefence.Objects.TowerObject;
import com.darkhouse.gdefence.Screens.BottomPanel.SellButton;

public class StoreBuyPanel extends Window /*InventoryActor*/{

    public static class StoreBuyInventory extends Inventory{

        public StoreBuyInventory() {
            super(TowerObject.class, 2, 1);
        }

    }

//    private Label[] cost;
//    private SellButton sellButton;

    private Table table;
    private Table container;
    private VerticalGroup items;
    private VerticalGroup costs;

    private Inventory fastInventory;

    private DragAndDrop dragAndDrop;



    public StoreBuyPanel(/*Inventory inventory*/DragAndDrop dragAndDrop) {
        super(GDefence.getInstance().assetLoader.getWord("store"), GDefence.getInstance().assetLoader.getSkin(), "description");
        this.dragAndDrop = dragAndDrop;
        getTitleLabel().setAlignment(Align.center);
        setMovable(false);
        setResizable(false);
//        setFillParent(true);
//        setHeight(500);



//        init();



    }

//    @Override
//    public float getPrefWidth() {
//        return 200;
//    }
//
//    @Override
//    public float getPrefHeight() {
//        return 400;
//    }

    public void init() {

//        row().fill().expandX();

//        super(inventory, new DragAndDrop(), GDefence.getInstance().assetLoader.get("skins/uiskin.json", Skin.class));
//        getTitleLabel().setText("Store");

        Skin skin = GDefence.getInstance().assetLoader.getSkin();

//        List itemsList = new List();
        items = new VerticalGroup();
//        items.setFillParent(true);
        costs = new VerticalGroup();
        table = new Table();
//        container = new Table();
//        container.setFillParent(true);

//        table.pad(15);
        table.padRight(50);
//        table.padBottom(60);
//        table.defaults().space(10);

        items.space(8);
        costs.space(8);
//        table.setClip(true);

//        setWidth(500f);
//        setHeight(500f);
//        setFillParent(true);
//        table.setFillParent(true);

        table.add(items);//.space(10);
        table.add(costs);//.space(10);//.spaceRight(30);


//        store(ItemEnum.Tower.Basic);
//        store(ItemEnum.Tower.Rock);
//        store(ItemEnum.Tower.Arrow);
//        store(ItemEnum.Tower.Basic);
//        store(ItemEnum.Tower.Rock);
//        store(ItemEnum.Tower.Arrow);
//        store(ItemEnum.Tower.Range);
//        table.setPosition(0, 0);



        ScrollPane scroll = new ScrollPane(table, skin)/*{
//            @Override
//            public float getPrefWidth() {
//                return 25;
//            }
//
//            @Override
//            public float getMaxWidth() {
//                return 25;
//            }
          }*/;
//        scroll.setWidth(25);
        scroll.setScrollingDisabled(true, false);
//        scroll.setForceScroll(false, true);
//        scroll.setScrollX(0);
//        scroll.setOverscroll(false, true);
//        scroll.setScrollY(250);

//        scroll.layout();
//        scroll.layout();
//
//        scroll.pack();



//        items.layout();
//        items.invalidate();
//        items.pack();
//        costs.layout();
//        costs.pack();
//        scroll.pack();


//        table.pad(30f);


//        add(table);


//        add(container);

//        container.add(table);
//        scroll.setScrollY(500);
//        scroll.setX(1000);
//        scroll.setY(200);
//        scroll.setHeight(300 - getPadTop());
//        addActor(scroll);
        add(scroll);

//        container.add(scroll);
//        container.row();
//        container.pack();
//
//        add(container);
//        getStage().addActor(scroll);//.maxWidth(30f).fill();/*.expandY();*///.fill();
//        scroll.toFront();


        setSize(500, 300);


        table.pack();
        pack();





//        scroll.setHeight(table.getHeight());
//
//        setWidth(400f);
//        scroll.debugAll();

//        items.setSize(items.getPrefWidth(), items.getPrefHeight());
//        costs.setSize(costs.getPrefWidth(), costs.getPrefHeight());
//        table.setSize(items.getWidth() + costs.getWidth(), items.getHeight() + costs.getHeight());




//        System.out.println(table.getWidth() + " " + table.getHeight());
//        System.out.println(table.getPrefWidth() + " " + table.getPrefHeight());
//        System.out.println(costs.getWidth() + " " + costs.getHeight());
//        System.out.println(costs.getPrefWidth() + " " + costs.getPrefHeight());
//        System.out.println(items.getWidth() + " " + items.getHeight());
//        System.out.println(items.getPrefWidth() + " " + items.getPrefHeight());
//        System.out.println(getWidth() + " " + getHeight());
//        System.out.println(getPrefWidth() + " " + getPrefHeight());

//        row();

        SellButton sellButton = new SellButton();
        dragAndDrop.addTarget(new SellTarget(sellButton));
//
        row();
        add(sellButton).colspan(2).growX().expandX().row();





//        debugAll();
//        container.debugAll();


//        items.debug();
//        costs.debug();
//        table.debug();



    }

//    @Override
//    public float getPrefHeight() {
//        return 300;
//    }

//    @Override
//    public float getPrefWidth() {
//        return 500;
//    }
    @Override
    public float getMaxHeight() {
        return 300;
    }

    public void store(Item item){
        SlotActor a = new SlotActor(GDefence.getInstance().assetLoader.get("skins/uiskin.json", Skin.class), new Slot(1, TowerObject.class)){
            @Override
            public float getPrefWidth() {
                return 60;
            }

            @Override
            public float getPrefHeight() {
                return 60;
            }
        };
        a.setSize(60, 60);
        a.getSlot().add(TowerObject.generateStartObjects(item, 1));
        a.addListener(new TooltipListener(new SlotTooltip(getStage(), a.getSlot(), GDefence.getInstance().assetLoader.getSkin()), true));
        dragAndDrop.addSource(new BuySlotSource(a));

        items.addActor(a);
        a.addListener(new DoubleClickListener(fastInventory, a){//fastInventory can be null in loading, check it //TODO
            @Override
            protected void move() {
                toInventory.store(TowerObject.generateStartObjects(owner.getSlot().getPrototype(), 1));
            }
        });
        Label l = new Label(a.getSlot().getPrototype().getGlobalCost() + "$", FontLoader.generateStyle(28, Color.BLACK)){
            @Override
            public float getPrefWidth() {
                return 60;
            }

            @Override
            public float getPrefHeight() {
                return 60;
            }
        };
        l.setAlignment(Align.center);
        l.setSize(60, 60);
        costs.addActor(l);

        items.pack();
        costs.pack();
//        table.pack();
//        pack();
        if(items.getChildren().size < 5) {
            table.pack();
            pack();
        }


//        System.out.println(items.getHeight());
//        System.out.println(costs.getHeight());

//        getInventory().storeNew(item, amount);
//        addCost();
    }

//    private void addCost(){
//        for (int i = 0; i < getInventory().getSlots().size; i++){
//            Item item = getInventory().getSlots().get(i).getPrototype();//TowerObject can be instead prototype
//            if(item!= null){
//                cost[i].setText("$" + item.getGlobalCost());
//            }
//
//        }
//    }
//
//    @Override
//    protected void setDefaults() {
//        setPosition(740, 200);
//        defaults().space(8);
//        defaults().size(60, 60);
//        row().fill().expandX();
//        setRowNumber(1);
//        setRows(4);
//    }
//
//    @Override
//    protected void addSourceTarget(SlotActor slotActor) {
//        dragAndDrop.addSource(new BuySlotSource(slotActor));
//
//        ////dragAndDrop.addTarget(new SlotTarget(slotActor));
//    }
//
//    @Override
//    public void addSlots(InventoryActor inventory) {
//        for(SlotActor s:inventory.getActorArray()){
//            ////dragAndDrop.addSource(new BuySlotSource(s));
//            ////inventory.getDragAndDrop().addSource(new CombinedSlotSource(s));
//            dragAndDrop.addTarget(new SlotTarget(s));
//
//            inventory.getDragAndDrop().addTarget(new SellTarget(sellButton));//need 1 time
//
//
//        }
//    }
//
//    @Override
//    protected void beforeRow(Slot slot, int i) {
//        //Item item = slot.getPrototype();
//        //if(item != null) {
//        //    ItemEnum.Tower tower = (ItemEnum.Tower) item;
//        //    super.add(new Label("$" + tower.getCost(), FontLoader.generateStyle(16, Color.BLACK)));
//        //    System.out.println(item);
//        //}else {
//        if(cost == null){
//            cost = new Label[getInventory().getSlots().size];
//        }
//        cost[i] = new Label("", FontLoader.generateStyle(26, Color.BLACK));
//        super.add(cost[i]);
//        //}
//    }

    public void addFastMove(Inventory toInv){
        fastInventory = toInv;
        for (Actor a:items.getChildren()){
            a.addListener(new DoubleClickListener(toInv, ((SlotActor) a)){//think that all items can be only slotActor
                @Override
                protected void move() {
                    toInventory.store(TowerObject.generateStartObjects(owner.getSlot().getPrototype(), 1));
                }
            });
        }
    }


}
