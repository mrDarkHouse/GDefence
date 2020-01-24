package com.darkhouse.gdefence;

//import ru.Towers.TowerObject;
//import ru.Towers.TowerType;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.InventorySystem.inventory.Inventory;
import com.darkhouse.gdefence.InventorySystem.inventory.Item;
import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;
import com.darkhouse.gdefence.Level.Ability.Spell.*;
import com.darkhouse.gdefence.Level.Ability.Tower.Ability;
import com.darkhouse.gdefence.Model.Panels.StoreBuyPanel;
import com.darkhouse.gdefence.Objects.*;
import com.darkhouse.gdefence.Screens.BottomPanel.TowerMap;

import java.io.*;
import java.util.*;

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
//            openLevel(numberLevel + 1);
            if (levelOpenOrder != null) {
                for (int i = 0; i < levelOpenOrder[numberLevel - 1].size; i++) {
//                System.out.println(levelOpenOrder[]);
                    openLevel(levelOpenOrder[numberLevel - 1].get(i) + 1);
                }
            }

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

    private Array<Integer>[] levelOpenOrder = new Array[30];


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

        public void setCurrentLevel(int currentLevel) {
            this.currentLevel = currentLevel;
            GDefence.getInstance().getSmith().notifyListeners();//dirty
        }

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

    public Gradable maxHealth = new Gradable("Max Health", 10, 5, 5, 100, 100);
    public Gradable maxEnergy = new Gradable("Max Energy", 10, 30, 10, 100, 100);

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

    public static void store(Array<? extends GameObject> objects){
        if (objects.size == 0) return;
//        System.out.println(objects.first().getClass());
        if(objects.first().getPrototype() instanceof ItemEnum.Tower){
            getTowerInventory().store(objects);
        }else if(objects.first() instanceof SpellObject){
            getSpellInventory().store(objects);
        }else if(objects.first() instanceof DetailObject){
            getDetailInventory().store(objects);
        }
    }

    private static Inventory towerInventory;//<Tower>
    private static Inventory spellInventory;//<Spell>
    private static Inventory detailInventory;//<Detail>

    private static StoreBuyPanel storePanel;
