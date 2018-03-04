package com.darkhouse.gdefence.Model.Level;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Level.Level;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Path.MapTile;
import com.darkhouse.gdefence.Level.Path.Road;
import com.darkhouse.gdefence.Objects.GameObject;
import com.darkhouse.gdefence.Screens.LevelMap;

import java.util.HashMap;

public class PathSigner extends WidgetGroup{

//    private class Signer{
//
//        public Signer() {
//        }
//    }

    private NextWaveTimer nextWaveTimer;
    private HashMap<Mob.MoveType, Array<Path>> paths;
    private Array<Array<ImageButton>> arrows;
//    private Array<Array<Texture>> arrows;

    public PathSigner(NextWaveTimer t, HashMap<Mob.MoveType, Array<Path>> paths) {
        this.nextWaveTimer = t;
        this.paths = paths;

        arrows = new Array<Array<ImageButton>>();
        Level lvl = LevelMap.getLevel();
        for (int i = 0; i < lvl.numberWaves; i+= lvl.getMap().getSpawner().size()) {
            arrows.add(initTextures(lvl, i));
        }
        update(0);

    }


    public Array<ImageButton> initTextures(Level lvl, int currentWave){
//        clear();
        Array<ImageButton> arr = new Array<ImageButton>();
//        Level lvl = LevelMap.getLevel();
//        System.out.println(paths);
        int spawners = lvl.getMap().getSpawner().size();
        int ground = 0;
        int water = 0;

        /*for (int i = 0; i < spawners; i++){
            System.out.println(lvl.getWave(lvl.currentWave + 1 + i));
            if(Mob.getMobById(lvl.getWave(lvl.currentWave + 1 + i).getMobID()).getMoveType() == Mob.MoveType.ground){
                ground++;
            }else water++;
        }*/
//        System.out.println(ground + " " + water);

//        Array<Path> typePaths = paths.get(Mob.getMobById(lvl.getCurrentWave().getMobID()).getMoveType());
//        System.out.println(typePaths);
//        Mob.MoveType[] moveTypes = paths.keySet().toArray(new Mob.MoveType[]{});
//        arrows = new Array<Array<Texture>>();
//        for (int i = 0; i < moveTypes.length; i++){
//            arrows.add(new Array<Array<Texture>>());
//            for (int k = 0; k < paths.get(moveTypes[i]).size; k++){
//        for (int spawnI = 0; spawnI < typePaths.size; spawnI++) {


            for (int k = 0; k < /*typePaths.size*/spawners; k++) {
//                arrows.add(new Array<Texture>());

                Color color = Color.WHITE;//default color()
//                    System.out.println(Mob.getMobById(lvl.getCurrentWave().getMobID()));
                Path currPath;
                switch (Mob.getMobById(/*lvl.getCurrentWave().getMobID()*/lvl.getWave(/*lvl.*/currentWave + 1 + k).getMobID()).getMoveType()) {
                    case ground:
                        if (/*currPath.getSpawnIndex()*/ground == 0) color = Color.FIREBRICK;
                        if (/*currPath.getSpawnIndex()*/ground == 1) color = Color.FOREST;
                        if (/*currPath.getSpawnIndex()*/ground == 2) color = Color.BROWN;
                        ground++;
                        currPath = paths.get(Mob.MoveType.ground).get(k);
                        break;
                    case water:
                        if (/*currPath.getSpawnIndex()*/water == 0) color = Color.BLUE;
                        if (/*currPath.getSpawnIndex()*/water == 1) color = Color.CYAN;
                        if (/*currPath.getSpawnIndex()*/water == 2) color = Color.CORAL;
                        water++;
                        currPath = paths.get(Mob.MoveType.water).get(k);
                        break;
                    default:throw new IllegalArgumentException("move type dont support by PathSigner");
                }
                for (int j = 0; j < currPath.size/*.get(k).size*/; j++) {
//                    Path currPath = /*typePaths.get(k)*/paths.get(Mob.getMobById(lvl.getCurrentWave().getMobID()).getMoveType()).get(0);
                    String signCode;
                    if (j - 1 < 0) signCode = MapTile.getSignerCode(null, currPath.get(j), currPath.get(j + 1));
                    else if (j + 1 >= currPath.size) continue;
                        //signCode = MapTile.getSignerCode(paths.get(moveTypes[i]).get(k).get(j - 1), paths.get(moveTypes[i]).get(k).get(j), null);
                    else signCode = MapTile.getSignerCode(currPath.get(j - 1), currPath.get(j), currPath.get(j + 1));
                    if(signCode == null) continue;
//                    switch (((Walkable) currPath.get(j)).getApplyMobs())
                    ImageButton s;
                    Texture signerTexture = GDefence.getInstance().assetLoader.get("Path/Signer/sign" + signCode + ".png", Texture.class);
                    TextureData data = signerTexture.getTextureData();
                    data.prepare();
                    Pixmap p = (signerTexture.getTextureData().consumePixmap());


                    for (int x = 0; x < p.getWidth(); x++) {
                        for (int y = 0; y < p.getHeight(); y++) {
                            int findColor = p.getPixel(x, y);
                            if (findColor == -16776961) {
                                p.setColor(color);
                                p.fillRectangle(x, y, 1, 1);
                            }
                        }
                    }
                    Texture updatedTexture = new Texture(p);
                    data.disposePixmap();
                    p.dispose();
//                    (GDefence.getInstance().assetLoader.get("Path/Signer/sign" + signCode + ".png", Texture.class));

//                    s = new ImageButton(new TextureRegionDrawable(new TextureRegion(GDefence.getInstance().assetLoader.get("Path/Signer/sign" + signCode + ".png", Texture.class))));
                    s = new ImageButton(new TextureRegionDrawable(new TextureRegion(updatedTexture)));
                    s.setBounds(currPath.get(j).getX(), currPath.get(j).getY(),
                            currPath.get(j).getWidth(), currPath.get(j).getHeight());

//                    addActor(s);
                    arr.add(s);
//                    arrows.get(i).get(k).add(GDefence.getInstance().assetLoader.get("Path/Turn/turnLU.png", Texture.class));
//                    switch ()
                }
            }
//        }
//        }
//        for (int y = 0; y < map[0].length; y++) {
//            for (int x = 0; x < map.length; x++) {
//
//            }
//        }
        return arr;

    }
    public void update(int currentWave){
        currentWave = currentWave/Level.getMap().getSpawner().size();
        clear();
        for (int i = 0; i < arrows.get(currentWave/* - Level.getMap().getSpawner().size()*/).size; i++) {
            addActor(arrows.get(currentWave/* - Level.getMap().getSpawner().size()*/).get(i));
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
//        if(LevelMap.getLevel().getCurrentWave() != null) {
//            initTextures();//kostil'
//        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(nextWaveTimer.getTime() > 0) {
            super.draw(batch, parentAlpha);
        }
    }
}
