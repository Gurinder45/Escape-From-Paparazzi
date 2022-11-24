package game;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ui.GameFrame;

class EnemyMovementTest {
	private GameFrame gFrame = new GameFrame();
	private EnemyMovement enemyMovement = new EnemyMovement(gFrame);
	private Node[][] nodes;

	@BeforeEach
	void setUp() {
		enemyMovement.resetNodes();
		enemyMovement.setNodes(1, 1, 4, 1);
		nodes = enemyMovement.getNodes();
		enemyMovement.search();
	}

	@Test
	void getCostTest() {
		assertEquals(1, nodes[2][1].gCost);
		assertEquals(2, nodes[2][1].hCost);
		assertEquals(3, nodes[2][1].fCost);
	}

	@Test
	void searchTest() {
		Node current = enemyMovement.getCurrentNode();
		int row = current.rowNum;
		int column = current.columnNum;
		assertEquals(4, column);
		assertEquals(1, row);
	}

	@Test
	void openNodeTest() {
		enemyMovement.resetNodes();
		enemyMovement.setNodes(1, 1, 1, 4);
		enemyMovement.openNode(nodes[1][2]);
		ArrayList<Node> opened = enemyMovement.getOpened();
		assertTrue(nodes[1][2].open);
		assertEquals(enemyMovement.getCurrentNode(), nodes[1][2].parent);
		assertTrue(opened.get(1) == nodes[1][2]);
	}

	@Test
	void trackPathTest() {
		ArrayList<Node> path = enemyMovement.getPath();
		assertEquals(nodes[2][1], path.get(0));
		assertEquals(nodes[3][1], path.get(1));
		assertEquals(nodes[4][1], path.get(2));
	}

	@Test
	void resetNodesTest() {
		enemyMovement.resetNodes();
		int column = 0;
		int row = 0;
		boolean nodesReset = true;
		while (column < gFrame.columnNum && row < gFrame.rowNum) {
			if (nodes[column][row].open == true || nodes[column][row].checked == true
					|| nodes[column][row].isCollidable == true) {
				nodesReset = false;
			}
			column++;
			if (column == gFrame.columnNum) {
				column = 0;
				row++;
			}
		}
		assertTrue(nodesReset);
		assertTrue(enemyMovement.getOpened().isEmpty());
		assertTrue(enemyMovement.getPath().isEmpty());
		assertFalse(enemyMovement.getSuceeded());

	}

	@Test
	void getNextColumnTest() {
		assertEquals(2, enemyMovement.getNextColumn());
	}

	@Test
	void getNextRowTest() {
		assertEquals(1, enemyMovement.getNextRow());
	}
}
