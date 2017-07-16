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
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.Level.Ability.Tower.*;
import com.darkhouse.gdefence.Level.Tower.AttackType;
import com.darkhouse.gdefence.Level.Tower.Tower;
import com.darkhouse.gdefence.Objects.TowerObject;
import com.darkhouse.gdefence.User;

import java.util.EnumSet;

import static com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum.Tower.Rock;
import static com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum.Tower.setComponents;


public enum ItemEnum {;

    public static void addItemById(int id, int count, User user){//bad way, but it for few time



//        (id > 1 && id < 5) ? System.out.println("1 to 5"):System.out.println("1 to 5");
//                : System.out.println("testing case 1 to 5") (id > 5 && id < 7)  ? System.out.println("testing case 1 to 5")
//                : (id > 7 && id < 8)  ? System.out.println("testing case 1 to 5")
//                : (id > 8 && id < 9)  ? System.out.println("testing case 1 to 5")


        switch (id){
            case 1:case 2:case 3:case 4:case 5:case 6:{
                user.addGems(User.GEM_TYPE.values()[id - 1], count);
                break;
            }case 120:case 121:
                user.openResearch(User.Research.values()[id - 120]);
                break;

        }
    }
//    public static int getIdOfItem(Item i){
//        if(i instanceof User.GEM_TYPE){
//
//        }
//    }
    public static Item getItemById(int id){
        switch (id){
            case 1:case 2:case 3:case 4:case 5:case 6:{
                return User.GEM_TYPE.values()[id - 1];
            }case 120:case 121:
                return User.Research.values()[id - 120];
            default:return null;
        }
    }
//    public static String getItemNameById(int id){
//        getItemById(id).getName();
//        switch (id){
//            case 1:case 2:case 3:case 4:case 5:case 6: return FontLoader.firstCapitalLetter(User.GEM_TYPE.values()[id - 1].name() + " gem");
//            case 120:case 121: return "" + FontLoader.firstCapitalLetter(User.Research.values()[id - 120].name());
//
//
//            default:return "Some shit id";
//        }
//
//
//
//    }
    public static String getItemTextureById(int id){
        switch (id){
            case 1:case 2:case 3:case 4:case 5:case 6: return User.GEM_TYPE.values()[id - 1].getTexturePath();
            case 120:case 121: return "" + FontLoader.firstCapitalLetter(User.Research.values()[id - 120].getTexturePath());


            default:return "Some shit id";
        }



    }



	public enum Tower implements Item {

