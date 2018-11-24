package com.darkhouse.gdefence.Objects;


import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.InventorySystem.inventory.Item;
import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;
import com.darkhouse.gdefence.InventorySystem.inventory.Tooltip.GemGradable;
import com.darkhouse.gdefence.Level.Ability.Tower.Ability;
import com.darkhouse.gdefence.Level.Tower.Tower;
import com.darkhouse.gdefence.User;

import java.util.Arrays;

public class TowerObject implements ExpEarner, GameObject{


    public static boolean isMatches(Class<? extends GameObject> a, Class<? extends GameObject> b){
        if(a == b) return true;
        if(a.getSuperclass() == b) return true;//a Recipe b Detail
        if(a == b.getSuperclass()) return true;
        return false;
    }
    public static Array<? extends GameObject> generateStartObjects(Item item, int amount){
        if(item instanceof ItemEnum.Tower){
            Array<TowerObject> tmp = new Array<TowerObject>();
            for (int i = 0; i < amount; i++) {
                tmp.add(new TowerObject(((ItemEnum.Tower) item)));
            }
            return tmp;
        }/*else if(item instanceof ItemEnum.Spell){
            Array<SpellObject> tmp = new Array<SpellObject>();
            for (int i = 0; i < amount; i++) {
                tmp.add(new SpellObject(((ItemEnum.Spell) item)));
            }
            return tmp;
        }*//*else if(item instanceof ItemEnum.Detail){
            Array<DetailObject> tmp = new Array<DetailObject>();
            for (int i = 0; i < amount; i++) {
                tmp.add(new DetailObject(((ItemEnum.Detail) item)));
            }
            return tmp;
        }*/
        return null;
    }
    public static TowerObject generateClearTower(ItemEnum.Tower prototype){
        return new TowerObject(prototype, 0, 0, 0);
    }

    @Override
    public int[] exp2nextLevel() {
        return exp2nextLevel;
    }

//    public int getExp2NextLvl(int lvl){
//        return exp2nextLevel[lvl];
//    }
    private void initExp2nextLvl(int cost){
        exp2nextLevel = new int[startExp2nextLvl.length];
        for (int i = 0; i < exp2nextLevel.length; i++){
            exp2nextLevel[i] = startExp2nextLvl[i] * cost;
        }
    }
//    private static int[] startExp2nextLvl = {40, 80, 140, 260, 640, 1280, 1280, 1280, 1280};
    private int[] exp2nextLevel;
    private static int[] startExp2nextLvl = {4, 8, 14, 26, 64, 80, 92, 102, 128, 154};//max 10 levels
//    public static int[] startExp2nextLvl = {30, 70, 130, 190, 260, 340, 430, 530};

    private ItemEnum.Tower prototype;
    private int level;
    private float totalExp;
    private float currentExp;

    private int range;
    private int dmg;
    private int speed;
    private int cost;
//    private int globalCost;
    protected Array<Ability.AbilityPrototype> abilities;

    @Override
    public int getGlobalCost() {
        return prototype.getGlobalCost();
    }

    @Override
    public int getSellCost() {
        return prototype.getGlobalCost();//full cost
    }

    @Override
    public String getSaveCode() {
        String gemsCode = "";
        for (int i = 0; i < gemsNumber.length; i++){
            gemsCode+= gemsNumber[i];
            if(i!= gemsNumber.length - 1){//may be unnecessary
                gemsCode+= ";";
            }
        }
        String abilitiesCode = "";
        for (int i = 0; i < abilities.size; i++){
            abilitiesCode += abilities.get(i).getSaveCode();
            if(i!= abilities.size - 1){//may be unnecessary
                abilitiesCode+= "x";
            }
        }
        return prototype.name() + "-" + totalExp + "-" + gemsCode + "-" + abilitiesCode;
    }


