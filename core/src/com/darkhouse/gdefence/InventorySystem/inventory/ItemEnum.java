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

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Level.Ability.Tower.*;
import com.darkhouse.gdefence.Level.Tower.AttackType;

import java.util.ArrayList;


public enum ItemEnum {;
	public enum Tower implements Item {

		//	        name        textures         attackType        projSp cost glCost dmg range speed abilities
		Basic(    "Basic",       "basic",      AttackType.projectile, 250, 10,  80,    10, 100, 23),//1.2
		Rock(     "Rock",        "rock",       AttackType.projectile, 250, 20,  140,   25, 120, 10, new DoubleAttack.P(3f)),//1.4,
		Arrow(    "Arrow",       "arrow",      AttackType.projectile, 250, 20,  140,   15, 120, 40, new Splash.P(150, 0.8f)),
		Range(    "Range",       "range",      AttackType.projectile, 350, 20,  180,   15, 160, 30, new SteelArrow.P(3, 200)),
        Short(    "Short",       "short",      AttackType.projectile, 250, 25,  180,   30, 180, 23),
        Mountain(  "Mountain",   "mountain",   AttackType.projectile, 250, 25,  180,   45, 130, 15, new Bash.P(0.2f, 1f, 20)),
        SteelArrow("Steel Arrow","steelArrow", AttackType.projectile, 250, 25,  200,   20, 140, 30),
        Catapult(  "Catapult",   "catapult",   AttackType.projectile, 250, 25,  240,   30, 180, 10, new Crit.P(0.2f, 2.5f)),
		Ballista(  "Ballista",   "ballista",   AttackType.projectile, 250, 25,  240,   20, 180, 40);


        public static Tower getTower(String name){
            for (Tower t:values()){
                if(t.getName().equals(name)){
                    return t;
                }
            }
            return null;
        }
		//protected TowerType ID;
		private Texture towerTexture;
		private Texture projectileTexture;
		private String name;
		private AttackType attackType;
        private int projectileSpeed;
		private int range;
		private int dmg;
		private int speed;
//		private float speedDelay;
		private int cost;
		private int globalCost;
		protected Array<Ability.AblityPrototype> abilities;

		public void setTextures(){
			towerTexture = GDefence.getInstance().assetLoader.get("Tower/" + textureName + ".png", Texture.class);
			projectileTexture = GDefence.getInstance().assetLoader.get("Projectiles/" + textureName + ".png", Texture.class);
		}

//		protected abstract void setTextures();

//        protected void setProjectileSpeed(){}

//        protected void setProjectileSpeed(int projectileSpeed) {
//            this.projectileSpeed = projectileSpeed;
//        }
		private String textureName;

        public Texture getTowerTexture() {
			return towerTexture;
		}
		public Texture getProjectileTexture() {
			return projectileTexture;
		}
		public Array<Ability.AblityPrototype> getAbilities() {
			return abilities;
		}
		public AttackType getAttackType() {
			return attackType;
		}
        public int getProjectileSpeed() {
            return projectileSpeed;
        }
        public int getRange() {
			return range;
		}
		public int getCost() {
			return cost;
		}

		@Override
		public String getTextureRegion() {
			return textureName;
		}

		public int getGlobalCost() {
			return globalCost;
		}
		public int getDmg() {
			return dmg;
		}
		public String getName() {
			return name;
		}
		public int getSpeed() {
			return speed;
		}




		Tower(String name, String textureName, AttackType attackType, int projectileSpeed, int cost, int globalCost,
			  int dmg, int range, int speed, Ability.AblityPrototype... abilities) {
			this.name = name;
			this.textureName = textureName;
			this.globalCost = globalCost;
			this.attackType = attackType;
			this.projectileSpeed = projectileSpeed;
			this.cost = cost;
			this.dmg = dmg;
			this.range = range;
            this.speed = speed;
			this.abilities = new Array<Ability.AblityPrototype>(abilities);

//            if(attackType == AttackType.projectile){
//                setProjectileSpeed();
//            }



//			abilities = new ArrayList<Ability>();
//			addAbilities();
//			setTextures();

		}
		public static void init(){
//			Basic.setTextures();
//			Rock.setTextures();
//			Arrow.setTextures();
//			Range.setTextures();
//            Short.setTextures();
//            Mountain.setTextures();
//            SteelArrow.setTextures();
//            Catapult.setTextures();
//			Ballista.setTextures();
            //for to last setTextures
            for (int i = 0; i < Tower.values().length; i++){
                Tower.values()[i].setTextures();
            }
		}

	}
	public enum Spell implements Item{
		Firestorm("Firestorm", 20, 100) {
			@Override
			public String getTextureRegion() {
				return null;
			}

			@Override
			public int getGlobalCost() {
				return 0;
			}
		},
		IceBlast("Ice Blast", 10, 60) {
			@Override
			public String getTextureRegion() {
				return null;
			}


			@Override
			public int getGlobalCost() {
				return 0;
			}
		};


		private String name;
		private int dmg;
		private int manaCost;

		Spell(String name, int dmg, int manaCost) {
			this.name = name;
			this.dmg = dmg;
			this.manaCost = manaCost;
		}


	}

	//Иличев Глеб


	public enum Detail implements Item{
		Recipe{
			@Override
			public String getTextureRegion() {
				return "recipe";
			}

			@Override
			public int getGlobalCost() {
				return 0;
			}
		}

	}



	//Detail;


	//protected String textureRegion;




//	private Item(String textureRegion) {
//		this.textureRegion = textureRegion;
//	}


//	public String getTextureRegion() {
//		return textureRegion;
//	}

}
