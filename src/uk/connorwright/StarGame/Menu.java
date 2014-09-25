package uk.connorwright.StarGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;


public class Menu {
	public static final String VERSION = "Beta v2";





	Random random = new Random();

	public Rectangle playButton = new Rectangle(Game.WIDTH / 2 + 120, 150, 100, 50);
	public Rectangle quitButton = new Rectangle(Game.WIDTH / 2 + 120, 350, 100, 50);
	public Rectangle bugsButton = new Rectangle(Game.WIDTH / 2 + 120, 250, 100, 50);

	public JTextComponent info = new JTextArea();
	public JTextComponent randtext = new JTextArea();

	public void render(Graphics g){

		Graphics2D g2d = (Graphics2D) g;







		Font space = new Font("SpaceBang", Font.BOLD, 60);
		g.setFont(space);
		g.setColor(Color.white);
		g.drawString("StarGame", 150, 100);

		Font space1 = new Font("SpaceBang", Font.BOLD, 30);
		g.setFont(space1);
		g.drawString("Play", playButton.x + 8, playButton.y + 35);
		g2d.draw(playButton);


		g.drawString("Quit", quitButton.x, quitButton.y + 35);
		g2d.draw(quitButton);

		Font spaceFooter = new Font("Arial", Font.ITALIC, 16);
		g.setFont(spaceFooter);
		g.drawString("Made by Connor Wright", 475, 470);

		Font r1 = new Font("Arial", Font.ITALIC, 14);
		g.setFont(r1);

		Font version = new Font("SpaceBang", Font.ITALIC, 17);
		g.setFont(version);
		g.drawString("Version - " + VERSION, 10, 470);

		Font bugs = new Font("SpaceBang", Font.BOLD, 30);
		g.setFont(bugs);
		g.drawString("Bugs", bugsButton.x + 5, bugsButton.y + 35);
		g2d.draw(bugsButton);


		g.setFont(version);
		g.drawString("Now in BETA", 440, 126);




	}

















}
