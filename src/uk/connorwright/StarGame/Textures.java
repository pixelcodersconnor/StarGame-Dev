package uk.connorwright.StarGame;

import java.awt.image.BufferedImage;

public class Textures {

	// define the variables
	private final SpriteSheet ss;
	public BufferedImage player, missile, enemy;

	public Textures(Game game) {
		ss = new SpriteSheet(game.getSpriteSheet());

		getTextures();

	}

	private void getTextures() {
		player = ss.grabImage(1, 1, 15, 15);
		missile = ss.grabImage(2, 1, 3, 12);
		enemy = ss.grabImage(1, 2, 15, 15);
	}

}
