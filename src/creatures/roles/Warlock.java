package creatures.roles;

import java.util.ArrayList;
import java.util.Collections;
import creatures.Creature;

public class Warlock extends Creature{

	public Warlock(int race, int level) {
		setRole("Priest");
		setRace(race);
		setLevel(level);
		setHealthMaximum(150);
		setManaMaximum(250);
		setExperience((level - 1) * (level - 1) * 100);		
		ArrayList<String> skills = new ArrayList<String>();
		ArrayList<String> skillsInfo = new ArrayList<String>();
		ArrayList<String> skillsArray = new ArrayList<String>();	
		ArrayList<Integer> skillsMana = new ArrayList<Integer>();	
		Collections.addAll(skills,
				"[1] [Attack]",
				"[2] [Block]",
				"[3] [Potions]",
				"[4] [Darkness Embrace]",
				"[5] [Curse Of Suffering]",
				"[6] [Blood Sacrifice]",
				"[7] [Shadow Bolt]",
				"[8] [Draining Touch]");
		Collections.addAll(skillsInfo,
				"[15 DMG][-- MP]",
				"[+10 Def][1 Turn][-- MP]",
				"[20 HP / 80 %][20 MP]",
				"[Remove All Debuffs / 40 %][80 MP]",
				"[5 DoT / 80 %][Stack 4][20 MP]",
				"[60 HP / 40 %][60 MP]");
		Collections.addAll(skillsArray,
				"attack",
				"block",
				"potions",
				"lesser",
				"cleanse",
				"curse",
				"greater");
		Collections.addAll(skillsMana, 0, 0, 20, 80, 20, 60);
		setSkills(skills);
		setSkillsInfo(skillsInfo);
		setSkillsArray(skillsArray);
		setSkillsMana(skillsMana);
	}
	
}
