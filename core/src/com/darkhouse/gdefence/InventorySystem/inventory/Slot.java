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
import com.darkhouse.gdefence.Objects.DetailObject;
import com.darkhouse.gdefence.Objects.GameObject;
import com.darkhouse.gdefence.Objects.SpellObject;
import com.darkhouse.gdefence.Objects.TowerObject;

/**
 * @author Daniel Holderbaum
 */
public class Slot {

	private Item prototype;

	private Array <GameObject> itemsArray;

	//private int amount;

	private Array<SlotListener> slotListeners = new Array<SlotListener>();

	public Slot(Item item, int amount) {
		this.prototype = item;
		//this.amount = amount;
		itemsArray = new Array<GameObject>();
		for (int i = 0; i < amount; i++){
			//itemsArray.add(prototype);
		}
	}

	/**
	 * Returns {@code true} in case this slot has the same prototype type and at
	 * least the same amount of items as the given other slot.
	 *
	 * @param //other
	 *            The other slot to be checked.
	 * @return {@code True} in case this slot has the same prototype type and at
	 *         least the same amount of items as the given other slot.
	 *         {@code False} otherwise.
	 */
//	public boolean matches(Slot other) {
//		return this.prototype == other.prototype && this.amount >= other.amount;
//	}

//	public boolean add(Item item, int amount) {
//		if (this.prototype == item || this.prototype == null) {
//			this.prototype = item;
//			//this.amount += amount;
//			if(prototype instanceof ItemEnum.Tower){
//				itemsArray.add(new TowerObject(((ItemEnum.Tower) prototype)));
//			}else if(prototype instanceof  ItemEnum.Spell){
//				itemsArray.add(new SpellObject(((ItemEnum.Spell) prototype)));
//			}else if(prototype instanceof  ItemEnum.Detail){
//				itemsArray.add(new DetailObject(((ItemEnum.Detail) prototype)));
//			}
//
//
//			updateArray();
//			notifyListeners();
//			return true;
//		}
//
//		return false;
//	}

	public boolean add(Array<GameObject> o){
		if(isEqualPrototype(prototype, o.first())) {
			itemsArray.addAll(o);
			return true;
		}
		return false;
	}

	private boolean isEqualPrototype(Item prototype, GameObject object){
		if(prototype instanceof ItemEnum.Tower && object instanceof TowerObject){
			return true;
		}else if(prototype instanceof ItemEnum.Spell && object instanceof SpellObject){
			return true;
		}else if(prototype instanceof  ItemEnum.Detail && object instanceof DetailObject){
			return true;
		}
		return false;
	}



	public boolean isEmpty() {
		return prototype == null || itemsArray.size <= 0;
	}

	public void addListener(SlotListener slotListener) {
		slotListeners.add(slotListener);
	}

	public void removeListener(SlotListener slotListener) {
		slotListeners.removeValue(slotListener, true);
	}




//	public boolean take(int amount) {
//		if (getAmount() >= amount) {
//			//this.amount -= amount;
//			if (getAmount() == 0) {
//				prototype = null;
//				//itemsArray.clear();
//			}
//			for (int i = 0; i < amount; i++){
////				itemsArray.removeIndex(itemsArray.size - 1);
//				itemsArray.removeValue(itemsArray.peek(), true);
//				updateArray();
//			}
//
//			notifyListeners();
//			return true;
//		}
//
//		return false;
//	}

	public Array<GameObject> take(int amount){
		Array<GameObject> tmpArr = new Array<GameObject>();
		if(getAmount() >= amount){
			for (int i = 0; i < amount; i++){
				tmpArr.add(itemsArray.peek());
				itemsArray.removeValue(itemsArray.peek(), true);
			}
		}
		return tmpArr;
	}

	public Array<GameObject> takeAll(){
		Array<GameObject> tmpArr = new Array<GameObject>(itemsArray);
		itemsArray.clear();
		return tmpArr;
	}






	private void updateArray(){
		//itemsArray.sort();

	}

	public void notifyListeners() {
		for (SlotListener slotListener : slotListeners) {
			slotListener.hasChanged(this);
		}
	}

	public Item getPrototype() {
		return prototype;
		//if(itemsArray.size > 0) {
		//	return itemsArray.get(itemsArray.size - 1);
		//}else return null;
	}

	public int getAmount() {
		//return amount;
		return itemsArray.size;
	}

	@Override
	public String toString() {
		return "Slot[" + prototype + ":" + getAmount() + "]";
	}
}