    public static TowerObject loadSaveCode(String save) {
        String[] info = save.split("-");
        String[] gemsString = info[2].split(";");

        TowerObject t = new TowerObject(ItemEnum.Tower.getTower(info[0])/*, Integer.parseInt(gemsString[0]), Integer.parseInt(gemsString[1]), Integer.parseInt(gemsString[2])*/);
//        System.out.println(t.abilities);
        t.addExp(Float.parseFloat(info[1]));
        t.updateExp();//adding level

        for (int i = 0; i < gemsString.length; i++){
            t.addGems(User.GEM_TYPE.values()[i], Integer.parseInt(gemsString[i]));
        }


//        t.abilities.clear();//TODO without it bug when after each load add additional ability for infinitely (creating clear abilities for new towers)
        if(info.length == 4){
            String[] abi = info[3].split("x");
            for (int i = 0; i < abi.length; i++){
//                System.out.println(abi[i]);
//                System.out.println(i);
//                System.out.println(abi[i]);
//                System.out.println(t.abilities);
                String[] gem = abi[i].split("z");//gem[0] is a ability id. Now it ignored
                t.abilities.get(i).addGems(SpellObject.loadGemCode(gem[1]));
//                t.abilities.add(Ability.AbilityPrototype.loadAbilityCode(abi[i]));
            }
        }
//
//        for (int i = 0; i < abi.length; i++){
//            t.abilities.add(Ability.AbilityPrototype.loadAbilityCode(abi[i]));
//        }

        return t;
    }


    public Array<Ability.AbilityPrototype> getAbilities() {
        return abilities;
    }
//    public AttackType getAttackType() {
//        return attackType;
//    }

    public ItemEnum.Tower getPrototype() {
        return prototype;
    }

    public int getRange() {
        return range;
    }
    public int getCost() {
        return cost;
    }
//    public int getGlobalCost() {
//        return globalCost;
//    }
    public int getDmg() {
        return dmg;
    }
    public String getName() {
        return prototype.getName() + " " + getSimplyGemStatString();
    }
    public int getSpeed() {
        return speed;
    }

    //    private HashMap<User.GEM_TYPE, Integer> gemsNumber;
    private int[] gemsNumber;



    public void addExp(float value){
        if (level == exp2nextLevel().length) return;//maxLevel
        totalExp += value;
        updateExp();
    }
    public boolean canGrade(){
        return (getLevel() > getPrimaryGemsNumber());
    }

    public void addGems(User.GEM_TYPE type, int value){
        for (int i = 0; i < value; i++){
            if(canGrade()) {
                gemsNumber[type.ordinal()]++;
                updateGemStat(type.ordinal(), 1);
            }
        }
//        if(canGrade()) {
//            gemsNumber[type.ordinal()] += value;
//            updateGemStat(type.ordinal(), value);
//        }
    }

    public int getPrimaryGemsNumber(){
        return gemsNumber[0] + gemsNumber[1] + gemsNumber[2];
    }

    public int getLevel() {
        return level;
    }
    public float getTotalExp() {
        return totalExp;
    }
    public float getCurrentExp() {
        return currentExp;
    }
    public int[] getSimplyGemStat(){
        return new int[]{gemsNumber[0], gemsNumber[1], gemsNumber[2]};
    }
    public String getSimplyGemStatString(){
        int[] gems = getSimplyGemStat();
        String s = "{";
        for (int i = 0; i < gems.length; i++){
            s += gems[i];
            if(i != gems.length - 1){
                s += ", ";
            }
        }
        s += "}";
        return s;
    }
    private void copyAbilities(Array<Ability.AbilityPrototype> toCopy){
        abilities = new Array<Ability.AbilityPrototype>();
        for (Ability.AbilityPrototype a:toCopy){
            abilities.add(a.copy());
        }
    }
    public TowerObject addAbilitiesGems(TowerObject other){
        if(other.getPrototype() == this.getPrototype()){
            for (int i = 0; i < getAbilities().size; i++){
//                for (Ability.AbilityPrototype at:other.getAbilities()){
//                    ap.gemCur(at.getGemCur());
                Ability.AbilityPrototype ap = abilities.get(i);
                Ability.AbilityPrototype at = other.abilities.get(i);
                    for (int r = 0; r < at.getGemCur().length; r++){
                        for (int a = 0; a < at.getGemCur()[r]; a++) {
                            ap.addGem(User.GEM_TYPE.values()[r + 3]);
                        }
                    }
//
//                }
            }
            return this;
        }
        return other;
    }


