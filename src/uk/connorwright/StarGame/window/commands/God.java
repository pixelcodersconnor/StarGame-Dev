package uk.connorwright.StarGame.window.commands;

import uk.connorwright.StarGame.Game;
import uk.connorwright.StarGame.Game.STATE;

public class God {

	public static void godMode() {
		if (Game.State == STATE.GAME) {
			for(int i = 0; i < Game.HEALTH; i++) {
				Game.HEALTH = 1000000;	
			}
			
		} else {
			System.out.println("You must be in-game!");
		}
	}

}
