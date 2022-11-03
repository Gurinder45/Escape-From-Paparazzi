package moveable_entity;

import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.EnemyMovement;
import ui.GameFrame;
import util.Direction;

public class Paparazzi extends MoveableEntity {

	EnemyMovement enemyMovement;

	public Paparazzi(int x, int y, GameFrame gameFrame) {
		super(x, y, gameFrame);
		this.speed = 2;
		this.enemyMovement = gameFrame.getEnemyMovement();
		loadImage();
	}

	@Override
	public void draw(Graphics g2d) {
		g2d.drawImage(img, this.getPositionX(), this.getPositionY(), gFrame.cellSize, gFrame.cellSize, null);

	}

	public void update(int x, int y) {
		findPath(x, y);

	}

	@Override
	public void loadImage() {
		try {
			this.img = ImageIO.read(getClass().getResourceAsStream("/images/paparazzi.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void move(Direction direction) {
		collisionFinder = gFrame.getCollisionFinder();
		collided = false;
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

	public void findPath(int x, int y) {
		int gap = 5;
		int playerColumn = (x + gap) / gFrame.cellSize;
		int playerRow = (y + gap) / gFrame.cellSize;
		int startColumn = (this.getPositionX() + gap) / gFrame.cellSize;
		int startRow = (this.getPositionY() + gap) / gFrame.cellSize;
		collisionFinder = gFrame.getCollisionFinder();
		collided = false;
		collisionFinder.checkEnemyCollision(this);
		enemyMovement.setNodes(startColumn, startRow, playerColumn, playerRow);
		if (enemyMovement.search()) {
			int nextPositionX = enemyMovement.getNextColumn();
			int nextPositionY = enemyMovement.getNextRow();

			int leftPosition = this.getPositionX() + gap;
			int rightPosition = this.getPositionX() - gap + gFrame.cellSize;
			int topPosition = this.getPositionY() + gap;
			int bottomPosition = this.getPositionY() - gap + gFrame.cellSize;

			// case when next position is above and the enemy is perfectly centered in the
			// tile
			if (topPosition > nextPositionY && leftPosition >= nextPositionX
					&& rightPosition < nextPositionX + gFrame.cellSize) {
				direction = Direction.UP;
				move(direction);
				// case when next position is below and the enemy is perfectly centered in the
				// tile
			} else if (topPosition < nextPositionY && leftPosition >= nextPositionX
					&& rightPosition < nextPositionX + gFrame.cellSize) {
				direction = Direction.DOWN;
				move(direction);
				// case when next position is above or below but enemy needs to move into the
				// center of the tile
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
	}

}
