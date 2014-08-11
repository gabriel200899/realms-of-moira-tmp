package creatures.foes;

import creatures.Creature;

public class Wolf extends Creature {

	public Wolf(int level) {
		setRole("Wolf");
		setRace(5);
		setLevel(level);
		setHealthMaximum(40 + 10 * level);
		setManaMaximum(60 + 10 * level);
		setGold(20 + 20 * level);
		setHealthPotion((int) (Math.random() * 2));
		setExperience(40 + 20 * level);
	}

	@Override
	public void skill1(Creature target) {
		if (this.evaluateHit(80)) {
			this.setManaCurrent(this.getManaCurrent() - 30);
			int damage = 40 + (int) (Math.random() * 11)
					+ this.getAttackCurrent();
			target.instantDamage(damage);
		} else {
			target.instantDamage(0);
		}
	}

	@Override
	public void skill2(Creature target) {
		if (this.evaluateHit(80)) {
			int damage = 10 + this.getAttackCurrent()
					- target.getDefenseCurrent();
			if (damage > 0) {
				target.setHealthCurrent(target.getHealthCurrent() - damage);
			}
			System.out.println("Wolf scratched you.");
		} else {
			System.out.println("Wolf tried to scratch you but it missed.");
		}
	}

	@Override
	public void nextSkill(Creature target) {
		if (this.getManaCurrent() >= 30) {
			skill1(target);
		} else {
			skill2(target);
		}
	}

}
