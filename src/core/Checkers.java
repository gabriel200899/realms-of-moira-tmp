package core;

import static core.Data.inUse;
import creatures.Creature;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Checkers {

	// SimpleDateFormat that defines how date and time are displayed
	private static SimpleDateFormat timeFormat = new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss");

	// Prints a message
	static void notAvailable() {
		System.out.println("This functionality is not available now.");
	}

	// Returns current time
	public static void timeNow() {
		System.out.println(timeFormat.format(Calendar.getInstance().getTime()));
	}

	// Checks if the user requested information about experience
	static boolean experienceCheck(String givenString) {
		try {
			if ((givenString.equals("exp")) || givenString.equals("experience")) {
				System.out.printf(
						"Total experience: %d\nExperience to level %d: %d\n",
						inUse.get(0).getExperience(), inUse.get(0).getLevel() + 1, 
						inUse.get(0).evaluateNecessaryExperience());
				return true;
			} else {
				return false;
			}
		} catch (IndexOutOfBoundsException e) {
			notAvailable();
			return true;
		}
	}

	// Checks if the user requested current gold amount
	static boolean goldCheck(String givenString) {
		try {
			if (givenString.equals("gold") || givenString.equals("chest")) {
				System.out.println("Gold: " + inUse.get(0).getGold());
				return true;
			} else {
				return false;
			}
		} catch (IndexOutOfBoundsException e) {
			notAvailable();
			return true;
		}
	}

	// Checks if the user requested current potion amount
	static boolean potionCheck(String givenString) {
		try {
			if (givenString.equals("potion") || givenString.equals("potions")) {
				int healthPotions = inUse.get(0).getHealthPotion();
				int manaPotions = inUse.get(0).getManaPotion();
				System.out.printf("Mana Potions:   % 3d\nHealth Potions: % 3d\n",
						manaPotions, healthPotions);
				return true;
			} else {
				return false;
			}
		} catch (IndexOutOfBoundsException e) {
			notAvailable();
			return true;
		}
	}
	
	// Checks if the user wants to buy a potion
	// NOT FINISHED
	static boolean buyCheck(String givenString) {
		try {
			if ((givenString.equals("buy")) || (givenString.equals("store"))) {
				System.out.println("The store is not open yet.");
				return true;
			} else {
				return false;
			}
		} catch (IndexOutOfBoundsException e) {
			notAvailable();
			return true;
		}
	}
	
	// Checks if the user requested his/hers character's stats
	static boolean statsCheck(String givenString) {
		try {
			if ((givenString.equals("stats")) || (givenString.equals("char"))) {
				System.out.printf("Strength: % 8d\nConstitution: % 4d\n"
						+ "Wisdom: % 10d\nDexterity: % 7d\n"
						+ "Agility: % 9d\nIntelligence: % 4d\n",
						inUse.get(0).getStrength(),
						inUse.get(0).getConstitution(),
						inUse.get(0).getWisdom(),
						inUse.get(0).getDexterity(),
						inUse.get(0).getAgility(),
						inUse.get(0).getIntelligence());
				return true;
			} else {
				return false;
			}
		} catch (IndexOutOfBoundsException e) {
			notAvailable();
			return true;
		}
	}
	
	// Checks if the user requested set verbose or set silent
	static boolean verboseCheck(String givenString) {
		try {
			if ((givenString.equals("verbose")) || (givenString.equals("verb"))) {
				System.out.println("Game set to verbose.");
				Data.verbose = true;
				return true;
			} else if ((givenString.equals("quiet")) || (givenString.equals("silent"))) {
				System.out.println("Game set to quiet.");
				Data.verbose = false;
				return true;
			} else {
				return false;
			}
		} catch (IndexOutOfBoundsException e) {
			return true;
		}
	}
	
	/**
	 * The following methods are nothing but overloaded versions
	 * @param givenString
	 * @param caller from what character the caller wants information
	 * @return
	 */
	// Checks if the user requested information about experience
	static boolean experienceCheck(String givenString, Creature caller) {
		try {
			if ((givenString.equals("exp")) || givenString.equals("experience")) {
				System.out.printf(
						"Total experience: %d\nExperience to level %d: %d\n",
						caller.getExperience(), caller.getLevel() + 1, 
						caller.evaluateNecessaryExperience());
				return true;
			} else {
				return false;
			}
		} catch (IndexOutOfBoundsException e) {
			notAvailable();
			return true;
		}
	}

	// Checks if the user requested current gold amount
	static boolean goldCheck(String givenString, Creature caller) {
		try {
			if (givenString.equals("gold") || givenString.equals("chest")) {
				System.out.println("Gold: " + caller.getGold());
				return true;
			} else {
				return false;
			}
		} catch (IndexOutOfBoundsException e) {
			notAvailable();
			return true;
		}
	}

	// Checks if the user requested current potion amount
	static boolean potionCheck(String givenString, Creature caller) {
		try {
			if (givenString.equals("potion") || givenString.equals("potions")) {
				int healthPotions = caller.getHealthPotion();
				int manaPotions = caller.getManaPotion();
				System.out.printf("Mana Potions:   % 3d\nHealth Potions: % 3d\n",
						manaPotions, healthPotions);
				return true;
			} else {
				return false;
			}
		} catch (IndexOutOfBoundsException e) {
			notAvailable();
			return true;
		}
	}
	
	// Checks if the user wants to buy a potion
	// NOT FINISHED
	static boolean buyCheck(String givenString, Creature caller) {
		try {
			if ((givenString.equals("buy")) || (givenString.equals("store"))) {
				System.out.println("The store is not open yet.");
				return true;
			} else {
				return false;
			}
		} catch (IndexOutOfBoundsException e) {
			notAvailable();
			return true;
		}
	}
	
	// Checks if the user requested his/hers character's stats
	static boolean statsCheck(String givenString, Creature caller) {
		try {
			if ((givenString.equals("stats")) || (givenString.equals("char"))) {
				System.out.printf("Strength: % 8d\nConstitution: % 4d\n"
						+ "Wisdom: % 10d\nDexterity: % 7d\n"
						+ "Agility: % 9d\nIntelligence: % 4d\n",
						caller.getStrength(),
						caller.getConstitution(),
						caller.getWisdom(),
						caller.getDexterity(),
						caller.getAgility(),
						caller.getIntelligence());
				return true;
			} else {
				return false;
			}
		} catch (IndexOutOfBoundsException e) {
			notAvailable();
			return true;
		}
	}
	
	// Checks if the user requested set verbose or set silent
	static boolean verboseCheck(String givenString, Creature caller) {
		try {
			if ((givenString.equals("verbose")) || (givenString.equals("verb"))) {
				System.out.println("Game set to verbose.");
				Data.verbose = true;
				return true;
			} else if ((givenString.equals("quiet")) || (givenString.equals("silent"))) {
				System.out.println("Game set to quiet.");
				Data.verbose = false;
				return true;
			} else {
				return false;
			}
		} catch (IndexOutOfBoundsException e) {
			return true;
		}
	}
		
	// Checks if the user requested current time
	static boolean timeCheck(String givenString) {
		try {
			if ((givenString.equals("time")) || (givenString.equals("date"))) {
				timeNow();
				return true;
			} else {
				return false;
			}
		} catch (IndexOutOfBoundsException e) {
			notAvailable();
			return true;
		}
	}
	
	// TODO: finish this.
	static boolean helpCheck(String givenString) {
	    try {
            if ((givenString.equals("help")) || (givenString.equals("info"))) {
                System.out.println(inUse.get(1).toString());
                return true;
            } else {
                return false;
            }
        } catch (IndexOutOfBoundsException e) {
            notAvailable();
            return true;
        }
	}
		
	// Checks if the user requested to quit the game
	static void exitCheck(String givenString) {
		if (givenString.replaceAll("\\s", "").replaceAll("\\W", "")
				.equals("exit")) {
			System.exit(0);
		}
	}

}
