package dungeon;

import core.Data;
import creatures.foes.Bat;
import creatures.foes.Wolf;

final class Encounters {

	static void line() {
		for (int i = 0; i < 79; i++) {
			System.out.print("=");
		}
		System.out.println();
	}

	static void lore1() {
		line();
		System.out
				.println("\tAfter two hundred and eighty four years of peace, "
						+ "the guardian goodness\nMoira has been captured by the evil "
						+ "villain Mastaph inside a dungeon. Evil has\nrisen as there "
						+ "was not anyone protecting the vicinity of the city of "
						+ "Argmadur\nfrom the evil creatures that spawn when it is dark. "
						+ "Now it is time for you to\nstop it. Enter the dungeon, make "
						+ "your way to the very end and release Moira!");
		line();
	}

	static void encounter1() {
		line();
		System.out.println("You arrived a dark cave.");
		System.out.println("You hear something flying in your direction.");
		System.out.println("The dim light that enters the cave reveals a bat.");
		System.out.println("Kill it to earn experience points and gold.");
		line();
		Data.inUse.add(new Bat(1));
		DungeonEngine.startBattle();
		if (Data.inUse.get(0).isAlive()) {
			System.out.println("You defeated the bat.");
		}
	}

	static void encounter1b() {
		line();
		System.out.println("You see another bat approaching.");
		System.out.println("Kill it to earn experience points and gold.");
		line();
		Data.inUse.add(new Bat(1));
		DungeonEngine.startBattle();
		if (Data.inUse.get(0).isAlive()) {
			System.out.println("You defeated the bat.");
		}
	}

	static void encounter2() {
		line();
		System.out
				.println("As you walk deeper into the darkness you hear a wolf.");
		System.out.println("It starts running in your direction.");
		System.out.println("Kill it to earn experience points and gold.");
		line();
		Data.inUse.add(new Wolf(1));
		DungeonEngine.startBattle();
		if (Data.inUse.get(0).isAlive()) {
			System.out.println("You defeated the wolf.");
		}
	}
}
