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

import com.badlogic.gdx.utils.Array;

/**
 * @author Daniel Holderbaum
 */
public class Inventory {

	protected Array<Slot> slots;

	public Inventory() {
		initSlots();

		// create some random items
		//for (Slot slot : slots) {
		//	slot.add(Item.values()[MathUtils.random(0, Item.values().length - 1)], 1);
		//}

		// create a few random empty slots
		//for (int i = 0; i < 3; i++) {
		//	Slot randomSlot = slots.get(MathUtils.random(0, slots.size - 1));
		//	randomSlot.take(randomSlot.getAmount());
		//}
//		for(Item i:GDefence.getInstance().user.items){
//			Slot s = firstSlotWithItem(null);
//			if(s != null){
//				s.add(i, 1);
//			}
//		}
//		for (Slot slot : slots) {
//
//			//slot.add(Item.BATTERY, 1);
//		}

	}
	protected void initSlots(){
		int numberSlots = 35;
		slots = new Array<Slot>(numberSlots);
		for (int i = 0; i < numberSlots; i++) {
			slots.add(new Slot(null, 0));
		}
	}

	public int checkInventory(Item item) {
		int amount = 0;

		for (Slot slot : slots) {
			if (slot.getItem() == item) {
				amount += slot.getAmount();
			}
		}

		return amount;
	}

	public boolean store(Item item, int amount) {
		// first check for a slot with the same item type
		Slot itemSlot = firstSlotWithItem(item);
		if (itemSlot != null) {
			itemSlot.add(item, amount);
			return true;
		} else {
			// now check for an available empty slot
			Slot emptySlot = firstSlotWithItem(null);
			if (emptySlot != null) {
				emptySlot.add(item, amount);
				return true;
			}
		}

		// no slot to add
		return false;
	}

	public Array<Slot> getSlots() {
		return slots;
	}

	protected Slot firstSlotWithItem(Item item) {
		for (Slot slot : slots) {
			if (slot.getItem() == item) {
				return slot;
			}
		}

		return null;
	}

}
