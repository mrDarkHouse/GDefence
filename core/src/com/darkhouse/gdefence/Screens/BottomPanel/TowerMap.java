package com.darkhouse.gdefence.Screens.BottomPanel;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.InventorySystem.inventory.HidingClickListener;
import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;
import com.darkhouse.gdefence.User;

public class TowerMap extends Window{
    private Array <RecipeButton> buttons;

    public TowerMap(Skin skin) {
        super("Tower Map", skin);
        getTitleLabel().setAlignment(Align.center);
        setMovable(false);

        setPosition(700, 450);
        defaults().space(15);
        defaults().size(60, 60);
        row().fill().expandX();

        initButtons();

        TextButton closeButton = new TextButton("X", skin);
        closeButton.addListener(new HidingClickListener(this));
        add(closeButton).height(getPadTop());//
        getTitleTable().add(closeButton).height(getPadTop());

        pack();

        setVisible(false);
    }



    private void initButtons(){
        buttons = new Array<RecipeButton>();
        buttons.add(new RecipeButton(ItemEnum.Tower.Basic));
        buttons.add(new RecipeButton(ItemEnum.Tower.Rock));
        buttons.add(new RecipeButton(ItemEnum.Tower.Arrow));
        buttons.add(new RecipeButton(ItemEnum.Tower.Range));
        buttons.add(new RecipeButton(ItemEnum.Tower.Ballista));


        for(RecipeButton b:buttons){
            b.setUpdateButtons(buttons);
        }

        //buttons.get(0).setPosition(200, 200);
        //buttons.get(0).setSize(40, 40);
        //buttons.get(1).setPosition(40, 120);
        //buttons.get(2).setPosition(100, 120);
        //buttons.get(3).setPosition(160, 120);

//        for (RecipeButton b:buttons){
//            add(b);
//        }
        add(buttons.get(0)).align(Align.center).colspan(3).row();
        add(buttons.get(1));
        add(buttons.get(2));
        add(buttons.get(3)).row();
        add(buttons.get(4));



    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        Vector2 line = new Vector2(buttons.get(0).getX(), buttons.get(0).getY()).sub(new Vector2(buttons.get(1).getX(), buttons.get(1).getY()));
        float lenght = line.len();
        for (int i = 0; i < lenght; i++){
            //batch.draw(GDefence.getInstance().assetLoader.get("buildGridLinePixel.png", Texture.class), ((float) (line.x + i * Math.cos(line.angle()))), ((float) (line.y + i * Math.sin(line.angle()))));
        }




    }
}
