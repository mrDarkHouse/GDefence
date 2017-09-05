package com.darkhouse.gdefence.Model.Panels;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.InventorySystem.inventory.Tooltip.AbstractTooltip;
import com.darkhouse.gdefence.InventorySystem.inventory.Tooltip.TooltipListener;
import com.darkhouse.gdefence.Level.Ability.Spell.Spell;
import com.darkhouse.gdefence.Level.Wave;
import com.darkhouse.gdefence.Model.Effectable;
import com.darkhouse.gdefence.Objects.SpellObject;
import com.darkhouse.gdefence.Screens.LevelMap;

public class SpellPanel extends Table{

    public class SpellButton extends ImageButton{

        public class SpellThrower extends ClickListener {

            public class AoeTargeter extends InputListener {
                private int aoe;
                private Array<Class<? extends Effectable>> targetTypes;
                private Image image;

                public AoeTargeter(final int aoe, Array<Class<? extends Effectable>> targetTypes) {
                    //            super(GDefence.getInstance().assetLoader.get("towerRangeTexture.png", Texture.class));
                    this.aoe = aoe;
                    this.targetTypes = targetTypes;
                    image = new Image(GDefence.getInstance().assetLoader.get("towerRangeTexture.png", Texture.class));
                    image.setSize(aoe, aoe);
                    SpellThrower.this.stage.addActor(image);
                    isTargeting = true;
                }

                @Override
                public boolean mouseMoved(InputEvent event, float x, float y) {
                    image.setPosition(event.getStageX() - aoe/2, event.getStageY() - aoe/2);
                    return true;
                }

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    use();
                }

                private Array<? extends Effectable> getTargets(){
                    //            Array<? extends Effectable> tmp = new Array<Effectable>();
                    //


                    Vector2 center = new Vector2(image.getX() + image.getWidth()/2, image.getY() + image.getHeight()/2);
                    return LevelMap.getLevel().getMap().getUnitsInRange(center, aoe/2, targetTypes);
                    //            return tmp;
                }

                private void use(){
                /*SpellThrower.this.*/spell.use(getTargets());
                /*SpellThrower.this.*/stage.removeListener(this);
                    image.remove();
                    isTargeting = false;
                    LevelMap.getLevel().removeEnergy(spell.getEnergyCost());
//                    spell.getCooldownObject().resetCooldown();
                    resetCooldown(spell);
                }


            }

            public class SoloTargeter extends InputListener{
                private Array<Class<? extends Effectable>> targetTypes;

                public SoloTargeter(Array<Class<? extends Effectable>> targetTypes) {
                    this.targetTypes = targetTypes;
                    isTargeting = true;
                }

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    use(new Vector2(x, y));
                }

                private Array<? extends Effectable> getTarget(Vector2 point){
                    Effectable target = LevelMap.getLevel().getMap().getTargetUnit(point, targetTypes);
                    if(target != null) return new Array<Effectable>(new Effectable[]{target});
                    else return null;
                }

                private void use(Vector2 point){
                    Array<? extends Effectable> target = getTarget(point);
                    if(target != null) {
                        spell.use(target);
                        stage.removeListener(this);
                        isTargeting = false;
                        LevelMap.getLevel().removeEnergy(spell.getEnergyCost());
//                        spell.getCooldownObject().resetCooldown();
                        resetCooldown(spell);
                    }
                }
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);

