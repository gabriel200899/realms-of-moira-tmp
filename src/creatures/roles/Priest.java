package creatures.roles;

import java.util.ArrayList;
import java.util.Collections;

import creatures.Creature;

public class Priest extends Creature {

	public Priest(int race, int level) {
		setRole("Priest");
		setHealthMaximum(150);
		setManaMaximum(250);
		setRace(race);
		setLevel(level);
		setExperience((level - 1) * (level - 1) * 100);
		ArrayList<String> skills = new ArrayList<String>();
		Collections.addAll(skills,
				"[1] [Attack]",
				"[2] [Block]",
				"[3] [Potions]",
				"[4] [Lesser Heal]",
				"[5] [Cleanse]",
				"[6] [Curse]",
				"[7] [Greater Heal]");
		ArrayList<String> skillsInfo = new ArrayList<String>();
		Collections.addAll(skillsInfo,
				"[15 DMG][-- MP]",
				"[+10 Def][1 Turn][-- MP]",
				"[20 HP / 80 %][20 MP]",
				"[Remove All Debuffs / 40 %][80 MP]",
				"[5 DoT / 80 %][Stack 4][20 MP]",
				"[60 HP / 40 %][60 MP]");
		ArrayList<String> skillsArray = new ArrayList<String>();
		Collections.addAll(skillsArray,
				"attack",
				"block",
				"lesser",
				"cleanse",
				"curse",
				"greater");
		ArrayList<Integer> skillsMana = new ArrayList<Integer>();
		Collections.addAll(skillsMana, 0, 0, 20, 80, 20, 60);
		setSkills(skills);
		setSkillsInfo(skillsInfo);
		setSkillsArray(skillsArray);
		setSkillsMana(skillsMana);
	}

	@Override
	// Lesser Heal
	public void skill1(Creature target) {
		if (this.evaluateHit(80)) {
			this.setManaCurrent(this.getManaCurrent() - this.getSkillsMana()[1]);
			this.instantHealing(20);
			System.out.println("Heal!");
		} else
			System.out.println("Miss!");
	}

	@Override
	// Cleanse
	public void skill2(Creature target) {
		if (this.evaluateHit(40)) {
			this.setManaCurrent(this.getManaCurrent() - this.getSkillsMana()[2]);
			this.setWeakness(0);
			this.setAgonyStack(0);
			this.setBleedStack(0);
			this.setPoisonStack(0);
			this.setCurseStack(0);
			System.out.println("Heal!");
		} else
			System.out.println("Miss!");
	}

	@Override
	// Curse
	public void skill3(Creature target) {
		if (this.evaluateHit(80)) {
			this.setManaCurrent(this.getManaCurrent() - this.getSkillsMana()[3]);
			target.setCurseStack(target.getCurseStack() + 1);
			System.out.println("Hit!");
		} else
			System.out.println("Miss!");
	}

	@Override
	// Greater Heal
	public void skill4(Creature target) {
		if (this.evaluateHit(80)) {
			this.setManaCurrent(this.getManaCurrent() - this.getSkillsMana()[4]);
			this.instantHealing(60);
			System.out.println("Heal!");
		} else
			System.out.println("Miss!");
	}

}
