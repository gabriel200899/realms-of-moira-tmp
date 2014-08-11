package core;

import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Scanner;
import creatures.Creature;
import duel.DuelEngine;
import dungeon.DungeonEngine;

public class Data {

	public static ArrayList<Creature> inUse = new ArrayList<Creature>();
	
	static Scanner input = new Scanner(System.in);	
	static boolean verbose = false;
	
	public static String[] availableClasses = { "[1] [Mage]", "[2] [Rogue]",
			"[3] [Shaman]", "[4] [Priest]" };
	public static String[] availableClassesInfo = { "[Damage Dealer]",
			"[Damage Dealer]", "[Healer]", "[Healer]" };
	public static String[] availableClassesArray = { "mage", "rogue", "shaman",
			"priest" };
	public static String[] availableRaces = { "[1] [Human]", "[2] [Orc]",
			"[3] [Dwarf]", "[4] [Elf]" };
	public static String[] availableRacesInfo = { "[Balanced]", "[Offensive]",
			"[Defensive]", "[Healer]" };
	public static String[] availableRacesArray = { "human", "orc", "dwarf",
			"elf" };

	public static void info() {
		out.println("Author:\tBernardo Sulzbach\n"
				+ "GitHub:\tbesulzbach/realmsofmoira/\n"
				+ "E-mail:\tbesulzbach@gmail.com");
	}

	public static void start() {
		out.println("Main menu");
		out.println("[1] Duel");
		out.println("[2] Dungeon Crawl");
		out.println("[3] Exit");
		out.print("Input: ");
		String[] array = { "duel", "dungeon", "quit" };
		int givenInt = getInput(3, array);
		if (givenInt == 1)
			DuelEngine.start();
		else if (givenInt == 2)
			DungeonEngine.start();
		else
			System.exit(0);
	}

	public static void printArray(String[] left, String[] right) {
		for (int i = 0; i < left.length; i++) {
			out.print(left[i]);
			for (int n = 0; n < 79 - left[i].length() - right[i].length(); n++)
				out.print(".");
			out.println(right[i]);
		}
	}

	public static int getInput(int max, String[] array) {
		while (true) {
			boolean invalid = true;
			if (input.hasNextLine()) {
				if (input.hasNextInt()) {
					int givenInt = input.nextInt();
					if (givenInt > 0 && givenInt <= max) {
						return givenInt;
					}
				} else if (input.hasNext()) {
					String givenString = input.next().toLowerCase();
					givenString = givenString.replaceAll("\\s", "");
					givenString = givenString.replaceAll("\\W", "");
					Checkers.exitCheck(givenString);
					for (int i = 0; i < array.length; i++) {
						if (array[i].equals(givenString)) {
							return (i + 1);
						}
					}
					invalid = checkAll(givenString);
				}
			}
			if (invalid) {
				out.print("Invalid input. Try again.\nInput: ");
				input.nextLine();
			} else {
				out.print("Input: ");
				input.nextLine();				
			}
		}
	}

	private static boolean checkAll(String givenString) {
		if (Checkers.experienceCheck(givenString)) {
			return false;
		} else if (Checkers.goldCheck(givenString)) {
			return false;
		} else if (Checkers.potionCheck(givenString)) {
			return false;
		} else if (Checkers.buyCheck(givenString)) {
			return false;
		} else if (Checkers.statsCheck(givenString)) {
			return false;
		} else if (Checkers.verboseCheck(givenString)) {
			return false;
		} else if (Checkers.timeCheck(givenString)) {
			return false;
		} else {
			return true;
		}
	}
	
	public static int getInput(int max, String[] array, Creature caller) {
		while (true) {
			boolean invalid = true;
			if (input.hasNextLine()) {
				if (input.hasNextInt()) {
					int givenInt = input.nextInt();
					if (givenInt > 0 && givenInt <= max) {
						return givenInt;
					}
				} else if (input.hasNext()) {
					String givenString = input.next().toLowerCase();
					givenString = givenString.replaceAll("\\s", "");
					givenString = givenString.replaceAll("\\W", "");
					Checkers.exitCheck(givenString);
					for (int i = 0; i < array.length; i++) {
						if (array[i].equals(givenString)) {
							return (i + 1);
						}
					}
					invalid = checkAll(givenString, caller);
				}
			}
			if (invalid) {
				out.print("Invalid input. Try again.\nInput: ");
				input.nextLine();
			} else {
				out.print("Input: ");
				input.nextLine();				
			}
		}
	}
	
