/* Copyright (c) 2014 PixelScientists
 * 
 * The MIT License (MIT)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.darkhouse.gdefence.InventorySystem.inventory.Tooltip;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.InventorySystem.inventory.AbstractSlot;
import com.darkhouse.gdefence.InventorySystem.inventory.DropSlot;
import com.darkhouse.gdefence.InventorySystem.inventory.Slot;
import com.darkhouse.gdefence.InventorySystem.inventory.SlotListener;
import com.darkhouse.gdefence.Level.Ability.Tower.Ability;
import com.darkhouse.gdefence.Objects.ExpEarner;
import com.darkhouse.gdefence.Objects.SpellObject;
import com.darkhouse.gdefence.Objects.TowerObject;

public class SlotTooltip extends AbstractTooltip implements SlotListener {

	private Skin skin;

	private AbstractSlot slot;

	public SlotTooltip(Stage stage, AbstractSlot slot, Skin skin) {
		super("", skin);
		this.slot = slot;
		this.skin = skin;
		hasChanged(slot);
		slot.addListener(this);
		setVisible(false);

		stage.addActor(this);
//        hasChanged(slot);
//		GDefence.getInstance().getSmith().getStage().addActor(this);
	}

	@Override
	public void hasChanged(AbstractSlot slot) {
		if (slot.isEmpty()) {
			setVisible(false);
            pack();
			return;
		}

        getTitleLabel().setText(slot.getTitle());
//        getTitleLabel().setAlignment(Align.center);
        clear();
        Label label = new Label(slot.getTooltip(), skin, "spell");
//        System.out.println("label " + label.getText());
        label.getStyle().font.getData().markupEnabled = true;
//        label.setAlignment(Align.center);
        add(label).row();//row can bad

        if (slot instanceof Slot) {
            Slot s = ((Slot) slot);

            if (s.getLast() instanceof ExpEarner) {
                ExpEarner t = ((ExpEarner) s.getLast());

            /*FontLoader.generateStyle(16, Color.WHITE)*/
                Label level = new Label(GDefence.getInstance().assetLoader.getWord("level") + " " + t.getLevel() + "", skin, "description");//allow FontLoader load skin fonts
                add(level).align(Align.center).row();
                pack();//need to know real width
                final float width = getWidth();


                ProgressBar expBar = new ProgressBar(0, t.exp2nextLevel()[t.getLevel() - 1], 0.2f, false,
                        GDefence.getInstance().assetLoader.getExpBarSkin()) {
                    @Override
                    public float getPrefWidth() {
                        return width + 10;
                    }
                };//add text inside
                expBar.getStyle().background.setMinHeight(20);
                expBar.getStyle().knob.setMinHeight(20);
                expBar.getStyle().background.setMinWidth(50);
                expBar.getStyle().knob.setMinWidth(0.1f);
                expBar.setWidth(getWidth());
//                expBar.setSize(80, 20);//dont work first argument
                expBar.setValue(t.getCurrentExp());
                add(expBar);
            }

        }/*else if(slot instanceof DropSlot){ //not need other information
            DropSlot s = ((DropSlot) slot);


        }*/
        pack();
	}
    @Override
    public void hasChanged() {
        hasChanged(slot);
    }

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (slot.isEmpty()) {
			super.setVisible(false);
		}
	}


}
