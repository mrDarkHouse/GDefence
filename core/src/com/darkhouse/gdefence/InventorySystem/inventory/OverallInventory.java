package com.darkhouse.gdefence.InventorySystem.inventory;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Align;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Screens.CampainMap;
import com.darkhouse.gdefence.User;

public class OverallInventory extends Window{

//    private Inventory[] inventories;
    private InventoryActor[] actors;
    private int currentInventory;
    private DragAndDrop dragAndDrop;

    public Inventory getCurrentInventory(){
        switch (currentInventory){
            case 0: return User.getTowerInventory();
            case 1: return User.getSpellInventory();
            case 2: return User.getDetailInventory();
        }
        return null;
    }


    public OverallInventory(Inventory[] customInventories){
        super(GDefence.getInstance().assetLoader.getWord("arsenal"), GDefence.getInstance().assetLoader.getSkin(), "description");
        getTitleLabel().setAlignment(Align.center);
        setMovable(false);

        dragAndDrop = new DragAndDrop();

        AssetLoader l = GDefence.getInstance().assetLoader;

        actors = new InventoryActor[3];
        actors[0] = new InventoryActor(customInventories[0], dragAndDrop,
                l.getSkin());
        actors[0].getTitleLabel().setText(l.getWord("towers"));
        actors[1] = new InventoryActor(customInventories[1], dragAndDrop,
                l.getSkin());
        actors[1].getTitleLabel().setText(l.getWord("spells"));
        actors[2] = new InventoryActor(customInventories[2], dragAndDrop,
                l.getSkin());
        actors[2].getTitleLabel().setText(l.getWord("details"));


        createButtons();
    }

    public OverallInventory() {
        this(new Inventory[]{User.getTowerInventory(), User.getSpellInventory(), User.getDetailInventory()});

//        super("Arsenal", GDefence.getInstance().assetLoader.getSkin(), "description");
//        getTitleLabel().setAlignment(Align.center);
//        setMovable(false);
//
//        dragAndDrop = new DragAndDrop();
//
//        AssetLoader l = GDefence.getInstance().assetLoader;
//
////        setPosition(100, 250);
//        actors = new InventoryActor[3];
//        actors[0] = new InventoryActor(User.getTowerInventory(), dragAndDrop,
//                l.get("skins/uiskin.json", Skin.class));
//        actors[0].getTitleLabel().setText("Towers");
//        actors[1] = new InventoryActor(User.getSpellInventory(), dragAndDrop,
//                l.get("skins/uiskin.json", Skin.class));
//        actors[1].getTitleLabel().setText("Spells");
//        actors[2] = new InventoryActor(User.getDetailInventory(), dragAndDrop,
//                l.get("skins/uiskin.json", Skin.class));
//        actors[2].getTitleLabel().setText("Details");
//
//        createButtons();
    }

    private void createButtons(){
        float size = actors[0].getHeight()/3;
        AssetLoader l = GDefence.getInstance().assetLoader;

        ImageButton changeTower = new ImageButton(l.generateImageButtonSkin(l.get("Buttons/towersButton.png", Texture.class)));
        changeTower.setSize(size, size);//dont work
        changeTower.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                changeInventory(0);
                return true;
            }
        });
        ImageButton changeSpell = new ImageButton(l.generateImageButtonSkin(l.get("Buttons/spellsButton.png", Texture.class)));
        changeSpell.setSize(size, size);
        changeSpell.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                changeInventory(1);
                return true;
            }
        });
        ImageButton changeDetail = new ImageButton(l.generateImageButtonSkin(l.get("Buttons/detailsButton.png", Texture.class)));
        changeDetail.setSize(size, size);
        changeDetail.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                changeInventory(2);
                return true;
            }
        });

        Stack inventoryGroup = new Stack(actors);
        VerticalGroup buttonsGroup = new VerticalGroup();
        buttonsGroup.addActor(changeTower);
        buttonsGroup.addActor(changeSpell);
        buttonsGroup.addActor(changeDetail);

//        add(changeTower);
//        add(actors[0]).row();
//        add(changeSpell).row();
//        add(changeDetail);
        add(buttonsGroup);
//        add(actors[0]);
        add(inventoryGroup);

//        add(actorGroup);


//        add(actors[0]);///
//        add(actors[1]);
//        add(actors[2]);


        currentInventory = 0;
        pack();


        update();
    }



    public void init(){
        for (InventoryActor a:actors){
            a.init();
        }
    }
    public void notifyListeners(){
        for (InventoryActor a:actors){
            a.notifyListeners();
        }
    }

    public void changeInventory(int newInventory){
        if(newInventory > 2 || newInventory < 0) return;
        if(newInventory != currentInventory){
            currentInventory = newInventory;
        }
        update();
    }

    public void addTarget(InventoryActor i){
        for (SlotActor a:i.actorArray){
            addTarget(a);
        }
    }

    public void addTarget(SlotActor a){
        for (InventoryActor i:actors){
            i.addAnotherTarget(a);
        }
    }
    public void addSlotAsTarget(DragAndDrop d){//split into 2 methods
        for (InventoryActor i:actors){
//            i.addThisAsSource(d);
            i.addThisAsTarget(d);
        }
    }





    private void update(){
        for (int i = 0; i < actors.length; i++){
            if(i != currentInventory){
                actors[i].setVisible(false);
            }else {
                actors[i].setVisible(true);
            }
        }


    }







}
