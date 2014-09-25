package uk.connorwright.StarGame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{

	//define the variables
	Game game;

	public KeyInput(Game game){
		this.game = game;
	}


	@Override
	public void keyPressed(KeyEvent e){
		game.keyPressed(e);

	}


	@Override
	public void keyReleased(KeyEvent e){
		game.keyReleased(e);
	}

}
