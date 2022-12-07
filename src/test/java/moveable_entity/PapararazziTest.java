package moveable_entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import util.Direction;

class PapararazziTest {
	private Paparazzi paparazzi;
	private Celebrity celebrity;

	@BeforeEach
	void setUp() {
		paparazzi = new Paparazzi(1080, 630);
	}

	@Test
	void setDirectionTest() {
		Paparazzi PaparazziTest = new Paparazzi(1080, 630);
		PaparazziTest.setDirection(Direction.UP);
		Direction expectedDirection = Direction.UP;
		assertEquals(expectedDirection, PaparazziTest.getDirection());
	}

	@Test
	void moveUpTest() {
		Paparazzi expectedPos = new Paparazzi(1080, 629);
		paparazzi.move(Direction.UP);
		assertEquals(expectedPos.getPositionY(), paparazzi.getPositionY());
	}

	@Test
	void moveDownTest() {
		Paparazzi expectedPos = new Paparazzi(1080, 631);
		paparazzi.move(Direction.DOWN);
		assertEquals(expectedPos.getPositionY(), paparazzi.getPositionY());
	}

	@Test
	void moveRightTest() {
		Paparazzi expectedPos = new Paparazzi(1081, 630);
		paparazzi.move(Direction.RIGHT);
		assertEquals(expectedPos.getPositionX(), paparazzi.getPositionX());
	}

	@Test
	void moveLeftTest() {
		Paparazzi expectedPos = new Paparazzi(1079, 629);
		paparazzi.move(Direction.LEFT);
		assertEquals(expectedPos.getPositionX(), paparazzi.getPositionX());
	}

	@Test
	void celebrityAboveTest() {
		celebrity = new Celebrity(32, 32);
		paparazzi = new Paparazzi(32, 96);
		paparazzi.update(celebrity.getPositionX(), celebrity.getPositionY());
		assertEquals(Direction.UP, paparazzi.getDirection());
	}

	@Test
	void celebrityBelowTest() {
		celebrity = new Celebrity(32, 96);
		paparazzi = new Paparazzi(32, 32);
		paparazzi.update(celebrity.getPositionX(), celebrity.getPositionY());
		assertEquals(Direction.DOWN, paparazzi.getDirection());
	}

	@Test
	void celebrityLeftTest() {
		celebrity = new Celebrity(32, 32);
		paparazzi = new Paparazzi(96, 32);
		paparazzi.update(celebrity.getPositionX(), celebrity.getPositionY());
		assertEquals(Direction.LEFT, paparazzi.getDirection());
	}

	@Test
	void celebrityRightTest() {
		celebrity = new Celebrity(96, 32);
		paparazzi = new Paparazzi(32, 32);
		paparazzi.update(celebrity.getPositionX(), celebrity.getPositionY());
		assertEquals(Direction.RIGHT, paparazzi.getDirection());
	}

	@Test
	void upCollisionMoveLeftTest() {
		celebrity = new Celebrity(352, 32);
		paparazzi = new Paparazzi(326, 91);
		paparazzi.update(celebrity.getPositionX(), celebrity.getPositionY());
		assertEquals(Direction.LEFT, paparazzi.getDirection());
	}

	@Test
	void upCollisionMoveRightTest() {
		celebrity = new Celebrity(64, 192);
		paparazzi = new Paparazzi(90, 251);
		paparazzi.update(celebrity.getPositionX(), celebrity.getPositionY());
		assertEquals(Direction.RIGHT, paparazzi.getDirection());
	}

	@Test
	void downCollisionMoveLeftTest() {
		celebrity = new Celebrity(352, 96);
		paparazzi = new Paparazzi(326, 37);
		paparazzi.update(celebrity.getPositionX(), celebrity.getPositionY());
		assertEquals(Direction.LEFT, paparazzi.getDirection());
	}

	@Test
	void downCollisionMoveRightTest() {
		celebrity = new Celebrity(64, 256);
		paparazzi = new Paparazzi(90, 196);
		paparazzi.update(celebrity.getPositionX(), celebrity.getPositionY());
		assertEquals(Direction.RIGHT, paparazzi.getDirection());
	}

}
