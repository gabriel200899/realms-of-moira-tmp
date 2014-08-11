package creatures;

import static java.lang.System.out;

import java.util.ArrayList;

import core.Data;

public class Creature {

	private String name;
	private String role;
	private String race;
	
	private static String[] potions = {"[1] Health Potion", "[2] Mana Potion"};
	private static String[] potionsInfo = {"[Restores Health]", "[Restores Mana]"};
	private static String[] potionsArray = {"health", "mana"};

	private ArrayList<String> skills = new ArrayList<String>();
	private ArrayList<String> skillsInfo = new ArrayList<String>();
	private ArrayList<String> skillsArray = new ArrayList<String>();
	private ArrayList<Integer> skillsMana = new ArrayList<Integer>();

	private int level = 1;
	private int experience = 0;
	private int gold = 0; // Game currency (can buy potions)

	private int strength = 0; // +5 Attack and +5 Defense
	private int constitution = 0; // +30 Health Maximum
	private int wisdom = 0; // +30 Mana Maximum and +5 Mana Regen
	private int dexterity = 0; // +5 Critical Chance
	private int agility = 0; // +5 Evasion Chance
	private int intelligence = 0; // +50 Potion Power
	
	private int criticalChance = 5; // The chance of a critical attack (in %)
	private int evasionChance = 5; // The chance of an evasive move (in %)

	private int healthPotionCurrent;
	private int healthPotionPower = 100; // How much every health potion heals

	private int manaPotionCurrent;
	private int manaPotionPower = 100; // How much every mana potion heals
	
	private int healthCurrent;
	private int healthMaximum;

	private int manaRegen = 10;
	private int manaCurrent;
	private int manaMaximum;

	private int attackCurrent = 0;
	private int attackDefault = 0;

	private int defenseCurrent = 0;
	private int defenseDefault = 0;

	private int regrowthStack = 0;
	private int regrowthPower = 5;
	private int regrowthMaximum = 4;

	private int weaknessStack = 0;
	private int weaknessPower = 5;
	private int weaknessMaximum = 4;

	private int agonyStack = 0;
	private int agonyPower = 5;
	private int agonyMaximum = 4;

	private int curseStack = 0;
	private int cursePower = 5;
	private int curseMaximum = 4;

	private int bleedStack = 0;
	private int bleedPower = 10;
	private int bleedMaximum = 2;

	private int poisonStack = 0;
	private int poisonPower = 10;
	private int poisonMaximum = 2;
	
	private int darknessEmbracePower = 10;
	private int curseOfSufferingPower = 20;
	
	private boolean stun = false;
	private boolean alive = true;
	private boolean smoke = false;
	private boolean blocking = false;
	private boolean berserker = false;
	private boolean darknessEmbrace = false;
	private boolean curseOfSuffering = false;

	public void attack(Creature it) {
		it.instantDamage(15 + this.attackCurrent);
	}

	public void block() {
		this.blocking = true;
	}
	
	public void potions() {
		out.println("Which potion do you want to consume?");
		Data.printArray(potions, potionsInfo);
		out.print("Input: ");
		int potion = Data.getInput(2, potionsArray, this);
		if (potion == 1 && this.healthPotionCurrent > 0) {
			this.drinkHealthPotion();
		} else if (potion == 2 && this.manaPotionCurrent > 0) {
			this.drinkManaPotion();
		} else {
			System.out.println("You do not have that.");
			potions();
		}		
	}

	public void skill1(Creature it) {
	}

	public void skill2(Creature it) {
	}

	public void skill3(Creature it) {
	}

	public void skill4(Creature it) {
	}

	public void skill5(Creature it) {
	}

	public void nextSkill(Creature it) {
	}

	public void instantHealing(int healing) {
		this.healthCurrent += healing;
		if (this.healthCurrent > this.healthMaximum) {
			this.healthCurrent = this.healthMaximum;
		}
	}

	public void instantDamage(int damage) {
		damage -= this.defenseCurrent;
		if (damage > 0) {
			this.setHealthCurrent(this.getHealthCurrent() - damage);
		}
	}

