package game;

import moveable_entity.Celebrity;
import moveable_entity.MoveableEntity;
import moveable_entity.Paparazzi;
import ui.GameFrame;
import ui.GamePanel;
import util.Direction;

public class CollisionFinder {
	private GameFrame gFrame;
	private GamePanel gPanel;

	public CollisionFinder(GameFrame gFrame) {
		this.gFrame = gFrame;
		this.gPanel = gFrame.getGamePanel();

	}

	public void checkStaticEntityCollision(int cellType, int index1, int index2) {
		int[][] mapArray = gPanel.getMapArray();
		switch (cellType) {
			case 3:
				mapArray[index2][index1] = 0;
				gFrame.addScore(2);
				System.out.println(gFrame.getScore());
				break;
			case 4:
				// try this
				mapArray[index2][index1] = 0;
				gFrame.substractScore(4);
				System.out.println(gFrame.getScore());
				break;
			case 5:
				mapArray[index2][index1] = 0;
				gFrame.addScore(3);
				System.out.println(gFrame.getScore());
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

		int[][] mapArray = gPanel.getMapArray();

		if (mvbEntity.getDirection() == Direction.UP) {
			next = (mvbEntity.getPositionY() - mvbEntity.getSpeed()) / gFrame.cellSize;
			cell1Type = mapArray[leftColumn][next];
			cell2Type = mapArray[rightColumn][next];

			if (cell1Type > 2 || cell2Type > 2) {
				if (cell1Type > 2) {
					checkStaticEntityCollision(cell1Type, next, leftColumn);
				} else {
					checkStaticEntityCollision(cell2Type, next, rightColumn);
				}
			} else {
				if (gPanel.isCollidable(cell1Type) || gPanel.isCollidable(cell2Type)) {
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
					checkStaticEntityCollision(cell1Type, next, leftColumn);
				} else {
					checkStaticEntityCollision(cell2Type, next, rightColumn);
				}
			} else {
				if (gPanel.isCollidable(cell1Type) || gPanel.isCollidable(cell2Type)) {
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
					checkStaticEntityCollision(cell1Type, topRow, next);
				} else {
					checkStaticEntityCollision(cell2Type, bottomRow, next);
				}
			} else {
				if (gPanel.isCollidable(cell1Type) || gPanel.isCollidable(cell2Type)) {
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
					checkStaticEntityCollision(cell1Type, topRow, next);
				} else {
					checkStaticEntityCollision(cell2Type, bottomRow, next);
				}
			} else {
				if (gPanel.isCollidable(cell1Type) || gPanel.isCollidable(cell2Type)) {
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

		int[][] mapArray = gPanel.getMapArray();

		if (paparazzi.getDirection() == Direction.UP) {
			next = (paparazzi.getPositionY() + gap - paparazzi.getSpeed()) / gFrame.cellSize;
			cell1Type = mapArray[leftColumn][next];
			cell2Type = mapArray[rightColumn][next];
			if (gPanel.isCollidable(cell1Type) || gPanel.isCollidable(cell2Type)) {
				paparazzi.setCollided(true);
			}
		}
		if (paparazzi.getDirection() == Direction.DOWN) {
			next = ((paparazzi.getPositionY() - gap + gFrame.cellSize) + paparazzi.getSpeed()) / gFrame.cellSize;
			cell1Type = mapArray[leftColumn][next];
			cell2Type = mapArray[rightColumn][next];
			if (gPanel.isCollidable(cell1Type) || gPanel.isCollidable(cell2Type)) {
				paparazzi.setCollided(true);
			}
		}
		if (paparazzi.getDirection() == Direction.LEFT) {
			next = (paparazzi.getPositionX() + gap - paparazzi.getSpeed()) / gFrame.cellSize;
			cell1Type = mapArray[next][topRow];
			cell2Type = mapArray[next][bottomRow];
			if (gPanel.isCollidable(cell1Type) || gPanel.isCollidable(cell2Type)) {
				paparazzi.setCollided(true);
			}
		}
		if (paparazzi.getDirection() == Direction.RIGHT) {
			next = (paparazzi.getPositionX() - gap + gFrame.cellSize + paparazzi.getSpeed()) / gFrame.cellSize;
			cell1Type = mapArray[next][topRow];
			cell2Type = mapArray[next][bottomRow];
			if (gPanel.isCollidable(cell1Type) || gPanel.isCollidable(cell2Type)) {
				paparazzi.setCollided(true);
			}
		}

	}

}
