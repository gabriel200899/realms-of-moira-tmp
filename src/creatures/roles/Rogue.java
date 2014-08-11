package creatures.roles;

import java.util.ArrayList;
import java.util.Collections;

import creatures.Creature;

public class Rogue extends Creature {

	public Rogue(int race, int level) {
		setRole("Rogue");
		setHealthMaximum(200);
		setManaMaximum(200);
		setRace(race);
		setLevel(level);
		setExperience((level - 1) * (level - 1) * 100);
		ArrayList<String> skills = new ArrayList<String>();
		Collections.addAll(skills,
				"[1] [Attack]",
				"[2] [Block]",
				"[3] [Stab]",
				"[4] [Sap]",
				"[5] [Bleed]",
				"[6] [Backstab]");
		ArrayList<String> skillsInfo = new ArrayList<String>();
		Collections.addAll(skillsInfo,
				"[15 DMG][-- MP]",
				"[+10 Def][1 Turn][-- MP]",
				"[20 DMG / 80 %][20 MP]",
				"[2 Stun / 60 %][40 MP]",
				"[10 DoT / 40 %][Stack 2][40 MP]",
				"[40 DMG / 60 %][60 MP]");
		ArrayList<String> skillsArray = new ArrayList<String>();
		Collections.addAll(skillsArray,
				"attack",
				"block",
				"stab",
				"sap",
				"bleed",
				"backstab");
		ArrayList<Integer> skillsMana = new ArrayList<Integer>();
		Collections.addAll(skillsMana, 0, 0, 20, 60, 40, 40);
		setSkills(skills);
		setSkillsInfo(skillsInfo);
		setSkillsArray(skillsArray);
		setSkillsMana(skillsMana);
	}

	@Override
	// Stab
	public void skill1(Creature target) {
		if (this.evaluateHit(80)) {
			this.setManaCurrent(this.getManaCurrent() - this.getSkillsMana()[1]);
			target.instantDamage(20 + this.getAttackCurrent());
			System.out.println("Hit!");
		} else
			System.out.println("Miss!");
	}

	@Override
	// Sap
	public void skill2(Creature target) {
		if (this.evaluateHit(60)) {
			this.setManaCurrent(this.getManaCurrent() - this.getSkillsMana()[3]);
			target.setStun(true);
			System.out.println("Hit!");
		} else
			System.out.println("Miss!");
	}

	@Override
	// Bleed
	public void skill3(Creature target) {
		if (this.evaluateHit(40)) {
			this.setManaCurrent(this.getManaCurrent() - this.getSkillsMana()[4]);
			target.setBleedStack(target.getBleedStack() + 1);
			System.out.println("Hit!");
		} else
			System.out.println("Miss!");
	}

	@Override
	// Backstab
	public void skill4(Creature target) {
		if (this.evaluateHit(60)) {
			this.setManaCurrent(this.getManaCurrent() - this.getSkillsMana()[2]);
			target.instantDamage(40 + this.getAttackCurrent());
			System.out.println("Hit!");
		} else
			System.out.println("Miss!");
	}

}
