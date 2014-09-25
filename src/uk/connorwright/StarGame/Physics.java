package uk.connorwright.StarGame;

import uk.connorwright.StarGame.entities.EntityA;
import uk.connorwright.StarGame.entities.EntityB;



public class Physics {

	public static boolean Collision(EntityA enta, EntityB entb) {

		if (enta.getBounds().intersects(entb.getBounds())) {
			return true;
		}

		return false;
	}

	public static boolean Collision(EntityB entb, EntityA enta) {

		if (entb.getBounds().intersects(enta.getBounds())) {
			return true;

		}

		return false;
	}

}
