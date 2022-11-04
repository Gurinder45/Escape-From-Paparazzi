package game;

import moveable_entity.Celebrity;
import moveable_entity.MoveableEntity;
import moveable_entity.Paparazzi;
import ui.GameFrame;
import ui.GamePanel;
import util.Direction;

public class CollisionFinder {
	private GameFrame gFrame;
	private int disguiseCollected;

	public CollisionFinder(GameFrame gFrame) {
		this.gFrame = gFrame;

	}

	public void resetDisguises() {
		disguiseCollected = 0;
	}

	public void checkStaticEntityCollision(int cellType, int index1, int index2, MoveableEntity mvbEntity) {
		int[][] mapArray = gFrame.getGamePanel().getMapArray();
		switch (cellType) {
			case 3:
				mapArray[index2][index1] = 0;
				gFrame.addScore(2);
				disguiseCollected++;
				break;
			case 4:
				mvbEntity.setCollided(true);
				break;
			case 5:
				mapArray[index2][index1] = 0;
				gFrame.addScore(3);
				break;
			case 6:
				if (disguiseCollected == 5) {
					gFrame.winGame();
				} else {
					mvbEntity.setCollided(true);
				}
				break;
			case 7:
				for (int i = 0; i < gFrame.columnNum; i++) {
					if (mapArray[i][index1] == 7) {
						mapArray[i][index1] = 0;
					}
				}
				gFrame.substractScore(4);
				if (gFrame.getScore() < 0) {
					gFrame.loseGame();
				}
				break;

		}
	}

	public void checkMapCollision(MoveableEntity mvbEntity) {
		// find the column(s)/row(s) the entity is in
		int gap = 5;
		int leftColumn = (mvbEntity.getPositionX() + gap) / gFrame.cellSize;
		int rightColumn = (mvbEntity.getPositionX() - gap + gFrame.cellSize) / gFrame.cellSize;
		int topRow = (mvbEntity.getPositionY() + gap) / gFrame.cellSize;
		int bottomRow = (mvbEntity.getPositionY() - gap + gFrame.cellSize) / gFrame.cellSize;

		int cell1Type, cell2Type, next;

		int[][] mapArray = gFrame.getGamePanel().getMapArray();

		if (mvbEntity.getDirection() == Direction.UP) {
			next = (mvbEntity.getPositionY() - mvbEntity.getSpeed()) / gFrame.cellSize;
			cell1Type = mapArray[leftColumn][next];
			cell2Type = mapArray[rightColumn][next];

			if (cell1Type > 2 || cell2Type > 2) {
				if (cell1Type > 2) {
					checkStaticEntityCollision(cell1Type, next, leftColumn, mvbEntity);
				} else {
					checkStaticEntityCollision(cell2Type, next, rightColumn, mvbEntity);
				}
			} else {
				if (gFrame.getGamePanel().isCollidable(cell1Type) || gFrame.getGamePanel().isCollidable(cell2Type)) {
					mvbEntity.setCollided(true);
				}
			}
		}
		if (mvbEntity.getDirection() == Direction.DOWN) {
			next = ((mvbEntity.getPositionY() + gFrame.cellSize) + mvbEntity.getSpeed()) / gFrame.cellSize;
			cell1Type = mapArray[leftColumn][next];
			cell2Type = mapArray[rightColumn][next];
			if (cell1Type > 2 || cell2Type > 2) {
				if (cell1Type > 2) {
					checkStaticEntityCollision(cell1Type, next, leftColumn, mvbEntity);
				} else {
					checkStaticEntityCollision(cell2Type, next, rightColumn, mvbEntity);
				}
			} else {
				if (gFrame.getGamePanel().isCollidable(cell1Type) || gFrame.getGamePanel().isCollidable(cell2Type)) {
					mvbEntity.setCollided(true);
				}
			}
		}
		if (mvbEntity.getDirection() == Direction.LEFT) {
			next = (mvbEntity.getPositionX() - mvbEntity.getSpeed()) / gFrame.cellSize;
			cell1Type = mapArray[next][topRow];
			cell2Type = mapArray[next][bottomRow];
			if (cell1Type > 2 || cell2Type > 2) {
				if (cell1Type > 2) {
					checkStaticEntityCollision(cell1Type, topRow, next, mvbEntity);
				} else {
					checkStaticEntityCollision(cell2Type, bottomRow, next, mvbEntity);
				}
			} else {
				if (gFrame.getGamePanel().isCollidable(cell1Type) || gFrame.getGamePanel().isCollidable(cell2Type)) {
					mvbEntity.setCollided(true);
				}
			}
		}
		if (mvbEntity.getDirection() == Direction.RIGHT) {
			next = (mvbEntity.getPositionX() + gFrame.cellSize + mvbEntity.getSpeed()) / gFrame.cellSize;
			cell1Type = mapArray[next][topRow];
			cell2Type = mapArray[next][bottomRow];
			if (cell1Type > 2 || cell2Type > 2) {
				if (cell1Type > 2) {
					checkStaticEntityCollision(cell1Type, topRow, next, mvbEntity);
				} else {
					checkStaticEntityCollision(cell2Type, bottomRow, next, mvbEntity);
				}
			} else {
				if (gFrame.getGamePanel().isCollidable(cell1Type) || gFrame.getGamePanel().isCollidable(cell2Type)) {
					mvbEntity.setCollided(true);
				}
			}
		}
	}

