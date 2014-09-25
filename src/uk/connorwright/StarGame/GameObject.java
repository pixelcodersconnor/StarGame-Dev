package uk.connorwright.StarGame;

import java.awt.Rectangle;

public class GameObject {

	//define the variables
	public double x, y;

	public GameObject(double x, double y){
		this.x = x;
		this.y = y;
	}

	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 15, 15);
	}
}
