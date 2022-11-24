package util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PointTest {
	private Point point;

	@BeforeEach
	void setUp() {
		point = new Point(5, 5);
	}

	@Test
	void testgetX() {
		assertEquals(5, point.getX());
	}

	@Test
	void testgetY() {
		assertEquals(5, point.getY());
	}

	@Test // test the subtraction and addition for y
	void testAddAndSubtractY() {
		point.subtractY(1);
		assertEquals(4, point.getY());
		point.addY(2);
		assertEquals(6, point.getY());
	}

	@Test // test the subtraction and addition for x
	void testAddAndSubstractX() {
		point.subtractX(1);
		assertEquals(4, point.getX());
		point.addX(2);
		assertEquals(6, point.getX());
	}

}
