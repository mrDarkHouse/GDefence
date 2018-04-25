package com.darkhouse.gdefence.Model.Panels;


import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.InventorySystem.inventory.Tooltip.AbstractTooltip;
import com.darkhouse.gdefence.InventorySystem.inventory.Tooltip.TooltipListener;
import com.darkhouse.gdefence.Level.Ability.Spell.Spell;
import com.darkhouse.gdefence.Level.Level;
import com.darkhouse.gdefence.Level.Wave;
import com.darkhouse.gdefence.Model.Effectable;
import com.darkhouse.gdefence.Model.Level.Map;
import com.darkhouse.gdefence.Model.ShapeCircle;
import com.darkhouse.gdefence.Model.ShapeTarget;
import com.darkhouse.gdefence.Objects.SpellObject;
import com.darkhouse.gdefence.Screens.LevelMap;

public class SpellPanel extends Table{

    public class SpellButton extends ImageButton{

        public class SpellThrower extends ClickListener {

            public class AoeTargeter extends InputListener {
                private int aoe;
                private Array<Class<? extends Effectable>> targetTypes;
//                private Image image;
                private ShapeCircle circle;

                public AoeTargeter(final int aoe, Array<Class<? extends Effectable>> targetTypes, Vector2 startCoord) {
                    //            super(GDefence.getInstance().assetLoader.get("towerRangeTexture.png", Texture.class));
                    this.aoe = aoe;
                    this.targetTypes = targetTypes;
//                    image = new Image(GDefence.getInstance().assetLoader.get("towerRangeTexture.png", Texture.class));
//                    image.setSize(aoe, aoe);
                    circle = new ShapeCircle(new ShapeRenderer(), aoe/2);
                    circle.setPosition(startCoord.x - aoe/4, startCoord.y - aoe/4);
                    SpellThrower.this.stage.addActor(circle);
                    isTargeting = true;
                }

                public void cancelTargeting(){
                    stage.removeListener(this);
                    isTargeting = false;
                    circle.remove();
                }

                @Override
                public boolean mouseMoved(InputEvent event, float x, float y) {
                    /*if(Level.getMap().inMapBounds(x, y))*/ circle.setPosition(event.getStageX() - aoe/4, event.getStageY() - aoe/4);
                    return true;
                }

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    if(button == 0) use();
                    else cancelTargeting();
                }

                @Override
                public boolean keyDown(InputEvent event, int keycode) {
//                    return super.keyUp(event, keycode);
//                    System.out.println(event + " " + keycode);
                    if(keycode == Input.Keys.ESCAPE){
                        cancelTargeting();
                        return true;
                    }else return false;
                }

                private Array<? extends Effectable> getTargets(){
                    //            Array<? extends Effectable> tmp = new Array<Effectable>();
                    //


                    Vector2 center = new Vector2(circle.getX() + circle.getWidth()/2, circle.getY() + circle.getHeight()/2);
                    return Level.getMap().getUnitsInRange(center, aoe/2, targetTypes, false, spell.isPierceImmunity());
                    //            return tmp;
                }

                private void use(){
                /*SpellThrower.this.*/spell.use(getTargets());
//                /*SpellThrower.this.*/stage.removeListener(this);
//                    circle.remove();
//                    isTargeting = false;

                    LevelMap.getLevel().removeEnergy(spell.getEnergyCost());
//                    spell.getCooldownObject().resetCooldown();
                    resetCooldown(spell);
                    cancelTargeting();
                }


            }

            public class SoloTargeter extends InputListener{
                private Array<Class<? extends Effectable>> targetTypes;
                private ShapeTarget circle;

                public SoloTargeter(Array<Class<? extends Effectable>> targetTypes, Vector2 startCoord) {
                    this.targetTypes = targetTypes;
                    circle = new ShapeTarget(new ShapeRenderer(), 20);
                    circle.setPosition(startCoord.x - 10, startCoord.y - 10);
                    SpellThrower.this.stage.addActor(circle);
                    isTargeting = true;
                }

                public void cancelTargeting(){
                    stage.removeListener(this);
                    isTargeting = false;
                    circle.remove();
                }

                @Override
                public boolean mouseMoved(InputEvent event, float x, float y) {
                    circle.setPosition(event.getStageX() - 10, event.getStageY() - 10);
                    return true;
                }

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    if(button == 0) use(new Vector2(x, y));
                    else cancelTargeting();
                }

                @Override
                public boolean keyDown(InputEvent event, int keycode) {
//                    return super.keyUp(event, keycode);
                    if(keycode == Input.Keys.ESCAPE){
                        cancelTargeting();
                        return true;
                    }else return false;
                }

                private Array<? extends Effectable> getTarget(Vector2 point){
                    Effectable target = Level.getMap().getTargetUnit(point, targetTypes);
                    if(target != null) return new Array<Effectable>(new Effectable[]{target});
                    else return null;
                }

                private void use(Vector2 point){
                    Array<? extends Effectable> target = getTarget(point);
                    if(target != null) {
                        spell.use(target);

                        LevelMap.getLevel().removeEnergy(spell.getEnergyCost());
//                        spell.getCooldownObject().resetCooldown();
                        resetCooldown(spell);
                    }
                    cancelTargeting();
//                    stage.removeListener(this);
//                    isTargeting = false;
//                    circle.remove();
                }
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);

