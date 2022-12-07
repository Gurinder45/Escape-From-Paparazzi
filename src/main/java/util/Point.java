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

	public int getLeftColumn() {
		return (this.getX() + gap) / cellSize;
	}

	public int getRightColumn() {
		return (this.getX() - gap + cellSize) / cellSize;
	}

	public int getTopRow() {
		return (this.getY() + gap) / cellSize;
	}

	public int getBottomRow() {
		return (this.getY() - gap + cellSize) / cellSize;
	}

	public int getNextUp(int speed) {
		return (this.getY() - speed) / cellSize;
	}

	public int getNextDown(int speed) {
		return ((this.getY() + cellSize) + speed) / cellSize;
	}

	public int getNextLeft(int speed) {
		return (this.getX() - speed) / cellSize;
	}

	public int getNextRight(int speed) {
		return (this.getX() + cellSize + speed) / cellSize;
	}

	public int getHitBoxLeft() {
		return this.getX() + gap;
	}

	public int getHitBoxRight() {
		return this.getX() - gap + cellSize;
	}

	public int getHitBoxTop() {
		return this.getY() + gap;
	}

	public int getHitBoxBottom() {
		return this.getY() - gap + cellSize;
	}

}