	public void evaluateHealth() {
		this.healthCurrent += this.evaluatePeriodicHealing();
		this.healthCurrent -= this.evaluatePeriodicDamage();
		if (this.healthCurrent > this.healthMaximum) {
			this.healthCurrent = this.healthMaximum;
		}
		if (this.healthCurrent < 1) {
			this.alive = false;
		}
	}

	public int evaluatePeriodicHealing() {
		int periodicHealing = 0;
		periodicHealing += this.regrowthPower * this.regrowthStack;
		return periodicHealing;
	}

	public int evaluatePeriodicDamage() {
		int periodicDamage = 0;
		periodicDamage += this.agonyPower * this.agonyStack;
		periodicDamage += this.bleedPower * this.bleedStack;
		periodicDamage += this.poisonPower * this.poisonStack;
		periodicDamage += this.cursePower * this.curseStack;
		return periodicDamage;
	}

	public void evaluateMana() {
		this.manaCurrent += this.getManaRegen();
		if (this.manaCurrent > this.manaMaximum) {
			this.manaCurrent = this.manaMaximum;
		}
	}

	public void evaluateAttack() {
		int attack = attackDefault;
		attack -= weaknessPower * weaknessStack;
		attack += 5 * getStrength();
		if (berserker) {
			attack += 20;
		}
		this.setAttackCurrent(attack);
	}

	public void evaluateDefense() {
		int defense = this.defenseDefault;
		defense -= this.weaknessPower * this.weaknessStack;
		defense += 5 * getStrength();
		if (berserker) {
			defense -= 20;
		}
		this.setDefenseCurrent(defense);
	}
	
	public void evaluateCritical() {
		int critical = 0;
		critical += criticalChance;
		if (berserker) {
			criticalChance += 50;
		}
		this.setCriticalChance(critical);
	}

	public void evaluateEvasion() {
		int critical = 0;
		critical += criticalChance;
		if (smoke) {
			evasionChance += 50;
		}
		this.setCriticalChance(critical);
	}

	public void evaluateLevel() {
		if (this.level < 6) {
			if (this.getExperience() >= this.getLevel() * this.getLevel() * 100) {
				this.setLevel(this.getLevel() + 1);
				System.out.println("You leveled up.");
				System.out.println("You reached level " + this.level + ".");
				this.setHealthMaximum(this.healthMaximum + 20);
				this.setManaMaximum(this.manaMaximum + 20);
			}
		}
	}

	public int evaluateNecessaryExperience() {
		return this.level * this.level * 100 - this.experience;
	}

	public boolean evaluateHit(int probability) {
		if (Math.random() * 100 < probability)
			return true;
		else
			return false;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
		this.evaluateAttack();
		this.evaluateDefense();
	}

	public int getConstitution() {
		return constitution;
	}

	public void setConstitution(int constitution) {
		this.constitution = constitution;
		this.setHealthMaximum(this.healthMaximum + 50 * this.constitution);
	}

	public int getWisdom() {
		return wisdom;
	}

	public void setWisdom(int wisdom) {
		this.wisdom = wisdom;
		this.setManaRegen(this.getManaRegen() + 5 * this.wisdom);
	}