    public TowerObject(ItemEnum.Tower prototype) {
//        super(prototype);
        this.prototype = prototype;

        dmg = prototype.getDmg();
        range = prototype.getRange();
        speed = prototype.getSpeed();
        cost = prototype.getCost();
//        globalCost = prototype.getGlobalCost();

        initExp2nextLvl(getCost());

        level = 1;
        totalExp = 0;
        updateExp();

        copyAbilities(prototype.getAbilities());
//        abilities = new Array<Ability.AbilityPrototype>(prototype.getAbilities());


        gemsNumber = new int[]{0, 0, 0/*, 0, 0, 0*/};
//        gemsNumber = new HashMap<User.GEM_TYPE, Integer>();
//        gemsNumber.put(User.GEM_TYPE.RED, 0);
    }

    public TowerObject(ItemEnum.Tower prototype, int red, int yellow, int blue) {
        this(prototype);
//        level += (red + yellow + blue) - 1;
        level = (red + yellow + blue) - 1;
        if(level < 0) level = 0;

        for(int i = 0; i < level; i++){
//            addExp(startExp2nextLvl[i]);
            totalExp += /*startExp2nextLvl[i];*/exp2nextLevel()[i];
        }
        updateExp();
        addGems(User.GEM_TYPE.RED, red);
        addGems(User.GEM_TYPE.YELLOW, yellow);
        addGems(User.GEM_TYPE.BLUE, blue);

    }




    @Override
    public String getTooltip() {

        AssetLoader l = GDefence.getInstance().assetLoader;
        StringBuilder s = new StringBuilder(l.getWord("dmg") + ": " + getDmg() + System.getProperty("line.separator")
                + l.getWord("speed") + ": " + getSpeed() + "(" + Tower.getAttackSpeedDelay(getSpeed()) + ")" + System.getProperty("line.separator")
                + l.getWord("range") + ": " + getRange() + System.getProperty("line.separator")
                + l.getWord("energyCost") + ": " + getCost() + System.getProperty("line.separator"));

//        String s = FontLoader.getOneColorButtonString(0, l.getWord("dmg"), FontLoader.RED, Color.WHITE) + ": " + getDmg() + System.getProperty("line.separator")
//                + FontLoader.getOneColorButtonString(0, l.getWord("speed"), FontLoader.YELLOW, Color.WHITE) + ": " + getSpeed() + "(" + Tower.getAttackSpeedDelay(getSpeed()) + ")" + System.getProperty("line.separator")
//                + FontLoader.getOneColorButtonString(0, l.getWord("range"), FontLoader.BLUE, Color.WHITE) + ": " + getRange() + System.getProperty("line.separator")
//                + l.getWord("cost") + ": " + getCost() + System.getProperty("line.separator");


//        s += prototype.getTooltip();
        for (int i = 0; i < getAbilities().size; i++){
            s.append(System.getProperty("line.separator"));
            Ability.AbilityPrototype a = getAbilities().get(i);
            s.append(a.getName()).append(" ");
            s.append(a.getGemStat());
        }
        return s.toString();
    }

    @Override
    public String toString() {
        return getName();
    }

    private void updateStat(){
        dmg = prototype.getDmg();
        speed = prototype.getSpeed();
        range = prototype.getRange();

        for (int i = 0; i < 3; i++){
            updateGemStat(i, gemsNumber[i]);
        }

    }

