package map;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MapTest {
	private Map map = new Map();
	private int mapArray[][];

	@BeforeEach
	void setUp() {
		map.loadMap();
		mapArray = map.getMapArray();
	}

	@Test
	void loadBonusRewardTest1() {
		map.loadBonusRewards(1);
		assertEquals(5, mapArray[29][17]);
	}

	@Test
	void loadBonusRewardTest2() {
		map.loadBonusRewards(2);
		assertEquals(0, mapArray[29][17]);
		assertEquals(5, mapArray[8][14]);
	}

	@Test
	void loadBonusRewardTest3() {
		map.loadBonusRewards(3);
		assertEquals(5, mapArray[22][3]);
		assertEquals(0, mapArray[8][14]);
	}

	@Test
	void loadBonusRewardTest4() {
		map.loadBonusRewards(4);
		assertEquals(5, mapArray[5][4]);
		assertEquals(0, mapArray[22][3]);
	}

	@Test
	void loadBonusRewardTest5() {
		map.loadBonusRewards(5);
		assertEquals(0, mapArray[5][4]);
	}

	@Test
	void checkCollidabletest() {
		assertEquals(true, map.checkColidable(1));
	}

}
