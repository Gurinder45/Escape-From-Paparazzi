
package moveable_entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ui.GameFrame;
import util.Direction;

class CelebrityTest {
	private Celebrity celebrity;
	private GameFrame gFrame = new GameFrame();

	@BeforeEach
	void setUp() {
		celebrity = new Celebrity(1080, 630, gFrame);
	}

	@Test
	void moveUpTest(){
		Celebrity expectedPos = new Celebrity(1080, 624, gFrame);
		//celebrity.direction = Direction.UP;
		celebrity.setDirection(Direction.UP);
		celebrity.update();
		assertEquals(expectedPos.getPositionY(), celebrity.getPositionY());
	}

	@Test
	void moveDownTest(){
		Celebrity expectedPos = new Celebrity(1080, 634, gFrame);
		//celebrity.direction = Direction.UP;
		celebrity.setDirection(Direction.DOWN);
		celebrity.update();
		assertEquals(expectedPos.getPositionY(), celebrity.getPositionY());
	}

}
