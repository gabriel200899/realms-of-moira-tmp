package creatures;

// This class is a WIP. In the future, all skills will be Skill objects.
public class Skill {

    // It will be possible to call the skill by its name and to print the skill to the console we will format its name.
	private String name;

    // The skill information will also be rendered from these variables.
	private int damage = 0;
	private int healing = 0;

	private int healthCost = 0;
	private int manaCost = 0;

    // True if the skill can kill another creature.
	private boolean deadly = true;

	public Skill(String name)
	{
        this.name = name;
    }
	
	/** Not yet implemented leveling method. */
	public void levelUp() {
	
	}

	public String getName() {
		return name;
	}

	public void setManaCost(int manaCost) {
		this.manaCost = manaCost;
	}

	public int getManaCost() {
		return manaCost;
	}

	public int getHealthCost() {
		return healthCost;
	}

	public void setHealthCost(int healthCost) {
		this.healthCost = healthCost;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getHealing() {
		return healing;
	}

	public void setHealing(int healing) {
		this.healing = healing;
	}

    public void setDeadly(boolean deadly) {
        this.deadly = deadly;
    }

	public boolean isDeadly() {
		return deadly;
	}

    /**
     * This method returns the string that should be printed when listing the skill.
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        // TODO: finish this
        // Adds damage information.

        // Adds effect information.

        // Adds stack information.

        // Adds mana cost information.
        if (this.manaCost != 0) {
            builder.append(String.format("[%d MP]", this.manaCost));
        } else {
            builder.append("[-- MP]");
        }
        return builder.toString();
    }
}
