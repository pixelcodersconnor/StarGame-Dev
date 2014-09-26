package uk.connorwright.StarGame.window.commands.help;

public class DisplayCommands {

	private static String divider = "-----------------------------------------------------------------------------";

	public static void showHelp() {
		System.out.println("HELP MENU");
		System.out.println(divider);
		System.out.println("CHEATS:");
		System.out.println("god - Enable god mode in-game");
		System.out.println(divider);
		System.out.println("HELPFUL:");
		System.out.println("restart - Restarts the game");
		System.out.println(divider);
		System.out.println("DEV:");
		System.out.println("nextstate - Switches game state");

	}

}
