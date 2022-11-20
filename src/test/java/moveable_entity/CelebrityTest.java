
package moveable_entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ui.GameFrame;

class CelebrityTest {
	private Celebrity celebrity;
	private GameFrame gFrame = new GameFrame();

	@BeforeEach
	void setUp() {
		celebrity = new Celebrity(1080, 630, gFrame);
	}

	@Test
	void Celebrity() {
		fail("Not yet implemented");
	}

}
