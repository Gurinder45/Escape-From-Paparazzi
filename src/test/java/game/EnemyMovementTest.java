package game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ui.GameFrame;

class EnemyMovementTest {
	private GameFrame gFrame = new GameFrame();
	private EnemyMovement enemyMovement = new EnemyMovement(gFrame);

	@BeforeEach
	void setUp() {
	}

	@Test
	void clearTest() {
		enemyMovement.clearAll();
		assertTrue(enemyMovement.isEmptyOpened());
		assertTrue(enemyMovement.isEmptyPath());
		assertFalse(enemyMovement.getSuceeded());
	}

}
