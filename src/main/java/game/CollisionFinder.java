package game;

import java.awt.Rectangle;
import moveable_entity.Celebrity;
import moveable_entity.Paparazzi;
import ui.GameFrame;
import util.Direction;

/**
 * Handles all collisions which happen on the map
 * 
 * @author Gurinder Bhogal
 */

public class CollisionFinder {
	private GameFrame gFrame;
	private int disguiseCollected;

	public CollisionFinder(GameFrame gFrame) {
		this.gFrame = gFrame;
	}

	/**
	 * resets the number of disguises collected for a restart
	 */
	public void resetDisguises() {
		disguiseCollected = 0;
	}

	/**
	 * Given the type of cell the player is about to walk into,
	 * make the appropriate change to the map or game state
	 *
	 * @param cellType  the type of cell that the character is walking into
	 * @param rowIndex  the row the cell is on
	 * @param colIndex  the colum the cell is on
	 * @param character the character
	 */
	public void checkCellType(int cellType, int rowIndex, int colIndex, Celebrity character) {
		int[][] mapArray = gFrame.getGamePanel().getMapArray();
		switch (cellType) {
			case 1:
				character.setCollided(true);
				break;
			case 2:
				character.setCollided(true);
				break;
			case 3:
				mapArray[colIndex][rowIndex] = 0;
				gFrame.addScore(2);
				disguiseCollected++;
				break;
			case 4:
				character.setCollided(true);
				break;
			case 5:
				mapArray[colIndex][rowIndex] = 0;
				gFrame.addScore(3);
				break;
			case 6:
				if (disguiseCollected >= 4) {
					gFrame.winGame();
				} else {
					character.setCollided(true);
				}
				break;
			case 7:
				for (int i = 0; i < gFrame.columnNum; i++) {
					if (mapArray[i][rowIndex] == 7) {
						mapArray[i][rowIndex] = 0;
					}
				}
				gFrame.substractScore(4);
				if (gFrame.getScore() < 0) {
					gFrame.loseGame();
				}
				break;
			case 8:
				character.setCollided(true);
				break;
			case 9:
				character.setCollided(true);
				break;
			case 10:
				character.setCollided(true);
				break;
		}
	}

	/**
	 * Checks which cells the character is about to walk into and calls
	 * CheckCellType
	 * to take the appropriate action
	 * 
	 * @param character the Character which is checked
	 */
	public void checkMapCollision(Celebrity character) {
		// find the column(s)/row(s) the entity is in
		int gap = 5;
		int leftColumn = (character.getPositionX() + gap) / gFrame.cellSize;
		int rightColumn = (character.getPositionX() - gap + gFrame.cellSize) / gFrame.cellSize;
		int topRow = (character.getPositionY() + gap) / gFrame.cellSize;
		int bottomRow = (character.getPositionY() - gap + gFrame.cellSize) / gFrame.cellSize;

		int cell1Type, cell2Type, next;

		int[][] mapArray = gFrame.getGamePanel().getMapArray();

		if (character.getDirection() == Direction.UP) {
			next = (character.getPositionY() - character.getSpeed()) / gFrame.cellSize;
			cell1Type = mapArray[leftColumn][next];
			checkCellType(cell1Type, next, leftColumn, character);
			cell2Type = mapArray[rightColumn][next];
			checkCellType(cell2Type, next, rightColumn, character);
		}
		if (character.getDirection() == Direction.DOWN) {
			next = ((character.getPositionY() + gFrame.cellSize) + character.getSpeed()) / gFrame.cellSize;
			cell1Type = mapArray[leftColumn][next];
			checkCellType(cell1Type, next, leftColumn, character);
			cell2Type = mapArray[rightColumn][next];
			checkCellType(cell2Type, next, rightColumn, character);
		}
		if (character.getDirection() == Direction.LEFT) {
			next = (character.getPositionX() - character.getSpeed()) / gFrame.cellSize;
			cell1Type = mapArray[next][topRow];
			checkCellType(cell1Type, topRow, next, character);
			cell2Type = mapArray[next][bottomRow];
			checkCellType(cell2Type, bottomRow, next, character);
		}
		if (character.getDirection() == Direction.RIGHT) {
			next = (character.getPositionX() + gFrame.cellSize + character.getSpeed()) / gFrame.cellSize;
			cell1Type = mapArray[next][topRow];
			checkCellType(cell1Type, topRow, next, character);
			cell2Type = mapArray[next][bottomRow];
			checkCellType(cell2Type, bottomRow, next, character);
		}
	}