		//	        texturePath        textures              attackType         projSp cost glCost dmg range speed abilities
		Basic(    "Basic",          "basic",         AttackType.projectile, 250, 10,  80,    10, 100, 23),//1.2
		Rock(     "Rock",           "rock",          AttackType.projectile, 400, 20,  140,   1, 120,  30, new DoubleAttack.P(3f, 2, new DoubleAttack.G(0.5f))),//1.4,
		Arrow(    "Arrow",          "arrow",         AttackType.projectile, 250, 20,  140,   15, 120, 40, new Bash.P(0.2f, 1f, 20, new Bash.G(0.05f, 0.5f, 10))),
		Range(    "Range",          "range",         AttackType.projectile, 350, 20,  180,   15, 160, 30),
        Short(    "Short",          "short",         AttackType.projectile, 250, 25,  180,   30, 180, 23),
        Mountain( "Mountain",       "mountain",      AttackType.projectile, 250, 25,  180,   45, 130, 15, new Bash.P(0.2f, 1f, 20, new Bash.G(0.05f, 0.5f, 10))),
//        SteelArrow("Steel Arrow","steelArrow", AttackType.projectile, 250, 25,  200,   20, 140, 30),
        Catapult(  "Catapult",      "range",      AttackType.projectile, 250, 25,  240,   30, 180, 10, new Splash.P(200, 0.4f)),
		Ballista(  "Ballista",      "range",      AttackType.projectile, 250, 25,  240,   20, 180, 40, new SteelArrow.P(5, 300)),
        Spear(     "Spear",         "range",         AttackType.projectile, 250, 25, 240, 20, 180, 40),
        CrossBow(  "CrossBow",      "range",      AttackType.projectile, 250, 25,  240,   20, 180, 40),
        Gun(       "Gun",           "range",           AttackType.projectile, 250, 25,  240,   20, 180, 40),
        Rifle(     "Rifle",         "range",         AttackType.projectile, 250, 25,  240,   20, 180, 40),
        MachineGun("Machine Gun",   "range",         AttackType.projectile, 250, 25,  240,   20, 180, 40, new ShotDelay.P(1f)),
        Sniper(    "Sniper",        "range",        AttackType.projectile, 250, 25,  240,   20, 180, 40, new ShotDelay.P(1f)),
        Shotgun(   "Shotgun",       "range",       AttackType.projectile, 250, 25,  240,   20, 180, 40, new BuckShot.P(5, 20f, 1)),
        DoubleBarrel("Double Barrel","range", AttackType.projectile, 250, 25,  240,   20, 180, 40, new DoubleAttack.P(3f, 1, new DoubleAttack.G(0.5f)), new BuckShot.P(5, 20f, 1)),
        Cannon(    "Cannon",        "range",        AttackType.projectile, 250, 25,  240,   20, 180, 40, new Splash.P(300, 0.8f)),
        Rocket(    "Rocket",        "range",        AttackType.projectile, 250, 25,  240,   20, 180, 40, new Splash.P(300, 0.8f)),
        Missle(    "Missle",        "range",        AttackType.projectile, 250, 25,  240,   20, 180, 40, new Splash.P(300, 0.8f), new DoubleAttack.P(3f, 1, new DoubleAttack.G(0.5f))),
        Glaive(    "Glaive",        "range",        AttackType.projectile, 250, 25,  240,   20, 180, 40, new Bounce.P(2, 0.2f, 200)),
        MultiShot( "MultiShot",     "range",     AttackType.projectile, 250, 25,  240,   20, 180, 40, new MultiShot.P(2)),
        SteamMachine("Steam Machine","range", AttackType.projectile, 250, 25,  240,   20, 180, 40);


        protected static void setComponents(){
            Rock.components.add(new TowerObject(Basic, 3, 1, 1));
            Arrow.components.add(new TowerObject(Basic, 1, 3, 1));
            Range.components.add(new TowerObject(Basic, 1, 1, 3));
            Short.components.add(new TowerObject(Rock, 0, 0, 0));
            Spear.components.add(new TowerObject(Arrow, 0, 0, 0));
            Gun.components.add(new TowerObject(Short, 0, 0, 0));
            Gun.components.add(new TowerObject(Spear, 0, 0, 0));
            Mountain.components.add(new TowerObject(Rock, 0, 0, 0));
            Mountain.components.add(new TowerObject(Range, 0, 0, 0));
            CrossBow.components.add(new TowerObject(Arrow, 0, 0, 0));
            CrossBow.components.add(new TowerObject(Range, 0, 0, 0));
            Rifle.components.add(new TowerObject(Gun, 0, 0, 0));
            Catapult.components.add(new TowerObject(Mountain, 0, 0, 0));
            Catapult.components.add(new TowerObject(SteamMachine, 0, 0, 0));
            Ballista.components.add(new TowerObject(CrossBow, 0, 0, 0));
            Ballista.components.add(new TowerObject(SteamMachine, 0, 0, 0));
            MachineGun.components.add(new TowerObject(Rifle, 0, 0, 0));
            MachineGun.components.add(new TowerObject(SteamMachine, 0, 0, 0));
            Cannon.components.add(new TowerObject(Gun, 0, 0, 0));
            Cannon.components.add(new TowerObject(Catapult, 0, 0, 0));
            Glaive.components.add(new TowerObject(Spear, 0, 0, 0));
            Glaive.components.add(new TowerObject(Ballista, 0, 0, 0));
            Rocket.components.add(new TowerObject(Cannon, 0, 0, 0));
            MultiShot.components.add(new TowerObject(Glaive, 0, 0, 0));
            Shotgun.components.add(new TowerObject(Gun, 0, 0, 0));
            Shotgun.components.add(new TowerObject(MultiShot, 0, 0, 0));
            Missle.components.add(new TowerObject(Rocket, 0, 0, 0));
            Sniper.components.add(new TowerObject(MachineGun, 4, 0, 2));//
            DoubleBarrel.components.add(new TowerObject(Shotgun, 0, 0, 0));
            DoubleBarrel.components.add(new TowerObject(Rifle, 0, 0, 0));
            SteamMachine.components.add(new TowerObject(Rock, 0, 0, 0));


            Short.researchNeed.add(User.Research.Powder);
            SteamMachine.researchNeed.add(User.Research.Steam);



        }


