package com.darkhouse.gdefence;

//import ru.Towers.TowerObject;
//import ru.Towers.TowerType;

import com.badlogic.gdx.Gdx;
import com.darkhouse.gdefence.InventorySystem.inventory.Inventory;
import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;
import com.darkhouse.gdefence.Objects.Recipe;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

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
    private boolean[] levelsCompleted = new boolean[100];
    private boolean[] levelsAvailable = new boolean[100];

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


    private void initOpenedTowers(){
        openedTowers = new HashMap<ItemEnum.Tower, RecipeType>();
        openedTowers.put(ItemEnum.Tower.Basic, RecipeType.opened);
        openedTowers.put(ItemEnum.Tower.Rock, RecipeType.locked);
        openedTowers.put(ItemEnum.Tower.Arrow, RecipeType.locked);
        openedTowers.put(ItemEnum.Tower.Range, RecipeType.locked);


        openRecipes();
    }
    public RecipeType isOpenedTower(ItemEnum.Tower t){
        return openedTowers.get(t);
    }
    public void buyTowerRecipe(ItemEnum.Tower t){
        getDetailInventory().store(new Recipe(t));
        openedTowers.put(t, RecipeType.opened);
        openRecipes();
    }
    private void openRecipes(){
        if(isOpenedTower(ItemEnum.Tower.Basic) == RecipeType.opened){
            openedTowers.put(ItemEnum.Tower.Rock, RecipeType.canOpen);
            openedTowers.put(ItemEnum.Tower.Arrow, RecipeType.canOpen);
            openedTowers.put(ItemEnum.Tower.Range, RecipeType.canOpen);
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

    public int[] getGemsNumbers(){
        return new int[]{redGems, yellowGems, blueGems, blackGems, greenGems, whiteGems};
    }

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
        switch (t){
            case RED:
                return redGems;
            case YELLOW:
                return yellowGems;
            case BLUE:
                return blueGems;
            case BLACK:
                return blackGems;
            case GREEN:
                return greenGems;
            case WHITE:
                return whiteGems;
        }
        return 0;
    }

    public boolean spendGems(GEM_TYPE t, int number){
        switch (t){
            case RED:
                if(redGems >= number){
                    redGems -= number;
                    return true;
                }
                break;
            case YELLOW:
                if(yellowGems >= number){
                    yellowGems -= number;
                    return true;
                }
                break;
            case BLUE:
                if(blueGems >= number){
                    blueGems -= number;
                    return true;
                }
                break;
//            case BLACK:
//                return blackGems;
//            case GREEN:
//                return greenGems;
//            case WHITE:
//                return whiteGems;
        }
        return false;
    }



    private int redGems;
    private int yellowGems;
    private int blueGems;
    private int blackGems;
    private int greenGems;
    private int whiteGems;

    private File saveFile;


    public User() {

    }

    public void init(){
        this.totalExp = 0;
        addGold(3000);
        currentMap = 1;


        //towers = new ArrayList<Tower>();




        //towers.add(new ArrowTower());
        //towers.add(new RockTower());
        //towers.add(new BasicTower());
        //items = new ArrayList<Item>();
        //items.add(Item.BATTERY);
        //items.add(Item.CANNON);
        //items.add(Item.CRYSTAL_GREEN);
        //items.add(Item.CRYSTAL_GREEN);

        towerInventory = new Inventory();
        spellInventory = new Inventory();
        detailInventory = new Inventory();
        towerInventory.store(ItemEnum.Tower.Basic, 1);
        towerInventory.store(ItemEnum.Tower.Rock, 1);
        towerInventory.store(ItemEnum.Tower.Arrow, 3);
        detailInventory.store(new Recipe(ItemEnum.Tower.Range));

        initOpenedTowers();

//        towers.add(new TowerObject(TowerType.Basic));
//        towers.add(new TowerObject(TowerType.Basic));
//        towers.add(new TowerObject(TowerType.Rocky));
//        towers.add(new TowerObject(TowerType.Range));
       /*
        addTower(new TowerObject(TowerType.Basic, 1));
        addTower(new TowerObject(TowerType.Basic, 1));
        addTower(new TowerObject(TowerType.Rocky, 1));
        addTower(new TowerObject(TowerType.Basic, 1));
        addTower(new TowerObject(TowerType.Range, 1));
        */

//        towers[0] = new TowerObject("Basic", 2);
//        towers[1] = new TowerObject("Rocky", 3);//rework
//        towers[2] = new TowerObject("Arrow", 5);
//        towers[4] = new TowerObject("Range", 4);

        redGems = 3;
        yellowGems = 2;
        blueGems = 4;
        blackGems = 1;
        greenGems = 9;
        whiteGems = 41;



        update();


        //this.levelsCompletedInt = 0;
        this.levelsAvailable[0] = true;
        for(int i = 1; i < 5; i++) {
            this.levelsAvailable[i] = false;
        }
    }


    public User(File saveFile) {
        this.saveFile = saveFile;
    }

    public void update(){
        currentExp = getTotalExp();
        for(int i = level - 1; currentExp >= Value.needExp2Lvl[i] ; i ++){
            currentExp -= Value.needExp2Lvl[i];
            level++;
        }
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

    public void save(){
        //File saveFile = new File("UserSave");
        //
        File f = new File("Save/UserSave");
        try {
            f.createNewFile();
            PrintWriter writer = new PrintWriter(f);
            writer.println(getGold());
            writer.println(getTotalExp());
            //writer.print(getCurrentExp());
            //writer.println(getLevel());
            writer.println(getLevelsCompletedInt());
            //writer.println(getLevelAvailable());
            writer.print(redGems + " " + yellowGems + " " + blueGems + " " + blackGems + " " + greenGems + " " + whiteGems);
            writer.close();
            //System.out.println(f.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean load(){
        try {
            //InputStream in = Files.newInputStream(loadFile);
            //BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            File loadFile = new File("Save/UserSave");

            Scanner sc = new Scanner(loadFile);
            gold = sc.nextInt();
            totalExp = sc.nextInt();
            //level = sc.nextInt();
            setLevelsCompleted(sc.nextInt());
            redGems = sc.nextInt();
            yellowGems = sc.nextInt();
            blueGems = sc.nextInt();
            blackGems = sc.nextInt();
            greenGems = sc.nextInt();
            whiteGems = sc.nextInt();



            update();

            return true;//TODO

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