	public int getDexterity() {
		return dexterity;
	}

	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
		this.evaluateCritical();
	}

	public int getAgility() {
		return agility;
	}

	public void setAgility(int agility) {
		this.agility = agility;
		this.evaluateEvasion();
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
		this.healthPotionPower = 100 + 50 * intelligence;
		this.manaPotionPower = 100 + 50 * intelligence;
	}

	public int getRegrowthStack() {
		return regrowthStack;
	}

	public void setRegrowthStack(int regrowthStack) {
		if (regrowthStack > this.regrowthMaximum) {
			this.regrowthStack = this.regrowthMaximum;
		} else {
			this.regrowthStack = regrowthStack;
		}
	}

	public int getWeaknessStack() {
		return weaknessStack;
	}

	public void setWeakness(int weakness) {
		if (weakness > this.weaknessMaximum) {
			this.weaknessStack = this.weaknessMaximum;
		} else {
			this.weaknessStack = weakness;
		}
	}

	public int getAgonyStack() {
		return agonyStack;
	}

	public void setAgonyStack(int agony) {
		if (agony > this.agonyMaximum)
			this.agonyStack = this.agonyMaximum;
		else
			this.agonyStack = agony;
	}

	public int getCurseStack() {
		return curseStack;
	}

	public void setCurseStack(int curse) {
		if (curse > curseMaximum)
			this.curseStack = curseMaximum;
		else
			this.curseStack = curse;
	}

	public int getBleedStack() {
		return bleedStack;
	}

	public void setBleedStack(int bleed) {
		if (bleed > bleedMaximum)
			this.bleedStack = bleedMaximum;
		else
			this.bleedStack = bleed;
	}

	public int getPoisonStack() {
		return poisonStack;
	}

	public void setPoisonStack(int poisonStack) {
		if (poisonStack > poisonMaximum)
			this.poisonStack = poisonMaximum;
		else
			this.poisonStack = poisonStack;
	}
	
	public int getAttackCurrent() {
		return attackCurrent;
	}

	public void setAttackCurrent(int attack) {
		this.attackCurrent = attack;
	}

	public int getAttackDefault() {
		return attackDefault;
	}

	public void setAttackDefault(int attackDefault) {
		this.attackDefault = attackDefault;
		this.attackCurrent = this.attackDefault;
	}

	public int getDefenseCurrent() {
		return defenseCurrent;
	}

	public void setDefenseCurrent(int defense) {
		this.defenseCurrent = defense;
	}

	public int getDefenseDefault() {
		return defenseDefault;
	}

	public void setDefenseDefault(int defenseDefault) {
		this.defenseDefault = defenseDefault;
		this.defenseCurrent = this.defenseDefault;
	}

	public int getCriticalChance() {
		return criticalChance;
	}

	public void setCriticalChance(int criticalChance) {
		this.criticalChance = criticalChance;
		if (this.criticalChance < 0) {
			this.criticalChance = 0;
		} else if (this.criticalChance > 100) {
			this.criticalChance = 0;
		}
	}

	public int getEvasionChance() {
		return evasionChance;
	}

	public void setEvasionChance(int evasionChance) {
		this.evasionChance = evasionChance;
		if (this.evasionChance < 0) {
			this.evasionChance = 0;
		} else if (this.evasionChance > 100) {
			this.evasionChance = 0;
		}
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public boolean hasPotions() {
		if (this.healthPotionCurrent > 0) {
			return true;
		} else if (this.manaPotionCurrent > 0) {
			return true;
		} else {
			return false;
		}
	}

	public int getHealthPotion() {
		return this.healthPotionCurrent;
	}

	public void setHealthPotion(int potionCurrent) {
		this.healthPotionCurrent = potionCurrent;
	}

	public void drinkHealthPotion() {
		if (this.healthPotionCurrent > 0) {
			this.setHealthCurrent(this.healthCurrent + this.healthPotionPower);
			this.healthPotionCurrent--;
			System.out.println("You drink a health potion.");
		}
	}
	
	public int getManaPotion() {
		return this.manaPotionCurrent;
	}

	public void setManaPotion(int potionCurrent) {
		this.manaPotionCurrent = potionCurrent;
	}

	public void drinkManaPotion() {
		if (this.manaPotionCurrent > 0) {
			this.setManaCurrent(this.manaCurrent + this.manaPotionPower);
			this.manaPotionCurrent--;
			System.out.println("You drink a mana potion.");
		}
	}
	
	public int getHealthCurrent() {
		return healthCurrent;
	}

	public void setHealthCurrent(int health) {
		if (health <= 0) {
			this.healthCurrent = 0;
			this.alive = false;
		} else if (health > healthMaximum)
			this.healthCurrent = healthMaximum;
		else
			this.healthCurrent = health;
	}

	public int getHealthMaximum() {
		return healthMaximum;
	}

	public void setHealthMaximum(int healthMaximum) {
		this.healthMaximum = healthMaximum;
		this.healthCurrent = healthMaximum;
	}

	public int getManaRegen() {
		return manaRegen;
	}

	public void setManaRegen(int manaRegen) {
		this.manaRegen = manaRegen;
		if (manaRegen < 0) {
			manaRegen = 0;
		}
	}

	public int getManaCurrent() {
		return manaCurrent;
	}

	public void setManaCurrent(int mana) {
		if (mana < 0)
			this.manaCurrent = 0;
		if (mana > manaMaximum)
			this.manaCurrent = manaMaximum;
		else
			this.manaCurrent = mana;
	}

	public int getManaMaximum() {
		return manaMaximum;
	}

	public void setManaMaximum(int manaMaximum) {
		this.manaMaximum = manaMaximum;
		this.manaCurrent = manaMaximum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getRace() {
		return race;
	}

	/**
	 * Preferred method to assign a race to a creature. Sets up race-dependent
	 * attributes.
	 * 
	 * @param race
	 *            the race to be assigned to the creature
	 *            race 1 = Human 
	 *            race 2 = Orc 
	 *            race 3 = Dwarf 
	 *            race 4 = Elf 
	 *            race 5 = Beast 
	 *            race 6 = Undead 
	 *            race 7 = Goblin
	 */
	public void setRace(int race) {
		if (race == 1) {
			this.race = "Human";
			this.setHealthMaximum(this.getHealthMaximum() + 20);
			this.setManaMaximum(this.getManaMaximum() + 20);
		} else if (race == 2) {
			this.race = "Orc";
			this.setAttackDefault(10);
		} else if (race == 3) {
			this.race = "Dwarf";
			this.setDefenseDefault(10);
		} else if (race == 4) {
			this.race = "Elf";
			this.setManaMaximum(this.getManaMaximum() + 60);
		} else if (race == 5) {
			this.race = "Beast";
		} else if (race == 6) {
			this.race = "Undead";
		} else if (race == 7) {
			this.race = "Goblin";

		}
	}
	
	public String[] getSkills(int level) {
		String[] array = new String[level];
		for (int n = 0; n < level; n++) {
			array[n] = skills.get(n);
		}
		return array;
	}

	public void setSkills(ArrayList<String> skills) {
		this.skills = skills;
	}

	public String[] getSkillsArray(int level) {
		String[] array = new String[level];
		for (int n = 0; n < level; n++) {
			array[n] = skillsArray.get(n);
		}
		return array;
	}

	public void setSkillsArray(ArrayList<String> skillsArray) {
		this.skillsArray = skillsArray;
	}

	public String[] getSkillsInfo(int level) {
		String[] array = new String[level];
		for (int n = 0; n < level; n++) {
			array[n] = skillsInfo.get(n);
		}
		return array;
	}

	public void setSkillsInfo(ArrayList<String> skillsInfo) {
		this.skillsInfo = skillsInfo;
	}

	public int[] getSkillsMana() {
		int[] array = new int[skillsMana.size()];
		for (int n = 0; n < skillsMana.size(); n++) {
			array[n] = skillsMana.get(n);
		}
		return array;
	}

	public void setSkillsMana(ArrayList<Integer> skillsMana) {
		this.skillsMana = skillsMana;
	}

	public boolean isStun() {
		return stun;
	}

	public void setStun(boolean stun) {
		this.stun = stun;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public boolean isBlocking() {
		return blocking;
	}

	public void setBlocking(boolean blocking) {
		this.blocking = blocking;
	}

	public boolean isBerserker() {
		return berserker;
	}

	public void setBerserker(boolean berserker) {
		this.berserker = berserker;
	}

	public void getDrops(Creature creature) {
		this.gold += creature.gold;
		this.experience += creature.experience;
		this.healthPotionCurrent += creature.healthPotionCurrent;
		System.out.printf("%s dropped:\n%d gold coins\n%d experience points\n",
						creature.role, creature.gold, creature.experience);
		if (creature.healthPotionCurrent > 1) {
			System.out.printf("%d health potions\n", creature.healthPotionCurrent);
		} else if (creature.healthPotionCurrent > 0) {
			System.out.print("1 health potion\n");
		}
	}
}
