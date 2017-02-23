package com.darkhouse.gdefence.Level.Path;


import com.badlogic.gdx.graphics.Texture;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Mob.Way;

public class Bridge extends MapTile implements Walkable{
    private int toAct;
    private int counter;
    private Way inputWay;
    private Way endWay1;
    private Way endWay2;
    private Texture texture1;
    private Texture texture2;


    public Bridge(Way inputWay, Way endWay1, Way endWay2, int toAct) {
        this.inputWay = inputWay;
        this.endWay1 = endWay1;
        this.endWay2 = endWay2;
        this.toAct = toAct;
        initTexture();
    }

    private void updateTexture(){
        if(counter == toAct)setRegion(texture2);
        else setRegion(texture1);
    }

    @Override
    protected void initTexture() {
//        Texture texture;
        try {
            texture1 = GDefence.getInstance().assetLoader.get("Path/Bridge/bridge" + inputWay.getShortName() +
                    endWay1.getShortName() + endWay2.getShortName() + "1.png", Texture.class);
            texture2 = GDefence.getInstance().assetLoader.get("Path/Bridge/bridge" + inputWay.getShortName() +
                    endWay1.getShortName() + endWay2.getShortName() + "2.png", Texture.class);
        }catch (IllegalArgumentException e){
            e.printStackTrace();////may not work
        }
        updateTexture();

//        switch (inputWay){
//            case LEFT:
//                if(endWay1 == Way.UP && endWay2 == Way.DOWN){
//                    texture = GDefence.getInstance().assetLoader.get("Path/Bridge/bridgeLUD" + getActNumber() + ".png", Texture.class);
//                    break;
//                }
//                break;
//            case RIGHT:
//                if(endWay1 == Way.UP && endWay2 == Way.DOWN){
//                    texture = GDefence.getInstance().assetLoader.get("Path/Bridge/bridgeRUD" + getActNumber() + ".png", Texture.class);
//                    break;
//                }
//                break;
//            case UP:
//                if(endWay1 == Way.LEFT && endWay2 == Way.RIGHT){
//                    texture = GDefence.getInstance().assetLoader.get("Path/Bridge/bridgeULR" + getActNumber() + ".png", Texture.class);
//                    break;
//                }
//                if(endWay1 == Way.UP && endWay2 == Way.LEFT){
//                    texture = GDefence.getInstance().assetLoader.get("Path/Bridge/bridgeUUL" + getActNumber() + ".png", Texture.class);
//                    break;
//                }
//                if(endWay1 == Way.UP && endWay2 == Way.RIGHT){
//                    texture = GDefence.getInstance().assetLoader.get("Path/Bridge/bridgeUUR" + getActNumber() + ".png", Texture.class);
//                    break;
//                }
//                break;
//            case DOWN:
//                if(endWay1 == Way.LEFT && endWay2 == Way.RIGHT){
//                    texture = GDefence.getInstance().assetLoader.get("Path/Bridge/bridgeDLR" + getActNumber() + ".png", Texture.class);
//                    break;
//                }
//                if(endWay1 == Way.DOWN && endWay2 == Way.LEFT){
//                    texture = GDefence.getInstance().assetLoader.get("Path/Bridge/bridgeDDL" + getActNumber() + ".png", Texture.class);
//                    break;
//                }
//                if(endWay1 == Way.DOWN && endWay2 == Way.RIGHT){
//                    texture = GDefence.getInstance().assetLoader.get("Path/Bridge/bridgeDDR" + getActNumber() + ".png", Texture.class);
//                    break;
//                }
//                break;
//            default: throw new IllegalArgumentException("inputWay cannot be " + inputWay);
//        }
//        setRegion(texture);
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
        return false;
    }

    public Way manipulatePath(Mob enterMob){
        Way w = counter == toAct?endWay2:endWay1;//2:1 or 1:2 ???
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
        if(counter < toAct) counter++;
        else counter = 0;
        updateTexture();
        return w;
    }
}
