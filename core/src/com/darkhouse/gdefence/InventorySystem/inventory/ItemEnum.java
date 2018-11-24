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
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.Level.Ability.Spell.*;
import com.darkhouse.gdefence.Level.Ability.Tower.*;
import com.darkhouse.gdefence.Level.Tower.AttackType;
import com.darkhouse.gdefence.Level.Tower.Tower;
import com.darkhouse.gdefence.Objects.SpellObject;
import com.darkhouse.gdefence.Objects.TowerObject;
import com.darkhouse.gdefence.User;


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
            }case 120:case 121: {
                user.openResearch(User.Research.values()[id - 120]);
                break;
            }case 150:case 151:case 152:case 153:case 154:{
                user.getSpellInventory().store(getSpellById(id));
            }
        }
    }
    public static SpellObject getSpellById(int id){
        switch (id){
            case 150:{
                return new EchoSmash.P(10, 25, 8, 3f, 150, new EchoSmash.G(4, 1f, 100, new int[]{2, 2, 2}));
            }case 151:{
                return new SuddenDeath.P(10, 20, 0.5f, 0, 0.1f, new SuddenDeath.G(0.5f, 3f, 0.1f, new int[]{3, 3, 2}));
            }case 152:{
                return new EmergencyRepair.P(15, 30, 3, new EmergencyRepair.G(2, new int[]{3, 0, 0}));
            }case 153:{
                return new IceBlast.P(10, 15, 30, 0.7f, 2, 200, new IceBlast.G(15, 0.1f, 1, new int[]{3, 2, 3}));
            }case 154:{
                return new GlobalSlow.P(8, 24, 0.3f, 4, 0, new GlobalSlow.G(0.1f, 1, 4f, new int[]{4, 4, 2}));
            }default: throw new RuntimeException("no spell found with id: " + id);
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
            }case 120:case 121: {
                return User.Research.values()[id - 120];
            }case 150:case 151:case 152:case 153:case 154:{
                return ItemEnum.Spell.values()[id - 150];
            }
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
    private void addSpell(int id){

    }



	public enum Tower implements Item {

		//	  texturePath              attackType         projSp cost glCost  recCost dmg range speed /n abilities
		Basic(   AttackType.projectile, 250, 10,   80,     0,   10, 80, 20/*,
                new Bash.P(0.2f, 1f, 20, new Bash.G(0.05f, 0.5f, 10, new int[]{3, 3, 3}))*//*,
                new SteelArrow.P(2, 300, new SteelArrow.G(1, 50, new int[]{3, 2, 0})),
                new ShotDelay.P(1f, new ShotDelay.G(0.2f, new int[]{2, 0, 0}))*/),

		Rock(    AttackType.projectile, 400, 15,  120,    70,   22, 90,  25/*,
                new BuckShot.P(5, 20f, new BuckShot.G(1, 5f, new int[]{3, 2, 0})),
                new SpreadAttack.P(3f, 2, new SpreadAttack.G(0.5f, new int[]{2, 0, 0}))*/),

		Arrow(   AttackType.projectile, 250, 15,  120,    70,   15, 90,  32/*,
                new Bash.P(0.5f, 1f, 20, new Bash.G(0.05f, 0.5f, 10, new int[]{3, 3, 3})),
                new Crit.P(0.3f, 2f,     new Crit.G(0.05f, 0.5f,     new int[]{2, 2, 0}))*/),

        Range(   AttackType.projectile, 350, 15,  120,    70,   15, 115, 30/*,
                new FireArrow.P(10, 0.2f, 2, new FireArrow.G(5, 1f, new int[]{2, 2, 0})),
                new HunterSpeed.P(5, 30, 5, new HunterSpeed.G(10, 1, 1, new int[]{3, 3, 2}))*/),

        Short(   AttackType.projectile, 500, 20,  200,   120,   45, 70,  35),
        Mountain( /*"mountain", */     AttackType.projectile, 250, 20,  200,   120,   35, 110, 30,
                new Bash.P(0.25f, 2f, 20, new Bash.G(0.10f, 1f, 15, new int[]{2, 3, 3}))),

//        SteelArrow("Steel Arrow","steelArrow", AttackType.projectile, 250, 25,  200,   20, 140, 30),
        Catapult( AttackType.projectile, 350, 30,  300,   240,   50, 140, 30,
                new Splash.P(60, 0.3f, new Splash.G(30, 0.2f, new int[]{1, 1, 0}))),

		Ballista(  AttackType.projectile, 250, 30,  300,   240,   35, 140, 55,
                new SteelArrow.P(2, 300, new SteelArrow.G(1, 50, new int[]{3, 2, 0}))),

        Spear(    AttackType.projectile, 250, 20,  300,   120,   20, 100, 42),
        CrossBow(  AttackType.projectile, 250, 20,  300,   120,   25, 110, 50, new Desolate.P(2, 5, new Desolate.G(1, 5f, new int[]{3, 1, 0}))),
        Gun(      AttackType.projectile, 500, 25,  300,   180,   50, 120, 55),
        Rifle(     AttackType.projectile, 500, 30,  300,   240,   40, 140, 35, new SpreadAttack.P(2f, 2, 0.1f, new SpreadAttack.G(0.3f, new int[]{0, 2, 0}))),
        MachineGun(AttackType.projectile, 500, 35,  300,   300,   40, 140, 110,
                new ShotDelay.P(2f, true, new ShotDelay.G(0.5f, new int[]{2, 0, 0}))),

        Sniper(   AttackType.projectile, 1200, 45,  350,  420,   700, 240, 20,
                new ShotDelay.P(1.6f, false, new ShotDelay.G(0.2f, new int[]{3, 0, 0}))),

        Shotgun(  AttackType.projectile, 250, 45,  300,   420,   160, 120, 50,
                new BuckShot.P(3, 25f, new BuckShot.G(1, 5f, new int[]{0, 3, 2}))),

        DoubleBarrel(AttackType.projectile, 250, 50,  300,   500,   180, 140, 10,
                new SpreadAttack.P(3f, 1, 0.1f, new SpreadAttack.G(0.5f, new int[]{0, 3, 0})),
                new BuckShot.P(5, 25f, new BuckShot.G(1, 5f, new int[]{0, 4, 3}))),

        Cannon(   AttackType.projectile, 500, 35,  300,   300,   85, 130, 50,
                new Splash.P(60, 0.3f, new Splash.G(30, 0.2f, new int[]{2, 2, 0}))),

        Rocket(   AttackType.projectile, 300, 40,  300,   360,   120, 160, 30,
                new Splash.P(60, 0.3f, new Splash.G(30, 0.2f, new int[]{3, 3, 0}))),

        Missle(  AttackType.projectile, 250, 45,  400,   420,   200, 220, 20,
                new Splash.P(50, 0.3f, new Splash.G(30, 0.2f, new int[]{4, 3, 0})),
                new SpreadAttack.P(3.5f, 1, 0.3f, new SpreadAttack.G(0.5f, new int[]{0, 3, 0}))),

        Glaive(   AttackType.projectile, 250, 35,  300,   300,   55, 150, 70,
                new Bounce.P(2, 0.2f, 200, new Bounce.G(1, 0.05f, 50, new int[]{3, 3, 2}))),

        MultiShot( AttackType.projectile, 250, 40,  300,   360,   70,  160,  100,
                new MultiShot.P(2, 0.5f, new MultiShot.G(1, 0.2f, new int[]{3, 0, 3}))),

        SteamMachine(AttackType.none,       250, 15,  300,   180,   5,  50,   5, new SteamAura.P());


        protected static void setComponents(){
            Rock.components.add(new TowerObject(Basic, 2, 1, 1));
            Arrow.components.add(new TowerObject(Basic, 1, 2, 1));
            Range.components.add(new TowerObject(Basic, 1, 1, 2));
            Short.components.add(new TowerObject(Rock, 1, 1, 0));
            Spear.components.add(new TowerObject(Arrow, 1, 0, 1));
            Gun.components.add(new TowerObject(Short, 1, 2, 1));
            Gun.components.add(new TowerObject(Spear, 1, 1, 1));
            Mountain.components.add(new TowerObject(Rock, 0, 1, 1));
            Mountain.components.add(new TowerObject(Range, 2, 0, 1));
            CrossBow.components.add(new TowerObject(Arrow, 1, 0, 1));
            CrossBow.components.add(new TowerObject(Range, 0, 2, 1));
            Rifle.components.add(new TowerObject(Gun, 1, 2, 2));
            Catapult.components.add(new TowerObject(Mountain, 2, 1, 2));
            Catapult.components.add(new TowerObject(SteamMachine, 2, 1, 0));
            Ballista.components.add(new TowerObject(CrossBow, 1, 2, 2));
            Ballista.components.add(new TowerObject(SteamMachine, 0, 1, 2));
            MachineGun.components.add(new TowerObject(Rifle, 2, 3, 1));
            MachineGun.components.add(new TowerObject(SteamMachine, 0, 3, 0));
            Cannon.components.add(new TowerObject(Short, 2, 0, 1));
            Cannon.components.add(new TowerObject(Catapult, 3, 2, 1));
            Glaive.components.add(new TowerObject(Spear, 2, 1, 0));
            Glaive.components.add(new TowerObject(Ballista, 1, 3, 2));
            Rocket.components.add(new TowerObject(Cannon, 4, 1, 2));
            MultiShot.components.add(new TowerObject(Glaive, 2, 4, 1));
            Shotgun.components.add(new TowerObject(Gun, 2, 0, 2));
            Shotgun.components.add(new TowerObject(MultiShot, 6, 1, 1));
            Missle.components.add(new TowerObject(Rocket, 3, 1, 4));
            Missle.components.add(new TowerObject(Rifle, 3, 0, 1));
            Sniper.components.add(new TowerObject(MachineGun, 4, 0, 4));
            DoubleBarrel.components.add(new TowerObject(Shotgun, 4, 1, 2));
            DoubleBarrel.components.add(new TowerObject(Rifle, 1, 0, 3));
            SteamMachine.components.add(new TowerObject(Basic, 1, 1, 1));


            Short.researchNeed.add(User.Research.Powder);
            SteamMachine.researchNeed.add(User.Research.Mech);



        }


        public static Tower getTower(String name){
            for (Tower t:values()){
                if(t.name().equals(name)){
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
//		private String name;
		private AttackType attackType;
        private int projectileSpeed;
		private int range;
		private int dmg;
		private int speed;
//		private float speedDelay;
		private int cost;
		private int recipeCost;
        private int globalCost;
		protected Array<Ability.AbilityPrototype> abilities;
        private Array<TowerObject> components;// = new Array<TowerObject>();
        private Array<User.Research> researchNeed = new Array<User.Research>();

		public void setTextures(){
//            TextureAtlas a = GDefence.getInstance().assetLoader.get("Tower/towers.atlas", TextureAtlas.class);
//            towerTexture = new TextureRegion(a.findRegion(textureName)).getTexture();
//            System.out.println("a" + a.findRegion(textureName));
//            System.out.println(textureName);
//            towerTexture = a.findRegion(textureName).getTexture();
//            System.out.println("t " + towerTexture);
			towerTexture = GDefence.getInstance().assetLoader.get("Tower/" + textureName + ".png", Texture.class);
//            System.out.println(towerTexture.getMinFilter() + " " + towerTexture.getMagFilter());
//            towerTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
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
		public Array<Ability.AbilityPrototype> getAbilities() {
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

		public int getRecipeCost() {
			return recipeCost;
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
            return GDefence.getInstance().assetLoader.getWord(name().toLowerCase());
        }

        @Override
        public String getTooltip() {
//            String s = "";
            AssetLoader l = GDefence.getInstance().assetLoader;
            String s = l.getWord("dmg") + ": " + getDmg() + System.getProperty("line.separator")
                    + l.getWord("speed") + ": " + getSpeed() + "(" + com.darkhouse.gdefence.Level.Tower.Tower.getAttackSpeedDelay(getSpeed()) + ")" + System.getProperty("line.separator")
                    + l.getWord("range") + ": " + getRange() + System.getProperty("line.separator")
                    + l.getWord("energyCost") + ": " + getCost();// + System.getProperty("line.separator");
            //"Damage: " + getDmg() + System.getProperty("line.separator")
//                    + "Attack range: " + getRange() + System.getProperty("line.separator")
//                    + "Attack speed: " + getSpeed() + "(" + com.darkhouse.gdefence.Level.Tower.Tower.getAttackSpeedDelay(getSpeed()) + ")" + System.getProperty("line.separator")
//                    + "Energy cost: " + getCost() + System.getProperty("line.separator");
//            for (int i = 0; i < getAbilities().size; i++){
//                s += System.getProperty("line.separator");
//                Ability.AbilityPrototype a = getAbilities().get(i);
//                s += a.getName();
//                s += a.getGemStat();
//            }

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



        Tower(/*String name, *//*String textureName, */AttackType attackType, int projectileSpeed, int cost, int globalCost, int recipeCost,
              int dmg, int range, int speed, Ability.AbilityPrototype... abilities) {
//			this.name = name;
			this.textureName = name().toLowerCase();
			this.recipeCost = recipeCost;
            this.globalCost = globalCost;
			this.attackType = attackType;
			this.projectileSpeed = projectileSpeed;
			this.cost = cost;
			this.dmg = dmg;
			this.range = range;
            this.speed = speed;
			this.abilities = new Array<Ability.AbilityPrototype>(abilities);

            components = new Array<TowerObject>();

//            if(attackType == AttackType.projectile){
//                setProjectileSpeed();
//            }



//			abilities = new ArrayList<Ability>();
//			addAbilities();
//			setTextures();

		}
		public static void init(){
            //for to last setTextures
            for (int i = 0; i < Tower.values().length; i++){
                Tower.values()[i].setTextures();
            }
		}

	}

    public enum Spell implements Item{
        EchoSmash("Echo Smash", "echoSmash"),
        SuddenDeath("Sudden Death", "suddenDeath"),
        EmergencyRepair("Emergency Repair", "emergencyRepair"),
        IceBlast("Ice blast", "iceBlast"),
        GlobalSlow("Global Slow", "globalSlow");



        private String name;
        private String texturePath;
        private int recipeCost;

        Spell(String name, String texturePath) {
            this.name = name;
            this.texturePath = texturePath;
//            this.recipeCost = recipeCost;
        }

        @Override
        public String getTextureRegion() {
            return texturePath;
        }

        public int getRecipeCost() {
            return 0;
        }

        @Override
        public int getGlobalCost() {
            return 0;
        }

        @Override
        public int getID() {
            return ordinal() + 117;//
        }//150 spellObject

        @Override
        public String getName(){
            return GDefence.getInstance().assetLoader.getWord(FontLoader.firstLowerLetter(name())) + " (" + GDefence.getInstance().assetLoader.getWord("spell") + ")";
        }

        @Override
        public String getTooltip() {
            return GDefence.getInstance().assetLoader.getWord("spellDropTooltip");
        }
    }


	public enum Detail implements Item{
		Recipe{
			@Override
			public String getTextureRegion() {
				return "recipe";
			}

			public int getRecipeCost() {
				return 0;
			}

            @Override
            public int getGlobalCost() {
                return 0;
            }
        };


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

}
