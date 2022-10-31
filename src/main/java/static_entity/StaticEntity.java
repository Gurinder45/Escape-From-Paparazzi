package static_entity;

import entity.Entity;
import ui.GameFrame;
import util.Point;

public abstract class StaticEntity extends Entity {
	
	protected int val;
	protected  GameFrame gameFrame;
	
	public StaticEntity(int x, int y, GameFrame gameFrame) {
		position = new Point(x,y);
		this.gameFrame = gameFrame;
		
	}

	
}
