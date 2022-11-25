
package moveable_entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ui.GameFrame;
import util.Direction;
import util.Point;

class CelebrityTest {
	private Celebrity celebrity;
	private GameFrame gFrame = new GameFrame();

	@BeforeEach
	void setUp() {
		celebrity = new Celebrity(1080, 630, gFrame);
	}

	@Test
	void getPositionTest() {
		Celebrity expectedCeleb = new Celebrity(1080, 630, gFrame);
		Point celebPos = celebrity.getPosition();
		Point expectedPos = expectedCeleb.getPosition();
		assertEquals(expectedPos.getX(), celebPos.getX());
		assertEquals(expectedPos.getY(), celebPos.getY());
	}

	@Test
	void getPositionXTest() {
		Celebrity expectedCeleb = new Celebrity(1080, 630, gFrame);
		int expectedX = expectedCeleb.getPositionX();
		int celebPosX = celebrity.getPositionX();
		assertEquals(expectedX, celebPosX);
	}

	@Test
	void getPositionYTest() {
		Celebrity expectedCeleb = new Celebrity(1080, 630, gFrame);
		int expectedY = expectedCeleb.getPositionY();
		int celebPosY = celebrity.getPositionY();
		assertEquals(expectedY, celebPosY);
	}

	@Test
	void setDirectionTest() {
		Celebrity celebTest = new Celebrity(1080, 630, gFrame);
		celebTest.setDirection(Direction.UP);
		Direction expectedDirection = Direction.UP;
		assertEquals(expectedDirection, celebTest.inpHandler.direction);
	}

	@Test
	void moveUpTest() {
		Celebrity expectedPos = new Celebrity(1080, 626, gFrame);
		// celebrity.direction = Direction.UP;
		celebrity.setDirection(Direction.UP);
		celebrity.update();
		assertEquals(expectedPos.getPositionY(), celebrity.getPositionY());
	}

	@Test
	void moveDownTest() {
		Celebrity expectedPos = new Celebrity(1080, 634, gFrame);
		// celebrity.direction = Direction.UP;
		celebrity.setDirection(Direction.DOWN);
		celebrity.update();
		assertEquals(expectedPos.getPositionY(), celebrity.getPositionY());
	}

	@Test
	void moveRightTest() {
		Celebrity expectedPos = new Celebrity(1084, 630, gFrame);
		// celebrity.direction = Direction.UP;
		celebrity.setDirection(Direction.RIGHT);
		celebrity.update();
		assertEquals(expectedPos.getPositionX(), celebrity.getPositionX());
	}

	@Test
	void moveLeftTest() {
		Celebrity expectedPos = new Celebrity(1076, 630, gFrame);
		// celebrity.direction = Direction.UP;
		celebrity.setDirection(Direction.LEFT);
		celebrity.update();
		assertEquals(expectedPos.getPositionX(), celebrity.getPositionX());
	}

	@Test
	void collidedTest() {
		celebrity = new Celebrity(27, 27, gFrame);
		celebrity.setDirection(Direction.UP);
		celebrity.update();
		assertEquals(27, celebrity.getPositionY());
	}

}
