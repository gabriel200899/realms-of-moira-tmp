package core;

// Make Data and Launcher the same class?

public class Launcher {
    
    private static final int CONSOLE_WIDTH = 80;
    /** 40 whitespace: used for centering other strings. */
    private static final String BLANK = "                                        ";
    private static final String NAME = "Realms of Moira";
    private static final String REPO = "https://github.com/mafagafogigante/realms-of-moira-tmp";
    
	public static void main(String[] args) {
		printCenter(NAME);
		printCenter(REPO);
		while (true) {
			Data.start();
		}
	}
	
	private static void printCenter(String s) {
	    if (s.length() < CONSOLE_WIDTH) {
	        System.out.println(BLANK.substring(0, (CONSOLE_WIDTH - s.length()) / 2) + s);
	    } else {
            System.out.println(s);
	    }
	}
}