package static_entity;

import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;

import ui.GameFrame;

public class Camera extends StaticEntity {

	public Camera(int x, int y, GameFrame gameFrame) {
		super(x, y, gameFrame);
		loadImage();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics g2d) {
		g2d.drawImage(img, this.getPositionX(), this.getPositionY(), gameFrame.cellSize, gameFrame.cellSize, null);

	}

	@Override
	public void loadImage() {
		try {
			this.img = ImageIO.read(getClass().getResourceAsStream("/images/camera.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
