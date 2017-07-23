package com.darkhouse.gdefence.Objects;


import com.darkhouse.gdefence.InventorySystem.inventory.Item;
import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;
import com.darkhouse.gdefence.InventorySystem.inventory.Tooltip.GemGradable;
import com.darkhouse.gdefence.User;

public class SpellObject extends GameObject implements GemGradable{


    public static SpellObject loadSaveCode(String s) {
        return null;
    }


    private ItemEnum.Spell prototype;


    public SpellObject(ItemEnum.Spell prototype) {
    }


    public interface ITarget{
        enum targetType{
            TOWER, MOB, BOTH
        }
    }
    public interface INonTarget{

    }


    @Override
    public String getName() {
        return null;
    }

    @Override
    public Item getPrototype() {
        return null;
    }

    @Override
    public String getTooltip() {
        return null;
    }

    @Override
    public String getSaveCode() {
        return null;
    }


    @Override
    public String getGemGradeTooltip(User.GEM_TYPE gemType) {
        return null;
    }
}
