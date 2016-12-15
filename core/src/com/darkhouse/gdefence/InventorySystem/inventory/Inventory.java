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
import com.darkhouse.gdefence.Objects.GameObject;

/**
 * @author Daniel Holderbaum
 */
public class Inventory {

	protected Array<Slot> slots;

	public Inventory() {
		initSlots(35);

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

	public Inventory(Inventory copyInventory){
		this();
		copy(copyInventory);
	}
	public void copy(Inventory inventory){
//		for(int i = 0; i < inventory.getSlots().size; i++){
//			slots.get(i).add(inventory.slots.get(i).getAll());
//		}
//		slots = new Array<Slot>(inventory.getSlots());
		for (int i = 0; i < inventory.getSlots().size; i++){//slots.size or inventory.getSlots.size
			slots.get(i).copy(inventory.getSlots().get(i));
		}

	}


	public Inventory copy(){
		Inventory newInventory = new Inventory();
//		for(int i = 0; i < slots.size; i++){
//			//newInventory.slots.clear();
//			//newInventory.slots.addAll(slots);
//			newInventory.slots.get(i).add(slots.get(i).getPrototype(), slots.get(i).getAmount());
//		}
//		newInventory.slots = new Array<Slot>(slots);
		for (int i = 0; i < slots.size; i++){
			//newInventory.slots.get(i).copy(slots.get(i));
			newInventory.slots.get(i).add(slots.get(i).getAll());
		}
		return newInventory;
	}




	protected void initSlots(int numberSlots){
		slots = new Array<Slot>(numberSlots);
		for (int i = 0; i < numberSlots; i++) {
			slots.add(new Slot(null, 0));
		}
	}

	public int checkInventory(Item item) {
		int amount = 0;

		for (Slot slot : slots) {
			if (slot.getPrototype() == item) {
				amount += slot.getAmount();
			}
		}

		return amount;
	}

	public boolean store(Item item, int amount) {
		// first check for a slot with the same item type
		Slot itemSlot = firstSlotWithItem(item);
		if (itemSlot != null) {
//			itemSlot.add(item, amount);
			itemSlot.add(Slot.genereateStartObjects(item, amount));
			return true;
		} else {
			// now check for an available empty slot
			Slot emptySlot = firstSlotWithItem(null);
			if (emptySlot != null) {
				emptySlot.add(Slot.genereateStartObjects(item, amount));
				return true;
			}
		}

		// no slot to add
		return false;
	}
    public boolean store(GameObject object) {
        Slot itemSlot = firstSlotWithItem(object.getPrototype());
        if (itemSlot != null) {
            itemSlot.add(object);
            return true;
        } else {
            // now check for an available empty slot
            Slot emptySlot = firstSlotWithItem(null);
            if (emptySlot != null) {
                emptySlot.add(object);
                return true;
            }
        }


        return false;
    }

	public Array<Slot> getSlots() {
		return slots;
	}

	protected Slot firstSlotWithItem(Item item) {
		for (Slot slot : slots) {
			if (slot.getPrototype() == item) {
				return slot;
			}
		}

		return null;
	}

}
