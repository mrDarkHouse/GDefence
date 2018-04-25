package com.darkhouse.gdefence.Model.Panels.ModPanels;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.InventorySystem.inventory.Tooltip.AbilityTooltip;
import com.darkhouse.gdefence.InventorySystem.inventory.Tooltip.TooltipListener;
import com.darkhouse.gdefence.Level.Ability.Mob.MobAbility;
import com.darkhouse.gdefence.Level.Ability.Mob.StagedAbilities;
import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Level;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Model.Panels.AbstractPanel;
import com.darkhouse.gdefence.Screens.LevelMap;

public class BossStatusPanel extends AbstractPanel{

    private Mob boss;
//    private ProgressBar health;
    private Label stage;
    private Label info;
    private float width;
    private Array<Label> abilitiesLabels;

//    private class BossHealth extends WidgetGroup{
//        private ProgressBar health;
//        private Label text;
//
//        public BossHealth() {
//            health = new ProgressBar(0, boss.getMaxHealth() ,0.2f, false, /*GDefence.getInstance().assetLoader.getMobHpBarStyle()*/GDefence.getInstance().assetLoader.getSkin(), "health-bar");
////        health.getStyle().background.setMinHeight(20);
////        health.getStyle().knob.setMinHeight(20);
////        expBar.getStyle().background.setMinWidth(width - 10);
////        health.getStyle().knob.setMinWidth(0.1f);
////        health.setValue(boss.getHealth());
////            health.setAnimateDuration(0.5f);
//
//            addActor(health);
//
//
//            text = new Label(LevelMap.getLevel().getHealthNumber() + "/" + LevelMap.getLevel().getMaxHP(),
//                    FontLoader.generateStyle(0, 16, Color.BLACK));
//            text.setPosition(health.getX() + health.getWidth()/2 - text.getWidth()/2,
//                    health.getY() + health.getHeight()/2 - text.getHeight()/2);
//            addActor(text);
//            pack();
//        }
//        public void hasChanged(){
//            health.setValue(boss.getHealth());
//            text.setText(boss.getHealth() + "/" + boss.getMaxHealth());
//        }
//    }

    public BossStatusPanel(float width) {
        this.width = width;
        AssetLoader l = GDefence.getInstance().assetLoader;
        setBackground(l.getSkin().getDrawable("info-panel"));
    }

    @Override
    public float getMinWidth() {
        return width;
    }

    public void init(Mob boss){
        this.boss = boss;
        setWidth(width);


        stage = new Label("1", FontLoader.generateStyle(0, 16, Color.BLACK));
        add(stage).align(Align.center).padLeft(5f).padRight(5f).row();

//        bossHealth = new BossHealth();

        info = new Label("", /*GDefence.getInstance().assetLoader.getSkin(), "description"*/FontLoader.generateStyle(0, 16, Color.BLACK));
        info.getStyle().font.getData().markupEnabled = true;
        add(info).align(Align.center).padLeft(5f).padRight(5f).row();





//        for (Label label:abilitiesLabels){
//            abilities.add(label);
//        }
        hasChanged();
//        pack();
    }

    public void hasChanged(){
        AssetLoader l = GDefence.getInstance().assetLoader;
        String s = "";
        s += /*"[#000000ff]Armor: "*/FontLoader.colorString(l.getWord("mobArmor") + ":" + boss.getOwnArmor(), 13)   /*+ " (" + Mob.getArmorReduction(boss.getArmor())*100f + ")"*/;
        if(boss.getBonusArmor() != 0) s += FontLoader.colorString("+" + boss.getBonusArmor(), 1);
        s += System.getProperty("line.separator");
        s += FontLoader.colorString(l.getWord("mobSpeed") + ":" + boss.getSpeed(), 13);
        s += System.getProperty("line.separator");
//        for (MobAbility a:boss.getAbilities()){
//            s += a.getPrototype().getTooltip();
//            s += System.getProperty("line.separator");
//        }

//        if(boss.haveEffect(StagedAbilities.class)){
        Effect e = boss.getEffect(StagedAbilities.StageChecker.class);
            if(e != null){
//                System.out.println(e);
                stage.setText(l.getWord("stage") + ":" + ((StagedAbilities.StageChecker) e).getStage());
            }
//        }
//        for (MobAbility m:boss.getAbilities()){
//            if(m instanceof StagedAbilities){
//                System.out.println("a");
//                stage.setText(Integer.toString(((StagedAbilities) m).getCurentStage() + 1));
//            }
//        }


        info.setText(s);
//        bossHealth.hasChanged();
        initAbilities();


        pack();

    }
    private void initAbilities(){
        if(abilitiesLabels != null) {
//            for (int i = abilitiesLabels.size - 1; i >= 0; i--){
//                if(i!= 0) {
//                    removeActor(abilitiesLabels.get(i));
//                    abilitiesLabels.removeIndex(i);
//                }
//            }
            for (Label l:abilitiesLabels){
                removeActor(l);
//                getCell(l).clearActor();
//                getCell(l).reset();
            }
            abilitiesLabels.clear();
        }


        abilitiesLabels = new Array<Label>();
        for (int i = 0; i < boss.getAbilities().size; i++){
            MobAbility.AbilityPrototype ab = boss.getAbilities().get(i).getPrototype();
            if(!ab.isHidden()) {
//                System.out.println(ab);
//                abilitiesLabels.add(new Label(ab.getName(), GDefence.getInstance().assetLoader.getSkin()));
//                        if(!empty) row();
                //abilitiesS += System.getProperty("line.separator");
                //abilitiesS += ab.getName();
                Label label = new Label(ab.getName(), GDefence.getInstance().assetLoader.getInfoPanelSkin());
                label.setAlignment(Align.center);
                AbilityTooltip aTooltip = new AbilityTooltip(this, ab, GDefence.getInstance().assetLoader.getSkin());
                TooltipListener tListener = new TooltipListener(aTooltip, true);
                tListener.setOffset(-aTooltip.getWidth()/2, 15/*-20, 10*/);//center
                label.addListener(tListener);

                abilitiesLabels.add(label);
            }
        }
        for (Label l:abilitiesLabels){
            add(l).align(Align.center).row();
        }
    }
}
