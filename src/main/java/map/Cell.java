package map;

import java.awt.image.BufferedImage;

public class Cell {
    BufferedImage img;
	private Boolean collision;
	
	public Cell() {
		this.collision = false;
	}
	
	public void setCollidable(Boolean bool) {
		collision = bool;
	}

}