                if(LevelMap.getLevel().haveEnergy(spell.getEnergyCost()) && spell.getCooldownObject().isReady()) {
                    use();
                }
            }

            private Spell spell;
            private Stage stage;

            public SpellThrower(Spell spell) {
                this.spell = spell;
            }
            public void init(Stage stage){
                this.stage = stage;
            }
            private void use(){
                if (spell.getPrototype() instanceof Spell.INonTarget){
                    spell.use(Wave.mobs);
                    LevelMap.getLevel().removeEnergy(spell.getEnergyCost());
                    resetCooldown(spell);
//                    spell.getCooldownObject().resetCooldown();
                }else if(spell.getPrototype() instanceof Spell.IAoe){
                    stage.addListener(new AoeTargeter(((Spell.IAoe) spell.getPrototype()).getAoe(), spell.getAffectedTypes()));
//                    isTargeting = true;
                }else if(spell.getPrototype() instanceof Spell.ITarget){
                    stage.addListener(new SoloTargeter(spell.getAffectedTypes()));
                }
            }


        }
        private void resetCooldown(Spell s){
            for (SpellButton button : buttons) {
                if (button.spell != null && button.spell.getPrototype().getPrototype() == s.getPrototype().getPrototype()) {
                    button.spell.getCooldownObject().resetCooldown();
                }
            }
        }


        public class SpellTooltip extends AbstractTooltip{
            private Label tooltipLabel;
//            private TextButton cooldown;

            public SpellTooltip(Skin skin) {
                super(SpellButton.this.spell.getPrototype().getName(), skin);
//                getTitleLabel().setAlignment(Align.center);
                tooltipLabel = new Label(SpellButton.this.spell.getPrototype().getTooltip(), skin, "description");
//                cooldown = new TextButton(SpellButton.this.spell.getPrototype().getCooldown() + "", skin);

                add(tooltipLabel);
//                add(cooldown).align(Align.right);

                pack();

                setVisible(false);
            }
            public void init(Stage stage){
                stage.addActor(this);
            }

            @Override
            public void hasChanged() {

            }
        }

        private Spell spell;

        public Spell getSpell() {
            return spell;
        }

        private ImageButton spellIcon;
        private TextButton energyCost;
