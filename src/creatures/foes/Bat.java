package creatures.foes;

import creatures.Creature;

public class Bat extends Creature {

	public Bat(int level) {
		setRole("Bat");
		setRace(5);
		setLevel(level);
		setHealthMaximum(20 + 10 * level);
		setManaMaximum(40 + 10 * level);
		setGold(10 + 10 * level);
		setHealthPotion((int) (Math.random() * 2));
		setExperience(30 + 10 * level);
	}

	@Override
	// Bite
	public void skill1(Creature target) {
		if (this.evaluateHit(80)) {
			this.setManaCurrent(this.getManaCurrent() - 20);
			int damage = 20 + this.getAttackCurrent()
					- target.getDefenseCurrent();
			if (damage > 0) {
				target.setHealthCurrent(target.getHealthCurrent() - damage);
			}
			System.out.println("Bat bit you.");
		} else {
			System.out.println("Bat tried to bite you but it missed.");
		}
	}

	@Override
	// Scratch
	public void skill2(Creature target) {
		if (this.evaluateHit(80)) {
			int damage = 10 + this.getAttackCurrent()
					- target.getDefenseCurrent();
			if (damage > 0) {
				target.setHealthCurrent(target.getHealthCurrent() - damage);
			}
			System.out.println("Bat scratched you.");
		} else {
			System.out.println("Bat tried to scratch you but it missed.");
		}
	}

	@Override
	public void nextSkill(Creature target) {
		if (this.getManaCurrent() >= 20) {
			skill1(target);
		} else {
			skill2(target);
		}
	}

}