	public void checkEnemyCollision(Paparazzi paparazzi) {
		int gap = 5;
		int leftColumn = (paparazzi.getPositionX() + gap) / gFrame.cellSize;
		int rightColumn = (paparazzi.getPositionX() - gap + gFrame.cellSize) / gFrame.cellSize;
		int topRow = (paparazzi.getPositionY() + gap) / gFrame.cellSize;
		int bottomRow = (paparazzi.getPositionY() - gap + gFrame.cellSize) / gFrame.cellSize;

		int cell1Type, cell2Type, next;

		int[][] mapArray = gFrame.getGamePanel().getMapArray();

		if (paparazzi.getDirection() == Direction.UP) {
			next = (paparazzi.getPositionY() + gap - paparazzi.getSpeed()) / gFrame.cellSize;
			cell1Type = mapArray[leftColumn][next];
			cell2Type = mapArray[rightColumn][next];
			if (gFrame.getGamePanel().isCollidable(cell1Type) || gFrame.getGamePanel().isCollidable(cell2Type)) {
				paparazzi.setCollided(true);
			}
		}
		if (paparazzi.getDirection() == Direction.DOWN) {
			next = ((paparazzi.getPositionY() - gap + gFrame.cellSize) + paparazzi.getSpeed()) / gFrame.cellSize;
			cell1Type = mapArray[leftColumn][next];
			cell2Type = mapArray[rightColumn][next];
			if (gFrame.getGamePanel().isCollidable(cell1Type) || gFrame.getGamePanel().isCollidable(cell2Type)) {
				paparazzi.setCollided(true);
			}
		}
		if (paparazzi.getDirection() == Direction.LEFT) {
			next = (paparazzi.getPositionX() + gap - paparazzi.getSpeed()) / gFrame.cellSize;
			cell1Type = mapArray[next][topRow];
			cell2Type = mapArray[next][bottomRow];
			if (gFrame.getGamePanel().isCollidable(cell1Type) || gFrame.getGamePanel().isCollidable(cell2Type)) {
				paparazzi.setCollided(true);
			}
		}
		if (paparazzi.getDirection() == Direction.RIGHT) {
			next = (paparazzi.getPositionX() - gap + gFrame.cellSize + paparazzi.getSpeed()) / gFrame.cellSize;
			cell1Type = mapArray[next][topRow];
			cell2Type = mapArray[next][bottomRow];
			if (gFrame.getGamePanel().isCollidable(cell1Type) || gFrame.getGamePanel().isCollidable(cell2Type)) {
				paparazzi.setCollided(true);
			}
		}

	}

}
