package com.darkhouse.gdefence.Model.Panels;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.InventorySystem.inventory.*;
import com.darkhouse.gdefence.InventorySystem.inventory.Tooltip.GemGradeTooltip;
import com.darkhouse.gdefence.Objects.GameObject;
import com.darkhouse.gdefence.Objects.TowerObject;
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
    private ImageButton[] gemGrades;

    public GemGradePanel(DragAndDrop dragAndDrop, Skin skin) {
        super("Grade Panel", skin);
        getTitleLabel().setAlignment(Align.center);
        setMovable(false);
        setDefaults();

        this.dragAndDrop = dragAndDrop;
        gradeTowerSlot = new SlotActor(skin, new Slot(TowerObject.class, null, 0));
        add(gradeTowerSlot);
        gemGrades = new ImageButton[3];
        gemGrades[0] = new ImageButton(GDefence.getInstance().assetLoader.generateImageButtonSkin(GDefence.getInstance().assetLoader.get("Gems/redGem.png", Texture.class)));
        gemGrades[1] = new ImageButton(GDefence.getInstance().assetLoader.generateImageButtonSkin(GDefence.getInstance().assetLoader.get("Gems/yellowGem.png", Texture.class)));
        gemGrades[2] = new ImageButton(GDefence.getInstance().assetLoader.generateImageButtonSkin(GDefence.getInstance().assetLoader.get("Gems/blueGem.png", Texture.class)));
//        gemGrades[0].addListener(new InputListener() {
//            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                addGems(User.GEM_TYPE.RED);
//                notifyListeners();
//                return true;
//            }
//        });
//        gemGrades[1].addListener(new InputListener() {
//            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                addGems(User.GEM_TYPE.YELLOW);
//                notifyListeners();
//                return true;
//            }
//        });
//        gemGrades[2].addListener(new InputListener() {
//            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                addGems(User.GEM_TYPE.BLUE);
//                notifyListeners();
//                return true;
//            }
//        });
        for (int i = 0; i < gemGrades.length; i++){
            final int index = i;
            gemGrades[i].addListener(new InputListener() {
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    addGems(User.GEM_TYPE.values()[index]);
                    notifyListeners();
                    return true;
                }
            });
//            gemGrades[i].addListener(new TooltipListener(new GemGradeTooltip(User.GEM_TYPE.values()[index], GDefence.getInstance().assetLoader.get("skins/uiskin.json", Skin.class)), true));
        }
        for (ImageButton i:gemGrades){
            add(i);
        }



        pack();
        setVisible(true);

    }

    public void addTooltips(){//after stage.addActor
        for (int i = 0; i < gemGrades.length; i++){
            gemGrades[i].addListener(new TooltipListener(new GemGradeTooltip(getStage(), User.GEM_TYPE.values()[i], GDefence.getInstance().assetLoader.get("skins/uiskin.json", Skin.class)), true));
        }


        gradeTowerSlot.addTooltip(getStage());
    }
    private void notifyListeners(){
        gradeTowerSlot.getSlot().notifyListeners();


    }

    private void addGems(User.GEM_TYPE type){
        GameObject object = gradeTowerSlot.getSlot().getLast();
        if(object != null){
            if(object instanceof TowerObject){
                TowerObject towerObject = (TowerObject) object;
                if(towerObject.getLevel() > towerObject.getPrimaryGemsNumber()) {
                    if(GDefence.getInstance().user.spendGems(type, 1)) {
                        towerObject.addGems(type, 1);
                    }
                }else {
                    tooltipShow();
                }
            }
        }
    }

    private void tooltipShow(){
        //TextTooltip tt = new TextTooltip("Level must be greater then gems number", GDefence.getInstance().getSkin());
        Dialog d = new Dialog("", GDefence.getInstance().assetLoader.get("uiskin.json", Skin.class)){
            {
                text("Level must be greater then gems number").padTop(250);
                button("Ok", true);
                //getButtonTable();//defaults().width(200);
                Array<Cell> cells = getButtonTable().getCells();
                Cell cell = cells.get(0);
                cell.width(50).padBottom(360);
                cell.height(40);
                //cell.padBottom(Gdx.graphics.getHeight()/2);
                //cell.center().top();
//
//                cell = cells.get(1);
//                cell.width(50).padBottom(360);
//                cell.height(40);
                //cell.padBottom(Gdx.graphics.getHeight()/2);

            }
            @Override
            protected void result(Object object) {
                hide();
            }
        };
        d.setBackground(new TextureRegionDrawable(new TextureRegion(GDefence.getInstance().assetLoader.get("MainMenuTranparent.png", Texture.class))));
        d.getBackground().setMinWidth(Gdx.graphics.getWidth());
        d.getBackground().setMinHeight(Gdx.graphics.getHeight());
        d.show(getStage());

    }



    protected void setDefaults(){
        setPosition(100, 250);
        defaults().space(8);
        defaults().size(60, 60);
        row().fill().expandX();
    }






}
