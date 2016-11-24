package com.darkhouse.gdefence.Screens.BottomPanel;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Align;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.InventorySystem.inventory.*;
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
        gradeTowerSlot = new SlotActor(skin, new Slot(null, 0));
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
            gemGrades[i].addListener(new TooltipListener(new GemGradeTooltip(User.GEM_TYPE.values()[index], GDefence.getInstance().assetLoader.get("skins/uiskin.json", Skin.class)), true));
        }
        for (ImageButton i:gemGrades){
            add(i);
        }



        pack();
        setVisible(true);

    }
    private void notifyListeners(){
        gradeTowerSlot.getSlot().notifyListeners();


    }

    private void addGems(User.GEM_TYPE type){
        Item i = gradeTowerSlot.getSlot().getPrototype();
        if(i != null){
            if(i instanceof ItemEnum.Tower){
                if(GDefence.getInstance().user.spendGems(type, 1))
                    ((ItemEnum.Tower) i).addGems(type, 1);
            }
        }
    }

    protected void setDefaults(){
        setPosition(100, 250);
        defaults().space(8);
        defaults().size(60, 60);
        row().fill().expandX();
    }






}
