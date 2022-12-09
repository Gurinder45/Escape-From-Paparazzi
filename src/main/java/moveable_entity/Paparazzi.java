package moveable_entity;

import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.CollisionFinder;
import game.EnemyMovement;
import util.Direction;

/**
 * Represents the enemies in the game
 * 
 * @author Gurinder Bhogal
 */
public class Paparazzi extends MoveableEntity {
	public Paparazzi(int x, int y) {
		super(x, y);
		this.speed = 1;
		loadImage();
	}

	@Override
	public void draw(Graphics g2d) {
		g2d.drawImage(img, this.getPositionX(), this.getPositionY(), gFrame.cellSize, gFrame.cellSize, null);

	}

	@Override
	public void loadImage() {
		try {
			this.img = ImageIO.read(getClass().getResourceAsStream("/images/paparazzi.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Moves the paparazzi in the given direction
	 * 
	 * @param direction direction to move enemy in
	 */
	public void move(Direction direction) {
		collided = false;
		CollisionFinder.getInstance().checkEnemyMapCollision(this);
		CollisionFinder.getInstance().checkEnemyCollision(this);

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

	/**
	 * Calls the EnemyMovement class to create a path to the main character
	 * 
	 * @param col celebrities column in map
	 * @param row celebrities row in map
	 */
	public void update(int col, int row) {
		int startColumn = this.getLeftColumn();
		int startRow = this.getTopRow();
		collided = false;
		CollisionFinder.getInstance().checkEnemyMapCollision(this);
		CollisionFinder.getInstance().checkEnemyCollision(this);
		EnemyMovement.getInstance().setNodes(startColumn, startRow, col, row);
		if (EnemyMovement.getInstance().search()) {
			findDirection();
			int nextColumn = EnemyMovement.getInstance().getNextColumn();
			int nextRow = EnemyMovement.getInstance().getNextRow();
			if (nextColumn == col && nextRow == row) {
				gFrame.loseGame();
			}
		}
	}

	/**
	 * Finds the next direction to move the paparazzi closer to the celebrity
	 */
	public void findDirection() {
		int nextPositionX = EnemyMovement.getInstance().getNextColumn() * gFrame.cellSize;
		int nextPositionY = EnemyMovement.getInstance().getNextRow() * gFrame.cellSize;

		int leftPosition = this.getHitBoxLeft();
		int rightPosition = this.getHitBoxRight();
		int topPosition = this.getHitBoxTop();
		int bottomPosition = this.getHitBoxBottom();

		// case when next position is above and the enemy is centered in the
		// tile
		if (topPosition > nextPositionY && leftPosition >= nextPositionX
				&& rightPosition < nextPositionX + gFrame.cellSize) {
			direction = Direction.UP;
			move(direction);
			// case when next position is below and the enemy is centered in the
			// tile
		} else if (topPosition < nextPositionY && leftPosition >= nextPositionX
				&& rightPosition < nextPositionX + gFrame.cellSize) {
			direction = Direction.DOWN;
			move(direction);
			// case when next position is left or right
		} else if (topPosition >= nextPositionY && bottomPosition < nextPositionY +
				gFrame.cellSize) {
			if (leftPosition > nextPositionX) {
				direction = Direction.LEFT;
				move(direction);
			}
			if (leftPosition < nextPositionX) {
				direction = Direction.RIGHT;
				move(direction);
			}
			// if a collision is preventing the enemy from moving up, move left or right
		} else if (topPosition > nextPositionY && leftPosition > nextPositionX) {
			direction = Direction.UP;
			move(direction);
			if (collided) {
				direction = Direction.LEFT;
				move(direction);
			}
		} else if (topPosition > nextPositionY && leftPosition < nextPositionX) {
			direction = Direction.UP;
			move(direction);
			if (collided) {
				direction = Direction.RIGHT;
				move(direction);
			}

			// if a collision is preventing the enemy from moving down, move left or right
		} else if (topPosition < nextPositionY && leftPosition > nextPositionX) {
			direction = Direction.DOWN;
			move(direction);
			if (collided) {
				direction = Direction.LEFT;
				move(direction);
			}
		} else if (topPosition < nextPositionY && leftPosition < nextPositionX) {
			direction = Direction.DOWN;
			move(direction);
			if (collided) {
				direction = Direction.RIGHT;
				move(direction);
			}
		}
	}

	@Override
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

}