        public static Tower getTower(String name){
            for (Tower t:values()){
                if(t.getName().equals(name)){
                    return t;
                }
            }
            return null;
        }

        static {
//            try {
                setComponents();
//            }catch (Exception e){
//                e.printStackTrace();
//            }
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
        private Array<TowerObject> components = new Array<TowerObject>();
        private Array<User.Research> researchNeed = new Array<User.Research>();

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

        @Override
        public int getID() {
            return ordinal() + 50;
        }

        public String getName() {
            return name;
        }

        @Override
        public String getTooltip() {
            String s = "Damage: " + getDmg() + System.getProperty("line.separator")
                    + "Attack range: " + getRange() + System.getProperty("line.separator")
                    + "Attack speed: " + getSpeed() + "(" + com.darkhouse.gdefence.Level.Tower.Tower.getAttackSpeedDelay(getSpeed()) + ")" + System.getProperty("line.separator")
                    + "Energy cost: " + getCost() + System.getProperty("line.separator");
            for (int i = 0; i < getAbilities().size; i++){
                s += System.getProperty("line.separator");
                Ability.AblityPrototype a = getAbilities().get(i);
                s += a.getName();
                s += a.getGemStat();
            }
            return s;
        }

        public int getSpeed() {
			return speed;
		}
        public Array<TowerObject> getComponents() {
            return components;
        }
        public Array<User.Research> getResearchNeed() {
            return researchNeed;
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

        public int getDmg() {
            return dmg;
        }
        public int getManaCost() {
            return manaCost;
        }

        Spell(String name, int dmg, int manaCost) {
			this.name = name;
			this.dmg = dmg;
			this.manaCost = manaCost;
		}


        @Override
        public int getID() {
            return ordinal() + 200;
        }

        @Override
        public String getName() {
            return name();
        }

        @Override
        public String getTooltip() {//
            return "Dmg: " + getDmg() + System.getProperty("line.separator") +
                    "Mana Cost: " + getManaCost();
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
		},;


        @Override
        public int getID() {
            return ordinal() + 10;
        }

        @Override
        public String getName() {
            return name();
        }

        @Override
        public String getTooltip() {
            return name();
        }


    }
//    public enum Gem implements Item{
//        Red {
//            @Override
//            public String getTextureRegion() {
//                return "red";
//            }
//
//            @Override
//            public int getGlobalCost() {
//                return 0;
//            }
//        },
//        Yellow {
//            @Override
//            public String getTextureRegion() {
//                return "yellow";
//            }
//
//            @Override
//            public int getGlobalCost() {
//                return 0;
//            }
//        },
//        Blue {
//            @Override
//            public String getTextureRegion() {
//                return "blue";
//            }
//
//            @Override
//            public int getGlobalCost() {
//                return 0;
//            }
//        }
//
//    }
//    public enum Research implements Item{
//        Steam {
//            @Override
//            public String getTextureRegion() {
//                return "steam";
//            }
//
//            @Override
//            public int getGlobalCost() {
//                return 0;
//            }
//        },
//        Powder {
//            @Override
//            public String getTextureRegion() {
//                return "powder";
//            }
//
//            @Override
//            public int getGlobalCost() {
//                return 0;
//            }
//        }
//
//
//    }



	//Detail;


	//protected String textureRegion;




//	private Item(String textureRegion) {
//		this.textureRegion = textureRegion;
//	}


//	public String getTextureRegion() {
//		return textureRegion;
//	}

}
