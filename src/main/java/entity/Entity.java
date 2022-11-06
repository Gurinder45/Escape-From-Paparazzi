<<<<<<< HEAD
=======

>>>>>>> c394876712777fe4e044b50ae8d1d620d5698562
package entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import util.Point;

public abstract class Entity {
	protected Point position;
	protected BufferedImage img;

	public Point getPosition() {
		return position;
	}

	public int getPositionX() {
		return position.getX();
	}

	public int getPositionY() {
		return position.getY();
	}

	public void setPosition(int x, int y) {
		position.setPoint(x, y);
	}

	public abstract void draw(Graphics g2d);

	public abstract void loadImage();

}
