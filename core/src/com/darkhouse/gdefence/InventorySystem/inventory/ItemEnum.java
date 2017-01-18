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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Level.Ability.Ability;
import com.darkhouse.gdefence.Level.Ability.Crit;
import com.darkhouse.gdefence.Level.Ability.MultiShot;
import com.darkhouse.gdefence.Level.Ability.PoisonArrow;
import com.darkhouse.gdefence.Level.Tower.AttackType;
import com.darkhouse.gdefence.User;

import java.util.ArrayList;


public enum ItemEnum {;
	//public static int[] exp2nextLvl = {30, 70, 130, 190, 260, 340, 430, 530};



	/*CRYSTAL_RED("redcrystal"), CRYSTAL_BLUE("bluecrystal"), CRYSTAL_GREEN("greencrystal"), CRYSTAL_YELLOW("yellowcrystal"), CRYSTAL_MAGENTA("magentacrystal"), CRYSTAL_CYAN(
			"cyancrystal"), CRYSTAL_ORANGE("orangecrystal"), CRYSTAL_VIOLET("violetcrystal"), TITANIUM("titanium"), PALLADIUM("palladium"), IRIDIUM("iridium"), RHODIUM("rhodium"), HULL(
			"hullbase"), CANNON("cannonbase"), RAY("raybase"), LAUNCHER("launcherbase"), DROID("droidbase"), MINE("dropperbase"), BATTERY("batterybase");
*/
	public enum Tower implements Item {
		Basic("Basic", AttackType.projectile, 10, 80, 10, 100, 1.2f){
			@Override
			protected void setTextures() {
				setTowerTexture(GDefence.getInstance().assetLoader.get("Tower/basicTower.png", Texture.class));
				setProjectileTexture(GDefence.getInstance().assetLoader.get("Projectiles/basic.png", Texture.class));
			}

			@Override
			public String getTextureRegion() {
				return "basic";
			}

			@Override
			protected void addAbilities() {

			}
		},
		Rock("Rock", AttackType.projectile, 20, 140, 25, 120, 1.0f){
			@Override
			protected void setTextures() {
				setTowerTexture(GDefence.getInstance().assetLoader.get("Tower/rockTower.png", Texture.class));
				setProjectileTexture(GDefence.getInstance().assetLoader.get("Projectiles/rock.png", Texture.class));
			}
			@Override
			public String getTextureRegion() {
				return "rock";
			}

			@Override
			protected void addAbilities() {
				abilities.add(new Crit(0.2f, 2.5f));
			}
		},
		Arrow("Arrow", AttackType.projectile, 20, 140, 15, 120, 0.8f){
			@Override
			protected void setTextures() {
				setTowerTexture(GDefence.getInstance().assetLoader.get("Tower/arrowTower.png", Texture.class));
				setProjectileTexture(GDefence.getInstance().assetLoader.get("Projectiles/arrow.png", Texture.class));
			}
			@Override
			public String getTextureRegion() {
				return "arrow";
			}

			@Override
			protected void addAbilities() {
				abilities.add(new PoisonArrow());
			}
		},
		Range("Range", AttackType.projectile, 20, 180, 15, 160, 1.0f){
			@Override
			protected void setTextures() {
				setTowerTexture(GDefence.getInstance().assetLoader.get("Tower/rangeTower.png", Texture.class));
				setProjectileTexture(GDefence.getInstance().assetLoader.get("Projectiles/range.png", Texture.class));
			}

			@Override
			public String getTextureRegion() {
				return "range";
			}

			@Override
			protected void addAbilities() {
				abilities.add(new MultiShot(2));
			}
		},
        Catapult("Catapult", AttackType.projectile, 25, 200, 30, 180, 1.5f){
            @Override
            protected void setTextures() {
                setTowerTexture(GDefence.getInstance().assetLoader.get("Tower/catapult.png", Texture.class));
                setProjectileTexture(GDefence.getInstance().assetLoader.get("Projectiles/catapult.png", Texture.class));
            }

            @Override
            public String getTextureRegion() {
                return "catapult";
            }


            @Override
            protected void addAbilities() {
                //
            }
        },
		Ballista("Ballista", AttackType.projectile, 25, 200, 20, 180, 0.8f){
			@Override
			protected void setTextures() {
				setTowerTexture(GDefence.getInstance().assetLoader.get("Tower/ballista.png", Texture.class));
				setProjectileTexture(GDefence.getInstance().assetLoader.get("Projectiles/ballista.png", Texture.class));
			}

			@Override
			public String getTextureRegion() {
				return "ballista";
			}


			@Override
			protected void addAbilities() {
				//
			}
		};


		//protected TowerType ID;
		private Texture towerTexture;
		private Texture projectileTexture;
		private String name;
		private AttackType attackType;
		private int range;
		private int dmg;
		//protected int speed;
		private float speedDelay;
		private int cost;
		private int globalCost;
		protected ArrayList<Ability> abilities;

		protected void addAbilities(){
		}

		protected void setTowerTexture(Texture towerTexture) {
			this.towerTexture = towerTexture;
		}

		protected void setProjectileTexture(Texture projectileTexture) {
			this.projectileTexture = projectileTexture;
		}

		protected abstract void setTextures();

		public Texture getTowerTexture() {
			return towerTexture;
		}
		public Texture getProjectileTexture() {
			return projectileTexture;
		}
		public ArrayList<Ability> getAbilities() {
			return abilities;
		}
		public AttackType getAttackType() {
			return attackType;
		}
		public int getRange() {
			return range;
		}
		public int getCost() {
			return cost;
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
		public float getSpeedDelay() {
			return speedDelay;
		}


		Tower(String name, AttackType attackType, int cost, int globalCost, int dmg, int range, float speedDelay) {
			this.name = name;
			this.globalCost = globalCost;
			this.attackType = attackType;
			this.cost = cost;
			this.dmg = dmg;
			this.range = range;
			this.speedDelay = speedDelay;


			abilities = new ArrayList<Ability>();
			addAbilities();
//			setTextures();

		}
		public static void init(){
			Basic.setTextures();
			Rock.setTextures();
			Arrow.setTextures();
			Range.setTextures();
            Catapult.setTextures();
			Ballista.setTextures();
            //for to last setTextures
//            for (int i = 0; i < Tower.values().length; i++){
//                Tower.values()[i].setTextures();
//            }
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
