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
package com.darkhouse.gdefence.InventorySystem.inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.InventorySystem.inventory.Tooltip.SlotTooltip;
import com.darkhouse.gdefence.InventorySystem.inventory.Tooltip.TooltipListener;


public class SlotActor extends ImageButton implements SlotListener {

	private Slot slot;
	private Skin skin;
	private Amount amount;

	public SlotActor(Skin skin, Slot slot) {
		super(createStyle(skin, slot.getPrototype()));
		this.slot = slot;
		this.skin = skin;

		slot.addListener(this);


		amount = new Amount(skin, slot);
		addActor(amount);
		//do align right //TODO
		amount.setAlignment(Align.center);
//		addTooltip();
	}

    @Override
    public float getPrefWidth() {
        return 60;
    }

    @Override
    public float getPrefHeight() {
        return 60;
    }

    public void addTooltip(Stage stage){
		SlotTooltip tooltip = new SlotTooltip(stage, slot, skin);
		tooltip.setTouchable(Touchable.disabled); // allows for mouse to hit tooltips in the top-right corner of the screen without flashing
		//Arsenal.getStage().addActor(tooltip);
		//((AbstractScreen)GDefence.getInstance().getScreen()).getStage().addActor(tooltip);
		addListener(new TooltipListener(tooltip, true));
	}
	public void notifyListeners(){
		for (EventListener e:getListeners()){
			if(e instanceof TooltipListener){
				((TooltipListener) e).getTooltip().hasChanged();
			}
		}
	}

	/*public*/ static ImageButtonStyle createStyle(Skin skin, Item prototype/* slot*/) {
		//TextureAtlas icons = LibgdxUtils.assets.get("icons/icons.atlas", TextureAtlas.class);
//		TextureAtlas icons = new TextureAtlas(Gdx.files.internal("icons/icons.atlas"));
        TextureAtlas icons = GDefence.getInstance().assetLoader.get("icons/icons.atlas", TextureAtlas.class);
		TextureRegion image;
		if (/*slot.getPrototype()*/prototype != null) {
			image = icons.findRegion(prototype.getTextureRegion());
		} else {
			image = icons.findRegion("nothing");
		}
		ImageButtonStyle style = new ImageButtonStyle(skin.get(ButtonStyle.class));
		style.imageUp = new TextureRegionDrawable(image);
		style.imageDown = new TextureRegionDrawable(image);

		return style;
	}

	public Slot getSlot() {
		return slot;
	}

	@Override
	public void hasChanged(AbstractSlot slot) {
		setStyle(createStyle(skin, slot.getPrototype()));
		amount.change();
	}


}
