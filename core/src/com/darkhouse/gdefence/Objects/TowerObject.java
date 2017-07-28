package com.darkhouse.gdefence.Objects;


import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.InventorySystem.inventory.Item;
import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;
import com.darkhouse.gdefence.InventorySystem.inventory.Tooltip.GemGradable;
import com.darkhouse.gdefence.Level.Ability.Tower.Ability;
import com.darkhouse.gdefence.Level.Tower.Tower;
import com.darkhouse.gdefence.User;

public class TowerObject implements ExpEarner, GameObject, GemGradable{

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
        }*/else if(item instanceof ItemEnum.Detail){
            Array<DetailObject> tmp = new Array<DetailObject>();
            for (int i = 0; i < amount; i++) {
                tmp.add(new DetailObject(((ItemEnum.Detail) item)));
            }
            return tmp;
        }
        return null;
    }


    public static int[] exp2nextLvl = {30, 70, 130, 190, 260, 340, 430, 530};

    private ItemEnum.Tower prototype;
    private int level;
    private float totalExp;
    private float currentExp;

    private int range;
    private int dmg;
    private int speed;
    private int cost;
    private int globalCost;
    protected Array<Ability.AbilityPrototype> abilities;




    @Override
    public String getSaveCode() {
        String gemsCode = "";
        for (int i = 0; i < gemsNumber.length; i++){
            gemsCode+= gemsNumber[i];
            if(i!= gemsNumber.length - 1){//may be unnecessary
                gemsCode+= ";";
            }
        }
        return prototype.name() + "-" + totalExp + "-" + gemsCode;
    }


    public static TowerObject loadSaveCode(String save) {
        String[] info = save.split("-");
        String[] gemsString = info[2].split(";");

        TowerObject t = new TowerObject(ItemEnum.Tower.getTower(info[0]));
        t.addExp(Float.parseFloat(info[1]));
        t.updateExp();//adding level

        for (int i = 0; i < gemsString.length; i++){
            t.addGems(User.GEM_TYPE.values()[i], Integer.parseInt(gemsString[i]));
        }


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
    public int getGlobalCost() {
        return globalCost;
    }
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
        totalExp += value;
        updateExp();
    }
    public boolean canGrade(){
        return (getLevel() > getPrimaryGemsNumber());
    }

    public void addGems(User.GEM_TYPE type, int value){
        if(canGrade()) {
            gemsNumber[type.ordinal()] += value;
            updateGemStat(type.ordinal(), value);
        }
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


    public TowerObject(ItemEnum.Tower prototype) {
//        super(prototype);
        this.prototype = prototype;
        level = 1;
        totalExp = 0;
        updateExp();

        dmg = prototype.getDmg();
        range = prototype.getRange();
        speed = prototype.getSpeed();
        cost = prototype.getCost();
        globalCost = prototype.getGlobalCost();

        copyAbilities(prototype.getAbilities());
//        abilities = new Array<Ability.AbilityPrototype>(prototype.getAbilities());


        gemsNumber = new int[]{0, 0, 0, 0, 0, 0};
//        gemsNumber = new HashMap<User.GEM_TYPE, Integer>();
//        gemsNumber.put(User.GEM_TYPE.RED, 0);
    }

    public TowerObject(ItemEnum.Tower prototype, int red, int yellow, int blue) {
        this(prototype);
        level += (red + yellow + blue) - 1;
        addGems(User.GEM_TYPE.RED, red);
        addGems(User.GEM_TYPE.YELLOW, yellow);
        addGems(User.GEM_TYPE.BLUE, blue);

    }




    @Override
    public String getTooltip() {
        String s = "Dmg: " + getDmg() + System.getProperty("line.separator")
                + "Range: " + getRange() + System.getProperty("line.separator")
                + "Speed: " + getSpeed() + "(" + Tower.getAttackSpeedDelay(getSpeed()) + ")" + System.getProperty("line.separator")
                + "Cost: " + getCost() + System.getProperty("line.separator");
//        s += prototype.getTooltip();
        for (int i = 0; i < getAbilities().size; i++){
            s += System.getProperty("line.separator");
            Ability.AbilityPrototype a = getAbilities().get(i);
            s += a.getName() + " ";
            s += a.getGemStat();
        }
        return s;
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
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
        }
    }

    public void updateExp(){
        currentExp = getTotalExp();
        for(int i = level - 1; currentExp >= exp2nextLvl[i]; i++){//if max lvl throws exeption
            currentExp -= exp2nextLvl[i];
            level++;
        }
    }

    public boolean equalsOrHigher(TowerObject anotherTower){
        int[] first = getSimplyGemStat();
        int[] second = anotherTower.getSimplyGemStat();
        boolean b = second[0] >= first[0] && second[1] >= first[1] && second[2] >= first[2];
        return (getPrototype() == anotherTower.getPrototype() && b);
    }

    private String getBoostName(User.GEM_TYPE gemType){
        switch (gemType) {
            case RED: return "damage";
            case YELLOW: return "attack speed";
            case BLUE: return "attack range";
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
        if(!canGrade()) s += "[Up tower level to upgrade]" + System.getProperty("line.separator");
        s += "+ " + User.GEM_TYPE.getBoost(gemType) + " " + getBoostName(gemType) + System.getProperty("line.separator")
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
}
