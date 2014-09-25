package uk.connorwright.StarGame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import uk.connorwright.StarGame.entities.EntityA;
import uk.connorwright.StarGame.entities.EntityB;



public class Enemy extends GameObject implements EntityB{

	//define the variables


	private Textures tex;
	Random r = new Random();
	private Game game;
	private Controller c;

	private int speed = r.nextInt(3) + 1;

	public Enemy(double x, double y, Textures tex, Controller c, Game game){
		super(x,y);
		this.tex = tex;
		this.c = c;
		this.game = game;
	}

	@Override
	public void tick(){
		y += speed;

		if(y > Game.HEIGHT * Game.SCALE){
			x = r.nextInt(640);
			y = -10;
		}

		for(int i = 0; i < game.ea.size(); i++)
		{
			EntityA tempEnt = game.ea.get(i);


			if(Physics.Collision(this, tempEnt)){
				c.removeEntity(this);
				game.setEnemy_kill(game.getEnemy_kill() + 1);
			}
		}

	}

	@Override
	public void render(Graphics g){
		g.drawImage(tex.enemy, (int)x, (int)y, null);
	}

	@Override
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 15, 15);
	}

	@Override
	public double getX(){
		return x;
	}

	@Override
	public double getY(){
		return y;
	}

	public void setY(double Y, double y){
		this.y = y;
	}

}
