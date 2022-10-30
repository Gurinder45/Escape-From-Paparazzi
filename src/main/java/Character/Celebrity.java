package Character;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import ui.GamePanel;
import util.Direction;
import util.InputHandler;

public class Celebrity extends Character {
	// celebrity should be singleton

	GamePanel gPanel;
	InputHandler inpHandler;
	BufferedImage img;

	public Celebrity(int x, int y, GamePanel gP, InputHandler inpH) {
		super(x, y);
		this.gPanel = gP;
		this.inpHandler = inpH;
		this.speed = 4;
		loadImage();

	}

	public void loadImage() {
		try {
			this.img = ImageIO.read(getClass().getResourceAsStream("/img/celeb_placeholder.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void update() {
		if (inpHandler.getDirection() == Direction.UP) {
			position.subtractY(speed);
		}
		if (inpHandler.getDirection() == Direction.DOWN) {
			position.addY(speed);
		}
		if (inpHandler.getDirection() == Direction.LEFT) {
			position.subtractX(speed);

		}
		if (inpHandler.getDirection() == Direction.RIGHT) {
			position.addX(speed);

		}
	}

	@Override
	public void draw(Graphics g2d) {
		g2d.drawImage(img, this.getPositionX(), this.getPositionY(), 48, 48, null);

	}

}
