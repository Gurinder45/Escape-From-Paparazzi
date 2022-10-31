package game;

import moveable_entity.MoveableEntity;
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

	public void checkEntity(MoveableEntity mvbEntity) {
		// find the column(s)/row(s) the entity is in
		int leftColumn = mvbEntity.getPositionX() / gFrame.cellSize;
		int rightColumn = (mvbEntity.getPositionX() + gFrame.cellSize) / gFrame.cellSize;
		int topRow = mvbEntity.getPositionY() / gFrame.cellSize;
		int bottomRow = (mvbEntity.getPositionY() + gFrame.cellSize) / gFrame.cellSize;

		int cell1Type, cell2Type, next;

		int[][] mapArray = gPanel.getMapArray();

		if (mvbEntity.getDirection() == Direction.UP) {
			next = (mvbEntity.getPositionY() - mvbEntity.getSpeed()) / gFrame.cellSize;
			cell1Type = mapArray[leftColumn][next];
			cell2Type = mapArray[rightColumn][next];
			if (gPanel.isCollidable(cell1Type) || gPanel.isCollidable(cell2Type)) {
				mvbEntity.setCollided(true);
			}
		}
		if (mvbEntity.getDirection() == Direction.DOWN) {
			next = ((mvbEntity.getPositionY() + gFrame.cellSize) + mvbEntity.getSpeed()) / gFrame.cellSize;
			cell1Type = mapArray[leftColumn][next];
			cell2Type = mapArray[rightColumn][next];
			if (gPanel.isCollidable(cell1Type) || gPanel.isCollidable(cell2Type)) {
				mvbEntity.setCollided(true);
			}
		}
		if (mvbEntity.getDirection() == Direction.LEFT) {
			next = (mvbEntity.getPositionX() - mvbEntity.getSpeed()) / gFrame.cellSize;
			cell1Type = mapArray[next][topRow];
			cell2Type = mapArray[next][bottomRow];
			if (gPanel.isCollidable(cell1Type) || gPanel.isCollidable(cell2Type)) {
				mvbEntity.setCollided(true);
			}
		}
		if (mvbEntity.getDirection() == Direction.RIGHT) {
			next = (mvbEntity.getPositionX() + gFrame.cellSize + mvbEntity.getSpeed()) / gFrame.cellSize;
			cell1Type = mapArray[next][topRow];
			cell2Type = mapArray[next][bottomRow];
			if (gPanel.isCollidable(cell1Type) || gPanel.isCollidable(cell2Type)) {
				mvbEntity.setCollided(true);
			}
		}
	}

}
