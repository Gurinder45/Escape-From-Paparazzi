package moveable_entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import game.CollisionFinder;
import ui.GameFrame;
import util.Direction;
import util.Point;

/**
 * Super class of all entities which can move
 * 
 * @author Gurinder Bhogal
 */
public abstract class MoveableEntity {
	protected Point position;
	protected BufferedImage img;
	protected Direction direction;
	protected int speed;
	protected boolean collided;
	protected GameFrame gFrame;
	CollisionFinder collisionFinder;

	public MoveableEntity(int x, int y, GameFrame gameFrame) {
		position = new Point(x, y);
		this.gFrame = gameFrame;
	}

	/**
	 * Sets the direction for a moveable entity
	 * 
	 * @param direction Direction for the entity to move of type Direction
	 */
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

	public boolean getCollided() {
		return collided;
	}

	public Point getPosition() {
		return position;
	}

	/**
	 * @return the x (value) position of the entity
	 */
	public int getPositionX() {
		return position.getX();
	}

	/**
	 * @return the y (value) position of the entity
	 */
	public int getPositionY() {
		return position.getY();
	}

	/**
	 * draws 2d the entity to be shown in the map
	 * 
	 * @param g2d to be drawn
	 */
	public abstract void draw(Graphics g2d);

	/**
	 * loads the image in order for it to be drawn
	 */
	public abstract void loadImage();

}
