package com.darkhouse.gdefence.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.utils.Align;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.Model.LevelButton;
import com.darkhouse.gdefence.Model.Panels.GemPanel;
import com.darkhouse.gdefence.Model.Panels.GoldPanel;
import com.darkhouse.gdefence.Model.Panels.UserPanel;
import com.darkhouse.gdefence.Screens.BottomPanel.BottomPanel;

public class CampainMap extends AbstractCampainScreen {
    private class PagedMap extends WidgetGroup{
        private Page[] pages;
        private int currentPage;

        public Page getCurrentPage(){
            return pages[currentPage];
        }

        private ImageButton next;
        private ImageButton prev;

        public boolean hasPrevous(){
            if(currentPage == 0) return false;
            //if locked prevous
            return true;
        }

        public boolean hasNext(){
            if(pages.length - 1 == currentPage) return false;
            if(pages[currentPage + 1].isLocked()) return false;
            return true;
        }

        public PagedMap(int number) {
            currentPage = 0;
            pages = new Page[number];
            pages[0] = new Page(6, 1, "forest");
            pages[1] = new Page(6, pages[0].getLastButtonsInt(), "desert");
            pages[2] = new Page(5, pages[1].getLastButtonsInt(), "lake");
            pages[3] = new Page(5, pages[2].getLastButtonsInt(), "space");
            for (Page p:pages){
                addActor(p);
                addActor(new Link(p));
            }
//            addActor(pages[0]);
//            addActor(pages[1]);
//            addActor(pages[2]);

            next = new ImageButton(GDefence.getInstance().assetLoader.getNextButtonSkin());
            prev = new ImageButton(GDefence.getInstance().assetLoader.getPrevButtonSkin());
            next.setPosition(1100, 200);
            next.addListener(new InputListener(){
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    nextPage();
                    return true;
                }
            });
            prev.setPosition(120, 200);
            prev.addListener(new InputListener(){
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    prevPage();
                    return true;
                }
            });
            addActor(next);
            addActor(prev);
//            next.setVisible(false);
//            prev.setVisible(false);
//
//            pages[0].setVisible(false);
//            pages[1].setVisible(false);
//            pages[2].setVisible(false);
            update();
        }
        private void nextPage(){
            if(currentPage == pages.length - 1) return;
            currentPage++;
            update();
        }
        private void prevPage(){
            if(currentPage == 0) return;
            currentPage--;
            update();
        }

        private void update(){//rework
            for (int i = 0; i < pages.length; i++){
                if(i == currentPage) pages[i].setVisible(true);
                else pages[i].setVisible(false);
            }
            if(hasNext()) next.setVisible(true);
            else next.setVisible(false);
            if(hasPrevous()) prev.setVisible(true);
            else prev.setVisible(false);

//            for (Page p:pages){//
////                p.updateLockedLevels();
//            }
        }
        public void updateTooltips(){
            for (Page p:pages){
                for (int i = 0; i < p.buttons; i++){
                    p.levels[i].getTooltip().hasChanged();
                }
            }
        }
    }
    private class Link extends Actor{
        private ShapeRenderer sr;
        private Page p;

        private Link(Page p) {
            this.p = p;
            sr = new ShapeRenderer();
            sr.setColor(Color.BLACK);
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
//            super.draw(batch, parentAlpha);
            if(!p.isVisible()) return;
            batch.end();
            sr.begin(ShapeRenderer.ShapeType.Filled);
            sr.setProjectionMatrix(camera.combined);
//            Gdx.gl.glLineWidth(6);
            for (int i = 0; i < /*pagedMap.getCurrentPage().levels.length */p.levels.length; i++){
                LevelButton l = p.levels[i];
                if(l.getNumber() + 1 != p.getLastButtonsInt()){
                    sr.rectLine(l.getX() + l.getWidth(), l.getY() + l.getHeight()/2, p.levels[i + 1].getX(),
                            p.levels[i + 1].getY() + p.levels[i + 1].getWidth()/2, 6);
                }
            }

//            sr.rectLine(getX() + table.getX() + r1.getX() + r1.getWidth()/2, getY() + table.getY() + r1.getY(),
//                    getX() + table.getX() + r2.getX() + r2.getWidth()/2, getY() + table.getY() + r2.getY() + r2.getHeight(), 3);

            sr.end();
            batch.begin();
        }
    }
    private class Page extends WidgetGroup{
//        private boolean isLocked;

        private int buttons;
        private int firstButtonInt;

        private Label name;
        private String text;

        public int getLastButtonsInt() {
            return firstButtonInt + buttons;
        }

        private LevelButton[] levels;

        @Override
        public void setVisible(boolean visible) {
            super.setVisible(visible);
            if(visible) {
                setBackground(GDefence.getInstance().assetLoader.get("Backgrounds/" + text + ".png", Texture.class));
            }
        }

        public Page(int buttons, int firstButton, String text) {
            this.buttons = buttons;
            this.firstButtonInt = firstButton;

            this.text = text;
            name = new Label(GDefence.getInstance().assetLoader.getWord(text), FontLoader.generateStyle(0, 70, Color.GRAY, 5, Color.BLACK));

            levels = new LevelButton[buttons];
            int borderSize = GDefence.WIDTH/8;
            int sizeBetween = GDefence.WIDTH/42;
            int levelButtonsSize[] = new int[2];
            levelButtonsSize[0] = (GDefence.WIDTH - (borderSize * 2 + sizeBetween * (buttons - 1))) / buttons;
            levelButtonsSize[1] = levelButtonsSize[0];


            addActor(name);
            for (int i = 0; i < buttons; i++){
                levels[i] = new LevelButton(firstButton + i);

//                if(!GDefence.getInstance().user.getLevelAvailable(firstButton + i)){
//                    levels[i].lock();
//                }
                levels[i].setSize(levelButtonsSize[0], levelButtonsSize[1]);
                levels[i].setPosition(borderSize + (levelButtonsSize[0] + sizeBetween)*i,
                        GDefence.HEIGHT/2 - GDefence.HEIGHT/8);

                addActor(levels[i]);
            }
            name.setPosition(GDefence.WIDTH/2 - name.getWidth()/2, GDefence.HEIGHT/2 + levels[0].getHeight() - 20);
//            updateLockedLevels();

        }
        public boolean isLocked(){
            return levels[0].isLocked();
        }

//        public void updateLockedLevels(){//calling 2 times
////            updateLock();
//            for (int i = 0; i < buttons; i++) {
//                //levels[i] = new LevelButton(firstButtonInt + i);
//                if (GDefence.getInstance().user.getLevelAvailable(firstButtonInt + i)) {
//                    levels[i].unLock();
//                }else {
//                    levels[i].lock();
//                }
//            }
//        }
    }
    private PagedMap pagedMap;
    private UserPanel userPanel;

    public UserPanel getUserPanel() {
        return userPanel;
    }

    //private GDefence mainClass;

    //private SpriteBatch batch;
    //private ShapeRenderer shape;

    //private Stage stage;


    public CampainMap() {
        super("campain");
//        loadButtons();
        //batch = new SpriteBatch();
        //shape = new ShapeRenderer();
        //this.mainClass = mainClass;

    }

    public void init(){
        loadButtons();
//        loadFrames();
    }

    @Override
    public void show() {
        super.show();
        update();
//        System.out.println(getStage().getWidth() + " " + getStage().getHeight());
        //init();
        //loadFrames();
    }

    private void loadButtons(){
        pagedMap = new PagedMap(4);//
        stage.addActor(pagedMap);


        //Viewport viewport = new ExtendViewport(GDefence.WIDTH, GDefence.HEIGHT);
        //stage = new Stage(viewport,batch);
        //Gdx.input.setInputProcessor(stage);

        int userPanelSize[] = {240, 120};
        int goldPanelSize[] = {60, userPanelSize[1]};
        stage.addActor(new GoldPanel(GDefence.WIDTH - userPanelSize[0] - goldPanelSize[0], GDefence.HEIGHT - userPanelSize[1] -
                topPadSize, goldPanelSize[0], goldPanelSize[1]));
        userPanel = new UserPanel(GDefence.WIDTH - userPanelSize[0], GDefence.HEIGHT - userPanelSize[1] - topPadSize,
                userPanelSize[0], userPanelSize[1]);
        stage.addActor(userPanel);
        //System.out.println(GDefence.HEIGHT - userPanelSize[1] - topPadSize);
        stage.addActor(new GemPanel(0, GDefence.HEIGHT - 140 - topPadSize, 200, 140));


        BottomPanel p = new BottomPanel();
//        p.align(Align.center);
        stage.addActor(p);

    }


    private void loadFrames(){
        Gdx.gl.glLineWidth(2);
        shape = new ShapeRenderer();

    }



    @Override
    public void render(float delta) {
//        drawLines();
        super.render(delta);

//        drawLines();
    }
