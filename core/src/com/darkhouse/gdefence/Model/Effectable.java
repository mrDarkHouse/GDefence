package com.darkhouse.gdefence.Model;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Ability.Tools.EffectIcon;
import com.darkhouse.gdefence.Level.Ability.Tower.Ability;

import java.util.ArrayList;
import java.util.HashMap;

public class Effectable extends GDSprite{

    protected class EffectBar extends WidgetGroup{
        private float staticWidth;
        private float staticHeight;

        public void setStaticSize(float width, float height){
            this.staticWidth = width;
            this.staticHeight = height;
        }
//        public float getStaticWidth() {
//            return staticWidth;
//        }
        public float getStaticHeight() {
            return staticHeight;
        }

        public Array<EffectIcon> getChildrenArray() {
//            return super.getChildren();
            Array<EffectIcon> icons = new Array<EffectIcon>();
            for (Actor a:super.getChildren()){
                icons.add(((EffectIcon) a));
            }
            return icons;
        }

        @Override
        public void addActor(Actor actor) {
            super.addActor(actor);
            updatePosition();
//            actor.setPosition(getPosition().x, getPosition().y);

//            System.out.println(getPosition());
//            System.out.println(actor.getX() + " " + actor.getY());



        }

        private void updatePosition(){
            int size = getChildrenArray().size;
            for (int i = 0; i < size; i++){
//                System.out.println(getChildrenArray().get(i) + " " + (getChildrenArray().get(i).getWidth() + " " + (getChildrenArray().get(i).getX())));
                float x = staticWidth/2 - getChildrenArray().get(i).getWidth()/2*size - 3.5f*(size-1);
//                System.out.println(getWidth() + " " + getChildrenArray().get(i).getWidth());
//                System.out.println("x " + x);
                getChildrenArray().get(i).setX(x + 7*i +20*i);
                pack();
//                getChildrenArray().get(i)
//                System.out.println(getChildrenArray().get(i) + " " + (getChildrenArray().get(i).getWidth() + " " + (getChildrenArray().get(i).getX())));
            }


        }
        public EffectBar() {
        }

        public void removeIcon(Texture icon){
            for (EffectIcon e:getChildrenArray()) {
                if (e.getIcon() == icon) {
                    removeActor(e);
                    updatePosition();
                }
            }
        }
    }

    protected class EffectBars extends Table {
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
                if(e.getIcon() == icon){
//                    getCell(e).reset();
                    Array<Cell> array = new Array<Cell>();
                    Array<Actor> actors = new Array<Actor>();
                    for (Cell c:getCells()){
                        array.add(c);
                        actors.add(c.getActor());
                    }
                    Cell c = getCell(e);
                    actors.removeValue(e, false);
                    array.removeValue(c, false);

//                    System.out.println(actors);

//                    Cell c = getCell(e);
//                    array.removeValue(c, false);
//                    c.clearActor();
                    clearChildren();
//                    System.out.println(actors);
//                    System.out.println(getCells());
                    for (Cell cell:array){
                        getCells().add(cell);
                        getCells().peek().setLayout(this);
                        cell.setActor(actors.pop());
                    }


//                    removeActor(e);

//                    getCells().removeValue(c, false);
//                    getCell(e).clearActor();

//                    removeActor(getCell(e));
                    pack();


                }
            }
        }
    }

    protected EffectBar effectBar;
    protected HashMap<Class<? extends Ability.IAbilityType> , Array<Effect>> effects;
//    protected ArrayList<Effect> effects;

//    public ArrayList<Effect> getEffects() {
//        return effects;
//    }

    public Effectable() {
//        effects = new ArrayList<Effect>();
        effects = new HashMap<Class<? extends Ability.IAbilityType>, Array<Effect>>();
        effectBar = new EffectBar();
    }
    public void initEffectable(){//after setSize
        effectBar.setStaticSize(getWidth(), getHeight()/2);
//        effectBar/*.defaults()*/.space(7);/*.spaceLeft(5).spaceRight(5)*/;
//        effectBar.align(Align.center);
    }
    private void setEffect(Effect d){
        Class<? extends Ability.IAbilityType> tt = d.getType();
        if(effects.containsKey(tt)){
            effects.get(tt).add(d);
        }else {
            Array<Effect> arr = new Array<Effect>();
            arr.add(d);
            effects.put(tt, arr);
        }
    }

    public void addEffect(Effect d){
//        if(effects.size() >= 5) return; //add to cache
        if(!haveEffect(d.getClass())) {
            setEffect(d);
//            effects.add(d);
            d.apply();//start effect
            if(!d.isHidden()) {
                EffectIcon ei = new EffectIcon(d);
                ei.setSize(effectBar.getStaticHeight(), effectBar.getStaticHeight());
                effectBar.addActor(ei);
//                effectBar.pack();
            }
//            System.out.println(getEffects());
        }else {
            getEffect(d.getClass()).updateDuration();
            //effects.get(effects.indexOf(d)).updateDuration();
        }
    }
//    public void deleteEffect(Class /*Effect */d){
////        if(effects.containsValue(d));
//        Effect searched = getEffect(d);
//        if(searched != null) {
////            effects.remove(searched);
////            if(searched.getIconPath() != null) {
////                effectBar.removeIcon(GDefence.getInstance().assetLoader.getEffectIcon(searched.getIconPath()));
//////                effectBar.pack();
////            }
//        }
//    }

    public boolean haveEffect(Class d){
//        for (Debuff db: effects){
//            if(db.getClass() == d.getClass()){
//                return true;
//            }
//        }
//        return false;
        return (getEffect(d) != null);
    }
    public Array<Effect> getEffects(){
        Array<Effect> a = new Array<Effect>();
        for (java.util.Map.Entry<Class<? extends Ability.IAbilityType> , Array<Effect>> e:effects.entrySet()){
//            System.out.println("a " + e.getValue() + "|" + e.getKey());
            for (Effect ab:e.getValue()){
                a.add(ab);
            }
        }
        return a;
    }
    public void deleteEffect(Class d){
        for (java.util.Map.Entry<Class<? extends Ability.IAbilityType> , Array<Effect>> e:effects.entrySet()){
            for (Effect ab:e.getValue()){
                if(ab.getClass() == d){
                    e.getValue().removeValue(ab, true);
                    if(ab.getIconPath() != null) {
                        effectBar.removeIcon(GDefence.getInstance().assetLoader.getEffectIcon(ab.getIconPath()));
                        effectBar.pack();
                    }
                }
            }
        }
    }
//    public void deleteEffect(Effect e)

    public Effect getEffect(Class d){
        for (Effect e:getEffects()){
            if(e.getClass() == d) return e;
        }
//        for (Effect db: effects){
//            if(db.getClass() == d){
//                return db;
//            }
//        }
        return null;
    }

}
