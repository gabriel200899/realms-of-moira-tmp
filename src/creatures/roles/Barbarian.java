package creatures.roles;

import java.util.ArrayList;
import java.util.Collections;
import creatures.Creature;

public class Barbarian extends Creature {
	
	public Barbarian(int race, int level) {
		setRole("Barbarian");
		setHealthMaximum(250);
		setManaMaximum(150);
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

}
