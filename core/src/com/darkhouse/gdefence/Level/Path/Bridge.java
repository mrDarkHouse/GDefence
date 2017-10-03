package com.darkhouse.gdefence.Level.Path;


import com.badlogic.gdx.graphics.Texture;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Mob.Way;

public class Bridge extends WalkableMapTile/* implements Walkable*/{
    private int toAct;
    private int counter;
    private Way inputWay;
    private Way endWay1;
    private Way endWay2;
    public Texture texture1; //oh shit oh shit IT'S PUBLIC
    public Texture texture2;
    private TargetType applyMobs;

    @Override
    public TargetType getApplyMobs() {
        return applyMobs;
    }


    public Bridge(Way inputWay, Way endWay1, Way endWay2, TargetType applyMobs, int toAct) {
        this.inputWay = inputWay;
        this.endWay1 = endWay1;
        this.endWay2 = endWay2;
        this.applyMobs = applyMobs;
        this.toAct = toAct;
//        initTexture();
    }

    private void updateTexture(){
        if(counter == toAct)setRegion(texture2);
        else setRegion(texture1);
    }

    private String getTextureName(Way endWay1, Way endWay2){
        if      (endWay1 == Way.DOWN && endWay2 == Way.RIGHT || endWay1 == Way.RIGHT && endWay2 == Way.DOWN){
            return "DR";
        }else if(endWay1 == Way.RIGHT && endWay2 == Way.UP || endWay1 == Way.UP && endWay2 == Way.RIGHT){
            return "UR";
        }
        return null;
    }

    @Override
    public void initTexture() {
//        Texture texturePath;
        try {
//            texture1 = GDefence.getInstance().assetLoader.get("Path/roadHorizontal.png", Texture.class);
//            texture2 = GDefence.getInstance().assetLoader.get("Path/roadHorizontal.png", Texture.class);
            String scnd = inputWay.getShortName() + getTextureName(endWay1, endWay2);
            if (!isSwimmable()) {
                texture1 = GDefence.getInstance().assetLoader.get("Path/Bridge/bridge" + scnd + "1.png", Texture.class);
                texture2 = GDefence.getInstance().assetLoader.get("Path/Bridge/bridge" + scnd + "2.png", Texture.class);
            }else {
                texture1 = GDefence.getInstance().assetLoader.get("Path/Bridge/waterBridge" + scnd + "1.png", Texture.class);
                texture2 = GDefence.getInstance().assetLoader.get("Path/Bridge/waterBridge" + scnd + "2.png", Texture.class);
            }
        }catch (IllegalArgumentException e){
            e.printStackTrace();////may not work
        }
        updateTexture();

//        switch (inputWay){
//            case LEFT:
//                if(endWay1 == Way.UP && endWay2 == Way.DOWN){
//                    texturePath = GDefence.getInstance().assetLoader.get("Path/Bridge/bridgeLUD" + getActNumber() + ".png", Texture.class);
//                    break;
//                }
//                break;
//            case RIGHT:
//                if(endWay1 == Way.UP && endWay2 == Way.DOWN){
//                    texturePath = GDefence.getInstance().assetLoader.get("Path/Bridge/bridgeRUD" + getActNumber() + ".png", Texture.class);
//                    break;
//                }
//                break;
//            case UP:
//                if(endWay1 == Way.LEFT && endWay2 == Way.RIGHT){
//                    texturePath = GDefence.getInstance().assetLoader.get("Path/Bridge/bridgeULR" + getActNumber() + ".png", Texture.class);
//                    break;
//                }
//                if(endWay1 == Way.UP && endWay2 == Way.LEFT){
//                    texturePath = GDefence.getInstance().assetLoader.get("Path/Bridge/bridgeUUL" + getActNumber() + ".png", Texture.class);
//                    break;
//                }
//                if(endWay1 == Way.UP && endWay2 == Way.RIGHT){
//                    texturePath = GDefence.getInstance().assetLoader.get("Path/Bridge/bridgeUUR" + getActNumber() + ".png", Texture.class);
//                    break;
//                }
//                break;
//            case DOWN:
//                if(endWay1 == Way.LEFT && endWay2 == Way.RIGHT){
//                    texturePath = GDefence.getInstance().assetLoader.get("Path/Bridge/bridgeDLR" + getActNumber() + ".png", Texture.class);
//                    break;
//                }
//                if(endWay1 == Way.DOWN && endWay2 == Way.LEFT){
//                    texturePath = GDefence.getInstance().assetLoader.get("Path/Bridge/bridgeDDL" + getActNumber() + ".png", Texture.class);
//                    break;
//                }
//                if(endWay1 == Way.DOWN && endWay2 == Way.RIGHT){
//                    texturePath = GDefence.getInstance().assetLoader.get("Path/Bridge/bridgeDDR" + getActNumber() + ".png", Texture.class);
//                    break;
//                }
//                break;
//            default: throw new IllegalArgumentException("inputWay cannot be " + inputWay);
//        }
//        setRegion(texturePath);
    }
//    private int getActNumber(){
//        if(counter == toAct)return 2;
//        else return 1;
//    }

    @Override
    public boolean isBuildable() {
        return false;
    }

    @Override
    public boolean isSwimmable() {
        return applyMobs == TargetType.WATER_ONLY;
    }



    public Way manipulatePath(Mob.MoveType enterMobType, Way currentWay){
        if (applyMobs == null || applyMobs.isConsist(enterMobType)) {
            Way w = counter == toAct ? endWay1 : endWay2;//2:1 or 1:2 ???

//        switch (inputWay){
//            case LEFT:
//                w = counter%toAct == 0? Way.DOWN:Way.UP;
//                break;
//            case RIGHT:
//                w = counter%toAct == 0? Way.DOWN:Way.UP;
//                break;
//            case UP:
//                w = counter%toAct == 0? Way.RIGHT:Way.LEFT;
//                break;
//            case DOWN:
//                w = counter%toAct == 0? Way.RIGHT:Way.LEFT;
//                break;
//        }
            if (counter < toAct) counter++;
            else counter = 0;
            updateTexture();//pre level not need //TODO
            return w;
        }
        return null;
    }
}
