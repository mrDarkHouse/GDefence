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
import com.darkhouse.gdefence.Objects.Recipe;
import com.darkhouse.gdefence.Objects.TowerObject;

import java.util.HashMap;

/**
 * @author Daniel Holderbaum
 */
public class Inventory {

	protected Array<Slot> slots;
	private Class<? extends GameObject> type;

	public Class<? extends GameObject> getType() {
		return type;
	}

	public Inventory(Class<? extends GameObject> type, int slots) {
		initSlots(type, slots);//35
		this.type = type;
	}
	public Inventory(Class<? extends GameObject> type, int slots, int maxItems) {
		initSlots(type, slots, maxItems);//35
		this.type = type;
	}

	public Inventory(Inventory copyInventory){
		this(copyInventory.getType(), copyInventory.getSlots().size);
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
		Inventory newInventory = new Inventory(type, slots.size);
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




	protected void initSlots(Class<? extends GameObject> type, int numberSlots){
		slots = new Array<Slot>(numberSlots);
		for (int i = 0; i < numberSlots; i++) {
			slots.add(new Slot(type/*, null, 0*/));
		}
	}
	protected void initSlots(Class<? extends GameObject> type, int numberSlots, int maxItems){
		slots = new Array<Slot>(numberSlots);
		for (int i = 0; i < numberSlots; i++) {
			slots.add(new Slot(maxItems, type/*, null, 0*/));
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

	public boolean storeNew(Item item, int amount) {
		// first check for a slot with the same item type
		Slot itemSlot = firstSlotWithItem(item);
		if (itemSlot != null) {
//			itemSlot.add(item, amount);
			itemSlot.add(TowerObject.generateStartObjects(item, amount));
			return true;
		} else {
			// now check for an available empty slot
			Slot emptySlot = firstSlotWithItem(null);
			if (emptySlot != null) {
				emptySlot.add(TowerObject.generateStartObjects(item, amount));
				return true;
			}
		}

		// no slot to add
		return false;
	}
    public boolean store(GameObject o, int slotNumber){//can rework for Array of GameObjects
        if(slotNumber > slots.size || slotNumber < 0) throw new IllegalArgumentException("illegal slot");
        Slot s = slots.get(slotNumber);
        if(s.isEmpty() || s.getPrototype() == o.getPrototype()){
//            for (GameObject o:objects){
                if(!s.add(o))return false;
//            }
        }
        return true;
    }
    public boolean store(GameObject object) {
        Slot itemSlot = firstSlotWithItem(object.getPrototype());
        if (itemSlot != null && !(object instanceof Recipe)) {//
            itemSlot.add(object);
            return true;
        } else {
            //check for an available empty slot
            Slot emptySlot = firstSlotWithItem(null);
            if (emptySlot != null) {
                emptySlot.add(object);
                return true;
            }
        }


        return false;
    }
	public boolean store(Array<? extends GameObject> objects) {//Array<GameObject>
		for (GameObject o:objects){
			if(!store(o)) return false;
		}

		return true;
	}
    public void flush(){
        for (Slot s:slots){
            s.takeAll();
//            for (GameObject o:s.getAll()){
//
//            }
        }
    }

    public String getSave(){
        String save = "";
        for (int i = 0; i < slots.size; i++){
            if(!slots.get(i).isEmpty()) {//
                for (GameObject o : slots.get(i).getAll()) {
//                map.put(i + "", o.getSaveCode());
                    save += i + "-" + o.getSaveCode() + "/";
                }
//                save += System.getProperty("line.separator");
            }
        }
        return save;
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
