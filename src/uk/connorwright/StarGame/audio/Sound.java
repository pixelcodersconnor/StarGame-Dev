package uk.connorwright.StarGame.audio;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
	public static final Sound laserFired = new Sound("/music/laser.wav");
	public static final Sound selectEffect = new Sound("/music/select.wav");
	public static final Sound loseSound = new Sound("/music/lose.wav");
	public static final Sound enemyHit = new Sound("/music/sfx/enemy_hit.wav");
	public static final Sound gameWin = new Sound("/music/sfx/game_win.wav");
	// public static final Sound playerHit = new Sound("/player_hit.wav");
	public static final Sound shaunEgg1 = new Sound(
			"/music/easter_eggs/shaun_1.wav");
	public static final Sound shaunEgg2 = new Sound(
			"/music/easter_eggs/shaun_2.wav");
	private AudioClip clip;

	private Sound(String name) {
		try {
			clip = Applet.newAudioClip(Sound.class.getResource(name));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void play() {
		try {
			new Thread() {

				@Override
				public void run() {
					clip.play();
				}
			}.start();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
