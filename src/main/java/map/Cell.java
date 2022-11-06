package map;

import java.awt.image.BufferedImage;

/**
 * Used to make op the map of the game
 * A cell can be collided with or not
 * 
 * @author Gurinder Bhogal
 */
public class Cell {
	BufferedImage img;
	private Boolean collidable;

	public Cell() {
		this.collidable = false;
	}

	/**
	 * Change the cell to be collidable or not
	 * 
	 * @param bool
	 */
	public void setCollidable(Boolean bool) {
		collidable = bool;
	}

	/**
	 * See if the cell is collidable or not
	 * 
	 * @return true if collidable, false if not
	 */
	public boolean getCollidable() {
		return collidable;
	}

}
