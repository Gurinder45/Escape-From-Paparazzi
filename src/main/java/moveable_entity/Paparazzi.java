package moveable_entity;

import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;

import ui.GameFrame;

public class Paparazzi extends MoveableEntity {

	public Paparazzi(int x, int y, GameFrame gameFrame) {
		super(x,y, gameFrame);
		this.speed = 2;
		loadImage();
	}

	@Override
	public void draw(Graphics g2d) {
		g2d.drawImage(img, this.getPositionX(), this.getPositionY(), gFrame.cellSize, gFrame.cellSize, null);
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadImage() {
		try {
			this.img = ImageIO.read(getClass().getResourceAsStream("/images/paparazzi.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

}
