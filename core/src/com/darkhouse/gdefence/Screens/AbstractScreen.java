package com.darkhouse.gdefence.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.darkhouse.gdefence.GDefence;


public abstract class AbstractScreen implements Screen {
    protected Viewport viewport;
    protected Stage stage;
    protected OrthographicCamera camera;

    public AbstractScreen() {
        camera = new OrthographicCamera(GDefence.WIDTH, GDefence.HEIGHT);
        camera.setToOrtho(false, GDefence.WIDTH, GDefence.HEIGHT);
        viewport = new FitViewport(GDefence.WIDTH, GDefence.HEIGHT, camera);
        viewport.apply();


        stage = new Stage(viewport);
//        camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
    }

    public Stage getStage() {
        return stage;
    }

    @Override
    public void show() {
      //  stage = new Stage();
    }

    @Override
    public void render(float delta) {
//        camera.update();
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, false);
//        camera.update();
//        camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
    }
}
