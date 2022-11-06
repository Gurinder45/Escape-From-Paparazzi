package util;

/**
 * @author julio patrick Asifiwe
 * 
 * handles the x and y point  on the map or the frame
 */
public class Point {
	private int x;
	private int y;
	
	/**
	 * @param x 
	 * @param y
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @return the value of x position
	 */
	public int getX() {
		return x;
	}
	
	public void setX(int val) {
		x = val;
	}
	
	/**
	 * @return the value of y position
	 */
	public int getY() {
		return y;
	}
	
	public void setY(int val) {
		y = val;
	}
	
	
	public void setPoint(int valX, int valY) {
		x = valX;
		y = valY;
	}
	
	/**
	 * @param val the value to remove from the current x position
	 */
	public void subtractX(int val) {
		x -= val;
	}
	
	/**
	 * @param val the value to add from the current x position
	 */
	public void addX(int val) {
		x += val;
	}
	
	/**
	 * @param val the value to remove from the current y position
	 */
	public void subtractY(int val) {
		y -= val;
	}
	
	/**
	 * @param val the value to add from the current y position
	 */
	public void addY(int val) {
		y += val;
	}
	
	
	
	

}
