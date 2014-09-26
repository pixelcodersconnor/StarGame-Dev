package uk.connorwright.StarGame.window.commands.dev;

import uk.connorwright.StarGame.Game;
import uk.connorwright.StarGame.Game.STATE;

public class NextState {

	public static void toggleState() {
		if (Game.State == STATE.GAME) {
			Game.State = STATE.MENU;

		} else if (Game.State == STATE.MENU) {
			Game.State = STATE.GAME;
			Game.HEALTH = 200;
		}
	}

}
