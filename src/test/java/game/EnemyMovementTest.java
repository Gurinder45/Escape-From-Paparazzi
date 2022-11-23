package game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ui.GameFrame;

class EnemyMovementTest {
	private GameFrame gFrame = new GameFrame();
	private EnemyMovement enemyMovement = new EnemyMovement(gFrame);
	private Node[][] nodes;

	@BeforeEach
	void setUp() {
		enemyMovement.clearAll();
		enemyMovement.setNodes(1, 1, 4, 1);
		nodes = enemyMovement.getNodes();
	}

	@Test
	void clearTest() {
		enemyMovement.clearAll();
		assertTrue(enemyMovement.isEmptyOpened());
		assertTrue(enemyMovement.isEmptyPath());
		assertFalse(enemyMovement.getSuceeded());
	}

	@Test
	void getCostTest() {
		assertEquals(1, nodes[2][1].gCost);
		assertEquals(2, nodes[2][1].hCost);
		assertEquals(3, nodes[2][1].fCost);
	}

	@Test
	void searchTest() {
		enemyMovement.search();
		Node current = enemyMovement.getCurrentNode();
		int row = current.rowNum;
		int column = current.columnNum;
		assertEquals(4, column);
		assertEquals(1, row);
	}
}
