package dungeon;

import static core.Data.inUse;
import core.Data;
import creatures.Creature;
import creatures.roles.Mage;
import creatures.roles.Priest;
import creatures.roles.Rogue;
import creatures.roles.Shaman;

public class DungeonEngine {

	static int[] playerSelection;

	public static void start() {
		create();
		build(playerSelection);
		startDungeon();
	}

	private static void create() {
		System.out.println("Give your hero a name.");
		Data.getPlayerName("Name: ");
		int heroRole = Data.getPlayerRole("Select a class.");
		int heroRace = Data.getPlayerRace("Select a race.");
		playerSelection = new int[] { heroRole, heroRace };
	}

	private static void build(int[] selected) {
		if (selected[0] == 1)
			inUse.add(new Mage(selected[1], 1));
		if (selected[0] == 2)
			inUse.add(new Rogue(selected[1], 1));
		if (selected[0] == 3)
			inUse.add(new Shaman(selected[1], 1));
		if (selected[0] == 4)
			inUse.add(new Priest(selected[1], 1));
	}

	private static void startDungeon() {
		Encounters.lore1();
		Encounters.encounter1();
		while (true) {
			if (inUse.get(0).isAlive()) {
				Encounters.encounter1b();
			}
			if (inUse.get(0).isAlive()) {
				Encounters.encounter2();
			} else {
				System.out.println("You died in combat.");
				break;
			}
		}
		inUse.remove(0);
		inUse.remove(0);
	}

	static void startBattle() {
		while (inUse.get(0).isAlive() && inUse.get(1).isAlive()) {
			playerTurn();
			if (inUse.get(0).isAlive() && inUse.get(1).isAlive()) {
				computerTurn();
			}
		}
		endBattle();
	}

	private static void playerTurn() {
		Data.refresh(inUse);
		recount(inUse.get(0));
		if (!inUse.get(0).isStun()) {
			System.out.println("Your turn.");
			Data.printArray(
					inUse.get(0).getSkills(
							inUse.get(0).getLevel() + 1),
					inUse.get(0).getSkillsInfo(
							inUse.get(0).getLevel() + 1));
			int skill = -3;
			while (true) {
				while (skill == -3) {
					System.out.print("Skill: ");
					skill = Data.getInput(
							inUse.get(0).getLevel() + 1,
							inUse.get(0).getSkillsArray(
									inUse.get(0).getLevel() + 1));
					if (inUse.get(0).getSkillsMana()[skill - 1] <= inUse.get(0).getManaCurrent()) {
						skill -= 3;
						break;
					} else {
						System.out.println("Not enough mana to use that.");
						skill = -3;
					}
				}
				if (skill == -2) {
					inUse.get(0).attack(inUse.get(1));
					break;
				} else if (skill == -1) {
					inUse.get(0).block();
					break;
				} else if (skill == 0) {
					if (inUse.get(0).hasPotions()) {
						inUse.get(0).potions();
						break;
					} else {
						System.out.println("You don't have any potions.");
						skill = -3;
					}
				} else if (skill == 1) {
					inUse.get(0).skill1(inUse.get(1));
					break;
				} else if (skill == 2) {
					inUse.get(0).skill2(inUse.get(1));
					break;
				} else if (skill == 3) {
					inUse.get(0).skill3(inUse.get(1));
					break;
				} else if (skill == 4) {
					inUse.get(0).skill4(inUse.get(1));
					break;
				} else {
					inUse.get(0).skill5(inUse.get(1));
					break;
				}
			}
		}
		endTurn(inUse.get(0));
	}

	private static void computerTurn() {
		if (!inUse.get(1).isStun()) {
			inUse.get(1).nextSkill(inUse.get(0));
		}
		endTurn(inUse.get(1));
	}
	
	private static void recount(Creature creature) {

		if (creature.isBlocking()) {
			creature.setBlocking(false);
			creature.evaluateDefense();
		}
	}

	private static void endTurn(Creature creature) {
		if (inUse.get(0).isAlive()) {
			creature.evaluateHealth();
			creature.evaluateMana();
			creature.evaluateCritical();			
			creature.evaluateEvasion();
			creature.setStun(false);
		}
		for (Creature it : inUse) {
			it.evaluateAttack();
			it.evaluateDefense();
		}
	}

	private static void endBattle() {
		if (inUse.get(0).isAlive()) {
			inUse.get(0).getDrops(inUse.get(1));
			inUse.get(0).evaluateLevel();
			inUse.remove(1);
		}
	}

}
