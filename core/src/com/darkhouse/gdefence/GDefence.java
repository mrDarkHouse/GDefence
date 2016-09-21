package com.darkhouse.gdefence;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.Screens.*;
import com.darkhouse.gdefence.Screens.BottomPanel.Arsenal;
import com.darkhouse.gdefence.Screens.BottomPanel.Smith;
import com.darkhouse.gdefence.Screens.BottomPanel.Store;

public class GDefence extends Game {
	//SpriteBatch batch;
	//Texture img;
	//private MainMenu menu;
	private static GDefence mainClass;

	public static GDefence getInstance(){
		return mainClass;
	}

	public User user;

	
	@Override
	public void create () {
		mainClass = this;
        AssetLoader.load();
		FontLoader.load();
		setScreen(new MainMenu());


		//batch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");
	}

	//@Override
	//public void render () {
		//getScreen().render();
		//Gdx.gl.glClearColor(1, 0, 0, 1);
		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



		//batch.begin();
		//batch.draw(img, 0, 0);
		//batch.end();
	//}
	
	@Override
	public void dispose () {
		//super.dispose();
        AssetLoader.dispose();
		FontLoader.dispose();
	}

	public Screen setPreviousScreen(){
		Screen currentScreen = getScreen();
		if(currentScreen.getClass().getName().equals(CampainMap.class.getName())){
			setScreen(new CampainChoose());
		}else if(currentScreen.getClass().getName().equals(OptionScreen.class.getName())){
			setScreen(new MainMenu());
		}else if(currentScreen.getClass().getName().equals(CampainChoose.class.getName())){
			setScreen(new MainMenu());
		}else if(currentScreen.getClass().getName().equals(Arsenal.class.getName())){
			setScreen(new CampainMap());
		}else if(currentScreen.getClass().getName().equals(Store.class.getName())){
			setScreen(new CampainMap());
		}else if(currentScreen.getClass().getName().equals(Smith.class.getName())){
			setScreen(new CampainMap());
		}else if(currentScreen.getClass().getName().equals(LevelPreparationScreen.class.getName())){
			setScreen(new CampainMap());
		}
		return null;


	}

	public Skin getSkin(){
		return new Skin(Gdx.files.internal("uiskin.json"));
	}
}
