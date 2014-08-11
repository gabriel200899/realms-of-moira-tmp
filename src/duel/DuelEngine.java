package duel;

import core.Data;
import creatures.Creature;
import creatures.roles.Mage;
import creatures.roles.Priest;
import creatures.roles.Rogue;
import creatures.roles.Shaman;

public class DuelEngine {

	public static void build(int[][] selected) {
		for (int i = 0; i < selected.length; i++) {
			if (selected[i][0] == 1)
				Data.inUse.add(new Mage(selected[i][1], 5));
			else if (selected[i][0] == 2)
				Data.inUse.add(new Rogue(selected[i][1], 5));
			else if (selected[i][0] == 3)
				Data.inUse.add(new Shaman(selected[i][1], 5));
			else
				Data.inUse.add(i, new Priest(selected[i][1], 5));
		}
	}

	public static void clearInUse() {
		Data.inUse.remove(0);
		Data.inUse.remove(0);
	}

	public static void create() {

		int firstRole = Data
				.getPlayerRole("Select the first character's class:");

		System.out.println("Select first character's race:");
		Data.printArray(Data.availableRaces, Data.availableRacesInfo);
		System.out.print("Input: ");
		int firstRace = Data.getInput(4, Data.availableRacesArray);

		int secondRole = Data
				.getPlayerRole("Select the second character's class:");

		System.out.println("Select second character's race:");
		Data.printArray(Data.availableRaces, Data.availableRacesInfo);
		System.out.print("Input: ");
		int secondRace = Data.getInput(4, Data.availableRacesArray);

		int[] firstCharacter = { firstRole, firstRace };
		int[] secondCharacter = { secondRole, secondRace };
		int[][] allCharacters = { firstCharacter, secondCharacter };
		build(allCharacters);
	}

	public static void endGame() {
		if (Data.inUse.get(0).isAlive()) {
			System.out.printf("%s is dead! %s wins!\n", Data.inUse.get(1)
					.getRole(), Data.inUse.get(0).getRole());
			Data.inUse.get(0).evaluateLevel();
		} else {
			System.out.printf("%s is dead! %s wins!\n", Data.inUse.get(0)
					.getRole(), Data.inUse.get(1).getRole());
			Data.inUse.get(1).evaluateLevel();
		}
	}

	private static void endTurn(Creature creature) {
		if (creature.isAlive()) {
			creature.evaluateHealth();
			creature.evaluateMana();
			creature.setStun(false);
		}
		for (Creature player : Data.inUse) {
			player.evaluateAttack();
			player.evaluateDefense();
		}
	}

	public static void start() {
		create();
		startGame();
		clearInUse();
	}

	public static void startGame() {
		while (Data.inUse.get(0).isAlive() && Data.inUse.get(1).isAlive()) {
			startTurn(Data.inUse.get(0));
			if (Data.inUse.get(0).isAlive() && Data.inUse.get(1).isAlive()) {
				startTurn(Data.inUse.get(1));
			}
		}
		endGame();
	}

	public static void startTurn(Creature player) {
		if (player.isBlocking()) {
			player.setBlocking(false);
			player.evaluateDefense();
		}
		Data.refresh(Data.inUse);
		if (!player.isStun()) {
			int enemy;
			if (Data.inUse.indexOf(player) == 0)
				enemy = 1;
			else
				enemy = 0;
			System.out.println(player.getRole() + " playing.");
			Data.printArray(player.getSkills(Data.inUse.get(0).getLevel() + 1),
					player.getSkillsInfo(Data.inUse.get(0).getLevel() + 1));
			int skill = -1;
			while (skill == -1) {
				System.out.print("Skill: ");
				skill = Data.getInput(Data.inUse.get(0).getLevel() + 1,
								player.getSkillsArray(Data.inUse.get(0).getLevel() + 1),
								player);
				if (player.getSkillsMana()[skill - 1] <= player
						.getManaCurrent())
					break;
				else
					System.out.println("Not enough mana.");
				skill = -1;
			}
			if (skill == 1) {
				player.attack(Data.inUse.get(enemy));
			} else if (skill == 2) {
				player.block();
			} else if (skill == 3) {
				player.skill1(Data.inUse.get(enemy));
			} else if (skill == 4) {
				player.skill2(Data.inUse.get(enemy));
			} else if (skill == 5) {
				player.skill3(Data.inUse.get(enemy));
			} else {
				player.skill4(Data.inUse.get(enemy));
			}
		}
		endTurn(player);
	}
}