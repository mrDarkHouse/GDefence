package com.darkhouse.gdefence.Model;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Ability.Tools.EffectIcon;

import java.util.ArrayList;

public class Effectable extends GDSprite{

    protected class EffectBar extends Table {
        public Array<EffectIcon> getChildrenArray() {
//            return super.getChildren();
            Array<EffectIcon> icons = new Array<EffectIcon>();
            for (Actor a:super.getChildren()){
                icons.add(((EffectIcon) a));
            }
            return icons;
        }

        public void removeIcon(Texture icon){
            for (EffectIcon e:getChildrenArray()){
                if(e.getIcon() == icon)removeActor(e);
            }
        }
    }

    protected EffectBar effectBar;
    protected ArrayList<Effect> effects;

    public Effectable() {
        effects = new ArrayList<Effect>();
        effectBar = new EffectBar();
    }
    public void initEffectable(){//after setSize
        effectBar.setSize(getWidth(), getHeight()/2);
        effectBar.defaults().spaceLeft(5).spaceRight(5);
        effectBar.align(Align.center);
    }

    public void addEffect(Effect d){
        if(!haveEffect(d.getClass())) {
            effects.add(d);
            d.apply();//start effect
            if(!d.isHidden()) {
                EffectIcon ei = new EffectIcon(d);
                ei.setSize(effectBar.getHeight(), effectBar.getHeight());
                effectBar.add(ei);
            }
        }else {
            getEffect(d.getClass()).updateDuration();
            //effects.get(effects.indexOf(d)).updateDuration();
        }
    }
    public void deleteEffect(Class d){
        Effect searched = getEffect(d);
        if(searched != null) {
            effects.remove(searched);
            if(searched.getIconPath() != null) {
                effectBar.removeIcon(GDefence.getInstance().assetLoader.getEffectIcon(searched.getIconPath()));
            }
        }
    }

    public boolean haveEffect(Class d){
//        for (Debuff db: effects){
//            if(db.getClass() == d.getClass()){
//                return true;
//            }
//        }
//        return false;
        return (getEffect(d) != null);
    }
    private Effect getEffect(Class d){
        for (Effect db: effects){
            if(db.getClass() == d){
                return db;
            }
        }
        return null;
    }

}
