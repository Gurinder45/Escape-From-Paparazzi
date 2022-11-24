package map;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CellTest {
   private Cell cell;
	@BeforeEach
	void setUp() {
		cell=new Cell();
	}

	@Test// testing getcollidable status of the cell works
	void getCollidabletest() {
		assertEquals(false, cell.getCollidable());
	}
	@Test// testing if i can set the collidable status of  cell
	void setCollidabletest(){
		cell.setCollidable(true);
		assertEquals(true, cell.getCollidable());

	}

}
