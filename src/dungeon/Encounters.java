package dungeon;

import core.Data;
import creatures.foes.Bat;
import creatures.foes.Wolf;

final class Encounters {

	// A string with 78 '=', used as a separator.
    private static final String BLANK_LINE = "===============================================================================";
    private static final String ENCOUNTER_1_DIALOG = "You are in a dark cave.\nYou hear something flying in your direction.\nThe dim light that enters the cave reveals a bat.\nKill it to earn experience points and gold.";
    private static final String ENCOUNTER_2_DIALOG = "As you walk into the darkness you hear a wolf.\nIt starts to run in your direction.\nKill it to earn experience points and gold.";

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
        System.out.println(BLANK_LINE);
        System.out.println(ENCOUNTER_1_DIALOG);
        System.out.println(BLANK_LINE);
		Data.inUse.add(new Bat(1));
		DungeonEngine.startBattle();
		if (Data.inUse.get(0).isAlive()) {
			System.out.println("You defeated the bat.");
		}
	}

    static void encounter2() {
        System.out.println(BLANK_LINE);
        System.out.println(ENCOUNTER_2_DIALOG);
        System.out.println(BLANK_LINE);
        Data.inUse.add(new Wolf(1));
		DungeonEngine.startBattle();
		if (Data.inUse.get(0).isAlive()) {
			System.out.println("You defeated the wolf.");
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

}
