package moveable_entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ui.GameFrame;
import util.Direction;

class PapararazziTest {
	private Paparazzi paparazzi;
	private Celebrity celebrity;
	private GameFrame gFrame = new GameFrame();

	@BeforeEach
	void setUp() {
		paparazzi = new Paparazzi(1080, 630, gFrame);
	}

	@Test
	void setDirectionTest() {
		Paparazzi PaparazziTest = new Paparazzi(1080, 630, gFrame);
		PaparazziTest.setDirection(Direction.UP);
		Direction expectedDirection = Direction.UP;
		assertEquals(expectedDirection, PaparazziTest.getDirection());
	}

	@Test
	void moveUpTest() {
		Paparazzi expectedPos = new Paparazzi(1080, 629, gFrame);
		paparazzi.move(Direction.UP);
		assertEquals(expectedPos.getPositionY(), paparazzi.getPositionY());
	}

	@Test
	void moveDownTest() {
		Paparazzi expectedPos = new Paparazzi(1080, 631, gFrame);
		paparazzi.move(Direction.DOWN);
		assertEquals(expectedPos.getPositionY(), paparazzi.getPositionY());
	}

	@Test
	void moveRightTest() {
		Paparazzi expectedPos = new Paparazzi(1081, 630, gFrame);
		paparazzi.move(Direction.RIGHT);
		assertEquals(expectedPos.getPositionX(), paparazzi.getPositionX());
	}

	@Test
	void moveLeftTest() {
		Paparazzi expectedPos = new Paparazzi(1079, 629, gFrame);
		paparazzi.move(Direction.LEFT);
		assertEquals(expectedPos.getPositionX(), paparazzi.getPositionX());
	}

	@Test
	void celebrityAboveTest() {
		celebrity = new Celebrity(32, 32, gFrame);
		paparazzi = new Paparazzi(32, 96, gFrame);
		paparazzi.update(celebrity.getPositionX(), celebrity.getPositionY());
		assertEquals(Direction.UP, paparazzi.getDirection());
	}

	@Test
	void celebrityBelowTest() {
		celebrity = new Celebrity(32, 96, gFrame);
		paparazzi = new Paparazzi(32, 32, gFrame);
		paparazzi.update(celebrity.getPositionX(), celebrity.getPositionY());
		assertEquals(Direction.DOWN, paparazzi.getDirection());
	}

	@Test
	void centerLeftTest() {
		celebrity = new Celebrity(32, 32, gFrame);
		paparazzi = new Paparazzi(39, 96, gFrame);
		paparazzi.update(celebrity.getPositionX(), celebrity.getPositionY());
		assertEquals(Direction.LEFT, paparazzi.getDirection());
	}

}
