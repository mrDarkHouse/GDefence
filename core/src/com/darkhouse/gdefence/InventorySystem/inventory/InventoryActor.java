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

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.Screens.BottomPanel.CombinedSlotSource;

/**
 * @author Daniel Holderbaum
 */
public class InventoryActor extends Window {
	protected Array<SlotActor> actorArray;
	protected Inventory inventory;
	private int rowNumber;
	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}

	public Array<SlotActor> getActorArray() {
		return actorArray;
	}

	public Inventory getInventory() {
		return inventory;
	}

	protected DragAndDrop dragAndDrop;

	public DragAndDrop getDragAndDrop() {
		return dragAndDrop;
	}

	public InventoryActor(Inventory inventory, DragAndDrop dragAndDrop, Skin skin) {
		super("Arsenal", skin);
		getTitleLabel().setAlignment(Align.center);
		setMovable(false);

		this.inventory = inventory;
		this.dragAndDrop = dragAndDrop;

		//TextButton closeButton = new TextButton("X", skin);
		//closeButton.addListener(new HidingClickListener(this));
		//add(closeButton).height(getPadTop());//
		//getButtonTable().add(closeButton).height(getPadTop());

		setDefaults();
		initCells(dragAndDrop, skin, inventory);

		pack();

		//setVisible(false);
		setVisible(true);
	}

	protected void setDefaults(){
		setPosition(100, 250);
		defaults().space(8);
		defaults().size(60, 60);
		row().fill().expandX();
		setRowNumber(7);
	}
	protected void initCells(DragAndDrop dragAndDrop, Skin skin, Inventory inventory){
		beforeInitCells();
		actorArray = new Array<SlotActor>();
		int i = 0;
		for (Slot slot : inventory.getSlots()) {
			SlotActor slotActor = new SlotActor(skin, slot);
			addSourceTarget(slotActor);
			actorArray.add(slotActor);
			add(slotActor);
			beforeRow(slot, i);
			i++;
			if (i % rowNumber == 0) {
				row();
			}
		}
		afterInitCells();
	}
	protected void addSourceTarget(SlotActor slotActor){
		dragAndDrop.addSource(new CombinedSlotSource(slotActor));
		dragAndDrop.addTarget(new SlotTarget(slotActor));
	}

	protected void beforeRow(Slot slot, int i){}

	protected void afterInitCells(){}

	protected void beforeInitCells(){}



	public void addSlots(InventoryActor inventory){
		for(SlotActor s:inventory.getActorArray()){
			dragAndDrop.addSource(new SlotSource(s));
			dragAndDrop.addTarget(new SlotTarget(s));

		}
	}

}