	private static boolean checkAll(String givenString, Creature caller) {		
		if (Checkers.experienceCheck(givenString, caller)) {
			return false;
		} else if (Checkers.goldCheck(givenString, caller)) {
			return false;
		} else if (Checkers.potionCheck(givenString, caller)) {
			return false;
		} else if (Checkers.buyCheck(givenString, caller)) {
			return false;
		} else if (Checkers.statsCheck(givenString, caller)) {
			return false;
		} else if (Checkers.verboseCheck(givenString, caller)) {
			return false;
		} else if (Checkers.timeCheck(givenString)) {
			return false;
		} else {
			return true;
		}
	}

	public static String getPlayerName(String question) {
		while (true) {
			boolean invalid = true;
			out.print(question);
			if (input.hasNext()) {
				String givenString = input.next();
				Checkers.exitCheck(givenString);
				invalid = checkAll(givenString);
				if (givenString.length() > 4) {
					return givenString;
				} else {
					out.println("Name must be at least five characters.");
				}
			}
			if (invalid) {
				out.print("Invalid input. Try again.\nInput: ");
				input.nextLine();
			} else {
				out.print("Input: ");
				input.nextLine();
			}
		}
	}

	public static int getPlayerRole(String question) {
		out.println(question);
		Data.printArray(Data.availableClasses, Data.availableClassesInfo);
		out.print("Input: ");
		return Data.getInput(4, Data.availableClassesArray);
	}

	public static int getPlayerRace(String question) {
		out.println(question);
		Data.printArray(Data.availableRaces, Data.availableRacesInfo);
		out.print("Input: ");
		return Data.getInput(4, Data.availableRacesArray);
	}
	
	/**
	 * This method is used to retrieve and print vital information from the characters
	 * that are currently active. 
	 * @param inUse		the ArrayList from where the Creatures will be obtained
	 * @param verbose	true if irrevelant stacks should be printed
	 */
	public static void refresh(ArrayList<Creature> inUse) {
		
		String aTop = "[" + inUse.get(0).getRole() + "][" + inUse.get(0).getRace() + "]";
		String bTop = "[" + inUse.get(1).getRole() + "][" + inUse.get(1).getRace() + "]";
		out.printf("\n%5s[1]%-31s[2]%-40s\n", "", aTop, bTop);
		
		int aLevel = inUse.get(0).getLevel();
		int bLevel = inUse.get(1).getLevel();
		out.printf("%5s[LEVEL %d]%25s[LEVEL %d]\n", "", aLevel, "", bLevel);
		out.printf("%5s[GENERAL]%<25s[GENERAL]\n", "");
		
		int aHealth = inUse.get(0).getHealthCurrent();
		int bHealth = inUse.get(1).getHealthCurrent();
		out.printf("%5sHealth: % 8d%18sHealth: % 8d\n", "", aHealth, "", bHealth);
		
		int aMana = inUse.get(0).getManaCurrent();
		int bMana = inUse.get(1).getManaCurrent();
		out.printf("%5sMana: % 10d%18sMana: % 10d\n", "", aMana, "", bMana);

		int aAttack = inUse.get(0).getAttackCurrent();
		int bAttack = inUse.get(1).getAttackCurrent();
		out.printf("%5sAttack: % 8d%18sAttack: % 8d\n", "", aAttack, "", bAttack);

		int aDefense = inUse.get(0).getDefenseCurrent();
		int bDefense = inUse.get(1).getDefenseCurrent();
		out.printf("%5sDefense: % 7d%18sDefense: % 7d\n\n", "", aDefense, "", bDefense);

		refreshStats(inUse, verbose);
	}
	