//        private Label cdText;
        private ImageTextButton cdText;
        private Image chooseTransparent;
        private boolean isTargeting;
        private int borderSize = 1;

        public SpellButton(Spell spell) {
            super(GDefence.getInstance().assetLoader.generateImageButtonSkin(
                    GDefence.getInstance().assetLoader.getSpellIcon(spell.getPrototype().getTexturePath())));
            this.spell = spell;

//            spellIcon = new ImageButton(GDefence.getInstance().assetLoader.generateImageButtonSkin(
//                  GDefence.getInstance().assetLoader.getSpellIcon(spell.getPrototype().getTexturePath())));
//
            energyCost = new TextButton(spell.getEnergyCost() + "",
                    GDefence.getInstance().assetLoader.getSkin(), "description");
            chooseTransparent = new Image(GDefence.getInstance().assetLoader.get("spellCdTransparent.png", Texture.class));

//
//            setSize(100, 100);
//            spellIcon.setSize(70, 70);
//            energyCost.setSize(25, 25);
//            energyCost.align(Align.center);
//            Table t = new Table();
//            t.setSize(70, 70);
//            addActor(spellIcon);
//            t.add(energyCost).align(Align.right);
//            addActor(t);

            cdText = new ImageTextButton("", GDefence.getInstance().assetLoader.getSpellPanelCdButtonStyle());
            cdText.setVisible(false);

//            cdText = new Label("", FontLoader.generateStyle(19, Color.BLACK));
//            cdText.setAlignment(Align.center);
            addListener(new TooltipListener(new SpellTooltip(GDefence.getInstance().assetLoader.getSkin()), true));
            addListener(new SpellThrower(spell));//


        }
        public SpellButton() {
            super(GDefence.getInstance().assetLoader.generateImageButtonSkin(
                    GDefence.getInstance().assetLoader.getSpellIcon("nothing")));
//            spellIcon = new ImageButton(GDefence.getInstance().assetLoader.generateImageButtonSkin(
//                    GDefence.getInstance().assetLoader.getSpellIcon("crit")));
//            setSize(100, 100);
//            spellIcon.setSize(70, 70);
//            addActor(spellIcon);
        }
        private void init(){
//            Vector2 point = localToStageCoordinates(new Vector2(getX(), getY()));//TODO
//
//            if(chooseTransparent != null) chooseTransparent.setBounds(point.x + borderSize, point.y + borderSize, //if() - button not empty
//                    getWidth() - borderSize * 2, getHeight() - borderSize * 2);
//            if(cdText != null) cdText.setBounds(getX() + borderSize, getY() + borderSize,
//                    getWidth() - borderSize * 2, getHeight() - borderSize * 2);
//            System.out.println(getX() + " " + getY() + " " + getWidth() + " " + getHeight());
            for (EventListener l:getListeners()) {
                if (l instanceof TooltipListener) {
                    ((SpellTooltip) ((TooltipListener) l).getTooltip()).init(getStage());
                }
                if(l instanceof SpellButton.SpellThrower){
                    ((SpellButton.SpellThrower) l).init(getStage());
                }
            }
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            super.draw(batch, parentAlpha);

            if(isTargeting){
                chooseTransparent.draw(batch, parentAlpha);
            }

            if(cdText != null) {
                chooseTransparent.setBounds(getX() + borderSize, getY() + borderSize, //if() - button not empty
                        getWidth() - borderSize * 2, getHeight() - borderSize * 2);
                cdText.setBounds(getX() + borderSize, getY() + borderSize,
                        getWidth() - borderSize * 2, getHeight() - borderSize * 2);

                int cd = (int) Math.ceil(spell.getCooldownObject().getCooldown());
                if (cd != 0) {
                    cdText.setText(cd + "");
                    cdText.draw(batch, parentAlpha);
                } else {
//                    cdText.setText("");
                }

//                cdText.draw(batch, parentAlpha);
            }
        }
    }

    private SpellButton[] buttons;

    public SpellButton[] getButtons() {
        return buttons;
    }
    public void act(float delta){
        super.act(delta);
        for (SpellButton s:getButtons()){
            Spell a = s.getSpell();
            if(a != null) a.act(delta);
        }
    }

    public SpellPanel(Array<SpellObject> spells) {
        super();
        buttons = new SpellButton[4];
        Drawable bg = GDefence.getInstance().assetLoader.getSkin().getDrawable("info-panel");
//        TextureRegion bg = new TextureRegion(
//                GDefence.getInstance().assetLoader.get("spellPanelFon.png", Texture.class));
//        bg.setRegionWidth(60*4/*+8*5*/);
//        bg.setRegionHeight(70);
        pad(10);
        setBackground(bg);
        defaults().space(8);

        for (int i = 0; i < 4; i++){
            if(i < spells.size) {
                buttons[i] = new SpellButton(spells.get(i).createSpell());
            }
            else buttons[i] = new SpellButton();

            add(buttons[i]).minSize(70);
            row();
        }
//        debug();

        pack();
    }

    public void init(){
        for (int i = 0; i < 4; i++){
            buttons[i].init();
//            for (EventListener l:buttons[i].getListeners()){
//                if(l instanceof TooltipListener) {
//                    ((SpellButton.SpellTooltip) ((TooltipListener) l).getTooltip()).init(getStage());//
//                }
//                if(l instanceof SpellButton.SpellThrower){
//                    ((SpellButton.SpellThrower) l).init(getStage());
//                }
//            }
        }
    }


    //    public static class SpellInventory extends Inventory{
//
//        public SpellInventory() {
//            super(SpellObject.class, 4);
//        }
//
//        public SpellInventory(Inventory copyInventory) {
//            this();
//            copy(copyInventory);
//        }
//    }
//
//    public SpellPanel(Inventory inventory) {
//        super(new SpellInventory(inventory), new DragAndDrop(), GDefence.getInstance().assetLoader.get("skins/uiskin.json", Skin.class));
//        getTitleLabel().setText("");
//    }
//
//
//    @Override
//    protected void setDefaults() {
//        defaults().space(8);
//        defaults().size(60, 60);
//        row().fill().expandX();
//        setRowNumber(8);//infinity
//        setRows(2);
//    }
//
//    @Override
//    protected void addSourceTarget(SlotActor slotActor) {
////        dragAndDrop.addSource(new SlotSource(slotActor));
////        dragAndDrop.addTarget(new SlotTarget(slotActor));
//    }


}
