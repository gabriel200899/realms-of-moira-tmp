package creatures.roles;

import java.util.ArrayList;
import java.util.Collections;

import creatures.Creature;

public class Shaman extends Creature {

	public Shaman(int race, int level) {
		setRole("Shaman");
		setHealthMaximum(150);
		setManaMaximum(250);
		setRace(race);
		setLevel(level);
		setExperience((level - 1) * (level - 1) * 100);
		ArrayList<String> skills = new ArrayList<String>();
		Collections.addAll(skills,
				"[1] [Attack]",
				"[2] [Block]",
				"[3] [Heal]",
				"[4] [Poison]",
				"[5] [Purify]",
				"[6] [Regrowth]");
		ArrayList<String> skillsInfo = new ArrayList<String>();
		Collections.addAll(skillsInfo,
				"[15 DMG][-- MP]",
				"[+10 Def][1 Turn]",
				"[20 HP / 80 %][20 MP]",
				"[5 HoT / 80 %][Stack 4][20 MP]",
				"[Remove All Debuffs / 60 %][60 MP]",
				"[10 DoT / 60 %][Stack 2][20 MP]");
		ArrayList<String> skillsArray = new ArrayList<String>();
		Collections.addAll(skillsArray,
				"attack",
				"heal",
				"regrowth",
				"purify",
				"poison",
				"block");
		ArrayList<Integer> skillsMana = new ArrayList<Integer>();
		Collections.addAll(skillsMana, 0, 0, 20, 20, 60, 20);
		setSkills(skills);
		setSkillsInfo(skillsInfo);
		setSkillsArray(skillsArray);
		setSkillsMana(skillsMana);
	}

	@Override
	// Heal
	public void skill1(Creature target) {
		if (this.evaluateHit(80)) {
			this.setManaCurrent(this.getManaCurrent() - this.getSkillsMana()[1]);
			this.instantHealing(20);
			System.out.println("Heal!");
		} else
			System.out.println("Miss!");
	}

	@Override
	// Poison
	public void skill2(Creature target) {
		if (this.evaluateHit(60)) {
			this.setManaCurrent(this.getManaCurrent() - this.getSkillsMana()[4]);
			target.setPoisonStack(target.getPoisonStack() + 1);
			System.out.println("Hit!");
		} else
			System.out.println("Miss!");
	}

	@Override
	// Purify
	public void skill3(Creature target) {
		if (this.evaluateHit(60)) {
			this.setManaCurrent(this.getManaCurrent() - this.getSkillsMana()[3]);
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
	// Regrowth
	public void skill4(Creature target) {
		if (this.evaluateHit(80)) {
			this.setManaCurrent(this.getManaCurrent() - this.getSkillsMana()[2]);
			this.setRegrowthStack(this.getRegrowthStack() + 1);
			System.out.println("Heal!");
		} else
			System.out.println("Miss!");
	}

}
