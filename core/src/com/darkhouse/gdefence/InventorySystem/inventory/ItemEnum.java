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

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.darkhouse.gdefence.Level.Ability.Ability;
import com.darkhouse.gdefence.Level.Tower.AttackType;

import java.util.ArrayList;

/**
 * @author Daniel Holderbaum
 */
public enum ItemEnum {;



	/*CRYSTAL_RED("redcrystal"), CRYSTAL_BLUE("bluecrystal"), CRYSTAL_GREEN("greencrystal"), CRYSTAL_YELLOW("yellowcrystal"), CRYSTAL_MAGENTA("magentacrystal"), CRYSTAL_CYAN(
			"cyancrystal"), CRYSTAL_ORANGE("orangecrystal"), CRYSTAL_VIOLET("violetcrystal"), TITANIUM("titanium"), PALLADIUM("palladium"), IRIDIUM("iridium"), RHODIUM("rhodium"), HULL(
			"hullbase"), CANNON("cannonbase"), RAY("raybase"), LAUNCHER("launcherbase"), DROID("droidbase"), MINE("dropperbase"), BATTERY("batterybase");
*/
	public enum Tower implements Item {
		Basic(AttackType.projectile, 25, 10, 0.5f){
			@Override
			public String getTextureRegion() {
				return "redcrystal";
			}

			@Override
			public String getTooltip() {
				return "Dmg: " + getDmg() + System.getProperty("line.separator")
						+ "Speed: " + getSpeedDelay() + System.getProperty("line.separator") + "Cost: " + getCost();
			}
		},
		Rock(AttackType.projectile, 25, 10, 0.5f){
			@Override
			public String getTextureRegion() {
				return "bluecrystal";
			}
			@Override
			public String getTooltip() {
				return "Dmg: " + getDmg() + System.getProperty("line.separator")
						+ "Speed: " + getSpeedDelay() + System.getProperty("line.separator") + "Cost: " + getCost();
			}
		},
		Arrow(AttackType.projectile, 25, 10, 0.5f){
			@Override
			public String getTextureRegion() {
				return "greencrystal";
			}
			@Override
			public String getTooltip() {
				return "Dmg: " + getDmg() + System.getProperty("line.separator")
						+ "Speed: " + getSpeedDelay() + System.getProperty("line.separator") + "Cost: " + getCost();
			}
		},
		Range(AttackType.projectile, 25, 10, 0.5f){
			@Override
			public String getTextureRegion() {
				return "Range";
			}
			@Override
			public String getTooltip() {
				return "Dmg: " + getDmg() + System.getProperty("line.separator")
						+ "Speed: " + getSpeedDelay() + System.getProperty("line.separator") + "Cost: " + getCost();
			}
		};


		//protected TowerType ID;
		private String name;
		private AttackType attackType;
		private int dmg;
		//protected int speed;
		private float speedDelay;
		private int cost;
		private ArrayList<Ability> abilities;

		public ArrayList<Ability> getAbilities() {
			return abilities;
		}
		public AttackType getAttackType() {
			return attackType;
		}
		public int getCost() {
			return cost;
		}
		public int getDmg() {
			return dmg;
		}
		public String getName() {
			return name;
		}
		public float getSpeedDelay() {
			return speedDelay;
		}

		Tower(AttackType attackType, int cost, int dmg, float speedDelay) {
			this.name = name;
			this.attackType = attackType;
			this.cost = cost;
			this.dmg = dmg;
			this.speedDelay = speedDelay;

		}
	}
	public enum Spell {
		Firestorm("Firestorm", 20, 100),
		IceBlast("Ice Blast", 10, 60);


		private String name;
		private int dmg;
		private int manaCost;

		Spell(String name, int dmg, int manaCost) {
			this.name = name;
			this.dmg = dmg;
			this.manaCost = manaCost;
		}
	}




	//Detail;


	protected String textureRegion;




//	private Item(String textureRegion) {
//		this.textureRegion = textureRegion;
//	}


//	public String getTextureRegion() {
//		return textureRegion;
//	}

}