//    public static StoreBuyPanel getStorePanel() {
//        return storePanel;
//    }
    public static void setStorePanel(StoreBuyPanel storePanel) {
        User.storePanel = storePanel;
    }

    public void openTowerToBuy(ItemEnum.Tower t){
        storePanel.store(t);
//        storePanel.pack();
    }

    private int[] craftedTowers;//need to know how many towers are crafted

    private String getCraftedTowersCode(){
        String s = "";
        for (int i = 0; i < craftedTowers.length; i++){
            s += craftedTowers[i] + "/";
        }
        return s;
    }
    private void loadCraftedTowersCode(String s){
        String[] crafted = s.split("/");
        for (int i = 0; i < crafted.length; i++){
            craftedTowers[i] = Integer.parseInt(crafted[i]);
        }
//        System.out.println(Arrays.toString(craftedTowers));
    }

    public void craftTower(/*ItemEnum.Tower t*/TowerObject t){
        getTowerInventory().store(TowerObject.generateClearTower(t.getPrototype()).addAbilitiesGems(t)/*t*/);
        craftedTowers[t.getPrototype().ordinal()]++;
        if(craftedTowers[t.getPrototype().ordinal()] >= 3){//3 = NUMBER TO AVAILABLE TOWER
//            if(getOpenType(t) != RecipeType.available){
                setAvailableTower(t.getPrototype());
//            }
//            openTowerToBuy(t);//
        }
    }
    public void loadAvailableTowers(){//TODO sort by tower id/tier(cost)
        for (Map.Entry<ItemEnum.Tower, RecipeType> entry: openedTowers.entrySet()){
            if(entry.getValue() == RecipeType.available){
                storePanel.store(entry.getKey());
            }
        }
//        for (int i = 0; i < openedTowers.entrySet(); i++){
//
//        }
    }

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



    private void loadOpenedTowers(String savecode){//can work after initialized default openedTowers map
        String[] towersTypes = savecode.split("/");
        for (String type:towersTypes){
            String[] s = type.split("-");//s[0] = key, s[1] = value
            openedTowers.put(ItemEnum.Tower.valueOf(s[0]), RecipeType.valueOf(s[1]));
        }
    }
    private void loadOpenedResearches(String savecode){
        String[] towersTypes = savecode.split("/");
        for (String type:towersTypes){
            String[] s = type.split("-");
//            System.out.println(s[0] + " ");
            researches.put(Research.valueOf(s[0]), Boolean.valueOf(s[1]));
        }
    }

    private String getOpenedTowersSavecode(){
        String save = "";
        for (Map.Entry<ItemEnum.Tower, RecipeType> entry: openedTowers.entrySet()){
            save += entry.getKey().name() + "-" + entry.getValue().name() + "/";
        }
        return save;
    }

    public enum Research implements Item{
        Powder(/*"powder", "Complete 1 map" + System.getProperty("line.separator") + "to open this research"*/),
        Mech(/*"steam", "Beat boss on second map" + System.getProperty("line.separator") +  "to open this research"*/);

        private String tooltip;
//        private String texturePath;

        public String getTexturePath() {
            return name().toLowerCase();//
        }



        Research(/*String texturePath, String tooltip*/) {
//            this.texturePath = texturePath;
//            this.tooltip = GDefence.getInstance().assetLoader.getWord(name().toLowerCase() + "_tooltip");
//            this.tooltip = tooltip;
        }
        public static void init(){
            for (int i = 0; i < Research.values().length; i++){
//                Research.values()[i].initTooltips();
            }
        }

//        private void initTooltips(){
//            this.tooltip = GDefence.getInstance().assetLoader.getWord(name().toLowerCase() + "_tooltip") + System.getProperty("line.separator") +
//                           GDefence.getInstance().assetLoader.getWord("research_complete_need");
//        }

        @Override
        public String getTextureRegion() {
            return getTexturePath();
        }

        public int getRecipeCost() {
            return 0;
        }

        @Override
        public int getGlobalCost() {
            return 0;
        }

        @Override
        public int getID() {
            return ordinal() + 120;
        }

        public String getName(){
            return GDefence.getInstance().assetLoader.getWord(name().toLowerCase()) + " (" + GDefence.getInstance().assetLoader.getWord("research") + ")";
        }

        public String getTooltip() {
            return GDefence.getInstance().assetLoader.getWord(name().toLowerCase() + "_tooltip") + System.getProperty("line.separator") +
                    GDefence.getInstance().assetLoader.getWord("research_complete_need");
        }


    }

    private HashMap <Research, Boolean> researches;

    private TowerMap towerMap;

    public void setTowerMap(TowerMap towerMap) {
        this.towerMap = towerMap;
    }

    private void initResearches(){
        researches = new HashMap<Research, Boolean>();

        for (Research r: Research.values()){
            researches.put(r, false);
        }
//        openResearch(Research.Powder);

//        openRecipes();
    }

    public boolean isResearchOpened(Research r){
        return researches.get(r);
    }
    public void openResearch(Research r){
        researches.put(r, true);
        if(towerMap != null) towerMap.updateTypes(); //==null when open researches in start(in debug)
        openRecipes();//
    }
    private String getResearchCode(){
        String save = "";
        for (Map.Entry<Research, Boolean> entry: researches.entrySet()){
            save += entry.getKey().name() + "-" + entry.getValue().toString() + "/";
        }
        return save;
    }


    public enum RecipeType{
        opened, canOpen, locked, available;
    }

    private HashMap <ItemEnum.Tower, RecipeType> openedTowers;

    private void initOpenedTowers(){//default opened
        openedTowers = new HashMap<ItemEnum.Tower, RecipeType>();

        for (ItemEnum.Tower t: ItemEnum.Tower.values()){
            openedTowers.put(t, RecipeType.locked);
        }
        openedTowers.put(ItemEnum.Tower.Basic, RecipeType.opened);

        openRecipes();
    }
    private void initLevelsOpenOrder(){
        try {
            File loadFile = new File("Maps/OpenOrder.txt");
            Scanner sc = new Scanner(loadFile);
//            levelOpenOrder = new Array<Integer>[30];
            while (sc.hasNextLine()){
                String s = sc.nextLine();
                String[] arg = s.split("=");
                if(arg.length > 1) {
                    String[] open = arg[1].split(",");
                    levelOpenOrder[Integer.parseInt(arg[0])] = new Array<Integer>();
                    for (String anOpen : open) {
                        levelOpenOrder[Integer.parseInt(arg[0])].add(Integer.parseInt(anOpen));
                    }
                }
            }
//            System.out.println(Arrays.toString(levelOpenOrder));


//            Properties prop = new Properties();
//            FileInputStream fs = new FileInputStream(loadFile);
//            prop.load(fs);





        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    private void unlockRecipe(ItemEnum.Tower t){
        if(getOpenType(t) == RecipeType.locked) openedTowers.put(t, RecipeType.canOpen);
    }
    private void setAvailableTower(ItemEnum.Tower t){
        if(getOpenType(t) == RecipeType.opened) {
            openedTowers.put(t, RecipeType.available);
            openTowerToBuy(t);
        }
    }
    public RecipeType getOpenType(ItemEnum.Tower t){//texturePath
        return openedTowers.get(t);
    }
    private boolean isOpened(ItemEnum.Tower t){
        return getOpenType(t) == RecipeType.opened || getOpenType(t) == RecipeType.available;
    }
    public void buyTowerRecipe(ItemEnum.Tower t){
//        if(t == ItemEnum.Tower.Range) openTowerToBuy(ItemEnum.Tower.Range);//debug tool
//        if(t == ItemEnum.Tower.Arrow) openResearch(Research.Powder);
        getDetailInventory().store(new Recipe(t));
        openedTowers.put(t, RecipeType.opened);
        openRecipes();
    }
    private void openRecipes(){
        for (ItemEnum.Tower t: ItemEnum.Tower.values()){
            boolean open = true;
            for (TowerObject o : t.getComponents()) {
                if (!isOpened(o.getPrototype())) open = false;
            }
            for (Research r: t.getResearchNeed()){
                if(!isResearchOpened(r)) open = false;
            }
            if(open) unlockRecipe(t);
        }
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

    public enum GEM_TYPE implements Item{
        RED("redGem"), YELLOW("yellowGem"), BLUE("blueGem"), BLACK("blackGem"), GREEN("greenGem"), WHITE("whiteGem");

        private final String texturePath;

        public String getTexturePath() {
            return texturePath;
        }

        GEM_TYPE(String texturePath) {
            this.texturePath = texturePath;
        }

        public static boolean isTowerGem(GEM_TYPE g){
            return g == RED || g == YELLOW || g == BLUE;
        }

        public static int getBoost(GEM_TYPE type){
            switch (type){
                case RED:
                    return 5;//dmg
                case YELLOW:
                    return 5;//as
                case BLUE:
                    return 10;//range
                case BLACK:
                    //
                case GREEN:
                    //
                case WHITE:
                    //
            }
            return 0;
        }

        @Override
        public String getTextureRegion() {
            return texturePath;
        }

        public int getRecipeCost() {
            return 0;
        }

        @Override
        public int getGlobalCost() {
            return 0;
        }


        @Override
        public int getID() {
            return ordinal() + 1;
        }

        public String getName(){
            return GDefence.getInstance().assetLoader.getWord(name().toLowerCase()) + " " + GDefence.getInstance().assetLoader.getWord("gem");
        }

        @Override
        public String getTooltip() {
            switch (this){
                case RED:case YELLOW:case BLUE:
//                    System.out.println("a");// + GDefence.getInstance().assetLoader.getWord("rybGemTooltip"));
                    return GDefence.getInstance().assetLoader.getWord("rybGemTooltip");
                case BLACK:case GREEN:case WHITE:
//                    System.out.println("b");// + GDefence.getInstance().assetLoader.getWord("bgwGemTooltip"));
                    return GDefence.getInstance().assetLoader.getWord("bgwGemTooltip");
                default:return "smth goes wrong";//throw wrongArgumentException
            }
        }
    }

    public int getGemNumber(GEM_TYPE t){
        return gems[t.ordinal()];
    }

    public boolean spendGems(GEM_TYPE t, int number){
        if(getGemNumber(t) >= number){
            gems[t.ordinal()] -= number;
            return true;
        }
        return false;
    }
    public void addGems(GEM_TYPE t, int number){
        gems[t.ordinal()] += number;
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
//        storePanel = new StoreBuyPanel(new StoreBuyInventory());

        craftedTowers = new int[ItemEnum.Tower.values().length];

        initResearches();
        initOpenedTowers();
        initLevelsOpenOrder();
    }
//    public void init(){//
//        File f = new File("Save/UserSave.properties");
//        if(f.exists()) load();
//        else initNewUser();
//    }

    public boolean isDebug = true;

    public void initNewUser(){
        GDefence.getInstance().log("Init new User");
//        GDefence.getInstance().user = new User();
//        flush();
//        this.totalExp = 0;
//        addGold(7000);
        addGold(160);
//        addGold(11160);
        currentMap = 1;

//        maxHealth = new Gradable("Max Health", 5, 5, 5, 100, 25);
//        maxEnergy = new Gradable("Max Energy", 5, 30, 10, 100, 25);

//        openResearch(Research.Powder);
//        openResearch(Research.Mech);
//        towerInventory = new Inventory(TowerObject.class, 35);
//        spellInventory = new Inventory(SpellObject.class, 35);
//        detailInventory = new Inventory(DetailObject.class, 35);

        setStorePanel(GDefence.getInstance().getStore().getStoreBuyPanel());
        setTowerMap(GDefence.getInstance().getArsenal().getTowerMap());
        openTowerToBuy(ItemEnum.Tower.Basic);


        if(isDebug) {
            addGold(51160);
            openResearch(Research.Powder);
            openResearch(Research.Mech);
            towerInventory.store(new TowerObject(ItemEnum.Tower.Glaive, 2, 2, 2));
//            towerInventory.store(new TowerObject(ItemEnum.Tower.SteamMachine, 0, 0, 0));
            gems[0] = 7;
            gems[1] = 7;
            gems[2] = 8;
            gems[3] = 10;
            gems[4] = 9;
            gems[5] = 41;
//            openLevel(2);
//            openLevel(3);
//            openLevel(4);
//            openLevel(5);
//            openLevel(6);
//            openLevel(7);
//            openLevel(8);


//            spellInventory.store(new EchoSmash.P(5, 5, 5, 2f, 100, new EchoSmash.G(5, 1f, new int[]{2, 2, 2})));
//            spellInventory.store(new GlobalSlow.P(5, 15, 0.3f, 5, new GlobalSlow.G(0.1f, 1, 4, new int[]{3, 3, 0})));
            spellInventory.store(new IceBlast.P(5, 10, 10, 0.3f, 3, 200, new IceBlast.G(5, 0.1f, 1, new int[]{3, 2, 3})));
            spellInventory.store(new EmergencyRepair.P(5, 15, 3, new EmergencyRepair.G(2, new int[]{3, 0, 0})));
//            spellInventory.store(new SuddenDeath.P(5, 10));


        }



//        openRecipes();//need to init
//        System.out.println(towerMap);
//        System.out.println(towerMap);


//        towerInventory.storeNew(ItemEnum.Tower.Basic, 1);



//        towerInventory.store(new TowerObject(ItemEnum.Tower.Basic, 0, 0, 0));
//        towerInventory.store(new TowerObject(ItemEnum.Tower.Range, 0, 1, 1));
//        towerInventory.store(new TowerObject(ItemEnum.Tower.Basic, 3, 1, 1));
//        towerInventory.store(new TowerObject(ItemEnum.Tower.Basic, 3, 1, 1));

//        ((TowerObject) towerInventory.getSlots().get(0).getLast()).addExp(500f);
//        ((TowerObject) towerInventory.getSlots().get(0).getLast()).addGems(GEM_TYPE.RED, 3);
//        ((TowerObject) towerInventory.getSlots().get(0).getLast()).addGems(GEM_TYPE.YELLOW, 1);
//        ((TowerObject) towerInventory.getSlots().get(0).getLast()).addGems(GEM_TYPE.BLUE, 1);
////        towerInventory.storeNew(ItemEnum.Tower.Basic, 1);
//        ((TowerObject) towerInventory.getSlots().get(2).getLast()).addGems(GEM_TYPE.RED, 3);
//        ((TowerObject) towerInventory.getSlots().get(2).getLast()).addGems(GEM_TYPE.YELLOW, 1);
//        ((TowerObject) towerInventory.getSlots().get(2).getLast()).addGems(GEM_TYPE.BLUE, 1);
////        towerInventory.storeNew(ItemEnum.Tower.Rock, 1);
//        ((TowerObject) towerInventory.getSlots().get(1).getLast()).addGems(GEM_TYPE.RED, 3);
//        ((TowerObject) towerInventory.getSlots().get(1).getLast()).addGems(GEM_TYPE.YELLOW, 1);
//        ((TowerObject) towerInventory.getSlots().get(1).getLast()).addGems(GEM_TYPE.BLUE, 1);
//        towerInventory.storeNew(ItemEnum.Tower.Arrow, 1);
//        ((TowerObject) towerInventory.getSlots().get(2).getLast()).addGems(GEM_TYPE.RED, 2);
//        ((TowerObject) towerInventory.getSlots().get(2).getLast()).addGems(GEM_TYPE.YELLOW, 2);
//        ((TowerObject) towerInventory.getSlots().get(2).getLast()).addGems(GEM_TYPE.BLUE, 2);
//        towerInventory.storeNew(ItemEnum.Tower.Range, 1);
//        ((TowerObject) towerInventory.getSlots().get(3).getLast()).addGems(GEM_TYPE.RED, 2);
//        ((TowerObject) towerInventory.getSlots().get(3).getLast()).addGems(GEM_TYPE.YELLOW, 2);
//        ((TowerObject) towerInventory.getSlots().get(3).getLast()).addGems(GEM_TYPE.BLUE, 1);
//
//        spellInventory.store(new GlobalSlow.P(5, 15, 0.3f, 5, new GlobalSlow.G(0.1f, 1, new int[]{3, 3, 0})));
//        spellInventory.store(new IceBlast.P(5, 10, 10, 0.3f, 3, 200, new IceBlast.G(5, 0.1f, 1, new int[]{3, 2, 3})));
//        spellInventory.store(new EmergencyRepair.P(5, 15, 3, new EmergencyRepair.G(2, new int[]{3, 0, 0})));
//        spellInventory.store(new SuddenDeath.P(5, 10));


//        storePanel.store(ItemEnum.Tower.Basic);
//        storePanel.store(ItemEnum.Tower.Rock);
//        storePanel.store(ItemEnum.Tower.Arrow);
//        storePanel.store(ItemEnum.Tower.Basic);
//        storePanel.store(ItemEnum.Tower.Rock);
//        storePanel.store(ItemEnum.Tower.Arrow);
//        storePanel.store(ItemEnum.Tower.Range);

//        detailInventory.storeNew(ItemEnum.Gem., 1);


//        detailInventory.storeNew(new Recipe(ItemEnum.Tower.Range));

//        initOpenedTowers();



//        addGems(GEM_TYPE.RED, 11);
//        addGems(GEM_TYPE.YELLOW, 4);
//        addGems(GEM_TYPE.BLUE, 9);
//        addGems(GEM_TYPE.WHITE, 7);
//        addGems(GEM_TYPE.GREEN, 4);
//        addGems(GEM_TYPE.BLACK, 5);

        update();

//        this.levelsAvailable[0] = true;



//        addGems(GEM_TYPE.RED, 1);
//        storePanel.store(ItemEnum.Tower.Rock);
//        storePanel.store(ItemEnum.Tower.Arrow);
//        storePanel.store(ItemEnum.Tower.Range);
//        storePanel.store(ItemEnum.Tower.Basic);
//        storePanel.store(ItemEnum.Tower.Rock);
//        storePanel.store(ItemEnum.Tower.Arrow);

        openLevel(1);
//        openLevel(2);
//        openLevel(3);
//        openLevel(4);
//        openLevel(5);
//        openLevel(6);
//        openLevel(7);
//        openLevel(8);
//        openLevel(9);
//        openLevel(10);
//        openLevel(11);
//        openLevel(12);
//        openLevel(13);
//        openLevel(14);
//        openLevel(15);
//        openLevel(16);
//        openLevel(17);
//        openLevel(18);
//        openLevel(19);
//        openLevel(20);
//        openLevel(21);
//        openLevel(22);
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
        level = 0;
        int i = 0;
//        System.out.println(totalExp);
        int t = totalExp;
        while (t >= Value.needExp2Lvl[i]){
            i++;
            t -= Value.needExp2Lvl[i];
        }
        int newLevel = 0;
        if(i != 0) {
            currentExp = totalExp % Value.needExp2Lvl[i - 1];
            newLevel = i + 1;
        }else {
            currentExp = totalExp;
        }
        for (int j = 0; j <= newLevel/* - getLevel()*/; j++){
            addLevel();
        }
//        System.out.println(getLevel() + "|" + currentExp);


    }


    public int getExp2NextLvl(){
        return Value.needExp2Lvl[level - 1] - currentExp;
    }
    public int getMaxExpThisLvl(){
        int exp;
//        if (level < 2){
            exp = Value.needExp2Lvl[level - 1];
//        }else {
//            exp = Value.needExp2Lvl[level - 1]/* - Value.needExp2Lvl[level - 2]*/;
//        }

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
//        GDefence.getInstance().flushCampainScreens();

//        gold = 0;
//        totalExp = 0;
//        currentExp = 0;
//        for (int i = 0; i < gems.length; i++){
//            gems[i] = 0;
//        }
//        getTowerInventory().flush();
//        getSpellInventory().flush();
//        getDetailInventory().flush();
//        initOpenedTowers();//default towers
//        for (int i = 0; i < levelsAvailable.length; i++){
//            levelsAvailable[i] = false;
//        }
//        for (int i = 0; i < levelsCompleted.length; i++){
//            levelsCompleted[i] = false;
//        }



//        setStorePanel(null);
//        setTowerMap(null);

        //etc
    }

    public void save(){
        GDefence.getInstance().log("Saving");
        //File saveFile = new File("UserSave");
        //
        File f = new File("Save" + File.separator + "UserSave.properties");
        try {
//            if(f.exists()) {
//                f.delete();
//            }
            GDefence.getInstance().log("Created new save file - " + f.createNewFile());
            f.getParentFile().mkdirs();
            Properties prop = new Properties();
            FileOutputStream fs = new FileOutputStream(f);

            prop.put("gold", getGold() + "");
            prop.put("totalExp", getTotalExp() + "");
            prop.put("towerInventory", getTowerInventory().getSave());
            prop.put("spellInventory", getSpellInventory().getSave());
            prop.put("detailInventory", getDetailInventory().getSave());
            prop.put("openedTowers", getOpenedTowersSavecode());
            prop.put("gems", getGemsSavecode());
            prop.put("researches", getResearchCode());
            prop.put("levelsCompleted", getLevelsCompletedSavecode());
            prop.put("levelAvailable", getLevelAvailableSavecode());
            prop.put("gradable", getGradableCode());
            prop.put("craftedTowers", getCraftedTowersCode());



            prop.store(fs, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        GDefence.getInstance().log("Saved");
    }
    private String getGradableCode(){
        return maxHealth.getCurrentLevel() + ";" + maxEnergy.getCurrentLevel();
    }
    private void loadGradableCode(String loadCode){
        String[] grades = loadCode.split(";");
//        maxHealth = new Gradable("Max Health", 5, 5, 5, 100, 25);
//        maxEnergy = new Gradable("Max Energy", 5, 30, 10, 100, 25);

        maxHealth.setCurrentLevel(Integer.parseInt(grades[0]));
        maxEnergy.setCurrentLevel(Integer.parseInt(grades[1]));
    }


    public boolean load(){
        GDefence.getInstance().log("Loading");
        setStorePanel(GDefence.getInstance().getStore().getStoreBuyPanel());
        setTowerMap(GDefence.getInstance().getArsenal().getTowerMap());
        openTowerToBuy(ItemEnum.Tower.Basic);

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
            String spells[] = prop.getProperty("spellInventory").split("/");
//            System.out.println(spells[0]);
            for (String t:spells){
                String[] info = t.split("-", 2);//info[0] - numberSlot, info[1] - savecode
                if(info.length > 1) getSpellInventory().store(SpellObject.loadSaveCode(info[1]), Integer.parseInt(info[0]));
            }

            String details[] = prop.getProperty("detailInventory").split("/");
            for (String t:details){
                String[] info = t.split("-", 2);//info[0] - numberSlot, info[1] - savecode
                if(info.length > 1) getDetailInventory().store(DetailObject.loadSaveCode(info[1]), Integer.parseInt(info[0]));

            }
            loadOpenedTowers(prop.getProperty("openedTowers"));
            loadOpenedResearches(prop.getProperty("researches"));
            String[] gemsSave = prop.getProperty("gems").split("-");
            for (int i = 0; i < gems.length; i++){
                gems[i] = Integer.parseInt(gemsSave[i]);
            }
            loadLevelsCompleted(prop.getProperty("levelsCompleted"));
            loadLevelsAvailable(prop.getProperty("levelAvailable"));
            loadGradableCode(prop.getProperty("gradable"));
            loadCraftedTowersCode(prop.getProperty("craftedTowers"));
            loadAvailableTowers();

            update();

//            GDefence.getInstance().user.openResearch(Research.Powder);
//            GDefence.getInstance().user.openResearch(Research.Mech);
//
//            ItemEnum.addItemById(152, 1, this);
//            ItemEnum.addItemById(153, 1, this);
//            towerInventory.store(new TowerObject(ItemEnum.Tower.Catapult, 0, 0, 0));
//            towerInventory.store(new TowerObject(ItemEnum.Tower.MultiShot, 0, 0, 0));

//            towerInventory.store(new TowerObject(ItemEnum.Tower.Arrow, 1, 1, 1));
//            towerInventory.store(new TowerObject(ItemEnum.Tower.Range, 0, 2, 1));
//            towerInventory.store(new TowerObject(ItemEnum.Tower.CrossBow, 1, 2, 2));
//            towerInventory.store(new TowerObject(ItemEnum.Tower.SteamMachine, 1, 1, 2));
//            towerInventory.store(new TowerObject(ItemEnum.Tower.Spear, 2, 1, 2));
//            towerInventory.store(new TowerObject(ItemEnum.Tower.Ballista, 1, 3, 2));
//            towerInventory.store(new TowerObject(ItemEnum.Tower.Glaive, 2, 4, 1));
//            towerInventory.store(new TowerObject(ItemEnum.Tower.Gun, 2, 2, 2));
//            towerInventory.store(new TowerObject(ItemEnum.Tower.Arrow, 1, 2, 1));
//            towerInventory.store(new TowerObject(ItemEnum.Tower.Rifle, 1, 0, 3));
//            towerInventory.store(new TowerObject(ItemEnum.Tower.MultiShot, 6, 1, 1));
//            towerInventory.store(new TowerObject(ItemEnum.Tower.Shotgun, 4, 1, 2));
//            update();

            GDefence.getInstance().log("User loaded");

            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
