package util;

import ui.GameFrame;

/**
 * Tracks an Entity with its x and y pixel coordinates
 * 
 * @author julio patrick Asifiwe
 */
public class Point {
	private int x;
	private int y;
	final int gap = 5;
	final int cellSize = GameFrame.getInstance().cellSize;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Get x coordinate
	 * 
	 * @return integer for x coordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * Get y coordinate
	 * 
	 * @return integer for y coordinate
	 */
	public int getY() {
		return y;
	}

	/**
	 * subtract given value from the x coordinate
	 * 
	 * @param val integer value to remove
	 */
	public void subtractX(int val) {
		x -= val;
	}

	/**
	 * add given value to the x coordinate
	 * 
	 * @param val integer value to add
	 */
	public void addX(int val) {
		x += val;
	}

	/**
	 * subtract given value from the y coordinate
	 * 
	 * @param val integer value to remove
	 */
	public void subtractY(int val) {
		y -= val;
	}

	/**
	 * add given value to the y coordinate
	 * 
	 * @param val integer value to add
	 */
	public void addY(int val) {
		y += val;
	}

	/**
	 * Gets the left column number of the Entity
	 * 
	 * @return integer representing column number
	 */
	public int getLeftColumn() {
		return (this.getX() + gap) / cellSize;
	}

	/**
	 * Gets the right column number of the Entity
	 * 
	 * @return integer representing column number
	 */
	public int getRightColumn() {
		return (this.getX() - gap + cellSize) / cellSize;
	}

	/**
	 * Gets the top row number of the Entity
	 * 
	 * @return integer representing row number
	 */
	public int getTopRow() {
		return (this.getY() + gap) / cellSize;
	}

	/**
	 * Gets the bottom number of the Entity
	 * 
	 * @return integer representing row number
	 */
	public int getBottomRow() {
		return (this.getY() - gap + cellSize) / cellSize;
	}

	/**
	 * Gets the next block row above the Entity
	 * 
	 * @return integer representing row number
	 */
	public int getNextUp(int speed) {
		return (this.getY() - speed) / cellSize;
	}

	/**
	 * Gets the next block row below the Entity
	 * 
	 * @return integer representing row number
	 */
	public int getNextDown(int speed) {
		return ((this.getY() + cellSize) + speed) / cellSize;
	}

	/**
	 * Gets the next block column on the left of the Entity
	 * 
	 * @return integer representing colum number
	 */
	public int getNextLeft(int speed) {
		return (this.getX() - speed) / cellSize;
	}

	/**
	 * Gets the next block column on the right of the Entity
	 * 
	 * @return integer representing colum number
	 */
	public int getNextRight(int speed) {
		return (this.getX() + cellSize + speed) / cellSize;
	}

	/**
	 * Gets the top pixel of the Entity hit box
	 * 
	 * @return integer representing pixel
	 */
	public int getHitBoxLeft() {
		return this.getX() + gap;
	}

	/**
	 * Gets the right pixel of the Entity hit box
	 * 
	 * @return integer representing pixel
	 */
	public int getHitBoxRight() {
		return this.getX() - gap + cellSize;
	}

	/**
	 * Gets the left pixel of the Entity hit box
	 * 
	 * @return integer representing pixel
	 */
	public int getHitBoxTop() {
		return this.getY() + gap;
	}

	/**
	 * Gets the bottom pixel of the Entity hit box
	 * 
	 * @return integer representing pixel
	 */
	public int getHitBoxBottom() {
		return this.getY() - gap + cellSize;
	}

}
