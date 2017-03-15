package com.darkhouse.gdefence;

//import ru.Towers.TowerObject;
//import ru.Towers.TowerType;

import com.badlogic.gdx.Gdx;
import com.darkhouse.gdefence.InventorySystem.inventory.Inventory;
import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;
import com.darkhouse.gdefence.Objects.DetailObject;
import com.darkhouse.gdefence.Objects.Recipe;
import com.darkhouse.gdefence.Objects.SpellObject;
import com.darkhouse.gdefence.Objects.TowerObject;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class User {
    public int getTotalExp() {
        return totalExp;
    }
//    public boolean[] getlevelsAvailable() {
//        return levelsAvailable;
//    }
    public int getLevelsCompletedInt() {
        int completed = 0;
        for (boolean b: levelsCompleted){
            if(b){
                completed++;
            }
        }
        return completed;
    }

    public void setLevelsCompleted(int number) {
        for (int i = 0; i < number; i++){
            levelsCompleted[i] = true;
        }
    }


    public String getName() {
        return name;
    }

    public void setTotalExp(int exp) {
        this.totalExp = exp;
    }
//    public void setLevelsCompleted(boolean[] levelsAvailable) {
//        this.levelsAvailable = levelsCompleted;
//    }

    public int getLevel() {
        return level;
    }

    public int getCurrentExp() {
        return currentExp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentMap() {
        return currentMap;
    }

    public int getGold() { return gold; }

    public boolean getLevelCompleted(int numberLevel) {
        if(numberLevel > 0) {
            return levelsCompleted[numberLevel - 1];//array
        }else {
            Gdx.app.log("Error", "Wrong level number");
            return false;//can do throw exception
        }
    }
    public void setLevelCompleted(int numberLevel) {
        if(numberLevel > 0) {
            levelsCompleted[numberLevel - 1] = true;//array
        }else {
            Gdx.app.log("Error", "Wrong level number");
        }
    }

    public boolean getLevelAvailable(int number) {
        return levelsAvailable[number - 1];//array
    }

    private String name;
    private int currentMap;
    private boolean[] levelsCompleted = new boolean[30];////
    private boolean[] levelsAvailable = new boolean[30];////

    private String getLevelsCompletedSavecode(){
        String s = "";
        for (boolean b:levelsCompleted){
            s+= b + "-";
        }
        return s;
    }

    private String getLevelAvailableSavecode(){
        String s = "";
        for (boolean b:levelsAvailable){
            s+= b + "-";
        }
        return s;
    }
    private void loadLevelsCompleted(String savecode){
        String[] s = savecode.split("-");
        for (int i = 0; i < s.length; i++){
            levelsCompleted[i] = Boolean.parseBoolean(s[i]);
        }
    }
    private void loadLevelsAvailable(String savecode){
        String[] s = savecode.split("-");
        for (int i = 0; i < s.length; i++){
            levelsAvailable[i] = Boolean.parseBoolean(s[i]);
        }
    }

    public void openLevel(int number) {
        levelsAvailable[number - 1] = true;//because array
    }


    public class Gradable{
        private int maxLevel;
        private int currentLevel = 1;
        private int[] grades;
        private int[] cost;
        private String name;

        public String getName() {
            return name;
        }

        public int getCurrentValue() {
            return grades[currentLevel - 1];
        }
        public int getNextValue(){
            if(currentLevel != maxLevel)
            return grades[currentLevel];
            else return 0;//////
        }

        public int getNextCost(){
            if(currentLevel != maxLevel) {
                return cost[currentLevel - 1];
            }
            return 0;///////
        }

        public int getCurrentLevel() {
            return currentLevel;
        }
        public boolean isMaxLevel(){
            return currentLevel == maxLevel;
        }

        public void up(){
            if(currentLevel!= maxLevel) currentLevel++;
        }



        public Gradable(String name, int maxLevel, int firstValue, int stepGrade, int firstCost, int costStep) {
            this.name = name;
            this.maxLevel = maxLevel;
            grades = new int[maxLevel];
            cost = new int[maxLevel];
            for (int i = 0; i < grades.length; i++){
                grades[i] = firstValue + stepGrade*i;
                cost[i] = firstCost + costStep*i;
            }
        }
    }

    public Gradable maxHealth = new Gradable("Max Health", 5, 10, 5, 100, 25);
    public Gradable maxEnegry = new Gradable("Max Energy", 5, 50, 10, 100, 25);

    //public void upHealth(){
    //    addMaxHealth(5);
    //}

//    public void upEnergy(){
//        addMaxEnegry(10);
//    }

    private int totalExp;
    private int currentExp;
    private int gold;






    //private Spell[] spells;
    //private Detail[] details;


    private int level = 1;// = exp - Value.needExp2Lvl[0];

    public void addLevel() {
        level++;
        //WOW YOU LEVEL UP screen
    }
    //public TowerObject[] towers;
    //public ArrayList <Tower> towers;

    //public ArrayList <Item> items;

    private static Inventory towerInventory;//<Tower>
    private static Inventory spellInventory;//<Spell>
    private static Inventory detailInventory;//<Detail>

    public static Inventory getTowerInventory() {
        return towerInventory;
    }
    public static Inventory getSpellInventory() {
        return spellInventory;
    }
    public static Inventory getDetailInventory() {
        return detailInventory;
    }
    //    public static void setTowerInventory(Inventory towerInventory) {
//        User.towerInventory = towerInventory;
////        User.towerInventory = new Inventory(towerInventory);//
//    }
//    public static void setSpellInventory(Inventory spellInventory) {
//        User.spellInventory = spellInventory;
//    }
//    public static void setDetailInventory(Inventory detailInventory) {
//        User.detailInventory = detailInventory;
//    }

    public enum RecipeType{
        opened, canOpen, locked
    }

    private HashMap <ItemEnum.Tower, RecipeType> openedTowers;


    private void initOpenedTowers(){//default opened
        openedTowers = new HashMap<ItemEnum.Tower, RecipeType>();//for each
        openedTowers.put(ItemEnum.Tower.Basic, RecipeType.opened);
        openedTowers.put(ItemEnum.Tower.Rock, RecipeType.locked);
        openedTowers.put(ItemEnum.Tower.Arrow, RecipeType.locked);
        openedTowers.put(ItemEnum.Tower.Range, RecipeType.locked);
        openedTowers.put(ItemEnum.Tower.Short, RecipeType.locked);
        openedTowers.put(ItemEnum.Tower.Mountain, RecipeType.locked);
        openedTowers.put(ItemEnum.Tower.SteelArrow, RecipeType.locked);
        openedTowers.put(ItemEnum.Tower.Ballista, RecipeType.locked);
        openedTowers.put(ItemEnum.Tower.Catapult, RecipeType.locked);

        openRecipes();
    }

    private void loadOpenedTowers(String savecode){//can work after initialized default openedTowers map
        String[] towersTypes = savecode.split("/");
        for (String type:towersTypes){
            String[] s = type.split("-");//s[0] = key, s[1] = value
            openedTowers.put(ItemEnum.Tower.valueOf(s[0]), RecipeType.valueOf(s[1]));
        }
    }

    private String getOpenedTowersSavecode(){
        String save = "";
        for (Map.Entry<ItemEnum.Tower, RecipeType> entry: openedTowers.entrySet()){
            save += entry.getKey().name() + "-" + entry.getValue().name() + "/";
        }
        return save;
    }


    private void unlockRecipe(ItemEnum.Tower t){
        if(getOpenType(t) == RecipeType.locked) openedTowers.put(t, RecipeType.canOpen);
    }
    public RecipeType getOpenType(ItemEnum.Tower t){//name
        return openedTowers.get(t);
    }
    private boolean isOpened(ItemEnum.Tower t){
        return getOpenType(t) == RecipeType.opened;
    }
    public void buyTowerRecipe(ItemEnum.Tower t){
        getDetailInventory().store(new Recipe(t));
        openedTowers.put(t, RecipeType.opened);
        openRecipes();
    }
    private void openRecipes(){//rework with getComponents
        if(isOpened(ItemEnum.Tower.Basic)){
            unlockRecipe(ItemEnum.Tower.Rock);
            unlockRecipe(ItemEnum.Tower.Arrow);
            unlockRecipe(ItemEnum.Tower.Range);
        }
        if(isOpened(ItemEnum.Tower.Rock)){
            unlockRecipe(ItemEnum.Tower.Short);
            unlockRecipe(ItemEnum.Tower.Mountain);
        }
        if(isOpened(ItemEnum.Tower.Arrow)){
            unlockRecipe(ItemEnum.Tower.SteelArrow);
        }
        if(isOpened(ItemEnum.Tower.Arrow) && isOpened(ItemEnum.Tower.Range)){
            unlockRecipe(ItemEnum.Tower.Ballista);
        }
        if(isOpened(ItemEnum.Tower.Rock) && isOpened(ItemEnum.Tower.Range)){
            unlockRecipe(ItemEnum.Tower.Catapult);
        }



        //if() etc
    }

    /*
    public boolean addTower(TowerObject o){
        boolean b;
        b = towers.add(o);
//        System.out.println(towers.size());
//        System.out.println(towers.get(towers.size() - 1).getName());
        return b;
    }

    public boolean removeTower(TowerObject o){
        boolean b;
//        System.out.println(towers.indexOf(o));//o).getName());
        b = towers.remove(o);

//        System.out.println(towers.size());
        return b;
    }
    */

//    public int[] getGemsNumbers(){
//        return new int[]{redGems, yellowGems, blueGems, blackGems, greenGems, whiteGems};
//    }

    public enum GEM_TYPE{
        RED, YELLOW, BLUE, BLACK, GREEN, WHITE;


        public static int getBoost(GEM_TYPE type){
            switch (type){
                case RED:
                    return 5;//dmg
                case YELLOW:
                    return 10;//as
                case BLUE:
                    return 20;//range
                case BLACK:
                    //
                case GREEN:
                    //
                case WHITE:
                    //
            }
            return 0;
        }

    }

    public int getGemNumber(GEM_TYPE t){
        return gems[t.ordinal()];

//        switch (t){
//            case RED:
//                return redGems;
//            case YELLOW:
//                return yellowGems;
//            case BLUE:
//                return blueGems;
//            case BLACK:
//                return blackGems;
//            case GREEN:
//                return greenGems;
//            case WHITE:
//                return whiteGems;
//        }
//        return 0;
    }

    public boolean spendGems(GEM_TYPE t, int number){
        if(getGemNumber(t) >= number){
            gems[t.ordinal()] -= number;
            return true;
        }
        return false;

//        switch (t){
//            case RED:
//                if(redGems >= number){
//                    redGems -= number;
//                    return true;
//                }
//                break;
//            case YELLOW:
//                if(yellowGems >= number){
//                    yellowGems -= number;
//                    return true;
//                }
//                break;
//            case BLUE:
//                if(blueGems >= number){
//                    blueGems -= number;
//                    return true;
//                }
//                break;
////            case BLACK:
////                return blackGems;
////            case GREEN:
////                return greenGems;
////            case WHITE:
////                return whiteGems;
//        }
//        return false;
    }
    private int gems[];

    private String getGemsSavecode(){
        String gemsSave = "";
        for (int gem : gems) {
            gemsSave += gem + "-";
        }
        return gemsSave;
    }

//    private int redGems;
//    private int yellowGems;
//    private int blueGems;
//    private int blackGems;
//    private int greenGems;
//    private int whiteGems;

    public User() {
        gems = new int[GEM_TYPE.values().length];//6
        towerInventory = new Inventory(TowerObject.class, 35);
        spellInventory = new Inventory(SpellObject.class, 35);
        detailInventory = new Inventory(DetailObject.class, 35);
        initOpenedTowers();
    }
//    public void init(){//
//        File f = new File("Save/UserSave.properties");
//        if(f.exists()) load();
//        else initNewUser();
//    }

    public void initNewUser(){
        GDefence.getInstance().log("Init new User");
        flush();
        this.totalExp = 0;
        addGold(3000);
        currentMap = 1;
//        towerInventory = new Inventory(TowerObject.class, 35);
//        spellInventory = new Inventory(SpellObject.class, 35);
//        detailInventory = new Inventory(DetailObject.class, 35);
        towerInventory.storeNew(ItemEnum.Tower.Basic, 1);
        ((TowerObject) towerInventory.getSlots().get(0).getLast()).addGems(GEM_TYPE.RED, 1);
        ((TowerObject) towerInventory.getSlots().get(0).getLast()).addGems(GEM_TYPE.YELLOW, 1);
        ((TowerObject) towerInventory.getSlots().get(0).getLast()).addGems(GEM_TYPE.BLUE, 3);
        towerInventory.storeNew(ItemEnum.Tower.Rock, 1);
        ((TowerObject) towerInventory.getSlots().get(1).getLast()).addGems(GEM_TYPE.RED, 4);
        ((TowerObject) towerInventory.getSlots().get(1).getLast()).addGems(GEM_TYPE.YELLOW, 1);
        ((TowerObject) towerInventory.getSlots().get(1).getLast()).addGems(GEM_TYPE.BLUE, 1);
        towerInventory.storeNew(ItemEnum.Tower.Arrow, 1);
        ((TowerObject) towerInventory.getSlots().get(2).getLast()).addGems(GEM_TYPE.RED, 2);
        ((TowerObject) towerInventory.getSlots().get(2).getLast()).addGems(GEM_TYPE.YELLOW, 2);
        ((TowerObject) towerInventory.getSlots().get(2).getLast()).addGems(GEM_TYPE.BLUE, 2);
        towerInventory.storeNew(ItemEnum.Tower.Range, 1);
        ((TowerObject) towerInventory.getSlots().get(3).getLast()).addGems(GEM_TYPE.RED, 2);
        ((TowerObject) towerInventory.getSlots().get(3).getLast()).addGems(GEM_TYPE.YELLOW, 2);
        ((TowerObject) towerInventory.getSlots().get(3).getLast()).addGems(GEM_TYPE.BLUE, 1);
//        detailInventory.storeNew(new Recipe(ItemEnum.Tower.Range));

//        initOpenedTowers();

        gems[0] = 3;
        gems[1] = 2;
        gems[2] = 4;
        gems[3] = 1;
        gems[4] = 9;
        gems[5] = 41;

        update();

//        this.levelsAvailable[0] = true;
        openLevel(1);
        openLevel(2);
        openLevel(3);
        openLevel(4);
        openLevel(5);
        openLevel(6);
        openLevel(7);
//        levelsAvailable[5] = true;
//        levelsAvailable[6] = true;
//        levelsAvailable[4] = true;
//        levelsCompleted[4] = true;
//        setLevelCompleted(5);
//        for(int i = 1; i < 5; i++) {//test this
//            this.levelsAvailable[i] = false;
//        }

        GDefence.getInstance().log("New User created");
    }




    public void update(){//rework
//        currentExp = getTotalExp();
//        for(int i = level - 1; currentExp >= Value.needExp2Lvl[i] ; i++){
//            currentExp -= Value.needExp2Lvl[i];
//            System.out.println(level + " " + currentExp);
//            addLevel();
//        }
        int i = 0;
//        System.out.println(totalExp);
        while (totalExp >= Value.needExp2Lvl[i]){
            i++;
        }
        int newLevel = 0;
        if(i != 0) {
            currentExp = totalExp % Value.needExp2Lvl[i - 1];
            newLevel = i + 1;
        }else {
            currentExp = totalExp;
        }
        for (int j = 0; j <= newLevel - getLevel(); j++){
            addLevel();
        }
//        System.out.println(getLevel());


    }


    public int getExp2NextLvl(){
        return Value.needExp2Lvl[level - 1] - currentExp;
    }
    public int getMaxExpThisLvl(){
        int exp;
        if (level < 2){
            exp = Value.needExp2Lvl[level - 1];
        }else {
            exp = Value.needExp2Lvl[level - 1] - Value.needExp2Lvl[level - 2];
        }

        return exp;
    }
    public void addExp(final int value){
        totalExp += value;
        update();
    }
    public void addGold(final int value) { gold += value; }
    public boolean deleteGold(final int value) {
        if (gold >= value) {
            gold -= value;
            return true;
        }else return false;
    }
    public void flush(){
        GDefence.getInstance().log("User flush");
        gold = 0;
        totalExp = 0;
        currentExp = 0;
        for (int i = 0; i < gems.length; i++){
            gems[i] = 0;
        }
        getTowerInventory().flush();
        getSpellInventory().flush();
        getDetailInventory().flush();
        initOpenedTowers();//default towers
        for (int i = 0; i < levelsAvailable.length; i++){
            levelsAvailable[i] = false;
        }
        for (int i = 0; i < levelsCompleted.length; i++){
            levelsCompleted[i] = false;
        }

        //etc
    }

    public void save(){
        GDefence.getInstance().log("Saving");
        //File saveFile = new File("UserSave");
        //
        File f = new File("Save/UserSave.properties");
        try {
//            if(f.exists()) {
//                f.delete();
//            }
            f.createNewFile();
            Properties prop = new Properties();
            FileOutputStream fs = new FileOutputStream(f);

            prop.put("gold", getGold() + "");
            prop.put("totalExp", getTotalExp() + "");
            prop.put("towerInventory", getTowerInventory().getSave());
//            prop.put("spellInventory", getSpellInventory().getSave());
            prop.put("detailInventory", getDetailInventory().getSave());
            prop.put("openedTowers", getOpenedTowersSavecode());
            prop.put("gems", getGemsSavecode());
            prop.put("levelsCompleted", getLevelsCompletedSavecode());
            prop.put("levelAvailable", getLevelAvailableSavecode());



            prop.store(fs, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        GDefence.getInstance().log("Saved");
    }

    public boolean load(){
        GDefence.getInstance().log("Loading");
        try {
            //InputStream in = Files.newInputStream(loadFile);
            //BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            File loadFile = new File("Save/UserSave.properties");
//            Scanner sc = new Scanner(loadFile);

            Properties prop = new Properties();
            FileInputStream fs = new FileInputStream(loadFile);
            prop.load(fs);
            flush();//delete current user info

            gold = Integer.parseInt(prop.getProperty("gold"));
            totalExp = Integer.parseInt(prop.getProperty("totalExp"));
//            String towerInvLoad = prop.getProperty("towerInventory");
            String towers[] = prop.getProperty("towerInventory").split("/");
            for (String t:towers){
                String[] info = t.split("-", 2);//info[0] - numberSlot, info[1] - savecode
                if(info.length > 1) getTowerInventory().store(TowerObject.loadSaveCode(info[1]), Integer.parseInt(info[0]));
            }
//            String detailInvLoad = prop.getProperty("detailInventory");
            String details[] = prop.getProperty("detailInventory").split("/");
            for (String t:details){
                String[] info = t.split("-", 2);//info[0] - numberSlot, info[1] - savecode
                if(info.length > 1) getDetailInventory().store(DetailObject.loadSaveCode(info[1]), Integer.parseInt(info[0]));

            }
            loadOpenedTowers(prop.getProperty("openedTowers"));
            String[] gemsSave = prop.getProperty("gems").split("-");
            for (int i = 0; i < gems.length; i++){
                gems[i] = Integer.parseInt(gemsSave[i]);
            }
            loadLevelsCompleted(prop.getProperty("levelsCompleted"));
            loadLevelsAvailable(prop.getProperty("levelAvailable"));

//            System.out.println(gold + " " + totalExp);



//            gold = sc.nextInt();
//            totalExp = sc.nextInt();
//            //level = sc.nextInt();
//            setLevelsCompleted(sc.nextInt());
//            redGems = sc.nextInt();
//            yellowGems = sc.nextInt();
//            blueGems = sc.nextInt();
//            blackGems = sc.nextInt();
//            greenGems = sc.nextInt();
//            whiteGems = sc.nextInt();



            update();
            GDefence.getInstance().log("User loaded");

            return true;//TODO

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