    public void updateGemStat(int gemType, int value){
        switch (gemType){
            case 0:
                for (int i = 0; i < value; i++)dmg += User.GEM_TYPE.getBoost(User.GEM_TYPE.RED);
                break;
            case 1:
                for (int i = 0; i < value; i++)speed += User.GEM_TYPE.getBoost(User.GEM_TYPE.YELLOW);
                break;
            case 2:
                for (int i = 0; i < value; i++)range += User.GEM_TYPE.getBoost(User.GEM_TYPE.BLUE);
                break;
        }
    }
    public void flushGems(){
//        int numberFlushed = getPrimaryGemsNumber();
        for (int i = 0; i < gemsNumber.length; i++){
//            numberFlushed += gemsNumber[i];
            gemsNumber[i] = 0;
            updateStat();
        }
        for (Ability.AbilityPrototype ap:getAbilities()){
            ap.flushGems();
        }

//        return numberFlushed;
    }

    public void updateExp(){//currentExp bug
        float tmpExp = getTotalExp();

        int n = 1;
//        System.out.println(Arrays.toString(exp2nextLevel()));
        for(int i = /*level - 1*/0; tmpExp >= exp2nextLevel()[i]; i++){//if max lvl throws exeption//TODO
//            System.out.println("tmp " + tmpExp);
            tmpExp -= /*startExp2nextLvl[i];*/exp2nextLevel()[i];
            n++;
        }
        level = n;
//        System.out.println(tmpExp);
        currentExp = tmpExp;
    }



    public boolean equalsOrHigher(TowerObject anotherTower){
        int[] first = getSimplyGemStat();
        int[] second = anotherTower.getSimplyGemStat();
        boolean b = second[0] >= first[0] && second[1] >= first[1] && second[2] >= first[2];
        return (getPrototype() == anotherTower.getPrototype() && b);
    }

    private String getBoostName(User.GEM_TYPE gemType){
        switch (gemType) {
            case RED: return "dmg";
            case YELLOW: return "speed2";
            case BLUE: return "range2";
            default: return "error";
        }
    }
    private String getBoostValue(User.GEM_TYPE gemType){
        switch (gemType) {
            case RED: return getDmg() + "";
            case YELLOW: return getSpeed() + "(" + Tower.getAttackSpeedDelay(getSpeed()) + ")";
            case BLUE: return getRange() + "";
            default: return "error";
        }
    }
    private String getGradedBoostValue(User.GEM_TYPE gemType){
        switch (gemType) {
            case RED: return (getDmg() + User.GEM_TYPE.getBoost(gemType)) + "";
            case YELLOW: return (getSpeed() + User.GEM_TYPE.getBoost(gemType)) + "(" + Tower.getAttackSpeedDelay(getSpeed()
                    + User.GEM_TYPE.getBoost(gemType)) + ")";
            case BLUE: return (getRange() + User.GEM_TYPE.getBoost(gemType)) + "";
            default: return "error";
        }
    }

    @Override
    public String getGemGradeTooltip(User.GEM_TYPE gemType) {
        String s = "";
        AssetLoader l = GDefence.getInstance().assetLoader;
        if(!canGrade()) s += l.getWord("gemGradeTooltip") + System.getProperty("line.separator");
        s += "+ " + User.GEM_TYPE.getBoost(gemType) + " " + l.getWord(getBoostName(gemType)) + System.getProperty("line.separator")
                + "(" + getBoostValue(gemType) + "=>" + getGradedBoostValue(gemType) + ")";
        return s;



//        if (canGrade()) {
//            return "+ " + User.GEM_TYPE.getBoost(gemType) + " " + getBoostName(gemType) + System.getProperty("line.separator")
//                    + "(" + getBoostValue(gemType) + "=>" + getGradedBoostValue(gemType) + ")";
//        }else {
//            return getBoostName(gemType) + " MAX" + System.getProperty("line.separator") +
//                    "(" + getBoostValue(gemType) + ")";
//        }
    }
    @Override
    public String getGradeNumberInfo(User.GEM_TYPE gemType) {
        return "";
    }
}
