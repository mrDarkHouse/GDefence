package com.darkhouse.gdefence.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.I18NBundleLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.I18NBundle;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import java.util.Locale;


public class OptionScreen extends AbstractMenuScreen {
    public OptionScreen() {
        super(true);
        loadButtons();
    }

    @Override
    public void show() {
        super.show();
//        loadButtons();
    }

    private class VariableStorer{
        private int a;
        private int b;
        private char regex;

        public VariableStorer(int a, int b, char regex) {
            this.a = a;
            this.b = b;
            this.regex = regex;
        }

        @Override
        public String toString() {
            return "" + a + regex + b;
        }
    }

    private void loadButtons(){
        Skin skin = GDefence.getInstance().assetLoader.getSkin();

        final Table table = new Table();
        table.defaults().space(10);






        final VariableStorer[][] allResolutions = new VariableStorer[2][];
        allResolutions[0] = new VariableStorer[]{new VariableStorer(1280, 720, 'x'), new VariableStorer(1920, 1080, 'x'), new VariableStorer(680, 320, 'x')};
        allResolutions[1] = new VariableStorer[]{new VariableStorer(1440, 1080, 'x'), new VariableStorer(1600, 1200, 'x')};

        final SelectBox<VariableStorer> resolution = new SelectBox<VariableStorer>(skin, "description"){
            @Override
            protected void onHide(Actor selectBoxList) {
                super.onHide(selectBoxList);
//                Gdx.graphics.setWindowedMode(getSelected().a, getSelected().b);
            }
        };
        resolution.setItems(allResolutions[0]);

        VariableStorer[] ratio = new VariableStorer[]{new VariableStorer(16, 9, ':'), new VariableStorer(4, 3, ':')};
        SelectBox<VariableStorer> aspectRatio = new SelectBox<VariableStorer>(skin, "description"){
            @Override
            protected void onHide(Actor selectBoxList) {
                super.onHide(selectBoxList);
                resolution.setItems(allResolutions[getSelectedIndex()]);
            }
        };
        aspectRatio.setItems(ratio);

        final CheckBox fullscreen = new CheckBox("", skin);
        fullscreen.setSize(80, 80);
        if(Gdx.graphics.isFullscreen())fullscreen.setChecked(true);
        final CheckBox vSync = new CheckBox("", skin);
        vSync.sizeBy(80, 80);
//        if(Gdx.graphics.v)

        String[] languagesS = new String[]{"Russian", "English"};
        final SelectBox<String> language = new SelectBox<String>(skin, "description"){
            @Override
            protected void onHide(Actor selectBoxList) {
                super.onHide(selectBoxList);
            }
        };
        language.setItems(languagesS);

        TextButton apply = new TextButton("Apply", skin, "description");
        apply.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                try {
                    Display.setDisplayMode(new DisplayMode(resolution.getSelected().a, resolution.getSelected().b));
                    if(fullscreen.isChecked()) Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());

//                    getStage().getViewport().update(resolution.getSelected().a, resolution.getSelected().b);
//                    else Gdx.graphics.setWindowedMode(Gdx.graphics.getDisplayMode().width, Gdx.graphics.getDisplayMode().height);

                    if(vSync.isChecked()) Gdx.graphics.setVSync(true);
                    else Gdx.graphics.setVSync(false);


                    I18NBundleLoader.I18NBundleParameter param;
                    switch (language.getSelectedIndex()){
                        case 0:
                            GDefence.getInstance().assetLoader.unload("Language/text");
                            param = new I18NBundleLoader.I18NBundleParameter(new Locale("ru"), "UTF-8");
                            GDefence.getInstance().assetLoader.load("Language/text", I18NBundle.class, param);
                            GDefence.getInstance().assetLoader.finishLoading();
                            GDefence.getInstance().initScreens();
                            GDefence.getInstance().switchScreen(GDefence.getInstance().getOptionScreen());
                            break;
                        case 1:
                            GDefence.getInstance().assetLoader.unload("Language/text");
                            param = new I18NBundleLoader.I18NBundleParameter(Locale.US, "UTF-8");
                            GDefence.getInstance().assetLoader.load("Language/text", I18NBundle.class, param);
                            GDefence.getInstance().assetLoader.finishLoading();
                            GDefence.getInstance().initScreens();
                            GDefence.getInstance().switchScreen(GDefence.getInstance().getOptionScreen());
                            break;
                    }
                } catch (LWJGLException e) {
                    e.printStackTrace();
                }
//                if(fullscreen.isChecked()) {
//                    Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
//                }else {
//                    Gdx.graphics.setWindowedMode(resolution.getSelected().a, resolution.getSelected().b);
//                }

                return true;
            }
        });




//        TextButton resolution = new TextButton("Resolution", GDefence.getInstance().assetLoader.getSkin());
//        resolution.addListener(new InputListener(){
//            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
//
//                //mainclass.setScreen(new CampainMap(mainclass));
//                return true;
//            }
//        });
//        TextButton sound = new TextButton("Sound", GDefence.getInstance().assetLoader.getSkin());
//        sound.addListener(new InputListener(){
//            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
//
//                //mainclass.setScreen(new CampainMap(mainclass));
//                return true;
//            }
//        });


        table.setWidth(stage.getWidth());
        table.align(Align.center|Align.top);
        table.setPosition(0, Gdx.graphics.getHeight());

        I18NBundle b = GDefence.getInstance().assetLoader.get("Language/text", I18NBundle.class);
//        System.out.println(b.get("options"));
//        System.out.println(b.get("aspect_ratio"));

        table.padTop(30);
        table.add(new Label(b.get("aspect_ratio"), skin, "description"));
        table.add(aspectRatio).width(200).height(50).row();
        table.add(new Label(b.get("resolution"), skin, "description"));
        table.add(resolution).width(200).height(50).row();
        table.add(new Label(b.get("fullscreen"), skin, "description"));
        table.add(fullscreen).size(90).row();
        table.add(new Label(b.get("vsync"), skin, "description"));
        table.add(vSync).row();
        table.add(new Label(b.get("language"), skin, "description"));
        table.add(language).row();


        table.add(apply).width(200).height(50).colspan(2).spaceTop(50);
//        table.add(sound).width(200).height(50).row();

        stage.addActor(table);

    }





    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        batch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
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

//    @Override
//    public void hide() {
//        super.hide();
//    }
//
//    @Override
//    public void dispose() {
//        super.dispose();
//    }
}
