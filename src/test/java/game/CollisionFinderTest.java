package game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import moveable_entity.Celebrity;
import moveable_entity.Paparazzi;
import ui.GameFrame;
import ui.GamePanel;
import util.Direction;

class CollisionFinderTest {
	private CollisionFinder collisionFinder = new CollisionFinder();
	private Paparazzi enemy;
	private Celebrity celebrity;

	@BeforeEach
	void setUp() {
		GameFrame.getInstance().restartGame();
	}

	@Test
	void enemyUpCollisionTest() {
		enemy = new Paparazzi(27, 27);
		enemy.setDirection(Direction.UP);
		collisionFinder.checkEnemyMapCollision(enemy);
		assertTrue(enemy.getCollided());
	}

	@Test
	void enemyLeftCollisionTest() {
		enemy = new Paparazzi(27, 27);
		enemy.setDirection(Direction.LEFT);
		collisionFinder.checkEnemyMapCollision(enemy);
		assertTrue(enemy.getCollided());
	}

	@Test
	void enemyRightCollisionTest() {
		enemy = new Paparazzi(421, 27);
		enemy.setDirection(Direction.RIGHT);
		collisionFinder.checkEnemyMapCollision(enemy);
		assertTrue(enemy.getCollided());
	}

	@Test
	void enemyDownCollisionTest() {
		enemy = new Paparazzi(421, 37);
		enemy.setDirection(Direction.DOWN);
		collisionFinder.checkEnemyMapCollision(enemy);
		assertTrue(enemy.getCollided());
	}

	@Test
	void enemyOnEnemyUPCollisionTest() {
		enemy = new Paparazzi(576, 608);
		enemy.setDirection(Direction.UP);
		collisionFinder.checkEnemyCollision(enemy);
		assertTrue(enemy.getCollided());
	}

	@Test
	void enemyOnEnemyDownCollisionTest() {
		enemy = new Paparazzi(576, 544);
		enemy.setDirection(Direction.DOWN);
		collisionFinder.checkEnemyCollision(enemy);
		assertTrue(enemy.getCollided());
	}

	@Test
	void enemyOnEnemyLeftCollisionTest() {
		enemy = new Paparazzi(608, 576);
		enemy.setDirection(Direction.LEFT);
		collisionFinder.checkEnemyCollision(enemy);
		assertTrue(enemy.getCollided());
	}

	@Test
	void enemyOnEnemyRightCollisionTest() {
		enemy = new Paparazzi(544, 576);
		enemy.setDirection(Direction.RIGHT);
		collisionFinder.checkEnemyCollision(enemy);
		assertTrue(enemy.getCollided());
	}

	@Test
	void celebrityUpCollisionTest() {
		celebrity = new Celebrity(27, 27);
		celebrity.setDirection(Direction.UP);
		collisionFinder.checkMapCollision(celebrity);
		assertTrue(celebrity.getCollided());
	}

	@Test
	void celebrityLeftCollisionTest() {
		celebrity = new Celebrity(27, 27);
		celebrity.setDirection(Direction.LEFT);
		collisionFinder.checkMapCollision(celebrity);
		assertTrue(celebrity.getCollided());
	}

	@Test
	void celebrityRightCollisionTest() {
		celebrity = new Celebrity(421, 27);
		celebrity.setDirection(Direction.RIGHT);
		collisionFinder.checkMapCollision(celebrity);
		assertTrue(celebrity.getCollided());
	}

	@Test
	void celebrityDownCollisionTest() {
		celebrity = new Celebrity(421, 7);
		celebrity.setDirection(Direction.DOWN);
		collisionFinder.checkMapCollision(celebrity);
		assertTrue(celebrity.getCollided());
	}

	@Test
	void cellType1CollisionTest() {
		celebrity = new Celebrity(27, 27);
		collisionFinder.checkCellType(1, 0, 0, celebrity);
		assertTrue(celebrity.getCollided());
	}

	@Test
	void cellType2CollisionTest() {
		celebrity = new Celebrity(27, 27);
		collisionFinder.checkCellType(2, 0, 0, celebrity);
		assertTrue(celebrity.getCollided());
	}

	@Test
	void cellType3CollisionTest() {
		celebrity = new Celebrity(27, 27);
		collisionFinder.checkCellType(3, 0, 0, celebrity);
		int[][] map = GamePanel.getInstance().getMapArray();
		assertEquals(10, GameFrame.getInstance().getScore());
		assertEquals(1, collisionFinder.getDisguiseNumber());
		assertEquals(0, map[0][0]);
	}

	@Test
	void cellType4CollisionTest() {
		celebrity = new Celebrity(27, 27);
		collisionFinder.checkCellType(4, 0, 0, celebrity);
		assertTrue(celebrity.getCollided());
	}

	@Test
	void cellType5CollisionTest() {
		celebrity = new Celebrity(27, 27);
		collisionFinder.checkCellType(5, 0, 0, celebrity);
		int[][] map = GamePanel.getInstance().getMapArray();
		assertEquals(20, GameFrame.getInstance().getScore());
		assertEquals(0, map[0][0]);
	}

	@Test
	void cellType6CollisionTest() {
		celebrity = new Celebrity(27, 27);
		collisionFinder.checkCellType(6, 0, 0, celebrity);
		assertTrue(celebrity.getCollided());
	}

	@Test
	void cellType7CollisionTest() {
		celebrity = new Celebrity(27, 27);
		collisionFinder.checkCellType(7, 3, 0, celebrity);
		int[][] map = GamePanel.getInstance().getMapArray();
		boolean errorFound = false;
		for (int i = 0; i < GameFrame.getInstance().columnNum; i++) {
			if (map[i][3] == 7) {
				errorFound = true;
			}
		}
		assertFalse(errorFound);
		assertEquals(-20, GameFrame.getInstance().getScore());
	}

	@Test
	void cellType8CollisionTest() {
		celebrity = new Celebrity(27, 27);
		collisionFinder.checkCellType(8, 0, 0, celebrity);
		assertTrue(celebrity.getCollided());
	}

	@Test
	void cellType9CollisionTest() {
		celebrity = new Celebrity(27, 27);
		collisionFinder.checkCellType(9, 0, 0, celebrity);
		assertTrue(celebrity.getCollided());
	}

	@Test
	void cellType10CollisionTest() {
		celebrity = new Celebrity(27, 27);
		collisionFinder.checkCellType(10, 0, 0, celebrity);
		assertTrue(celebrity.getCollided());
	}

	@Test
	void resetDisguisesTest() {
		celebrity = new Celebrity(27, 27);
		// collect a disguise
		collisionFinder.checkCellType(3, 0, 0, celebrity);
		collisionFinder.resetDisguises();
		assertEquals(0, collisionFinder.getDisguiseNumber());

	}

	@Test
	void getDisguiseNumberTest() {
		celebrity = new Celebrity(27, 27);
		// collect a disguise
		collisionFinder.checkCellType(3, 0, 0, celebrity);
		assertEquals(1, collisionFinder.getDisguiseNumber());

	}
}
