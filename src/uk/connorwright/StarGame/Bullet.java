package uk.connorwright.StarGame;

import java.awt.Graphics;
import java.awt.Rectangle;

import uk.connorwright.StarGame.entities.EntityA;


public class Bullet extends GameObject implements EntityA {

	//define the variables


	private Textures tex;
	public Bullet(double x, double y, Textures tex, Game game){
		super(x,y);
		this.tex = tex;

	}

	@Override
	public void tick(){
		y -= 5;


	}

	@Override
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 15, 15);
	}

	@Override
	public void render(Graphics g){
		g.drawImage(tex.missile, (int) x, (int) y, null);
	}

	@Override
	public double getY(){
		return y;
	}
	@Override
	public double getX(){
		return x;
	}
}