	/**
	 * Checks the cells the enemy is going to move to and checks if it should be
	 * collided with
	 * 
	 * @param enemy the enemy to be checked
	 */
	public void checkEnemyMapCollision(Paparazzi enemy) {
		int gap = 5;
		int leftColumn = (enemy.getPositionX() + gap) / gFrame.cellSize;
		int rightColumn = (enemy.getPositionX() - gap + gFrame.cellSize) / gFrame.cellSize;
		int topRow = (enemy.getPositionY() + gap) / gFrame.cellSize;
		int bottomRow = (enemy.getPositionY() - gap + gFrame.cellSize) / gFrame.cellSize;
		int cell1Type, cell2Type, next;
		int[][] mapArray = gFrame.getGamePanel().getMapArray();

		if (enemy.getDirection() == Direction.UP) {
			next = (enemy.getPositionY() + gap - enemy.getSpeed()) / gFrame.cellSize;
			cell1Type = mapArray[leftColumn][next];
			cell2Type = mapArray[rightColumn][next];
			if (gFrame.getGamePanel().isCollidable(cell1Type) || gFrame.getGamePanel().isCollidable(cell2Type)) {
				enemy.setCollided(true);
			}
		}
		if (enemy.getDirection() == Direction.DOWN) {
			next = ((enemy.getPositionY() - gap + gFrame.cellSize) + enemy.getSpeed()) / gFrame.cellSize;
			cell1Type = mapArray[leftColumn][next];
			cell2Type = mapArray[rightColumn][next];
			if (gFrame.getGamePanel().isCollidable(cell1Type) || gFrame.getGamePanel().isCollidable(cell2Type)) {
				enemy.setCollided(true);
			}
		}
		if (enemy.getDirection() == Direction.LEFT) {
			next = (enemy.getPositionX() + gap - enemy.getSpeed()) / gFrame.cellSize;
			cell1Type = mapArray[next][topRow];
			cell2Type = mapArray[next][bottomRow];
			if (gFrame.getGamePanel().isCollidable(cell1Type) || gFrame.getGamePanel().isCollidable(cell2Type)) {
				enemy.setCollided(true);
			}
		}
		if (enemy.getDirection() == Direction.RIGHT) {
			next = (enemy.getPositionX() - gap + gFrame.cellSize + enemy.getSpeed()) / gFrame.cellSize;
			cell1Type = mapArray[next][topRow];
			cell2Type = mapArray[next][bottomRow];
			if (gFrame.getGamePanel().isCollidable(cell1Type) || gFrame.getGamePanel().isCollidable(cell2Type)) {
				enemy.setCollided(true);
			}
		}

	}

	/**
	 * Checks if enemies are about to collide with each other
	 * 
	 * @param enemy enemy to be checked
	 */
	public void checkEnemyCollision(Paparazzi enemy) {
		Paparazzi[] pprzziArray = gFrame.getPaparazzis();
		Rectangle paparazziRect = new Rectangle(enemy.getPositionX(), enemy.getPositionY(), 32, 32);
		Rectangle otherPaparazziRect = new Rectangle(0, 0, 32, 32);

		for (int i = 0; i < pprzziArray.length; i++) {
			if (enemy != pprzziArray[i]) {

				if (enemy.getDirection() == Direction.UP) {
					paparazziRect.y = enemy.getPositionY() - enemy.getSpeed();
					otherPaparazziRect.x = pprzziArray[i].getPositionX();
					otherPaparazziRect.y = pprzziArray[i].getPositionY();
					if (paparazziRect.intersects(otherPaparazziRect)) {
						enemy.setCollided(true);
					}
				}
				if (enemy.getDirection() == Direction.DOWN) {
					paparazziRect.y = enemy.getPositionY() + enemy.getSpeed();
					otherPaparazziRect.x = pprzziArray[i].getPositionX();
					otherPaparazziRect.y = pprzziArray[i].getPositionY();
					if (paparazziRect.intersects(otherPaparazziRect)) {
						enemy.setCollided(true);
					}
				}
				if (enemy.getDirection() == Direction.RIGHT) {
					paparazziRect.x = enemy.getPositionX() + enemy.getSpeed();
					otherPaparazziRect.x = pprzziArray[i].getPositionX();
					otherPaparazziRect.y = pprzziArray[i].getPositionY();
					if (paparazziRect.intersects(otherPaparazziRect)) {
						enemy.setCollided(true);
					}
				}
				if (enemy.getDirection() == Direction.LEFT) {
					paparazziRect.x = enemy.getPositionX() - enemy.getSpeed();
					otherPaparazziRect.x = pprzziArray[i].getPositionX();
					otherPaparazziRect.y = pprzziArray[i].getPositionY();
					if (paparazziRect.intersects(otherPaparazziRect)) {
						enemy.setCollided(true);
					}
				}
			}
		}
	}

	public int getDisguiseNumber() {
		return disguiseCollected;

	}

}