//    private void drawLines(){
//        int lineWidth = 6;      //KOSTIl'
//        Gdx.gl.glLineWidth(lineWidth);
//        shape.begin(ShapeRenderer.ShapeType.Line);
//        shape.setProjectionMatrix(batch.getProjectionMatrix());
//        shape.setColor(0, 0, 0, 1);
//        shape.line(0, GDefence.HEIGHT/5 + lineWidth, GDefence.WIDTH, GDefence.HEIGHT/5 + lineWidth);
//        shape.line(GDefence.WIDTH/5, GDefence.HEIGHT/5 + lineWidth, GDefence.WIDTH/5 , 0);
//        shape.line(GDefence.WIDTH/5 * 2, GDefence.HEIGHT/5 + lineWidth, GDefence.WIDTH/5 * 2 , 0);
//        shape.line(GDefence.WIDTH/5 * 3, GDefence.HEIGHT/5 + lineWidth, GDefence.WIDTH/5 * 3, 0);
//        shape.line(GDefence.WIDTH / 5 * 4, GDefence.HEIGHT / 5 + lineWidth, GDefence.WIDTH / 5 * 4, 0);
//
////        linkLevels();
//
//
//
//
//        shape.end();
//    }

//    private void linkLevels(){
//        for (int i = 0; i < pagedMap.getCurrentPage().levels.length; i++){
//            LevelButton l = pagedMap.getCurrentPage().levels[i];
//            if(l.getNumber() + 1 != pagedMap.getCurrentPage().getLastButtonsInt()){
//                shape.line(l.getX() + l.getWidth(), l.getY() + l.getHeight()/2, pagedMap.getCurrentPage().levels[i + 1].getX(),
//                        pagedMap.getCurrentPage().levels[i + 1].getY() + pagedMap.getCurrentPage().levels[i + 1].getWidth()/2);
//            }
//        }
//    }




//    @Override
//    public void resize(int width, int height) {
//
//    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
    private void update(){
        pagedMap.update();
        pagedMap.updateTooltips();
        userPanel.update();
    }


//    private void flush(){//set all parametres in default
//
//    }



    @Override
    public void hide() {
        super.hide();
//        GDefence.getInstance().user.save();//saving on each switchScreen
//        flush();
        //Gdx.input.setInputProcessor(null);
        //this.dispose();
    }

//    @Override
//    public void dispose() {
//        super.dispose();
//        //batch.dispose();
//    }
}
