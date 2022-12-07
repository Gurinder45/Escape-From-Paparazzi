package moveable_entity;

import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.EnemyMovement;
import ui.GameFrame;
import util.Direction;

/**
 * Represents the enemies in the game
 * 
 * @author Gurinder Bhogal
 */
public class Paparazzi extends MoveableEntity {

	EnemyMovement enemyMovement;

	public Paparazzi(int x, int y) {
		super(x, y);
		this.speed = 1;
		this.enemyMovement = GameFrame.getInstance().getEnemyMovement();
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
		collisionFinder = gFrame.getCollisionFinder();
		collided = false;
		collisionFinder.checkEnemyMapCollision(this);
		collisionFinder.checkEnemyCollision(this);

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
	 * Finds the next direction to move the paparazzi closer to the celebrity
	 * 
	 * @param x celebrities x position
	 * @param y celebrities y position
	 */
	public void update(int x, int y) {
		int gap = 5;
		int playerColumn = (x + gap) / gFrame.cellSize;
		int playerRow = (y + gap) / gFrame.cellSize;
		int startColumn = (this.getPositionX() + gap) / gFrame.cellSize;
		int startRow = (this.getPositionY() + gap) / gFrame.cellSize;
		collisionFinder = gFrame.getCollisionFinder();
		collided = false;
		collisionFinder.checkEnemyMapCollision(this);
		collisionFinder.checkEnemyCollision(this);
		enemyMovement.setNodes(startColumn, startRow, playerColumn, playerRow);
		if (enemyMovement.search()) {
			int nextPositionX = enemyMovement.getNextColumn() * gFrame.cellSize;
			int nextPositionY = enemyMovement.getNextRow() * gFrame.cellSize;

			int leftPosition = this.getPositionX() + gap;
			int rightPosition = this.getPositionX() - gap + gFrame.cellSize;
			int topPosition = this.getPositionY() + gap;
			int bottomPosition = this.getPositionY() - gap + gFrame.cellSize;

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
			int nextColumn = enemyMovement.getNextColumn();
			int nextRow = enemyMovement.getNextRow();
			if (nextColumn == playerColumn && nextRow == playerRow) {
				gFrame.loseGame();
			}
		}
	}

	@Override
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

}
