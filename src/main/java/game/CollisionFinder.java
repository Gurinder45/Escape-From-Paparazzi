package game;

import javafx.scene.shape.Rectangle;
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
			case 1:
				mvbEntity.setCollided(true);
				break;
			case 2:
				mvbEntity.setCollided(true);
				break;
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
				if (disguiseCollected == 1) { // change to 5 later
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
			case 8:
				mvbEntity.setCollided(true);
				break;
			case 9:
				mvbEntity.setCollided(true);
				break;
			case 10:
				mvbEntity.setCollided(true);
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
			checkStaticEntityCollision(cell1Type, next, leftColumn, mvbEntity);
			cell2Type = mapArray[rightColumn][next];
			checkStaticEntityCollision(cell2Type, next, rightColumn, mvbEntity);
		}
		if (mvbEntity.getDirection() == Direction.DOWN) {
			next = ((mvbEntity.getPositionY() + gFrame.cellSize) + mvbEntity.getSpeed()) / gFrame.cellSize;
			cell1Type = mapArray[leftColumn][next];
			checkStaticEntityCollision(cell1Type, next, leftColumn, mvbEntity);
			cell2Type = mapArray[rightColumn][next];
			checkStaticEntityCollision(cell2Type, next, rightColumn, mvbEntity);
		}
		if (mvbEntity.getDirection() == Direction.LEFT) {
			next = (mvbEntity.getPositionX() - mvbEntity.getSpeed()) / gFrame.cellSize;
			cell1Type = mapArray[next][topRow];
			checkStaticEntityCollision(cell1Type, topRow, next, mvbEntity);
			cell2Type = mapArray[next][bottomRow];
			checkStaticEntityCollision(cell2Type, bottomRow, next, mvbEntity);
		}
		if (mvbEntity.getDirection() == Direction.RIGHT) {
			next = (mvbEntity.getPositionX() + gFrame.cellSize + mvbEntity.getSpeed()) / gFrame.cellSize;
			cell1Type = mapArray[next][topRow];
			checkStaticEntityCollision(cell1Type, topRow, next, mvbEntity);
			cell2Type = mapArray[next][bottomRow];
			checkStaticEntityCollision(cell2Type, bottomRow, next, mvbEntity);
		}
	}

	public void checkEnemyMapCollision(Paparazzi paparazzi) {
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

	public void checkEnemyCollision(Paparazzi paparazzi) {
		Paparazzi[] pprzziArray = gFrame.getPaparazzis();
		Rectangle paparazziRect = new Rectangle(paparazzi.getPositionX(), paparazzi.getPositionY(), 32, 32);
		Rectangle otherPaparazziRect = new Rectangle(0, 0, 32, 32);

		for (int i = 0; i < pprzziArray.length; i++) {
			if (paparazzi != pprzziArray[i]) {

				if (paparazzi.getDirection() == Direction.UP) {
					paparazziRect.setY(paparazzi.getPositionY() - paparazzi.getSpeed());
					otherPaparazziRect.setX(pprzziArray[i].getPositionX());
					otherPaparazziRect.setY(pprzziArray[i].getPositionY());
					if (paparazziRect.getLayoutBounds().intersects(otherPaparazziRect.getLayoutBounds())) {
						paparazzi.setCollided(true);
					}
				}
				if (paparazzi.getDirection() == Direction.DOWN) {
					paparazziRect.setY(paparazzi.getPositionY() + paparazzi.getSpeed());
					otherPaparazziRect.setX(pprzziArray[i].getPositionX());
					otherPaparazziRect.setY(pprzziArray[i].getPositionY());
					if (paparazziRect.getLayoutBounds().intersects(otherPaparazziRect.getLayoutBounds())) {
						paparazzi.setCollided(true);
					}
				}
				if (paparazzi.getDirection() == Direction.RIGHT) {
					paparazziRect.setX(paparazzi.getPositionX() + paparazzi.getSpeed());
					otherPaparazziRect.setX(pprzziArray[i].getPositionX());
					otherPaparazziRect.setY(pprzziArray[i].getPositionY());
					if (paparazziRect.getLayoutBounds().intersects(otherPaparazziRect.getLayoutBounds())) {
						paparazzi.setCollided(true);
					}
				}
				if (paparazzi.getDirection() == Direction.LEFT) {
					paparazziRect.setX(paparazzi.getPositionX() - paparazzi.getSpeed());
					otherPaparazziRect.setX(pprzziArray[i].getPositionX());
					otherPaparazziRect.setY(pprzziArray[i].getPositionY());
					if (paparazziRect.getLayoutBounds().intersects(otherPaparazziRect.getLayoutBounds())) {
						paparazzi.setCollided(true);
					}
				}
			}
		}
	}

}
