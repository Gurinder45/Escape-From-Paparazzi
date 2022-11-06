package util;

/**
 * Tracks an Entity with its x and y pixel coordinates
 * 
 * @author julio patrick Asifiwe
 */
public class Point {
	private int x;
	private int y;

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

}
