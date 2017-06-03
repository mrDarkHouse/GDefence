//package com.darkhouse.gdefence.Objects;
//
//
//import com.darkhouse.gdefence.Helpers.FontLoader;
//import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;
//import com.darkhouse.gdefence.User;
//
//public class Gem extends DetailObject{
//    private User.GEM_TYPE type;
//
//    public Gem(User.GEM_TYPE type) {
////        super(ItemEnum.Detail.Gem);
//        this.type = type;
//
//
//    }
//
//    @Override
//    public String getName() {
//        return FontLoader.firstCapitalLetter(type.name()) + " gem";
//    }
//
//    @Override
//    public String getSaveCode() {//
//        return "";
//    }
//
//    @Override
//    public String getTooltip() {
//        String t;
//        if(type == User.GEM_TYPE.RED || type == User.GEM_TYPE.YELLOW || type == User.GEM_TYPE.BLUE) t = "Used for grade towers";
//        else t = "Used for grade spells";
//        return t;
//    }
//}
