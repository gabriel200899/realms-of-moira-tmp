package creatures.foes;

import creatures.Creature;

public class Spider extends Creature {

    public Spider(int level) {
		setRole("Spider");
		setRace(5);
		setLevel(level);
		setHealthMaximum(20 + 10 * level);
		setManaMaximum(40 + 10 * level);
		setGold(10 + 10 * level);
		setExperience(30 + 10 * level);
    
    }
    
}
