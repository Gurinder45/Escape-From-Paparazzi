package map;

import java.awt.image.BufferedImage;

public class Cell {
    BufferedImage img;
	private Boolean collidable;
	
	public Cell() {
		this.collidable = false;
	}
	
	public void setCollidable(Boolean bool) {
		collidable= bool;
	}
	
	public boolean getCollidable() {
		return collidable;
	}

}
