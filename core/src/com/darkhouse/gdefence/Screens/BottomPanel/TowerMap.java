package com.darkhouse.gdefence.Screens.BottomPanel;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
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
    private ShapeRenderer sr;

    public TowerMap(Skin skin) {
        super("Tower Map", skin);
        getTitleLabel().setAlignment(Align.center);
        setMovable(false);
        setResizable(false);
//        setModal(true);
//        setClip(true);

        sr = new ShapeRenderer();
        sr.setColor(Color.BLACK);
//        Gdx.gl.glLineWidth(1);
//        sr.setAutoShapeType(true);

        setPosition(700, 350);
        defaults().space(25);
        defaults().spaceBottom(50);
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
        buttons.add(new RecipeButton(ItemEnum.Tower.Short));
        buttons.add(new RecipeButton(ItemEnum.Tower.Mountain));
        buttons.add(new RecipeButton(ItemEnum.Tower.SteelArrow));
        buttons.add(new RecipeButton(ItemEnum.Tower.Catapult));
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
        add(buttons.get(0)).align(Align.center).colspan(5).row();
        add(buttons.get(1)).colspan(2);
        add(buttons.get(2));
        add(buttons.get(3)).colspan(2).row();
        add(buttons.get(4));
        add(buttons.get(5));
        add(buttons.get(6));
        add(buttons.get(7));
        add(buttons.get(8));



    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
//        Vector2 line = new Vector2(buttons.get(0).getX(), buttons.get(0).getY()).sub(new Vector2(buttons.get(1).getX(), buttons.get(1).getY()));
//        float lenght = line.len();
//        for (int i = 0; i < lenght; i++){
//            //batch.draw(GDefence.getInstance().assetLoader.get("buildGridLinePixel.png", Texture.class), ((float) (line.x + i * Math.cos(line.angle()))), ((float) (line.y + i * Math.sin(line.angle()))));
//        }
        batch.end();
        sr.begin(ShapeRenderer.ShapeType.Filled);
        for (int i = 1; i <= 3; i++){
            linkTowers(sr, buttons.get(0), buttons.get(i));
        }
        linkTowers(sr, buttons.get(1), buttons.get(4));
        linkTowers(sr, buttons.get(1), buttons.get(5));
        linkTowers(sr, buttons.get(1), buttons.get(7));
        linkTowers(sr, buttons.get(2), buttons.get(6));
        linkTowers(sr, buttons.get(2), buttons.get(8));
        linkTowers(sr, buttons.get(3), buttons.get(7));
        linkTowers(sr, buttons.get(3), buttons.get(8));

        sr.end();
        batch.begin();
    }

    private void linkTowers(ShapeRenderer sh, RecipeButton r1, RecipeButton r2){
        sh.rectLine(getX() + r1.getX() + r1.getWidth()/2, getY() + r1.getY(),
                getX() + r2.getX() + r2.getWidth()/2, getY() + r2.getY() + r2.getHeight(), 3);

    }
}
