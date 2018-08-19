package com.darkhouse.gdefence.Level.Path;


import com.badlogic.gdx.graphics.Texture;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Mob.Way;

public class Bridge extends WalkableMapTile/* implements Walkable*/{
    private int toAct;
    private int counter;
    private int state;
//    private int crossCounter;
    private Way inputWay;
    private Way endWay1;
    private Way endWay2;
    public Texture[] textures;
//    public Texture texture1;
//    public Texture texture2;
    private TargetType applyMobs;

    @Override
    public TargetType getApplyMobs() {
        return applyMobs;
    }


    public Bridge(Way inputWay, Way endWay1, Way endWay2, TargetType applyMobs, int toAct) {
        this.inputWay = inputWay;
        this.endWay1 = endWay1;
        this.endWay2 = endWay2;
//        System.out.println("n " + endWay1 + " " + endWay2);
        this.applyMobs = applyMobs;
        this.toAct = toAct;
        textures = new Texture[2];
//        initTexture();
    }
    public Bridge(TargetType applyMobs, int toAct){//cross 4-ways
        this.applyMobs = applyMobs;
        this.toAct = toAct;
        textures = new Texture[4];
//        System.out.println("4w " + endWay1 + " " + endWay2);
    }

    private void updateTexture(){
        setRegion(textures[state]);
//        if(counter == toAct)setRegion(texture2);
//        else setRegion(texture1);
    }

//    private String getTextureName(Way endWay1, Way endWay2){
//        if      (endWay1 == Way.DOWN && endWay2 == Way.RIGHT || endWay1 == Way.RIGHT && endWay2 == Way.DOWN){
//            return "DR";
//        }else if(endWay1 == Way.RIGHT && endWay2 == Way.UP || endWay1 == Way.UP && endWay2 == Way.RIGHT){
//            return "UR";
//        }else if(endWay1 == Way.DOWN && endWay2 == Way.UP || endWay1 == Way.UP && endWay2 == Way.DOWN) {
//            return "DU";
//        }else if(endWay1 == Way.LEFT && endWay2 == Way.RIGHT || endWay1 == Way.RIGHT && endWay2 == Way.LEFT) {
//            return "LR";
//        }
//        return null;
//    }

    @Override
    public void initTexture() {
//        Texture texturePath;
        try {
//            System.out.println("n " + endWay1 + " " + endWay2);
            if(endWay2 == null){//endWay1
                textures[0] = GDefence.getInstance().assetLoader.get("Path/Bridge/bridgeCrossL.png", Texture.class);
                textures[1] = GDefence.getInstance().assetLoader.get("Path/Bridge/bridgeCrossU.png", Texture.class);
                textures[2] = GDefence.getInstance().assetLoader.get("Path/Bridge/bridgeCrossR.png", Texture.class);
                textures[3] = GDefence.getInstance().assetLoader.get("Path/Bridge/bridgeCrossD.png", Texture.class);
                updateTexture();
                return;
            }
//            texture1 = GDefence.getInstance().assetLoader.get("Path/roadHorizontal.png", Texture.class);
//            texture2 = GDefence.getInstance().assetLoader.get("Path/roadHorizontal.png", Texture.class);
//            String scnd = inputWay.getShortName() + getTextureName(endWay1, endWay2);
            String sc1 = Turn.getTurnCode(inputWay, endWay1);
            String sc2 = Turn.getTurnCode(inputWay, endWay2);
//            System.out.println(scnd);
            if (!isSwimmable()) {
                textures[0] = GDefence.getInstance().assetLoader.get("Path/Bridge/bridge" + sc1 + endWay2.getShortName() + ".png", Texture.class);
                textures[1] = GDefence.getInstance().assetLoader.get("Path/Bridge/bridge" + sc2 + endWay1.getShortName() + ".png", Texture.class);
//                texture1 = GDefence.getInstance().assetLoader.get("Path/Bridge/bridge" + scnd + "1.png", Texture.class);
//                texture2 = GDefence.getInstance().assetLoader.get("Path/Bridge/bridge" + scnd + "2.png", Texture.class);
            }else {
                textures[0] = GDefence.getInstance().assetLoader.get("Path/Bridge/waterBridge" + sc1 + ".png", Texture.class);
                textures[1] = GDefence.getInstance().assetLoader.get("Path/Bridge/waterBridge" + sc2 + ".png", Texture.class);
            }
//            System.out.println(texture2);
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



    public Way manipulatePath(Mob.MoveType enterMobType, Way currentWay){//endWays[] simplify this //TODO
        if(currentWay != inputWay) return null;
        if (applyMobs == null || applyMobs.isConsist(enterMobType)) {
            if(endWay1 == null){//cross
                Way w;// = Way.values()[crossCounter];
                switch (state){
                    case 0:w = Way.LEFT;break;
                    case 1:w = Way.UP;break;
                    case 2:w = Way.RIGHT;break;
                    case 3:w = Way.DOWN;break;
                    default:w = null;
                }
                if (state < 3) state++;
                else state = 0;
                setRegion(textures[state]);
                return w;
            }

            Way w = calculateWay();

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
            switchTurn();


//            updateTexture();//pre level not need //TODO
            return w;
        }
        return null;
    }
    protected Way calculateWay(){
        return counter == toAct ? endWay2 : endWay1;//2:1 or 1:2 ???
    }

    protected void switchTurn(){
        if (counter < toAct) {
            counter++;
            if (counter == toAct){
                if (state < 1) state++;
                else state = 0;
//                    setRegion(textures[state]);
            }
        } else {
            counter = 0;
            if (state < 1) state++;
            else state = 0;
//                setRegion(textures[state]);
        }
        setRegion(textures[state]);
    }







}