                if(LevelMap.getLevel().haveEnergy(spell.getEnergyCost()) && spell.getCooldownObject().isReady()) {
                    use(new Vector2(event.getStageX(), event.getStageY()));
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
            private void use(Vector2 useCoord){
                //
                if (spell.getPrototype() instanceof Spell.INonTarget){
                    spell.use(Level.getMap().getAllUnitsOnMap(spell.getAffectedTypes(), spell.isPierceImmunity()));
                    LevelMap.getLevel().removeEnergy(spell.getEnergyCost());
                    resetCooldown(spell);
//                    spell.getCooldownObject().resetCooldown();
                }else if(spell.getPrototype() instanceof Spell.IAoe){
                    stage.addListener(new AoeTargeter(((Spell.IAoe) spell.getPrototype()).getAoe(), spell.getAffectedTypes(), useCoord));
//                    stage.addListener(new )
//                    isTargeting = true;
                }else if(spell.getPrototype() instanceof Spell.ITarget){
                    stage.addListener(new SoloTargeter(spell.getAffectedTypes(), useCoord));
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
            private ProgressBar expBar;
            private Label level;
            private String levelString;
            private int levelNum;
//            private TextButton cooldown;

            public SpellTooltip(Skin skin) {
                super(SpellButton.this.spell.getPrototype().getName(), skin);
                getTitleLabel().setStyle(FontLoader.generateStyle(1, 14, Color.WHITE));
                setTouchable(Touchable.disabled);
//                getTitleLabel().setAlignment(Align.center);
                tooltipLabel = new Label(SpellButton.this.spell.getPrototype().getTooltip(), skin, "spell");
//                tooltipLabel.getStyle().font = FontLoader.generateSecondaryFont(12, Color.WHITE);
                tooltipLabel.getStyle().font.getData().markupEnabled = true;
//                cooldown = new TextButton(SpellButton.this.spell.getPrototype().getCooldown() + "", skin);

                setVisible(false);
                add(tooltipLabel).padBottom(5f).row();
//                setKeepWithinStage(true);


                level = new Label("", skin, "description");
                levelString = GDefence.getInstance().assetLoader.getWord("level") + " ";
                levelNum = spell.getPrototype().getLevel();

                pack();
                final float width = getWidth();
                expBar = new ProgressBar(0, spell.getPrototype().exp2nextLevel()[spell.getPrototype().getLevel() - 1], 0.2f, false,
                        GDefence.getInstance().assetLoader.getExpBarSkin()) {
                    @Override
                    public float getPrefWidth() {
                        return width + 10;
                    }
                };//add text inside
                expBar.getStyle().background.setMinHeight(20);
                expBar.getStyle().knob.setMinHeight(20);
                expBar.getStyle().knob.setMinWidth(0.1f);
                expBar.setValue(spell.getPrototype().getCurrentExp());

                add(level).align(Align.center).row();
                add(expBar).align(Align.center);
                hasChanged();
                pack();

            }
            public void init(Stage stage){
                stage.addActor(this);
            }

            @Override
            public void hasChanged() {
                level.setText(levelString + levelNum);
                if(levelNum != spell.getPrototype().getLevel()){
                    Cell c = getCell(expBar);
                    final float width = getWidth();
                    expBar = new ProgressBar(0, spell.getPrototype().exp2nextLevel()[spell.getPrototype().getLevel() - 1], 0.2f, false,
                            GDefence.getInstance().assetLoader.getExpBarSkin()) {
                        @Override
                        public float getPrefWidth() {
                            return width - 10;
                        }
                    };
                    expBar.getStyle().background.setMinHeight(20);
                    expBar.getStyle().knob.setMinHeight(20);
                    expBar.getStyle().knob.setMinWidth(0.1f);
                    expBar.setValue(spell.getPrototype().getCurrentExp());
                    c.setActor(expBar);
                    pack();
                }else {
                    expBar.setValue(spell.getPrototype().getCurrentExp());
                }

                levelNum = spell.getPrototype().getLevel();
                level.setText(levelString + levelNum);

                pack();
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
            SpellTooltip tooltip = new SpellTooltip(GDefence.getInstance().assetLoader.getSkin());
            TooltipListener t = new TooltipListener(tooltip, true);
            spell.setTooltip(tooltip);
//            t.setOffset(-50, -40);
            addListener(t);
//            System.out.println(this.getListeners());
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
                    ((TooltipListener) l).getTooltip().init(getStage());
                }
                if(l instanceof SpellButton.SpellThrower){
                    ((SpellButton.SpellThrower) l).init(getStage());
                }
            }
        }
        private void resetTargeting(){
            for (EventListener l:getListeners()) {
                if(l instanceof SpellButton.SpellThrower){
//                    ((SpellButton.SpellThrower) l);
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

//        @Override
//        public void act(float delta) {
////            super.act(delta);
//
//        }
    }

    private SpellButton[] buttons;

    public SpellButton[] getButtons() {
        return buttons;
    }

    public void physic(float delta){
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
            if(/*i < spells.size*/spells.get(i) != null) {
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
