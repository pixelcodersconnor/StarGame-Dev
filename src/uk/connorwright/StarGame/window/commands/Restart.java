package uk.connorwright.StarGame.window.commands;

import uk.connorwright.StarGame.Game;
import uk.connorwright.StarGame.Game.STATE;

public class Restart {

	public static void restartGame() {
		if (Game.State == STATE.GAME) {
			Game.State = STATE.MENU;
		}
	}

}