	private static void refreshStats(ArrayList<Creature> inUse, boolean verbose) {
		if (verbose) {
			// Prints all stacks, even those that are zero
			boolean aStun = inUse.get(0).isStun();
			boolean bStun = inUse.get(1).isStun();
			int aRegrowth = inUse.get(0).getRegrowthStack();
			int bRegrowth = inUse.get(1).getRegrowthStack();
			int aWeakness = inUse.get(0).getWeaknessStack();
			int bWeakness = inUse.get(1).getWeaknessStack();
			int aAgony = inUse.get(0).getAgonyStack();
			int bAgony = inUse.get(1).getAgonyStack();	
			int aBleed = inUse.get(0).getBleedStack();
			int bBleed = inUse.get(1).getBleedStack();	
			int aPoison = inUse.get(0).getPoisonStack();
			int bPoison = inUse.get(1).getPoisonStack();
			int aCurse = inUse.get(0).getCurseStack();
			int bCurse = inUse.get(1).getCurseStack();			
			if (aStun && bStun) {
				out.printf("%5sStun%<9s(1)%<18sStun%<9s(1)\n", "");
			} else if (aStun) {
				out.printf("%5sStun%<9s(1)%<18sStun%<9s(0)\n", "");
			} else if (bStun) {
				out.printf("%5sStun%<9s(0)%<18sStun%<9s(1)\n", "");			
			} else {
				out.printf("%5sStun%<9s(0)%<18sStun%<9s(0)\n", "");
			}
			out.printf("%5sRegrowth%<5s(%d)%18sRegrowth%<5s(%d)\n", "", aRegrowth, "", bRegrowth);
			out.printf("%5sWeakness%<5s(%d)%18sWeakness%<5s(%d)\n", "", aWeakness, "", bWeakness);
			out.printf("%5sAgony%<8s(%d)%18sAgony%<8s(%d)\n", "", aAgony, "", bAgony);
			out.printf("%5sBleed%<8s(%d)%18sBleed%<8s(%d)\n", "", aBleed, "", bBleed);
			out.printf("%5sPoison%<7s(%d)%18sPoison%<7s(%d)\n", "", aPoison, "", bPoison);
			out.printf("%5sCurse%<8s(%d)%18sCurse%<8s(%d)\n", "", aCurse, "", bCurse);
		} else {
			
			// Checks stun
			boolean aStun = inUse.get(0).isStun();
			boolean bStun = inUse.get(1).isStun();
			if (aStun && bStun) {
				out.printf("%5sStun (1)%26sStun (1)\n", "", "");
			} else if (aStun) {
				out.printf("%5sStun (1)\n", "");
			} else if (bStun) {
				out.printf("%39sStun (1)\n", "");			
			}
			
			// Checks regrowth
			int aRegrowth = inUse.get(0).getRegrowthStack();
			int bRegrowth = inUse.get(1).getRegrowthStack();		
			if (aRegrowth > 0 && bRegrowth > 0) {
				out.printf("%5sRegrowth (%d)%22sRegrowth (%d)\n", "", aRegrowth, "", bRegrowth);
			} else if (aRegrowth > 0) {
				out.printf("%5sRegrowth (%d)\n", "", aRegrowth);
			} else if (bRegrowth > 0) {
				out.printf("%39sRegrowth (%d)\n", "", bRegrowth);			
			}
			
			// Checks weakness
			int aWeakness = inUse.get(0).getWeaknessStack();
			int bWeakness = inUse.get(1).getWeaknessStack();		
			if (aWeakness > 0 && bWeakness > 0) {
				out.printf("%5sWeakness (%d)%22sWeakness (%d)\n", "", aWeakness, "", bWeakness);
			} else if (aWeakness > 0) {
				out.printf("%5sWeakness (%d)\n", "", aWeakness);
			} else if (bWeakness > 0) {
				out.printf("%39sWeakness (%d)\n", "", bWeakness);			
			}
			
			// Checks agony
			int aAgony = inUse.get(0).getAgonyStack();
			int bAgony = inUse.get(1).getAgonyStack();		
			if (aAgony > 0 && bAgony > 0) {
				out.printf("%5sAgony (%d)%25sAgony (%d)\n", "", aAgony, "", bAgony);
			} else if (aAgony > 0) {
				out.printf("%5sAgony (%d)\n", "", aAgony);
			} else if (bAgony > 0) {
				out.printf("%39sAgony (%d)\n", "", bAgony);			
			}
			
			// Checks bleed
			int aBleed = inUse.get(0).getBleedStack();
			int bBleed = inUse.get(1).getBleedStack();
			if (aBleed > 0 && bBleed > 0) {
				out.printf("%5sBleed (%d)%25sBleed (%d)\n", "", aBleed, "", bBleed);
			} else if (aBleed > 0) {
				out.printf("%5sBleed (%d)\n", "", aBleed);
			} else if (bBleed > 0) {
				out.printf("%39sBleed (%d)\n", "", bBleed);			
			}
			
			// Checks poison
			int aPoison = inUse.get(0).getPoisonStack();
			int bPoison = inUse.get(1).getPoisonStack();		
			if (aPoison > 0 && bPoison > 0) {
				out.printf("%5sPoison (%d)%24sPoison (%d)\n", "", aPoison, "", bPoison);
			} else if (aPoison > 0) {
				out.printf("%5sPoison (%d)\n", "", aPoison);
			} else if (bPoison > 0) {
				out.printf("%39sPoison (%d)\n", "", bPoison);			
			}
			
			// Checks curse
			int aCurse = inUse.get(0).getCurseStack();
			int bCurse = inUse.get(1).getCurseStack();		
			if (aCurse > 0 && bCurse > 0) {
				out.printf("%5sCurse (%d)%25sCurse (%d)\n", "", aCurse, "", bCurse);
			} else if (aCurse > 0) {
				out.printf("%5sCurse (%d)\n", "", aCurse);
			} else if (bCurse > 0) {
				out.printf("%39sCurse (%d)\n", "", bCurse);			
			}
		}
	}
}
