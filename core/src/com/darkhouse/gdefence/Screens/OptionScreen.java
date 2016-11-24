package com.darkhouse.gdefence.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;

public class OptionScreen extends AbstractMenuScreen {
    public OptionScreen() {
        super(true);
    }

    @Override
    public void show() {
        super.show();
        loadButtons();
    }

    private void loadButtons(){
        Table table = new Table();

        TextButton resolution = new TextButton("Resolution", GDefence.getInstance().assetLoader.getSkin());
        resolution.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {

                //mainclass.setScreen(new CampainMap(mainclass));
                return true;
            }
        });
        TextButton sound = new TextButton("Sound", GDefence.getInstance().assetLoader.getSkin());
        sound.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {

                //mainclass.setScreen(new CampainMap(mainclass));
                return true;
            }
        });


        table.setWidth(stage.getWidth());
        table.align(Align.center|Align.top);
        table.setPosition(0, Gdx.graphics.getHeight());


        table.padTop(30);
        table.add(resolution).width(200).height(50).spaceBottom(10).row();
        table.add(sound).width(200).height(50).row();

        stage.addActor(table);

    }





    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
