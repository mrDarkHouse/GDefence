package com.darkhouse.gdefence.Objects;


import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.InventorySystem.inventory.Item;
import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;
import com.darkhouse.gdefence.InventorySystem.inventory.Tooltip.GemGradable;
import com.darkhouse.gdefence.Level.Ability.Spell.GlobalSlow;
import com.darkhouse.gdefence.Level.Ability.Spell.Spell;
import com.darkhouse.gdefence.Level.Ability.Tower.Ability;
import com.darkhouse.gdefence.Model.Effectable;
import com.darkhouse.gdefence.User;

public abstract class SpellObject extends Ability.AbilityPrototype implements ExpEarner, GameObject, GemGradable{
//    public static int[] exp2nextLvl = {30, 70, 130, 190, 260, 340, 430, 530};
    //                                 30  100 230  420  660  800  1230 1760

    public static SpellObject loadSaveCode(String s) {
        String[] info = s.split("-");
        Ability.AbilityPrototype a = Ability.AbilityPrototype.loadAbilityCode(info[1]);
        if (a instanceof SpellObject) {
            SpellObject sp = ((SpellObject) a);
            sp.addExp(Float.parseFloat(info[0]));
//            sp.totalExp = Float.parseFloat(info[0]);
            return sp;
        }
        else throw new RuntimeException("che ti loadish daun eto ne spell save");
    }

    @Override
    public String getSaveCode() {
        String s = totalExp + "-" + super.getSaveCode() + "z" + energyCost + ";" + cooldown;
        if(this instanceof Spell.IAoe) s += ";" + ((Spell.IAoe) this).getAoe();
        return s;
    }


//    private int[] currentGems;
//    private int[] maxGems;

//    protected Ability.AbilityPrototype.Boost[] gemBoost = new Ability.AbilityPrototype.Boost[3];

    private int level;
    private float totalExp;
    private float currentExp;

    public int getLevel() {
        return level;
//        for (int i = 0; totalExp >= exp2nextLvl[i]; i++){
//
//        }
    }
    public float getTotalExp() {
        return totalExp;
    }
    public float getCurrentExp() {
        return currentExp;

    }

    public void addExp(float value){
        totalExp += value;
        updateExp();
    }
    public void updateExp(){
        currentExp = getTotalExp();
        for(int i = level - 1; currentExp >= exp2nextLevel()[i]; i++){//if max lvl throws exeption
            currentExp -= exp2nextLevel()[i];
            level++;
        }
    }

    protected int energyCost;
    protected int cooldown;

    private Array<Class<? extends Effectable>> affectedTypes;

    public Array<Class<? extends Effectable>> getAffectedTypes() {
        return affectedTypes;
    }

    public int getEnergyCost() {
        return energyCost;
    }

    public int getCooldown() {
        return cooldown;
    }

    public SpellObject(int id, String name, int energyCost, int cooldown, int[] maxGems,
                       Class<? extends Effectable>... affectedTypes) {
        super(id, name, maxGems);
        this.affectedTypes = new Array<Class<? extends Effectable>>(affectedTypes);
        this.energyCost = energyCost;
        this.cooldown = cooldown;
        level = 1;
        totalExp = 0;
        updateExp();
    }

    @Override
    public boolean canGrade(User.GEM_TYPE t) {
        return super.canGrade(t) && level > getGemsNumber();
    }

    @Override
    public String getName() {
        return super.getName() + " " + getGemStat();
    }

//    @Override
//    public Ability.AbilityPrototype copy() {
//        return null;
//    }

    @Override
    public String getTooltip() {
        AssetLoader l = GDefence.getInstance().assetLoader;
        String s = getChildTooltip() + System.getProperty("line.separator");
        if(this instanceof Spell.IAoe){
            s += FontLoader.colorString(l.getWord("radius"), 9) + ": " + ((Spell.IAoe) this).getAoe() + System.getProperty("line.separator");
        }
        s += FontLoader.colorString(l.getWord("energyCost"), 6) + ": " + energyCost + System.getProperty("line.separator") +
                FontLoader.colorString(l.getWord("cooldown"), 8) + ": " + cooldown + "s";
        return s;
    }

    abstract protected String getChildTooltip();

    @Override
    public String getGemGradeTooltip(User.GEM_TYPE gemType) {
        AssetLoader l = GDefence.getInstance().assetLoader;
        String s = "";
        if(level <= getGemsNumber() && super.canGrade(gemType)) s += l.getWord("gemGradeSpellTooltip") +
                System.getProperty("line.separator");
        return s + super.getGemGradeTooltip(gemType);//TODO Max when not max
    }

    @Deprecated
    public Ability getAbility() {
        return null;
    }

    public abstract Spell createSpell();


    //    @Override
//    public Item getPrototype() {
//        return null;
//    }






//    @Override
//    public String getGemGradeTooltip(User.GEM_TYPE gemType) {
//        return null;
//    }
}
