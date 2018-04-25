package com.darkhouse.gdefence.InventorySystem.inventory.Tooltip;


import com.darkhouse.gdefence.User;

public interface GemGradable {

    String getGemGradeTooltip(User.GEM_TYPE gemType);
    String getGradeNumberInfo(User.GEM_TYPE gemType);
    public void flushGems();

}
