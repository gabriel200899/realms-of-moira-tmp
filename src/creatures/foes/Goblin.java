package creatures.foes;

import creatures.Creature;

public class Goblin extends Creature {

	public Goblin(int level) {
		setRole("Goblin");
		setRace(7);
		setLevel(level);
		setHealthMaximum(20 + 10 * level);
		setManaMaximum(40 + 10 * level);
		setGold(10 + 10 * level);
		setExperience(30 + 10 * level);
	}
}
