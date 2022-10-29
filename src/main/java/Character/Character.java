package Character;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import util.Point;

public class Character {
	protected Point position;
	protected int speed;
	protected Image img;
	
	
	public Character(int x, int y) {
		position = new Point(x,y);
//		loadImage();
		
	}
	
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
		position.setPoint(x,y);
	}
	
	public void update() {
		
	}
	
	public void draw() {
		
	}
	
//	public void loadImage() {
//		String path = "./img/celeb_placeholder.png\\";
//		File file = new File(path);
//		try {
//			img = ImageIO.read(file);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//	}
	
	
	
	
}
