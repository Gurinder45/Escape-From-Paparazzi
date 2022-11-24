package map;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.GameFrame;

class MapTest {
	private GameFrame gFrame = new GameFrame();
	private Map map=new Map(gFrame);
	private int mapArray[][];
	@BeforeEach
	void setUp() {
		map.loadMap();
		mapArray= map.getMapArray();
	}

	@Test
	void loadbonusrewardtest1() {
		map.loadBonusRewards(1);
		assertEquals(5,mapArray[29][17]);
	}
	@Test
	void loadbonusrewardtest2() {
		map.loadBonusRewards(2);
		assertEquals(0,mapArray[29][17]);
		assertEquals(5,mapArray[8][14]);
	}
	@Test
	void loadbonusrewardtest3() {
		map.loadBonusRewards(3);
		assertEquals(5,mapArray[22][3]);
		assertEquals(0,mapArray[8][14]);
	}
	@Test
	void loadbonusrewardtest4() {
		map.loadBonusRewards(4);
		assertEquals(5,mapArray[5][4]);
		assertEquals(0,mapArray[22][3]);
	}
	@Test
	void loadbonusrewardtest5() {
		map.loadBonusRewards(5);
		assertEquals(0,mapArray[5][4]);
	}

	@Test
	void checkCollidabletest(){
		assertEquals(true,map.checkColidable(1));
	}


}
