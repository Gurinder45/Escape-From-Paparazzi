package util;

public class Point {
	private int x;
	private int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int val) {
		x = val;
	}
	
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
	
	public void subtractX(int val) {
		x -= val;
	}
	
	public void addX(int val) {
		x += val;
	}
	
	public void subtractY(int val) {
		y -= val;
	}
	
	public void addY(int val) {
		y += val;
	}
	
	
	
	

}
