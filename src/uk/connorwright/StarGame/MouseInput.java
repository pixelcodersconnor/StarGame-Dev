package uk.connorwright.StarGame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import uk.connorwright.StarGame.Game.STATE;
import uk.connorwright.StarGame.audio.Sound;



public class MouseInput implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {

		int mx = e.getX();
		int my = e.getY();
		/**
		 * public Rectangle playButton = new Rectangle(Game.WIDTH / 2 + 120,
		 * 150, 100, 50); public Rectangle helpButton = new Rectangle(Game.WIDTH
		 * / 2 + 120, 250, 100, 50); public Rectangle quitButton = new
		 * Rectangle(Game.WIDTH / 2 + 120, 350, 100, 50);
		 */
		// Play button
		if (Game.State == STATE.MENU) {
			if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220) {
				if (my >= 150 && my <= 200) {
					// Pressed Play Button
					Game.State = Game.STATE.GAME;
					Sound.selectEffect.play();
				}

			}

			// Quit button

			if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220) {
				if (my >= 350 && my <= 400) {
					// Pressed Quit Button
					System.exit(1);

				}
			}
			// public Rectangle bugsButton = new Rectangle(Game.WIDTH / 2 + 120,
			// 250, 100, 50);
			// Controls button

			if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220) {
				if (my >= 250 && my <= 300) {
					System.out
					.println("The bugs feature does not currently work - please try again in a later update!");
					Sound.selectEffect.play();

				}

			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
