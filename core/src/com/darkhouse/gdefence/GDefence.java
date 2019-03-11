package com.darkhouse.gdefence;

import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.backends.lwjgl.LwjglCursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;
import com.darkhouse.gdefence.Screens.*;
import com.darkhouse.gdefence.Screens.BottomPanel.Arsenal;
import com.darkhouse.gdefence.Screens.BottomPanel.Smith;
import com.darkhouse.gdefence.Screens.BottomPanel.Store;

import java.util.Random;

public class GDefence extends Game {
	//SpriteBatch batch;
	//Texture img;
	//private MainMenu menu;
	private static GDefence mainClass;

    public static int WIDTH = 1280;
    public static int HEIGHT = 720;

	public static GDefence getInstance(){
		return mainClass;
	}

	private MainMenu mainMenu;
	private CampainChoose campainChoose;
	private OptionScreen optionScreen;
	private CampainMap campainMap;//must be in campainChoose
	private Arsenal arsenal;
	private Store store;
	private Smith smith;
	private LevelPreparationScreen levelPreparationScreen;

	public MainMenu getMainMenu() {
		return mainMenu;
	}
	public CampainChoose getCampainChoose() {
		return campainChoose;
	}
    public void flushCampainChoose(){
        campainChoose = new CampainChoose();
    }
	public OptionScreen getOptionScreen() {
		return optionScreen;
	}
	public CampainMap getCampainMap() {
		return campainMap;
	}
	public Arsenal getArsenal() {
		return arsenal;
	}
	public Store getStore() {
		return store;
	}
	public Smith getSmith() {
		return smith;
	}

    public void flushCampainScreens(){
        initCampainMap();
        arsenal = new Arsenal();
        arsenal.init();
        store = new Store();
        store.init();
        smith = new Smith();
        smith.init();
        levelPreparationScreen = new LevelPreparationScreen();
    }

	public LevelPreparationScreen getLevelPreparationScreen() {
		return levelPreparationScreen;
	}
	//private Screen currentScreen;

	public User user;

	public AssetLoader assetLoader = new AssetLoader();

    public boolean vSync;

//    public boolean isvSync() {
//        return vSync;
//    }
    //public AssetManager assets;



	@Override
	public void create () {
		mainClass = this;
		//assets = new AssetManager();
		Texture.setAssetManager(assetLoader);



		if(Gdx.app.getType() == Application.ApplicationType.Desktop) {
            Pixmap pm = new Pixmap(Gdx.files.internal("Cursors/stock.png"));
            int xHotSpot = /*pm.getWidth() / 2 */9;  //for stick cursor
            int yHotSpot = /*pm.getHeight() / 2 */3;
            Gdx.graphics.setCursor(new LwjglCursor(pm, xHotSpot, yHotSpot));
            pm.dispose();
        }




//        assetLoader.loadOld();



//		user = new User();//

		//setScreen(new StartingLoadScreen());
		switchScreen(new StartingLoadScreen());
		//initScreens();

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
        assetLoader.dispose();
		FontLoader.dispose();
	}
	public void initAll(){
        FontLoader.load();
		ItemEnum.Tower.init();
//        User.Research.init();
//        assetLoader.init();
//		GDefence.getInstance().user.init();//it must be in campain loading
//		GDefence.getInstance().user.save();
		initScreens();
        initTips();
	}
    public void initTips(){
//        String[] tips;
        tips = new String[9];
        tips[0] = assetLoader.getWord("tip1");
        tips[1] = assetLoader.getWord("tip2_1") + " " + User.GEM_TYPE.getBoost(User.GEM_TYPE.RED) + System.getProperty("line.separator") +
                assetLoader.getWord("tip2_2") + " " + User.GEM_TYPE.getBoost(User.GEM_TYPE.YELLOW) + System.getProperty("line.separator") +
                assetLoader.getWord("tip2_3") + " " + User.GEM_TYPE.getBoost(User.GEM_TYPE.BLUE);
        tips[2] = assetLoader.getWord("tip3_1") + System.getProperty("line.separator") +
                assetLoader.getWord("tip3_2");
        tips[3] = assetLoader.getWord("tip4_1") + System.getProperty("line.separator") +
                assetLoader.getWord("tip4_2")+ System.getProperty("line.separator") +
                assetLoader.getWord("tip4_3");
        tips[4] = assetLoader.getWord("tip5");
        tips[5] = assetLoader.getWord("tip6_1") + System.getProperty("line.separator") +
                assetLoader.getWord("tip6_2");
        tips[6] = assetLoader.getWord("tip7_1") + System.getProperty("line.separator") +
                assetLoader.getWord("tip7_2") + System.getProperty("line.separator") +
                assetLoader.getWord("tip7_3");
        tips[7] = assetLoader.getWord("tip8_1") + System.getProperty("line.separator") +
                assetLoader.getWord("tip8_2");
        tips[8] = assetLoader.getWord("tip9");


//        tips = new String[]{, tip2, tip3, tip4, tip5 ,tip6, tip7, tip8, tip9};
    }

    public void initCampainMap(){
        campainMap = new CampainMap();
        campainMap.init();
    }

	public void initScreens(){
		mainMenu = new MainMenu();
		mainMenu.init();
		campainChoose = new CampainChoose();
//        initCampainMap();
		optionScreen = new OptionScreen();
//		arsenal = new Arsenal();
//		arsenal.init();
//		store = new Store();
//		store.init();
//		smith = new Smith();
//		smith.init();
//		levelPreparationScreen = new LevelPreparationScreen();
	}

	public void switchScreen(Screen screen){
        setScreen(screen);

		//setInputProcessor
	}


	public void setPreviousScreen(){
		Screen currentScreen = getScreen();
		if(currentScreen instanceof CampainMap){//only in back button
            GDefence.getInstance().user.save();
            GDefence.getInstance().user.flush();//empty user info in main menu
			setScreen(campainChoose);
		}else if(currentScreen instanceof OptionScreen){
			setScreen(mainMenu);
		}else if(currentScreen instanceof CampainChoose){
			setScreen(mainMenu);
		}else if(currentScreen instanceof Arsenal){
			setScreen(campainMap);
		}else if(currentScreen instanceof Store){
			setScreen(campainMap);
		}else if(currentScreen instanceof Smith){
			setScreen(campainMap);
		}else if(currentScreen instanceof LevelPreparationScreen){
			setScreen(campainMap);
		}
	}
    public void log(String s){
//        System.out.println("LOG: " + s);
    }


	private String[] tips;/* = {
			"Every tower level up it can be grade by gem",

			"Red gem up attack damage by " + User.GEM_TYPE.getBoost(User.GEM_TYPE.RED) + System.getProperty("line.separator") +
			"Yellow up attack speed by " + User.GEM_TYPE.getBoost(User.GEM_TYPE.YELLOW) + System.getProperty("line.separator") +
			"Blue up attack range by " + User.GEM_TYPE.getBoost(User.GEM_TYPE.BLUE),

			"You can create new Tower if you had bought recipe and get all needed components" + System.getProperty("line.separator") +
			"Created tower become available in shop",

			"After grading similar gem 4 times other gems are down by 1 each" + System.getProperty("line.separator") +
			"Example: 4 red gems up, -1 yellow, -1 blue",

			"If you complete level in second time " + System.getProperty("line.separator") +
            "You receive only 1/4 of gold and exp" + System.getProperty("line.separator") +
            "Drop also became less quality and count",
			*//*"f",*//*};*/

	public String getTip(){
		Random r = new Random();
		return tips[r.nextInt(tips.length)];
	}

}
