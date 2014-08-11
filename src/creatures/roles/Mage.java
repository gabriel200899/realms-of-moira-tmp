package creatures.roles;

import java.util.ArrayList;
import java.util.Collections;

import creatures.Creature;

public class Mage extends Creature {

	public Mage(int race, int level) {
		setRole("Mage");
		setHealthMaximum(150);
		setManaMaximum(250);
		setRace(race);
		setLevel(level);
		setExperience((level - 1) * (level - 1) * 100);
		ArrayList<String> skills = new ArrayList<String>();
		ArrayList<String> skillsInfo = new ArrayList<String>();
		ArrayList<String> skillsArray = new ArrayList<String>();
		ArrayList<Integer> skillsMana = new ArrayList<Integer>();
		Collections.addAll(skills,
				"[1] [Attack]",
				"[2] [Block]",
				"[3] [Fireball]",
				"[4] [Weakness]",
				"[5] [Fireblast]",
				"[6] [Agony]");
		Collections.addAll(skillsInfo,
				"[15 DMG][-- MP]",
				"[+10 Def][1 Turn][-- MP]",
				"[20 DMG / 80 %][20 MP]",
				"[-5 Att / -5 Def / 80 %][Stack 2][20 MP]",
				"[40 DMG / 60 %][60 MP]",
				"[5 DoT / 80 %][Stack 4][20 MP]");
		Collections.addAll(skillsArray,
				"attack",
				"block",
				"fireball",
				"weakness",
				"fireblast",
				"agony");
		Collections.addAll(skillsMana, 0, 0, 20, 20, 60, 20);
		setSkills(skills);
		setSkillsInfo(skillsInfo);
		setSkillsArray(skillsArray);
		setSkillsMana(skillsMana);
	}

	@Override
	// Fireball
	public void skill1(Creature target) {
		if (this.evaluateHit(80)) {
			this.setManaCurrent(this.getManaCurrent() - this.getSkillsMana()[1]);
			target.instantDamage(20 + this.getAttackCurrent());
			System.out.println("Hit!");
		} else
			System.out.println("Miss!");
	}

	@Override
	// Weakness
	public void skill2(Creature target) {
		if (this.evaluateHit(80)) {
			this.setManaCurrent(this.getManaCurrent() - this.getSkillsMana()[3]);
			target.setWeakness(target.getWeaknessStack() + 1);
			System.out.println("Hit!");
		} else
			System.out.println("Miss!");
	}

	@Override
	// Fireblast
	public void skill3(Creature target) {
		if (this.evaluateHit(60)) {
			this.setManaCurrent(this.getManaCurrent() - this.getSkillsMana()[2]);
			target.instantDamage(40 + this.getAttackCurrent());
			System.out.println("Hit!");
		} else
			System.out.println("Miss!");
	}

	@Override
	// Agony
	public void skill4(Creature target) {
		if (this.evaluateHit(80)) {
			this.setManaCurrent(this.getManaCurrent() - this.getSkillsMana()[4]);
			target.setAgonyStack(target.getAgonyStack() + 1);
			System.out.println("Hit!");
		} else
			System.out.println("Miss!");
	}

}
