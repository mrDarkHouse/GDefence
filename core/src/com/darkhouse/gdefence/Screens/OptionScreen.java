package com.darkhouse.gdefence.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.I18NBundleLoader;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
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
import com.badlogic.gdx.utils.viewport.FitViewport;
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
        final AssetLoader l = GDefence.getInstance().assetLoader;

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
        final CheckBox vSync = new CheckBox("", skin)/*{
            @Override
            public float getPrefWidth() {
                return 40;
            }

            @Override
            public float getMinWidth() {
                return 40;
            }

            @Override
            public float getMinHeight() {
                return 40;
            }

            @Override
            public float getPrefHeight() {
                return 40;
            }
        }*/;
        if(GDefence.getInstance().vSync) vSync.setChecked(true);
//        vSync.setSize(80, 80);
//        if(Gdx.graphics.v)

        String[] languagesS = new String[]{"English", "Русский"};
        final SelectBox<String> language = new SelectBox<String>(skin, "description"){
            @Override
            protected void onHide(Actor selectBoxList) {
                super.onHide(selectBoxList);
            }
        };
        language.setItems(languagesS);
//        System.out.println(l.getLanguage());
         if     (l.getLanguage().equals("en"))  language.setSelectedIndex(0);
         else if(l.getLanguage().equals("ru"))  language.setSelectedIndex(1);


        TextButton apply = new TextButton(l.getWord("apply"), skin, "description");
        apply.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

//                    Display.setDisplayMode(new DisplayMode(resolution.getSelected().a, resolution.getSelected().b));//
                Gdx.graphics.setWindowedMode(resolution.getSelected().a, resolution.getSelected().b);
                Preferences pref = Gdx.app.getPreferences("config");
                pref.putBoolean("fullscreen", fullscreen.isChecked());
                pref.flush();

                if(fullscreen.isChecked()) {
                    if(!Gdx.graphics.isFullscreen())
//                            Display.setFullscreen(true);
                    Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());

                }


//                    getStage().getViewport().update(resolution.getSelected().a, resolution.getSelected().b);
//                    else Gdx.graphics.setWindowedMode(Gdx.graphics.getDisplayMode().width, Gdx.graphics.getDisplayMode().height);

                if(vSync.isChecked() != GDefence.getInstance().vSync) {
                    if (vSync.isChecked()) {
                        Gdx.graphics.setVSync(true);
                        GDefence.getInstance().vSync = true;
                    } else {
                        Gdx.graphics.setVSync(false);
                        GDefence.getInstance().vSync = false;
                    }
                }

                switch (language.getSelectedIndex()){
                    case 0: {
                        if(!l.getLanguage().equals("en")) {
                            l.changeLang("en");

                        }
                    }break;
                    case 1: {
                        if(!l.getLanguage().equals("ru")) {
                            l.changeLang("ru");

//                                System.out.println(l.getLanguage());
//                                System.out.println(language.getSelected());
                        }
                    }break;
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
        table.setPosition(0, GDefence.HEIGHT);

        I18NBundle b = GDefence.getInstance().assetLoader.get("Language/text", I18NBundle.class);
//        System.out.println(b.get("options"));
//        System.out.println(b.get("aspect_ratio"));

        table.padTop(30);
        table.add(new Label(b.get("aspect_ratio"), skin, "description"));
        table.add(aspectRatio).width(200).height(50).row();
        table.add(new Label(b.get("resolution"), skin, "description"));
        table.add(resolution).width(200).height(50).row();
        table.add(new Label(b.get("fullscreen"), skin, "description"));
        table.add(fullscreen).prefSize(40).row();
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
//        batch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
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
