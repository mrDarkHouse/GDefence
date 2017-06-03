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
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.darkhouse.gdefence.Objects.*;


public class Slot extends AbstractSlot{


	private Item prototype;
	private Class<? extends GameObject> type;

	public Class<? extends GameObject> getType() {
		return type;
	}

	public void setPrototype(Item newPrototype) {
		if(prototype != newPrototype) prototype = newPrototype;
		notifyListeners();
	}


	private Array <GameObject> itemsArray;


	//private int amount;

//	private Array<SlotListener> slotListeners = new Array<SlotListener>();

	public Slot(Class<? extends GameObject> type/*, Item item, int amount*/) {//created empty slot
//		this.prototype = item;
		this.type = type;
		//this.amount = amount;
        itemsArray = new Array<GameObject>();
//		itemsArray = new Array<GameObject>(GameObject.generateStartObjects(item, amount));//
//		for (int i = 0; i < amount; i++){
//			itemsArray.add(GameObject.generateStartObjects(item, 1));
//		}
	}
	public void copy(Slot s){
//		itemsArray.clear();
		itemsArray = new Array<GameObject>(s.itemsArray);//
		setPrototype(s.getPrototype());

		//itemsArray.addAll(s.itemsArray);
//		for (int i = 0; i < s.itemsArray.size; i++){
//			itemsArray.add(s.itemsArray.get(i));
//		}
	}

	public boolean matches(Slot other) {
//		if(getType() != other.getType()) return false;
        if(getPrototype() == ItemEnum.Detail.Recipe && other.getPrototype() == ItemEnum.Detail.Recipe) {
            return (((Recipe) getLast()).getTower() == ((Recipe) other.getLast()).getTower());
        }
		return this.prototype == other.prototype;

	}

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
//	public T getInstance() throws InstantiationException, IllegalAccessException{
//		return tClass.newInstance();
//	}
//
//	public Array<T> genereateStartObjects(Class<T> type, int amount){
//		Array<T> tmp = new Array<T>();
//		try {
//			for (int i = 0; i < amount; i++) {
//				tmp.add(type.newInstance());
//			}
//		} catch (InstantiationException e) {
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		}
//
//		return tmp;
//	}



	public boolean add(Array<? extends GameObject> o){//boolean not need now
		if(isEqualPrototype(prototype, o.first()) || prototype == null) {
			itemsArray.addAll(o);
//			if(prototype != o.peek().getPrototype()) prototype = o.peek().getPrototype();
//
//			notifyListeners();
			setPrototype(o.peek().getPrototype());
			return true;
		}
		return false;
	}
    public boolean add(GameObject o){//boolean not need now
        if(isEqualPrototype(prototype, o) || prototype == null) {
            itemsArray.add(o);
//			if(prototype != o.peek().getPrototype()) prototype = o.peek().getPrototype();
//
//			notifyListeners();
            setPrototype(o.getPrototype());
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

//	public void addListener(SlotListener slotListener) {
//		slotListeners.add(slotListener);
//	}
//
//	public void removeListener(SlotListener slotListener) {
//		slotListeners.removeValue(slotListener, true);
//	}




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
//	public GameObject takeLast(){
//		GameObject o = itemsArray.peek();
//		itemsArray.removeValue(itemsArray.peek(), true);
//		return o;
//	}
	public GameObject getLast(){
		if(itemsArray.size > 0) {
			return itemsArray.peek();
		}else {
			return null;
		}
	}
	public Array<GameObject> get(int amount){
		Array<GameObject> tmpArr = new Array<GameObject>();
		if(getAmount() >= amount){
			for (int i = 0; i < amount; i++){
				tmpArr.add(itemsArray.get(itemsArray.size - 1 - i));
				//itemsArray.removeValue(itemsArray.peek(), true);
			}
		}
		return tmpArr;
	}
	public Array<GameObject> getAll(){
		return itemsArray;
	}


	public Array<GameObject> take(int amount){
		Array<GameObject> tmpArr = new Array<GameObject>();
		if(getAmount() >= amount){
			for (int i = 0; i < amount; i++){
				tmpArr.add(itemsArray.peek());
				itemsArray.removeValue(itemsArray.peek(), true);
			}
		}
		if(getAmount() == 0) setPrototype(null);//notify listeners getDmg twice
//		prototype = null;
//
		else notifyListeners();//fixed(?)
		return tmpArr;
	}

	public Array<? extends GameObject> takeAll(){
		Array<GameObject> tmpArr = new Array<GameObject>(itemsArray);
		itemsArray.clear();
//		prototype = null;
//
//		notifyListeners();
		setPrototype(null);
		return tmpArr;
	}






	private void updateArray(){
		//itemsArray.sort();

	}

//	public void notifyListeners() {
//		for (SlotListener slotListener : slotListeners) {
//			slotListener.hasChanged(this);
//		}
//	}

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
    public String getTitle() {
        return getLast().getName();
    }

    @Override
    public String getTooltip() {//
        return getLast().getTooltip();
    }

    @Override
	public String toString() {
		return "Slot[" + prototype + ":" + getAmount() + "]";
	}
}
