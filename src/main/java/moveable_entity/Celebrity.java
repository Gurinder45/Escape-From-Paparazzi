package moveable_entity;

import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;

import ui.GameFrame;
import util.Direction;
import util.InputHandler;

/**
 * The playable character in the game
 * 
 * @author Gurinder Bhogal
 */
public class Celebrity extends MoveableEntity {
	InputHandler inpHandler;

	public Celebrity(int x, int y, GameFrame gameFrame) {
		super(x, y, gameFrame);
		this.inpHandler = gameFrame.getInputHandler();
		this.speed = 4;
		this.direction = Direction.NONE;
		loadImage();
	}

	@Override
	public void loadImage() {
		if (direction == Direction.UP) {
			try {
				this.img = ImageIO.read(getClass().getResourceAsStream("/images/celeb_up.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (direction == Direction.DOWN) {
			try {
				this.img = ImageIO.read(getClass().getResourceAsStream("/images/celeb_down.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (direction == Direction.RIGHT) {
			try {
				this.img = ImageIO.read(getClass().getResourceAsStream("/images/celeb_right.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (direction == Direction.LEFT) {
			try {
				this.img = ImageIO.read(getClass().getResourceAsStream("/images/celeb_left.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			// if no direction yet
		} else {
			try {
				this.img = ImageIO.read(getClass().getResourceAsStream("/images/celeb_down.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * checks the direction the player is going in and moves it in that direction if
	 * its permissable
	 */
	public void update() {
		direction = inpHandler.getDirection();
		loadImage();

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

	@Override
	public void setDirection(Direction direction) {
		inpHandler.direction = direction;
	}

}
