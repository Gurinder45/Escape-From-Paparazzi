
package entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import util.Point;

/**
 * @author julio patrick Asifiwe
 *         Handles all the entity needed functions
 */
public abstract class Entity {
	protected Point position;
	protected BufferedImage img;

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
