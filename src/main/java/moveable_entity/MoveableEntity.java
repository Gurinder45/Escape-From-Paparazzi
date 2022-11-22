package moveable_entity;

import entity.Entity;
import game.CollisionFinder;
import ui.GameFrame;
import util.Direction;
import util.Point;

/**
 * Super class of all entities which can move
 * 
 * @author Gurinder Bhogal
 */
public abstract class MoveableEntity extends Entity {
	protected Direction direction;
	protected int speed;
	protected boolean collided;
	protected GameFrame gFrame;
	CollisionFinder collisionFinder;

	public MoveableEntity(int x, int y, GameFrame gameFrame) {
		position = new Point(x, y);
		this.gFrame = gameFrame;
	}

	public abstract void setDirection(Direction direction);

	/**
	 * Return the direction the entity is heading in
	 * 
	 * @return direction of the entity
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * Returns the speed of the entity
	 * 
	 * @return integer representing speed
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * Set if the entity has collided into something
	 * 
	 * @param bool boolean representing if the entity collided
	 */
	public void setCollided(boolean bool) {
		collided = bool;
	}

}
