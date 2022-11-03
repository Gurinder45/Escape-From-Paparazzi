package moveable_entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.CollisionFinder;
import ui.GameFrame;
import util.Direction;
import util.InputHandler;

public class Celebrity extends MoveableEntity {
	// celebrity should be singleton

	InputHandler inpHandler;

	public Celebrity(int x, int y, GameFrame gameFrame) {
		super(x, y, gameFrame);
		this.inpHandler = gameFrame.getInputHandler();
		this.speed = 4;
		this.direction = Direction.NONE;
		loadImage();
	}

	public void loadImage() {
		try {
			this.img = ImageIO.read(getClass().getResourceAsStream("/images/celeb_placeholder.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void update() {
		direction = inpHandler.getDirection();

		collisionFinder = gFrame.getCollisionFinder();
		collided = false;
		collisionFinder.checkMapCollision(this);

		if (!collided) {
			if (direction == Direction.UP) {
				position.subtractY(speed);
			}
			if (direction == Direction.DOWN) {
				position.addY(speed);
			}
			if (direction == Direction.LEFT) {
				position.subtractX(speed);

			}
			if (direction == Direction.RIGHT) {
				position.addX(speed);

			}

		}

	}

	@Override
	public void draw(Graphics g2d) {
		g2d.drawImage(img, this.getPositionX(), this.getPositionY(), gFrame.cellSize, gFrame.cellSize, null);

	}

}
