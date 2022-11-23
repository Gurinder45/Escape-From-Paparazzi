package moveable_entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ui.GameFrame;
import ui.GamePanel; //not sure if we rly need it
import util.Direction;

class PapararazziTest {
	private Paparazzi paparazzi;
	private GameFrame gFrame = new GameFrame();
	private GamePanel gPanel = new GamePanel(gFrame);

	@BeforeEach
	void setUp() {
		paparazzi = new Paparazzi(1080, 630, gFrame);
	}

	@Test
	void setDirectionTest(){
		Paparazzi PaparazziTest = new Paparazzi(1080, 630, gFrame);
		PaparazziTest.setDirection(Direction.UP);
		Direction expectedDirection = Direction.UP;
		assertEquals(expectedDirection, PaparazziTest.getDirection());
	}	

	@Test
	void moveUpTest(){
		Paparazzi expectedPos = new Paparazzi(1080, 629, gFrame);
		paparazzi.move(Direction.UP);
		assertEquals(expectedPos.getPositionY(), paparazzi.getPositionY());
	}
	@Test
	void moveDownTest(){
		Paparazzi expectedPos = new Paparazzi(1080, 631, gFrame);
		paparazzi.move(Direction.DOWN);
		assertEquals(expectedPos.getPositionY(), paparazzi.getPositionY());
	}

	@Test
	void moveRightTest(){
		Paparazzi expectedPos = new Paparazzi(1081, 630, gFrame);
		paparazzi.move(Direction.RIGHT);
		assertEquals(expectedPos.getPositionX(), paparazzi.getPositionX());
	}

	@Test
	void moveLeftTest(){
		Paparazzi expectedPos = new Paparazzi(1079, 629, gFrame);
		paparazzi.move(Direction.LEFT);
		assertEquals(expectedPos.getPositionX(), paparazzi.getPositionX());
	}

}
